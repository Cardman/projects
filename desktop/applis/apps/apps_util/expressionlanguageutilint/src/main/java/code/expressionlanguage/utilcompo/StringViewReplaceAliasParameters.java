package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class StringViewReplaceAliasParameters {
    private static final String ABS_STRING_VIEW_0_INDEX_0="____________2138";
    private static final String ABS_STRING_VIEW_0_INDEX_1="____________2139";
    private static final String ABS_STRING_REPLACER_0_REPLACE_0="____________2142";
    private static final String ABS_STRING_REPLACER_0_REPLACE_1="____________2143";
    private static final String ABS_STRING_REPLACER_0_REPLACE_2="____________2144";
    private static final String ABS_STRING_REPLACER_0_REPLACE_3="____________2145";
    private String aliasAbsStringView0Index0;
    private String aliasAbsStringView0Index1;
    private String aliasAbsStringReplacer0Replace0;
    private String aliasAbsStringReplacer0Replace1;
    private String aliasAbsStringReplacer0Replace2;
    private String aliasAbsStringReplacer0Replace3;
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ABS_STRING_VIEW_0_INDEX_0),aliasAbsStringView0Index0),new KeyValueMemberName(_mapping.getVal(ABS_STRING_VIEW_0_INDEX_1),aliasAbsStringView0Index1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_0),aliasAbsStringReplacer0Replace0),new KeyValueMemberName(_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_1),aliasAbsStringReplacer0Replace1),new KeyValueMemberName(_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_2),aliasAbsStringReplacer0Replace2),new KeyValueMemberName(_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_3),aliasAbsStringReplacer0Replace3)));
        return m_;
    }
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        aliasAbsStringView0Index0 = LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_VIEW_0_INDEX_0));
        aliasAbsStringView0Index1 = LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_VIEW_0_INDEX_1));
        aliasAbsStringReplacer0Replace0 = LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_0));
        aliasAbsStringReplacer0Replace1 = LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_1));
        aliasAbsStringReplacer0Replace2 = LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_2));
        aliasAbsStringReplacer0Replace3 = LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_REPLACER_0_REPLACE_3));
    }
    public static void en(TranslationsFile _en){
        _en.add(ABS_STRING_VIEW_0_INDEX_0,"AbsStringView0Index0=a");
        _en.add(ABS_STRING_VIEW_0_INDEX_1,"AbsStringView0Index1=b");
        _en.add(ABS_STRING_REPLACER_0_REPLACE_0,"AbsStringReplacer0Replace0=a");
        _en.add(ABS_STRING_REPLACER_0_REPLACE_1,"AbsStringReplacer0Replace1=b");
        _en.add(ABS_STRING_REPLACER_0_REPLACE_2,"AbsStringReplacer0Replace2=c");
        _en.add(ABS_STRING_REPLACER_0_REPLACE_3,"AbsStringReplacer0Replace3=d");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(ABS_STRING_VIEW_0_INDEX_0,"AbsStringView0Index0=a");
        _fr.add(ABS_STRING_VIEW_0_INDEX_1,"AbsStringView0Index1=b");
        _fr.add(ABS_STRING_REPLACER_0_REPLACE_0,"AbsStringReplacer0Replace0=a");
        _fr.add(ABS_STRING_REPLACER_0_REPLACE_1,"AbsStringReplacer0Replace1=b");
        _fr.add(ABS_STRING_REPLACER_0_REPLACE_2,"AbsStringReplacer0Replace2=c");
        _fr.add(ABS_STRING_REPLACER_0_REPLACE_3,"AbsStringReplacer0Replace3=d");
    }

    public String getAliasAbsStringView0Index0() {
        return aliasAbsStringView0Index0;
    }

    public String getAliasAbsStringView0Index1() {
        return aliasAbsStringView0Index1;
    }

    public String getAliasAbsStringReplacer0Replace0() {
        return aliasAbsStringReplacer0Replace0;
    }

    public String getAliasAbsStringReplacer0Replace1() {
        return aliasAbsStringReplacer0Replace1;
    }

    public String getAliasAbsStringReplacer0Replace2() {
        return aliasAbsStringReplacer0Replace2;
    }

    public String getAliasAbsStringReplacer0Replace3() {
        return aliasAbsStringReplacer0Replace3;
    }
}
