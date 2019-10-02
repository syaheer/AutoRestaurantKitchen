package com.syahiramir.autorestaurantkitchen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IngredientsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        TextView header = findViewById(R.id.header);
        TextView list = findViewById(R.id.ingredients_list);

        header.setText("Ingredients for: " + getIntent().getStringExtra("name"));

        // Another iterator pattern, this time using Java's iterator
        for (String ingredient : getIntent().getStringArrayListExtra("ingredients")) {
            list.append("\t" + ingredient + "\n");
        }
    }
}
