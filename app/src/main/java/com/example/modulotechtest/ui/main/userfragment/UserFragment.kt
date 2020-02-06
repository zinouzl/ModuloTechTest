package com.example.modulotechtest.ui.main.userfragment


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.modulotechtest.R
import com.example.modulotechtest.model.User
import dagger.android.support.DaggerFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class UserFragment : DaggerFragment() {

    //private val TAG = "UserFragment"
    private lateinit var userViewModel: UserViewModel
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        subscribeObservers()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.setFragmentDrawable(user_image)
        enable_edit_user_info.setOnClickListener {
            enableEdit()
        }
        save_user_button.setOnClickListener {
            disableEdit()
        }


    }

    private fun disableEdit() {
        edit_text_first_name_user.isEnabled = false
        edit_text_last_name_user.isEnabled = false
        edit_text_street_code_user.isEnabled = false
        edit_text_street_user.isEnabled = false
        edit_text_postal_code_user.isEnabled = false
        edit_text_city_user.isEnabled = false
        edit_text_country_user.isEnabled = false
        save_user_button.hide()
        edit_text_birthday_user.isEnabled = false
        edit_text_birthday_user.setOnClickListener { }
        val parser = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
        val date = parser.parse(edit_text_birthday_user.text.toString())!!

        val user = User(
            edit_text_first_name_user.text.toString(),
            edit_text_last_name_user.text.toString(),
            edit_text_city_user.text.toString(),
            edit_text_postal_code_user.text.toString().toInt(),
            edit_text_street_user.text.toString(),
            edit_text_street_code_user.text.toString(),
            edit_text_country_user.text.toString(),
            date.time
        )
        user.id = 1
        updateUser(user)


    }

    @SuppressLint("CheckResult")
    fun updateUser(user: User) {
        Completable.fromAction {
            userViewModel.updateUser(user)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {

                Toast.makeText(context, context?.getText(R.string.user_could_not_be_updated), Toast.LENGTH_LONG).show()
            }
            .subscribe {
                Toast.makeText(context, context?.getText(R.string.user_has_been_updated), Toast.LENGTH_SHORT).show()
            }
    }

    // I can suppress Lint since my strings do not contain fraction.
    @SuppressLint("SetTextI18n")
    fun enableEdit() {
        edit_text_first_name_user.isEnabled = true
        edit_text_last_name_user.isEnabled = true
        edit_text_street_code_user.isEnabled = true
        edit_text_street_user.isEnabled = true
        edit_text_postal_code_user.isEnabled = true
        edit_text_city_user.isEnabled = true
        edit_text_country_user.isEnabled = true
        save_user_button.show()
        edit_text_birthday_user.isEnabled = true
        edit_text_birthday_user.setOnClickListener {
            val date = edit_text_birthday_user.text.toString().split("/")

            // DataPicker start from 0 for month
            val datePickerDialog = DatePickerDialog(
                context!!, R.style.Theme_AppCompat_Light_Dialog_Alert,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    edit_text_birthday_user.text = "${dayOfMonth}/${month + 1}/${year}".toEditable()
                },
                date[2].toInt(),
                date[1].toInt() - 1,
                date[0].toInt()
            )

            datePickerDialog.show()

        }
    }


    private fun subscribeObservers() {
        userViewModel.getUser().removeObservers(viewLifecycleOwner)
        userViewModel.getUser().observe(viewLifecycleOwner) { user ->
            hello_user_name.text = user.firstName
            edit_text_first_name_user.text = (user.firstName).toEditable()
            edit_text_last_name_user.text = (user.lastName).toEditable()
            edit_text_birthday_user.text =
                (SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.FRANCE
                ).format(Date(user.birthDate))).toEditable()
            edit_text_street_code_user.text = (user.streetCode).toEditable()
            edit_text_street_user.text = (user.street).toEditable()
            edit_text_city_user.text = (user.city).toEditable()
            edit_text_postal_code_user.text = (user.postalCode.toString()).toEditable()
            edit_text_country_user.text = (user.country).toEditable()


        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}