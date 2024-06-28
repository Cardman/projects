package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class CmpLocalFileBreakPoint implements Comparing<BreakPointBlockPair> {
    @Override
    public int compare(BreakPointBlockPair _one, BreakPointBlockPair _two) {
        return NumberUtil.compareLg(_one.getBp().getOffset(),_two.getBp().getOffset());
    }
}
