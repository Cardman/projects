package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamPredefinedTypes {
    private static final String SEED_GENERATOR_0_GET_0="______1796";
    private String aliasSeedGenerator0Get0 = "a";
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasSeedGenerator0Get0(LgNamesContent.get(_util,_cust,_mapping.getVal(SEED_GENERATOR_0_GET_0)));
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(SEED_GENERATOR_0_GET_0,"SeedGenerator0Get0");
    }
    public static void en(TranslationsFile _en){
        _en.add(SEED_GENERATOR_0_GET_0,"SeedGenerator0Get0=a");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(SEED_GENERATOR_0_GET_0,"SeedGenerator0Get0=a");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(_mapping.getVal(SEED_GENERATOR_0_GET_0),getAliasSeedGenerator0Get0())
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
