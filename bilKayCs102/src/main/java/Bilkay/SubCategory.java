package Bilkay;

public class SubCategory {

    public String name;
    public Category category;
    public SubCategory(String name, Category category) {
        this.name = name;
        this.category = category;
        category.addSubcategory(this);
    }


    public static void main(String[] args) {

        // CREATION OF CATEGORIES
        Category visualArt = new Category("Visual Arts");
        Category performArts = new Category("Performing Arts");
        // Category literature = new Category("Literature");

        Category physicalSports = new Category("Physical Sports");
        Category mindSports = new Category("Mind Sports");
        Category eSports = new Category("E-Sports");
        Category extSports = new Category("Extreme Sports");

        Category BoardGames = new Category("Board Games");
        Category cardGames = new Category("Card Games");
        Category VideoGames = new Category("Board Games");

        Category Music = new Category("Music");
        Category Science = new Category("Science");
        Category SocialSciences = new Category("Social Sciences");
        Category Cinema = new Category("Cinema");
        Category OutdoorActivities = new Category("Outdoor Activities");
        Category CommunityServices = new Category("Community Services");
        Category Writing = new Category("Writing");
        Category Reading = new Category("Reading");


        // More categories to be added


        //adding subcategories to existing categories

        // VISUAL ART CATEGORY :
        SubCategory painting = new SubCategory("Painting", visualArt);
        SubCategory drawing = new SubCategory("Drawing", visualArt);
        SubCategory sculpture = new SubCategory("Sculpture", visualArt);
        SubCategory ceramics = new SubCategory("Ceramics", visualArt);
        SubCategory photography = new SubCategory("Photography", visualArt);
        SubCategory videos = new SubCategory("Videos", visualArt);
        SubCategory filmmaking = new SubCategory("Filmmaking", visualArt);
        SubCategory design = new SubCategory("Design", visualArt);
        SubCategory crafting = new SubCategory("Crafting", visualArt);
        SubCategory DIY = new SubCategory("DIY", visualArt);
        SubCategory architecture = new SubCategory("Architecture", visualArt);
        SubCategory graphicDesign = new SubCategory("Graphic Design", visualArt);
        SubCategory animation = new SubCategory("Animation", visualArt);
        SubCategory photoshop = new SubCategory("Photoshop", visualArt);
        SubCategory printing3D = new SubCategory("3D Printing", visualArt);
        SubCategory printmaking = new SubCategory("Printmaking", visualArt);

        // MIND SPORTS :

        // PERFORMING ARTS :
        SubCategory singing = new SubCategory("Music", performArts);
        SubCategory dancing = new SubCategory("Dancing", performArts);
        SubCategory theatre = new SubCategory("Theatre", performArts);
        SubCategory ballet = new SubCategory("Ballet", performArts);
        SubCategory illusion = new SubCategory("Illusion", performArts);
        SubCategory pantomime = new SubCategory("Pantomime", performArts);
        SubCategory improv = new SubCategory("Improv", performArts);
        SubCategory opera = new SubCategory("Opera", performArts);
        SubCategory modernDancing = new SubCategory("Modern Dancing", performArts);
        SubCategory hiphopDance = new SubCategory("Hiphop Dancing", performArts);
        SubCategory tapDance = new SubCategory("Tap Dance", performArts);
        SubCategory modernDance = new SubCategory("Modern Dance", performArts);
        SubCategory folkDance = new SubCategory("Folk Dance", performArts);
        SubCategory discoDancing = new SubCategory("Disco Dancing", performArts);
        SubCategory electronicDance = new SubCategory("Electronic Dance", performArts);
        SubCategory partnerDances = new SubCategory("Partner Dances", performArts);
        SubCategory breakdance = new SubCategory("Breakdance", performArts);
        SubCategory music = new SubCategory("Music", performArts);

        // SCIENCE CATEGORY :
        SubCategory maths = new SubCategory("Mathematics", Science);
        SubCategory phys = new SubCategory("Physics", Science);
        SubCategory chem = new SubCategory("Chemistry", Science);
        SubCategory bio = new SubCategory("Biology", Science);
        SubCategory geology = new SubCategory("Geology", Science);
        SubCategory data = new SubCategory("Data Science", Science);
        SubCategory comp = new SubCategory("Computer Science", Science);
        SubCategory biochem = new SubCategory("Biochemistry", Science);
        SubCategory zoology = new SubCategory("Zoology", Science);
        SubCategory botany = new SubCategory("Botany", Science);
        SubCategory medicine = new SubCategory("Medicine", Science);
        SubCategory engineering = new SubCategory("Engineering", Science);

        // SOCIAL SCIENCES CATEGORY :
        SubCategory psyc = new SubCategory("Psychology", SocialSciences);
        SubCategory socio = new SubCategory("Sociology", SocialSciences);
        SubCategory pols = new SubCategory("Political Science", SocialSciences);
        SubCategory geography = new SubCategory("Geography", SocialSciences);
        SubCategory archae = new SubCategory("Archaeology", SocialSciences);
        SubCategory econ = new SubCategory("Economics", SocialSciences);
        SubCategory ling = new SubCategory("Linguistics", SocialSciences);
        SubCategory anthropology = new SubCategory("Anthropology", SocialSciences);
        SubCategory law = new SubCategory("Law", SocialSciences);
        SubCategory history = new SubCategory("History", SocialSciences);
        SubCategory interRelations = new SubCategory("International Relations", SocialSciences);
        SubCategory demography = new SubCategory("Demography", SocialSciences);
        //SubCategory geography = new SubCategory("Geography", SocialSciences);
        //SubCategory geography = new SubCategory("Geography", SocialSciences);


        // SPORTS CATEGORY :
        SubCategory badminton = new SubCategory("Badminton", physicalSports);
        SubCategory tennis = new SubCategory("Tennis", physicalSports);
        SubCategory squash = new SubCategory("Squash", physicalSports);
        SubCategory tableTennis = new SubCategory("Table Tennis", physicalSports);
        SubCategory archery = new SubCategory("Archery", physicalSports);
        SubCategory skateboarding = new SubCategory("Skateboarding", physicalSports);
        SubCategory snowboarding = new SubCategory("Scootering", physicalSports);
        SubCategory scooter = new SubCategory("Scootering", physicalSports);
        SubCategory surfing = new SubCategory("Surfing", physicalSports);
        SubCategory frisbee = new SubCategory("Frisbee", physicalSports);
        SubCategory abseiling = new SubCategory("Abseiling", physicalSports);
        SubCategory Climbing = new SubCategory("Climbing", physicalSports);
        SubCategory canyoning = new SubCategory("Canyoning", physicalSports);
        SubCategory hiking = new SubCategory("Hiking", physicalSports);//outdoor or sports??
        SubCategory cycling = new SubCategory("Cycling", physicalSports);
        SubCategory unicycling = new SubCategory("Unicycling", physicalSports);
        SubCategory aikido = new SubCategory("Aikido", physicalSports);
        SubCategory judo = new SubCategory("Judo", physicalSports);
        SubCategory sambo = new SubCategory("Sambo", physicalSports);
        SubCategory wrestling = new SubCategory("Wrestlig", physicalSports);
        SubCategory kravmaga = new SubCategory("Krav Maga", physicalSports);
        SubCategory martialArts = new SubCategory("Martial Arts", physicalSports);
        SubCategory fencing = new SubCategory("Fencing (Swordplay)", physicalSports);
        SubCategory kungfu = new SubCategory("Kung fu", physicalSports);
        SubCategory iceHockey = new SubCategory("Ice Hockey", physicalSports);
        SubCategory gymnastics = new SubCategory("Gymnastics", physicalSports);
        SubCategory parkour = new SubCategory("Parkour", physicalSports);
        SubCategory figureSkating = new SubCategory("Figure Skating", physicalSports);
        SubCategory yachting = new SubCategory("Yatching", physicalSports);
        SubCategory speedSkating = new SubCategory("Speed Skating", physicalSports);
        SubCategory baseball = new SubCategory("Baseball", physicalSports);
        SubCategory football = new SubCategory("Football", physicalSports);
        SubCategory volleyball = new SubCategory("Volleyball", physicalSports);
        SubCategory basketball = new SubCategory("Basketball", physicalSports);
        SubCategory handball = new SubCategory("Handball", physicalSports);
        SubCategory running = new SubCategory("Running", physicalSports);
        SubCategory orientiring = new SubCategory("Orientiring", physicalSports);
        SubCategory sailing = new SubCategory("Sailing", physicalSports);
        SubCategory windsurfing = new SubCategory("Windsurfing", physicalSports);
        SubCategory skiing = new SubCategory("Skiing", physicalSports);
        SubCategory shootingSports = new SubCategory("Shooting Sports", physicalSports);
        SubCategory bodybuilding = new SubCategory("Body Building", physicalSports);
        SubCategory fitness = new SubCategory("Fitness", physicalSports);
        SubCategory walking = new SubCategory("Walking", physicalSports);
        SubCategory kayaking = new SubCategory("Kayaking", physicalSports);
        SubCategory canoeing = new SubCategory("Canoeing", physicalSports);
        SubCategory workingout = new SubCategory("Working Out", physicalSports);
        SubCategory waterPolo = new SubCategory("Water Polo", physicalSports);
        SubCategory rugby = new SubCategory("Rugby", physicalSports);
        SubCategory americanfootball = new SubCategory("American Football", physicalSports);
        SubCategory swimming = new SubCategory("Swimming", physicalSports);
        SubCategory scubaDiving = new SubCategory("Scuba Diving", physicalSports);
        SubCategory cliffDiving = new SubCategory("Cliff Diving", physicalSports);
        SubCategory weightLifting = new SubCategory("Weight Lifting", physicalSports);
        SubCategory motorSports = new SubCategory("Motor Sports", physicalSports);
        SubCategory equestry = new SubCategory("Equestry", physicalSports);
        SubCategory softball = new SubCategory("Softball", physicalSports);
        SubCategory cricket = new SubCategory("Cricket", physicalSports);
        SubCategory lacrosse = new SubCategory("Lacrosse", physicalSports);

        //  E-SPORTS CATEGORY :
        SubCategory streetFighter = new SubCategory("Street Fighter", eSports);
        SubCategory supSmashBros = new SubCategory("Super Smash Bros", eSports);

    }

}
