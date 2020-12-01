package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecSuperFctOperation extends ExecSettableCallFctOperation {

    private ExecInstFctContent instFctContent;
    private ExecTypeFunction pair;
    public ExecSuperFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _execArr) {
        super(_opCont, _intermediateDottedOperation, _execArr);
        instFctContent = _instFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _conf);
        String classNameFound_ = getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, _previous.getStruct(), _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        Struct pr_ = prev_.getStruct();
        return callPrepare(_conf.getExiting(), _conf, classNameFound_, pair, prev_,null, getArgs(_nodes, _conf, pr_), null, MethodAccessKind.INSTANCE, "");
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr) {
        return fetchFormattedArgs(_nodes,_conf,_pr,getClassName(),pair.getType(), instFctContent.getLastType(), instFctContent.getNaturalVararg());
    }

    public String getClassName() {
        return instFctContent.getClassName();
    }


}
