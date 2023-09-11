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
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionPair;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ArrayCustWrapper extends ValueWrapper {
    private final CustList<ArgumentWrapper> ls;
    private final Struct parent;
    private final String previousClass;
    private final ExecTypeFunctionPair readWrite;
    public ArrayCustWrapper(Struct _v, CustList<ArgumentWrapper> _list, Struct _pa, String _previousClass, ExecTypeFunctionPair _readWrite) {
        super(_v);
        ls = _list;
        parent = _pa;
        previousClass = _previousClass;
        readWrite = _readWrite;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        setValue(ArgumentListCall.toStr(_right));
        ExecCustArrOperation.redirect(_conf, _stack, readWrite.getInstWrite(), parent,ArgumentListCall.wrapCall(ls,_right));
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecCustArrOperation.redirect(_conf, _stack, readWrite.getInstRead(), parent,ArgumentListCall.wrapCall(ls,null)).getStruct();
    }

    @Override
    public String getClassName(ContextEl _conf) {
        ExecTypeFunction fct_ = readWrite.getInstWrite().getPair();
        ExecRootBlock type_ = fct_.getType();
        ExecNamedFunctionBlock named_ = fct_.getFct();
        return ExecFieldTemplates.formatType(_conf, type_, named_.getImportedReturnType(), previousClass);
    }

    public Struct getParent() {
        return parent;
    }

    public ArgumentListCall args() {
        return ArgumentListCall.wrapCall(ls,null);
    }
    public ArgumentListCall argsWrite(Struct _right) {
        return ArgumentListCall.wrapCall(ls,ArgumentListCall.toStr(_right));
    }
    public ExecOverrideInfo poly(ContextEl _cont, Struct _pr) {
        return ExecCustArrOperation.poly(readWrite.getInstRead(), _cont, _pr);
    }
    public ExecOverrideInfo polyWrite(ContextEl _cont, Struct _pr) {
        return ExecCustArrOperation.poly(readWrite.getInstWrite(), _cont, _pr);
    }
}
