package Bilkay;

import java.util.ArrayList;

public class Category {

    public String name;
    public ArrayList<SubCategory> subcategories;

    public Category(String name) {

        this.name = name;
        this.subcategories = new ArrayList<>();

    }

    public void addSubcategory(SubCategory subcategory) {
        this.subcategories.add(subcategory);
    }

    public ArrayList<SubCategory> getSubcategories() {
        return subcategories;
    }

    public String getName() {
        return name;
    }
}

