package code.formathtml.util;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendSelectOperators {
    private final CustList<RendDynOperationNode> opsValue;
    private final CustList<RendDynOperationNode> opsMap;
    private final CustList<RendDynOperationNode> opsDefault;
    private final CustList<RendDynOperationNode> opsConverterField;
    private final CustList<RendDynOperationNode> opsConverterFieldValue;

    public RendSelectOperators(CustList<RendDynOperationNode> _v, CustList<RendDynOperationNode> _m, CustList<RendDynOperationNode> _d, CustList<RendDynOperationNode> _cf, CustList<RendDynOperationNode> _cfv) {
        this.opsValue = _v;
        this.opsMap = _m;
        this.opsDefault = _d;
        this.opsConverterField = _cf;
        this.opsConverterFieldValue = _cfv;
    }

    public CustList<RendDynOperationNode> getOpsConverterField() {
        return opsConverterField;
    }

    public CustList<RendDynOperationNode> getOpsConverterFieldValue() {
        return opsConverterFieldValue;
    }

    public CustList<RendDynOperationNode> getOpsDefault() {
        return opsDefault;
    }

    public CustList<RendDynOperationNode> getOpsMap() {
        return opsMap;
    }

    public CustList<RendDynOperationNode> getOpsValue() {
        return opsValue;
    }
}
