package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class CustomReflectRecordConstructor extends AbstractReflectElement {

    private final Struct instance;
    private final ExecRootBlock root;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final ExecFormattedRootBlock className;
    private final CustList<Struct> arguments;
    private final CustList<ExecFormattedRootBlock> ints;
    private final int ref;

    public CustomReflectRecordConstructor(Struct _instance, ExecRootBlock _root, CustList<ExecNamedFieldContent> _namedFields, ExecFormattedRootBlock _className,
                                          CustList<Struct> _arguments, CustList<ExecFormattedRootBlock> _supInts, int _r) {
        super(true);
        instance = _instance;
        root = _root;
        namedFields = _namedFields;
        className = _className;
        arguments = _arguments;
        ints = _supInts;
        ref = _r;
    }

    public Struct getInstance() {
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

    public CustList<Struct> getArguments() {
        return arguments;
    }

    public CustList<ExecFormattedRootBlock> getInts() {
        return ints;
    }

    public int getRef() {
        return ref;
    }
}
