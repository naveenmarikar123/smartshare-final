package com.example.naveenmarikar.smartshare1;

/**
 * Created by naveenmarikar on 13/05/15.
 */
import android.graphics.drawable.Drawable;

public class SearchResult {
    public String title;
    public String id;
    public Drawable icon;

    /**
     * Create a search result with text and an icon
     * @param title
     * @param icon
     */
    public SearchResult(String title, Drawable icon , String id) {
        this.title = title;
        this.icon = icon;
        this.id = id;
    }

    /**
     * Return the title of the result
     */
    @Override
    public String toString() {
        return id;
    }

}