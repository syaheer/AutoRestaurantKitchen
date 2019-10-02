package com.syahiramir.autorestaurantkitchen.menuItem;

// The Iterator Pattern! Note that this is used on the user app. The kitchen has no use for the menu.
public class MenuItemIterator implements Iterator {
    MenuItem[] items;
    int position = 0;

    public MenuItemIterator(MenuItem[] items) {
        this.items = items;
    }

    public Object next() {
        MenuItem menuItem = items[position];
        position = position + 1;
        return menuItem;
    }

    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }
}
