package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;

public final class RootBlockIdValidator {
    private final CustList<MethodId> idMethods = new CustList<MethodId>();
    private final CustList<NamedCalledFunctionBlock> indexersGet = new CustList<NamedCalledFunctionBlock>();
    private final CustList<NamedCalledFunctionBlock> indexersSet = new CustList<NamedCalledFunctionBlock>();
    private final CustList<ConstructorId> idConstructors = new CustList<ConstructorId>();
    private final CustList<MethodHeaderInfo> unary = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> binaryFirst = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> binarySecond = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> binaryAll = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> explicit = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> explicitId = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> explicitFrom = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> implicit = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> implicitId = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> implicitFrom = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> trues = new CustList<MethodHeaderInfo>();
    private final CustList<MethodHeaderInfo> falses = new CustList<MethodHeaderInfo>();

    public CustList<ConstructorId> getIdConstructors() {
        return idConstructors;
    }

    public CustList<MethodHeaderInfo> getBinaryAll() {
        return binaryAll;
    }

    public CustList<MethodHeaderInfo> getBinaryFirst() {
        return binaryFirst;
    }

    public CustList<MethodHeaderInfo> getBinarySecond() {
        return binarySecond;
    }

    public CustList<MethodHeaderInfo> getExplicit() {
        return explicit;
    }

    public CustList<MethodHeaderInfo> getExplicitFrom() {
        return explicitFrom;
    }

    public CustList<MethodHeaderInfo> getExplicitId() {
        return explicitId;
    }

    public CustList<MethodHeaderInfo> getFalses() {
        return falses;
    }

    public CustList<MethodHeaderInfo> getImplicit() {
        return implicit;
    }

    public CustList<MethodHeaderInfo> getImplicitFrom() {
        return implicitFrom;
    }

    public CustList<MethodHeaderInfo> getImplicitId() {
        return implicitId;
    }

    public CustList<MethodHeaderInfo> getTrues() {
        return trues;
    }

    public CustList<MethodHeaderInfo> getUnary() {
        return unary;
    }

    public CustList<MethodId> getIdMethods() {
        return idMethods;
    }

    public CustList<NamedCalledFunctionBlock> getIndexersGet() {
        return indexersGet;
    }

    public CustList<NamedCalledFunctionBlock> getIndexersSet() {
        return indexersSet;
    }
}
