package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecCallDynMethodOperation extends ExecSettableCallFctOperation {

    private final String fctName;
    public ExecCallDynMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _fctName, ExecArrContent _execArr) {
        super(_opCont, _intermediateDottedOperation, _execArr);
        fctName = _fctName;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_= getPreviousArg(this, _nodes, _stack);
        if (StringUtil.quickEq(fctName, _conf.getStandards().getContent().getReflect().getAliasMetaInfo())) {
            Argument res_ = getMetaInfo(previous_, _conf, _stack);
            setSimpleArgument(res_, _conf, _nodes, _stack);
            return;
        }
        if (StringUtil.quickEq(fctName, _conf.getStandards().getContent().getReflect().getAliasInstance())) {
            Argument res_ = getInstanceCall(previous_, _conf, _stack);
            setSimpleArgument(res_, _conf, _nodes, _stack);
            return;
        }
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode last_ = getLast(chidren_);
        if (last_ instanceof ExecNamedArgumentOperation) {
            chidren_ = ((ExecNamedArgumentOperation)last_).getChildrenNodes();
            last_ = getLast(chidren_);
        }
        if (last_ instanceof ExecArgumentListInstancing) {
            chidren_ = ((ExecArgumentListInstancing)last_).getChildrenNodes();
        }
        ArgumentList argumentList_ = listNamedArguments(buildInfos(_nodes, chidren_));
        Argument res_ = prepareCallDynNormal(previous_, argumentList_.getArguments().getArgumentWrappers(), _conf, _stack);
        setResult(res_, _conf, _nodes, _stack);
    }

    private static ExecOperationNode getLast(CustList<ExecOperationNode> _list) {
        if (_list.isEmpty()) {
            return null;
        }
        return _list.last();
    }
}
