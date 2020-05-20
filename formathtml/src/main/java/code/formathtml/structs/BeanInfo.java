package code.formathtml.structs;

import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;

public final class BeanInfo {

    private String scope = "";

    private String className = "";

    private CustList<RendDynOperationNode> exps = new CustList<RendDynOperationNode>();

    public String getScope() {
        return scope;
    }

    public void setScope(String _scope) {
        scope = _scope;
    }

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
