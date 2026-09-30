package com.jessica.servicosconecta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jessica.servicosconecta.ui.theme.ServicosConectaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServicosConectaTheme {
                NavegacaoDoApp()
            }
        }
    }
}

@Composable
fun NavegacaoDoApp() {
    val navController = rememberNavController()

    // O NavHost gerencia a troca de telas no seu app
    NavHost(navController = navController, startDestination = "login") {

        // Tela 1: Login
        composable("login") {
            TelaLogin(
                onLoginSucesso = {
                    // Quando o login dá certo, vai para a tela de serviços e limpa a pilha de voltar
                    navController.navigate("servicos") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Tela 2: Diretório/Cadastro de Serviços
        composable("servicos") {
            TelaServicos()
        }
    }
}