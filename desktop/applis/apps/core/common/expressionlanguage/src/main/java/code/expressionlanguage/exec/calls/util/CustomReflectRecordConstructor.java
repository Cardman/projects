package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.CustList;
import code.util.StringMap;

public final class CustomReflectRecordConstructor extends AbstractReflectElement {

    private final ExecRootBlock root;
    private final StringMap<String> id;
    private final String className;

    public CustomReflectRecordConstructor(ExecRootBlock _root,StringMap<String> _id,String _className,
                                          CustList<Argument> _arguments, boolean _lambda) {
        super(_arguments,_lambda);
        root = _root;
        id = _id;
        className = _className;
    }

    public ExecRootBlock getRoot() {
        return root;
    }

    public StringMap<String> getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.CONSTRUCTOR;
    }
}
