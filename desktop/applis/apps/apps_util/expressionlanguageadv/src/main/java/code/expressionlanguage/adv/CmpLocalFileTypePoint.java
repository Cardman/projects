package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.TypePointBlockPair;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class CmpLocalFileTypePoint implements Comparing<TypePointBlockPair> {
    @Override
    public int compare(TypePointBlockPair _one, TypePointBlockPair _two) {
        return NumberUtil.compareLg(_one.getBp().getOffset(),_two.getBp().getOffset());
    }
}
