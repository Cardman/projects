package code.expressionlanguage.options;

import code.util.CustList;

public final class SuffixedNumbers {
    private final CustList<SuffixedNumber> encodedChars = new CustList<SuffixedNumber>();

    public void addEntry(String _key, char _ch) {
        encodedChars.add(new SuffixedNumber(_key,_ch));
    }

    public SuffixedNumber get(int _index) {
        return encodedChars.get(_index);
    }
}
