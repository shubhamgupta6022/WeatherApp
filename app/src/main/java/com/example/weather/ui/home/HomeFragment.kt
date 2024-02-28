package com.example.weather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.domain.model.ForeCastData
import com.example.weather.domain.states.HomeScreenViewStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.states.onEach {
            when(it) {
                is HomeScreenViewStates.WeatherResponse -> {
                    Log.d(TAG, "HomeScreenViewStates: WeatherResponse, ${it.list}")
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
                }
                is HomeScreenViewStates.PageLoading -> {
                    Log.d(TAG, "HomeScreenViewStates: PageLoading")
                    setProgressBarVisibility(true)
                }
                else -> {}
            }
        }
            .flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)
    }

    private fun openBottomSheet(data: ForeCastData) {
        val bottomSheet = WeatherForecastBottomSheetFragment.newInstance(data)
        activity?.supportFragmentManager?.let {
            bottomSheet.show(it, bottomSheet.tag)
        }
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

}