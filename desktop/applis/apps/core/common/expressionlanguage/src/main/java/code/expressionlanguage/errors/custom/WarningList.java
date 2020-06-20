package code.expressionlanguage.errors.custom;

import code.expressionlanguage.analyze.blocks.Classes;
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
        return StringList.join(l_, SEP_INFO);
    }

}
