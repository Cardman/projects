package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class MethodHeaders {
    private final StringMap<CustList<MethodHeaderInfo>> explicitCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitIdCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitFromCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitIdCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitFromCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringList typesWithInnerOperators = new StringList();
    private StringList packagesFound = new StringList();

    private final CustList<RootBlock> allFound = new CustList<RootBlock>();

    public StringMap<CustList<MethodHeaderInfo>> getExplicitCastMethods() {
        return explicitCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitIdCastMethods() {
        return explicitIdCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getExplicitFromCastMethods() {
        return explicitFromCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitCastMethods() {
        return implicitCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitIdCastMethods() {
        return implicitIdCastMethods;
    }

    public StringMap<CustList<MethodHeaderInfo>> getImplicitFromCastMethods() {
        return implicitFromCastMethods;
    }

    public StringList getTypesWithInnerOperators() {
        return typesWithInnerOperators;
    }
    public StringList getPackagesFound() {
        return packagesFound;
    }

    public CustList<RootBlock> getAllFound() {
        return allFound;
    }
}
