package code.expressionlanguage.analyze.errors.custom;

import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public class ErrorList extends CustList<FoundErrorInterpret> {

    private static final String SEP_INFO = "\n\n";

    public ErrorList() {
    }

    public String display() {
        StringList str_ = new StringList();
        for (FoundErrorInterpret e: this) {
            str_.add(e.display());
        }
        return StringUtil.join(str_,SEP_INFO);
    }

}
