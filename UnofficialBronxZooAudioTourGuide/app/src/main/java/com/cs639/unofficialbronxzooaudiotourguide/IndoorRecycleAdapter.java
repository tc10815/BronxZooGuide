package com.cs639.unofficialbronxzooaudiotourguide;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


/**
 * The Recycle Adapter for use with inside Animals
 *
 * @author Tom Cookson
 */
public class IndoorRecycleAdapter extends RecyclerView.Adapter<IndoorRecycleAdapter.MyViewHolder> {
    String data1[], data2[];
    int images[];
    Context context;
    AnimalContainerActivity theParent;
    //    AllAppData myAppData;
    View rowView;

    public IndoorRecycleAdapter(Context ct, String[] dataList, String[] dataList2, int[] myimages, AnimalContainerActivity myDad) {
        context = ct;
         data1 = dataList;
         data2 = dataList2;
//        data3 = s3;
        images = myimages;
        theParent = myDad;
//        screenWidth = myWidth;
//        myAppData = theAppData;
//        theParent = myDad;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inside_animal_row, parent, false);
        rowView = view;

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtZooName.setText(data1[position]);
        holder.txtBiNom.setText(data2[position]);
        holder.imgAnimal.setImageResource(images[position]);
        holder.parentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                theParent.launchAnimal(data1[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtZooName, txtBiNom;
        ImageView imgAnimal;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtZooName = itemView.findViewById(R.id.rowinsideAnimalNameTxt);
            txtBiNom = itemView.findViewById(R.id.rowinsideAnimalBiNomTxt);
            imgAnimal = itemView.findViewById(R.id.rowinsideAnimalImg);
            parentLayout = itemView.findViewById(R.id.parentLayout);


        }
    }
}