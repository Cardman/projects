package code.formathtml.structs;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class BeanInfo {

    private String scope = "";

    private String className = "";

    private String resolvedClassName = "";

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

    public String getResolvedClassName() {
        return resolvedClassName;
    }

    public void setResolvedClassName(String _reasolvedClassName) {
        resolvedClassName = _reasolvedClassName;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }

    public void setExps(CustList<RendDynOperationNode> _exps) {
        exps = _exps;
    }
}
