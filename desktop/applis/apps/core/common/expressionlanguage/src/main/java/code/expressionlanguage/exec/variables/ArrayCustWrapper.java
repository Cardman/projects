package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.opers.ExecCustArrOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ArrayCustWrapper implements AbstractWrapper {
    private final CustList<ArgumentWrapper> ls;
    private final Struct parent;
    private final String previousClass;
    private final ExecTypeFunctionPair readWrite;
    public ArrayCustWrapper(CustList<ArgumentWrapper> _list, Struct _pa, String _previousClass, ExecTypeFunctionPair _readWrite) {
        ls = _list;
        parent = _pa;
        previousClass = _previousClass;
        readWrite = _readWrite;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecCustArrOperation.redirect(_conf, _stack, readWrite.getWrite(),readWrite.getInstWrite(), parent,ArgumentListCall.wrapCall(ls,_right));
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecCustArrOperation.redirect(_conf, _stack, readWrite.getRead(),readWrite.getInstRead(), parent,ArgumentListCall.wrapCall(ls,null)).getStruct();
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        ExecTypeFunction fct_ = readWrite.getWrite();
        ExecRootBlock type_ = fct_.getType();
        ExecNamedFunctionBlock named_ = fct_.getFct();
        return ExecFieldTemplates.formatType(_conf, type_, named_.getImportedReturnType(), previousClass);
    }
}
