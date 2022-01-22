package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;
import code.util.core.StringUtil;

public final class LambdaRecordConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall = Argument.createVoid();

    private final ExecRootBlock root;
    private final String className;
    private final ExecFormattedRootBlock formClassName;

    private final CustList<ExecNamedFieldContent> namedFields;

    public LambdaRecordConstructorStruct(ExecRootBlock _root, String _className, ExecFormattedRootBlock _formClassName, CustList<ExecNamedFieldContent> _namedFields) {
        root = _root;
        className = StringUtil.nullToEmpty(_className);
        formClassName = _formClassName;
        namedFields = _namedFields;
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

    public CustList<ExecNamedFieldContent> getNamedFields() {
        return namedFields;
    }
}
