package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class CustomFoundRecordConstructor implements CallingState,GlobalClassCallingState {

    private final Struct argument;
    private final ExecFormattedRootBlock className;
    private final ExecTypeFunction pair;

    private final CustList<ExecNamedFieldContent> namedFields;
    private final String fieldName;
    private final int childIndex;

    private final CustList<Struct> arguments;
    private final CustList<ExecFormattedRootBlock> list;

    public CustomFoundRecordConstructor(Struct _argument, ExecFormattedRootBlock _className,
                                        ExecTypeFunction _pair,
                                        CustList<ExecNamedFieldContent> _named,
                                        CustList<Struct> _arguments, CustList<ExecFormattedRootBlock> _listSup) {
        argument = _argument;
        className = _className;
        pair = _pair;
        namedFields = _named;
        fieldName = "";
        childIndex = -1;
        arguments = _arguments;
        list = _listSup;
    }

    public CustomFoundRecordConstructor(Struct _argument, ExecFormattedRootBlock _className,
                                        ExecTypeFunction _pair, ExecInstancingStdContent _instancingStdContent,
                                        CustList<Struct> _arguments) {
        argument = _argument;
        className = _className;
        pair = _pair;
        namedFields = _instancingStdContent.getNamedFields();
        fieldName = _instancingStdContent.getFieldName();
        childIndex = _instancingStdContent.getBlockIndex();
        list = _instancingStdContent.getSupInts();
        arguments = _arguments;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createRecordInstancing(_context,this);
    }

    public Struct getArgument() {
        return argument;
    }

    public CustList<ExecNamedFieldContent> getNamedFields() {
        return namedFields;
    }

    public CustList<ExecFormattedRootBlock> getList() {
        return list;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public CustList<Struct> getArguments() {
        return arguments;
    }

}
