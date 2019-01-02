package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.InstanceOfOperation;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInstanceOfOperation extends ExecAbstractUnaryOperation {

    private String className;
    private int offset;
    private boolean correctTemplate;
    public ExecInstanceOfOperation(InstanceOfOperation _i) {
        super(_i);
        className = _i.getClassName();
        offset = _i.getOffset();
        correctTemplate = _i.isCorrectTemplate();
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument( arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            arg_.setObject(false);
            return arg_;
        }
        String className_ = stds_.getStructClassName(objArg_.getStruct(), _conf.getContextEl());
        PageEl page_ = _conf.getOperationPageEl();
        String str_ = page_.formatVarType(className, _conf);
        if (!correctTemplate) {
            className_ = Templates.getIdFromAllTypes(className_);
            boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(false, str_, className_, _conf);
            Argument arg_ = new Argument();
            arg_.setObject(res_);
            return arg_;
        }
        boolean res_ = Templates.safeObject(str_, objArg_, _conf) == ErrorType.NOTHING;
        Argument arg_ = new Argument();
        arg_.setObject(res_);
        return arg_;
    }
}
