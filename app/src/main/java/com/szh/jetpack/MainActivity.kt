package com.szh.jetpack

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.szh.jetpack.composable.TextFieldPage
import com.szh.jetpack.composable.UiList
import com.szh.jetpack.ui.theme.JetpackDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val context:MainActivity by lazy { this }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val controller = rememberNavController()
                    NavHost(navController = controller, startDestination = UI_LIST_PAGE){
                        composable(UI_LIST_PAGE) {
                            UiList(controller)
                        }
                        composable(TEXT_FIELD_PAGE){
                            TextFieldPage()
                        }
                    }
                    controller.navigate(TEXT_FIELD_PAGE)
                }
            }
        }
    }
}