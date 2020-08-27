package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.InterfaceInvokingConstructor;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;

public final class ExecInterfaceInvokingConstructor extends ExecAbstractInvokingConstructor {

    protected ExecInterfaceInvokingConstructor(InterfaceInvokingConstructor _int, ContextEl _context) {
        super(_int,_context);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        CustList<Argument> firstArgs_;
        String superClass_ = _conf.getLastPage().formatVarType(getClassFromName(),_conf);
        String lastType_ = getLastType();
        lastType_ = ExecTemplates.quickFormat(getRootBlock(),superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments);
        checkParametersCtors(_conf, superClass_, getRootBlock(),getCtor(),  arg_, firstArgs_, InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
    }

}
