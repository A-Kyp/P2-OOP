package pojo;

import enums.Cities;
import pojo.database.AnnualChange;
import service.ChildService;
import sort.Sort;
import strategy.DistributionStrategy;

import java.util.ArrayList;

public final class RoundPlayer {
    private final Round round;
    private final ChildService childService = ChildService.getInstance();

    public RoundPlayer(final Round round1) {
        this.round  = round1;
    }

    /**
     * execute specific operation for initial round
     * <p>A particular case of the normalRound, but with fewer steps</p>
     * @param santaBudget current round's santaBudget
     * @param kids current round's children list
     * @param gifts current round's gift list
     */
    public void initial(final Double santaBudget, final ArrayList<Child> kids,
                        final ArrayList<Gift> gifts) {
        round.eliminateYoungAdults(kids); // kick out young adults if there are any
        round.eliminateDuplicatePreferences(kids);
        childService.updateMassHistory(kids); // update niceScoreHistory for each kid
        round.calcAverageScore(kids);   // Calculate AverageScore for each kid
        round.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit
        round.calcAllocatedBudget(kids); //Calculated allocated budget for each kid
        round.elvesChangeBudget(kids); //-> PINK & BLACK elves do their magic
        Sort.sortGift(gifts); //Sort gift list
        round.distributeGifts(kids, gifts); //distribute gifts to kids
        round.yellowElf(kids, gifts); //-> YELLOW elf do your magic
    }

    /**
     * execute specific operation for a normal round
     * @param santaBudget current round's santaBudget
     * @param kids current round's children list
     * @param gifts current round's gift list
     */
    public void normalRound(final Double santaBudget, final ArrayList<Child> kids,
                            final ArrayList<Gift> gifts,
                            final AnnualChange change, final ArrayList<Cities> cities,
                            final DistributionStrategy strategy) {

        round.aYearHasPassed(kids); // everybody ages exactly 1 year :)
        round.eliminateYoungAdults(kids); // kick out young adults
        round.resetReceivedGifts(kids);
        round.roundHistoryUpdate(kids, change.getChildrenUpdates()); // update existing kids
        round.addNewChildren(kids, change.getNewChildren(), cities); // add new kids
        round.eliminateYoungAdults(kids); // kick out young adults
        round.eliminateDuplicatePreferences(kids);
        Sort.sortChildById(kids); //->make sure the kids are ordered by id before calculating budget
        round.calcAverageScore(kids);   // re-calculate AverageScore for each kid (P1)
        round.calcBudgetUnit(santaBudget, kids); // Calculate budgetUnit (P1)
        round.calcAllocatedBudget(kids); // re-calculated allocated budget for each kid (P1)
        round.elvesChangeBudget(kids); //-> PINK & BLACK elves do their magic
        round.addNewGifts(gifts, change.getNewGifts()); // update gift list (P1)
        Sort.sortGift(gifts); // sort [updated] gift list (P1)
        strategy.arrange(kids); //-> order kids based on the current round's strategy
        round.distributeGifts(kids, gifts); //distribute gifts to kids (P1)
        round.yellowElf(kids, gifts); //-> YELLOW elf do your magic
        Sort.sortChildById(kids); //-> re-order the children by ID for writing the result
    }
}
