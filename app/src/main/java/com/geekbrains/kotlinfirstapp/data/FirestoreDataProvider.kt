package com.geekbrains.kotlinfirstapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDataProvider : DataProvider {
    companion object {
        private const val NOTES_COLLECTION = "notes"
    }

    private val store = FirebaseFirestore.getInstance()
    private val notesReference = store.collection(NOTES_COLLECTION)

    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.addSnapshotListener { snapshot, e ->
            e?.let {
                result.value = NoteResult.Error(e)
            } ?: snapshot?.let {
                val notes: List<Note?> = snapshot.documents.map { doc -> doc.toObject(Note::class.java) }
                result.value = NoteResult.Success(notes)
            }
        }

        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.document(id).get()
                .addOnSuccessListener { snapshot ->
                    result.value = NoteResult.Success(snapshot.toObject(Note::class.java))
                }.addOnFailureListener {
                    result.value = NoteResult.Error(it)
                }
        return result
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.document(note.id).set(note)
                .addOnSuccessListener { snapshot ->
                    result.value = NoteResult.Success(note)
                }.addOnFailureListener {
                    result.value = NoteResult.Error(it)
                }
        return result
    }
}