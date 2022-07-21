package andrefigas.com.github.appcountries.presenter.details

import andrefigas.com.github.appcountries.R
import andrefigas.com.github.appcountries.domain.model.Country
import andrefigas.com.github.appcountries.presenter.BaseObserver
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class DetailsFragment : Fragment() {

    private lateinit var country: Country

    private val viewModel: CountryDetailsViewModel by lazy {
        getViewModel()
    }

    companion object {

        const val ARG_COUNTRY = "country"

        @JvmStatic
        fun newInstance(country: Country) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COUNTRY, country)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            country = it.getParcelable(ARG_COUNTRY) ?: throw IllegalArgumentException()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                view?.let {
                    Navigation.findNavController(it).navigateUp()
                }

            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }


    override fun onDetach() {
        super.onDetach()

        activity.let {
            if (it is AppCompatActivity) {
                it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
                it.supportActionBar?.setHomeButtonEnabled(false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        details_code_tv.text = country.code
        details_title_tv.text = country.name

        activity.let {
            if (it is AppCompatActivity) {
                it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                it.supportActionBar?.setHomeButtonEnabled(true)
            }
        }

        viewModel.observe(viewLifecycleOwner, object : BaseObserver<Country> {
            override fun onChanged(country: Country) {
                details_description_tv.text = country.description
            }

            override fun onStart() {
                details_progress_pb.visibility = View.VISIBLE
            }

            override fun onFinish() {
                details_progress_pb.visibility = View.GONE
            }

            override fun onError() {
                details_description_tv.setText(R.string.summary_error)
            }

        })

        viewModel.getCountryDetails(country)
    }


}