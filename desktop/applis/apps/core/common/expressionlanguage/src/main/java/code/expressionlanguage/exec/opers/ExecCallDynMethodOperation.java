package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCallDynMethodOperation extends ExecSettableCallFctOperation implements StdParamsOperable {

    private final StandardNamedFunction stdMethod;
    public ExecCallDynMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrContent _execArr, StandardNamedFunction _std) {
        super(_opCont, _intermediateDottedOperation, _execArr);
        stdMethod = _std;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_= getPreviousArg(this, _nodes, _stack.getLastPage());
        if (stdMethod == _conf.getStandards().getContent().getReflect().getFctTypeMeta()) {
            Argument res_ = getMetaInfo(previous_, _conf, _stack);
            setCheckedResult(res_, _conf, _nodes, _stack);
            return;
        }
        if (stdMethod == _conf.getStandards().getContent().getReflect().getFctTypeInstance()) {
            Argument res_ = getInstanceCall(previous_, _conf, _stack);
            setCheckedResult(res_, _conf, _nodes, _stack);
            return;
        }
        ArgumentList argumentList_ = list(_nodes);
        prepareCallDynNormal(previous_, argumentList_.getArguments(), _conf, _stack);
        setCheckedResult(ArgumentListCall.toStr(NullStruct.NULL_VALUE), _conf, _nodes, _stack);
    }

    @Override
    public Struct instance(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _stack) {
        return ArgumentListCall.toStr(getPreviousArg(this, _nodes, _stack));
    }
    @Override
    public StandardNamedFunction fct() {
        return stdMethod;
    }

    @Override
    public ArgumentList args(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _alias) {
        if (stdMethod.getImportedParametersTypes().isEmpty()) {
            return new ArgumentList();
        }
        ArgumentList ls_ = list(_nodes);
        int len_ = ls_.getArguments().getArgumentWrappers().size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(_alias));
        for (int i = 0; i < len_; i++) {
            arr_.set(i, ArgumentListCall.toStr(ls_.getArguments().getArgumentWrappers().get(i).getValue()));
        }
        ArgumentList merge_ = new ArgumentList();
        merge_.getArguments().getArgumentWrappers().add(new ArgumentWrapper(arr_));
        return merge_;
    }

    public ArgumentList list(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode last_ = getLast(chidren_);
        if (last_ instanceof ExecNamedArgumentOperation) {
            chidren_ = ((ExecNamedArgumentOperation)last_).getChildrenNodes();
            last_ = getLast(chidren_);
        }
        if (last_ instanceof ExecArgumentListInstancing) {
            chidren_ = ((ExecArgumentListInstancing)last_).getChildrenNodes();
        }
        return listNamedArguments(buildInfos(_nodes, chidren_));
    }
    private static ExecOperationNode getLast(CustList<ExecOperationNode> _list) {
        if (_list.isEmpty()) {
            return null;
        }
        return _list.last();
    }
}
