package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStandardInstancingOperation extends
        ExecAbstractInstancingOperation {

    private ExecInstancingStdContent instancingStdContent;

    private ExecRootBlock rootBlock;
    public ExecStandardInstancingOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, boolean _initBefore, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_opCont, _intermediateDottedOperation, _initBefore, _pair,_instancingCommonContent);
        instancingStdContent = _instancingStdContent;
        rootBlock = _pair.getType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                         ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(getInstancingCommonContent().getMethodName());
        if (!instancingStdContent.getFieldName().isEmpty()) {
            off_ -= _conf.getLastPage().getTranslatedOffset();
            off_ -= instancingStdContent.getFieldName().length();
            off_--;
        }
        setRelOffsetPossibleLastPage(off_, _conf);
        String className_ = _conf.formatVarType(getClassName());
        if (instancingStdContent.getFieldName().isEmpty()) {
            String base_ = StringExpUtil.getIdFromAllTypes(className_);
            if (_conf.getExiting().hasToExit(base_)) {
                return Argument.createVoid();
            }
        }
        CustList<Argument> firstArgs_ = getArgs(_nodes, className_);
        return instancePrepareCust(_conf, className_, getPair(), _previous, firstArgs_, instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex());
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _className) {
        return fectchInstFormattedArgs(_nodes,_className,rootBlock, getInstancingCommonContent().getLastType(), getInstancingCommonContent().getNaturalVararg());
    }

    public String getClassName() {
        return getInstancingCommonContent().getClassName();
    }

}
