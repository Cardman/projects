package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInterfaceFctConstructor extends ExecAbstractInvokingConstructor {
    private final String className;
    public ExecInterfaceFctConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation, _invokingConstructorContent, _pair);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        int off_ = getInvokingConstructorContent().getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecOperationNode main_ = ExecHelper.getMainNode(this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, main_);
        Argument mainArgument_ = Argument.getNullableValue(pair_.getArgument());
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(mainArgument_.getStruct());
            if (!ExecTemplates.checkObject(_conf.getStandards().getContent().getReflect().getAliasFct(), lda_, _conf, _stack)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes, _stack);
                return;
            }
            String form_ = _stack.formatVarType(className);
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _conf, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _conf, _stack)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes, _stack);
                return;
            }
            pair_.setArgument(ref_);
            ExecFormattedRootBlock superClass_ = _stack.formatVarType(getFormattedType());
            prep(_conf, _stack, ref_, superClass_, buildInfos(_nodes), getInvokingConstructorContent(), getPair());
            Argument res_ = Argument.createVoid();
            setSimpleArgument(res_, _conf, _nodes, _stack);
            return;
        }
        Argument arg_;
        if (getParent() == null) {
            arg_ = _stack.getLastPage().getGlobalArgument();
        } else {
            arg_ = mainArgument_;
        }
        ExecFormattedRootBlock superClass_ = _stack.formatVarType(getFormattedType());
        prep(_conf, _stack, arg_, superClass_, buildInfos(_nodes), getInvokingConstructorContent(), getPair());
        Argument res_ = Argument.createVoid();
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static void prep(ContextEl _conf, StackCall _stack, Argument _arg, ExecFormattedRootBlock _superClass, CustList<ExecOperationInfo> _infos, ExecInvokingConstructorContent _invokingConstructorContent, ExecTypeFunction _pair) {
        String lastType_ = ExecInherits.quickFormat(_superClass, _invokingConstructorContent.getLastType());
        new DefaultParamChecker(_pair, fectchArgs(lastType_, _invokingConstructorContent.getNaturalVararg(), null, _conf, _stack, _infos), MethodAccessKind.INSTANCE, CallPrepareState.CTOR, InstancingStep.USING_SUPER).checkParams(_superClass, _arg, null, _conf, _stack);
    }

}
