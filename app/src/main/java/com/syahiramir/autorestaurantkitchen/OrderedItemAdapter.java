package com.syahiramir.autorestaurantkitchen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.syahiramir.autorestaurantkitchen.menuItem.MenuItem;

import java.util.List;

public class OrderedItemAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private String userId;
    private List<MenuItem> menuItems;

    public OrderedItemAdapter(Activity activity, List<MenuItem> menuItems, String userId) {
        this.activity = activity;
        this.menuItems = menuItems;
        this.userId = userId;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int location) {
        return menuItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.ordered_item, null);

        TextView name = convertView.findViewById(R.id.item_name);
        TextView orderStatus = convertView.findViewById(R.id.orderStatus);
        Button ingredientsButton = convertView.findViewById(R.id.ingredients_button);
        Button progressButton = convertView.findViewById(R.id.progress_button);

        final MenuItem m = menuItems.get(position);
        name.setText(m.getName());
        ingredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.viewIngredients(activity);
            }
        });

        if (!m.isPreparing()) {
            progressButton.setText("Prepare Ingredients");
            progressButton.setEnabled(true);
            orderStatus.setText("Order Received");
            progressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m.prepareIngredients(activity, userId);
                }
            });
        } else if (!m.isCooking()) {
            progressButton.setText("Cook");
            progressButton.setEnabled(true);
            orderStatus.setText("Ingredients Prepared");
            progressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m.cook(activity, userId);
                }
            });
        } else if (!m.isReadyForPickup()) {
            progressButton.setText("Serve");
            orderStatus.setText("Food ready to serve");
            progressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m.readyForPickup(activity, userId);
                }
            });
        } else if (!m.isPickedUp()) {
            progressButton.setVisibility(View.GONE); // The kitchen has no use for this button anymore. Customer has picked up the food
            orderStatus.setText("Food ready for pickup by customer");
        } else if (!m.isPaid()) {
            progressButton.setVisibility(View.GONE); // The kitchen has no use for this button anymore. Customer is paying for food
            orderStatus.setText("Awaiting payment by customer");
        } else {
            // All actions done
            progressButton.setVisibility(View.GONE);// The kitchen has no use for this button anymore. Customer has paid for food
            orderStatus.setText("Food is paid");
        }

        return convertView;
    }

}