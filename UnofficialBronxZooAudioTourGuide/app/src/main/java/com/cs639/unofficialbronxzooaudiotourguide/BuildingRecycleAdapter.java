package com.cs639.unofficialbronxzooaudiotourguide;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

public class BuildingRecycleAdapter extends RecyclerView.Adapter<BuildingRecycleAdapter.MyViewHolderBuilding>{
    String data1[], data2[];
    int images[], visibility[];
    Context context;
    CompassListFragment theParent;
    AllAppData myAppData;
    View rowView;
    public  BuildingRecycleAdapter(Context ct, CompassListFragment myDad, AllAppData theAppData, String[] s1, String[] s2, String[] s3, int[] myimages, int[] myvisibility, int myWidth){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = myimages;
        visibility = myvisibility;
        myAppData = theAppData;
        theParent = myDad;
    }

    @NonNull
    @Override
    public MyViewHolderBuilding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderBuilding holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolderBuilding extends RecyclerView.ViewHolder  {
        TextView txtZooName, txtBiNom;
        ImageView imgAnimal, imgCheck;
        ConstraintLayout parentLayout;
        public MyViewHolderBuilding(@NonNull View itemView) {
            super(itemView);
            txtZooName = itemView.findViewById(R.id.rowBuildingAnimalNameTxt);
            txtBiNom = itemView.findViewById(R.id.rowBuildingAnimalBiNomTxt);
            imgAnimal = itemView.findViewById(R.id.rowBuildingAnimalImg);
            imgCheck = itemView.findViewById(R.id.imgCheckbox);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            // Create the observer which updates the UI.
            final Observer<String> locationObserver = new Observer<String>() {
                @Override
                public void onChanged(@Nullable final String newLocation) {
                    // Update the UI, in this case, a TextView.
                    String itemName = txtZooName.getText().toString();
                    Location itemLocation = myAppData.getLocationOf(itemName);
                    Location phoneLocation = myAppData.getCurrentPhoneLocation();

                }
            };

            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            myAppData.getCurrentLocation().observe(theParent, locationObserver);

        }
    }
}
