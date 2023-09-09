package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpLocalFileExcPoint implements Comparing<ExcPointBlockPair> {
    @Override
    public int compare(ExcPointBlockPair _one, ExcPointBlockPair _two) {
        return StringUtil.compareStrings(_one.getEp().getClName(),_one.getEp().getClName());
    }
}
