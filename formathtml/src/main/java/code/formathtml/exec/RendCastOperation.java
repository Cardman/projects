package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.CastOperation;
import code.util.CustList;

public final class RendCastOperation extends RendAbstractUnaryOperation {

    private String className;
    private int offset;
    public RendCastOperation(CastOperation _c) {
        super(_c);
        className = _c.getClassName();
        offset = _c.getOffset();
    }


    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (RendDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf);
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = _arguments.first();
        String paramName_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        Templates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
}
