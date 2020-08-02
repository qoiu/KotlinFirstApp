package com.geekbrains.kotlinfirstapp.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.main.MainActivity
import com.geekbrains.kotlinfirstapp.ui.main.MainViewModel
import com.geekbrains.kotlinfirstapp.ui.main.MainViewState
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class NoteActivityTest {
    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, true, false)

    private val model = mockk<MainViewModel>(relaxed = true)
    private val viewStateLiveData = MutableLiveData<MainViewState>()
    private val testNotes = listOf(
            Note("1", "tit1", "text1`"),
            Note("2", "tit2", "text2`"),
            Note("3", "tit3", "text3`")
    )

    @Before
    fun setup() {
        loadKoinModules(
                listOf(
                        module {
                            viewModel { model }
                        }
                )
        )
        every { model.getViewState() } returns viewStateLiveData
        activityTestRule.launchActivity(null)
        viewStateLiveData.postValue(MainViewState(notes = testNotes))
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}