package com.jessica.servicosconecta


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun TelaLogin(onLoginSucesso: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ServiçoConecta",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Digite suas credenciais para acessar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; erro = "" },
            label = { Text("E-mail") },
            placeholder = { Text("seu@email.com") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it; erro = "" },
            label = { Text("Senha") },
            placeholder = { Text("••••••••") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (erro.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = erro, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.isNotBlank() && senha.isNotBlank()) {
                    onLoginSucesso()
                } else {
                    erro = "Preencha o e-mail e a senha!"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Login")

        }
// Espaço entre os botões
        Spacer(modifier = Modifier.height(12.dp))

// Novo Botão: Cadastrar-se (Abaixo do Entrar)
        Button(
            onClick = {
                // Coloque aqui a ação de ir para a tela de cadastro
//                onNavegarParaCadastro()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Cadastrar-se")
        }


        }

    }

@Preview(showBackground = true)
@Composable
fun TelaLoginPreview() = TelaLogin(
    onLoginSucesso = {}
)