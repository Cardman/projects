package code.maths.litteralcom;

import code.util.CustList;

public final class StrTypes {
    private final CustList<IndexStrPart> values = new CustList<IndexStrPart>();

    public static int offset(StrTypes _off, int _index) {
        if (_off.getValues().isValidIndex(_index)) {
            return _off.getKey(_index);
        }
        return 0;
    }

    public static String value(StrTypes _off, int _index) {
        if (_off.getValues().isValidIndex(_index)) {
            return _off.getValue(_index);
        }
        return "";
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }
    public int size() {
        return values.size();
    }
    public void clear() {
        values.clear();
    }
    public int firstKey() {
        return values.first().getIndex();
    }

    public int lastKey() {
        return values.last().getIndex();
    }

    public String firstValue() {
        return values.first().getPart();
    }

    public String lastValue() {
        return values.last().getPart();
    }

    public int getKey(int _i) {
        return values.get(_i).getIndex();
    }
    public String getValue(int _i) {
        return values.get(_i).getPart();
    }

    public void remove(int _i) {
        values.remove(_i);
    }
    public CustList<String> values() {
        CustList<String> s_ = new CustList<String>();
        for (IndexStrPart e: getValues()) {
            s_.add(e.getPart());
        }
        return s_;
    }
    public void addAllEntries(StrTypes _m) {
        values.addAllElts(_m.values);
    }
    public void addEntry(int _k, String _v) {
        values.add(new IndexStrPart(_k, _v));
    }
    public CustList<IndexStrPart> getValues() {
        return values;
    }
}
