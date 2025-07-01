// app/src/main/java/com/example/sportteammakerproject/data/repository/FirestoreRepo.kt
package com.example.sportteammakerproject.data.repository

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.ui.semantics.error
import androidx.core.util.remove
import com.example.sportteammakerproject.data.model.Match
import com.example.sportteammakerproject.data.model.Player
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreRepo {

    private val db = Firebase.firestore
    private val playersCollectionRef = db.collection("players")
    private val matchesCollectionRef = db.collection("matches")

    suspend fun addMatch(match: Match): Result<String> {
        return try {
            val documentRef = matchesCollectionRef.add(match).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Log.e("FirestoreRepo", "Maç eklenirken hata oluştu", e)
            Result.failure(e)
        }
    }

    suspend fun addPlayer(player: Player): Result<String> {
        return try {
            val documentRef = playersCollectionRef.add(player).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Log.e("FirestoreRepo", "Oyuncu eklenirken hata oluştu", e)
            Result.failure(e)
        }
    }

    fun getAllPlayers(): Flow<Result<List<Player>>> = callbackFlow {
        var snapshotListener: ListenerRegistration? = null

        try {
            snapshotListener = playersCollectionRef.addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("FirestoreRepo", "Tüm oyuncuları dinlerken hata: ${error.message}", error)
                    trySend(Result.failure(error))
                    close(error)
                    return@addSnapshotListener
                }

                val playersList = querySnapshot
                    ?.documents
                    ?.mapNotNull { it.toObject(Player::class.java) }
                    ?: emptyList()

                Log.d("FirestoreRepo", "Oyuncular güncellendi, ${playersList.size} oyuncu bulundu.")
                trySend(Result.success(playersList))
            }
        } catch (e: Exception) {
            Log.e(
                "FirestoreRepo",
                "getAllPlayersRealtime dinleyici eklenirken hata: ${e.message}",
                e
            )
            trySend(Result.failure(e))
            close(e)
        }

        awaitClose {
            Log.d("FirestoreRepo", "getAllPlayersRealtime dinleyicisi kaldırılıyor.")
            snapshotListener?.remove()
        }
    }

    fun getAllMatches(): Flow<Result<List<Match>>> = callbackFlow {
        var snapshotListener: ListenerRegistration? = null

        try {
            snapshotListener = matchesCollectionRef.addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("FirestoreRepo", "Tüm maçları dinlerken hata: ${error.message}", error)
                    trySend(Result.failure(error))

                    close(error)
                    return@addSnapshotListener
                }

                val matchesList = querySnapshot
                    ?.documents
                    ?.mapNotNull { it.toObject(Match::class.java) }
                    ?: emptyList()

                Log.d("FirestoreRepo", "Maçlar güncellendi, ${matchesList.size} maç bulundu.")
                trySend(Result.success(matchesList))
            }
        } catch (e: Exception) {
            Log.e("FirestoreRepo", "getAllMatchesRealtime dinleyici eklenirken istisna: ${e.message}", e)
            trySend(Result.failure(e))
            close(e)
        }

        awaitClose {
            Log.d("FirestoreRepo", "getAllMatchesRealtime dinleyicisi kaldırılıyor.")
            snapshotListener?.remove()
        }
    }
}


