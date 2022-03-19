package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;
import code.util.core.StringUtil;

public final class LambdaRecordConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall;

    private final ExecRootBlock root;
    private final String className;
    private final ExecFormattedRootBlock formClassName;
    private final boolean safeInstance;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final boolean shiftInstance;
    private final CustList<ExecFormattedRootBlock> ints;

    public LambdaRecordConstructorStruct(ExecLambdaCommonContent _cont, Argument _previous, ExecRootBlock _root, String _className, ExecFormattedRootBlock _formClassName, CustList<ExecNamedFieldContent> _namedFields, CustList<ExecFormattedRootBlock> _supInts) {
        root = _root;
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        formClassName = _formClassName;
        namedFields = _namedFields;
        safeInstance = _cont.isSafeInstance();
        shiftInstance = _cont.isShiftArgument();
        ints = _supInts;
    }


    public boolean isShiftInstance() {
        return shiftInstance;
    }
    public boolean isSafeInstance() {
        return safeInstance && instanceCall.isNull();
    }

    public ExecRootBlock getRoot() {
        return root;
    }

    public CustList<ExecFormattedRootBlock> getInts() {
        return ints;
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
