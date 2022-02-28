package code.bean.nat.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringMap;

public final class NatStdRefVariableOperation extends RendLeafOperation implements
        RendCalculableOperation {
    private final NatExecVariableContent variableContent;

    public NatStdRefVariableOperation(ExecOperationContent _l, NatExecVariableContent _variableContent) {
        super(_l);
        variableContent = _variableContent;
    }

    public static Argument getIndexLoop(NatExecVariableContent _varCont, StringMap<LoopVariable> _vars) {
        return getIndexLoop(_varCont.getVariableName(), _vars);
    }

    public static Argument getIndexLoop(String _val, StringMap<LoopVariable> _vars) {
        LoopVariable locVar_ = _vars.getVal(_val);
        IntStruct str_ = new IntStruct((int) locVar_.getIndex());
        return new Argument(str_);
    }

    public static AbstractWrapper getWrapper(NatExecVariableContent _varCont, StringMap<AbstractWrapper> _refParams) {
        return getWrapper(_varCont.getVariableName(), _refParams);
    }

    public static AbstractWrapper getWrapper(String _val, StringMap<AbstractWrapper> _refParams) {
        return _refParams.getVal(_val);
    }

    public static Argument getArgValue(AbstractWrapper _w, ContextEl _context, StackCall _stackCall) {
        return new Argument(getValue(_w, _context, _stackCall));
    }

    public static Struct getValue(AbstractWrapper _w, ContextEl _context, StackCall _stackCall) {
        if (_w == null) {
            return NullStruct.NULL_VALUE;
        }
        return Argument.getNull(_w.getValue(_stackCall, _context));
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _rendStack);
        ImportingPage ip_ = _rendStack.getLastPage();
        AbstractWrapper val_ = getWrapper(variableContent, ip_.getRefParams());
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        pair_.setWrapper(val_);
        Argument arg_ = new ArgumentWrapper(getArgValue(val_, _context, _rendStack.getStackCall()), null).getValue();
        calcArg(_nodes, arg_);
    }


}
