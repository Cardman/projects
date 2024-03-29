package code.expressionlanguage.analyze;

import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class MethodHeaders {
    private final StringMap<CustList<MethodHeaderInfo>> unary = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> binaryAll = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> binaryFirst = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> binarySecond = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitIdCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> explicitFromCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitIdCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> implicitFromCastMethods = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> trues = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringMap<CustList<MethodHeaderInfo>> falses = new StringMap<CustList<MethodHeaderInfo>>();
    private final StringList typesWithInnerOperators = new StringList();
    private final StringList packagesFound = new StringList();
    private final StringList basePackagesFound = new StringList();
    public static void addFromTo(MethodHeaders _headers, MethodHeaders _dest) {
        _dest.basePackagesFound.addAllElts(_headers.basePackagesFound);
        _dest.packagesFound.addAllElts(_headers.packagesFound);
        _dest.typesWithInnerOperators.addAllElts(_headers.typesWithInnerOperators);
        _dest.falses.addAllEntries(_headers.falses);
        _dest.trues.addAllEntries(_headers.trues);
        _dest.unary.addAllEntries(_headers.unary);
        _dest.binaryAll.addAllEntries(_headers.binaryAll);
        _dest.binaryFirst.addAllEntries(_headers.binaryFirst);
        _dest.binarySecond.addAllEntries(_headers.binarySecond);
        _dest.explicitCastMethods.addAllEntries(_headers.explicitCastMethods);
        _dest.explicitIdCastMethods.addAllEntries(_headers.explicitIdCastMethods);
        _dest.explicitFromCastMethods.addAllEntries(_headers.explicitFromCastMethods);
        _dest.implicitCastMethods.addAllEntries(_headers.implicitCastMethods);
        _dest.implicitIdCastMethods.addAllEntries(_headers.implicitIdCastMethods);
        _dest.implicitFromCastMethods.addAllEntries(_headers.implicitFromCastMethods);
    }

    public StringMap<CustList<MethodHeaderInfo>> getUnary() {
        return unary;
    }

    public StringMap<CustList<MethodHeaderInfo>> getBinaryAll() {
        return binaryAll;
    }

    public StringMap<CustList<MethodHeaderInfo>> getBinaryFirst() {
        return binaryFirst;
    }

    public StringMap<CustList<MethodHeaderInfo>> getBinarySecond() {
        return binarySecond;
    }

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

    public StringMap<CustList<MethodHeaderInfo>> getTrues() {
        return trues;
    }

    public StringMap<CustList<MethodHeaderInfo>> getFalses() {
        return falses;
    }

    public StringList getTypesWithInnerOperators() {
        return typesWithInnerOperators;
    }
    public StringList getPackagesFound() {
        return packagesFound;
    }

    public StringList getBasePackagesFound() {
        return basePackagesFound;
    }
}
