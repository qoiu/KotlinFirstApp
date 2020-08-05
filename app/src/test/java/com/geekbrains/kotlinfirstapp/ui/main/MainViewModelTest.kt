package com.geekbrains.kotlinfirstapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.channels.ReceiveChannel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest{
/*
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    //mockrepo -> testViewModel -> livedata

    private val mockReository=mockk<Repository>()
    private val notesLiveData = ReceiveChannel<NoteResult>()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        clearAllMocks()
        every { mockReository.getNotes() } returns notesLiveData
        viewModel = MainViewModel(mockReository)
    }

    @Test
    fun `should call getNotes once`(){
        verify(exactly = 1) { mockReository.getNotes() }
    }

    @Test
    fun `should returns Notes`(){
        var result: List<Note>? = null
        val testData = listOf(Note("1"),Note("2"))
        viewModel.getViewState().observeForever{
            result=it?.data
        }
        notesLiveData.value = NoteResult.Success(testData)
        assertEquals(testData,result)
    }

    @Test
    fun `should returns error`(){
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever{
            result=it?.error
        }
        notesLiveData.value = NoteResult.Error(error = testData)
        assertEquals(testData,result)
    }

    @Test
    fun `should remove observer`(){
        viewModel.onCleared()
        assertFalse(notesLiveData.hasObservers())
    }

 */
}