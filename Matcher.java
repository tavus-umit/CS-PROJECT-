package Bilkay;

import java.util.HashMap;

public abstract class Matcher {

    int catScore=0;
    int subcatScore=0;
    int matchScore;
    IndividualMatcher im;

    public static HashMap<user,Integer>  createNomineeList(user userToBeMatched, user[] otherUser){
        HashMap<user,Integer> userxscore = new HashMap<>();

        IndividualMatcher im = new IndividualMatcher(userToBeMatched,userToBeMatched);

        for (int i = 0; i < otherUser.length; i++) {
            im.setUsers(userToBeMatched, otherUser[i]);
            if(!im.getPreviouslyMatched()){
                userxscore.put(otherUser[i], im.calcMatchScore());
            }
        }
        return userxscore;
    }

}
