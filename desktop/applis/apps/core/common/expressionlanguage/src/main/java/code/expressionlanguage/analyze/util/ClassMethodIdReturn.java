package code.expressionlanguage.analyze.util;


import code.expressionlanguage.analyze.opers.util.AbsPossibleVarArg;
import code.expressionlanguage.analyze.opers.util.NamedArgIndex;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;

public final class ClassMethodIdReturn extends AbsPossibleVarArg {

    private ClassMethodId id;
    private MethodId realId;
    private String realClass;

    private String returnType;
    private String originalReturnType = "";

    private boolean abstractMethod;

    private int ancestor;
    private StandardType standardType;
    private StandardMethod standardMethod;
    private CustList<NamedArgIndex> indexesParams;
    private CommonOperSymbol virtualCall;

    public CommonOperSymbol getVirtualCall() {
        return virtualCall;
    }

    public void setVirtualCall(CommonOperSymbol _v) {
        this.virtualCall = _v;
    }

    public ClassMethodId getId() {
        return id;
    }

    public void setId(ClassMethodId _id) {
        id = _id;
    }

    @Override
    public Identifiable ident() {
        return getRealId();
    }

    public MethodId getRealId() {
        return realId;
    }

    public void setRealId(MethodId _realId) {
        realId = _realId;
    }

    public String getRealClass() {
        return realClass;
    }

    public void setRealClass(String _realClass) {
        realClass = _realClass;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public String getOriginalReturnType() {
        return originalReturnType;
    }

    public void setOriginalReturnType(String _originalReturnType) {
        this.originalReturnType = _originalReturnType;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        abstractMethod = _abstractMethod;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _s) {
        this.standardType = _s;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public CustList<NamedArgIndex> getIndexesParams() {
        return indexesParams;
    }

    public void setIndexesParams(CustList<NamedArgIndex> _name) {
        indexesParams = _name;
    }
}
