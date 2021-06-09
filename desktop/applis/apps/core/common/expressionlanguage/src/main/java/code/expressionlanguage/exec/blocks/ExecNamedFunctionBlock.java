package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;
import code.util.StringList;

public abstract class ExecNamedFunctionBlock extends ExecMemberCallingsBlock implements ExecAnnotableParamBlock {

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();

    private final String name;

    private String importedReturnType;

    private final StringList importedParametersTypes;
    private final CustList<Boolean> parametersRef;

    private final StringList parametersNames;

    private final AccessEnum access;

    private final boolean retRef;
    private final boolean varargs;
    private final CustList<CustList<ExecAnnotContent>> annotationsOpsParams = new CustList<CustList<ExecAnnotContent>>();

    ExecNamedFunctionBlock(boolean _retRef, String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _offsetTrim, StringList _importedParametersTypes, CustList<Boolean> _parametersRef) {
        super(_offsetTrim);
        retRef = _retRef;
        importedParametersTypes = _importedParametersTypes;
        name = _name;
        varargs = _varargs;
        access = _access;
        parametersNames = _parametersNames;
        parametersRef = _parametersRef;
    }

    public CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    public String getName() {
        return name;
    }

    public final boolean getParametersRef(int _index) {
        return parametersRef.get(_index);
    }

    public final String getParametersName(int _index) {
        return parametersNames.get(_index);
    }

    public final boolean isRetRef() {
        return retRef;
    }

    public final boolean isVarargs() {
        return varargs;
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public final StringList getImportedParametersTypes() {
        return importedParametersTypes;
    }

    public String getImportedReturnType() {
        return importedReturnType;
    }
    @Override
    public CustList<ExecAnnotContent> getAnnotationsOps() {
        return annotationsOps;
    }

    public void setImportedReturnType(String _importedReturnType) {
        importedReturnType = _importedReturnType;
    }

    public CustList<Boolean> getParametersRef() {
        return parametersRef;
    }
}
