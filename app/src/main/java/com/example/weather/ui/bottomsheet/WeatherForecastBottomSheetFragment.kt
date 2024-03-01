package com.example.weather.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.databinding.WeatherForecastBottomSheetFragmentBinding
import com.example.weather.domain.model.ForeCastData
import com.example.weather.ui.adapter.WeatherForecastAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WeatherForecastBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: WeatherForecastBottomSheetFragmentBinding
    private lateinit var adapter: WeatherForecastAdapter

    private var data: ForeCastData? = null

    companion object {
        fun newInstance(data: ForeCastData): WeatherForecastBottomSheetFragment {
            return WeatherForecastBottomSheetFragment().apply {
                this.data = data
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherForecastBottomSheetFragmentBinding.inflate(
            LayoutInflater.from(context),
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            adapter = WeatherForecastAdapter(data?.list)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

}