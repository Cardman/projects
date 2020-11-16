package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class Mapping {

    private AnaClassArgumentMatching arg;
    private AnaClassArgumentMatching param;
    private StringMap<StringList> mapping = new StringMap<StringList>();

    public StringList getCyclic() {
        for (String k: mapping.getKeys()) {
            StringList visitedBounds_ = new StringList();
            StringList visitedBoundsAll_ = new StringList();
            StringList currentBounds_ = new StringList(k);
            while (true) {
                StringList nextBounds_ = new StringList();
                for (String c: currentBounds_) {
                    String var_ = c;
                    if (c.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
                        var_ = c.substring(1);
                    }
                    if (!mapping.contains(var_)) {
                        continue;
                    }
                    visitedBounds_.add(var_);
                    for (String n: mapping.getVal(var_)) {
                        if (n.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
                            if (StringUtil.quickEq(n.substring(1),k)) {
                                return visitedBounds_;
                            }
                        }
                        if (StringUtil.contains(visitedBoundsAll_, n)) {
                            continue;
                        }
                        visitedBoundsAll_.add(n);
                        nextBounds_.add(n);
                    }
                }
                if (nextBounds_.isEmpty()) {
                    break;
                }
                currentBounds_ = nextBounds_;
            }
        }
        return new StringList();
    }
    public static StringList getAllUpperBounds(StringMap<StringList> _mapping, String _className, String _objectClassName) {
        StringList visitedBounds_ = new StringList();
        StringList visitedBoundsAll_ = new StringList();
        StringList currentBounds_ = new StringList(_className);
        while (true) {
            StringList nextBounds_ = new StringList();
            for (String c: currentBounds_) {
                String var_ = c;
                if (c.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
                    var_ = c.substring(1);
                }
                if (!_mapping.contains(var_)) {
                    visitedBounds_.add(c);
                    continue;
                }
                for (String n: _mapping.getVal(var_)) {
                    if (StringUtil.contains(visitedBoundsAll_, n)) {
                        continue;
                    }
                    visitedBoundsAll_.add(n);
                    nextBounds_.add(n);
                }
            }
            if (nextBounds_.isEmpty()) {
                if (visitedBounds_.isEmpty()) {
                    visitedBounds_.add(_objectClassName);
                }
                return visitedBounds_;
            }
            currentBounds_ = nextBounds_;
        }
    }
    public static StringList getAllBounds(StringMap<StringList> _mapping, String _className, String _objectClassName) {
        StringList visitedBounds_ = new StringList();
        StringList varBounds_ = new StringList();
        StringList visitedBoundsAll_ = new StringList();
        StringList currentBounds_ = new StringList(_className);
        while (true) {
            StringList nextBounds_ = new StringList();
            for (String c: currentBounds_) {
                String var_ = c;
                if (c.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
                    var_ = c.substring(1);
                }
                if (!_mapping.contains(var_)) {
                    visitedBounds_.add(c);
                    continue;
                }
                StringBuilder str_ = new StringBuilder();
                str_.append(AnaTemplates.PREFIX_VAR_TYPE);
                str_.append(var_);
                varBounds_.add(str_.toString());
                for (String n: _mapping.getVal(var_)) {
                    if (StringUtil.contains(visitedBoundsAll_, n)) {
                        continue;
                    }
                    visitedBoundsAll_.add(n);
                    nextBounds_.add(n);
                }
            }
            if (nextBounds_.isEmpty()) {
                if (visitedBounds_.isEmpty()) {
                    visitedBounds_.add(_objectClassName);
                }
                visitedBounds_.addAllElts(varBounds_);
                return visitedBounds_;
            }
            currentBounds_ = nextBounds_;
        }
    }
    public boolean inheritArgParam(String _param, String _arg) {
        StringList visitedBounds_ = new StringList(_arg);
        StringList currentBounds_ = new StringList(_arg);
        if (StringUtil.quickEq(_arg, _param)) {
            return true;
        }
        while (true) {
            StringList nextBounds_ = new StringList();
            for (String c: currentBounds_) {
                if (!mapping.contains(c)) {
                    continue;
                }
                for (String n: mapping.getVal(c)) {
                    if (!n.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
                        continue;
                    }
                    String var_ = n.substring(1);
                    if (StringUtil.contains(visitedBounds_, var_)) {
                        continue;
                    }
                    if (StringUtil.quickEq(var_, _param)) {
                        return true;
                    }
                    visitedBounds_.add(var_);
                    nextBounds_.add(var_);
                }
            }
            if (nextBounds_.isEmpty()) {
                return false;
            }
            currentBounds_ = nextBounds_;
        }
    }
    public AnaClassArgumentMatching getArg() {
        return arg;
    }
    public void setArg(String _arg) {
        arg = new AnaClassArgumentMatching(_arg);
    }
    public void setArg(AnaClassArgumentMatching _arg) {
        arg = _arg;
    }
    public AnaClassArgumentMatching getParam() {
        return param;
    }
    public void setParam(String _param) {
        param = new AnaClassArgumentMatching(_param);
    }
    public void setParam(AnaClassArgumentMatching _param) {
        param = _param;
    }
    public StringMap<StringList> getMapping() {
        return mapping;
    }
    public void setMapping(StringMap<StringList> _mapping) {
        mapping = _mapping;
    }

}
