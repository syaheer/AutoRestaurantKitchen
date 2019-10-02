package com.syahiramir.autorestaurantkitchen;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.syahiramir.autorestaurantkitchen.menuItem.BeefSandwich;
import com.syahiramir.autorestaurantkitchen.menuItem.CreamyOatmeal;
import com.syahiramir.autorestaurantkitchen.menuItem.MenuItem;
import com.syahiramir.autorestaurantkitchen.menuItem.SpinachLasagna;
import com.syahiramir.autorestaurantkitchen.menuItem.TacoSalad;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    OrderedItemAdapter orderedItemAdapter;
    ArrayList<MenuItem> orderedItemList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView orders = findViewById(R.id.orders_list);
        String userId = getIntent().getStringExtra("userId");

        orderedItemAdapter = new OrderedItemAdapter(this, orderedItemList, userId);
        orders.setAdapter(orderedItemAdapter);

        db.collection("orders").document(userId).collection("food")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (value != null) {
                            orderedItemList.clear();
                            for (QueryDocumentSnapshot document : value) {
                                long itemId = (long) document.get("menuId");
                                if (itemId == 1) {
                                    orderedItemList.add(new BeefSandwich(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                } else if (itemId == 2) {
                                    orderedItemList.add(new CreamyOatmeal(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                } else if (itemId == 3) {
                                    orderedItemList.add(new SpinachLasagna(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                } else if (itemId == 4) {
                                    orderedItemList.add(new TacoSalad(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                }
                            }
                            orderedItemAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
