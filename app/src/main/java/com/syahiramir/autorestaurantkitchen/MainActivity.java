package com.syahiramir.autorestaurantkitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> userIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.orders_list);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userIdList);

        listView.setAdapter(arrayAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("orders");
        query.addSnapshotListener(
                new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot snapshots,
                                        FirebaseFirestoreException e) {
                        userIdList.clear();
                        for (DocumentSnapshot snapshot : snapshots.getDocuments()) {
                            userIdList.add(snapshot.getId());
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
                intent.putExtra("userId", userIdList.get(i));
                startActivity(intent);
            }
        });
    }
}
