package com.github.spike.ram

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.github.spike.ram.ui.theme.RickAndMortyTheme

const val RESULT_TAG = "sampleTag"

class ReceiverActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ReceiverScreen(
                        onFinishActivity = {
                            val resultIntent = Intent().apply {
                                putExtra(RESULT_TAG, 123)
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        }
                    )
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val resultValue: Int?  = data?.extras?.getInt(RESULT_TAG)
            showResultValueAsToast(resultValue)
        }
    }

    private fun showResultValueAsToast(resultValue: Int?) {
        val toastText = if (resultValue != null) {
            "ResultCode: $resultValue"
        } else {
            "Received invalid resultCode"
        }
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
    }

}
