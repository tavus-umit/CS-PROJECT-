package Bilkay.UserRelatedServices;

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

    public static String[] subCategoryNamesForBoardAndCardGames = {"Chess", "Go", "Bridge", "Poker", "Scrabble", "Sudoku", "Mahjong", "Backgammon",
            "Rubik's Cube", "Shogi", "Xiangqi", "Checkers", "Othello", "Mastermind", "Risk",
            "Trivia games", "Stratego", "Catan", "Rummy", "Magic: The Gathering", "Monopoly",
            "Clue", "Ticket to Ride", "Carcassonne", "Settlers of Catan", "Uno", "Battleship",
            "Trivial Pursuit", "Dominion", "Hanabi", "Codenames", "Love Letter"};
    public static String[] subCategoryNamesForMusic= {"Classical", "Jazz", "Rock", "Pop", "Hip Hop", "Country", "R&B", "Electronic",
            "Reggae", "Folk", "Blues", "Metal", "Punk", "Alternative", "Soul", "Gospel",
            "Funk", "Indie", "World", "Latin", "Instrumental", "Opera", "Dance", "Acoustic",
            "Rap", "Experimental", "Choral", "Orchestral", "Ska", "Techno", "Disco", "Ambient"};
    public static String[] subCategoryNamesForCinema= { "Action", "Adventure", "Comedy", "Drama", "Horror", "Romance", "Thriller", "Sci-Fi",
            "Fantasy", "Animation", "Family", "Documentary", "Mystery", "Crime", "Historical",
            "Biography", "Musical", "Western", "War", "Superhero", "Suspense", "Film Noir"};
    public static String[] subCategoryNamesForOutdoorActivities= {"Hiking", "Camping", "Cycling", "Fishing", "Canoeing", "Kayaking", "Rock Climbing", "Mountaineering",
            "Skiing", "Snowboarding", "Surfing", "Swimming", "Scuba Diving", "Snorkeling", "Sailing", "Whitewater Rafting",
            "Gardening", "Bird Watching", "Photography", "Stargazing", "Geocaching", "Outdoor Yoga", "Picnicking", "Trail Running"};
    public static String[] subCategoryNamesForCommunityServices = { "Volunteering", "Mentoring", "Tutoring", "Homelessness Support", "Food Drives", "Elderly Care",
            "Youth Programs", "Community Clean-up", "Animal Shelter Support", "Environmental Conservation",
            "Health Education", "Disaster Relief", "Fundraising", "Literacy Programs", "Soup Kitchens",
            "Crisis Hotline", "Community Gardens", "Blood Donation", "Advocacy", "Habitat Restoration"};
    public static String[] subCategoryNamesForWritings = { "Fiction", "Non-fiction", "Poetry", "Short Stories", "Novels", "Essays", "Biographies",
            "Autobiographies", "Memoirs", "Journalism", "Creative Writing", "Screenwriting", "Playwriting",
            "Technical Writing", "Copywriting", "Blogging", "Travel Writing", "Historical Writing",
            "Scientific Writing", "Academic Writing", "Children's Books", "Fantasy Writing", "Mystery Writing",
            "Romance Writing", "Thriller Writing"};
    public static String[] subCategoryNamesForReadings = { "Fiction", "Non-fiction", "Mystery", "Science Fiction", "Fantasy", "Romance", "Biography",
            "Autobiography", "History", "Self-help", "Thriller", "Crime", "Young Adult", "Children's Books",
            "Poetry", "Classic Literature", "Contemporary Literature", "Historical Fiction",
            "Science and Technology", "Philosophy", "Travel", "Art and Photography", "Business and Finance",
            "Health and Wellness", "Cookbooks", "Religion and Spirituality"};




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
