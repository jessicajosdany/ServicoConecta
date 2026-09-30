package com.jessica.servicosconecta


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaServicos() {
    var busca by remember { mutableStateOf("") }

    val listaPrestadores = remember {
        listOf(
            Prestador(
                id = 1,
                nome = "Carlos Eduardo Silva",
                telefone = "(11) 98765-4321",
                avaliacao = 4.9,
                servicos = listOf("Eletricista", "Instalação de Ar", "Reparos")
            ),
            Prestador(
                id = 2,
                nome = "Mariana Costa",
                telefone = "(21) 99876-1234",
                avaliacao = 5.0,
                servicos = listOf("Design UI/UX", "Criação de Marcas", "Ilustração")
            ),
            Prestador(
                id = 3,
                nome = "Lucas Mendes Tech",
                telefone = "(31) 98456-7890",
                avaliacao = 4.7,
                servicos = listOf("Desenvolvimento Web", "Manutenção de PCs")
            ),
            Prestador(
                id = 4,
                nome = "Juliana Rocha",
                telefone = "(41) 97123-4567",
                avaliacao = 4.8,
                servicos = listOf("Maquiagem", "Penteados", "Consultoria")
            )
        )
    }

    val prestadoresFiltrados = listaPrestadores.filter { prestador ->
        prestador.nome.contains(busca, ignoreCase = true) ||
                prestador.servicos.any { servico -> servico.contains(busca, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Prestadores de Serviços") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = busca,
                onValueChange = { busca = it },
                label = { Text("Buscar por nome ou serviço...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (prestadoresFiltrados.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum prestador encontrado.")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(prestadoresFiltrados) { prestador ->
                        CardPrestador(prestador = prestador)
                    }
                }
            }
        }
    }
}

@Composable
fun CardPrestador(prestador: Prestador) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = prestador.nome,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "⭐ ${prestador.avaliacao}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "📞 ${prestador.telefone}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "SERVIÇOS PRESTADOS",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                prestador.servicos.forEach { servico ->
                    SuggestionChip(
                        onClick = { },
                        label = { Text(servico, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaServicosPreview() = TelaServicos()