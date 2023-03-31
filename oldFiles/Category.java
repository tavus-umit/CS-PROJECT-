import java.util.ArrayList;

public class Category {

    public static String name;
    public static ArrayList<SubCategory> subcategories;

    public Category(String name) {

        this.name = name;
        this.subcategories = new ArrayList<>();

    }

    public void addSubcategory(SubCategory subcategory) {
        this.subcategories.add(subcategory);
    }

    public String printSubcategories() {

    }

}

