package ru.bin.presentation.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.bin.app.MyApplication
import ru.bin.presentation.R
import ru.bin.presentation.databinding.FragmentBankInformationBinding
import ru.bin.presentation.fragment.basefragment.BaseFragment
import ru.bin.presentation.fragment.viewmodel.BankInformationViewModel
import ru.bin.presentation.fragment.viewmodelfactory.BinViewModelFactory
import ru.bin.presentation.model.BinInformationView
import javax.inject.Inject

class BankInformationFragment : BaseFragment<BankInformationViewModel>() {

    private var _binding: FragmentBankInformationBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<BankInformationFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: BinViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(requireActivity(), viewModelFactory)[BankInformationViewModel::class.java]
    }

    private val component by lazy(LazyThreadSafetyMode.NONE) {
        (requireActivity().applicationContext as MyApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        viewModel.getBankInformation(args.binNumber)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBankInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
    }

    private fun observeViewModels() {
        viewModel.bankInformationLiveData.observe(viewLifecycleOwner) { view ->
            bindingBank(requireActivity(), view)
            bindingCountry(requireActivity(), view)
            bindingCard(requireActivity(), view)
            chooseIntentAction(view)
        }
    }

    private fun bindingBank(context: Context, view: BinInformationView) {
        with(binding) {
            bankName.text =
                checkTextForNull(context.getString(R.string.bank_name), view.bank?.bankName)
            bankCity.text =
                checkTextForNull(context.getString(R.string.bank_city), view.bank?.bankCity)
            bankUrl.text =
                checkTextForNull(context.getString(R.string.bank_url), view.bank?.bankUrl)
            bankPhone.text =
                checkTextForNull(context.getString(R.string.bank_phone), view.bank?.bankPhone)
        }
    }

    private fun bindingCountry(context: Context, view: BinInformationView) {
        with(binding) {
            countryName.text =
                checkTextForNull(
                    context.getString(R.string.country_name),
                    view.country?.countyName
                )
            countryCurrency.text = String.format(
                context.getString(R.string.country_currency),
                view.country?.countyCurrency
            )
            countryCode.text =
                checkTextForNull(
                    context.getString(R.string.country_code),
                    view.country?.countryCode
                )
            countryLatitudeLongitude.text = String.format(
                context.getString(R.string.country_latitude_longitude),
                view.country?.latitude,
                view.country?.longitude
            )
        }
    }

    private fun bindingCard(context: Context, view: BinInformationView) {
        with(binding) {
            binNumber.text = args.binNumber
            cardScheme.text = checkTextForNull(
                context.getString(R.string.card_scheme),
                view.cardScheme?.uppercase()
            )
            cardType.text =
                checkTextForNull(
                    context.getString(R.string.card_type),
                    view.cardType?.uppercase()
                )
            cardBrand.text =
                checkTextForNull(context.getString(R.string.card_brand), view.cardBrand)
            cardPrepaid.text =
                String.format(context.getString(R.string.card_prepaid), view.cardPrepaid)
            cardNumberLength.text = String.format(
                context.getString(R.string.card_number_length),
                view.cardInfo?.cardLength
            )
            cardLuhn.text =
                String.format(context.getString(R.string.card_luhn), view.cardInfo?.luhn)
        }
    }

    private fun chooseIntentAction(view: BinInformationView) {
        binding.bankUrl.setOnClickListener {
            val url =
                String.format(requireActivity().getString(R.string.url_format), view.bank?.bankUrl)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.bankPhone.setOnClickListener {
            val phone = String.format(
                requireActivity().getString(R.string.phone_format),
                view.bank?.bankPhone
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(phone))
            startActivity(intent)
        }
        binding.countryLatitudeLongitude.setOnClickListener {
            val geo = String.format(
                requireActivity().getString(R.string.geo_format),
                view.country?.latitude,
                view.country?.longitude
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geo))
            startActivity(intent)
        }
    }

    private fun checkTextForNull(res: String, text: String?): String = if (text != null) {
        String.format(res, text)
    } else {
        String.format(res, activity?.getString(R.string.no_info))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}