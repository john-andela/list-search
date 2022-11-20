package com.johnseremba.stubhub.data

import com.johnseremba.stubhub.model.Event

interface EventRepo {
    fun fetchEvents(): List<Event>
}
class EventDataStore: EventRepo {

    override fun fetchEvents(): List<Event> {
        TODO("Not yet implemented")
    }

    private fun readFile() {
        val jsonFileName = ""

    }

}
