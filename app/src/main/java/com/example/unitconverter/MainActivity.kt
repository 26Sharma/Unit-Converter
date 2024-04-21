package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.text.toDoubleOrNull as toDoubleOrNull1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}
@Composable
fun unitConverter(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    val conversinFactor=  remember{ mutableStateOf(1.00) }
    val oconversinFactor=  remember{ mutableStateOf(1.00) }

    fun convertsUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull1() ?:0.0
        val result= (inputValueDouble * conversinFactor.value * 100.0 / oconversinFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }



    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Unit Converter",    style = MaterialTheme.typography.headlineLarge)
        //alt+enter->import
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange ={
            inputValue = it
           convertsUnits()
        },
            label = {Text(text = "Enter value:")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //input box
            Box {
                //input button
               Button(onClick = { iExpanded=true }) {
                        Text(text =inputUnit)
                   Icon(Icons.Default.ArrowDropDown,
                       contentDescription = "Arrow Down")
               }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false}) {
                  DropdownMenuItem(text = { Text(text = "Centimeters") },
                      onClick = {
                          iExpanded=false
                          inputUnit="Centimeters"
                          conversinFactor.value=0.01
                          convertsUnits()
                      }
                  )
                    DropdownMenuItem(text = { Text(text = "Meters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Meters"
                            conversinFactor.value=1.0
                            convertsUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            conversinFactor.value=0.3048
                            convertsUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Milimeters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Milimeters"
                            conversinFactor.value=0.001
                            convertsUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                Button(onClick = { oExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimeters"
                            oconversinFactor.value=0.01
                            convertsUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Meters") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Meters"
                            oconversinFactor.value=1.00
                            convertsUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            oconversinFactor.value=0.3048
                            convertsUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Milimeters") },
                        onClick = { oExpanded=false
                            outputUnit="Milimeters"
                            oconversinFactor.value=0.001
                            convertsUnits() }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // result
        Text(text = "Result: $outputValue $outputUnit ",
            style = MaterialTheme.typography.headlineMedium
        )

    }
}


@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}