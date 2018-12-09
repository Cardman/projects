package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.ints.Listable;

public class WarningList extends CustList<FoundWarningInterpret> {

    private static final String SEP_INFO = "\n\n";

    public WarningList() {
    }

    public WarningList(FoundWarningInterpret... _elements) {
        super(_elements);
    }

    public WarningList(Listable<FoundWarningInterpret> _c) {
        super(_c);
    }

    public String display(Classes _classes) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(first().display(_classes));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(SEP_INFO);
            return_.append(get(i).display(_classes));
        }
        return return_.toString();
    }

}
