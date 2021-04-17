package code.expressionlanguage.analyze.errors.stds;

import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public class StdErrorList extends CustList<StdWordError> implements Displayable {

    @Override
    public String display() {
        StringList l_ = new StringList();
        for (StdWordError f: this) {
            l_.add(f.display());
        }
        return StringUtil.join(l_, ExportCst.JOIN_ERR);
    }

}
