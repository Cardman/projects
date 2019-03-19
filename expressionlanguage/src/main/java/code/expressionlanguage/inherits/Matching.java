package code.expressionlanguage.inherits;


public final class Matching {

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

}
