package com.example.challenge3.ui.fragment.places

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge3.BuildConfig
import com.example.challenge3.R
import com.example.challenge3.adapter.PlaceAdapter
import com.example.challenge3.base.BaseFragment
import com.example.challenge3.base.Extensions.isBottomOfListReached
import com.example.challenge3.base.Extensions.ll
import com.example.challenge3.core.domain.Place
import com.example.challenge3.databinding.FragmentPlacesBinding
import com.example.challenge3.service.ForegroundOnlyLocationService
import com.example.challenge3.sharedpref.UserSharedPref
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class PlacesFragment : BaseFragment<FragmentPlacesBinding>() {
    companion object {
        private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
    }

    @Inject
    lateinit var userSharedPref: UserSharedPref

    var lastLoc: Location? = null


    private lateinit var viewModel: PlacesViewModel
    private val placeAdapter = PlaceAdapter(object : PlaceAdapter.CallBack {
        override fun onItemClick(place: Place) {
            navigateToDetailsFragment(place.fsqId)
        }
    })

    private var foregroundOnlyLocationServiceBound = false

    private var foregroundOnlyLocationService: ForegroundOnlyLocationService? = null

    private lateinit var foregroundOnlyBroadcastReceiver: ForegroundOnlyBroadcastReceiver

    private val foregroundOnlyServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as ForegroundOnlyLocationService.LocalBinder
            foregroundOnlyLocationService = binder.service
            startLocationService()
            foregroundOnlyLocationServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            foregroundOnlyLocationService = null
            foregroundOnlyLocationServiceBound = false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            getBaseActivity().providerFactory
        )[PlacesViewModel::class.java]

        foregroundOnlyBroadcastReceiver = ForegroundOnlyBroadcastReceiver()

        lastLoc = userSharedPref.getLastLocation()

    }

    override fun setupViews() {

        binding.rvPlaces.apply {

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (viewModel.pager.isLoadingAllowed() && recyclerView.isBottomOfListReached()) {
                        lastLoc?.let { viewModel.fetchPlaces(viewModel.pager.index, it.ll()) }
                    }
                }
            })
            layoutManager = LinearLayoutManager(context)
            adapter = placeAdapter
        }

        startLocationService()
    }

    private fun startLocationService() {
        if (foregroundPermissionApproved()) {
            foregroundOnlyLocationService?.subscribeToLocationUpdates()
        } else {
            requestForegroundPermissions()
        }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_places

    override fun subscribe() {

        viewModel.getAllPlaces().observe(viewLifecycleOwner, {
            placeAdapter.submitList(it)

//            if (it.isEmpty()) binding.llEmptyState.visibility = View.VISIBLE
//            else binding.llEmptyState.visibility = View.GONE
        })

        viewModel.progressBarLiveData.observe(viewLifecycleOwner, {
            if (it) binding.pbLoading.visibility = View.VISIBLE
            else binding.pbLoading.visibility = View.GONE
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {

        })
    }

    override fun unSubscribe() {

    }

    private fun navigateToDetailsFragment(placeId: String) {
        val action = PlacesFragmentDirections.actionPlacesFragmentToPlaceDetailFragment(placeId)
        Navigation.findNavController(binding.root).navigate(action)
    }

    override fun onStart() {
        super.onStart()

        val serviceIntent = Intent(requireContext(), ForegroundOnlyLocationService::class.java)
        requireActivity().bindService(
            serviceIntent,
            foregroundOnlyServiceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            foregroundOnlyBroadcastReceiver,
            IntentFilter(
                ForegroundOnlyLocationService.ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST
            )
        )
    }


    override fun onPause() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(
            foregroundOnlyBroadcastReceiver
        )
        super.onPause()
    }

    override fun onStop() {
        if (foregroundOnlyLocationServiceBound) {
            requireActivity().unbindService(foregroundOnlyServiceConnection)
            foregroundOnlyLocationServiceBound = false
        }
        super.onStop()
    }

    private fun foregroundPermissionApproved(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun requestForegroundPermissions() {
        val provideRationale = foregroundPermissionApproved()

        if (provideRationale) {
            Snackbar.make(
                binding.root,
                R.string.permission_rationale,
                Snackbar.LENGTH_LONG
            )
                .setAction(R.string.ok) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
                    )
                }
                .show()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE -> when {
                grantResults.isEmpty() -> {}

                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    foregroundOnlyLocationService?.subscribeToLocationUpdates()

                else -> {

                    Snackbar.make(
                        binding.root,
                        R.string.permission_denied_explanation,
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(R.string.settings) {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                BuildConfig.APPLICATION_ID,
                                null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        .show()
                }
            }
        }
    }

    private inner class ForegroundOnlyBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val location = intent.getParcelableExtra<Location>(
                ForegroundOnlyLocationService.EXTRA_LOCATION
            )

            if (location != null) {
                onNewLocation(location)
                lastLoc = location
            }
        }
    }

    private fun onNewLocation(location: Location) {
        if (lastLoc == null) {
            viewModel.pager.index = 0
            viewModel.fetchPlaces(viewModel.pager.index, location.ll())

        } else {
            val distance = lastLoc?.distanceTo(location) ?: 0F
            if (distance > 100) {
                viewModel.pager.index = 0
                viewModel.fetchPlaces(viewModel.pager.index, location.ll())
            }
        }

    }

    override fun onDestroy() {
        lastLoc?.let { userSharedPref.saveLastLocation(it) }
        super.onDestroy()
    }

}