package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;

public final class ExecArrayElementOperation extends
        ExecAbstractArrayInstancingOperation {

    public ExecArrayElementOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_opCont, _intermediateDottedOperation, _arrayInstancingContent);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        int off_ = getMethodName();
        setRelOffsetPossibleLastPage(off_, _stack);
        String cl_ = getClassName();
        String className_ = _stack.formatVarType(cl_);

        int nbCh_ = arguments_.size();

        Ints dims_ = new Ints(nbCh_);
        Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf);
        ExecTemplates.setCheckedElements(arguments_,str_, _conf, _stack);
        Argument res_ = new Argument(str_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
