package com.johnseremba.stubhub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.johnseremba.stubhub.databinding.ActivityMainBinding
import com.johnseremba.stubhub.model.Event
import com.johnseremba.stubhub.ui.EventUiContract.UiEvent.LoadEvents
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private val eventAdapter: EventAdapter = EventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        setContentView(binding.root)
        initUi()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.sendEvent(LoadEvents)
    }

    private fun initUi() {
        binding.rvEventsList.adapter = eventAdapter
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    showEventList(it.eventList)
                    binding.progressBar.isVisible = it.isLoading
                }
            }
        }
    }

    private fun showEventList(eventList: List<Event>) {
        eventAdapter.submitList(eventList)
    }
}
