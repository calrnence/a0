package com.example.a0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a0.ui.theme.A0Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A0Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var decisionMaker = DecisionMaker()
                    DecisionMakerScreen(
                        decisionMaker,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class DECISION {
    YES, NO, UNDECIDED
}

class DecisionMaker(
) {
    var _decision by mutableStateOf<DECISION>(DECISION.UNDECIDED)
    var _clickCounter by mutableIntStateOf(0)

    val decision: DECISION
        get() = _decision

    val clickCounter: Int
        get() = _clickCounter

    fun makeDecision(
        weight: Int
    ) {
        _clickCounter += 1
        val result = (0 until 100).random()
        if (result < weight) {
            _decision = DECISION.YES
        } else {
            _decision = DECISION.NO
        }
    }
}

@Composable
fun DecisionMakerScreen(
    decisionMaker: DecisionMaker,
    modifier: Modifier = Modifier
) {
    val yesWeight = 50
    val maybeWeight = 20
    val noWeight = 10

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            val result = when (decisionMaker.decision) {
                DECISION.YES -> Text("Yes")
                DECISION.NO -> Text("No")
                else -> Text("Should we go?")
            }
        }

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
        ) {
            //
            Button(
                onClick = {
                    decisionMaker.makeDecision(yesWeight)
                }
            ) {
                Text("Yes")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    decisionMaker.makeDecision(maybeWeight)
                }
            ) {
                Text("Maybe")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    decisionMaker.makeDecision(noWeight)
                }
            ) {
                Text("No")
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Times clicked: ${decisionMaker.clickCounter}"
            )
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("1800282 - cvh")
        }
    }
}

