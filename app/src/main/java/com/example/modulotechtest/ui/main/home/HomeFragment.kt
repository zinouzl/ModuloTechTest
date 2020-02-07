package com.example.modulotechtest.ui.main.home


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modulotechtest.R
import com.example.modulotechtest.model.device.Devices
import com.example.modulotechtest.model.device.Heater
import com.example.modulotechtest.model.device.Light
import com.example.modulotechtest.model.device.RollerShutter
import com.example.modulotechtest.recyclerview.DevicesAdapter
import com.example.modulotechtest.ui.main.editdialogfragments.EditHeaterDialogFragment
import com.example.modulotechtest.ui.main.editdialogfragments.EditLightDialogFragment
import com.example.modulotechtest.ui.main.editdialogfragments.EditRollerShutterDialogFragment
import com.example.modulotechtest.utils.DeviceUpdatedListener
import dagger.android.support.DaggerFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment(), DeviceUpdatedListener {

    private lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    @Inject
    lateinit var deviceAdapter: DevicesAdapter
    private var firstTimeAnimation = true


    private val itemTouchHelperCallback =
        object : ItemTouchHelper.SimpleCallback(0, RIGHT or LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            // delete item if user push an item left. An alert dialog will appear to confirm the deletion
            @SuppressLint("CheckResult")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val alertDialog = activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setMessage(R.string.do_you_really_want_to_delete_this_device)
                        setTitle(R.string.delete_device)
                        setPositiveButton(R.string.ok) { dialog, _ ->

                            Completable.fromAction {
                                homeViewModel.deleteDevice(deviceAdapter.devices[viewHolder.adapterPosition])
                            }.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    Toast.makeText(
                                        context,
                                        context?.getText(R.string.device_deleted),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            dialog.dismiss()

                        }
                        setNegativeButton(R.string.cancel) { dialog, _ ->
                            deviceAdapter.notifyItemChanged(viewHolder.adapterPosition)
                            dialog.dismiss()
                        }

                    }
                    builder.create()

                }
                alertDialog?.show()


            }

        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstTimeAnimation = true
        lights_device_check.setOnClickListener {
            lightClicked()
        }
        roller_shutter_check.setOnClickListener {
            rollerShutterClicked()
        }
        heaters_device_check.setOnClickListener {
            heaterClicked()
        }

        recycler_view_devices.apply {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)

            deviceAdapter.listener = object : DevicesAdapter.OnItemClickListener {
                override fun onItemClick(devices: Devices) {
                    when (devices) {
                        is Light -> {
                            val editLightDialogFragment = EditLightDialogFragment.newInstance(
                                devices.id,
                                devices.deviceName,
                                devices.mode,
                                devices.intensity
                            )
                            editLightDialogFragment.show(childFragmentManager, "Setting")
                        }
                        is Heater -> {
                            val editHeaterDialogFragment = EditHeaterDialogFragment.newInstance(
                                devices.id,
                                devices.deviceName,
                                devices.mode,
                                devices.temperature
                            )
                            editHeaterDialogFragment.show(childFragmentManager, "Setting")
                        }
                        is RollerShutter -> {
                            val editRollerShutterDialogFragment =
                                EditRollerShutterDialogFragment.newInstance(
                                    devices.id,
                                    devices.deviceName,
                                    devices.position
                                )
                            editRollerShutterDialogFragment.show(childFragmentManager, "Setting")
                        }
                    }
                }

            }
            adapter = deviceAdapter
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)

        }
        subscribeObservers()
    }


    // observe the changes on device LiveData
    private fun subscribeObservers() {
        homeViewModel.getAllDevices().removeObservers(viewLifecycleOwner)
        homeViewModel.getAllDevices().observe(viewLifecycleOwner) {
            it?.let {
                deviceAdapter.devices = it
                if (firstTimeAnimation and it.isNotEmpty()) {
                    recycler_view_devices.scheduleLayoutAnimation()
                    firstTimeAnimation = !firstTimeAnimation
                }
                //recycler_view_devices.scheduleLayoutAnimation()
            }
        }
    }


    // this three function are for filtering data sources
    private fun lightClicked() {
        if (lights_device_check.isChecked) {
            homeViewModel.checkedRadioLights()
        } else {
            homeViewModel.uncheckedRadioLights()
        }
    }

    private fun heaterClicked() {
        if (heaters_device_check.isChecked) {
            homeViewModel.checkedRadioHeaters()

        } else {
            homeViewModel.uncheckedRadioHeaters()
        }


    }

    private fun rollerShutterClicked() {
        if (roller_shutter_check.isChecked) {
            homeViewModel.checkedRadioRollerShutter()
        } else {
            homeViewModel.uncheckedRadioRollerShutter()
        }

    }


    //
    @SuppressLint("CheckResult")
    override fun heaterUpdated(id: Int, name: String, mode: String, temperature: Double) {
        val heater = Heater(id, name, mode, temperature)
        Completable.fromAction {
            homeViewModel.updateDevice(heater)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Toast.makeText(
                    context,
                    context?.getText(R.string.heater_could_not_be_updated),
                    Toast.LENGTH_LONG
                ).show()
            }
            .subscribe {
                Toast.makeText(
                    context,
                    context?.getText(R.string.heater_has_been_updated),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    @SuppressLint("CheckResult")
    override fun lightUpdated(id: Int, name: String, mode: String, intensity: Int) {
        val light = Light(id, name, mode, intensity)
        Completable.fromAction {
            homeViewModel.updateDevice(light)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Toast.makeText(
                    context,
                    context?.getText(R.string.light_could_not_be_updated),
                    Toast.LENGTH_LONG
                ).show()
            }
            .subscribe {
                Toast.makeText(
                    context,
                    context?.getText(R.string.light_has_been_updated),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    @SuppressLint("CheckResult")
    override fun rollerShutterUpdated(id: Int, name: String, position: Int) {
        val rollerShutter = RollerShutter(id, name, position)
        Completable.fromAction {
            homeViewModel.updateDevice(rollerShutter)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Toast.makeText(
                    context,
                    context?.getText(R.string.roller_shutter_could_not_be_updated),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            .subscribe {
                Toast.makeText(
                    context,
                    context?.getText(R.string.roller_Shutter_has_been_updated),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

}