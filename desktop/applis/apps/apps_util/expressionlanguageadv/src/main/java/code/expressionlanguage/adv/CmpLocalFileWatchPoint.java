package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpLocalFileWatchPoint implements Comparing<WatchPointBlockPair> {
    @Override
    public int compare(WatchPointBlockPair _one, WatchPointBlockPair _two) {
        return StringUtil.compareStrings(_one.getWp().fieldName(),_two.getWp().fieldName());
    }
}
