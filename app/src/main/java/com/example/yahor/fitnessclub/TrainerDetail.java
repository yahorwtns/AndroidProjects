package com.example.yahor.fitnessclub;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.yahor.fitnessclub.Database.Database;
import com.example.yahor.fitnessclub.Model.Order;
import com.example.yahor.fitnessclub.Model.Trainer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TrainerDetail extends AppCompatActivity {

    TextView trainer_name,trainer_price,trainer_description;
    ImageView trainer_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String trainerId="";

    FirebaseDatabase database;
    DatabaseReference trainer;
    Trainer currentTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        trainer = database.getReference("Trainer");

        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        trainerId,currentTrainer.getName(),
                        numberButton.getNumber(),
                        currentTrainer.getPrice(),
                        currentTrainer.getDiscount()

                ));
                Toast.makeText(TrainerDetail.this, "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });

        trainer_description = (TextView) findViewById(R.id.trainer_description);
        trainer_name = (TextView) findViewById(R.id.trainer_name);
        trainer_price = (TextView) findViewById(R.id.trainer_price);
        trainer_image = (ImageView)findViewById(R.id.img_trainer);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get trainer id
            if(getIntent()!=null)
                trainerId = getIntent().getStringExtra("TrainerId");
            if(!trainerId.isEmpty()){
                getDetailTrainer(trainerId);

            }

    }

    private void getDetailTrainer(String trainerId) {
        trainer.child(trainerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentTrainer = dataSnapshot.getValue(Trainer.class);

                Picasso.with(getBaseContext()).load(currentTrainer.getImage())
                        .into(trainer_image);

                collapsingToolbarLayout.setTitle(currentTrainer.getName());
                trainer_price.setText(currentTrainer.getPrice());
                trainer_name.setText(currentTrainer.getName());
                trainer_description.setText(currentTrainer.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
