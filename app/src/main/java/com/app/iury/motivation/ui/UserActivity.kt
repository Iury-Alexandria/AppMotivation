package com.app.iury.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.iury.motivation.infra.MotivationConstants
import com.app.iury.motivation.R
import com.app.iury.motivation.infra.SecurityPreferences
import com.app.iury.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bindind: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityUserBinding.inflate(layoutInflater)
        setContentView(bindind.root)
        //retirando a actionBar
        supportActionBar?.hide()
        //eventos
        bindind.buttonSave.setOnClickListener(this)

        //verificar se o nome já existe, e mandar diretamente para a tela das frases
        verifyUserName()
    }

    //respondendo ao botão
    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != ""){
            //navega entre tela/ inicia a segunda tela
            startActivity(Intent(this, MainActivity :: class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = bindind.editName.text.toString()
        if (name != "") {
            //salva o nome que o usuário digitar
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            //navegando entre tela
            startActivity(Intent(this, MainActivity :: class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }



}