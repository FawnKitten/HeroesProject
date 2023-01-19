package com.example.heroes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroes.databinding.ActivityHeroesListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeroesListActivity : AppCompatActivity() {
    companion object {
        const val TAG = "HeroesListActivity"
        const val EXTRA_HERO = "EXTRA_HERO"
    }

    private lateinit var binding: ActivityHeroesListBinding

    private var heroes = mutableListOf<Hero>()
    private lateinit var adapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        heroes = loadHeroes()
        heroes.sortDescending()

        adapter = HeroAdapter(heroes) { hero -> adapterOnClick(hero)}
        binding.recyclerViewHeroesList.adapter = adapter
        binding.recyclerViewHeroesList.layoutManager = LinearLayoutManager(this)
    }

    private fun loadHeroes(): MutableList<Hero> {
        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<MutableList<Hero>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(hero: Hero) {
        Log.d(TAG, "adapterOnClick: hero.name=${hero.name}")
        val intent = Intent(this, HeroesDetailActivity()::class.java)
        intent.putExtra(EXTRA_HERO, hero)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.list_order, menu)
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    // the entire data set changed so there is no "more efficient"
    // way to know what changed
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.listOrder_orderByRanking -> {
                heroes.sortBy { it.ranking }
                heroes.reverse()
                adapter.notifyDataSetChanged()
                true
            }
            R.id.listOrder_sortByName -> {
                heroes.sortBy{ it.name.removePrefix("The ") }
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}