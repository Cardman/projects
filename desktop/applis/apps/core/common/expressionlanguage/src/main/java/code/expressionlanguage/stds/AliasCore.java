package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasCore {
    private static final String VOID="1";
    private static final String RANGE="712";
    private static final String OBJECTS_UTIL="_________1849";
    private static final String STRING_UTIL="_______1804";
    private static final String RESOURCES="_______1805";
    private static final String ERROR_INIT_CLASS="__________1882";
    private static final String ENUMS="_________1854";
    private static final String BAD_ENCODE="__________1883";
    private static final String BAD_INDEX="__________1884";
    private static final String BAD_ARG_NUMBER="__________1885";
    private static final String ABSTRACT_TYPE_ERR="__________1886";
    private static final String ILLEGAL_TYPE="__________1887";
    private static final String NON_INVOKABLE="__________1888";
    private static final String ILLEGAL_ARG="__________1889";
    private static final String DIVISION_ZERO="__________1890";
    private static final String STORE="__________1891";
    private static final String CAST_TYPE="__________1892";
    private static final String BAD_SIZE="__________1893";
    private static final String SOF="__________1894";
    private static final String NULL_PE="__________1895";
    private static final String NB_FORMAT="__________1896";
    private static final String ERROR="__________1897";
    private static final String OBJECT="4";
    private static final String CLONE="711";
    private static final String ERROR_CURRENT_STACK="__________1898";
    private static final String ERROR_TO_STRING="__________1899";
    private static final String GET_MESSAGE="__________1902";
    private static final String GET_CAUSE="__________1903";
    private static final String RANGE_LOWER="713";
    private static final String RANGE_UPPER="714";
    private static final String RANGE_STEP="715";
    private static final String RANGE_UNLIMITED_STEP="716";
    private static final String RANGE_UNLIMITED="717";
    private static final String SAME_REF="_________1850";
    private static final String GET_PARENT="_________1851";
    private static final String SET_PARENT="_________1852";
    private static final String GET_FCT="_________1853";
    private static final String STRING_UTIL_VALUE_OF="_______1806";
    private static final String READ_RESOURCES_NAMES="_______1807";
    private static final String READ_RESOURCES_NAMES_LENGTH="_______1808";
    private static final String READ_RESOURCES="_______1809";
    private static final String READ_RESOURCES_INDEX="_______1810";
    private static final String NAME="_________1855";
    private static final String ORDINAL="_________1856";
    private static final String ARRAY_LENGTH="710";
    private static final String EMPTY_STRING = "";
    private String aliasObject;

    private String aliasVoid;

    private String aliasClone;

    private String aliasEnums;
    private String aliasError;
    private String aliasErrorCurrentStack;
    private String aliasErrorToString;
    private String aliasGetCause;
    private String aliasGetMessage;
    private String aliasBadSize;
    private String aliasDivisionZero;
    private String aliasCastType;
    private String aliasStore;
    private String aliasNullPe;
    private String aliasNbFormat;
    private String aliasBadEncode;
    private String aliasBadIndex;
    private String aliasBadArgNumber;
    private String aliasAbstractTypeErr;
    private String aliasIllegalType;
    private String aliasNonInvokable;
    private String aliasIllegalArg;
    private String aliasSof;

    private String aliasName;
    private String aliasOrdinal;
    private String aliasErrorInitClass;
    private String aliasRange;
    private String aliasRangeLower;
    private String aliasRangeUpper;
    private String aliasRangeStep;
    private String aliasRangeUnlimited;
    private String aliasRangeUnlimitedStep;
    private String aliasObjectsUtil;
    private String aliasSameRef;
    private String aliasGetParent;
    private String aliasSetParent;
    private String aliasGetFct;
    private String aliasReadResourcesNames;
    private String aliasReadResources;
    private String aliasReadResourcesNamesLength;
    private String aliasReadResourcesIndex;
    private String aliasResources;
    private String aliasStringUtil;
    private String aliasStringUtilValueOf;
    private String aliasArrayLength;
    private final AliasParamCore params = new AliasParamCore();
    private StandardClass objType;
    private StandardClass errType;

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasBadEncode(LgNamesContent.get(_util,_cust,_mapping.getVal(BAD_ENCODE)));
        setAliasDivisionZero(LgNamesContent.get(_util,_cust,_mapping.getVal(DIVISION_ZERO)));
        setAliasGetMessage(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_MESSAGE)));
        setAliasBadSize(LgNamesContent.get(_util,_cust,_mapping.getVal(BAD_SIZE)));
        setAliasNullPe(LgNamesContent.get(_util,_cust,_mapping.getVal(NULL_PE)));
        setAliasObject(LgNamesContent.get(_util,_cust,_mapping.getVal(OBJECT)));
        setAliasCastType(LgNamesContent.get(_util,_cust,_mapping.getVal(CAST_TYPE)));
        setAliasStore(LgNamesContent.get(_util,_cust,_mapping.getVal(STORE)));
        setAliasError(LgNamesContent.get(_util,_cust,_mapping.getVal(ERROR)));
        setAliasErrorCurrentStack(LgNamesContent.get(_util,_cust,_mapping.getVal(ERROR_CURRENT_STACK)));
        setAliasErrorToString(LgNamesContent.get(_util,_cust,_mapping.getVal(ERROR_TO_STRING)));
        setAliasVoid(LgNamesContent.get(_util,_cust,_mapping.getVal(VOID)));
        setAliasGetCause(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_CAUSE)));
        setAliasBadIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(BAD_INDEX)));
        setAliasBadArgNumber(LgNamesContent.get(_util,_cust,_mapping.getVal(BAD_ARG_NUMBER)));
        setAliasIllegalType(LgNamesContent.get(_util,_cust,_mapping.getVal(ILLEGAL_TYPE)));
        setAliasAbstractTypeErr(LgNamesContent.get(_util,_cust,_mapping.getVal(ABSTRACT_TYPE_ERR)));
        setAliasNonInvokable(LgNamesContent.get(_util,_cust,_mapping.getVal(NON_INVOKABLE)));
        setAliasEnums(LgNamesContent.get(_util,_cust,_mapping.getVal(ENUMS)));
        setAliasNbFormat(LgNamesContent.get(_util,_cust,_mapping.getVal(NB_FORMAT)));
        setAliasSof(LgNamesContent.get(_util,_cust,_mapping.getVal(SOF)));
        setAliasIllegalArg(LgNamesContent.get(_util,_cust,_mapping.getVal(ILLEGAL_ARG)));
        setAliasClone(LgNamesContent.get(_util,_cust,_mapping.getVal(CLONE)));
        setAliasName(LgNamesContent.get(_util,_cust,_mapping.getVal(NAME)));
        setAliasOrdinal(LgNamesContent.get(_util,_cust,_mapping.getVal(ORDINAL)));
        setAliasReadResourcesNames(LgNamesContent.get(_util,_cust,_mapping.getVal(READ_RESOURCES_NAMES)));
        setAliasReadResourcesNamesLength(LgNamesContent.get(_util,_cust,_mapping.getVal(READ_RESOURCES_NAMES_LENGTH)));
        setAliasReadResources(LgNamesContent.get(_util,_cust,_mapping.getVal(READ_RESOURCES)));
        setAliasReadResourcesIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(READ_RESOURCES_INDEX)));
        setAliasResources(LgNamesContent.get(_util,_cust,_mapping.getVal(RESOURCES)));
        setAliasArrayLength(LgNamesContent.get(_util,_cust,_mapping.getVal(ARRAY_LENGTH)));
        setAliasErrorInitClass(LgNamesContent.get(_util,_cust,_mapping.getVal(ERROR_INIT_CLASS)));
        setAliasSameRef(LgNamesContent.get(_util,_cust,_mapping.getVal(SAME_REF)));
        setAliasStringUtil(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_UTIL)));
        setAliasStringUtilValueOf(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_UTIL_VALUE_OF)));
        setAliasSetParent(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_PARENT)));
        setAliasRange(LgNamesContent.get(_util,_cust,_mapping.getVal(RANGE)));
        setAliasRangeUpper(LgNamesContent.get(_util,_cust,_mapping.getVal(RANGE_UPPER)));
        setAliasRangeStep(LgNamesContent.get(_util,_cust,_mapping.getVal(RANGE_STEP)));
        setAliasRangeUnlimitedStep(LgNamesContent.get(_util,_cust,_mapping.getVal(RANGE_UNLIMITED_STEP)));
        setAliasRangeLower(LgNamesContent.get(_util,_cust,_mapping.getVal(RANGE_LOWER)));
        setAliasRangeUnlimited(LgNamesContent.get(_util,_cust,_mapping.getVal(RANGE_UNLIMITED)));
        setAliasObjectsUtil(LgNamesContent.get(_util,_cust,_mapping.getVal(OBJECTS_UTIL)));
        setAliasGetParent(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PARENT)));
        setAliasGetFct(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_FCT)));
    }
    public static void en(TranslationsFile _en){
        _en.add(VOID,"Void=void");
        _en.add(RANGE,"Range=$core.Range");
        _en.add(OBJECTS_UTIL,"ObjectsUtil=$core.ObjectsUtil");
        _en.add(STRING_UTIL,"StringUtil=$core.StringUtil");
        _en.add(RESOURCES,"Resources=$core.Resources");
        _en.add(ERROR_INIT_CLASS,"ErrorInitClass=$core.DefErrorClass");
        _en.add(ENUMS,"Enums=$core.Enums");
        _en.add(BAD_ENCODE,"BadEncode=$core.BadEncode");
        _en.add(BAD_INDEX,"BadIndex=$core.BadIndexException");
        _en.add(BAD_ARG_NUMBER,"BadArgNumber=$core.BadArgNumber");
        _en.add(ABSTRACT_TYPE_ERR,"AbstractTypeErr=$core.AbstractTypeError");
        _en.add(ILLEGAL_TYPE,"IllegalType=$core.IllegalType");
        _en.add(NON_INVOKABLE,"NonInvokable=$core.NonInvokable");
        _en.add(ILLEGAL_ARG,"IllegalArg=$core.IllegalArgument");
        _en.add(DIVISION_ZERO,"DivisionZero=$core.DivideZero");
        _en.add(STORE,"Store=$core.BadStore");
        _en.add(CAST_TYPE,"CastType=$core.BadCast");
        _en.add(BAD_SIZE,"BadSize=$core.NegativeSize");
        _en.add(SOF,"Sof=$core.StackOverFlow");
        _en.add(NULL_PE,"NullPe=$core.NullObject");
        _en.add(NB_FORMAT,"NbFormat=$core.BadFormat");
        _en.add(ERROR,"Error=$core.Error");
        _en.add(OBJECT,"Object=$core.Object");
        _en.add(CLONE,"Clone=clone");
        _en.add(ERROR_CURRENT_STACK,"ErrorCurrentStack=current");
        _en.add(ERROR_TO_STRING,"ErrorToString=toString");
        _en.add(GET_MESSAGE,"GetMessage=getMessage");
        _en.add(GET_CAUSE,"GetCause=getCause");
        _en.add(RANGE_LOWER,"RangeLower=lower");
        _en.add(RANGE_UPPER,"RangeUpper=upper");
        _en.add(RANGE_STEP,"RangeStep=step");
        _en.add(RANGE_UNLIMITED_STEP,"RangeUnlimitedStep=unlimitedStep");
        _en.add(RANGE_UNLIMITED,"RangeUnlimited=unlimited");
        _en.add(SAME_REF,"SameRef=eq");
        _en.add(GET_PARENT,"GetParent=getParent");
        _en.add(SET_PARENT,"SetParent=setParent");
        _en.add(GET_FCT,"GetFct=getFct");
        _en.add(STRING_UTIL_VALUE_OF,"StringUtilValueOf=valueOf");
        _en.add(READ_RESOURCES_NAMES,"ReadResourcesNames=readNames");
        _en.add(READ_RESOURCES_NAMES_LENGTH,"ReadResourcesNamesLength=nbNames");
        _en.add(READ_RESOURCES,"ReadResources=readContent");
        _en.add(READ_RESOURCES_INDEX,"ReadResourcesIndex=index");
        _en.add(NAME,"Name=name");
        _en.add(ORDINAL,"Ordinal=ordinal");
        _en.add(ARRAY_LENGTH,"ArrayLength=length");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(VOID,"Void=vide");
        _fr.add(RANGE,"Range=$coeur.Rang");
        _fr.add(OBJECTS_UTIL,"ObjectsUtil=$coeur.ObjetsUtil");
        _fr.add(STRING_UTIL,"StringUtil=$coeur.ChaineUtil");
        _fr.add(RESOURCES,"Resources=$coeur.Ressources");
        _fr.add(ERROR_INIT_CLASS,"ErrorInitClass=$coeur.ErrorDefClasse");
        _fr.add(ENUMS,"Enums=$coeur.Enums");
        _fr.add(BAD_ENCODE,"BadEncode=$coeur.MauvaisEncodage");
        _fr.add(BAD_INDEX,"BadIndex=$coeur.MauvaisIndice");
        _fr.add(BAD_ARG_NUMBER,"BadArgNumber=$coeur.MauvaisNombreArgs");
        _fr.add(ABSTRACT_TYPE_ERR,"AbstractTypeErr=$coeur.TypeAbstraitErreur");
        _fr.add(ILLEGAL_TYPE,"IllegalType=$coeur.TypeInattendu");
        _fr.add(NON_INVOKABLE,"NonInvokable=$coeur.NonInvoquable");
        _fr.add(ILLEGAL_ARG,"IllegalArg=$coeur.IllegalArgument");
        _fr.add(DIVISION_ZERO,"DivisionZero=$coeur.DivisionZero");
        _fr.add(STORE,"Store=$coeur.MauvaisStockage");
        _fr.add(CAST_TYPE,"CastType=$coeur.MauvaisTranstype");
        _fr.add(BAD_SIZE,"BadSize=$coeur.TailleNegative");
        _fr.add(SOF,"Sof=$coeur.PileTropGrande");
        _fr.add(NULL_PE,"NullPe=$coeur.ObjetNul");
        _fr.add(NB_FORMAT,"NbFormat=$coeur.MauvaisFormat");
        _fr.add(ERROR,"Error=$coeur.Erreur");
        _fr.add(OBJECT,"Object=$coeur.Objet");
        _fr.add(CLONE,"Clone=clone");
        _fr.add(ERROR_CURRENT_STACK,"ErrorCurrentStack=courante");
        _fr.add(ERROR_TO_STRING,"ErrorToString=chaine");
        _fr.add(GET_MESSAGE,"GetMessage=valMessage");
        _fr.add(GET_CAUSE,"GetCause=valCause");
        _fr.add(RANGE_LOWER,"RangeLower=inf");
        _fr.add(RANGE_UPPER,"RangeUpper=sup");
        _fr.add(RANGE_STEP,"RangeStep=pas");
        _fr.add(RANGE_UNLIMITED_STEP,"RangeUnlimitedStep=illimitePas");
        _fr.add(RANGE_UNLIMITED,"RangeUnlimited=illimite");
        _fr.add(SAME_REF,"SameRef=eq");
        _fr.add(GET_PARENT,"GetParent=valParent");
        _fr.add(SET_PARENT,"SetParent=majParent");
        _fr.add(GET_FCT,"GetFct=valFct");
        _fr.add(STRING_UTIL_VALUE_OF,"StringUtilValueOf=valeurDe");
        _fr.add(READ_RESOURCES_NAMES,"ReadResourcesNames=lireNoms");
        _fr.add(READ_RESOURCES_NAMES_LENGTH,"ReadResourcesNamesLength=nbNoms");
        _fr.add(READ_RESOURCES,"ReadResources=lireContenu");
        _fr.add(READ_RESOURCES_INDEX,"ReadResourcesIndex=indice");
        _fr.add(NAME,"Name=nom");
        _fr.add(ORDINAL,"Ordinal=ordinal");
        _fr.add(ARRAY_LENGTH,"ArrayLength=longueur");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(VOID,"Void");
        _m.addEntry(RANGE,"Range");
        _m.addEntry(OBJECTS_UTIL,"ObjectsUtil");
        _m.addEntry(STRING_UTIL,"StringUtil");
        _m.addEntry(RESOURCES,"Resources");
        _m.addEntry(ERROR_INIT_CLASS,"ErrorInitClass");
        _m.addEntry(ENUMS,"Enums");
        _m.addEntry(BAD_ENCODE,"BadEncode");
        _m.addEntry(BAD_INDEX,"BadIndex");
        _m.addEntry(BAD_ARG_NUMBER,"BadArgNumber");
        _m.addEntry(ABSTRACT_TYPE_ERR,"AbstractTypeErr");
        _m.addEntry(ILLEGAL_TYPE,"IllegalType");
        _m.addEntry(NON_INVOKABLE,"NonInvokable");
        _m.addEntry(ILLEGAL_ARG,"IllegalArg");
        _m.addEntry(DIVISION_ZERO,"DivisionZero");
        _m.addEntry(STORE,"Store");
        _m.addEntry(CAST_TYPE,"CastType");
        _m.addEntry(BAD_SIZE,"BadSize");
        _m.addEntry(SOF,"Sof");
        _m.addEntry(NULL_PE,"NullPe");
        _m.addEntry(NB_FORMAT,"NbFormat");
        _m.addEntry(ERROR,"Error");
        _m.addEntry(OBJECT,"Object");
        _m.addEntry(CLONE,"Clone");
        _m.addEntry(ERROR_CURRENT_STACK,"ErrorCurrentStack");
        _m.addEntry(ERROR_TO_STRING,"ErrorToString");
        _m.addEntry(GET_MESSAGE,"GetMessage");
        _m.addEntry(GET_CAUSE,"GetCause");
        _m.addEntry(RANGE_LOWER,"RangeLower");
        _m.addEntry(RANGE_UPPER,"RangeUpper");
        _m.addEntry(RANGE_STEP,"RangeStep");
        _m.addEntry(RANGE_UNLIMITED_STEP,"RangeUnlimitedStep");
        _m.addEntry(RANGE_UNLIMITED,"RangeUnlimited");
        _m.addEntry(SAME_REF,"SameRef");
        _m.addEntry(GET_PARENT,"GetParent");
        _m.addEntry(SET_PARENT,"SetParent");
        _m.addEntry(GET_FCT,"GetFct");
        _m.addEntry(STRING_UTIL_VALUE_OF,"StringUtilValueOf");
        _m.addEntry(READ_RESOURCES_NAMES,"ReadResourcesNames");
        _m.addEntry(READ_RESOURCES_NAMES_LENGTH,"ReadResourcesNamesLength");
        _m.addEntry(READ_RESOURCES,"ReadResources");
        _m.addEntry(READ_RESOURCES_INDEX,"ReadResourcesIndex");
        _m.addEntry(NAME,"Name");
        _m.addEntry(ORDINAL,"Ordinal");
        _m.addEntry(ARRAY_LENGTH,"ArrayLength");
    }

    public StringMap<String> allPrimitives(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(VOID), getAliasVoid());
        return list_;
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(RANGE), getAliasRange());
        list_.addEntry(_mapping.getVal(OBJECTS_UTIL), getAliasObjectsUtil());
        list_.addEntry(_mapping.getVal(STRING_UTIL), getAliasStringUtil());
        list_.addEntry(_mapping.getVal(RESOURCES), getAliasResources());
        list_.addEntry(_mapping.getVal(ERROR_INIT_CLASS), getAliasErrorInitClass());
        list_.addEntry(_mapping.getVal(ENUMS), getAliasEnums());
        list_.addEntry(_mapping.getVal(BAD_ENCODE), getAliasBadEncode());
        list_.addEntry(_mapping.getVal(BAD_INDEX), getAliasBadIndex());
        list_.addEntry(_mapping.getVal(BAD_ARG_NUMBER), getAliasBadArgNumber());
        list_.addEntry(_mapping.getVal(ABSTRACT_TYPE_ERR), getAliasAbstractTypeErr());
        list_.addEntry(_mapping.getVal(ILLEGAL_TYPE), getAliasIllegalType());
        list_.addEntry(_mapping.getVal(NON_INVOKABLE), getAliasNonInvokable());
        list_.addEntry(_mapping.getVal(ILLEGAL_ARG), getAliasIllegalArg());
        list_.addEntry(_mapping.getVal(DIVISION_ZERO), getAliasDivisionZero());
        list_.addEntry(_mapping.getVal(STORE), getAliasStore());
        list_.addEntry(_mapping.getVal(CAST_TYPE), getAliasCastType());
        list_.addEntry(_mapping.getVal(BAD_SIZE), getAliasBadSize());
        list_.addEntry(_mapping.getVal(SOF), getAliasSof());
        list_.addEntry(_mapping.getVal(NULL_PE), getAliasNullPe());
        list_.addEntry(_mapping.getVal(NB_FORMAT), getAliasNbFormat());
        list_.addEntry(_mapping.getVal(ERROR), getAliasError());
        list_.addEntry(_mapping.getVal(OBJECT),getAliasObject());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(StringExpUtil.getPrettyArrayType(getAliasObject()), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ARRAY_LENGTH), getAliasArrayLength())));
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(StringExpUtil.getPrettyArrayType(getAliasObject()), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CLONE), getAliasClone())));
        map_.addEntry(getAliasError(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ERROR_CURRENT_STACK), getAliasErrorCurrentStack()),
                new KeyValueMemberName(_mapping.getVal(ERROR_TO_STRING), getAliasErrorToString()),
                new KeyValueMemberName(_mapping.getVal(GET_MESSAGE), getAliasGetMessage()),
                new KeyValueMemberName(_mapping.getVal(GET_CAUSE), getAliasGetCause())));
        map_.addEntry(getAliasRange(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(RANGE_LOWER), getAliasRangeLower()),
                new KeyValueMemberName(_mapping.getVal(RANGE_UPPER), getAliasRangeUpper()),
                new KeyValueMemberName(_mapping.getVal(RANGE_STEP), getAliasRangeStep()),
                new KeyValueMemberName(_mapping.getVal(RANGE_UNLIMITED_STEP), getAliasRangeUnlimitedStep()),
                new KeyValueMemberName(_mapping.getVal(RANGE_UNLIMITED), getAliasRangeUnlimited())));
        map_.addEntry(getAliasObjectsUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SAME_REF), getAliasSameRef()),
                new KeyValueMemberName(_mapping.getVal(GET_PARENT), getAliasGetParent()),
                new KeyValueMemberName(_mapping.getVal(SET_PARENT), getAliasSetParent()),
                new KeyValueMemberName(_mapping.getVal(GET_FCT), getAliasGetFct())));
        map_.addEntry(getAliasStringUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(STRING_UTIL_VALUE_OF), getAliasStringUtilValueOf())));
        map_.addEntry(getAliasResources(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(READ_RESOURCES_NAMES), getAliasReadResourcesNames()),
                new KeyValueMemberName(_mapping.getVal(READ_RESOURCES_NAMES_LENGTH), getAliasReadResourcesNamesLength()),
                new KeyValueMemberName(_mapping.getVal(READ_RESOURCES), getAliasReadResources()),
                new KeyValueMemberName(_mapping.getVal(READ_RESOURCES_INDEX), getAliasReadResourcesIndex())));
        map_.addEntry(getAliasEnums(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(NAME), getAliasName()),
                new KeyValueMemberName(_mapping.getVal(ORDINAL), getAliasOrdinal())));
        return map_;
    }
    private void buildObj(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        setObjType(new StandardClass(aliasObject, fields_, constructors_, methods_, EMPTY_STRING, MethodModifier.NORMAL, new DfObj()));
        StandardClass stdcl_ = getObjType();
        StringList params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new FctObj());
        constructors_.add(ctor_);
        standards_.addEntry(aliasObject, stdcl_);
    }
    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        buildObj(_lgNames);
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        errType = new StandardClass(aliasError, fields_, constructors_, methods_, aliasObject, StdClassModifier.ABSTRACT);
        StandardClass stdcl_ = errType;
        stdcl_.addSuperStdTypes(objType);
        String stackElt_ = _lgNames.getContent().getStackElt().getAliasStackTraceElement();
        stackElt_ = StringExpUtil.getPrettyArrayType(stackElt_);
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasErrorCurrentStack, params_, stackElt_, false, MethodModifier.NORMAL, new FctErrorCurrentStack0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasErrorToString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL,new FctErrorToString0());
        methods_.add( method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(aliasErrorCurrentStack, params_, stackElt_, false, MethodModifier.STATIC,new StringList(params.getAliasError0CurrentStack0()),new FctErrorCurrentStack1());
        methods_.add( method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(aliasErrorToString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasError0ToStringMethod0()),new FctErrorToString1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMessage, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL,new FctErrorGetMessage());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetCause, params_, _lgNames.getContent().getCoreNames().getAliasObject(), false, MethodModifier.NORMAL,new FctErrorGetCause());
        methods_.add( method_);
        StandardType std_ = stdcl_;
        standards_.addEntry(aliasError, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasNullPe, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasNullPe, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasDivisionZero, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasDivisionZero, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCastType, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasCastType, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasStore, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasStore, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadSize, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasBadSize, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasNbFormat, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasNbFormat, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadIndex, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasBadIndex, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadArgNumber, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasBadArgNumber, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasIllegalType, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasIllegalType, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAbstractTypeErr, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasAbstractTypeErr, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasNonInvokable, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasNonInvokable, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasIllegalArg, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasIllegalArg, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSof, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasSof, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasErrorInitClass, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasErrorInitClass, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadEncode, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(errType);
        stdcl_.addSuperStdTypes(objType);
        std_ = stdcl_;
        standards_.addEntry(aliasBadEncode, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasEnums, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        stdcl_.addSuperStdTypes(objType);
        params_ = new StringList(_lgNames.getContent().getPredefTypes().getAliasEnumType());
        method_ = new StandardMethod(aliasName, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasEnums0Name0()),new FctEnumsName());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPredefTypes().getAliasEnumType());
        method_ = new StandardMethod(aliasOrdinal, params_, _lgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(params.getAliasEnums0Ordinal0()),new FctEnumsOrdinal());
        methods_.add( method_);
        standards_.addEntry(aliasEnums, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasRange, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(objType);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger());
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new StringList(params.getAliasRange0Range0(),params.getAliasRange0Range1()),new FctRange1());
        constructors_.add( ctor_);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_, false, new StringList(params.getAliasRange1Range0()),new FctRange0());
        constructors_.add( ctor_);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_, false, new StringList(params.getAliasRange2Range0(),params.getAliasRange2Range1(),params.getAliasRange2Range2()),new FctRange2());
        constructors_.add( ctor_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeLower, params_, _lgNames.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctRangeLower());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeUpper, params_, _lgNames.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctRangeUpper());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeStep, params_, _lgNames.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctRangeStep());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasRangeUnlimitedStep, params_, aliasRange, false, MethodModifier.STATIC,new StringList(params.getAliasRange0UnlimitedStep0(),params.getAliasRange0UnlimitedStep1()), new FctRangeUnlimitedStep());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeUnlimited, params_, _lgNames.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctRangeUnlimited());
        methods_.add( method_);
        standards_.addEntry(aliasRange, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasObjectsUtil, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        stdcl_.addSuperStdTypes(objType);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSameRef, params_, _lgNames.getContent().getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0SameRef0(),params.getAliasObjectsUtil0SameRef1()), new FctObjEquals());
        methods_.add( method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetParent, params_, aliasObject, false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0GetParent0()),new FctObjGetParent());
        methods_.add( method_);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSetParent, params_, aliasVoid, false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0SetParent0(),params.getAliasObjectsUtil0SetParent1()),new FctObjSetParent());
        methods_.add( method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetFct, params_, _lgNames.getReflect().getAliasFct(), false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0GetFct0()),new FctObjGetFct());
        methods_.add( method_);
        standards_.addEntry(aliasObjectsUtil, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasStringUtil, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        stdcl_.addSuperStdTypes(objType);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasStringUtilValueOf, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasStringUtil0ValueOfMethod0()),new FctStringUtilValueOf());
        methods_.add( method_);
        standards_.addEntry(aliasStringUtil, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasResources, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        stdcl_.addSuperStdTypes(objType);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNames, params_, StringExpUtil.getPrettyArrayType(_lgNames.getContent().getCharSeq().getAliasString()), false, MethodModifier.STATIC, new FctResourcesNames());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasReadResources, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasResources0ReadResources0()),new FctResourcesRead());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNamesLength, params_, _lgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC, new FctResourcesReadNamesLength());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasReadResourcesIndex, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasResources0ReadResourcesIndex0()),new FctResourcesReadIndex());
        methods_.add( method_);
        standards_.addEntry(aliasResources, stdcl_);
    }

    public StandardClass getObjType() {
        return objType;
    }

    public void setObjType(StandardClass _o) {
        this.objType = _o;
    }

    public StandardClass getErrType() {
        return errType;
    }

    public String getAliasObject() {
        return aliasObject;
    }

    public void setAliasObject(String _aliasObject) {
        aliasObject = _aliasObject;
    }

    public String getAliasEnums() {
        return aliasEnums;
    }

    public void setAliasEnums(String _aliasEnums) {
        aliasEnums = _aliasEnums;
    }

    public String getAliasError() {
        return aliasError;
    }

    public void setAliasError(String _aliasError) {
        aliasError = _aliasError;
    }

    public String getAliasErrorToString() {
        return aliasErrorToString;
    }

    public void setAliasErrorToString(String _aliasErrorToString) {
        this.aliasErrorToString = _aliasErrorToString;
    }

    public String getAliasErrorCurrentStack() {
        return aliasErrorCurrentStack;
    }

    public void setAliasErrorCurrentStack(String _aliasErrorCurrentStack) {
        this.aliasErrorCurrentStack = _aliasErrorCurrentStack;
    }

    public String getAliasGetMessage() {
        return aliasGetMessage;
    }

    public void setAliasGetMessage(String _aliasGetMessage) {
        aliasGetMessage = _aliasGetMessage;
    }

    public String getAliasGetCause() {
        return aliasGetCause;
    }

    public void setAliasGetCause(String _aliasGetCause) {
        aliasGetCause = _aliasGetCause;
    }

    public String getAliasBadSize() {
        return aliasBadSize;
    }

    public void setAliasBadSize(String _aliasBadSize) {
        aliasBadSize = _aliasBadSize;
    }

    public String getAliasDivisionZero() {
        return aliasDivisionZero;
    }

    public void setAliasDivisionZero(String _aliasDivisionZero) {
        aliasDivisionZero = _aliasDivisionZero;
    }

    public String getAliasCastType() {
        return aliasCastType;
    }

    public void setAliasCastType(String _aliasCast) {
        aliasCastType = _aliasCast;
    }

    public String getAliasStore() {
        return aliasStore;
    }

    public void setAliasStore(String _aliasStore) {
        aliasStore = _aliasStore;
    }

    public String getAliasNullPe() {
        return aliasNullPe;
    }

    public void setAliasNullPe(String _aliasNullPe) {
        aliasNullPe = _aliasNullPe;
    }

    public String getAliasNbFormat() {
        return aliasNbFormat;
    }

    public void setAliasNbFormat(String _aliasNbFormat) {
        aliasNbFormat = _aliasNbFormat;
    }

    public String getAliasBadEncode() {
        return aliasBadEncode;
    }

    public void setAliasBadEncode(String _aliasBadEncode) {
        aliasBadEncode = _aliasBadEncode;
    }

    public String getAliasBadIndex() {
        return aliasBadIndex;
    }

    public void setAliasBadIndex(String _aliasBadIndex) {
        aliasBadIndex = _aliasBadIndex;
    }

    public String getAliasBadArgNumber() {
        return aliasBadArgNumber;
    }

    public void setAliasBadArgNumber(String _aliasBadArgNumber) {
        this.aliasBadArgNumber = _aliasBadArgNumber;
    }

    public String getAliasAbstractTypeErr() {
        return aliasAbstractTypeErr;
    }

    public void setAliasAbstractTypeErr(String _aliasAbstractTypeErr) {
        this.aliasAbstractTypeErr = _aliasAbstractTypeErr;
    }

    public String getAliasIllegalType() {
        return aliasIllegalType;
    }

    public void setAliasIllegalType(String _aliasIllegalType) {
        this.aliasIllegalType = _aliasIllegalType;
    }

    public String getAliasNonInvokable() {
        return aliasNonInvokable;
    }

    public void setAliasNonInvokable(String _aliasNonInvokable) {
        this.aliasNonInvokable = _aliasNonInvokable;
    }

    public String getAliasIllegalArg() {
        return aliasIllegalArg;
    }

    public void setAliasIllegalArg(String _aliasIllegalArg) {
        aliasIllegalArg = _aliasIllegalArg;
    }

    public String getAliasSof() {
        return aliasSof;
    }

    public void setAliasSof(String _aliasSof) {
        aliasSof = _aliasSof;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String _aliasName) {
        aliasName = _aliasName;
    }

    public String getAliasOrdinal() {
        return aliasOrdinal;
    }

    public void setAliasOrdinal(String _aliasOrdinal) {
        aliasOrdinal = _aliasOrdinal;
    }

    public String getAliasErrorInitClass() {
        return aliasErrorInitClass;
    }

    public void setAliasErrorInitClass(String _aliasErrorInitClass) {
        aliasErrorInitClass = _aliasErrorInitClass;
    }

    public String getAliasRange() {
        return aliasRange;
    }

    public void setAliasRange(String _aliasRange) {
        this.aliasRange = _aliasRange;
    }

    public String getAliasRangeLower() {
        return aliasRangeLower;
    }

    public void setAliasRangeLower(String _aliasRangeLower) {
        this.aliasRangeLower = _aliasRangeLower;
    }

    public String getAliasRangeUpper() {
        return aliasRangeUpper;
    }

    public void setAliasRangeUpper(String _aliasRangeUpper) {
        this.aliasRangeUpper = _aliasRangeUpper;
    }

    public String getAliasRangeStep() {
        return aliasRangeStep;
    }

    public void setAliasRangeStep(String _v) {
        this.aliasRangeStep = _v;
    }

    public String getAliasRangeUnlimitedStep() {
        return aliasRangeUnlimitedStep;
    }

    public void setAliasRangeUnlimitedStep(String _aliasRangeUnlimitedStep) {
        this.aliasRangeUnlimitedStep = _aliasRangeUnlimitedStep;
    }

    public String getAliasRangeUnlimited() {
        return aliasRangeUnlimited;
    }

    public void setAliasRangeUnlimited(String _aliasRangeUnlimited) {
        this.aliasRangeUnlimited = _aliasRangeUnlimited;
    }

    public String getAliasObjectsUtil() {
        return aliasObjectsUtil;
    }

    public void setAliasObjectsUtil(String _aliasObjectsUtil) {
        aliasObjectsUtil = _aliasObjectsUtil;
    }

    public String getAliasStringUtil() {
        return aliasStringUtil;
    }

    public void setAliasStringUtil(String _aliasStringUtil) {
        aliasStringUtil = _aliasStringUtil;
    }

    public String getAliasStringUtilValueOf() {
        return aliasStringUtilValueOf;
    }

    public void setAliasStringUtilValueOf(String _aliasStringUtilValueOf) {
        this.aliasStringUtilValueOf = _aliasStringUtilValueOf;
    }

    public String getAliasSameRef() {
        return aliasSameRef;
    }

    public void setAliasSameRef(String _aliasSameRef) {
        aliasSameRef = _aliasSameRef;
    }

    public String getAliasGetParent() {
        return aliasGetParent;
    }

    public void setAliasGetParent(String _aliasGetParent) {
        aliasGetParent = _aliasGetParent;
    }

    public String getAliasSetParent() {
        return aliasSetParent;
    }

    public void setAliasSetParent(String _aliasSetParent) {
        aliasSetParent = _aliasSetParent;
    }

    public String getAliasGetFct() {
        return aliasGetFct;
    }

    public void setAliasGetFct(String _aliasGetFct) {
        this.aliasGetFct = _aliasGetFct;
    }

    public String getAliasVoid() {
        return aliasVoid;
    }

    public void setAliasVoid(String _aliasVoid) {
        aliasVoid = _aliasVoid;
    }

    public String getAliasClone() {
        return aliasClone;
    }

    public void setAliasClone(String _aliasClone) {
        aliasClone = _aliasClone;
    }

	public String getAliasReadResourcesNames() {
		return aliasReadResourcesNames;
	}

	public void setAliasReadResourcesNames(String _aliasReadResourcesNames) {
		aliasReadResourcesNames = _aliasReadResourcesNames;
	}

    public String getAliasReadResourcesNamesLength() {
        return aliasReadResourcesNamesLength;
    }

    public void setAliasReadResourcesNamesLength(String _aliasReadResourcesNamesLength) {
        this.aliasReadResourcesNamesLength = _aliasReadResourcesNamesLength;
    }

    public String getAliasReadResources() {
		return aliasReadResources;
	}

	public void setAliasReadResources(String _aliasReadResources) {
		aliasReadResources = _aliasReadResources;
	}

    public String getAliasReadResourcesIndex() {
        return aliasReadResourcesIndex;
    }

    public void setAliasReadResourcesIndex(String _aliasReadResourcesIndex) {
        this.aliasReadResourcesIndex = _aliasReadResourcesIndex;
    }

    public String getAliasResources() {
		return aliasResources;
	}

	public void setAliasResources(String _aliasResources) {
		aliasResources = _aliasResources;
	}

    public String getAliasArrayLength() {
        return aliasArrayLength;
    }

    public void setAliasArrayLength(String _aliasArrayLength) {
        this.aliasArrayLength = _aliasArrayLength;
    }

    public AliasParamCore getParams() {
        return params;
    }
}
