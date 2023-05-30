package code.expressionlanguage.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.sample.CustLgNames;
import code.util.StringMap;

public abstract class ProcessDbgCommon extends ProcessMethodCommon {
    protected static StackCall dbgNormal(String _class, MethodId _method, ResultContext _cont) {
        ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont.getContext());
        ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont.getContext(), stackCall_);
        return stackCall_;
    }

    protected static StackCall dbgContinueNormal(StackCall _stack, ContextEl _cont) {
        _cont.getInit().loopCalling(_cont, _stack);
        return _stack;
    }
    protected static ResultContext ctxLgReadOnlyOkQuick(String _lg, StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setDebugging(false);
        opt_.setDebugging(true);
        addTypesInit(opt_, _types);
        CustLgNames lgName_ = getLgNames();
        assertEq("en",_lg);
        KeyWords kwl_ = en(lgName_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(opt_,lgName_,kwl_,page_);
        AnalyzedPageEl a_ = validateWithoutInit(_files, page_);
        assertTrue( isEmptyErrors(a_));
        generalForward(a_, forwards_);
        ContextEl ctx_ = forwardAndClear(forwards_);
        ResultContext res_ = new ResultContext(a_, forwards_, a_.getMessages());
        res_.setContext(ctx_);
        return res_;
    }
}
