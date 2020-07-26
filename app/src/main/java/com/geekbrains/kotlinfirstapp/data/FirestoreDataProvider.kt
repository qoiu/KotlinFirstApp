package com.geekbrains.kotlinfirstapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.entity.User
import com.geekbrains.kotlinfirstapp.data.error.NoAuthException
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDataProvider : DataProvider {
    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }

    private val store = FirebaseFirestore.getInstance()

    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    private fun userNoteCollection() = currentUser?.let {
        store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()


    override fun getCurrentUser(): LiveData<User> = MutableLiveData<User>().apply {
        value = currentUser?.let {
            User(it.displayName ?: "", it.email ?: "")
        }

    }

    override fun subscribeToAllNotes(): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            userNoteCollection().addSnapshotListener { snapshot, e ->
                e?.let {
                    value = NoteResult.Error(e)
                } ?: snapshot?.let {
                    val notes: List<Note?> = snapshot.documents.map { doc -> doc.toObject(Note::class.java) }
                    value = NoteResult.Success(notes)
                }
            }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }

    }

    override fun getNoteById(id: String): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            userNoteCollection().document(id).get()
                    .addOnSuccessListener { snapshot ->
                        value = NoteResult.Success(snapshot.toObject(Note::class.java))
                    }.addOnFailureListener {
                        value = NoteResult.Error(it)
                    }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }

    }

    override fun saveNote(note: Note): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            userNoteCollection().document(note.id).set(note)
                    .addOnSuccessListener { snapshot ->
                        value = NoteResult.Success(note)
                    }.addOnFailureListener {
                        value = NoteResult.Error(it)
                    }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }
}