package com.kingstek.companion.ui.parish.updates

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.kingstek.companion.R
import com.kingstek.companion.databinding.ParishImageDialogLayoutBinding
import com.kingstek.companion.databinding.PastoralTeamBinding
import com.kingstek.companion.databinding.PastorialDialogLayoutBinding
import com.kingstek.companion.models.parish.ImageModel
import com.kingstek.companion.models.parish.PastoralTeam

class ParishImageDialog(val viewModel: UpdateParishInfoViewModel, val pastoralAdapter: ParishImageAdapter): DialogFragment() {

    private lateinit var binding: ParishImageDialogLayoutBinding
    private lateinit var imgURI: Uri


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       binding = ParishImageDialogLayoutBinding.inflate(layoutInflater)
        val view = binding.root


        binding.ivSelectedImage.setOnClickListener {
            pickImage()
        }

        //todo fix cancelable not working fine
        return AlertDialog.Builder(requireContext())
            .setTitle("Fill all Fields")
            .setView(view)
            .setNegativeButton("Cancel") { _, _ ->
                // Dialog was cancelled
            }
            .setPositiveButton("ADD") { _, _ ->

                if (binding.etImageTitle.text?.isEmpty()!!) {
                    //todo add a check for empty images too
                    //todo add a check for empty lists
                    binding.tilImageTitle.error = "Enter Image tile and select Image"
                } else {
                    val parishImages = ImageModel(
                        imgURI,
                        binding.etImageTitle.text.toString(),
                    )

                    viewModel.addParishImageList(parishImages)

                    // is this necessary already being done in adapter
                    pastoralAdapter.notifyDataSetChanged()
                }

            }
            .setCancelable(false)
            .create()
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

}