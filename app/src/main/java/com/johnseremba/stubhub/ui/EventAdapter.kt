package com.johnseremba.stubhub.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.johnseremba.stubhub.R
import com.johnseremba.stubhub.databinding.EventListItemBinding
import com.johnseremba.stubhub.model.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EventAdapter : ListAdapter<Event, ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(EventListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = currentList[position]
        return (holder as EventViewHolder).bind(event)
    }

    private inner class EventViewHolder(private val binding: EventListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) = with(binding) {
            tvTitle.text = event.title
            tvCity.text = root.formatEventString(R.string.event_city, event.city)
            tvVenue.text = root.formatEventString(R.string.event_venue, event.venue)
            tvPrice.text = root.formatEventString(R.string.event_price, "$${event.price}")
            tvDate.text = root.formatEventString(R.string.event_date, formatDateString(event.date))
        }

        private fun formatDateString(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("dd.MMM yyyy")
            return date.format(formatter)
        }

        private fun View.formatEventString(resId: Int, value: String): String {
            return String.format(context.getString(resId), value)
        }
    }
}

private val diffUtil = object : ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}
