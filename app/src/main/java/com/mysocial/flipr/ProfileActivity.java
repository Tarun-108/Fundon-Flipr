package com.mysocial.flipr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mysocial.flipr.dashboard.DashboardActivity;
import com.mysocial.flipr.models.DetailsModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    TextInputEditText name, mobile, address, occupation;
    ImageView profilephoto;
    Button proceed;
    DetailsModel detailsmodel;
    TextView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        detailsmodel=(DetailsModel) getIntent().getSerializableExtra("abba");
        name = findViewById(R.id.nameagain);
        mobile = findViewById(R.id.phnnoagain);
        address = findViewById(R.id.addressagain);
        occupation = findViewById(R.id.occagain);
        proceed = findViewById(R.id.proceed);
        profilephoto=findViewById(R.id.profilephoto);
        back1=findViewById(R.id.back1);

        if(detailsmodel.getName()!=null)
            name.setText(detailsmodel.getName());
        if(detailsmodel.getMobile()!=null)
            mobile.setText(detailsmodel.getMobile());
        if(detailsmodel.getAddress()!=null)
            address.setText(detailsmodel.getAddress());
        if(detailsmodel.getOccupation()!=null)
            occupation.setText(detailsmodel.getOccupation());

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Map<String, String> params = new HashMap<>();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences("All Details",MODE_PRIVATE);
                String username=sharedPreferences.getString("userName","");
                String email =sharedPreferences.getString("email","");

                params.put("email",email);
                params.put("userName",username);
                params.put("name", name.getText().toString());
                params.put("mobile",mobile.getText().toString());
                params.put("address",address.getText().toString());
                params.put("occupation", occupation.getText().toString());
                params.put("imgUrl","blah");
                Intent intent = new Intent(ProfileActivity.this, BankDetailsActivity.class);
                intent.putExtra("1st page", (Serializable) params);
                intent.putExtra("abba",(Serializable) detailsmodel);

//                Log.d("params", String.valueOf(params));
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ProfileActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
