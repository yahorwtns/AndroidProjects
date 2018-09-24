package com.example.yahor.fitnessclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yahor.fitnessclub.Interface.ItemClickListener;
import com.example.yahor.fitnessclub.Model.Trainer;
import com.example.yahor.fitnessclub.ViewHolder.TrainerViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TrainerList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference trainerList;

    String categoryId="";

    FirebaseRecyclerAdapter<Trainer,TrainerViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);


        //Firebase
        database = FirebaseDatabase.getInstance();
        trainerList = database.getReference("Trainer");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_trainer);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent

        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null)
        {
            loadListTrainer(categoryId);

        }



    }

    private void loadListTrainer(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Trainer, TrainerViewHolder>(Trainer.class,R.layout.trainer_item,TrainerViewHolder.class,
                trainerList.orderByChild("MenuId").equalTo(categoryId)


                ) {
            @Override
            protected void populateViewHolder(TrainerViewHolder viewHolder, Trainer model, int position) {
                    viewHolder.trainer_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.trainer_image);

                final Trainer local = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       // Toast.makeText(TrainerList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                        Intent trainerDetail = new Intent(TrainerList.this,TrainerDetail.class);
                        //Send trainer id to new activity
                        trainerDetail.putExtra("TrainerId",adapter.getRef(position).getKey());
                        startActivity(trainerDetail);
                    }
                });

            }
        };

        recyclerView.setAdapter(adapter);


    }
}
