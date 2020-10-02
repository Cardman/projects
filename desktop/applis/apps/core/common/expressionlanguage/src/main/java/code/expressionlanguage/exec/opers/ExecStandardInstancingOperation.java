package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStandardInstancingOperation extends
        ExecInvokingOperation {

    private ExecInstancingCommonContent instancingCommonContent;
    private ExecInstancingStdContent instancingStdContent;

    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    public ExecStandardInstancingOperation(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        rootBlock = _rootBlock;
        ctor = _ctor;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        if (!instancingStdContent.getFieldName().isEmpty()) {
            off_ -= _conf.getLastPage().getTranslatedOffset();
            off_ -= instancingStdContent.getFieldName().length();
            off_ --;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl page_ = _conf.getLastPage();
        String className_ = page_.formatVarType(getClassName(), _conf);
        if (instancingStdContent.getFieldName().isEmpty()) {
            String base_ = StringExpUtil.getIdFromAllTypes(className_);
            if (_conf.getExiting().hasToExit(base_)) {
                return Argument.createVoid();
            }
        }
        CustList<Argument> firstArgs_ = getArgs(_nodes, className_);
        return instancePrepareFormat(_conf.getLastPage(),_conf, className_,rootBlock,getCtor(), _previous, firstArgs_, instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex());
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String className_) {
        return fectchInstFormattedArgs(_nodes,className_,rootBlock, instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg());
    }

    public String getClassName() {
        return instancingCommonContent.getClassName();
    }

    public ExecNamedFunctionBlock getCtor() {
        return ctor;
    }

    public int getNaturalVararg() {
        return instancingCommonContent.getNaturalVararg();
    }
}
