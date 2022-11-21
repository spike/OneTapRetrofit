package com.github.spike.ram

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.spike.ram.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    private val intentResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        // see 4:29 timestamp on youtube
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen(
                        onStartReceiverActivity = {
                            val intent = Intent(this, ReceiverActivity::class.java)
                            startActivityForResult(intent, 1337)
                        }
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val resultValue: Int? = data?.extras?.getInt(RESULT_TAG)

            showResultValueAsToast(resultValue)
        }
    }

    private fun showResultValueAsToast(resultValue: Int?) {
        val toastText= if (resultValue != null) {
            "ResultCode: $resultValue"
        } else {
            "Received invalid resultCode"
        }
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {
        Greeting("Android")
    }
}