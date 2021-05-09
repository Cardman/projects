package code.expressionlanguage.common;

import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractInheritProcess {

    public boolean isCorrectExecute(String _a, String _p){
        CustList<Matching> all_ = new CustList<Matching>();
        CustList<Matching> matchs_ = new CustList<Matching>();
        Matching match_ = new Matching();
        match_.setArg(_a);
        match_.setParam(_p);
        matchs_.add(match_);
        all_.add(match_);
        boolean okTree_ = true;
        while (true) {
            CustList<Matching> new_ = new CustList<Matching>();
            for (Matching m: matchs_) {
                String a_ = m.getArg();
                String p_ = m.getParam();
                MappingPairs m_ = getExecutingCorrect(a_,p_);
                if (ko(m_,all_,new_)) {
                    okTree_ = false;
                    break;
                }
            }
            if (new_.isEmpty() || !okTree_) {
                break;
            }
            matchs_ = new_;
        }
        return okTree_;
    }

    protected abstract MappingPairs getExecutingCorrect(String _a, String _p);

    private static boolean ko(MappingPairs _m, CustList<Matching> _all,CustList<Matching> _new) {
        if (_m == null) {
            return true;
        }
        boolean koTree_ = false;
        for (Matching n: _m.getPairsArgParam()) {
            String param_ = n.getParam();
            String arg_ = n.getArg();
            if (!StringUtil.quickEq(param_, arg_)) {
                if (n.getMatchEq() == MatchingEnum.EQ) {
                    koTree_ = true;
                    break;
                }
                Matching n_ = buildMatch(n);
                if (notExist(_all,n_)) {
                    _all.add(n_);
                    _new.add(n_);
                }
            }
        }
        return koTree_;
    }
    private static boolean notExist(CustList<Matching> _all, Matching _n) {
        for (Matching m: _all) {
            if (areTypePairs(m,_n)) {
                return false;
            }
        }
        return true;
    }
    public static boolean areTypePairs(Matching _m, Matching _n) {
        return StringUtil.quickEq(_m.getArg(), _n.getArg())
                &&StringUtil.quickEq(_m.getParam(), _n.getParam());
    }

    private static Matching buildMatch(Matching _n) {
        String param_ = _n.getParam();
        String arg_ = _n.getArg();
        Matching n_ = new Matching();
        if (_n.getMatchEq() == MatchingEnum.SUB) {
            n_.setArg(arg_);
            n_.setParam(param_);
        } else {
            n_.setArg(param_);
            n_.setParam(arg_);
        }
        return n_;
    }
}
