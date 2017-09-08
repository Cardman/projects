package code.expressionlanguage;

import code.util.StringList;
import code.util.ints.Equallable;


public final class Matching implements Equallable<Matching> {

    private String arg;
    private String param;
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
    @Override
    public boolean eq(Matching _g) {
        if (!StringList.quickEq(arg, _g.arg)) {
            return false;
        }
        if (!StringList.quickEq(param, _g.param)) {
            return false;
        }
        return true;
    }
}
