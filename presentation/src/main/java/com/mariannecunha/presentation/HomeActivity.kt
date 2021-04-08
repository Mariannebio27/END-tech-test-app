package com.mariannecunha.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.mariannecunha.core.base.MviView
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

        when {
            state.isLoading -> {
                successConstraintLayout.show()
                emptyErrorView.emptyErrorLayout.hide()
                showLoadingState()
            }
            state.error != null -> {
                successConstraintLayout.hide()
                emptyErrorView.emptyErrorLayout.hide()
                errorView.errorLayout.show()
            }
            state.products.products.isEmpty() -> {
                successConstraintLayout.show()
                errorView.errorLayout.hide()
                shimmerViewContainer.hide()
                shimmerViewContainer.stopShimmer()
                emptyErrorView.emptyErrorLayout.show()
            }
            else -> {
                successConstraintLayout.show()
                emptyErrorView.emptyErrorLayout.hide()
                errorView.errorLayout.hide()
                homeRecyclerView.show()
                shimmerViewContainer.hide()
                shimmerViewContainer.stopShimmer()
                adapter.updateProducts(state.products.products)
            }
        }
    }

    private fun setUpRecyclerView() = with(binding) {
        val layoutManager =
            GridLayoutManager(this@HomeActivity, 2, GridLayoutManager.HORIZONTAL, false)

        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.adapter = adapter
    }

    private fun setUpImageView() = with(binding) {
        Glide.with(this@HomeActivity)
            .load("https://media.endclothing.com/end-features/f_auto,q_auto,w_780/prodfeatures/241bc4c2-e279-4362-8285-23753d8c29e1_thumb.jpg?auto=compress,format")
            .into(adTopImageView)

        Glide.with(this@HomeActivity)
            .load("https://www.logolynx.com/images/logolynx/s_51/51195aa688e0fee277f2e35f4fb780fe.jpeg")
            .into(logoImageView)

        Glide.with(this@HomeActivity)
            .load("https://media.endclothing.com/end-features/prodfeatures/84f9ea34-a9a6-4731-9e36-464d828bd1ef_H04859_launches_hero_landscape_5.jpg?auto=compress,format")
            .into(adBottomImageView)
    }

    private fun showLoadingState() = with(binding.shimmerViewContainer) {
        visibility = View.VISIBLE
        startShimmer()
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

    fun View?.show() {
        this?.visibility = View.VISIBLE
    }

    fun View?.hide() {
        this?.visibility = View.GONE
    }
}
