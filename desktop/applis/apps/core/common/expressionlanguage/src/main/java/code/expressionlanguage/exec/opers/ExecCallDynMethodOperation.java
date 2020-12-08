package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecCallDynMethodOperation extends ExecSettableCallFctOperation {

    private String fctName;
    public ExecCallDynMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _fctName, ExecArrContent _execArr) {
        super(_opCont, _intermediateDottedOperation, _execArr);
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
        Argument res_ = prepareCallDynNormal(previous_, fectchPosArgs(_nodes), _conf);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(res_, _conf, _nodes);
            return;
        }
        setSimpleArgument(res_, _conf, _nodes);
    }

    private CustList<ArgumentsPair> fectchPosArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        CustList<ArgumentsPair> out_ = new CustList<ArgumentsPair>();
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        for (ExecOperationNode o: chidren_) {
            ArgumentsPair a_ = new ArgumentsPair();
            if (o instanceof ExecWrappOperation) {
                a_.setWrapper(ExecTemplates.getArgumentPair(_nodes,o).getWrapper());
            } else {
                a_.setArgument(ExecTemplates.getArgumentPair(_nodes,o).getArgument());
            }
            out_.add(a_);
        }
        return out_;
    }
}
