package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.*;
import code.util.*;

public final class AnaBlockCounts {
    private final Ints countAnon = new Ints();
    private final CustList<CustList<RootBlock>> localTypes = new CustList<CustList<RootBlock>>();
    private final CustList<CustList<AnonymousTypeBlock>> anonTypes = new CustList<CustList<AnonymousTypeBlock>>();
    private final CustList<AnonymousElementsFct> anonElts = new CustList<AnonymousElementsFct>();
    private final CustList<AnonymousElements> anonFieldsElts = new CustList<AnonymousElements>();
    private final CustList<AnonymousElements> anonTypesElts = new CustList<AnonymousElements>();
    private final CustList<StringMap<Integer>> countsAnon = new CustList<StringMap<Integer>>();
    private final CustList<StringMap<Integer>> counts = new CustList<StringMap<Integer>>();

    public static void addFromTo(AnaBlockCounts _from, AnaBlockCounts _dest) {
        _dest.countAnon.addAllElts(_from.countAnon);
        feedCount(_from.countsAnon, _dest.countsAnon);
        feedCount(_from.counts, _dest.counts);
        for (CustList<RootBlock> e: _from.localTypes) {
            _dest.localTypes.add(new CustList<RootBlock>(e));
        }
        for (CustList<AnonymousTypeBlock> e: _from.anonTypes) {
            _dest.anonTypes.add(new CustList<AnonymousTypeBlock>(e));
        }
        for (AnonymousElementsFct e: _from.anonElts) {
            AnonymousElementsFct cp_ = new AnonymousElementsFct();
            cp_.getReserved().addAllElts(e.getReserved());
            feed(cp_.getElements(), e.getElements());
            _dest.anonElts.add(cp_);
        }
        feedLoop(_from.anonFieldsElts, _dest.anonFieldsElts);
        feedLoop(_from.anonTypesElts, _dest.anonTypesElts);
    }

    private static void feedCount(CustList<StringMap<Integer>> _from, CustList<StringMap<Integer>> _dest) {
        for (StringMap<Integer> e: _from) {
            _dest.add(new StringMap<Integer>(e));
        }
    }

    private static void feedLoop(CustList<AnonymousElements> _from, CustList<AnonymousElements> _dest) {
        for (AnonymousElements e: _from) {
            AnonymousElements cp_ = new AnonymousElements();
            feed(cp_, e);
            _dest.add(cp_);
        }
    }

    private static void feed(AnonymousElements _dest, AnonymousElements _from) {
        _dest.getLambdas().addAllElts(_from.getLambdas());
        _dest.getSwitches().addAllElts(_from.getSwitches());
        _dest.getTypes().addAllElts(_from.getTypes());
    }

    public Ints getCountAnon() {
        return countAnon;
    }

    public CustList<StringMap<Integer>> getCountsAnon() {
        return countsAnon;
    }

    public CustList<StringMap<Integer>> getCounts() {
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
