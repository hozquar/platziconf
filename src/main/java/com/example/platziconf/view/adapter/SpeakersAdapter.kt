import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import com.example.platziconf.model.Speaker
import com.example.platziconf.view.adapter.SpeakerListener

class SpeakersAdapter(val speakerListener: SpeakerListener):RecyclerView.Adapter<SpeakersAdapter.ViewHolder>(){

    var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speakers , parent,false))

    override fun getItemCount()=listSpeakers.size

    override fun onBindViewHolder(holder: SpeakersAdapter.ViewHolder, position: Int) {

        val speakers= listSpeakers[position] as Speaker
        holder.tvSpeakersName = speakers.name
        holder.tvSpeakersWork = speakers.jobtitle

        Glide.with(holder.itemView.context) //indicamos el contexto de la imagen
            .load(speakers.image) //la carga del url
            .apply(RequestOptions.circleCropTransform()) //transforma la imagen cortandola en un circulo
            .into(holder.ivSpeakerPhoto) //va a colocar la imagen ahi

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speakers,position) }
    }

    fun update(data: List<Conference>){
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
        val tvSpeakersName = itemView.findViewById<TextView>(R.id.tv_itemSpeakersName)
        val tvSpeakersWork = itemView.findViewById<TextView>(R.id.tv_itemSpeakersJob)
        val ivSpeakerPhoto = itemView.findViewById<ImageView>(R.id.iv_itemSpeakerPhoto)
    }
}

