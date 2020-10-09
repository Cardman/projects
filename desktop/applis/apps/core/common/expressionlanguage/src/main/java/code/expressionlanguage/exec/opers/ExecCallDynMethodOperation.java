package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecCallDynMethodOperation extends ExecInvokingOperation {

    private String fctName;
    public ExecCallDynMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _fctName) {
        super(_opCont, _intermediateDottedOperation);
        fctName = _fctName;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_= getPreviousArg(this, _nodes, _conf);
        if (StringUtil.quickEq(fctName, _conf.getStandards().getContent().getReflect().getAliasMetaInfo())) {
            Argument res_ = getMetaInfo(previous_, _conf);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        if (StringUtil.quickEq(fctName, _conf.getStandards().getContent().getReflect().getAliasInstance())) {
            Argument res_ = getInstanceCall(previous_, _conf);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = prepareCallDyn(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

}
