package aiki.beans.effects;

import aiki.fight.moves.effects.EffectCombo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.TreeMap;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SimpleIterable;
import code.util.ints.SortableMap;

public final class ComboDto {

    private TreeMap<StringList, EffectCombo> combos;

    public ComboDto(TreeMap<StringList, EffectCombo> _combos) {
        combos = _combos;
    }

    public SimpleIterable entries() {
        return combos.entries();
    }

    public Iterable<EntryCust<StringList, EffectCombo>> entryList() {
        return combos.entryList();
    }

    public void putAllMap(ListableEntries<StringList, EffectCombo> _m) {
        combos.putAllMap(_m);
    }

    public void putAllTreeMap(SortableMap<StringList, EffectCombo> _m) {
        combos.putAllTreeMap(_m);
    }

    public void setKey(int _i, StringList _k) {
        combos.setKey(_i, _k);
    }

    public Listable<StringList> getKeysNullValue() {
        return combos.getKeysNullValue();
    }

    public void setValue(int _i, EffectCombo _object) {
        combos.setValue(_i, _object);
    }

    public CustList<EffectCombo> getValues(StringList _key) {
        return combos.getValues(_key);
    }

    public EffectCombo getValue(int _index) {
        return combos.getValue(_index);
    }

    public boolean isEmpty() {
        return combos.isEmpty();
    }

    public StringList getKey(int _index) {
        return combos.getKey(_index);
    }

    public int size() {
        return combos.size();
    }

    public Listable<StringList> getKeys() {
        return combos.getKeys();
    }

    public void put(StringList _key, EffectCombo _value) {
        combos.put(_key, _value);
    }

    public EffectCombo getVal(StringList _key) {
        return combos.getVal(_key);
    }

    public void removeKey(StringList _key) {
        combos.removeKey(_key);
    }

    public Listable<EffectCombo> values() {
        return combos.values();
    }

    public StringList firstKey() {
        return combos.firstKey();
    }

    public StringList lastKey() {
        return combos.lastKey();
    }

    public EffectCombo firstValue() {
        return combos.firstValue();
    }

    public EffectCombo lastValue() {
        return combos.lastValue();
    }

    public EntryCust<StringList, EffectCombo> lowerEntry(StringList _key) {
        return combos.lowerEntry(_key);
    }

    public StringList lowerKey(StringList _key) {
        return combos.lowerKey(_key);
    }

    public EntryCust<StringList, EffectCombo> floorEntry(StringList _key) {
        return combos.floorEntry(_key);
    }

    public StringList floorKey(StringList _key) {
        return combos.floorKey(_key);
    }

    public EntryCust<StringList, EffectCombo> higherEntry(StringList _key) {
        return combos.higherEntry(_key);
    }

    public StringList higherKey(StringList _key) {
        return combos.higherKey(_key);
    }

    public EntryCust<StringList, EffectCombo> firstEntry() {
        return combos.firstEntry();
    }

    public EntryCust<StringList, EffectCombo> lastEntry() {
        return combos.lastEntry();
    }

}
