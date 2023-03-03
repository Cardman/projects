package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.CustList;
import code.util.StringMap;

public final class LgNamesContent {

    private static final String DEFAULT_PKG = "DefaultPkg";
    private static final String FALSE_STRING = "FalseString";
    private static final String TRUE_STRING = "TrueString";
    private static final String NULL_STRING = "NullString";
    private static final String NULL_COVER_STRING = "NullCoverString";
    private static final String NOT_NULL_COVER_STRING = "NotNullCoverString";
    private static final String STATIC_STRING = "StaticString";
    private static final String STATIC_CALL_STRING = "StaticCallString";
    private static final String INFINITY = "Infinity";
    private static final String EXPONENT = "Exponent";
    private static final String NAN = "Nan";

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
    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setDefaultPkg(get(_util,_cust, DEFAULT_PKG));
        getPredefTypes().build(_util, _cust);
        getCharSeq().build(_util, _cust);
        getCoreNames().build(_util, _cust);
        getMathRef().build(_util, _cust);
        getNbAlias().build(_util, _cust);
        getReflect().build(_util, _cust);
        getStackElt().build(_util, _cust);
        getPrimTypes().build(_util, _cust);
        getDisplayedStrings().setFalseString(get(_util,_cust, FALSE_STRING));
        getDisplayedStrings().setTrueString(get(_util,_cust, TRUE_STRING));
        getDisplayedStrings().setNullString(get(_util,_cust, NULL_STRING));
        getDisplayedStrings().setNullCoverString(get(_util,_cust, NULL_COVER_STRING));
        getDisplayedStrings().setNotNullCoverString(get(_util,_cust, NOT_NULL_COVER_STRING));
        getDisplayedStrings().setStaticCallString(get(_util,_cust, STATIC_CALL_STRING));
        getDisplayedStrings().setStaticString(get(_util,_cust, STATIC_STRING));
        getDisplayedStrings().setInfinity(get(_util,_cust, INFINITY));
        getDisplayedStrings().setNan(get(_util,_cust, NAN));
        getDisplayedStrings().setExponent(get(_util,_cust, EXPONENT));
        getPredefTypes().getParams().build(_util, _cust);
        getCharSeq().getParams().build(_util, _cust);
        getCoreNames().getParams().build(_util, _cust);
        getMathRef().getParams().build(_util, _cust);
        getNbAlias().getParams().build(_util, _cust);
        getReflect().getParams().build(_util, _cust);
    }

    public StringMap<String> allPrimitives() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addAllEntries(getCoreNames().allPrimitives());
        list_.addAllEntries(getPrimTypes().allPrimitives());
        return list_;
    }
    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addAllEntries(getPredefTypes().allRefTypes());
        list_.addAllEntries(getCharSeq().allRefTypes());
        list_.addAllEntries(getCoreNames().allRefTypes());
        list_.addAllEntries(getMathRef().allRefTypes());
        list_.addAllEntries(getNbAlias().allRefTypes());
        list_.addAllEntries(getReflect().allRefTypes());
        list_.addAllEntries(getStackElt().allRefTypes());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addAllEntries(getCoreNames().allTableTypeFieldNames());
        map_.addAllEntries(getNbAlias().allTableTypeFieldNames());
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        return getPredefTypes().allTableTypeVarTypes();
    }
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        return getPredefTypes().allMergeTableTypeMethodNames();
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addAllEntries(getPredefTypes().allTableTypeMethodNames());
        map_.addAllEntries(getCharSeq().allTableTypeMethodNames());
        map_.addAllEntries(getCoreNames().allTableTypeMethodNames());
        map_.addAllEntries(getMathRef().allTableTypeMethodNames());
        map_.addAllEntries(getNbAlias().allTableTypeMethodNames());
        map_.addAllEntries(getReflect().allTableTypeMethodNames());
        map_.addAllEntries(getStackElt().allTableTypeMethodNames());
        return map_;
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.addAllElts(getPredefTypes().getParams().allTableTypeMethodParamNames());
        map_.addAllElts(getCharSeq().getParams().allTableTypeMethodParamNames());
        map_.addAllElts(getCoreNames().getParams().allTableTypeMethodParamNames());
        map_.addAllElts(getMathRef().getParams().allTableTypeMethodParamNames());
        map_.addAllElts(getNbAlias().getParams().allTableTypeMethodParamNames());
        map_.addAllElts(getReflect().getParams().allTableTypeMethodParamNames());
        return map_;
    }
    public static String get(StringMap<String> _util, StringMap<String> _cust,String _key) {
        String val_ = _cust.getVal(_key);
        if (val_ == null) {
            String def_ = _util.getVal(_key);
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
