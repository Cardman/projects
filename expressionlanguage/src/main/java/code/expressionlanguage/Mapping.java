package code.expressionlanguage;

import code.util.StringList;
import code.util.StringMap;

public class Mapping {

    private String arg;
    private String param;
    private StringMap<StringList> mapping = new StringMap<StringList>();

    public StringList getAllUpperBounds(String _className) {
        StringList visitedBounds_ = new StringList();
        StringList currentBounds_ = new StringList(_className);
        while (true) {
            StringList nextBounds_ = new StringList();
            for (String c: currentBounds_) {
                String var_ = c;
                if (c.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    var_ = c.substring(1);
                }
                if (!mapping.contains(var_)) {
                    visitedBounds_.add(c);
                    continue;
                }
                for (String n: mapping.getVal(var_)) {
                    nextBounds_.add(n);
                }
            }
            if (nextBounds_.isEmpty()) {
                return visitedBounds_;
            }
            currentBounds_ = nextBounds_;
        }
    }
    public boolean inheritArgParam(String _param, String _arg) {
        StringList visitedBounds_ = new StringList(_arg);
        StringList currentBounds_ = new StringList(_arg);
        if (StringList.quickEq(_arg, _param)) {
            return true;
        }
        while (true) {
            StringList nextBounds_ = new StringList();
            for (String c: currentBounds_) {
                if (!mapping.contains(c)) {
                    continue;
                }
                for (String n: mapping.getVal(c)) {
                    if (!n.startsWith(Templates.PREFIX_VAR_TYPE)) {
                        continue;
                    }
                    String var_ = n.substring(1);
                    if (visitedBounds_.containsStr(var_)) {
                        continue;
                    }
                    if (StringList.quickEq(var_, _param)) {
                        return true;
                    }
                    visitedBounds_.add(var_);
                    nextBounds_.add(var_);
                }
            }
            if (nextBounds_.isEmpty()) {
                return false;
            }
            nextBounds_ = currentBounds_;
        }
    }
    public String getArg() {
        return arg;
    }
    public void setArg(String _arg) {
        arg = _arg;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String _param) {
        param = _param;
    }
    public StringMap<StringList> getMapping() {
        return mapping;
    }
    public void setMapping(StringMap<StringList> _mapping) {
        mapping = _mapping;
    }

}
