package code.expressionlanguage.exec.dbg;

import code.util.EntryCust;

public class EntryCustKeyString implements AbsKeyString<EntryCust<String,Integer>> {
    @Override
    public String keyString(EntryCust<String, Integer> _elt) {
        return _elt.getKey();
    }
}
