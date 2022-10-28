package varo.jose.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import varo.jose.criminalintent.Crime
import java.util.UUID

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    suspend fun getCrime(id: UUID): Crime
}