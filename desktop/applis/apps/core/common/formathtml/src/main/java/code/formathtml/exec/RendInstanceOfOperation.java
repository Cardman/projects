package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.InstanceOfOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendInstanceOfOperation extends RendAbstractUnaryOperation {

    private String className;
    private int offset;
    public RendInstanceOfOperation(InstanceOfOperation _i) {
        super(_i);
        className = _i.getClassName();
        offset = _i.getOffset();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf,_nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            arg_.setStruct(BooleanStruct.of(false));
            return arg_;
        }
        PageEl page_ = _conf.getPageEl();
        String str_ = page_.formatVarType(className, _conf.getContext());
        boolean res_ = ExecTemplates.safeObject(str_, objArg_, _conf.getContext()) == ErrorType.NOTHING;
        Argument arg_ = new Argument();
        arg_.setStruct(BooleanStruct.of(res_));
        return arg_;
    }
}
