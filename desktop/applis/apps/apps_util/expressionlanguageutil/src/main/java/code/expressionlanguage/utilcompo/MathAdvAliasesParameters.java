package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class MathAdvAliasesParameters {
    private static final String LG_INT_0_LG_INT_0="______1787";
    private static final String RATE_0_RATE_0="______1788";
    private static final String LG_INT_0_LG_INT_PARSE_0="______1789";
    private static final String RATE_0_RATE_PARSE_0="______1790";
    private String aliasRate0RateParse0;
    private String aliasLgInt0LgIntParse0;
    private String aliasRate0Rate0;
    private String aliasLgInt0LgInt0;

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LG_INT_0_LG_INT_0),aliasLgInt0LgInt0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RATE_0_RATE_0),aliasRate0Rate0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LG_INT_0_LG_INT_PARSE_0),aliasLgInt0LgIntParse0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RATE_0_RATE_PARSE_0),aliasRate0RateParse0)));
        return m_;
    }
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        aliasRate0Rate0 = LgNamesContent.get(_util,_cust,_mapping.getVal(RATE_0_RATE_0));
        aliasLgInt0LgInt0 = LgNamesContent.get(_util,_cust,_mapping.getVal(LG_INT_0_LG_INT_0));
        aliasRate0RateParse0 = LgNamesContent.get(_util,_cust,_mapping.getVal(RATE_0_RATE_PARSE_0));
        aliasLgInt0LgIntParse0 = LgNamesContent.get(_util,_cust,_mapping.getVal(LG_INT_0_LG_INT_PARSE_0));
    }
    public static void en(TranslationsFile _en){
        _en.add(LG_INT_0_LG_INT_0,"LgInt0LgInt0=a");
        _en.add(RATE_0_RATE_0,"Rate0Rate0=a");
        _en.add(LG_INT_0_LG_INT_PARSE_0,"LgInt0LgIntParse0=a");
        _en.add(RATE_0_RATE_PARSE_0,"Rate0RateParse0=a");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(LG_INT_0_LG_INT_0,"LgInt0LgInt0=a");
        _fr.add(RATE_0_RATE_0,"Rate0Rate0=a");
        _fr.add(LG_INT_0_LG_INT_PARSE_0,"LgInt0LgIntParse0=a");
        _fr.add(RATE_0_RATE_PARSE_0,"Rate0RateParse0=a");
    }

    public String getAliasRate0RateParse0() {
        return aliasRate0RateParse0;
    }

    public String getAliasLgInt0LgIntParse0() {
        return aliasLgInt0LgIntParse0;
    }

    public String getAliasRate0Rate0() {
        return aliasRate0Rate0;
    }

    public String getAliasLgInt0LgInt0() {
        return aliasLgInt0LgInt0;
    }
}
