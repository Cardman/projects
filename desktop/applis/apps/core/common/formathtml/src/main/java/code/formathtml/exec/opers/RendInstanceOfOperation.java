package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendInstanceOfOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public RendInstanceOfOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<Struct> arguments_ = getArguments(_nodes,this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _rendStack);
        Struct objArg_ = ExecHelper.getFirstArgument(arguments_);
        Struct argres_;
        if (objArg_ == NullStruct.NULL_VALUE) {
            argres_ = BooleanStruct.of(false);
        } else {
            String str_ = _rendStack.formatVarType(typeCheckContent.getClassName());
            boolean res_ = ExecInherits.safeObject(str_, objArg_.getClassName(_context), _context) == ErrorType.NOTHING;
            argres_ = BooleanStruct.of(res_);
        }
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
