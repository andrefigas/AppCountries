package andrefigas.com.github.appcountries.presenter.countries

import andrefigas.com.github.appcountries.R
import andrefigas.com.github.appcountries.domain.model.Country
import andrefigas.com.github.appcountries.presenter.details.DetailsFragment
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import kotlinx.android.synthetic.main.country_item_adapter.view.*

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    class CountriesAdapter(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.country_item_adapter, parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun onViewAttachedToWindow(holder: CountryViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.loadImage()

    }

    override fun onViewDetachedFromWindow(holder: CountryViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unloadImage()
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var request: ViewTarget<ImageView, Drawable>? = null
        lateinit var country: Country

        fun bind(country: Country) {
            this.country = country

            itemView.country_item_title_tv.text = this.country.name

            itemView.setOnClickListener {
                val bundle = bundleOf(DetailsFragment.ARG_COUNTRY to country)
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_countriesFragment_to_detailsFragment, bundle)
            }

        }

        fun loadImage() {
            request = Glide.with(itemView.context)
                .load("https://www.kestrel.ws/flags/current/lg/${country.code}.png")
                .placeholder(R.drawable.empty_flag)
                .error(R.drawable.empty_flag).circleCrop().into(itemView.country_item_flag_iv)
        }

        fun unloadImage() {
            request?.request?.clear()
        }

    }

}