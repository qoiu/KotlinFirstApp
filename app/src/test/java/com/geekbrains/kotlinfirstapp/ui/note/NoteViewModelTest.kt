package com.geekbrains.kotlinfirstapp.ui.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteViewModelTest(){
/*
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    //mockrepo -> testViewModel -> livedata

    private val mockRepository= mockk<Repository>()//допускается relaxed=true, проверяет что вернул любое значение
    private val noteLiveData = MutableLiveData<NoteResult>()

    private lateinit var viewModel: NoteViewModel
    private val testNote= Note("1", "2", "3")

    @Before
    fun setup(){
        clearAllMocks()
        every { mockRepository.getNoteById(testNote.id) } returns noteLiveData
        every { mockRepository.deleteNote(testNote.id) } returns noteLiveData
        every { mockRepository.saveNote(any()) } returns noteLiveData
        viewModel = NoteViewModel(mockRepository)
    }

    @Test
    fun `loadNote should returns NoteData`(){
        var result:NoteData.Data? = null
        val testData = NoteData.Data(false,testNote)
        viewModel.getViewState().observeForever{
            result=it?.data
        }
        viewModel.loadNote(testNote.id)
        noteLiveData.value = NoteResult.Success(testNote)
        assertEquals(testData, result)
    }

    @Test
    fun `loadNote should returns error`(){
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever{
            result=it?.error
        }
        viewModel.loadNote(testNote.id)
        noteLiveData.value = NoteResult.Error(error = testData)
        Assert.assertEquals(testData, result)
    }

    @Test
    fun `delete should returns NoteData with isDeleted`(){
        var result:NoteData.Data? = null
        val testData = NoteData.Data(true,null)
        viewModel.getViewState().observeForever{
            result=it?.data
        }
        viewModel.save(testNote)
        viewModel.deleteNote()
        noteLiveData.value = NoteResult.Success(null)
        assertEquals(testData, result)
    }

    @Test
    fun `delete should returns error`(){
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever{
            result=it?.error
        }
        viewModel.save(testNote)
        viewModel.deleteNote()
        noteLiveData.value = NoteResult.Error(testData)
        assertEquals(testData, result)
    }

    @Test
    fun `should save changes`(){

        viewModel.save(testNote)
        viewModel.onCleared()
        verify(exactly = 1) { mockRepository.saveNote(testNote) }
    }

*/
}