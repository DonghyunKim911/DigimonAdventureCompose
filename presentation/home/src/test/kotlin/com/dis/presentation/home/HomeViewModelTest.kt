package com.dis.presentation.home

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test
import com.core.presentation.mapper.toPresentation
import com.dis.core.domain.model.Content
import com.dis.domain.digimon.repository.DigimonRepository
import com.dis.domain.digimon.usecase.GetDigimonListUseCase
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : FunSpec({
    test("initial collection loads first page and exposes mapped items") {
        runHomeViewModelTest {
            val firstPage = createContents(count = HomeViewModel.DEFAULT_PAGE_SIZE)
            val repository = repositoryWithPages(0 to firstPage)
            val viewModel = HomeViewModel(GetDigimonListUseCase(repository))

            viewModel.state.test {
                val loadedState = awaitState { !it.isLoading && it.digimonList.size == firstPage.size }

                loadedState.digimonList shouldBe firstPage.map { it.toPresentation() }
                loadedState.isLastPageReached shouldBe false

                cancelAndIgnoreRemainingEvents()
            }

            verify(exactly = 1) { repository.getDigimonList(0) }
            confirmVerified(repository)
        }
    }

    test("fetch next action appends next page results") {
        runHomeViewModelTest {
            val firstPage = createContents(count = HomeViewModel.DEFAULT_PAGE_SIZE)
            val secondPage = createContents(startId = firstPage.size + 1, count = 2)
            val repository = repositoryWithPages(0 to firstPage, 1 to secondPage)
            val viewModel = HomeViewModel(GetDigimonListUseCase(repository))

            viewModel.state.test {
                awaitState { !it.isLoading && it.digimonList.size == firstPage.size }

                viewModel.onAction(HomeAction.OnFetchNextDigimonList)

                val appendedState = awaitState {
                    !it.isLoading && it.digimonList.size == firstPage.size + secondPage.size
                }

                appendedState.digimonList shouldBe (firstPage + secondPage).map { it.toPresentation() }
                appendedState.isLastPageReached shouldBe true

                cancelAndIgnoreRemainingEvents()
            }

            verify(exactly = 1) { repository.getDigimonList(0) }
            verify(exactly = 1) { repository.getDigimonList(1) }
            confirmVerified(repository)
        }
    }

    test("fetch next action is ignored after the last page") {
        runHomeViewModelTest {
            val firstPage = createContents(count = 2)
            val repository = repositoryWithPages(0 to firstPage)
            val viewModel = HomeViewModel(GetDigimonListUseCase(repository))

            viewModel.state.test {
                val loadedState = awaitState { !it.isLoading && it.digimonList.size == firstPage.size }
                loadedState.isLastPageReached shouldBe true

                viewModel.onAction(HomeAction.OnFetchNextDigimonList)

                expectNoEvents()
                cancelAndIgnoreRemainingEvents()
            }

            verify(exactly = 1) { repository.getDigimonList(0) }
            verify(exactly = 0) { repository.getDigimonList(1) }
            confirmVerified(repository)
        }
    }

    test("digimon click emits detail navigation event") {
        runHomeViewModelTest {
            val repository = mockk<DigimonRepository>(relaxed = true)
            val viewModel = HomeViewModel(GetDigimonListUseCase(repository))

            viewModel.eventChannel.test {
                viewModel.onAction(HomeAction.OnDigimonClick(id = 42))

                awaitItem() shouldBe HomeEvent.NavigateToDigimonDetail(id = 42)
                cancelAndIgnoreRemainingEvents()
            }

            verify(exactly = 0) { repository.getDigimonList(any()) }
            confirmVerified(repository)
        }
    }
})

@OptIn(ExperimentalCoroutinesApi::class)
private fun runHomeViewModelTest(block: suspend TestScope.() -> Unit) {
    Dispatchers.setMain(UnconfinedTestDispatcher())

    try {
        runTest {
            block()
        }
    } finally {
        Dispatchers.resetMain()
    }
}

private fun repositoryWithPages(vararg pages: Pair<Int, List<Content>>): DigimonRepository {
    val pageMap = pages.toMap()

    return mockk {
        every { getDigimonList(any()) } answers {
            val page = invocation.args[0] as Int
            val content = pageMap[page] ?: error("Unexpected page request: $page")
            flowOf(content)
        }
    }
}

private suspend fun ReceiveTurbine<HomeUiState>.awaitState(predicate: (HomeUiState) -> Boolean): HomeUiState {
    while (true) {
        val state = awaitItem()
        if (predicate(state)) {
            return state
        }
    }
}

private fun createContents(startId: Int = 1, count: Int): List<Content> =
    (startId until startId + count).map { id ->
        Content(
            href = "https://digimon-api.com/api/v1/digimon/$id",
            id = id,
            name = "Digimon $id",
            image = "https://example.com/digimon/$id.png",
        )
    }
