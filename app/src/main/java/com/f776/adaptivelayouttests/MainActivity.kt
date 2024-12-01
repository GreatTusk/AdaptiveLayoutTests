package com.f776.adaptivelayouttests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.f776.adaptivelayouttests.ui.theme.AdaptiveLayoutTestsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveLayoutTestsTheme {
                NavigationSuiteLayout()
            }
        }
    }
}
