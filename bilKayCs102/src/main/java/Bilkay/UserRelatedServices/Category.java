package Bilkay.UserRelatedServices;

import java.util.ArrayList;

public class Category {

    public static String[] categoryNames = {"Visual Arts", "Performing Arts", "Physical Sports",
            "Board and Card Games", "Music", "Science", "Social Sciences",
            "Cinema", "Outdoor Activities", "Community Services", "Writing", "Reading"};
    public static int categoryIDGenerator = 1;
    private final int categoryID;
    private final String name;
    private final ArrayList<SubCategory> subcategories;

    public Category(String name) {
        this.categoryID = categoryIDGenerator;
        categoryIDGenerator++;
        this.name = name;
        this.subcategories = new ArrayList<>();

    }

    public static void resetCategoryIDs() {
        categoryIDGenerator = 1;
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

    public int getCategoryID() {
        return categoryID;
    }

    @Override
    public String toString() {
        return name;
    }
}

