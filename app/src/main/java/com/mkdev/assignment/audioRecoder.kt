//package com.mkdev.assignment
//
//import android.content.Context
//import android.media.MediaRecorder
//import android.os.Environment
//import android.util.Log
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.lifecycle.ViewModel
//import androidx.viewpager2.adapter.FragmentViewHolder
//import com.mkdev.assignment.viewModel.shareViewModel
//import java.io.File
//import java.io.IOException
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//import kotlin.apply
//import kotlin.text.format
//
//open class audioRecoder(private val context: Context) {
//
//    private lateinit var mediaRecorder: MediaRecorder
//    private lateinit var audioFilePath: File
//
//   var shareViewModel = shareViewModel()
//
//    fun startRecording() {
//       // audioFilePath = File(context.getExternalFilesDir(null), "answer_audio.wav")
//
//        mediaRecorder = MediaRecorder().apply {
//            setAudioSource(MediaRecorder.AudioSource.MIC)
//            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
//            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
//            val ab = getAudioFilePath()
//            setOutputFile(ab)
//            try {
//                prepare()
//                start()
//            } catch (e: IOException) {
//                Log.e("AudioRecording", "prepare() failed")
//            } catch (e: IllegalStateException) {
//                Log.e("AudioRecording", "start() failed")
//            }
//        }
//    }
//
//    private fun getAudioFilePath(): String {
//        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss",
//            Locale.getDefault()).format(Date())
//        val fileName = "audio_${timestamp}.3gp"
//        shareViewModel.addData("audio",fileName)
//        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC) // Replace with desired directory
//        return File(storageDir, fileName).absolutePath
//    }
//    fun stopRecording() {
//        mediaRecorder.apply {
//            this!!.stop()
//            release()
//        }
//    }
//
//}