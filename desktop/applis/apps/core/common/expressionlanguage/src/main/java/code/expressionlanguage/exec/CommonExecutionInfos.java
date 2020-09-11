package code.expressionlanguage.exec;

import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.stds.LgNames;

public final class CommonExecutionInfos {

    private final int tabWidth;

    private final int stackOverFlow;
    private final LgNames standards;
    private final Classes classes;
    private final Coverage coverage;

    public CommonExecutionInfos(int tabWidth, int stackOverFlow, LgNames standards, Classes classes, Coverage coverage) {
        this.tabWidth = tabWidth;
        this.stackOverFlow = stackOverFlow;
        this.standards = standards;
        this.classes = classes;
        this.coverage = coverage;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public int getStackOverFlow() {
        return stackOverFlow;
    }

    public LgNames getStandards() {
        return standards;
    }

    public Classes getClasses() {
        return classes;
    }

    public Coverage getCoverage() {
        return coverage;
    }
}
