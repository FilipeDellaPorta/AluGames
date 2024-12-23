package br.com.alura.alugames.main

import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite o código do jogo: ")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()

    var meuJogo: Jogo? = null

    val resultado = runCatching {

        val informacaoJogo = buscaApi.buscaJogo(busca)

        meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb)

    }

    resultado.onFailure {
        println("Jogo não encontrado. Tente outro id.")
    }

    resultado.onSuccess {
        println("Deseja inserir descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            println("Insira a descrição personalizada para o jogo:")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        } else {
            meuJogo?.descricao = meuJogo?.titulo
        }
        println(meuJogo)
    }

}