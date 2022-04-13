package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private final ExecTypeFunctionPair readWrite;

    public ExecCustArrOperation(ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecInstFctContent _instFctContentSet) {
        super(_opCont, _intermediateDottedOperation);
        readWrite = new ExecTypeFunctionPair(new ExecTypeFunctionInst(_instFctContent, _get), new ExecTypeFunctionInst(_instFctContentSet, _set));
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<ExecOperationInfo> infos_ = buildInfos(_nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Struct parent_ = ExecFieldTemplates.getParent(readWrite.getInstRead().getInst().getAnc(), previous_.getStruct(), _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_, readWrite.getInstRead(), infos_);
        ExecHelper.getArgumentPair(_nodes,this).setArgumentList(argumentListCall_.getArgumentWrappers());
        ExecHelper.getArgumentPair(_nodes,this).setArgumentParent(new Argument(parent_));
        Argument res_ = getArgument(this,_conf, _stack,readWrite.getInstRead(), ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),null), parent_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }
    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        CustList<ArgumentWrapper> argumentList_ = ExecHelper.getArgumentPair(_nodes, this).getArgumentList();
        Argument par_ = Argument.getNullableValue(ExecHelper.getArgumentPair(_nodes, this).getArgumentParent());
        return getArgument(this,_conf, _stack, readWrite.getInstWrite(), ArgumentListCall.wrapCall(argumentList_,_right), par_.getStruct());
    }

    @Override
    public boolean resultCanBeSet() {
        return false;
    }

    static Argument getArgument(ExecOperationNode _me, ContextEl _conf, StackCall _stackCall, ExecTypeFunctionInst _inst, ArgumentListCall _call, Struct _parent) {
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        _me.setRelativeOffsetPossibleLastPage(_stackCall);
        return redirect(_conf, _stackCall, _inst, _parent,_call);
    }

    public static Argument redirect(ContextEl _conf, StackCall _stackCall, ExecTypeFunctionInst _ins, Struct _parent, ArgumentListCall _call) {
        ExecTypeFunction fct_ = _ins.getPair();
        Argument prev_ = new Argument(_parent);
        Struct pr_ = prev_.getStruct();
        ExecOverrideInfo polymorph_ = polymorphOrSuper(_ins.getInst().isStaticChoiceMethod(), _conf,pr_, _ins.getInst().getFormattedType(),fct_);
        fct_ = polymorph_.getPair();
        ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
        return new MethodParamChecker(fct_, _call, MethodAccessKind.INSTANCE).checkParams(classNameFound_, prev_, null, _conf, _stackCall);
    }

    public ExecTypeFunctionPair getReadWrite() {
        return readWrite;
    }
}
