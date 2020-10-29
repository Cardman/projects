package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInterfaceFctConstructor extends ExecAbstractInvokingConstructor {
    private String className;
    public ExecInterfaceFctConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor, String _className) {
        super(_opCont, _intermediateDottedOperation, _invokingConstructorContent, _rootBlock, _ctor);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        ExecOperationNode main_ = ExecTemplates.getMainNode(this);
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, main_);
        Argument mainArgument_ = Argument.getNullableValue(pair_.getArgument());
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(mainArgument_.getStruct());
            if (!ExecTemplates.checkObject(_conf.getStandards().getContent().getReflect().getAliasFct(), lda_, _conf)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            String form_ = _conf.formatVarType(className);
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _conf, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _conf)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            pair_.setArgument(ref_);
            Argument res_ = getArgument(_nodes,ref_, _conf);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        Argument res_ = getArgument(_nodes, mainArgument_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes,Argument _argument, ContextEl _conf) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _conf);
        String superClass_ = _conf.formatVarType(getClassFromName());
        CustList<Argument> firstArgs_ = getArgs(_nodes, superClass_);
        checkParametersCtors(_conf, superClass_, getRootBlock(),getCtor(), _argument, firstArgs_, InstancingStep.USING_SUPER);
        return Argument.createVoid();
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _superClass) {
        String lastType_ = ExecTemplates.quickFormat(getRootBlock(),_superClass, getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }
}
