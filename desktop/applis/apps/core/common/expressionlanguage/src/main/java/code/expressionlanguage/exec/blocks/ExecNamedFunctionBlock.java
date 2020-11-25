package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;

public abstract class ExecNamedFunctionBlock extends ExecMemberCallingsBlock implements ExecAnnotableBlock {

    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();

    private final String name;

    private String importedReturnType;

    private final StringList importedParametersTypes;
    private final BooleanList parametersRef;

    private final StringList parametersNames;

    private final AccessEnum access;

    private final boolean varargs;

    private CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams = new CustList<CustList<CustList<ExecOperationNode>>>();

    ExecNamedFunctionBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _offsetTrim, StringList _importedParametersTypes, BooleanList _parametersRef) {
        super(_offsetTrim);
        importedParametersTypes = _importedParametersTypes;
        name = _name;
        varargs = _varargs;
        access = _access;
        parametersNames = _parametersNames;
        parametersRef = _parametersRef;
    }

    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams() {
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
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    public void setImportedReturnType(String _importedReturnType) {
        importedReturnType = _importedReturnType;
    }

    public BooleanList getParametersRef() {
        return parametersRef;
    }
}
