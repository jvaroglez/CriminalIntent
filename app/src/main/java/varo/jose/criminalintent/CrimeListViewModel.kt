package varo.jose.criminalintent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "CrimeListViewModel"

class CrimeListViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get()

    //val crimes = crimeRepository.getCrimes()
    private val _crimes: MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())
    val crimes: StateFlow<List<Crime>>
    get() = _crimes.asStateFlow()

    init {
        //Log.d(TAG, "init starting")
        viewModelScope.launch {
            crimeRepository.getCrimes().collect {
                _crimes.value = it
            }
            /*Log.d(TAG, "coroutine launched")

            crimes += loadCrimes()
            Log.d(TAG, "Loading crimes finished")*/
        }
    }

    /*suspend fun loadCrimes(): List<Crime> {
        val result = mutableListOf<Crime>()
        delay(5000)
        for (i in 1 until 101) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0
            )
            result += crime
        }
        return result
        return crimeRepository.getCrimes()
    }*/
}