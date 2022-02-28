package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendStrictCastOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public RendStrictCastOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _rendStack);
        Struct str_ = ExecHelper.getFirstArgument(arguments_).getStruct();
        String paramName_ = _rendStack.formatVarType(typeCheckContent.getClassName());
        ExecTemplates.checkQuick(paramName_,str_.getClassName(_context),_context,_rendStack.getStackCall());
        setSimpleArgument(new Argument(str_), _nodes, _context, _rendStack);
    }

}
