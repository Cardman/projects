package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnonymousInstancingOperation extends
        ExecInvokingOperation {

    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock ctor;
    private final ExecInstancingCommonContent instancingCommonContent;

    public ExecAnonymousInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
        super(_opCont, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
        rootBlock = _rootBlock;
        ctor = _ctor;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = _conf.formatVarType(instancingCommonContent.getClassName());
        String base_ = StringExpUtil.getIdFromAllTypes(className_);
        if (_conf.getExiting().hasToExit(base_)) {
            return Argument.createVoid();
        }
        CustList<Argument> firstArgs_ = getArgs(_nodes, className_);
        return instancePrepareFormatted(_conf, className_,rootBlock,ctor, _previous, firstArgs_, "", -1);
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String className_) {
        return fectchInstFormattedArgs(_nodes, className_, rootBlock, instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg());
    }

}
