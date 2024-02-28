package com.example.weather.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weather.common.Constants
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.databinding.SnackbarLayoutBinding
import com.example.weather.domain.model.ForeCastData
import com.example.weather.domain.states.HomeScreenViewStates
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.states.onEach {
            when (it) {
                is HomeScreenViewStates.WeatherResponse -> {
                    binding.apply {
                        tvCity.text = it.list.name
                        tvTemperature.text = it.list.temp.toInt().toString()
                    }
                    setProgressBarVisibility(false)
                }

                is HomeScreenViewStates.WeatherForeCastResponse -> {
                    openBottomSheet(it.list)
                }

                is HomeScreenViewStates.ApiError -> {
                    setProgressBarVisibility(false)
                    showSnackBar(it.message)
                }

                is HomeScreenViewStates.PageLoading -> {
                    setProgressBarVisibility(true)
                }

                else -> {}
            }
        }
            .flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)
    }

    private fun showSnackBar(message: String? = Constants.DEFAULT_ERROR_MESSAGE) {
        val snackBar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)
        val snackBarBinding = SnackbarLayoutBinding.inflate(layoutInflater)
        val customSnackView: View = snackBarBinding.root
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.setPadding(0, 0, 0, 0)
        snackBarBinding.apply {
            tvError.text = message
            tvRetry.text = Constants.RETRY_CTA
            tvRetry.setOnClickListener {
                snackBar.dismiss()
                viewModel.getCurrentWeatherResponse()
            }
        }
        snackBarLayout.addView(customSnackView, 0)
        snackBar.show()
    }

    private fun openBottomSheet(data: ForeCastData) {
        val bottomSheet = WeatherForecastBottomSheetFragment.newInstance(data)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(binding.bottomSheetLayout.id, bottomSheet)?.commit()
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

}