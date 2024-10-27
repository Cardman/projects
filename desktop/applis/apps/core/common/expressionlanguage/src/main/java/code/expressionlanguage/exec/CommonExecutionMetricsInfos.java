package code.expressionlanguage.exec;

import code.maths.montecarlo.AbsDoubleToStrConverter;
import code.maths.montecarlo.CustomSeedGene;

public final class CommonExecutionMetricsInfos {
    private final int tabWidth;

    private final int stackOverFlow;
    private final CustomSeedGene seed;
    private final AbsDoubleToStrConverter dbConverter;

    public CommonExecutionMetricsInfos(int _tabWidth, int _stackOverFlow, CustomSeedGene _seed, AbsDoubleToStrConverter _conv) {
        this.tabWidth = _tabWidth;
        this.stackOverFlow = _stackOverFlow;
        this.seed = _seed;
        this.dbConverter = _conv;
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

    public AbsDoubleToStrConverter getDbConverter() {
        return dbConverter;
    }
}
