package code.util;

import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class StringMapObject extends AbsBasicMap<String,Object> {

    @Override
    protected boolean matchKeys(String _one, String _two) {
        return StringUtil.quickEq(_one,_two);
    }
}
