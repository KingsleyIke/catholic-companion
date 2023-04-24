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
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.graphics.green
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingstek.companion.R
import com.kingstek.companion.databinding.FragmentUpdateParishInfoBinding
import com.kingstek.companion.models.parish.ImageModel
import com.kingstek.companion.models.parish.Parish
import com.kingstek.companion.models.parish.PastoralTeam
import com.kingstek.companion.onItemClickListener
import kotlinx.coroutines.launch


class UpdateParishInfoFragment : Fragment() {

    private var _binding: FragmentUpdateParishInfoBinding? = null
    private val binding get() =  _binding!!
    private lateinit var viewModel: UpdateParishInfoViewModel

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
         * ParishList Spinner
         */
        //todo only set parish list spinner when selecting update parish info
        //todo set parish coming from as default selected spinner value

        viewModel.isUpdateOrNew.postValue(true)

        viewModel.isUpdateOrNew.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val parishSpinner: Spinner = binding.spParishListSpinner

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
            } else {
                val editable = Editable.Factory.getInstance().newEditable("")
                binding.etParishName.text = editable
            }
        } )



        /**
         * Deanery list Spinner
         */

        val deanerySpinner: Spinner = binding.spDeaneryListSpinner

        binding.etDeanery.setOnClickListener {
            binding.spDeaneryListSpinner.performClick()
        }

        binding.spDeaneryListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = parent?.getItemAtPosition(position).toString()
                val editable = Editable.Factory.getInstance().newEditable(selected)
                binding.etDeanery.text = editable
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }


        lifecycleScope.launch {
            try {

                var deanList: List<String> = viewModel.getDeanaryList()

                val deaneryAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    deanList
                )

                deaneryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                deanerySpinner.adapter = deaneryAdapter

            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

        /**
         * Diocese list Spinner
         */

        //todo setup dioces spiner and filter deanery spiner based on selectedt diocese
        val dioceseSpinner: Spinner = binding.spDioceseListSpinner

        binding.etDiocese.setOnClickListener {
            binding.spDioceseListSpinner.performClick()
        }

        binding.spDioceseListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                val editable = Editable.Factory.getInstance().newEditable(selectedItem)
                binding.etDiocese.text = editable
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        val diocesesAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.dioceseListSpinner.value!!
        )

        diocesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dioceseSpinner.adapter = diocesesAdapter



        /**
         * Setup Pastoral team recycler vieww
         */
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
         * toggles visibility of edit views for adding pastoral team details
         */
        binding.tvAddBtn.setOnClickListener {

            val dialogFragment = PastoralTeamDialog(viewModel, pastoralAdapter)
            dialogFragment.show(childFragmentManager, "pastoralDialog")

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

            val dialogFragment = ParishImageDialog(viewModel, parishImageAdapter)
            dialogFragment.show(childFragmentManager, "parishImageDialog")

//            binding.ltParishImages.visibility = View.VISIBLE
//
//            if (binding.etImageTitle.text?.isEmpty()!!) {
//                //todo add a check for empty images too
//                //todo add a check for empty lists
//                binding.tilImageTitle.error = "Enter Image tile and select Image"
//                return@setOnClickListener
//            }
//
//            val parishImages = ImageModel(
//                imgURI,
//                binding.etImageTitle.text.toString(),
//            )
//
//            viewModel.addParishImageList(parishImages)
//
//            // is this necessary already being done in adapter
//            pastoralAdapter.notifyDataSetChanged()
//
//            val emptyString = ""
//            val editable = Editable.Factory.getInstance().newEditable(emptyString)
//
//            binding.etImageTitle.text = editable
//            binding.etImageTitle.error = null
        }

//        binding.tvImagesDoneBtn.setOnClickListener {
//            binding.ltParishImages.visibility = View.GONE
//        }

//        binding.ivSelectedImage.setOnClickListener {
//            pickImage()
//        }

        //todo use alert dialogs to add images and pastoral team
        binding.btnPublish.setOnClickListener {

            if (!validateParishValues()) {
                Toast.makeText(requireContext(), "All required fields not filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            setValues()

            viewModel.getParishImageListUrl().observe(viewLifecycleOwner, Observer {
                setValues()
            })

            viewModel.uploadParishInfo(requireContext())

//            setValues()
        }

        binding.rbUpdateBtn.setOnClickListener {
            onRadioButtonClicked(it)
        }

        binding.rbNewAdditionBtn.setOnClickListener {
            onRadioButtonClicked(it)
        }

        return root
    }


    fun setValues() {

        viewModel.parishModel.postValue(
            Parish(parishName = binding.etParishName.text.toString(),
                address = binding.etParishAddress.text.toString(),
                website = binding.etParishWebsite.text.toString(),
                diocese = binding.etDiocese.text.toString(),
                deanery = binding.etDeanery.text.toString(),
                pastoralTeam = viewModel.getPastoralList().value,
                parishImage = viewModel.getParishImageListUrl().value,
                createdBy = viewModel.currentUser.toString()
            ))

    }

    /**
     * run validation on all values
     */
    fun validateParishValues(): Boolean{

        if(binding.etParishName.text?.isEmpty()!!) {
            binding.tilParishName.error = "Field must not be empty"
            return false
        }
        binding.tilParishName.error = null


        if(binding.etParishAddress.text?.isEmpty()!!) {
            binding.tilParishAddress.error = "Field must not be empty"
            return false
        }
        binding.tilParishAddress.error = null


        if(binding.etDiocese.text?.isEmpty()!!) {
            binding.tilDiocese.error = "Field must not be empty"
            return false
        }
        binding.tilDiocese.error = null


        if(binding.etDeanery.text?.isEmpty()!!) {
            binding.tilDeanery.error = "Field must not be empty"
            return false
        }
        binding.tilDeanery.error = null

        if (viewModel.getPastoralList().value == null || viewModel.getPastoralList().value!!.isEmpty()) {
            Toast.makeText(requireContext(), "Add at least one Pastoral team member", Toast.LENGTH_SHORT).show()

            return false
        }

        if (viewModel.getParishImageList().value == null || viewModel.getParishImageList().value!!.isEmpty()) {
            Toast.makeText(requireContext(), "Add at least one Parish Image ", Toast.LENGTH_SHORT).show()
            return false
        }

            return true
    }

    /**
     * Pastoral team setup
     */
//    fun validatePastoralTeam(): Boolean {
//
//        if(binding.etTitle.text?.isEmpty()!!) {
//            binding.tilTitle.error = "Field must not be empty"
//            return false
//        }
//
//        binding.tilTitle.error = null
//
//        if(binding.etName.text?.isEmpty()!!) {
//            binding.tilName.error = "Field must not be empty"
//            return false
//        }
//
//        binding.tilName.error = null
//
//        if(binding.etPosition.text?.isEmpty()!!) {
//            binding.tilPosition.error = "Field must not be empty"
//            return false
//        }
//
//        binding.tilPosition.error = null
//
//        if(binding.etPhoneNumber.text?.isEmpty()!!) {
//            binding.tilPhonenumber.error = "Field must not be empty"
//            return false
//        }
//
//        binding.tilPhonenumber.error = null
//
//        return true
//    }

//    private fun pickImage() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = (Intent.ACTION_GET_CONTENT)
//
//        pickImageLauncher.launch(intent)
//    }

    /**
     * Toogle update and new addition radio buttons
     */
    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.rb_update_btn -> {
                if (checked) viewModel.isUpdateOrNew.postValue(true)
            }

            R.id.rb_new_addition_btn -> {
                if (checked) viewModel.isUpdateOrNew.postValue(false)
            }
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
                (requireActivity()), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
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