package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AbstractTernaryOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;

public final class ExecTernaryOperation extends ExecReflectableOpering {

    private int offsetLocal;

    public ExecTernaryOperation(AbstractTernaryOperation _t) {
        super(_t);
        offsetLocal = _t.getOffsetLocal();
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (getFirstChild().getArgument() == null) {
            return;
        }
        quickCalculate(_conf);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (arguments_.first().isNull()) {
            return;
        }
        Boolean obj_ = ((BooleanStruct) arguments_.first().getStruct()).getInstance();
        Argument arg_;
        if (obj_) {
            arg_ = arguments_.get(CustList.SECOND_INDEX);
        } else {
            arg_ = arguments_.last();
        }
        if (arg_ == null) {
            return;
        }
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf);
    }

    Argument  getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
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
