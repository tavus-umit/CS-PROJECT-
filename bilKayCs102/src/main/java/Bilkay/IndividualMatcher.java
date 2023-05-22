package Bilkay;

import Bilkay.UserRelatedServices.Category;
import Bilkay.UserRelatedServices.SubCategory;
import Bilkay.UserRelatedServices.user;

public class IndividualMatcher {

    int matchScore;
    boolean previouslyMatched;

    user u1;
    user u2;

    public IndividualMatcher(user firstPerson, user secondPerson) {
        this.u1 = firstPerson;
        this.u2 = secondPerson;
    }

    public void setUsers(user firstPerson, user secondPerson) {
        this.u1 = firstPerson;
        this.u2 = secondPerson;
    }

    public int calcMatchScore() {
        int catScore = 0;
        int subcatScore = 0;

        //checks for category matches
        for (Category cat1 : u1.getChosenCategories()) {

            for (Category cat2 : u2.getChosenCategories()) {
                if (cat1.equals(cat2)) {
                    catScore++;
                }
            }

        }
        //checks for subcategory matches
        for (SubCategory subcat1 : u1.getChosenSubCategories()) {

            for (SubCategory subcat2 : u2.getChosenSubCategories()) {
                if (subcat1.equals(subcat2)) {
                    subcatScore += 3;
                }
            }

        }
        matchScore = catScore + subcatScore;
        return matchScore;
    }

    public boolean getPreviouslyMatched() {
        return previouslyMatched;
    }

    public void setPreviouslyMatched(boolean previouslyMatched) {
        this.previouslyMatched = previouslyMatched;
    }
}
