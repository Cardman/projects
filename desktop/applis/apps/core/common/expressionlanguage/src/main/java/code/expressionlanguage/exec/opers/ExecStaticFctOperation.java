package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStaticFctOperation extends ExecSettableCallFctOperation {

    private ExecStaticFctContent staticFctContent;

    private ExecTypeFunction pair;
    public ExecStaticFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(res_, _conf, _nodes);
            return;
        }
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _conf);
        String classNameFound_ = ClassMethodId.formatType(staticFctContent.getClassName(),_conf, staticFctContent.getKind());
        if (_conf.getExiting().hasToExit(classNameFound_)) {
            return Argument.createVoid();
        }
        Argument prev_ = new Argument();
        return callPrepare(_conf.getExiting(), _conf, classNameFound_, pair, prev_,null, getArgs(_nodes, classNameFound_), null, staticFctContent.getKind(), "");
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _classNameFound) {
        String lastType_ = ClassMethodId.formatType(pair.getType(),_classNameFound, staticFctContent.getLastType(), staticFctContent.getKind());
        return fectchArgs(_nodes,lastType_, staticFctContent.getNaturalVararg());
    }


}
