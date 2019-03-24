package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.InterfaceInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public final class ExecInterfaceInvokingConstructor extends ExecAbstractInvokingConstructor {

    protected ExecInterfaceInvokingConstructor(InterfaceInvokingConstructor _int) {
        super(_int);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        Argument arg_ = _conf.getOperationPageEl().getGlobalArgument();
        String clCurName_ = arg_.getObjectClassName(_conf.getContextEl());
        CustList<Argument> firstArgs_;
        String cl_ = getConstId().getName();
        cl_ = Templates.getIdFromAllTypes(cl_);
        String superClass_ = Templates.getFullTypeByBases(clCurName_, cl_, _conf);
        String lastType_ = getLastType();
        lastType_ = Templates.quickFormat(superClass_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        checkParameters(_conf, superClass_, ctorId_, null, firstArgs_, 0,true,false,InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
    }

}
