package com.mariannecunha.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.mariannecunha.domain.mvibase.MviView
import com.mariannecunha.presentation.databinding.ActivityHomeBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), MviView<HomeIntent, HomeViewState> {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val adapter: MenswearAdapter by inject()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpImageView()
    }

    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun intents(): Observable<HomeIntent> {
        return Observable.just(HomeIntent.LoadAllMenswearIntent)
    }

    override fun render(state: HomeViewState) = with(binding) {
        homeProgressBar.visible = state.isLoading

        if (state.products.products.isEmpty()) {
            homeRecyclerView.visible = false
            homeEmptyState.visible = true
        } else {
            homeRecyclerView.visible = true
            homeEmptyState.visible = false
            adapter.updateProducts(state.products.products)
        }

        if (state.error != null) {
            Toast.makeText(this@HomeActivity, "We had an error.", Toast.LENGTH_SHORT).show()
            Log.e("TAG", "Error loading: ${state.error.localizedMessage}")
        }
    }

    private fun setUpRecyclerView() = with(binding) {
        val layoutManager = GridLayoutManager(this@HomeActivity, 2, GridLayoutManager.HORIZONTAL, false)

        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.adapter = adapter
    }

    private fun setUpImageView() = with(binding) {
        Glide.with(this@HomeActivity)
            .load("https://media.endclothing.com/media/wysiwyg/square_2.jpg")
            .into(adImageView)
    }

    private fun bind() {
        disposables.add(viewModel.states().subscribe(this::render))
        viewModel.processIntents(intents())
    }

    // TODO create extensions
    var View.visible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.INVISIBLE
        }
}
