package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamPredefinedTypes {
    private static final String SEED_GENERATOR_0_GET_0 = "SeedGenerator0Get0";
    private String aliasSeedGenerator0Get0 = "a";
    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasSeedGenerator0Get0(LgNamesContent.get(_util,_cust,SEED_GENERATOR_0_GET_0));
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(SEED_GENERATOR_0_GET_0,getAliasSeedGenerator0Get0())
                )
        );
        return map_;
    }
    public String getAliasSeedGenerator0Get0() {
        return aliasSeedGenerator0Get0;
    }

    public void setAliasSeedGenerator0Get0(String _v) {
        this.aliasSeedGenerator0Get0 = _v;
    }
}
