package code.expressionlanguage.errors.custom;

import code.util.CustList;
import code.util.StringList;

public class ErrorList extends CustList<FoundErrorInterpret> {

    private static final String SEP_INFO = "\n\n";

    public ErrorList() {
    }

    public String display() {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringList str_ = new StringList();
        for (FoundErrorInterpret e: this) {
            str_.add(e.display());
        }
        return StringList.join(str_,SEP_INFO);
    }

}
