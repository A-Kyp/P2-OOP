package strategy;

import enums.CityStrategyEnum;

public final class StrategyFactory {
    private StrategyFactory() { }

    /**
     * @param strategy the current round's strategy
     * @return the corresponding distribution strategy instance
     */
    public static DistributionStrategy createStrategy(final CityStrategyEnum strategy) {
        return switch (strategy) {
            case NICE_SCORE_CITY -> new CityStrategy();
            case NICE_SCORE -> new NiceScoreStrategy();
            case ID -> new IdStrategy();
        };
    }
}
