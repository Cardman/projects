package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamCore {
    private static final String ERROR_0_CURRENT_STACK_0="Error0CurrentStack0";
    private static final String ERROR_0_TO_STRING_METHOD_0="Error0ToStringMethod0";
    private static final String ENUMS_0_NAME_0="Enums0Name0";
    private static final String ENUMS_0_ORDINAL_0="Enums0Ordinal0";
    private static final String RANGE_0_RANGE_0="Range0Range0";
    private static final String RANGE_0_RANGE_1="Range0Range1";
    private static final String RANGE_1_RANGE_0="Range1Range0";
    private static final String RANGE_2_RANGE_0="Range2Range0";
    private static final String RANGE_2_RANGE_1="Range2Range1";
    private static final String RANGE_2_RANGE_2="Range2Range2";
    private static final String RANGE_0_UNLIMITED_STEP_0="Range0UnlimitedStep0";
    private static final String RANGE_0_UNLIMITED_STEP_1="Range0UnlimitedStep1";
    private static final String OBJECTS_UTIL_0_SAME_REF_0="ObjectsUtil0SameRef0";
    private static final String OBJECTS_UTIL_0_SAME_REF_1="ObjectsUtil0SameRef1";
    private static final String OBJECTS_UTIL_0_GET_PARENT_0="ObjectsUtil0GetParent0";
    private static final String OBJECTS_UTIL_0_SET_PARENT_0="ObjectsUtil0SetParent0";
    private static final String OBJECTS_UTIL_0_SET_PARENT_1="ObjectsUtil0SetParent1";
    private static final String OBJECTS_UTIL_0_GET_FCT_0="ObjectsUtil0GetFct0";
    private static final String STRING_UTIL_0_VALUE_OF_METHOD_0="StringUtil0ValueOfMethod0";
    private static final String RESOURCES_0_READ_RESOURCES_0="Resources0ReadResources0";
    private static final String RESOURCES_0_READ_RESOURCES_INDEX_0="Resources0ReadResourcesIndex0";
    private String aliasError0CurrentStack0="a";
    private String aliasError0ToStringMethod0="a";
    private String aliasEnums0Name0="a";
    private String aliasEnums0Ordinal0="a";
    private String aliasRange0Range0="a";
    private String aliasRange0Range1="b";
    private String aliasRange0UnlimitedStep0="a";
    private String aliasRange0UnlimitedStep1="b";
    private String aliasRange1Range0="a";
    private String aliasRange2Range0="a";
    private String aliasRange2Range1="b";
    private String aliasRange2Range2="c";
    private String aliasObjectsUtil0SameRef0="a";
    private String aliasObjectsUtil0SameRef1="b";
    private String aliasObjectsUtil0GetParent0="a";
    private String aliasObjectsUtil0SetParent0="a";
    private String aliasObjectsUtil0SetParent1="b";
    private String aliasObjectsUtil0GetFct0="a";
    private String aliasStringUtil0ValueOfMethod0="a";
    private String aliasResources0ReadResources0="a";
    private String aliasResources0ReadResourcesIndex0="a";

    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasError0CurrentStack0(LgNamesContent.get(_util,_cust, ERROR_0_CURRENT_STACK_0));
        setAliasError0ToStringMethod0(LgNamesContent.get(_util,_cust, ERROR_0_TO_STRING_METHOD_0));
        setAliasEnums0Name0(LgNamesContent.get(_util,_cust, ENUMS_0_NAME_0));
        setAliasEnums0Ordinal0(LgNamesContent.get(_util,_cust, ENUMS_0_ORDINAL_0));
        setAliasRange0Range0(LgNamesContent.get(_util,_cust, RANGE_0_RANGE_0));
        setAliasRange0Range1(LgNamesContent.get(_util,_cust, RANGE_0_RANGE_1));
        setAliasRange0UnlimitedStep0(LgNamesContent.get(_util,_cust, RANGE_0_UNLIMITED_STEP_0));
        setAliasRange0UnlimitedStep1(LgNamesContent.get(_util,_cust, RANGE_0_UNLIMITED_STEP_1));
        setAliasRange1Range0(LgNamesContent.get(_util,_cust, RANGE_1_RANGE_0));
        setAliasRange2Range0(LgNamesContent.get(_util,_cust, RANGE_2_RANGE_0));
        setAliasRange2Range1(LgNamesContent.get(_util,_cust, RANGE_2_RANGE_1));
        setAliasRange2Range2(LgNamesContent.get(_util,_cust, RANGE_2_RANGE_2));
        setAliasObjectsUtil0SameRef0(LgNamesContent.get(_util,_cust, OBJECTS_UTIL_0_SAME_REF_0));
        setAliasObjectsUtil0SameRef1(LgNamesContent.get(_util,_cust, OBJECTS_UTIL_0_SAME_REF_1));
        setAliasObjectsUtil0GetParent0(LgNamesContent.get(_util,_cust, OBJECTS_UTIL_0_GET_PARENT_0));
        setAliasObjectsUtil0SetParent0(LgNamesContent.get(_util,_cust, OBJECTS_UTIL_0_SET_PARENT_0));
        setAliasObjectsUtil0SetParent1(LgNamesContent.get(_util,_cust, OBJECTS_UTIL_0_SET_PARENT_1));
        setAliasObjectsUtil0GetFct0(LgNamesContent.get(_util,_cust, OBJECTS_UTIL_0_GET_FCT_0));
        setAliasStringUtil0ValueOfMethod0(LgNamesContent.get(_util,_cust, STRING_UTIL_0_VALUE_OF_METHOD_0));
        setAliasResources0ReadResources0(LgNamesContent.get(_util,_cust, RESOURCES_0_READ_RESOURCES_0));
        setAliasResources0ReadResourcesIndex0(LgNamesContent.get(_util,_cust, RESOURCES_0_READ_RESOURCES_INDEX_0));
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(ERROR_0_CURRENT_STACK_0, getAliasError0CurrentStack0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(ERROR_0_TO_STRING_METHOD_0, getAliasError0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(ENUMS_0_NAME_0, getAliasEnums0Name0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(ENUMS_0_ORDINAL_0, getAliasEnums0Ordinal0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(RANGE_0_UNLIMITED_STEP_0, getAliasRange0UnlimitedStep0()),new KeyValueMemberName(RANGE_0_UNLIMITED_STEP_1, getAliasRange0UnlimitedStep1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(RANGE_2_RANGE_0, getAliasRange2Range0()),new KeyValueMemberName(RANGE_2_RANGE_1, getAliasRange2Range1()),new KeyValueMemberName(RANGE_2_RANGE_2, getAliasRange2Range2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(RANGE_0_RANGE_0, getAliasRange0Range0()),new KeyValueMemberName(RANGE_0_RANGE_1, getAliasRange0Range1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(RANGE_1_RANGE_0, getAliasRange1Range0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(OBJECTS_UTIL_0_SAME_REF_0, getAliasObjectsUtil0SameRef0()),new KeyValueMemberName(OBJECTS_UTIL_0_SAME_REF_1, getAliasObjectsUtil0SameRef1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(OBJECTS_UTIL_0_GET_PARENT_0, getAliasObjectsUtil0GetParent0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(OBJECTS_UTIL_0_SET_PARENT_0, getAliasObjectsUtil0SetParent0()),new KeyValueMemberName(OBJECTS_UTIL_0_SET_PARENT_1, getAliasObjectsUtil0SetParent1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(OBJECTS_UTIL_0_GET_FCT_0, getAliasObjectsUtil0GetFct0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(STRING_UTIL_0_VALUE_OF_METHOD_0, getAliasStringUtil0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(RESOURCES_0_READ_RESOURCES_0, getAliasResources0ReadResources0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(RESOURCES_0_READ_RESOURCES_INDEX_0, getAliasResources0ReadResourcesIndex0())));
        return map_;
    }
    public String getAliasError0CurrentStack0() {
        return aliasError0CurrentStack0;
    }

    public void setAliasError0CurrentStack0(String _v) {
        this.aliasError0CurrentStack0 =_v;
    }

    public String getAliasError0ToStringMethod0() {
        return aliasError0ToStringMethod0;
    }

    public void setAliasError0ToStringMethod0(String _v) {
        this.aliasError0ToStringMethod0 =_v;
    }

    public String getAliasEnums0Name0() {
        return aliasEnums0Name0;
    }

    public void setAliasEnums0Name0(String _v) {
        this.aliasEnums0Name0 =_v;
    }

    public String getAliasEnums0Ordinal0() {
        return aliasEnums0Ordinal0;
    }

    public void setAliasEnums0Ordinal0(String _v) {
        this.aliasEnums0Ordinal0 =_v;
    }

    public String getAliasRange0Range0() {
        return aliasRange0Range0;
    }

    public void setAliasRange0Range0(String _v) {
        this.aliasRange0Range0 = _v;
    }

    public String getAliasRange0Range1() {
        return aliasRange0Range1;
    }

    public void setAliasRange0Range1(String _v) {
        this.aliasRange0Range1 = _v;
    }

    public String getAliasRange0UnlimitedStep0() {
        return aliasRange0UnlimitedStep0;
    }

    public void setAliasRange0UnlimitedStep0(String _v) {
        this.aliasRange0UnlimitedStep0 = _v;
    }

    public String getAliasRange0UnlimitedStep1() {
        return aliasRange0UnlimitedStep1;
    }

    public void setAliasRange0UnlimitedStep1(String _v) {
        this.aliasRange0UnlimitedStep1 = _v;
    }

    public String getAliasRange1Range0() {
        return aliasRange1Range0;
    }

    public void setAliasRange1Range0(String _v) {
        this.aliasRange1Range0 = _v;
    }

    public String getAliasRange2Range0() {
        return aliasRange2Range0;
    }

    public void setAliasRange2Range0(String _v) {
        this.aliasRange2Range0 = _v;
    }

    public String getAliasRange2Range1() {
        return aliasRange2Range1;
    }

    public void setAliasRange2Range1(String _v) {
        this.aliasRange2Range1 = _v;
    }

    public String getAliasRange2Range2() {
        return aliasRange2Range2;
    }

    public void setAliasRange2Range2(String _v) {
        this.aliasRange2Range2 = _v;
    }

    public String getAliasObjectsUtil0SameRef0() {
        return aliasObjectsUtil0SameRef0;
    }

    public void setAliasObjectsUtil0SameRef0(String _v) {
        this.aliasObjectsUtil0SameRef0 =_v;
    }

    public String getAliasObjectsUtil0SameRef1() {
        return aliasObjectsUtil0SameRef1;
    }

    public void setAliasObjectsUtil0SameRef1(String _v) {
        this.aliasObjectsUtil0SameRef1 =_v;
    }

    public String getAliasObjectsUtil0GetParent0() {
        return aliasObjectsUtil0GetParent0;
    }

    public void setAliasObjectsUtil0GetParent0(String _v) {
        this.aliasObjectsUtil0GetParent0 =_v;
    }

    public String getAliasObjectsUtil0SetParent0() {
        return aliasObjectsUtil0SetParent0;
    }

    public void setAliasObjectsUtil0SetParent0(String _v) {
        this.aliasObjectsUtil0SetParent0 =_v;
    }

    public String getAliasObjectsUtil0SetParent1() {
        return aliasObjectsUtil0SetParent1;
    }

    public void setAliasObjectsUtil0SetParent1(String _v) {
        this.aliasObjectsUtil0SetParent1 =_v;
    }

    public String getAliasObjectsUtil0GetFct0() {
        return aliasObjectsUtil0GetFct0;
    }

    public void setAliasObjectsUtil0GetFct0(String _v) {
        this.aliasObjectsUtil0GetFct0 = _v;
    }

    public String getAliasStringUtil0ValueOfMethod0() {
        return aliasStringUtil0ValueOfMethod0;
    }

    public void setAliasStringUtil0ValueOfMethod0(String _v) {
        this.aliasStringUtil0ValueOfMethod0 =_v;
    }

    public String getAliasResources0ReadResources0() {
        return aliasResources0ReadResources0;
    }

    public void setAliasResources0ReadResources0(String _v) {
        this.aliasResources0ReadResources0 =_v;
    }

    public String getAliasResources0ReadResourcesIndex0() {
        return aliasResources0ReadResourcesIndex0;
    }

    public void setAliasResources0ReadResourcesIndex0(String _v) {
        this.aliasResources0ReadResourcesIndex0 =_v;
    }
}
