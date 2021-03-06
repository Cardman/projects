package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendInstanceOfOperation extends RendAbstractUnaryOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public RendInstanceOfOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ typeCheckContent.getOffset(), _rendStack);
        Argument objArg_ = ExecHelper.getFirstArgument(arguments_);
        Argument argres_;
        if (objArg_.isNull()) {
            argres_ = new Argument(BooleanStruct.of(false));
        } else {
            String str_ = typeCheckContent.getClassName();
            boolean res_ = ExecTemplates.safeObject(str_, objArg_.getStruct(), _context) == ErrorType.NOTHING;
            argres_ = new Argument(BooleanStruct.of(res_));
        }
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

}
