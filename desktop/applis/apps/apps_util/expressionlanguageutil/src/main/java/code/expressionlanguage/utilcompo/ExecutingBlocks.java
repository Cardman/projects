package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.StringList;

public final class ExecutingBlocks {

    private ExecRootBlock executeType;
    private ExecNamedFunctionBlock executeMethod;
    private ExecRootBlock formatType;
    private ExecNamedFunctionBlock formatObject;
    private ExecNamedFunctionBlock formatObjectTwo;
    private ExecRootBlock runnableType;
    private ExecNamedFunctionBlock runMethod;
    public void forwardAndClear(LgNamesContent _content, CustAliases _aliases, Classes _classes) {
        String aliasExecute_ = _aliases.getAliasExecute();
        executeType = _classes.getClassBody(aliasExecute_);
        String infoTest_ = _aliases.getAliasInfoTest();
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                _aliases.getAliasExecuteTests(),new StringList(infoTest_));
        executeMethod = ExecBlock.getMethodBodiesById(executeType,fct_).first();
        formatType = _classes.getClassBody(_aliases.getAliasFormatType());
        formatObject = ExecBlock.getMethodBodiesById(formatType,new MethodId(MethodAccessKind.STATIC, _aliases.getAliasPrint(),new StringList(_content.getCoreNames().getAliasObject()))).first();
        formatObjectTwo = ExecBlock.getMethodBodiesById(formatType,new MethodId(MethodAccessKind.STATIC, _aliases.getAliasPrint(),new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject()),true)).first();
        runnableType = _classes.getClassBody(_aliases.getAliasRunnable());
        runMethod = ExecBlock.getMethodBodiesById(runnableType,new MethodId(MethodAccessKind.INSTANCE, _aliases.getAliasRun(),new StringList())).first();
    }

    public ExecRootBlock getRunnableType() {
        return runnableType;
    }

    public ExecNamedFunctionBlock getRunMethod() {
        return runMethod;
    }

    public ExecRootBlock getFormatType() {
        return formatType;
    }

    public ExecNamedFunctionBlock getFormatObjectTwo() {
        return formatObjectTwo;
    }

    public ExecNamedFunctionBlock getFormatObject() {
        return formatObject;
    }

    public ExecNamedFunctionBlock getExecuteMethod() {
        return executeMethod;
    }

    public ExecRootBlock getExecuteType() {
        return executeType;
    }
}
