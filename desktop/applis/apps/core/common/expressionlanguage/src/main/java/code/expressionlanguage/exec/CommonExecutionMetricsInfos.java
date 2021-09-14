package code.expressionlanguage.exec;

public final class CommonExecutionMetricsInfos {
    private final int tabWidth;

    private final int stackOverFlow;

    public CommonExecutionMetricsInfos(int _tabWidth, int _stackOverFlow) {
        this.tabWidth = _tabWidth;
        this.stackOverFlow = _stackOverFlow;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public int getStackOverFlow() {
        return stackOverFlow;
    }
}
