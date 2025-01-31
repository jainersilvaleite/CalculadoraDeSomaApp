package com.example.calculadoradesoma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoradesoma.ui.theme.CalculadoraDeSomaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraDeSomaTheme {
                CalculatorScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen() {
    var number1 by remember { mutableIntStateOf(0) }
    var number2 by remember { mutableIntStateOf(0) }
    var result by remember { mutableIntStateOf(0) }
    var history by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Calculadora de Soma",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0784F5)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.bigbrain),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(80.dp)
                )
                Box(
                    modifier = Modifier
                        .border(
                            color = Color(0xFF0784F5),
                            shape = RectangleShape,
                            width = 2.dp
                        )
                        .padding(horizontal = 10.dp)
                        .height(80.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = """
                        BigBrain informa:
                        - Insira dois números inteiros
                        - Some-os clicando no botão abaixo
                    """.trimIndent(),
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                NumberInsertColumn(
                    numberPosition = 1,
                    onNumberInsert = { number -> number1 = number }
                )
                NumberInsertColumn(
                    numberPosition = 2,
                    onNumberInsert = { number -> number2 = number }
                )
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
                    .padding(10.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0784F5)),
                onClick = {
                    result = number1 + number2
                    history += "$number1 + $number2 = $result\n"
                }
            ) {
                Text(
                    text = "Somar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
            Text(
                text = "Resultado: $result",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Histórico de Somas",
                modifier = Modifier.padding(top = 40.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Box(
                modifier = Modifier
                    .border(
                        color = Color(0xFF0784F5),
                        shape = RectangleShape,
                        width = 2.dp
                    )
                    .width(200.dp)
                    .height(350.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = history,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun NumberInsertColumn(
    numberPosition: Int,
    onNumberInsert: (number: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 15.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "${numberPosition}º",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(
                text = " número:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
        TextField(
            value = "0",
            modifier = Modifier
                .width(100.dp),
            onValueChange = { number ->
                onNumberInsert(number.toInt())
            }
        )
    }

}

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculadoraDeSomaTheme {
        CalculatorScreen()
    }
}

/*
CONTEÚDOS MINÍMOS PARA ABORDAR
- Funções @Composable; --> OK
- Modifier; --> OK
- Componentes:
    - Text() --> OK;
    - Column(); --> OK;
    - Row(); --> OK;
    - Box() --> OK;
    - Scaffold() --> OK;
- Alterando propriedades; --> OK
- Inserindo imagens; --> OK;
- Testando UI com a notação @Preview  --> OK
*/