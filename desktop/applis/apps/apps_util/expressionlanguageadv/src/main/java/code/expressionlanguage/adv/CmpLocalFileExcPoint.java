package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpLocalFileExcPoint implements Comparing<ExcPointBlockKey> {
    @Override
    public int compare(ExcPointBlockKey _one, ExcPointBlockKey _two) {
        return StringUtil.compareStrings(_one.getClName(),_one.getClName());
    }
}
