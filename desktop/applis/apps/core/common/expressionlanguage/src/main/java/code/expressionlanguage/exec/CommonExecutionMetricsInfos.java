package code.expressionlanguage.exec;

import code.maths.montecarlo.CustomSeedGene;

public final class CommonExecutionMetricsInfos {
    private final int tabWidth;

    private final int stackOverFlow;
    private final CustomSeedGene seed;

    public CommonExecutionMetricsInfos(int _tabWidth, int _stackOverFlow, CustomSeedGene _seed) {
        this.tabWidth = _tabWidth;
        this.stackOverFlow = _stackOverFlow;
        this.seed = _seed;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public int getStackOverFlow() {
        return stackOverFlow;
    }

    public CustomSeedGene getSeed() {
        return seed;
    }
}
