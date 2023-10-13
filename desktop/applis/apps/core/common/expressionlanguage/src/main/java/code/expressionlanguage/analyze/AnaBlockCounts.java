package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.util.*;

public final class AnaBlockCounts {
    private final Longs countAnon = new Longs();
    private long countOut;
    private final StringMap<Long> countAnonType = new StringMap<Long>();
    private final StringMap<Long> countLocalType = new StringMap<Long>();
    private final CustList<CustList<RootBlock>> localTypes = new CustList<CustList<RootBlock>>();
    private final CustList<CustList<AnonymousTypeBlock>> anonTypes = new CustList<CustList<AnonymousTypeBlock>>();
    private final CustList<AnonymousElementsFct> anonElts = new CustList<AnonymousElementsFct>();
    private final CustList<AnonymousElements> anonFieldsElts = new CustList<AnonymousElements>();
    private final CustList<AnonymousElements> anonTypesElts = new CustList<AnonymousElements>();
    private final CustList<StringMap<Long>> countsAnon = new CustList<StringMap<Long>>();
    private final CustList<StringMap<Long>> counts = new CustList<StringMap<Long>>();

    public static void completeFromTo(AnaBlockCounts _from, AnaBlockCounts _dest) {
        _dest.countOut = _from.countOut;
        _dest.countAnonType.addAllEntries(_from.countAnonType);
        _dest.countLocalType.addAllEntries(_from.countLocalType);
        feedCountAnon(_from, _dest, _dest.countAnon.size(), _from.countAnon.size());
        feedCount(_from.countsAnon, _dest.countsAnon, _dest.countsAnon.size(), _from.countsAnon.size());
        feedCount(_from.counts, _dest.counts, _dest.counts.size(), _from.counts.size());
        feedLocal(_from, _dest, _dest.localTypes.size(), _from.localTypes.size());
        feedAnon(_from, _dest, _dest.anonTypes.size(), _from.anonTypes.size());
        feedAnonElts(_from, _dest, _dest.anonElts.size(), _from.anonElts.size());
        feedLoop(_from.anonFieldsElts, _dest.anonFieldsElts, _dest.anonFieldsElts.size(), _from.anonFieldsElts.size());
        feedLoop(_from.anonTypesElts, _dest.anonTypesElts, _dest.anonTypesElts.size(), _from.anonTypesElts.size());
    }

    private static void feedCountAnon(AnaBlockCounts _from, AnaBlockCounts _dest, int _begin, int _end) {
        for (int i = _begin; i < _end; i++) {
            _dest.countAnon.add(_from.countAnon.get(i));
        }
    }

    private static void feedAnonElts(AnaBlockCounts _from, AnaBlockCounts _dest, int _begin, int _end) {
        for (int i = _begin; i < _end; i++) {
            AnonymousElementsFct e_ = _from.anonElts.get(i);
            AnonymousElementsFct cp_ = new AnonymousElementsFct();
            cp_.getReserved().addAllElts(e_.getReserved());
            feed(cp_.getElements(), e_.getElements());
            _dest.anonElts.add(cp_);
        }
    }

    private static void feedAnon(AnaBlockCounts _from, AnaBlockCounts _dest, int _begin, int _end) {
        for (int i = _begin; i < _end; i++) {
            _dest.anonTypes.add(new CustList<AnonymousTypeBlock>(_from.anonTypes.get(i)));
        }
    }

    private static void feedLocal(AnaBlockCounts _from, AnaBlockCounts _dest, int _begin, int _end) {
        for (int i = _begin; i < _end; i++) {
            _dest.localTypes.add(new CustList<RootBlock>(_from.localTypes.get(i)));
        }
    }

    private static void feedCount(CustList<StringMap<Long>> _from, CustList<StringMap<Long>> _dest, int _begin, int _end) {
        for (int i = _begin; i < _end; i++) {
            _dest.add(new StringMap<Long>(_from.get(i)));
        }
    }

    private static void feedLoop(CustList<AnonymousElements> _from, CustList<AnonymousElements> _dest, int _begin, int _end) {
        for (int i = _begin; i < _end; i++) {
            AnonymousElements cp_ = new AnonymousElements();
            feed(cp_, _from.get(i));
            _dest.add(cp_);
        }
    }

    private static void feed(AnonymousElements _dest, AnonymousElements _from) {
        _dest.getLambdas().addAllElts(_from.getLambdas());
        _dest.getSwitches().addAllElts(_from.getSwitches());
        _dest.getTypes().addAllElts(_from.getTypes());
    }

    public static StringList getAllReservedInners(AccessedBlock _acc){
        if (_acc != null) {
            return _acc.getAllReservedInners();
        }
        return new StringList();
    }

    public static StringMap<MappingLocalType> getRefMappings(AccessedBlock _acc){
        if (_acc != null) {
            return _acc.getRefMappings();
        }
        return new StringMap<MappingLocalType>();
    }
    public long getCountOut() {
        return countOut;
    }

    public void setCountOut(long _c) {
        this.countOut = _c;
    }

    public StringMap<Long> getCountAnonType() {
        return countAnonType;
    }

    public StringMap<Long> getCountLocalType() {
        return countLocalType;
    }

    public Longs getCountAnon() {
        return countAnon;
    }

    public CustList<StringMap<Long>> getCountsAnon() {
        return countsAnon;
    }

    public CustList<StringMap<Long>> getCounts() {
        return counts;
    }

    public CustList<CustList<AnonymousTypeBlock>> getAnonTypes() {
        return anonTypes;
    }

    public CustList<CustList<RootBlock>> getLocalTypes() {
        return localTypes;
    }

    public CustList<AnonymousElementsFct> getAnonElts() {
        return anonElts;
    }

    public CustList<AnonymousElements> getAnonFieldsElts() {
        return anonFieldsElts;
    }

    public CustList<AnonymousElements> getAnonTypesElts() {
        return anonTypesElts;
    }
}
