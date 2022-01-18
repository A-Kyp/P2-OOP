package pojo;

import enums.Cities;
import pojo.database.AnnualChange;
import service.ChildService;
import sort.Sort;
import strategy.DistributionStrategy;

import java.util.ArrayList;

public class RoundPlayer {
    private final Round round;
    private final ChildService childService = ChildService.getInstance();

    public RoundPlayer(Round round1) {
        this.round  = round1;
    }

    public void initial(Double santaBudget, ArrayList<Child> kids, ArrayList<Gift> gifts) {
        round.eliminateYoungAdults(kids); // kick out young adults if there are any
        round.eliminateDuplicatePreferences(kids);
        childService.updateMassHistory(kids); // update niceScoreHistory for each kid
        round.calcAverageScore(kids);   // Calculate AverageScore for each kid
        round.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit
        round.calcAllocatedBudget(kids); //Calculated allocated budget for each kid
        round.elvesChangeBudget(kids); //->
        Sort.sortGift(gifts); //Sort gift list
        round.distributeGifts(kids, gifts); //distribute gifts to kids
    }

    public void normalRound(Double santaBudget, ArrayList<Child> kids, ArrayList<Gift> gifts,
                            AnnualChange change, ArrayList<Cities> cities,
                            DistributionStrategy strategy) {
        round.aYearHasPassed(kids); // everybody ages exactly 1 year :)
        round.eliminateYoungAdults(kids); // kick out young adults
        round.resetReceivedGifts(kids);
        round.roundHistoryUpdate(kids, change.getChildrenUpdates()); // update existing kids
        round.addNewChildren(kids, change.getNewChildren(), cities); // add new kids
        round.eliminateYoungAdults(kids); // kick out young adults
        round.eliminateDuplicatePreferences(kids);
        Sort.sortChildById(kids);
        round.calcAverageScore(kids);   // re-calculate AverageScore for each kid (P1)
        round.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit (P1)
        round.calcAllocatedBudget(kids); // re-calculated allocated budget for each kid (P1)
        round.elvesChangeBudget(kids); //->
        round.addNewGifts(gifts, change.getNewGifts()); // update gift list (P1)
        Sort.sortGift(gifts); // sort [updated] gift list (P1)
        strategy.arrange(kids); //->
        round.distributeGifts(kids, gifts); //distribute gifts to kids (P1)
        round.yellowElf(kids, gifts); //->
        Sort.sortChildById(kids);//->
    }
}
