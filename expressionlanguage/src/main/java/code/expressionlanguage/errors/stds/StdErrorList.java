package code.expressionlanguage.errors.stds;

import code.util.CustList;
import code.util.ints.Displayable;

public class StdErrorList extends CustList<StdWordError> implements Displayable {

    private static final String SEP_INFO = "\n\n";

    @Override
    public String display() {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(first().display());
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(SEP_INFO);
            return_.append(get(i).display());
        }
        return return_.toString();
    }

}
