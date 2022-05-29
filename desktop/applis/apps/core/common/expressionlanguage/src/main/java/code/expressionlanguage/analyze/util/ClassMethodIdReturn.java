package code.expressionlanguage.analyze.util;


import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.analyze.opers.util.AbsPossibleVarArg;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.Ints;

public final class ClassMethodIdReturn extends AbsPossibleVarArg {

    private ClassMethodId id;
    private MethodId realId;
    private String realClass;

    private String returnType;
    private String originalReturnType = "";

    private boolean abstractMethod;

    private int ancestor;
    private StandardMethod standardMethod;
    private CustList<NamedArgumentOperation> filter;
    private Ints indexesParams;

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

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public CustList<NamedArgumentOperation> getFilter() {
        return filter;
    }

    public void setFilter(CustList<NamedArgumentOperation> _parameterFilter) {
        filter = _parameterFilter;
    }

    public Ints getIndexesParams() {
        return indexesParams;
    }

    public void setIndexesParams(Ints _name) {
        indexesParams = _name;
    }
}
