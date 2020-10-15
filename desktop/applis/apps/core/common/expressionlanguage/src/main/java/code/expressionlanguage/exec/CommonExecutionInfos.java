package code.expressionlanguage.exec;

import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.stds.LgNames;

public final class CommonExecutionInfos {

    private final int tabWidth;

    private final int stackOverFlow;
    private final LgNames standards;
    private final Classes classes;
    private final Coverage coverage;
    private final DefaultLockingClass locks;
    private final Initializer initializer;

    public CommonExecutionInfos(int _tabWidth, int _stackOverFlow,
                                LgNames _standards, Classes _classes, Coverage _coverage,
                                DefaultLockingClass _locks, Initializer _initializer) {
        this.tabWidth = _tabWidth;
        this.stackOverFlow = _stackOverFlow;
        this.standards = _standards;
        this.classes = _classes;
        this.coverage = _coverage;
        this.locks = _locks;
        this.initializer = _initializer;
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

    public DefaultLockingClass getLocks() {
        return locks;
    }

    public Initializer getInitializer() {
        return initializer;
    }
}
