package code.expressionlanguage.errors.custom;

import code.util.CustList;
import code.util.StringList;

public class WarningList extends CustList<FoundWarningInterpret> {

    private static final String SEP_INFO = "\n\n";

    public WarningList() {
    }

    public String display() {
        StringList l_ = new StringList();
        for (FoundWarningInterpret f: this) {
            l_.add(f.display());
        }
        return StringList.join(l_, SEP_INFO);
    }

}
