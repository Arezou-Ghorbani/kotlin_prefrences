package arezou.ghorbani.s1_navigation.pages1

import arezou.ghorbani.s1_navigation.databinding.FragmentHomeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            sendBtn.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(textEdt.text.toString())
                findNavController().navigate(direction)
            }
        }
    }
}