package com.mariannecunha.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.mariannecunha.core.base.MviView
import com.mariannecunha.core.extensions.hide
import com.mariannecunha.core.extensions.show
import com.mariannecunha.presentation.R
import com.mariannecunha.presentation.databinding.ActivityHomeBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), MviView<HomeIntent, HomeViewState> {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val adapter: ProductAdapter by inject()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpImageViews()
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

    override fun render(state: HomeViewState) {
        when {
            state.isLoading -> onLoading()
            state.error != null -> onError()
            state.products.isEmpty() -> onEmptyError()
            else -> onSuccess(state)
        }
    }

    private fun setUpRecyclerView() = with(binding) {
        val layoutManager =
            GridLayoutManager(this@HomeActivity, 2, GridLayoutManager.HORIZONTAL, false)

        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.adapter = adapter
    }

    private fun setUpImageViews() = with(binding) {
        Glide.with(this@HomeActivity)
            .load(getString(R.string.top_ad_url))
            .into(adTopImageView)

        Glide.with(this@HomeActivity)
            .load(getString(R.string.logo_url))
            .into(logoView.logoImageView)

        Glide.with(this@HomeActivity)
            .load(getString(R.string.bottom_ad_url))
            .into(adBottomImageView)
    }

    private fun bind() {
        disposables.add(viewModel.states().subscribe(this::render))
        viewModel.processIntents(intents())
    }

    private fun onSuccess(state: HomeViewState) = with(binding) {
        successConstraintLayout.show()
        emptyErrorView.emptyErrorLayout.hide()
        errorView.errorLayout.hide()
        homeRecyclerView.show()
        shimmerViewContainer.hide()
        shimmerViewContainer.stopShimmer()
        adapter.updateProducts(state.products)
    }

    private fun onLoading() = with(binding) {
        successConstraintLayout.show()
        emptyErrorView.emptyErrorLayout.hide()
        shimmerViewContainer.show()
    }

    private fun onError() = with(binding) {
        successConstraintLayout.hide()
        emptyErrorView.emptyErrorLayout.hide()
        errorView.errorLayout.show()
    }

    private fun onEmptyError() = with(binding) {
        successConstraintLayout.show()
        errorView.errorLayout.hide()
        shimmerViewContainer.hide()
        shimmerViewContainer.stopShimmer()
        emptyErrorView.emptyErrorLayout.show()
    }
}
