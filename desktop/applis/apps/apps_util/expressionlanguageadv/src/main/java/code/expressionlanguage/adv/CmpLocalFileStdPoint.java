package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpLocalFileStdPoint implements Comparing<StdMethodPointBlockPair> {
    @Override
    public int compare(StdMethodPointBlockPair _one, StdMethodPointBlockPair _two) {
        return StringUtil.compareStrings(_one.getSm().keyStr(),_one.getSm().keyStr());
    }
}
