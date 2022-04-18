package code.maths.litteralcom;

import code.util.CustList;
import code.util.core.IndexConstants;

public final class StrTypes {
    private final CustList<IndexStrPart> values = new CustList<IndexStrPart>();

    public static void loopArgs(String _string, StrTypes _ops, StrTypes _values) {
        int endValuePart_ = _ops.firstKey();
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = _ops.size();
        while (i_ < nbKeys_) {
            int beginValuePart_ = endValuePart_ + _ops.getValue(i_ - 1).length();
            endValuePart_ = _ops.getKey(i_);
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            _values.addEntry(beginValuePart_, str_);
            i_++;
        }
    }

    public static void addNotEmpty(String _string, StrTypes _ops, StrTypes _values) {
        int i_ = IndexConstants.SECOND_INDEX;
        int endValuePart_ = _ops.firstKey();
        int beginValuePart_ = endValuePart_ + _ops.getValue(0).length();
        endValuePart_ = _ops.getKey(i_);
        String str_ = _string.substring(beginValuePart_, endValuePart_);
        addNotEmpty(beginValuePart_, str_, _values);
    }
    public static void addNotEmpty(int _off, String _string, StrTypes _values) {
        if (!_string.trim().isEmpty()) {
            _values.addEntry(_off, _string);
        }
    }
    public static String firstPartNotUnary(String _string, StrTypes _ops, StrTypes _values) {
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = _ops.firstKey();
        String str_ = _string.substring(beginValuePart_, endValuePart_);
        _values.addEntry(beginValuePart_, str_);
        return str_;
    }

    public static void lastPart(String _string, StrTypes _ops, StrTypes _values) {
        int beginValuePart_ = _ops.lastKey() + _ops.lastValue().length();
        String str_ = _string.substring(beginValuePart_);
        _values.addEntry(beginValuePart_, str_);
    }
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
