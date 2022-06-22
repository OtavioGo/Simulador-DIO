package com.example.simulador_dio.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FieldClassification
import com.bumptech.glide.Glide
import com.example.simulador_dio.databinding.ActivityDetailBinding
import com.example.simulador_dio.domain.Match

class DetailActivity : AppCompatActivity() {

    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            //Carregando a imagem do estadio na toolbar
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            //Carregando o nome do estádio na toolbar
            supportActionBar?.title = it.place.name

            //Carregando a descrição da partida
            binding.tvDescription.text = it.description

            //Carregando a imagem dos times
            Glide.with(this).load(it.homeTeam.image).into(binding.ivHomeTeam)
            Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)

            //Carregando o nome do time
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.tvAwayTeamName.text = it.awayTeam.name

            //Carregando as estrelas do time
            binding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()
            binding.rbAwayTeamStars.rating = it.awayTeam.stars.toFloat()

            //If para verificar se os times tem um placar simulado
            if (it.homeTeam.score != null){
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }
            if (it.awayTeam.score != null){
                binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }
        }
    }
}