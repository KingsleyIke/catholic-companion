package com.kingstek.companion.ui.parish.updates

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.databinding.FragmentUpdateParishInfoBinding
import com.kingstek.companion.models.parish.ImageModel
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.onItemClickListener
import java.net.URI


class UpdateParishInfoFragment : Fragment() {

    private var _binding: FragmentUpdateParishInfoBinding? = null
    private val binding get() =  _binding!!
    private lateinit var viewModel: UpdateParishInfoViewModel
    private lateinit var imgURI: Uri

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateParishInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[UpdateParishInfoViewModel::class.java]

        //todo debug fragment name title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Update Parish Info"

        if (checkPermissionForReadExtertalStorage() == false) {
            try {
                requestPermissionForReadExtertalStorage()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /**
         * ParishList Spiner
         */
        val parishSpinner: Spinner = binding.spParishListSpinner
        val timeSpinner: Spinner = binding.spParishListSpinner

        binding.etParishName.setOnClickListener{
            binding.spParishListSpinner.performClick()
        }

        binding.spParishListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item from the adapter
                val selectedItem = parent.getItemAtPosition(position).toString()
                val editable = Editable.Factory.getInstance().newEditable(selectedItem)
                binding.etParishName.text = editable
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
                // to if nothing selected display error
            }
        }

        //spinner adapter
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.parishListSpinner.value!!
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        parishSpinner.adapter = dataAdapter

        timeSpinner.adapter = dataAdapter


        /**
         * Pastoral team setup
         */
        fun validatePastoralTeam(): Boolean {

            if(binding.etTitle.text?.isEmpty()!!) {
                binding.tilTitle.error = "Field must not be empty"
                return false
            }

            binding.tilTitle.error = null

            if(binding.etName.text?.isEmpty()!!) {
                binding.tilName.error = "Field must not be empty"
                return false
            }

            binding.tilName.error = null

            if(binding.etPosition.text?.isEmpty()!!) {
                binding.tilPosition.error = "Field must not be empty"
                return false
            }

            binding.tilPosition.error = null

            if(binding.etPhoneNumber.text?.isEmpty()!!) {
                binding.tilPhonenumber.error = "Field must not be empty"
                return false
            }

            binding.tilPhonenumber.error = null

            return true
        }


        viewModel.initilaizePastorlTeam()

        val pastoralRecyclerView = binding.rvPastoralTeam
        val pastoralAdapter = PastoralAdapter(viewModel.getPastoralList(), object : onItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                viewModel.removePastoralteam(position)
            }
        })


        pastoralRecyclerView.adapter = pastoralAdapter
        pastoralRecyclerView.layoutManager = LinearLayoutManager(context)


        /**
         * add edit views to pastoral team list that will be passed in when pushing parish details
         * toggles visibility of edit views for adding team details
         */
        binding.tvAddBtn.setOnClickListener {

            binding.ltPastoralTeam.visibility = View.VISIBLE

            if (!validatePastoralTeam()) {
                return@setOnClickListener
            }

            val pastoralTeam = PastoralTeam(
                binding.etPosition.text.toString(),
                binding.etTitle.text.toString(),
                binding.etName.text.toString(),
                binding.etPhoneNumber.text.toString()
            )

            viewModel.addPastoralTeam(pastoralTeam)

            // is this necessary already being done in adapter
            pastoralAdapter.notifyDataSetChanged()

            val emptyString = ""
            val editable = Editable.Factory.getInstance().newEditable(emptyString)

            binding.etPosition.text = editable
            binding.etTitle.text = editable
            binding.etName.text = editable
            binding.etPhoneNumber.text = editable
        }

        /**
         * Toggle pastoral team add and done buttons
         * toggles visibility of edit views for adding team details
         */

        binding.tvDoneBtn.setOnClickListener {
            binding.ltPastoralTeam.visibility = View.GONE
        }

        /**
         * Images SteUp
         */

        viewModel.initilaizeParishImageList()

        val parishImageRecyclerView = binding.rvParishImages
        val parishImageAdapter = ParishImageAdapter(viewModel.getParishImageList(), object : onItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                viewModel.removeParishImageList(position)
            }
        })

        parishImageRecyclerView.adapter = parishImageAdapter
        //todo fix images recycler view not displaying after random times after images
        parishImageRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.tvImagesAddBtn.setOnClickListener {

            binding.ltParishImages.visibility = View.VISIBLE

            if (binding.etImageTitle.text?.isEmpty()!!) {
                //todo add a check for empty images too
                //todo add a check for empty lists
                binding.tilImageTitle.error = "Enter Image tile and select Image"
                return@setOnClickListener
            }

            val parishImages = ImageModel(
                imgURI,
                binding.etImageTitle.text.toString(),
            )

            viewModel.addParishImageList(parishImages)

            // is this necessary already being done in adapter
            pastoralAdapter.notifyDataSetChanged()

            val emptyString = ""
            val editable = Editable.Factory.getInstance().newEditable(emptyString)

            binding.etImageTitle.text = editable
            binding.etImageTitle.error = null
        }


        binding.tvImagesDoneBtn.setOnClickListener {
            binding.ltParishImages.visibility = View.GONE
        }

        binding.ivSelectedImage.setOnClickListener {
            pickImage()
        }

        /**
         * run validation on all values
         */
        fun validateParishValues(): Boolean{

            return false
        }

        return root
    }

    private fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = (Intent.ACTION_GET_CONTENT)

        pickImageLauncher.launch(intent)
    }

    private var pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data

            val uri: Uri? = data?.data

            imgURI = uri!!
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            binding.ivSelectedImage.setImageBitmap(bitmap)
        }
    }

    private fun checkPermissionForReadExtertalStorage(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    @Throws(Exception::class)
    fun requestPermissionForReadExtertalStorage() {
        try {
            ActivityCompat.requestPermissions(
                (this as Activity), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                25
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}