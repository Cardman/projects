package code.expressionlanguage.analyze.errors.custom;

import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public class ErrorList extends CustList<FoundErrorInterpret> {

    public String display() {
        StringList str_ = new StringList();
        for (FoundErrorInterpret e: this) {
            str_.add(e.display());
        }
        return StringUtil.join(str_, ExportCst.JOIN_ERR);
    }

}
