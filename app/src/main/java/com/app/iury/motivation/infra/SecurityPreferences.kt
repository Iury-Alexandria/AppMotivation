package com.app.iury.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    // intanciando a classe SharedPreferences
    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
        //metodo para salvar a string recebida
    }

    //metodo para pegar a chave
    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
        // operador "Elvis" caso a primeira opção for nulo, vai retornar a segunda opção
    }


}