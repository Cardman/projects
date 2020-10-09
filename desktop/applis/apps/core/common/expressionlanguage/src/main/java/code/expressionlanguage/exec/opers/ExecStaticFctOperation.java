package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStaticFctOperation extends ExecInvokingOperation {

    private ExecStaticFctContent staticFctContent;

    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecStaticFctOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent) {
        super(_opCont, _intermediateDottedOperation);
        staticFctContent = _staticFctContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String classNameFound_ = ClassMethodId.formatType(staticFctContent.getClassName(),_conf, staticFctContent.getKind());
        CustList<Argument> firstArgs_ = getArgs(_nodes, classNameFound_);
        if (_conf.getExiting().hasToExit(classNameFound_)) {
            return Argument.createVoid();
        }
        Argument prev_ = new Argument();
        return callPrepare(_conf.getExiting(),_conf, classNameFound_,rootBlock, prev_, firstArgs_, null,getNamed(), staticFctContent.getKind(), "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String classNameFound_) {
        String lastType_ = ClassMethodId.formatType(rootBlock,classNameFound_, staticFctContent.getLastType(), staticFctContent.getKind());
        return fectchArgs(_nodes,lastType_, staticFctContent.getNaturalVararg());
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    public String getClassName() {
        return staticFctContent.getClassName();
    }


    public int getNaturalVararg() {
        return staticFctContent.getNaturalVararg();
    }

}
