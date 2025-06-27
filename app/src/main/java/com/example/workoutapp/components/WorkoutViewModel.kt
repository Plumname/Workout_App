package com.example.workoutapp.viewmodel

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workoutapp.R
import com.example.workoutapp.components.Excercise

class WorkoutViewModel : ViewModel() {


    private val exercises = listOf( // alle excercises.
        Excercise("Jumping Jacks", "Cardio Aufwärmen", R.drawable.jumping_jacks, 30, 10),
        Excercise("Squats", "Beintraining", R.drawable.squats, 30, 10),
        Excercise("Push-ups", "Oberkörper", R.drawable.push_up, 30, 10),
        Excercise("Plank", "Core Training", R.drawable.plank, 30, 10)
    )

    // MutableLiveData zur Speicherung der verbleibenden Zeit (Sekunden).
    private val _currentTime = MutableLiveData<Int>()
    // öffentliches LiveData, damit UI beobachten kann, aber nicht direkt ändern kann.
    val currentTime: LiveData<Int> = _currentTime

    // aktueller Index der Übung in der Liste (0-basiert)
    private val _currentExerciseIndex = MutableLiveData<Int>()
    val currentExerciseIndex: LiveData<Int> = _currentExerciseIndex

    // gibt an, ob gerade Pause (Rest) ist oder aktiv geübt wird.
    private val _isResting = MutableLiveData<Boolean>()
    val isResting: LiveData<Boolean> = _isResting

    // zeigt an, ob das gesamte Workout beendet ist.
    private val _isWorkoutFinished = MutableLiveData<Boolean>()
    val isWorkoutFinished: LiveData<Boolean> = _isWorkoutFinished

    // Timer für Countdown der Übungen und Pausen.
    private var timer: CountDownTimer? = null

    // liefert die Liste der Übungen.
    fun getExercises(): List<Excercise> = exercises

    // startet das Workout: setzt Übungsindex auf 0 und markiert Workout als noch nicht fertig.
    fun startWorkout() {
        _currentExerciseIndex.value = 0
        _isWorkoutFinished.value = false
        startExercise() // Startet die erste Übung.
    }

    // startet eine Übung basierend auf dem aktuellen Index.
    private fun startExercise() {
        val index = _currentExerciseIndex.value ?: return // falls null, nichts tun.
        val current = exercises.getOrNull(index) ?: return // Übung holen oder abbrechen.

        _isResting.value = false // wir sind in der aktiven Übungsphase.
        _currentTime.value = current.timeActive // Zeit für die Übung setzen durch timeActive.

        timer?.cancel() // falls Timer noch läuft, stoppen.
        timer = object : CountDownTimer(current.timeActive * 1000L, 1000L) {

            override fun onTick(millisUntilFinished: Long) { // Wird jede Sekunde aufgerufen.
                _currentTime.value = (millisUntilFinished / 1000).toInt() // Aktualisiere verbleibende Zeit
            }
            override fun onFinish() { // Wenn die Zeit abgelaufen ist, starte die Pause.
                startRest()
            }
        }.start()
    }

    // Startet die Pause nach einer Übung
    private fun startRest() {
        _isResting.value = true //wir sind jetzt in der Pause.
        val index = _currentExerciseIndex.value ?: return
        val current = exercises.getOrNull(index) ?: return
        val rest = current.timeBreak
        _currentTime.value = rest // Zeit der Pause setzen

        timer?.cancel()
        timer = object : CountDownTimer(rest * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / 1000).toInt() // Verbleibende Pausenzeit aktualisieren
            }

            override fun onFinish() {
                // Nach der Pause: nächstes Exercise starten oder Workout als fertig markieren
                val nextIndex = _currentExerciseIndex.value?.plus(1) ?: return
                if (nextIndex < exercises.size) {
                    _currentExerciseIndex.value = nextIndex // Index erhöhen, also zur nächsten Übung.
                    startExercise() // Nächste Übung starten.
                } else {
                    _isWorkoutFinished.value = true // Workout beendet.
                }
            }
        }.start()
    }

    // wrd aufgerufen, wenn ViewModel zerstört wird, z.B. wenn Activity weg ist.
    override fun onCleared() {
        super.onCleared()
        timer?.cancel() // Timer stoppen.
    }
}