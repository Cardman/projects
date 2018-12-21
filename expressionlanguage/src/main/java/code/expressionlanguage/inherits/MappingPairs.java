package code.expressionlanguage.inherits;

import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

public class MappingPairs {

    private StringMap<StringList> paramArgs = new StringMap<StringList>();
    private EqList<Matching> pairsArgParam = new EqList<Matching>();

    public void addParamArg(String _param, String _arg) {
        if (paramArgs.contains(_param)) {
            paramArgs.getVal(_param).add(_arg);
        } else {
            paramArgs.put(_param, new StringList(_arg));
        }
    }
    public boolean containsParamArg(String _param, String _arg) {
        if (!paramArgs.contains(_param)) {
            return false;
        }
        if (!paramArgs.getVal(_param).containsStr(_arg)) {
            return false;
        }
        return true;
    }
    public StringMap<StringList> getParamArgs() {
        return paramArgs;
    }
    public void setParamArgs(StringMap<StringList> _paramArgs) {
        paramArgs = _paramArgs;
    }
    public EqList<Matching> getPairsArgParam() {
        return pairsArgParam;
    }
    public void setPairsArgParam(EqList<Matching> _pairsArgParam) {
        pairsArgParam = _pairsArgParam;
    }

}
