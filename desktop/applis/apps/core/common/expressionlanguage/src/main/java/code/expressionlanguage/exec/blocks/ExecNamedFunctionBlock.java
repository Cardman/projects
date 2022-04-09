package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;

public abstract class ExecNamedFunctionBlock extends ExecMemberCallingsBlock implements ExecAnnotableParamBlock {

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();

    private final ExecExecNamedFunctionContent content;

    private String importedReturnType;

    private final AccessEnum access;

    private final CustList<CustList<ExecAnnotContent>> annotationsOpsParams = new CustList<CustList<ExecAnnotContent>>();

    ExecNamedFunctionBlock(AccessEnum _access, ExecExecNamedFunctionContent _content) {
        content = _content;
        access = _access;
    }

    public CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    public String getName() {
        return content.getName();
    }

    public final BoolVal getParametersRef(int _index) {
        return content.getParametersRef().get(_index);
    }

    public final String getParametersName(int _index) {
        return content.getParametersNames().get(_index);
    }

    public final boolean isRetRef() {
        return content.isRetRef();
    }

    public final boolean isVarargs() {
        return content.isVarargs();
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public final StringList getImportedParametersTypes() {
        return content.getImportedParametersTypes();
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

    public CustList<BoolVal> getParametersRef() {
        return content.getParametersRef();
    }
}
