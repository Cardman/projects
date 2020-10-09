package code.expressionlanguage.analyze.errors.custom;

import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public class WarningList extends CustList<FoundWarningInterpret> {

    private static final String SEP_INFO = "\n\n";

    public WarningList() {
    }

    public String display() {
        StringList l_ = new StringList();
        for (FoundWarningInterpret f: this) {
            l_.add(f.display());
        }
        return StringUtil.join(l_, SEP_INFO);
    }

}
