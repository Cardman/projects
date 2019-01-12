package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.InstanceOfOperation;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;

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
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf);
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
            boolean res_ = PrimitiveTypeUtil.canBeUseAsArgument(str_, className_, _conf);
            Argument arg_ = new Argument();
            arg_.setObject(res_);
            return arg_;
        }
        boolean res_ = Templates.isCorrectExecute(className_, str_, _conf);
        Argument arg_ = new Argument();
        arg_.setObject(res_);
        return arg_;
    }
}
