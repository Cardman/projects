package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendCastOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public RendCastOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ typeCheckContent.getOffset(), _rendStack);
        Argument objArg_ = new Argument(ExecHelper.getFirstArgument(arguments_).getStruct());
        String paramName_ = typeCheckContent.getClassName();
        ExecCastOperation.wrapFct(paramName_,false, _context, objArg_);
        ExecTemplates.checkObject(paramName_, objArg_, _context, _rendStack.getStackCall());
        setSimpleArgument(objArg_, _nodes, _context, _rendStack);
    }

}
