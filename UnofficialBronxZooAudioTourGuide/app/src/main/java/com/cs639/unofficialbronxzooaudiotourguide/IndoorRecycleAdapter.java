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
    //    String data1[], data2[], data3[];
    int images[];
    Context context;
//    CompassListFragment theParent;
//    int screenWidth;

    //    AllAppData myAppData;
    View rowView;

    List<Animal> dataList = new ArrayList<>();

    public IndoorRecycleAdapter(Context ct, List<Animal> dataList, int[] myimages) {
        context = ct;
//        data1 = s1;
//        data2 = s2;
//        data3 = s3;
        images = myimages;
//        screenWidth = myWidth;
//        myAppData = theAppData;
//        theParent = myDad;

        this.dataList = dataList;
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
        holder.txtZooName.setText(dataList.get(position).getZooName());
        holder.txtBiNom.setText(dataList.get(position).getBinomialNomenclature());
//        holder.txtDistance.setText(dataList.get(position).get);
        holder.imgAnimal.setImageResource(images[position]);
        holder.parentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                myAppData.compassViewClicked(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtZooName, txtBiNom, txtDistance;
        ImageView imgAnimal, imgArrow;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtZooName = itemView.findViewById(R.id.rowinsideAnimalNameTxt);
            txtBiNom = itemView.findViewById(R.id.rowinsideAnimalBiNomTxt);
            txtDistance = itemView.findViewById(R.id.rowinsideDistanceTxt);
            imgAnimal = itemView.findViewById(R.id.rowinsideAnimalImg);
            imgArrow = itemView.findViewById(R.id.rowinsideArrowImg);
            parentLayout = itemView.findViewById(R.id.parentLayout);


        }
    }
}