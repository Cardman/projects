package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAbstractInstancingOperation extends
        ExecSettableCallFctOperation {

    private final boolean initBefore;
    private final ExecInstancingCustContent instancingCommonContent;
    private final ExecFormattedRootBlock formattedType;

    private final ExecInstancingStdContent instancingStdContent;

    public ExecAbstractInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation,
                                              boolean _initBefore, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        initBefore = _initBefore;
        instancingCommonContent = _instancingCommonContent;
        formattedType = _instancingCommonContent.getFormattedType();
        instancingStdContent = _instancingStdContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        int off_ = StringUtil.getFirstPrintableCharIndex(getInstancingCommonContent().getMethodName());
        ExecFormattedRootBlock className_ = StackCall.formatVarType(_stack,getFormattedType());
        if (instancingStdContent == null) {
            setRelOffsetPossibleLastPage(off_, _stack);
            Struct res_ = ParamCheckerUtil.prep(_conf,_stack,previous_,className_,buildInfos(_nodes),getInstancingCommonContent());
            setResult(res_, _conf, _nodes, _stack);
            return;
        }
        if (instancingStdContent.getFieldName().isEmpty()) {
            setRelOffsetPossibleLastPage(off_, _stack);
        } else {
            off_ -= _stack.getLastPage().getTranslatedOffset();
            off_ -= instancingStdContent.getFieldName().length();
            off_--;
            setRelOffsetPossibleLastPage(off_, _stack);
        }
        Struct res_ = null;
        if (instancingStdContent.getFieldName().isEmpty()) {
            res_ = ParamCheckerUtil.tryInit(_conf,_stack, className_.getRootBlock());
        }
        if (res_ == null) {
            CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
            Struct resNext_ = ParamCheckerUtil.prep(_conf, _stack, previous_, className_, infos_, getInstancingCommonContent(), instancingStdContent);
            setResult(resNext_, _conf, _nodes, _stack);
        } else {
            setResult(res_, _conf, _nodes, _stack);
        }
    }
    public boolean isInitBefore() {
        return initBefore;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public ExecInstancingCustContent getInstancingCommonContent() {
        return instancingCommonContent;
    }
}
