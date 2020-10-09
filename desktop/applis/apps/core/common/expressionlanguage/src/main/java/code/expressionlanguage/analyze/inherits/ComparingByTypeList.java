package code.expressionlanguage.analyze.inherits;

import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparingByTypeList implements Comparing<String> {

    private StringList types;
    public ComparingByTypeList(StringList _types) {
        types = _types;
    }

    @Override
    public int compare(String _o1, String _o2) {
        return StringUtil.indexOf(types,_o1) - StringUtil.indexOf(types,_o2);
    }

}
