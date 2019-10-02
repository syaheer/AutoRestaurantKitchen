package com.syahiramir.autorestaurantkitchen.menuItem;

// The Iterator Pattern! Note that this is used on the user app. The kitchen has no use for the menu.
public interface Iterator {
    boolean hasNext();
    Object next();
}
