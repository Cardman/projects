package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;

public final class CustomReflectRecordConstructor extends AbstractReflectElement {

    private final Argument instance;
    private final ExecRootBlock root;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final ExecFormattedRootBlock className;
    private final CustList<Argument> arguments;
    private final CustList<ExecFormattedRootBlock> ints;
    private final int ref;

    public CustomReflectRecordConstructor(Argument _instance, ExecRootBlock _root, CustList<ExecNamedFieldContent> _namedFields, ExecFormattedRootBlock _className,
                                          CustList<Argument> _arguments, CustList<ExecFormattedRootBlock> _supInts, int _r) {
        super(true);
        instance = _instance;
        root = _root;
        namedFields = _namedFields;
        className = _className;
        arguments = _arguments;
        ints = _supInts;
        ref = _r;
    }

    public Argument getInstance() {
        return instance;
    }

    public ExecRootBlock getRoot() {
        return root;
    }

    public CustList<ExecNamedFieldContent> getNamedFields() {
        return namedFields;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }

    public CustList<ExecFormattedRootBlock> getInts() {
        return ints;
    }

    public int getRef() {
        return ref;
    }
}
