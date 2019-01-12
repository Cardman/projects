package code.expressionlanguage.inherits;

import code.util.StringList;
import code.util.ints.Equallable;


public final class Matching implements Equallable<Matching> {

    private String arg;
    private String param;
    private MatchingEnum matchEq = MatchingEnum.EQ;
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
    
    public MatchingEnum getMatchEq() {
        return matchEq;
    }
    public void setMatchEq(MatchingEnum _matchEq) {
        matchEq = _matchEq;
    }
    @Override
    public boolean eq(Matching _g) {
        if (!StringList.quickEq(arg, _g.arg)) {
            return false;
        }
        return StringList.quickEq(param, _g.param);
    }
}
