package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.StringList;

public class WarningList extends CustList<FoundWarningInterpret> {

    private static final String SEP_INFO = "\n\n";

    public WarningList() {
    }

    public String display(Classes _classes) {
        StringList l_ = new StringList();
        for (FoundWarningInterpret f: this) {
            l_.add(f.display(_classes));
        }
        return l_.join(SEP_INFO);
    }

}
