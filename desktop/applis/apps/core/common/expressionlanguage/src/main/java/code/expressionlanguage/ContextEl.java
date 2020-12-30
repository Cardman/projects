package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;

public abstract class ContextEl {

    private final CommonExecutionInfos executionInfos;
    private AbstractExiting exiting = new NoExiting();

    protected ContextEl(CommonExecutionInfos _executionInfos) {
        executionInfos = _executionInfos;
    }

    public GeneType getClassBody(String _type) {
        ExecRootBlock c_ = getExecutionInfos().getClasses().getClassBody(_type);
        if (c_ != null) {
            return c_;
        }
        return getExecutionInfos().getStandards().getStandards().getVal(_type);
    }
    public Initializer getInit() {
        return getExecutionInfos().getInitializer();
    }
    public int getStackOverFlow() {
        return getExecutionInfos().getStackOverFlow();
    }

    public int getTabWidth() {
        return getExecutionInfos().getTabWidth();
    }


    public AbstractMethodCriteria getDefCriteria() {
        return getExecutionInfos().getDefCriteria();
    }

    public AbstractMethodCriteria getStaticCriteria() {
        return getExecutionInfos().getStaticCriteria();
    }
    public Classes getClasses() {
        return getExecutionInfos().getClasses();
    }

    public Coverage getCoverage() {
        return getExecutionInfos().getCoverage();
    }

    public LgNames getStandards() {
        return getExecutionInfos().getStandards();
    }

    public DefaultLockingClass getLocks() {
        return getExecutionInfos().getLocks();
    }
    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        ForwardInfos.generalForward(_ana,_forwards, this);
        for (ClassMetaInfo c: _ana.getClassMetaInfos()) {
            getClasses().getClassMetaInfos().add(c);
        }
        _ana.getClassMetaInfos().clear();
        getClasses().setKeyWordValue(_ana.getKeyWords().getKeyWordValue());
        ExecClassesUtil.buildIterable(getClasses(), this);
    }

    public boolean callsOrException(StackCall _stack) {
        return _stack.callsOrException();
    }


    public AbstractExiting getExiting() {
        return exiting;
    }

    public void setExiting(AbstractExiting _exiting) {
        exiting = _exiting;
    }
}
