package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;

public final class RendArrayElementOperation extends
        RendAbstractArrayInstancingOperation {

    public RendArrayElementOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_content, _intermediateDottedOperation, _arrayInstancingContent);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        int off_ = getMethodName();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        String className_ = _rendStack.formatVarType(getClassName());

        int nbCh_ = arguments_.size();

        Ints dims_ = new Ints();
        dims_.add(nbCh_);
        Struct str_ = ExecArrayTemplates.newCustomArray(className_, dims_, _context);
        ExecArrayTemplates.setCheckedElements(arguments_,str_, _context, _rendStack.getStackCall());
        Argument res_ = new Argument(str_);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }

}
