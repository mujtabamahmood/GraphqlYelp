package com.a.yelpgraphql.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.a.yelpgraphql.data.common.onError
import com.a.yelpgraphql.data.common.onLoading
import com.a.yelpgraphql.data.common.onSuccess
import com.a.yelpgraphql.databinding.ActivitySearchBusinessesBinding
import com.a.yelpgraphql.domain.models.BusinessModel
import com.a.yelpgraphql.ui.details.BusinessDetailsActivity
import com.a.yelpgraphql.utils.*
import com.apollographql.apollo.api.Error
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBusinessesActivity : AppCompatActivity() {
    private val viewModel: BusinessesViewModel by viewModels()
    private lateinit var binding: ActivitySearchBusinessesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBusinessesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        Log.d("TAG", "search activity")
        val context = applicationContext
        viewModel.getListBusinesses("restaurant", "new york", context)
//        setEditTextListener()

    }

//    @FlowPreview
//    @ExperimentalCoroutinesApi
//    @SuppressLint("ClickableViewAccssibility")
//    private fun setEditTextListener() {
//        lifecycleScope.launch {
//            binding.etSearch.getTextChangeStateFlow()
//                .debounce(300)
//                .filter { query ->
//                    if (query.isNotEmpty())
//                        return@filter true
//
//                    return@filter false
//
//                }.distinctUntilChanged()
//                .flatMapLatest { query ->
//                    viewModel.getListBusinesses(query, "New York")
//                }
//                .flowOn(Dispatchers.IO)
//                .collect {result ->}
//        }
//    }

    private fun initObserver() {
        Log.d("TAG", "search activity init observer")

        viewModel.resultBusinesses.observe(this) {
            it.onSuccess { businessesList ->
                binding.progressCircular.hide()
                Log.d(
                    "TAG",
                    "search activity success to setBusinessList" + businessesList.business.size
                )

                setBusinessesList(businessesList.business)
            }.onError { error ->
                Log.d("TAG", "search activity error to setBusinessList" + error.message)

                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> toast(errorMessage.message) }
                    }
                }
            }.onLoading {
                Log.d("TAG", "search activity loading to setBusinessList")

                binding.progressCircular.show()
            }
        }
    }

    private fun setBusinessesList(businessList: List<BusinessModel>) {
        Log.d("TAG", "search activity inside setBusinessList")

        with(binding.rvBusinesses) {
            adapter = BusinessesAdapter(businessList) { goToDetailsActivity(it) }
        }
    }

    private fun goToDetailsActivity(it: BusinessModel) {
        Intent(this, BusinessDetailsActivity::class.java).apply {
            putExtra(BUSINESS_EXTRA, it)
            startActivity(this)
        }

    }


}