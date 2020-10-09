package code.formathtml.classes;

import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NatTreeMapStringInteger extends AbsBasicMap<String,Integer> {

    @Override
    protected boolean matchKeys(String _one, String _two) {
        return StringUtil.quickEq(_one,_two);
    }


}
