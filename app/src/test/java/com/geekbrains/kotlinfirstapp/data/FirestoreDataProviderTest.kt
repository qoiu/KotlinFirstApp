package com.geekbrains.kotlinfirstapp.data

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.error.NoAuthException
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.Error

class FirestoreDataProviderTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val mockDb = mockk<FirebaseFirestore>()
    private val mockAuth = mockk<FirebaseAuth>()
    private val provider = FirestoreDataProvider(mockDb,mockAuth)
    private val mockResultsCollection = mockk<CollectionReference>()
    private val mockUser = mockk<FirebaseUser>()
    private val mockDocument1 = mockk<DocumentSnapshot>()
    private val mockDocument2 = mockk<DocumentSnapshot>()
    private val mockDocument3 = mockk<DocumentSnapshot>()
    private val testNotes = listOf(Note("1"),Note("2"), Note("3"))


    @Before
    fun setUp() {
        clearAllMocks()
        every { mockAuth.currentUser } returns mockUser
        every { mockUser.uid } returns ""
        coEvery { mockDb.collection(any()).document(any()).collection(any()) } returns mockResultsCollection
        every { mockDocument1.toObject(Note::class.java) } returns testNotes[0]
        every { mockDocument2.toObject(Note::class.java) } returns testNotes[1]
        every { mockDocument3.toObject(Note::class.java) } returns testNotes[2]

    }

    @Test
    fun `subscribeToAllNotes should throw exception if no auth`() = runBlocking{
        var result: Any? = null
        every { mockAuth.currentUser } returns null
        result= provider.subscribeToAllNotes().receive() as NoteResult.Error
        assertTrue(result is NoAuthException)
    }

    @Test
    fun `subscribe all notes returns notes`() = runBlocking{
        var result: List<Note>? = null
        val mockSnapshot = mockk<QuerySnapshot>()
        val slot = slot<EventListener<QuerySnapshot>>()
        every { mockSnapshot.documents } returns listOf(mockDocument1,mockDocument2,mockDocument3)
        every { mockResultsCollection.addSnapshotListener(capture(slot)) } returns mockk()
        result = provider.subscribeToAllNotes().receive() as? List<Note>?
        slot.captured.onEvent(mockSnapshot,null)
        assertEquals(testNotes,result)
    }

    @Test
    fun `subscribe all notes returns throwable`()= runBlocking{
        var result: Throwable? = null
        val testError = mockk<FirebaseFirestoreException>()
        val slot = slot<EventListener<QuerySnapshot>>()
        every { mockResultsCollection.addSnapshotListener(capture(slot)) } returns mockk()
        result = provider.subscribeToAllNotes().receive() as? Throwable?
        slot.captured.onEvent(null,testError)
        assertEquals(testError,result)
    }

    @Test
    fun `saveNote calls set`()= runBlocking{
        val mockDocumentReference = mockk<DocumentReference>()
        every { mockResultsCollection.document(testNotes[0].id) } returns mockDocumentReference
        provider.saveNote(testNotes[0])
        verify(exactly = 1) { mockDocumentReference.set(testNotes[0]) }
    }

    @Test
    fun `saveNote returns note`() = runBlocking{
        var result: Note? = null
        val mockDocumentReference = mockk<DocumentReference>()
        val slot = slot<OnSuccessListener<in Void>>()
        every { mockResultsCollection.document(testNotes[0].id) } returns mockDocumentReference
        every { mockDocumentReference.set(testNotes[0]).addOnSuccessListener(capture(slot)) } returns mockk()
        result = provider.saveNote(testNotes[0])
        slot.captured.onSuccess(null)
        assertEquals(testNotes[0],result)
    }
/*
    @Test
    fun `deleteNote calls delete`(){
        val mockDocumentReference = mockk<DocumentReference>()
        every { mockResultsCollection.document(testNotes[0].id) } returns mockDocumentReference
        provider.deleteNote(testNotes[0].id)
        verify(exactly = 1) { mockDocumentReference.delete() }
    }

    @Test
    fun `delete returns null`(){
        var result: Note? = null
        val mockDocumentReference = mockk<DocumentReference>()
        val slot = slot<OnSuccessListener<in Void>>()
        every { mockResultsCollection.document(testNotes[0].id) } returns mockDocumentReference
        every { mockDocumentReference.delete().addOnSuccessListener(capture(slot)) } returns mockk()
        provider.deleteNote(testNotes[0].id).observeForever{
            result = (it as? NoteResult.Success<Note>)?.data
        }
        slot.captured.onSuccess(null)
        assertEquals(null,result)
    }

    @Test
    fun `get currentUser`(){
        var result: User? = null
        val testUser = User("as","as@")
        every { mockAuth.currentUser } returns mockUser
        every { mockUser.displayName } returns "as"
        every { mockUser.email } returns "as@"
        provider.getCurrentUser().observeForever{
            result=(it as? User)
        }
        assertEquals(testUser,result)

    }

    @Test
    fun `getNoteById should throw exception if no auth`(){
        var result: Any? = null
        every { mockAuth.currentUser } returns null
        provider.getNoteById("").observeForever(){
            result= (it as? NoteResult.Error)?.error
        }
        assertTrue(result is NoAuthException)
    }


    @Test
    fun `getNoteById returns notes`(){
        var result: Note? = null
        val mockDocumentReference = mockk<DocumentReference>()
        val mockDocument = mockk<DocumentSnapshot>()
        val slot = slot<OnSuccessListener<in DocumentSnapshot>>()
        every { mockResultsCollection.document(testNotes[0].id) } returns mockDocumentReference
        every { mockDocument.toObject(Note::class.java) } returns testNotes[0]

        every { mockDocumentReference.get().addOnSuccessListener(capture(slot)) } returns mockk()
        provider.getNoteById(testNotes[0].id).observeForever{
            result = (it as? NoteResult.Success<Note>)?.data
        }
        slot.captured.onSuccess(mockDocument)
        assertEquals(testNotes[0],result)
    }*/
}