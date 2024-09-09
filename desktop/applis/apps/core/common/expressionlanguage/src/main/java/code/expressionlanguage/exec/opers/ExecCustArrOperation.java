package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
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
import code.expressionlanguage.structs.NullStruct;
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
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        Struct parent_ = ExecFieldTemplates.getParent(readWrite.getInstRead().getInst().getAnc(), previous_, _conf, _stack);
        ArgumentListCall argumentListCall_ = fetchFormattedArgs(_conf, _stack, parent_, readWrite.getInstRead(), infos_);
        ExecHelper.getArgumentPair(_nodes,this).setArgumentList(argumentListCall_.getArgumentWrappers());
        ExecHelper.getArgumentPair(_nodes,this).setArgumentParent(parent_);
        getArgument(this,_conf, _stack,readWrite.getInstRead(), ArgumentListCall.wrapCall(argumentListCall_.getArgumentWrappers(),null), parent_);
        setCheckedResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack,false);
    }
    @Override
    public Struct calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _right, StackCall _stack) {
        CustList<ArgumentWrapper> argumentList_ = ExecHelper.getArgumentPair(_nodes, this).getArgumentList();
        Struct par_ = ArgumentListCall.getNull(ExecHelper.getArgumentPair(_nodes, this).getArgumentParent());
        getArgument(this,_conf, _stack, readWrite.getInstWrite(), ArgumentListCall.wrapCall(argumentList_,_right), par_);
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean resultCanBeSet() {
        return false;
    }
    public Struct instance(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last) {
        return ExecOperationNode.instance(this,readWrite.getInstRead().getInst().getAnc(), _nodes, _last);
    }
    public Struct instanceWrite(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return ExecHelper.getArgumentPair(_nodes, this).getArgumentParent();
    }
    public ArgumentListCall args(ContextEl _cont, Struct _pr, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return args(_cont,readWrite.getInstRead().getPair().getType(),readWrite.getInstRead().getInst().getLastType(),readWrite.getInstRead().getInst().getNaturalVararg(),_pr,_nodes).getArguments();
    }
    public ArgumentListCall argsWrite(Struct _right, IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        CustList<ArgumentWrapper> argumentList_ = ExecHelper.getArgumentPair(_nodes, this).getArgumentList();
        return ArgumentListCall.wrapCall(argumentList_,_right);
    }
    public ExecOverrideInfo poly(ContextEl _cont, Struct _pr) {
        return poly(readWrite.getInstRead(), _cont, _pr);
    }
    public ExecOverrideInfo polyWrite(ContextEl _cont, Struct _pr) {
        return poly(readWrite.getInstWrite(), _cont, _pr);
    }

    public static ExecOverrideInfo poly(ExecTypeFunctionInst _ins, ContextEl _cont, Struct _pr) {
        ExecFormattedRootBlock formattedType_ = _ins.getInst().getFormattedType();
        return polymorphOrSuper(_ins.getInst().isStaticChoiceMethod(), _cont, _pr, formattedType_, _ins.getPair());
    }
    static void getArgument(ExecOperationNode _me, ContextEl _conf, StackCall _stackCall, ExecTypeFunctionInst _inst, ArgumentListCall _call, Struct _parent) {
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        _me.setRelativeOffsetPossibleLastPage(_stackCall);
        redirect(_conf, _stackCall, _inst, _parent,_call);
    }

    public static void redirect(ContextEl _conf, StackCall _stackCall, ExecTypeFunctionInst _ins, Struct _parent, ArgumentListCall _call) {
        ExecTypeFunction fct_ = _ins.getPair();
        ExecOverrideInfo polymorph_ = polymorphOrSuper(_ins.getInst().isStaticChoiceMethod(), _conf, _parent, _ins.getInst().getFormattedType(),fct_);
        fct_ = polymorph_.getPair();
        ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
        new MethodParamChecker(fct_, _call, MethodAccessKind.INSTANCE).checkParams(classNameFound_, _parent, null, _conf, _stackCall);
    }

    public ExecTypeFunctionPair getReadWrite() {
        return readWrite;
    }
}
