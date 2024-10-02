package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.MessagesCdmBase;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class LgNamesContent {

    private static final String DEFAULT_PKG = "2";
    private static final String FALSE_STRING = "_________1869";
    private static final String TRUE_STRING = "_________1870";
    private static final String NULL_STRING = "_________1871";
    private static final String NULL_COVER_STRING = "________1834";
    private static final String NOT_NULL_COVER_STRING = "________1835";
    private static final String STATIC_STRING = "_________1875";
    private static final String STATIC_CALL_STRING = "_________1876";
    private static final String INFINITY = "_________1872";
    private static final String EXPONENT = "_________1873";
    private static final String NAN = "_________1874";
    private static final String UNICODE = "_________1874_";
    private static final String ALPHA = "_________1874_";
    private static final String ALPHA_HEX = "_________1874__";

    private final StringMap<StandardType> standards = new StringMap<StandardType>();

    private final AliasCore coreNames = new AliasCore();

    private final AliasCharSequenceType charSeq = new AliasCharSequenceType();
    private final AliasReflection reflect = new AliasReflection();
    private final AliasStackTraceElementType stackElt = new AliasStackTraceElementType();
    private final AliasNumberType nbAlias = new AliasNumberType();
    private final AliasMathType mathRef = new AliasMathType();
    private final PrimitiveTypes primTypes = new PrimitiveTypes();
    private final AliasPredefinedTypes predefTypes = new AliasPredefinedTypes();
    private final DisplayedStrings displayedStrings = new DisplayedStrings();
    private String defaultPkg = "";
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setDefaultPkg(get(_util,_cust, _mapping.getVal(DEFAULT_PKG)));
        getPredefTypes().build(_util, _cust,_mapping);
        getCharSeq().build(_util, _cust,_mapping);
        getCoreNames().build(_util, _cust,_mapping);
        getMathRef().build(_util, _cust,_mapping);
        getNbAlias().build(_util, _cust,_mapping);
        getReflect().build(_util, _cust,_mapping);
        getStackElt().build(_util, _cust,_mapping);
        getPrimTypes().build(_util, _cust,_mapping);
        getDisplayedStrings().setFalseString(get(_util,_cust, _mapping.getVal(FALSE_STRING)));
        getDisplayedStrings().setTrueString(get(_util,_cust, _mapping.getVal(TRUE_STRING)));
        getDisplayedStrings().setNullString(get(_util,_cust, _mapping.getVal(NULL_STRING)));
        getDisplayedStrings().setNullCoverString(get(_util,_cust, _mapping.getVal(NULL_COVER_STRING)));
        getDisplayedStrings().setNotNullCoverString(get(_util,_cust, _mapping.getVal(NOT_NULL_COVER_STRING)));
        getDisplayedStrings().setStaticCallString(get(_util,_cust, _mapping.getVal(STATIC_CALL_STRING)));
        getDisplayedStrings().setStaticString(get(_util,_cust, _mapping.getVal(STATIC_STRING)));
        getDisplayedStrings().setInfinity(get(_util,_cust, _mapping.getVal(INFINITY)));
        getDisplayedStrings().setNan(get(_util,_cust, _mapping.getVal(NAN)));
        getDisplayedStrings().setExponent(get(_util,_cust, _mapping.getVal(EXPONENT)));
        getDisplayedStrings().setAlpha(DisplayedStrings.patchAlpha(get(_util,_cust, _mapping.getVal(ALPHA))));
        getDisplayedStrings().setAlphaHex(DisplayedStrings.patchAlphaHex(get(_util,_cust, _mapping.getVal(ALPHA_HEX)), MessagesCdmBase.DEF_ALPHA_HEX));
        getPredefTypes().getParams().build(_util, _cust,_mapping);
        getCharSeq().getParams().build(_util, _cust,_mapping);
        getCoreNames().getParams().build(_util, _cust,_mapping);
        getMathRef().getParams().build(_util, _cust,_mapping);
        getNbAlias().getParams().build(_util, _cust,_mapping);
        getReflect().getParams().build(_util, _cust,_mapping);
    }
    public static void en(TranslationsFile _en) {
        _en.add(DEFAULT_PKG,"DefaultPkg=$core");
        _en.add(FALSE_STRING,"FalseString=false");
        _en.add(TRUE_STRING,"TrueString=true");
        _en.add(INFINITY,"Infinity=Infinity");
        _en.add(NAN,"Nan=Nan");
        _en.add(UNICODE,"Unicode=u");
        _en.add(EXPONENT,"Exponent=E");
        _en.add(NULL_STRING,"NullString=");
        _en.add(NULL_COVER_STRING,"NullCoverString=null");
        _en.add(NOT_NULL_COVER_STRING,"NotNullCoverString=not null");
        _en.add(STATIC_STRING,"StaticString=static");
        _en.add(STATIC_CALL_STRING,"StaticCallString=staticCall");
        _en.add(ALPHA,"Alpha="+ MessagesCdmBase.DEF_ALPHA);
        _en.add(ALPHA_HEX,"AlphaHex="+ MessagesCdmBase.DEF_ALPHA_HEX);
        AliasPredefinedTypes.en(_en);
        AliasParamPredefinedTypes.en(_en);
        AliasCharSequenceType.en(_en);
        AliasParamCharSequence.en(_en);
        AliasCore.en(_en);
        AliasParamCore.en(_en);
        AliasMathType.en(_en);
        AliasParamMath.en(_en);
        AliasNumberType.en(_en);
        AliasParamNumber.en(_en);
        AliasReflection.en(_en);
        AliasParamReflection.en(_en);
        AliasStackTraceElementType.en(_en);
        PrimitiveTypes.en(_en);
    }
    public static void fr(TranslationsFile _fr) {
        _fr.add(DEFAULT_PKG,"DefaultPkg=$coeur");
        _fr.add(FALSE_STRING,"FalseString=faux");
        _fr.add(TRUE_STRING,"TrueString=vrai");
        _fr.add(INFINITY,"Infinity=Infini");
        _fr.add(NAN,"Nan=Pun");
        _fr.add(UNICODE,"Unicode=u");
        _fr.add(EXPONENT,"Exponent=E");
        _fr.add(NULL_STRING,"NullString=");
        _fr.add(NULL_COVER_STRING,"NullCoverString=nul");
        _fr.add(NOT_NULL_COVER_STRING,"NotNullCoverString=non nul");
        _fr.add(STATIC_STRING,"StaticString=static");
        _fr.add(STATIC_CALL_STRING,"StaticCallString=staticAppel");
        _fr.add(ALPHA,"Alpha="+ MessagesCdmBase.DEF_ALPHA);
        _fr.add(ALPHA_HEX,"AlphaHex="+ MessagesCdmBase.DEF_ALPHA_HEX);
        AliasPredefinedTypes.fr(_fr);
        AliasParamPredefinedTypes.fr(_fr);
        AliasCharSequenceType.fr(_fr);
        AliasParamCharSequence.fr(_fr);
        AliasCore.fr(_fr);
        AliasParamCore.fr(_fr);
        AliasMathType.fr(_fr);
        AliasParamMath.fr(_fr);
        AliasNumberType.fr(_fr);
        AliasParamNumber.fr(_fr);
        AliasReflection.fr(_fr);
        AliasParamReflection.fr(_fr);
        AliasStackTraceElementType.fr(_fr);
        PrimitiveTypes.fr(_fr);
    }
    public static StringMap<String> mapping() {
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(DEFAULT_PKG,"DefaultPkg");
        m_.addEntry(FALSE_STRING,"FalseString");
        m_.addEntry(TRUE_STRING,"TrueString");
        m_.addEntry(INFINITY,"Infinity");
        m_.addEntry(NAN,"Nan");
        m_.addEntry(UNICODE,"Unicode");
        m_.addEntry(EXPONENT,"Exponent");
        m_.addEntry(NULL_STRING,"NullString");
        m_.addEntry(NULL_COVER_STRING,"NullCoverString");
        m_.addEntry(NOT_NULL_COVER_STRING,"NotNullCoverString");
        m_.addEntry(STATIC_STRING,"StaticString");
        m_.addEntry(STATIC_CALL_STRING,"StaticCallString");
        m_.addEntry(ALPHA,"Alpha");
        m_.addEntry(ALPHA_HEX,"AlphaHex");
        AliasPredefinedTypes.mapping(m_);
        AliasParamPredefinedTypes.mapping(m_);
        AliasCharSequenceType.mapping(m_);
        AliasParamCharSequence.mapping(m_);
        AliasCore.mapping(m_);
        AliasParamCore.mapping(m_);
        AliasMathType.mapping(m_);
        AliasParamMath.mapping(m_);
        AliasNumberType.mapping(m_);
        AliasParamNumber.mapping(m_);
        AliasReflection.mapping(m_);
        AliasParamReflection.mapping(m_);
        AliasStackTraceElementType.mapping(m_);
        PrimitiveTypes.mapping(m_);
        return m_;
    }
    public StringMap<String> allPrimitives(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addAllEntries(getCoreNames().allPrimitives(_mapping));
        list_.addAllEntries(getPrimTypes().allPrimitives(_mapping));
        return list_;
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addAllEntries(getPredefTypes().allRefTypes(_mapping));
        list_.addAllEntries(getCharSeq().allRefTypes(_mapping));
        list_.addAllEntries(getCoreNames().allRefTypes(_mapping));
        list_.addAllEntries(getMathRef().allRefTypes(_mapping));
        list_.addAllEntries(getNbAlias().allRefTypes(_mapping));
        list_.addAllEntries(getReflect().allRefTypes(_mapping));
        list_.addAllEntries(getStackElt().allRefTypes(_mapping));
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addAllEntries(getCoreNames().allTableTypeFieldNames(_mapping));
        map_.addAllEntries(getNbAlias().allTableTypeFieldNames(_mapping));
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(StringMap<String> _mapping) {
        return getPredefTypes().allTableTypeVarTypes(_mapping);
    }
    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping) {
        return getPredefTypes().allMergeTableTypeMethodNames(_mapping);
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addAllEntries(getPredefTypes().allTableTypeMethodNames(_mapping));
        map_.addAllEntries(getCharSeq().allTableTypeMethodNames(_mapping));
        map_.addAllEntries(getCoreNames().allTableTypeMethodNames(_mapping));
        map_.addAllEntries(getMathRef().allTableTypeMethodNames(_mapping));
        map_.addAllEntries(getNbAlias().allTableTypeMethodNames(_mapping));
        map_.addAllEntries(getReflect().allTableTypeMethodNames(_mapping));
        map_.addAllEntries(getStackElt().allTableTypeMethodNames(_mapping));
        return map_;
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.addAllElts(getPredefTypes().getParams().allTableTypeMethodParamNames(_mapping));
        map_.addAllElts(getCharSeq().getParams().allTableTypeMethodParamNames(_mapping));
        map_.addAllElts(getCoreNames().getParams().allTableTypeMethodParamNames(_mapping));
        map_.addAllElts(getMathRef().getParams().allTableTypeMethodParamNames(_mapping));
        map_.addAllElts(getNbAlias().getParams().allTableTypeMethodParamNames(_mapping));
        map_.addAllElts(getReflect().getParams().allTableTypeMethodParamNames(_mapping));
        return map_;
    }
    public static String get(StringMap<String> _util, StringMap<String> _cust,String _key) {
        String k_ = StringUtil.nullToEmpty(_key);
        String val_ = _cust.getVal(k_);
        if (val_ == null) {
            String def_ = _util.getVal(k_);
            if (def_ == null) {
                return "";
            }
            return def_;
        }
        return val_;
    }
    public PrimitiveTypes getPrimTypes() {
        return primTypes;
    }

    public DisplayedStrings getDisplayedStrings() {
        return displayedStrings;
    }

    public AliasNumberType getNbAlias() {
        return nbAlias;
    }

    public AliasCore getCoreNames() {
        return coreNames;
    }

    public AliasCharSequenceType getCharSeq() {
        return charSeq;
    }

    public AliasPredefinedTypes getPredefTypes() {
        return predefTypes;
    }

    public AliasMathType getMathRef() {
        return mathRef;
    }

    public AliasReflection getReflect() {
        return reflect;
    }

    public AliasStackTraceElementType getStackElt() {
        return stackElt;
    }

    public String getDefaultPkg() {
        return defaultPkg;
    }

    public void setDefaultPkg(String _defaultPkg) {
        this.defaultPkg = _defaultPkg;
    }

    public StringMap<StandardType> getStandards() {
        return standards;
    }
}
