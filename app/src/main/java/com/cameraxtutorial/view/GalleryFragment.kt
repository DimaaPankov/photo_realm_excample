package com.cameraxtutorial.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cameraxtutorial.GalleryAdapter
import com.cameraxtutorial.databinding.FragmentGalleryBinding
import java.io.File

class GalleryFragment : Fragment() {



val binding by lazy {FragmentGalleryBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val directory = File((requireContext() as MainActivity).externalMediaDirs[0].absolutePath)
        val files = directory?.listFiles() as Array<File>
        binding.viewPager.setLayoutManager(GridLayoutManager(MainActivity(), 2))
        val adapter = GalleryAdapter(files.reversedArray())
        binding.viewPager.adapter = adapter

        MainActivity.goBack = {findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToMainFragment())}


    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

}
