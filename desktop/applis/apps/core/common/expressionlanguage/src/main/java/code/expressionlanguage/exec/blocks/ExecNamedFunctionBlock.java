package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.util.CustList;
import code.util.StringList;

public abstract class ExecNamedFunctionBlock extends ExecMemberCallingsBlock implements ExecAnnotableBlock {

    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();

    private final String name;

    private String importedReturnType;

    private StringList importedParametersTypes;

    private final StringList parametersNames;

    private final AccessEnum access;

    private final boolean varargs;

    private CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams = new CustList<CustList<CustList<ExecOperationNode>>>();

    ExecNamedFunctionBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _offsetTrim) {
        super(_offsetTrim);
        importedParametersTypes = new StringList();
        name = _name;
        varargs = _varargs;
        access = _access;
        parametersNames = _parametersNames;
    }

    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    public String getName() {
        return name;
    }

    public final StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    public final boolean isVarargs() {
        return varargs;
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public StringList getImportedParametersTypes() {
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

}
