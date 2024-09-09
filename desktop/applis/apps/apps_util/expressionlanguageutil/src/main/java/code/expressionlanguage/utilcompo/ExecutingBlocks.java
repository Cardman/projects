package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecutingBlocks {

    private ExecTypeFunction groupClassPair;
    private ExecTypeFunction groupClassMethodPair;
    private ExecTypeFunction executeMethodPair;
    private ExecTypeFunction formatObjectPair;
    private ExecTypeFunction formatObjectTwoPair;
    private ExecRootBlock runnableType;
    private ExecNamedFunctionBlock runMethod;
    private ExecRootBlock callableType;
    private ExecNamedFunctionBlock callMethod;
    public void forwardAndClear(LgNamesContent _content, CustAliases _aliases, Classes _classes) {
        groupClassTests(_aliases, _classes);
        groupClassMethodTests(_content,_aliases, _classes);
        exec(_content,_aliases, _classes);
        formatter(_content, _aliases, _classes);
        runnable(_aliases, _classes);
        callable(_aliases, _classes);
    }

    public void groupClassMethodTests(LgNamesContent _content, CustAliases _aliases, Classes _classes) {
        String aliasExecute_ = _aliases.getAliasExecute();
        ExecRootBlock executeType_ = _classes.getClassBody(aliasExecute_);
        String infoTest_ = _aliases.getAliasInfoTest();
        AliasReflection ref_ = _content.getReflect();
        String cl_ = ref_.getAliasClassType();
        String sec_ = _aliases.getAliasTable()+"<"+ cl_ +","+_aliases.getAliasList()+"<"+_aliases.getAliasExecutedTest()+">>";
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                _aliases.getAliasExecuteGroupClassMethod(),new StringList(infoTest_,sec_));
        ExecNamedFunctionBlock executeMethod_ = ExecClassesUtil.getMethodBodiesById(executeType_, fct_).first();
        groupClassMethodPair = new ExecTypeFunction(executeType_, executeMethod_);
    }

    public void groupClassTests(CustAliases _aliases, Classes _classes) {
        String aliasExecute_ = _aliases.getAliasExecute();
        ExecRootBlock executeType_ = _classes.getClassBody(aliasExecute_);
        String infoTest_ = _aliases.getAliasInfoTest();

        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                _aliases.getAliasExecuteGroupClass(),new StringList(infoTest_));
        ExecNamedFunctionBlock executeMethod_ = ExecClassesUtil.getMethodBodiesById(executeType_, fct_).first();
        groupClassPair = new ExecTypeFunction(executeType_, executeMethod_);
    }

    public void exec(LgNamesContent _content, CustAliases _aliases, Classes _classes) {
        String aliasExecute_ = _aliases.getAliasExecute();
        ExecRootBlock executeType_ = _classes.getClassBody(aliasExecute_);
        String infoTest_ = _aliases.getAliasInfoTest();
        AliasReflection ref_ = _content.getReflect();
        String cl_ = ref_.getAliasClassType();
        String met_ = ref_.getAliasMethod();
        String sec_ = _aliases.getAliasTable()+"<"+ cl_ +","+_aliases.getAliasTable()+"<"+ met_ +","+_aliases.getAliasResult()+">>";
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                _aliases.getAliasExecuteTests(),new StringList(infoTest_,sec_));
        ExecNamedFunctionBlock executeMethod_ = ExecClassesUtil.getMethodBodiesById(executeType_, fct_).first();
        executeMethodPair = new ExecTypeFunction(executeType_, executeMethod_);
    }

    public void formatter(LgNamesContent _content, CustAliases _aliases, Classes _classes) {
        ExecRootBlock formatType_ = _classes.getClassBody(_aliases.getAliasFormatType());
        ExecNamedFunctionBlock formatObject_ = ExecClassesUtil.getMethodBodiesById(formatType_, new MethodId(MethodAccessKind.STATIC, _aliases.getAliasPrint(), new StringList(_content.getCoreNames().getAliasObject()))).first();
        formatObjectPair = new ExecTypeFunction(formatType_, formatObject_);
        ExecNamedFunctionBlock formatObjectTwo_ = ExecClassesUtil.getMethodBodiesById(formatType_, new MethodId(MethodAccessKind.STATIC, _aliases.getAliasPrint(), new StringList(_content.getCharSeq().getAliasString(), _content.getCoreNames().getAliasObject()), true)).first();
        formatObjectTwoPair = new ExecTypeFunction(formatType_, formatObjectTwo_);
    }

    public void runnable(CustAliases _aliases, Classes _classes) {
        runnableType = _classes.getClassBody(_aliases.getAliasRunnable());
        runMethod = ExecClassesUtil.getMethodBodiesById(runnableType,new MethodId(MethodAccessKind.INSTANCE, _aliases.getAliasRun(),new StringList())).first();
    }

    public void callable(CustAliases _aliases, Classes _classes) {
        callableType = _classes.getClassBody(_aliases.getAliasCallable());
        callMethod = ExecClassesUtil.getMethodBodiesById(callableType,new MethodId(MethodAccessKind.INSTANCE, _aliases.getAliasCallableMethod(),new StringList())).first();
    }

    public Struct infoTests(ContextEl _ctx, int _count) {
        LgNamesGui stds_ = (LgNamesGui) _ctx.getStandards();
        String infoTest_ = stds_.getExecContent().getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, _ctx.getClasses());
        Struct infoStruct_ = ProcessMethod.calculate(new CustomFoundConstructor(_ctx,className_, NullStruct.NULL_VALUE),_ctx,StackCall.newInstance(InitPhase.NOTHING,_ctx)).getValue();
        ((FieldableStruct)infoStruct_).getEntryStruct(new ClassField(infoTest_,stds_.getExecContent().getCustAliases().getAliasInfoTestNbThreads())).setStruct(new IntStruct(_count));
        return infoStruct_;
    }
    public Struct groupClass(Struct _infoStruct,ContextEl _ctx) {
        ContextEl cpGr_ = _ctx.copy(_ctx.getInterrupt(), NullStruct.NULL_VALUE);
        ExecTypeFunction pairGrCl_ = getGroupClassPair();
        return ArgumentListCall.getNull(EventStruct.invoke(NullStruct.NULL_VALUE,
                cpGr_, pairGrCl_, StackCall.newInstance(InitPhase.NOTHING,cpGr_), new ArgumentListCall(new CustList<ArgumentWrapper>(new ArgumentWrapper(_infoStruct)))));
    }
    public Struct groupClassMethod(Struct _infoStruct, Struct _gr,ContextEl _ctx) {
        ContextEl cpNext_ = _ctx.copy(_ctx.getInterrupt(),NullStruct.NULL_VALUE);
        ExecTypeFunction pairGrClTes_ = getGroupClassMethodPair();
        return ArgumentListCall.getNull(EventStruct.invoke(NullStruct.NULL_VALUE,
                cpNext_, pairGrClTes_, StackCall.newInstance(InitPhase.NOTHING,cpNext_), new ArgumentListCall(new CustList<ArgumentWrapper>(new ArgumentWrapper(_infoStruct),new ArgumentWrapper(_gr)))));
    }
    public Struct executeTests(Struct _infoStruct, Struct _gr,ContextEl _ctx) {
        ExecTypeFunction pair_ = getExecuteMethodPair();
        return ArgumentListCall.getNull(EventStruct.invoke(NullStruct.NULL_VALUE,
                _ctx, pair_, StackCall.newInstance(InitPhase.NOTHING,_ctx), new ArgumentListCall(new CustList<ArgumentWrapper>(new ArgumentWrapper(_infoStruct),new ArgumentWrapper(_gr)))));
    }
    public ExecRootBlock getRunnableType() {
        return runnableType;
    }

    public ExecNamedFunctionBlock getRunMethod() {
        return runMethod;
    }

    public ExecRootBlock getCallableType() {
        return callableType;
    }

    public ExecNamedFunctionBlock getCallMethod() {
        return callMethod;
    }

    public ExecTypeFunction getFormatObjectPair() {
        return formatObjectPair;
    }

    public ExecTypeFunction getFormatObjectTwoPair() {
        return formatObjectTwoPair;
    }

    public ExecTypeFunction getGroupClassPair() {
        return groupClassPair;
    }

    public ExecTypeFunction getGroupClassMethodPair() {
        return groupClassMethodPair;
    }

    public ExecTypeFunction getExecuteMethodPair() {
        return executeMethodPair;
    }

}
