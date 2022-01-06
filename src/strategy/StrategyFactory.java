package strategy;

import enums.CityStrategyEnum;

public class StrategyFactory {
    public static StrategyFactory instance = null;

    private StrategyFactory() { }

    public static StrategyFactory getInstance()
    {
        if (instance == null) {
            instance = new StrategyFactory();
        }
        return instance;
    }
    public static DistributionStrategy createStrategy(CityStrategyEnum strategy) {
        return switch (strategy) {
            case NICE_SCORE_CITY -> new CityStrategy();
            case NICE_SCORE -> new NiceScoreStrategy();
            case ID -> new IdStrategy();
        };
    }
}
