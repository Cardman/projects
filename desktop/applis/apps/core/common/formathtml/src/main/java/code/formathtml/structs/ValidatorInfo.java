package code.formathtml.structs;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class ValidatorInfo {
    private String className = "";

    private CustList<RendDynOperationNode> exps = new CustList<RendDynOperationNode>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }

    public void setExps(CustList<RendDynOperationNode> _exps) {
        exps = _exps;
    }

}
