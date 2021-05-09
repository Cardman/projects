package code.expressionlanguage.common;

import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractInheritProcess {
    public boolean isCorrectExecute(String _a, String _p){
        CustList<Matching> matchs_ = new CustList<Matching>();
        Matching match_ = new Matching();
        match_.setArg(_a);
        match_.setParam(_p);
        matchs_.add(match_);
        boolean okTree_ = true;
        while (true) {
            CustList<Matching> new_ = new CustList<Matching>();
            for (Matching m: matchs_) {
                String a_ = m.getArg();
                String p_ = m.getParam();
                MappingPairs m_ = getExecutingCorrect(a_,p_);
                if (ko(m_,new_)) {
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

    private static boolean ko(MappingPairs _m,CustList<Matching> _new) {
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
                _new.add(n_);
            }
        }
        return koTree_;
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
