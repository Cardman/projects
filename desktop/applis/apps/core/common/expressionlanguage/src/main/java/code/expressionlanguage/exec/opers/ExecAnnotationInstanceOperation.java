package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundAnnotation;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.StringUtil;

public final class ExecAnnotationInstanceOperation extends ExecInvokingOperation {

    private final ExecInstancingAnnotContent instancingAnnotContent;

    private final ExecRootBlock rootBlock;

    public ExecAnnotationInstanceOperation(
            ExecRootBlock _rootBlock, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingAnnotContent _instancingAnnotContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingAnnotContent = _instancingAnnotContent;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getArgument(CustList<Argument> _arguments,
                         ContextEl _conf, StackCall _stackCall) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stackCall);
        if (rootBlock == null) {
            int nbCh_ = chidren_.size();
            Ints dims_;
            dims_ = new Ints();
            dims_.add(nbCh_);
            String className_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(instancingAnnotContent.getClassName()));
            Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf);
            ExecTemplates.setCheckedElements(_arguments,str_,_conf, _stackCall);
            return new Argument(str_);
        }
        String base_ = StringExpUtil.getIdFromAllTypes(instancingAnnotContent.getClassName());
        if (_conf.getExiting().hasToExit(_stackCall, base_)) {
            return Argument.createVoid();
        }
        _stackCall.setCallingState(new CustomFoundAnnotation(instancingAnnotContent.getClassName(), rootBlock, instancingAnnotContent.getFieldNames(), _arguments));
        return Argument.createVoid();
    }

}
