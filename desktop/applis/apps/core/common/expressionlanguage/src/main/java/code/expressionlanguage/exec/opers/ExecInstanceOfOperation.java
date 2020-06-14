package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.InstanceOfOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInstanceOfOperation extends ExecAbstractUnaryOperation {

    private String className;
    private int offset;
    public ExecInstanceOfOperation(InstanceOfOperation _i) {
        super(_i);
        className = _i.getClassName();
        offset = _i.getOffset();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument( arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            arg_.setStruct(BooleanStruct.of(false));
            return arg_;
        }
        PageEl page_ = _conf.getLastPage();
        String str_ = page_.formatVarType(className, _conf);
        boolean res_ = Templates.safeObject(str_, objArg_, _conf) == ErrorType.NOTHING;
        Argument arg_ = new Argument();
        arg_.setStruct(BooleanStruct.of(res_));
        return arg_;
    }
}
