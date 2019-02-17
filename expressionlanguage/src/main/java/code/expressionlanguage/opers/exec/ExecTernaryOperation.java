package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractTernaryOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecTernaryOperation extends ExecReflectableOpering {

    private int offsetLocal;

    public ExecTernaryOperation(AbstractTernaryOperation _t) {
        super(_t);
        offsetLocal = _t.getOffsetLocal();
    }

    @Override
    public final void tryCalculateNode(Analyzable _conf) {
        quickCalculate(_conf);
    }
    @Override
    public final void quickCalculate(Analyzable _conf) {
        AbstractTernaryOperation.tryGetResult(_conf, this);
    }

    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    final Argument  getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _conf);
        Boolean obj_ = ((BooleanStruct) _arguments.first().getStruct()).getInstance();
        Argument arg_;
        if (obj_) {
            arg_ = _arguments.get(CustList.SECOND_INDEX);
        } else {
            arg_ = _arguments.last();
        }
        return arg_;
    }
}
