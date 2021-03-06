package com.example.pickrestaurant.people.overview.events

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pickrestaurant.people.R
import com.example.pickrestaurant.people.model.Event
import kotlinx.android.synthetic.main.item_event_card.view.*

class EventsDetailsAdapter(val events: List<Event>, val context: Context) : RecyclerView.Adapter<EventsDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EventsDetailsViewHolder {
        return EventsDetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event_card, parent, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventsDetailsViewHolder, position: Int) {
        holder.bind(events[position], position, context)
    }

}

class EventsDetailsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    private val title_text = view.title_text
    private val subtitle_text = view.subtitle_text
    private val ivThumbnail = view.avatar_image
    private val ivPicture = view.media_image
    private val btnWebSite = view.action_button_1
    private val btnShare = view.action_button_2

    fun bind(event: Event, position: Int, context: Context) {

        title_text?.text = event.name
        subtitle_text?.text = event.description

        if (!event.pictureUrl.isNullOrBlank())
            Glide.with(context)
                    .load(event.pictureUrl)
                    .into(ivPicture)

        if (!event.thumbnailUrl.isNullOrBlank())
            Glide.with(context)
                    .load(event.thumbnailUrl)
                    .into(ivThumbnail)

        btnWebSite.setOnClickListener {
            startActivity(context, Intent( Intent.ACTION_VIEW , Uri.parse( event.webUrl )), null)
        }

        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, event.webUrl )
                type = "text/plain"
            }
            startActivity(context, Intent.createChooser(sendIntent, "Share with"),null)
        }
    }
}