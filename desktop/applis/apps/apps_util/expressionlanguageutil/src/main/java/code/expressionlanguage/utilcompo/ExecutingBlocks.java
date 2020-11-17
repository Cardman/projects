package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.StringList;

public final class ExecutingBlocks {

    private ExecTypeFunction executeMethodPair;
    private ExecTypeFunction formatObjectPair;
    private ExecTypeFunction formatObjectTwoPair;
    private ExecRootBlock runnableType;
    private ExecNamedFunctionBlock runMethod;
    public void forwardAndClear(LgNamesContent _content, CustAliases _aliases, Classes _classes) {
        String aliasExecute_ = _aliases.getAliasExecute();
        ExecRootBlock executeType_ = _classes.getClassBody(aliasExecute_);
        String infoTest_ = _aliases.getAliasInfoTest();
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                _aliases.getAliasExecuteTests(),new StringList(infoTest_));
        ExecNamedFunctionBlock executeMethod_ = ExecClassesUtil.getMethodBodiesById(executeType_, fct_).first();
        executeMethodPair = new ExecTypeFunction(executeType_, executeMethod_);
        ExecRootBlock formatType_ = _classes.getClassBody(_aliases.getAliasFormatType());
        ExecNamedFunctionBlock formatObject_ = ExecClassesUtil.getMethodBodiesById(formatType_, new MethodId(MethodAccessKind.STATIC, _aliases.getAliasPrint(), new StringList(_content.getCoreNames().getAliasObject()))).first();
        formatObjectPair = new ExecTypeFunction(formatType_, formatObject_);
        ExecNamedFunctionBlock formatObjectTwo_ = ExecClassesUtil.getMethodBodiesById(formatType_, new MethodId(MethodAccessKind.STATIC, _aliases.getAliasPrint(), new StringList(_content.getCharSeq().getAliasString(), _content.getCoreNames().getAliasObject()), true)).first();
        formatObjectTwoPair = new ExecTypeFunction(formatType_, formatObjectTwo_);
        runnableType = _classes.getClassBody(_aliases.getAliasRunnable());
        runMethod = ExecClassesUtil.getMethodBodiesById(runnableType,new MethodId(MethodAccessKind.INSTANCE, _aliases.getAliasRun(),new StringList())).first();
    }

    public ExecRootBlock getRunnableType() {
        return runnableType;
    }

    public ExecNamedFunctionBlock getRunMethod() {
        return runMethod;
    }

    public ExecTypeFunction getFormatObjectPair() {
        return formatObjectPair;
    }

    public ExecTypeFunction getFormatObjectTwoPair() {
        return formatObjectTwoPair;
    }

    public ExecTypeFunction getExecuteMethodPair() {
        return executeMethodPair;
    }

}
