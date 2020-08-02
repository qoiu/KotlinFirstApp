package com.geekbrains.kotlinfirstapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class MainActivityTest {

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

    @Test
    fun check_data_is_displayed() {
        onView(withId(R.id.mainRecycler)).perform(scrollToPosition<NotesRVAdapter.ViewHolder>(1))
        onView(withText(testNotes[1].text)).check(matches(isDisplayed()))
    }
}