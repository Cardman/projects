package code.expressionlanguage.exec;

import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.LgNames;

public final class CommonExecutionInfos {

    private final AbstractInterceptorStdCaller caller;
    private final int tabWidth;

    private final int stackOverFlow;
    private final LgNames standards;
    private final Classes classes;
    private final Coverage coverage;
    private final DefaultLockingClass locks;
    private final Initializer initializer;
    private final AbstractMethodCriteria defCriteria = new DefaultMethodCriteria();
    private final AbstractMethodCriteria staticCriteria = new StaticMethodCriteria();

    public CommonExecutionInfos(AbstractInterceptorStdCaller _caller, CommonExecutionMetricsInfos _metrics,
                                LgNames _standards, Classes _classes, Coverage _coverage,
                                DefaultLockingClass _locks, Initializer _initializer) {
        this.caller = _caller;
        this.tabWidth = _metrics.getTabWidth();
        this.stackOverFlow = _metrics.getStackOverFlow();
        this.standards = _standards;
        this.classes = _classes;
        this.coverage = _coverage;
        this.locks = _locks;
        this.initializer = _initializer;
    }

    public AbstractInterceptorStdCaller getCaller() {
        return caller;
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

    public AbstractMethodCriteria getDefCriteria() {
        return defCriteria;
    }

    public AbstractMethodCriteria getStaticCriteria() {
        return staticCriteria;
    }
}
