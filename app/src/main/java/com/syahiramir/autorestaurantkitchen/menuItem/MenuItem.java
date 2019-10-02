package com.syahiramir.autorestaurantkitchen.menuItem;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.syahiramir.autorestaurantkitchen.IngredientsActivity;

import java.util.ArrayList;

// The Factory Pattern
public class MenuItem {
    String name;
    int itemNumber;
    String orderId;
    Boolean isPreparing = false;
    Boolean isCooking = false;
    Boolean isReadyForPickup = false;
    Boolean isPickedUp = false;
    Boolean isPaid = false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<String> ingredients = new ArrayList<>();

    // Used by the user. why would the kitchen order?!
    public void order(final Activity activity) {
        // Do nothing
    }

    public void viewIngredients(Activity activity) {
        Intent i = new Intent(activity, IngredientsActivity.class);
        i.putExtra("name", name);
        i.putExtra("ingredients", ingredients);
        activity.startActivity(i);
    }

    public void prepareIngredients(final Activity activity, String userId) {
        db.collection("orders").document(userId).collection("food").document(orderId)
                .update("isPreparing", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, "Prepared " + name,
                                Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Preparing " + name + " failed, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void cook(final Activity activity, String userId) {
        db.collection("orders").document(userId).collection("food").document(orderId)
                .update("isCooking", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, "Prepared " + name,
                                Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Preparing " + name + " failed, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void readyForPickup(final Activity activity, String userId) {
        db.collection("orders").document(userId).collection("food").document(orderId)
                .update("isReadyForPickup", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, "Prepared " + name,
                                Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Preparing " + name + " failed, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Used by the customer. Why would the kitchen pickup the food?
    public void pickup() {
        // DO nothing
    }

    // Used by the customer. Why would the kitchen pay for the food?
    public void pay() {
        // DO nothing
    }

    public String getName() {
        return name;
    }

    public String getOrderId() {
        return orderId;
    }

    public Boolean isPreparing() {
        return isPreparing;
    }

    public Boolean isCooking() {
        return isCooking;
    }

    public Boolean isReadyForPickup() {
        return isReadyForPickup;
    }

    public Boolean isPickedUp() {
        return isPickedUp;
    }

    public Boolean isPaid() {
        return isPaid;
    }
}
