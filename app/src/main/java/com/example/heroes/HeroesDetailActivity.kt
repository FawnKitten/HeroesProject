package com.example.heroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.heroes.databinding.ActivityHeroesDetailBinding

class HeroesDetailActivity : AppCompatActivity() {
    companion object {
        const val TAG = "HeroesDetailActivity"
    }

    private lateinit var binding: ActivityHeroesDetailBinding
    private lateinit var hero: Hero // the hero this class is detailing

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeHero()

        wireWidgets()
    }

    private fun initializeHero() {
        hero = intent?.getParcelableExtra(HeroesListActivity.EXTRA_HERO)!!
    }

    private fun wireWidgets() {
        binding.textViewDetailTitle.text = hero.name
        binding.textViewDetailDescriptionText.text = hero.description
        binding.textViewDetailSuperpowerText.text = hero.superpower
        binding.textViewDetailRankingText.text = hero.ranking.toString()

        val id = resources.getIdentifier("com.example.heroes:drawable/${hero.image}", "drawable", packageName)
        binding.imageViewDetailHeroImage.setImageResource(id)

    }
}