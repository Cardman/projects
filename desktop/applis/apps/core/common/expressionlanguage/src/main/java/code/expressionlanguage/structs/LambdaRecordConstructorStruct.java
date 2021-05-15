package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class LambdaRecordConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall = Argument.createVoid();

    private final ExecRootBlock root;
    private final String className;
    private final ExecFormattedRootBlock formClassName;

    private final StringMap<String> id;

    public LambdaRecordConstructorStruct(ExecRootBlock _root, String _className, ExecFormattedRootBlock _formClassName, StringMap<String> _id) {
        root = _root;
        className = StringUtil.nullToEmpty(_className);
        formClassName = _formClassName;
        id = _id;
    }

    public ExecRootBlock getRoot() {
        return root;
    }

    public ExecFormattedRootBlock getFormClassName() {
        return formClassName;
    }
    public Argument getInstanceCall() {
        return instanceCall;
    }

    public Struct getMetaInfo() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public StringMap<String> getId() {
        return id;
    }
}
