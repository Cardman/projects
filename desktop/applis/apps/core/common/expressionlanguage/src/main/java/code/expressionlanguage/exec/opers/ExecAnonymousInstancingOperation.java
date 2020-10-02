package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecAnonymousInstancingOperation extends
        ExecInvokingOperation {

    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    private ExecInstancingCommonContent instancingCommonContent;

    public ExecAnonymousInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
    }

    public void setExecAnonymousInstancingOperation(ExecInstancingCommonContent _instancingCommonContent, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
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
        int off_ = StringList.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl page_ = _conf.getLastPage();
        String className_ = page_.formatVarType(instancingCommonContent.getClassName(), _conf);
        String base_ = StringExpUtil.getIdFromAllTypes(className_);
        if (_conf.getExiting().hasToExit(base_)) {
            return Argument.createVoid();
        }
        CustList<Argument> firstArgs_ = getArgs(_nodes, className_);
        return instancePrepareFormat(_conf.getLastPage(),_conf, className_,rootBlock,ctor, _previous, firstArgs_, "", -1);
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String className_) {
        return fectchInstFormattedArgs(_nodes, className_, rootBlock, instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg());
    }

}
