package Bilkay;

public class SubCategory {
    public static String[] subCategoryNamesForVisualArts = {"Painting","Drawing", "Sculpture","Ceramics",
            "Photography", "Videos","Filmmaking", "Design", "Crafting" ,"DIY", "Architecture", "Graphic Design",
            "Animation", "Photoshop", "3D Printing", "Printmaking"};
    public static String[] subCategoryNamesForPerformArts = {"Music", "Dancing", "Theatre", "Ballet", "Illusion", "Pantomime",
            "Improv", "Opera", "Modern Dancing", "Hiphop Dancing","Tap Dance", "Modern Dance", "Folk Dance","Disco Dancing",
            "Electronic Dance", "Partner Dances", "Breakdance"};
    public static String[] subCategoryNamesForScience = {"Mathematics", "Physics", "Chemistry", "Biology", "Geology",
            "Data Science", "Computer Science", "Biochemistry", "Zoology", "Botany", "Medicine", "Engineering"};
    public static String[] subCategoryNamesForSocialSciences= {"Psychology", "Sociology", "Political Science", "Geography",
            "Archaeology", "Economics", "Linguistics", "Anthropology", "Law", "History", "International Relations", "Demography"};
    public static String[] subCategoryNamesForPhysicalSports = {"Badminton","Tennis", "Squash", "Table Tennis", "Archery",
            "Skateboarding", "Scootering", "Surfing", "Frisbee", "Climbing", "Canyoning", "Cycling", "Unicycling", "Aikido",
            "Judo", "Wrestlig", "Martial Arts", "Fencing (Swordplay)", "Ice Hockey", "Gymnastics",
            "Parkour", "Figure Skating", "Speed Skating", "Football", "Volleyball", "Basketball", "Handball", "Running",
            "Sailing", "Windsurfing", "Skiing", "Shooting Sports", "Fitness", "Walking", "Kayaking", "Canoeing", "Working Out", "Water Polo",
            "Rugby", "American Football", "Swimming", "Scuba Diving", "Cliff Diving", "Weight Lifting","Motor Sports", "Softball", "Cricket", };
    private static int subCategoryIDGenerator = 1;
    private int subCategoryID;
    private String name;
    private Category category;
    public SubCategory(String name, Category category) {
        this.subCategoryID = subCategoryIDGenerator;
        subCategoryIDGenerator++;
        this.name = name;
        this.category = category;
        category.addSubcategory(this);
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    @Override
    public String toString() {
        return name;
    }
}
