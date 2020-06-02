package code.expressionlanguage.inherits;

import code.util.StringList;
import code.util.ints.Comparing;

public class ComparingByTypeList implements Comparing<String> {

    private StringList types;
    public ComparingByTypeList(StringList _types) {
        types = _types;
    }

    @Override
    public int compare(String _o1, String _o2) {
        return StringList.indexOf(types,_o1) - StringList.indexOf(types,_o2);
    }

}
