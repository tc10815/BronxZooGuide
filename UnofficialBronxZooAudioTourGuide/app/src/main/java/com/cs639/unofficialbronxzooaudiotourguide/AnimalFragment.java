package com.cs639.unofficialbronxzooaudiotourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This class shows animal details, links and audio tour data for a
 * animal that outside in the zoo (not a stub)
 *
 * @author Rucha Madan
 */
public class AnimalFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnimalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AnimalFragment.
     */
    public static AnimalFragment newInstance() {
        AnimalFragment fragment = new AnimalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AllAppData userModel = new ViewModelProvider(requireActivity()).get(AllAppData.class);
        Animal myAnimal = userModel.getCurrentlySelectedAnimal();
        View rootView = inflater.inflate(R.layout.fragment_animal, container, false);
        TextView txtZooName = rootView.findViewById(R.id.zooNameAnimalFragTxt);
        TextView txtBiNom = rootView.findViewById(R.id.BiNomAnimalFragTxt);
        txtZooName.setText(myAnimal.getZooName());
        txtBiNom.setText(myAnimal.getBinomialNomenclature());
        return rootView;
    }
}