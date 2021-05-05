package com.cs639.unofficialbronxzooaudiotourguide;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
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
    Activity theParent;
    AllAppData myAppData;
    View rowView;
    public  BuildingRecycleAdapter(Context ct, String[] s1, String[] s2, int[] myimages, int[] myvisibility, Activity myDad){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = myimages;
        visibility = myvisibility;
        theParent = myDad;
    }

    @NonNull
    @Override
    public MyViewHolderBuilding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.building_animal_row, parent, false);
        rowView = parent;
        return new MyViewHolderBuilding(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderBuilding holder, int position) {
        holder.txtZooName.setText(data1[position]);
        holder.txtBiNom.setText(data2[position]);
        holder.imgAnimal.setImageResource(images[position]);
        holder.imgCheck.setVisibility(visibility[position]);
//        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//               // myAppData.compassViewClicked(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
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
            imgCheck = itemView.findViewById(R.id.imgBuildingCheckbox);
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

        }
    }
}
