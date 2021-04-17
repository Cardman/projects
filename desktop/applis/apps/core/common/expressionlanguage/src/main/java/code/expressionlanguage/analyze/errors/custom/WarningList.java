package code.expressionlanguage.analyze.errors.custom;

import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public class WarningList extends CustList<FoundWarningInterpret> {

    public String display() {
        StringList l_ = new StringList();
        for (FoundWarningInterpret f: this) {
            l_.add(f.display());
        }
        return StringUtil.join(l_, ExportCst.JOIN_ERR);
    }

}
