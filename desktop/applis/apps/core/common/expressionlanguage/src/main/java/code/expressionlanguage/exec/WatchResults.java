package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.dbg.BreakPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;

public final class WatchResults {
    private Struct watchedObject;
    private Struct watchedTrace;
    private ContextEl subContext;
    private ReportedMessages reportedMessages = new ReportedMessages();

    public static WatchResults dynamicAnalyze(String _dyn, StackCall _stack, ResultContext _res, AbsLightContextGenerator _gene) {
        BreakPointOutputInfo output_ = _stack.getBreakPointInfo().getBreakPointOutputInfo();
        BreakPointCondition bpc_ = output_.getBpc();
        if (bpc_ == null) {
            return new WatchResults();
        }
        ResultContextLambda resCtxLambda_ = bpc_.getSuperKeyPoint().analyze(_dyn, _res, _res.getPageEl().getAliasObject(), _gene, bpc_.getPhasePoint());
        CoreCheckedExecOperationNodeInfos opElt_ = output_.getOperElt();
        if (opElt_ == null) {
            return afterAnalyze(resCtxLambda_,_res.getContext(),null,_stack.getLastPage());
        }
        return afterAnalyze(resCtxLambda_,_res.getContext(),opElt_,null);
    }

    public static WatchResults dynamicAnalyze(String _dyn, ResultContext _res, AbsLightContextGenerator _gene, AbstractPageEl _last) {
        AbstractInterceptorStdCaller inter_ = _res.getContext().getCaller();
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_last.getFile(), FileBlock.number(_res.getPageEl().getPreviousFilesBodies().getVal(ExecFileBlock.name(_last.getFile()))), _last.getTraceIndex(), inter_, true);
        int phase_;
        if (_last instanceof AbstractCallingInstancingPageEl) {
            phase_ = BreakPoint.BPC_INSTANCE;
        } else if (_last instanceof StaticInitPageEl) {
            phase_ = BreakPoint.BPC_STATIC;
        } else {
            phase_ = BreakPoint.BPC_STD;
        }
        ResultContextLambda resCtxLambda_ = ResultContextLambda.dynamicAnalyze(_dyn, pair_, _res, _res.getPageEl().getAliasObject(), _gene, phase_);
        return afterAnalyze(resCtxLambda_,_res.getContext(),null,_last);
    }
    private static WatchResults afterAnalyze(ResultContextLambda _reCtx, ContextEl _original, CoreCheckedExecOperationNodeInfos _addon, AbstractPageEl _page) {
        ContextEl sub_ = _reCtx.getContext();
        if (sub_ == null) {
            WatchResults wr_ = new WatchResults();
            wr_.setReportedMessages(_reCtx.getReportedMessages());
            return wr_;
        }
        WatchResults wr_ = new WatchResults();
        wr_.setSubContext(sub_);
        StackCallReturnValue st_ = _reCtx.eval(_original, _addon, _page);
        if (st_.getStack().trueException() != null) {
            wr_.setWatchedTrace(st_.getStack().getStackView());
        } else {
            wr_.setWatchedObject(ArgumentListCall.toStr(st_.getStack().aw().getValue()));
        }
        return wr_;
    }
    public Struct getWatchedObject() {
        return watchedObject;
    }

    public void setWatchedObject(Struct _w) {
        this.watchedObject = _w;
    }

    public Struct getWatchedTrace() {
        return watchedTrace;
    }

    public void setWatchedTrace(Struct _w) {
        this.watchedTrace = _w;
    }

    public ContextEl getSubContext() {
        return subContext;
    }

    public void setSubContext(ContextEl _c) {
        this.subContext = _c;
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }

    public void setReportedMessages(ReportedMessages _r) {
        this.reportedMessages = _r;
    }
}
