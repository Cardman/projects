package code.expressionlanguage.inherits;

import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

public class MappingPairs {

    private StringMap<StringList> paramArgs = new StringMap<StringList>();
    private EqList<Matching> pairsArgParam = new EqList<Matching>();

    public EqList<Matching> getPairsArgParam() {
        return pairsArgParam;
    }
    public void setPairsArgParam(EqList<Matching> _pairsArgParam) {
        pairsArgParam = _pairsArgParam;
    }

}
