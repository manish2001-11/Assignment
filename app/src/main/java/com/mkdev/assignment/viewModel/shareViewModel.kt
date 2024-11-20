package com.mkdev.assignment.viewModel

import android.app.Application
import android.media.MediaRecorder
import androidx.constraintlayout.widget.SharedValues
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.IOException

class shareViewModel(application: Application): AndroidViewModel(application) {

    private var mediaRecorder: MediaRecorder? = null
    var audioFile: File? = null

    private val _formData= MutableLiveData<MutableMap<String , Any>>()
    val formData: LiveData<MutableMap<String , Any>> = _formData

    private val _submissionTime = MutableLiveData<String>()
    val submissionTime: LiveData<String> = _submissionTime

    fun addData(key: String,value: Any){

        val currentData = _formData.value ?:mutableMapOf()
        currentData[key] = value
        _formData.value = currentData
    }

//
//    // Start audio recording
//    fun startRecording() {
//        val context = getApplication<Application>().applicationContext
//        val storageDir = context.getExternalFilesDir("AudioRecords")
//        if (storageDir?.exists() == false) {
//            storageDir.mkdirs()
//        }
//
//        audioFile = File(storageDir, "AUDIO_${System.currentTimeMillis()}.m4a")
//
//        mediaRecorder = MediaRecorder().apply {
//            setAudioSource(MediaRecorder.AudioSource.MIC)
//            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
//            setOutputFile(audioFile?.absolutePath)
//            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
//            try {
//                prepare()
//                start()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    // Stop audio recording
//    fun stopRecording() {
//        mediaRecorder?.apply {
//            try {
//                stop()
//                reset()
//                release()
//            } catch (e: IllegalStateException) {
//                e.printStackTrace()
//            }
//        }
//        mediaRecorder = null
//    }
//



}