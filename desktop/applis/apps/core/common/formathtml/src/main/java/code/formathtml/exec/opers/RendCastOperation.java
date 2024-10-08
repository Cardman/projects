package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendCastOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public RendCastOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<Struct> arguments_ = getArguments(_nodes,this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _rendStack);
        Struct str_ = ExecHelper.getFirstArgument(arguments_);
        String paramName_ = _rendStack.formatVarType(typeCheckContent.getClassName());
        Struct objArg_ = ExecCastOperation.wrapFct(paramName_,false, _context, str_);
        Struct conv_ = ExecInheritsAdv.checkObject(paramName_, objArg_, _context, _rendStack.getStackCall());
        setSimpleArgument(conv_, _nodes, _context, _rendStack);
    }

}
