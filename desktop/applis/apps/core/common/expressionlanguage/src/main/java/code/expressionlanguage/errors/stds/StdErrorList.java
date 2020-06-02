package code.expressionlanguage.errors.stds;

import code.util.CustList;
import code.util.StringList;
import code.util.ints.Displayable;

public class StdErrorList extends CustList<StdWordError> implements Displayable {

    private static final String SEP_INFO = "\n\n";

    @Override
    public String display() {
        StringList l_ = new StringList();
        for (StdWordError f: this) {
            l_.add(f.display());
        }
        return StringList.join(l_, SEP_INFO);
    }

}
