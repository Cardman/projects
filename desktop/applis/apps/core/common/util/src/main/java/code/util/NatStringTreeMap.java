package code.util;

import code.util.core.StringUtil;

/**
    @author Cardman
*/
public final class NatStringTreeMap<V> extends AbsBasicTreeMap<String, V> {

    @Override
    protected int compare(String _one, String _two) {
        return StringUtil.compareStrings(_one,_two);
    }
}
