package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecCustArrOperation;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ArrayCustWrapper implements AbstractWrapper {
    private final Argument previous;
    private final CustList<ExecOperationInfo> infos;
    private final ExecInstFctContent instFctContent;
    private final ExecTypeFunctionPair readWrite;
    public ArrayCustWrapper(Argument _previous, CustList<ExecOperationInfo> _infos, ExecInstFctContent _instFctContent, ExecTypeFunctionPair _readWrite) {
        previous = _previous;
        infos = _infos;
        instFctContent = _instFctContent;
        readWrite = _readWrite;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecCustArrOperation.redirect(_conf,_right,_stack,previous,infos,instFctContent,readWrite);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecCustArrOperation.redirect(_conf,null,_stack,previous,infos,instFctContent,readWrite).getStruct();
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        ExecTypeFunction fct_ = readWrite.getRead();
        ExecRootBlock type_ = fct_.getType();
        ExecNamedFunctionBlock named_ = fct_.getFct();
        Struct pr_ = previous.getStruct();
        String argClassName_ = pr_.getClassName(_conf);
        return ExecTemplates.formatType(_conf, type_, named_.getImportedReturnType(), argClassName_);
    }
}
