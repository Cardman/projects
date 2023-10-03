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
        return dynamicAnalyze(_dyn,_stack,_res,_gene,_res.getContext().getClasses().getDebugMapping().getStopper());
    }
    public static WatchResults dynamicAnalyze(String _dyn, StackCall _stack, ResultContext _res, AbsLightContextGenerator _gene, AbsStackStopper _logger) {
        BreakPointOutputInfo output_ = _stack.getBreakPointInfo().getBreakPointOutputInfo();
        BreakPointCondition bpc_ = output_.getBpc();
        if (bpc_ == null) {
            AbstractPageEl exit_ = _stack.getBreakPointInfo().getBreakPointMiddleInfo().getExiting();
            if (exit_ != null) {
                return dynamicAnalyze(_dyn,_res,_gene, exit_,_logger);
            }
            return dynamicAnalyze(_dyn,_res,_gene,_stack.getLastPage(),_logger);
        }
        ResultContextLambda resCtxLambda_ = bpc_.getSuperKeyPoint().analyze(_dyn, _res, _res.getPageEl().getAliasObject(), _gene, bpc_.getPhasePoint());
        CheckedMethodInfos opElt_ = output_.getOperElt();
        if (opElt_ == null) {
            return afterAnalyze(resCtxLambda_, null,_stack.getLastPage(),_logger);
        }
        return afterAnalyze(resCtxLambda_, opElt_,null,_logger);
    }

    public static WatchResults dynamicAnalyze(String _dyn, ResultContext _res, AbsLightContextGenerator _gene, AbstractPageEl _last) {
        return dynamicAnalyze(_dyn, _res, _gene, _last, _res.getContext().getClasses().getDebugMapping().getStopper());
    }
    public static WatchResults dynamicAnalyze(String _dyn, ResultContext _res, AbsLightContextGenerator _gene, AbstractPageEl _last, AbsStackStopper _logger) {
        AbstractInterceptorStdCaller inter_ = _res.getContext().getCaller();
        ExecFileBlock ex_ = _last.getFile();
        FileBlock bl_ = _res.getRevFiles().getVal(ex_);
        if (bl_ == null) {
            return new WatchResults();
        }
        int tr_ = _last.getTraceIndex();
        int phase_;
        if (_last instanceof AbstractCallingInstancingPageEl) {
            phase_ = BreakPoint.BPC_INSTANCE;
        } else if (_last instanceof StaticInitPageEl) {
            phase_ = BreakPoint.BPC_STATIC;
        } else {
            phase_ = BreakPoint.BPC_STD;
        }
        BreakPointBlockPair pair_ = new BreakPointBlockPair(ex_, FileBlock.number(bl_), tr_, inter_, true, phase_ != BreakPoint.BPC_STD);
        ResultContextLambda resCtxLambda_ = ResultContextLambda.dynamicAnalyze(_dyn, pair_, _res, _res.getPageEl().getAliasObject(), _gene, phase_);
        return afterAnalyze(resCtxLambda_, null,_last,_logger);
    }
    private static WatchResults afterAnalyze(ResultContextLambda _reCtx, CheckedMethodInfos _addon, AbstractPageEl _page, AbsStackStopper _logger) {
        ContextEl sub_ = _reCtx.getContext();
        if (sub_ == null) {
            WatchResults wr_ = new WatchResults();
            wr_.setReportedMessages(_reCtx.getReportedMessages());
            return wr_;
        }
        WatchResults wr_ = new WatchResults();
        wr_.setSubContext(sub_);
        StackCallReturnValue st_ = _reCtx.eval(_logger,_addon, _page);
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
