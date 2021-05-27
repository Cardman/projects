package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import code.util.core.StringUtil;

public final class ExecAnnotationInstanceArrOperation extends ExecInvokingOperation {

    private final String className;

    public ExecAnnotationInstanceArrOperation(
            ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _className) {
        super(_opCont, _intermediateDottedOperation);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelOffsetPossibleLastPage(0, _stack);
        Argument res_;
        int nbCh_ = chidren_.size();
        Ints dims_;
        dims_ = new Ints();
        dims_.add(nbCh_);
        String className_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(className));
        Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf);
        ExecTemplates.setCheckedElements(arguments_,str_, _conf, _stack);
        res_ = new Argument(str_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
