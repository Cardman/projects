package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class Mapping {

    private AnaClassArgumentMatching arg;
    private AnaClassArgumentMatching param;
    private StringMap<StringList> mapp = new StringMap<StringList>();

    public StringList getCyclic() {
        for (String k: mapp.getKeys()) {
            StringList visitedBounds_ = new StringList();
            StringList visitedBoundsAll_ = new StringList();
            StringList currentBounds_ = new StringList(k);
            while (true) {
                StringList nextBounds_ = new StringList();
                boolean exitSec_ = exitSecCycle(k, visitedBounds_, visitedBoundsAll_, currentBounds_, nextBounds_);
                if (exitSec_) {
                    return visitedBounds_;
                }
                if (nextBounds_.isEmpty()) {
                    break;
                }
                currentBounds_ = nextBounds_;
            }
        }
        return new StringList();
    }

    private boolean exitSecCycle(String _k, StringList _visitedBounds, StringList _visitedBoundsAll, StringList _currentBounds, StringList _nextBounds) {
        boolean exitSec_ = false;
        for (String c: _currentBounds) {
            String var_ = extractVar(c);
            if (mapp.contains(var_)) {
                _visitedBounds.add(var_);
                boolean exit_ = exitCycle(_k, _visitedBoundsAll, _nextBounds, var_);
                if (exit_) {
                    exitSec_ = true;
                    break;
                }
            }
        }
        return exitSec_;
    }

    private boolean exitCycle(String _k, StringList _visitedBoundsAll, StringList _nextBounds, String _var) {
        boolean exit_ = false;
        for (String n: mapp.getVal(_var)) {
            if (n.startsWith(AnaInherits.PREFIX_VAR_TYPE) && StringUtil.quickEq(n.substring(1), _k)) {
                exit_ = true;
                break;
            }
            if (!StringUtil.contains(_visitedBoundsAll, n)) {
                _visitedBoundsAll.add(n);
                _nextBounds.add(n);
            }
        }
        return exit_;
    }

    private String extractVar(String _c) {
        String var_ = _c;
        if (_c.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            var_ = _c.substring(1);
        }
        return var_;
    }

    public static StringList getAllUpperBounds(StringMap<StringList> _mapping, String _className, String _objectClassName) {
        return getAllBounds(false,_mapping,_className,_objectClassName);
    }
    public static StringList getAllBounds(StringMap<StringList> _mapping, String _className, String _objectClassName) {
        return getAllBounds(true,_mapping,_className,_objectClassName);
    }
    public static StringList getAllBounds(boolean _include,StringMap<StringList> _mapping, String _className, String _objectClassName) {
        StringList visitedBounds_ = new StringList();
        StringList varBounds_ = new StringList();
        StringList visitedBoundsAll_ = new StringList();
        StringList currentBounds_ = new StringList(_className);
        while (true) {
            StringList nextBounds_ = nextBounds(_include, _mapping, visitedBounds_, varBounds_, visitedBoundsAll_, currentBounds_);
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

    private static StringList nextBounds(boolean _include, StringMap<StringList> _mapping, StringList _visitedBounds, StringList _varBounds, StringList _visitedBoundsAll, StringList _currentBounds) {
        StringList nextBounds_ = new StringList();
        for (String c: _currentBounds) {
            String var_ = c;
            if (c.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                var_ = c.substring(1);
            }
            if (!_mapping.contains(var_)) {
                _visitedBounds.add(c);
                continue;
            }
            if (_include) {
                StringBuilder str_ = new StringBuilder();
                str_.append(AnaInherits.PREFIX_VAR_TYPE);
                str_.append(var_);
                _varBounds.add(str_.toString());
            }
            for (String n: _mapping.getVal(var_)) {
                if (StringUtil.contains(_visitedBoundsAll, n)) {
                    continue;
                }
                _visitedBoundsAll.add(n);
                nextBounds_.add(n);
            }
        }
        return nextBounds_;
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
                if (!mapp.contains(c)) {
                    continue;
                }
                boolean exit_ = exitInherit(_param, visitedBounds_, nextBounds_, c);
                if (exit_) {
                    return true;
                }
            }
            if (nextBounds_.isEmpty()) {
                return false;
            }
            currentBounds_ = nextBounds_;
        }
    }

    private boolean exitInherit(String _param, StringList _visitedBounds, StringList _nextBounds, String _c) {
        boolean exit_ = false;
        for (String n: mapp.getVal(_c)) {
            if (n.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
                String var_ = n.substring(1);
                if (StringUtil.quickEq(var_, _param)) {
                    exit_ = true;
                    break;
                }
                if (!StringUtil.contains(_visitedBounds, var_)) {
                    _visitedBounds.add(var_);
                    _nextBounds.add(var_);
                }
            }
        }
        return exit_;
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
        return mapp;
    }
    public void setMapping(StringMap<StringList> _mapping) {
        mapp = _mapping;
    }

}
