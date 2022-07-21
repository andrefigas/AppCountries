package andrefigas.com.github.appcountries.presenter.countries

import andrefigas.com.github.appcountries.R
import andrefigas.com.github.appcountries.domain.model.Country
import andrefigas.com.github.appcountries.presenter.BaseObserver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_countries.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CountriesFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CountriesFragment()
    }

    private val viewModel: CountriesViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(viewLifecycleOwner, object : BaseObserver<List<Country>> {
            override fun onStart() {
                countries_pb.visibility = View.VISIBLE
            }

            override fun onFinish() {
                countries_pb.visibility = View.GONE
            }

            override fun onError() {
                Toast.makeText(context, R.string.areas_error, Toast.LENGTH_SHORT).show()
            }

            override fun onChanged(countries: List<Country>) {
                countries_rv.layoutManager = LinearLayoutManager(context)
                countries_rv.adapter = CountriesAdapter(countries)
            }

        })

        viewModel.getCountries()
    }
}