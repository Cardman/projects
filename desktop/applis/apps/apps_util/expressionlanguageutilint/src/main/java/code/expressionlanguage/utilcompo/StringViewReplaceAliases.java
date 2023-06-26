package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class StringViewReplaceAliases {
    private static final String ABS_STRING_VIEW="____________2136";
    private static final String ABS_STRING_VIEW_INDEX="____________2137";
    private static final String ABS_STRING_REPLACER="____________2140";
    private static final String ABS_STRING_REPLACER_REPLACE="____________2141";
    private static final String STRING_SEGMENT="____________2146";
    private static final String STRING_SEGMENT_BEGIN="____________2147";
    private static final String STRING_SEGMENT_END="____________2148";

    private String aliasAbsStringView;
    private String aliasAbsStringViewIndex;
    private String aliasAbsStringReplacer;
    private String aliasAbsStringReplacerReplace;

    private String aliasStringSegment;
    private String aliasStringSegmentBegin;
    private String aliasStringSegmentEnd;
    private final StringViewReplaceAliasParameters stringAliasParameters = new StringViewReplaceAliasParameters();

    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content) {
        StringMap<String> stds_ = new StringMap<String>();
        stds_.put(aliasAbsStringView,buildStringView(_keyWords, _content));
        stds_.put(aliasAbsStringReplacer,buildStringReplacer(_keyWords, _content));
        stds_.put(aliasStringSegment,buildStringSegment(_keyWords, _content));
        return stds_;
    }
    private String buildStringView(KeyWords _keyWords, LgNamesContent _content) {
        return _keyWords.getKeyWordPublic()+" "+_keyWords.getKeyWordInterface()+" "+aliasAbsStringView+"{"
         +_keyWords.getKeyWordPublic()+" "+_keyWords.getKeyWordAbstract()+" "+aliasStringSegment+" "+aliasAbsStringViewIndex+"("+_content.getCharSeq().getAliasString()+" "+ stringAliasParameters.getAliasAbsStringView0Index0()+","+_content.getPrimTypes().getAliasPrimInteger()+" "+ stringAliasParameters.getAliasAbsStringView0Index1()+");"
         +"}";
    }

    private String buildStringReplacer(KeyWords _keyWords, LgNamesContent _content) {
        return _keyWords.getKeyWordPublic()+" "+_keyWords.getKeyWordInterface()+" "+aliasAbsStringReplacer+":"+aliasAbsStringView+"{"
         +_keyWords.getKeyWordPublic()+" "+_keyWords.getKeyWordAbstract()+" "+_content.getCharSeq().getAliasString()+" "+aliasAbsStringReplacerReplace+"("+_content.getCharSeq().getAliasString()+" "+ stringAliasParameters.getAliasAbsStringReplacer0Replace0()+","+_content.getPrimTypes().getAliasPrimInteger()+" "+ stringAliasParameters.getAliasAbsStringReplacer0Replace1()+","+_content.getPrimTypes().getAliasPrimInteger()+" "+ stringAliasParameters.getAliasAbsStringReplacer0Replace2()+","+_content.getPrimTypes().getAliasPrimInteger()+" "+ stringAliasParameters.getAliasAbsStringReplacer0Replace3()+");"
         +"}";
    }

    private String buildStringSegment(KeyWords _keyWords, LgNamesContent _content) {
        return _keyWords.getKeyWordPublic()+" @"+_keyWords.getKeyWordInterface()+" "+aliasStringSegment+"{\n"
         +_keyWords.getKeyWordPublic()+" "+_content.getPrimTypes().getAliasPrimInteger()+" "+aliasStringSegmentBegin+";\n"
         +_keyWords.getKeyWordPublic()+" "+_content.getPrimTypes().getAliasPrimInteger()+" "+aliasStringSegmentEnd+";\n"
         +"}";
    }

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasAbsStringView(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_VIEW)));
        setAliasAbsStringViewIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_VIEW_INDEX)));
        setAliasAbsStringReplacer(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_REPLACER)));
        setAliasAbsStringReplacerReplace(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_STRING_REPLACER_REPLACE)));
        setAliasStringSegment(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_SEGMENT)));
        setAliasStringSegmentBegin(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_SEGMENT_BEGIN)));
        setAliasStringSegmentEnd(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_SEGMENT_END)));
        stringAliasParameters.build(_util,_cust,_mapping);
    }
    public static void en(TranslationsFile _en){
        _en.add(ABS_STRING_VIEW,"AbsStringView=$core.AbsStringView");
        _en.add(ABS_STRING_VIEW_INDEX,"AbsStringViewIndex=index");
        _en.add(ABS_STRING_REPLACER,"AbsStringReplacer=$core.AbsStringReplacer");
        _en.add(ABS_STRING_REPLACER_REPLACE,"AbsStringReplacerReplace=replace");
        _en.add(STRING_SEGMENT,"StringSegment=$core.StringSegment");
        _en.add(STRING_SEGMENT_BEGIN,"StringSegmentBegin=begin");
        _en.add(STRING_SEGMENT_END,"StringSegmentEnd=end");
        StringViewReplaceAliasParameters.en(_en);
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(ABS_STRING_VIEW,"AbsStringView=$coeur.AbsChaineVue");
        _fr.add(ABS_STRING_VIEW_INDEX,"AbsStringViewIndex=indice");
        _fr.add(ABS_STRING_REPLACER,"AbsStringReplacer=$coeur.AbsChaineRemplacement");
        _fr.add(ABS_STRING_REPLACER_REPLACE,"AbsStringReplacerReplace=remplace");
        _fr.add(STRING_SEGMENT,"StringSegment=$coeur.ChaineSegment");
        _fr.add(STRING_SEGMENT_BEGIN,"StringSegmentBegin=debut");
        _fr.add(STRING_SEGMENT_END,"StringSegmentEnd=fin");
        StringViewReplaceAliasParameters.fr(_fr);
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> f_ = new StringMap<CustList<KeyValueMemberName>>();
        f_.addEntry(getAliasStringSegment(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(STRING_SEGMENT_BEGIN),getAliasStringSegmentBegin()),
                new KeyValueMemberName(_mapping.getVal(STRING_SEGMENT_END),getAliasStringSegmentEnd())
        ));
        return f_;
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(stringAliasParameters.allTableTypeMethodParamNames(_mapping));
        return m_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
        m_.addEntry(getAliasAbsStringView(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS_STRING_VIEW_INDEX),getAliasAbsStringViewIndex())
        ));
        m_.addEntry(getAliasAbsStringReplacer(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS_STRING_REPLACER),getAliasAbsStringReplacerReplace())
        ));
        return m_;
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ = new StringMap<String>();
        ref_.addEntry(_mapping.getVal(ABS_STRING_VIEW),getAliasAbsStringView());
        ref_.addEntry(_mapping.getVal(ABS_STRING_REPLACER),getAliasAbsStringReplacer());
        ref_.addEntry(_mapping.getVal(STRING_SEGMENT),getAliasStringSegment());
        return ref_;
    }

    public String getAliasAbsStringView() {
        return aliasAbsStringView;
    }

    public void setAliasAbsStringView(String _v) {
        this.aliasAbsStringView = _v;
    }

    public String getAliasAbsStringViewIndex() {
        return aliasAbsStringViewIndex;
    }

    public void setAliasAbsStringViewIndex(String _v) {
        this.aliasAbsStringViewIndex = _v;
    }

    public String getAliasAbsStringReplacer() {
        return aliasAbsStringReplacer;
    }

    public void setAliasAbsStringReplacer(String _v) {
        this.aliasAbsStringReplacer = _v;
    }

    public String getAliasAbsStringReplacerReplace() {
        return aliasAbsStringReplacerReplace;
    }

    public void setAliasAbsStringReplacerReplace(String _v) {
        this.aliasAbsStringReplacerReplace = _v;
    }

    public String getAliasStringSegment() {
        return aliasStringSegment;
    }

    public void setAliasStringSegment(String _v) {
        this.aliasStringSegment = _v;
    }

    public String getAliasStringSegmentBegin() {
        return aliasStringSegmentBegin;
    }

    public void setAliasStringSegmentBegin(String _v) {
        this.aliasStringSegmentBegin = _v;
    }

    public String getAliasStringSegmentEnd() {
        return aliasStringSegmentEnd;
    }

    public void setAliasStringSegmentEnd(String _v) {
        this.aliasStringSegmentEnd = _v;
    }
}
