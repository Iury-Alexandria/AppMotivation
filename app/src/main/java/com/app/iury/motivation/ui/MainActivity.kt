package com.app.iury.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.app.iury.motivation.infra.MotivationConstants
import com.app.iury.motivation.R
import com.app.iury.motivation.data.Mock
import com.app.iury.motivation.infra.SecurityPreferences
import com.app.iury.motivation.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId: Int = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // esconder a barra de navegação
        supportActionBar?.hide()

        //mostra o nome do user passado na UserActivities
        handleUserName()
        //deixa o incone All marcado quando abre o app
        handleFilter(R.id.image_all)
        //chamando a frase quando abre o app
        handleNextFrase()
        //eventos de cliques
        setListeners()

    }

    //instanciando a classe Mock


    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            //alterando o texto de acordo com o parâmetro passado
            handleNextFrase()

        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }
    private fun setListeners(){
        // Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    private fun handleUserName() {
        //busca o nome do user
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name"
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.lilas_black))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.lilas_black))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.lilas_black))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleNextFrase() {
        val phraseAleatory = Mock()
        binding.textTextCentral.text = phraseAleatory.getPhrase(categoryId)
    }
}