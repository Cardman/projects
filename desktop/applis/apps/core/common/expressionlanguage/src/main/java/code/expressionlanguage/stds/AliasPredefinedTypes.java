package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class AliasPredefinedTypes {
    private static final String ENUM_TYPE="815";
    private static final String SEED_DOUBLE_GENERATOR="______1794";
    private static final String SEED_GENERATOR="______1795";
    private static final String ITERABLE="726";
    private static final String ITERATOR_TYPE="727";
    private static final String ENUM_PARAM="820";
    private static final String ITERATOR_TABLE_TYPE="728";
    private static final String ITERABLE_TABLE="729";
    private static final String PAIR_TYPE="730";
    private static final String ENUM_NAME="816";
    private static final String ENUM_ORDINAL="817";
    private static final String ENUM_PRED_VALUE_OF="818";
    private static final String ENUM_VALUES="819";
    private static final String SEED_GET="______1793";
    private static final String ITERATOR="736";
    private static final String HAS_NEXT="737";
    private static final String NEXT="738";
    private static final String ITERATOR_TABLE="739";
    private static final String HAS_NEXT_PAIR="740";
    private static final String NEXT_PAIR="741";
    private static final String GET_FIRST="742";
    private static final String GET_SECOND="743";
    private static final String ENUM_PARAM_VAR="821";
    private static final String ITERABLE_VAR="769";
    private static final String ITERATOR_TYPE_VAR="770";
    private static final String ITERABLE_TABLE_VAR_FIRST="771";
    private static final String ITERABLE_TABLE_VAR_SECOND="772";
    private static final String ITERATOR_TABLE_TYPE_VAR_FIRST="773";
    private static final String ITERATOR_TABLE_TYPE_VAR_SECOND="774";
    private static final String PAIR_TYPE_VAR_FIRST="775";
    private static final String PAIR_TYPE_VAR_SECOND="776";
    private String aliasSeedDoubleGenerator;
    private String aliasSeedGenerator;
    private String aliasSeedGet;
    private String aliasIterable;
    private String aliasIterableVar;
    private String aliasIterator;
    private String aliasIteratorType;
    private String aliasIteratorTypeVar;
    private String aliasNext;
    private String aliasHasNext;
    private String aliasIterableTable;
    private String aliasIterableTableVarFirst;
    private String aliasIterableTableVarSecond;
    private String aliasIteratorTable;
    private String aliasIteratorTableType;
    private String aliasIteratorTableTypeVarFirst;
    private String aliasIteratorTableTypeVarSecond;
    private String aliasHasNextPair;
    private String aliasNextPair;
    private String aliasPairType;
    private String aliasGetFirst;
    private String aliasGetSecond;
    private String aliasPairTypeVarFirst;
    private String aliasPairTypeVarSecond;

    private String aliasEnumParam;
    private String aliasEnumType;
    private String aliasEnumName;
    private String aliasEnumOrdinal;
    private String aliasEnumValues;
    private String aliasEnumPredValueOf;
    private String aliasEnumParamVar;
    private final AliasParamPredefinedTypes params = new AliasParamPredefinedTypes();
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasIteratorType(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR_TYPE)));
        setAliasEnumParam(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_PARAM)));
        setAliasIteratorTableTypeVarFirst(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR_TABLE_TYPE_VAR_FIRST)));
        setAliasIteratorTableTypeVarSecond(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR_TABLE_TYPE_VAR_SECOND)));
        setAliasIterator(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR)));
        setAliasEnumType(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_TYPE)));
        setAliasIterable(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERABLE)));
        setAliasHasNext(LgNamesContent.get(_util,_cust, _mapping.getVal(HAS_NEXT)));
        setAliasPairType(LgNamesContent.get(_util,_cust, _mapping.getVal(PAIR_TYPE)));
        setAliasNext(LgNamesContent.get(_util,_cust, _mapping.getVal(NEXT)));
        setAliasGetFirst(LgNamesContent.get(_util,_cust, _mapping.getVal(GET_FIRST)));
        setAliasNextPair(LgNamesContent.get(_util,_cust, _mapping.getVal(NEXT_PAIR)));
        setAliasIteratorTableType(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR_TABLE_TYPE)));
        setAliasIteratorTable(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR_TABLE)));
        setAliasIterableTable(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERABLE_TABLE)));
        setAliasHasNextPair(LgNamesContent.get(_util,_cust, _mapping.getVal(HAS_NEXT_PAIR)));
        setAliasGetSecond(LgNamesContent.get(_util,_cust, _mapping.getVal(GET_SECOND)));
        setAliasIterableTableVarSecond(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERABLE_TABLE_VAR_SECOND)));
        setAliasEnumValues(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_VALUES)));
        setAliasEnumPredValueOf(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_PRED_VALUE_OF)));
        setAliasIteratorTypeVar(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERATOR_TYPE_VAR)));
        setAliasIterableTableVarFirst(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERABLE_TABLE_VAR_FIRST)));
        setAliasPairTypeVarFirst(LgNamesContent.get(_util,_cust, _mapping.getVal(PAIR_TYPE_VAR_FIRST)));
        setAliasEnumParamVar(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_PARAM_VAR)));
        setAliasSeedGenerator(LgNamesContent.get(_util,_cust, _mapping.getVal(SEED_GENERATOR)));
        setAliasSeedDoubleGenerator(LgNamesContent.get(_util,_cust, _mapping.getVal(SEED_DOUBLE_GENERATOR)));
        setAliasSeedGet(LgNamesContent.get(_util,_cust, _mapping.getVal(SEED_GET)));
        setAliasPairTypeVarSecond(LgNamesContent.get(_util,_cust, _mapping.getVal(PAIR_TYPE_VAR_SECOND)));
        setAliasIterableVar(LgNamesContent.get(_util,_cust, _mapping.getVal(ITERABLE_VAR)));
        setAliasEnumName(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_NAME)));
        setAliasEnumOrdinal(LgNamesContent.get(_util,_cust, _mapping.getVal(ENUM_ORDINAL)));
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(ENUM_TYPE,"EnumType");
        _m.addEntry(SEED_DOUBLE_GENERATOR,"SeedDoubleGenerator");
        _m.addEntry(SEED_GENERATOR,"SeedGenerator");
        _m.addEntry(ITERABLE,"Iterable");
        _m.addEntry(ITERATOR_TYPE,"IteratorType");
        _m.addEntry(ENUM_PARAM,"EnumParam");
        _m.addEntry(ITERATOR_TABLE_TYPE,"IteratorTableType");
        _m.addEntry(ITERABLE_TABLE,"IterableTable");
        _m.addEntry(PAIR_TYPE,"PairType");
        _m.addEntry(ENUM_NAME,"EnumName");
        _m.addEntry(ENUM_ORDINAL,"EnumOrdinal");
        _m.addEntry(ENUM_PRED_VALUE_OF,"EnumPredValueOf");
        _m.addEntry(ENUM_VALUES,"EnumValues");
        _m.addEntry(SEED_GET,"SeedGet");
        _m.addEntry(ITERATOR,"Iterator");
        _m.addEntry(HAS_NEXT,"HasNext");
        _m.addEntry(NEXT,"Next");
        _m.addEntry(ITERATOR_TABLE,"IteratorTable");
        _m.addEntry(HAS_NEXT_PAIR,"HasNextPair");
        _m.addEntry(NEXT_PAIR,"NextPair");
        _m.addEntry(GET_FIRST,"GetFirst");
        _m.addEntry(GET_SECOND,"GetSecond");
        _m.addEntry(ENUM_PARAM_VAR,"EnumParamVar");
        _m.addEntry(ITERABLE_VAR,"IterableVar");
        _m.addEntry(ITERATOR_TYPE_VAR,"IteratorTypeVar");
        _m.addEntry(ITERABLE_TABLE_VAR_FIRST,"IterableTableVarFirst");
        _m.addEntry(ITERABLE_TABLE_VAR_SECOND,"IterableTableVarSecond");
        _m.addEntry(ITERATOR_TABLE_TYPE_VAR_FIRST,"IteratorTableTypeVarFirst");
        _m.addEntry(ITERATOR_TABLE_TYPE_VAR_SECOND,"IteratorTableTypeVarSecond");
        _m.addEntry(PAIR_TYPE_VAR_FIRST,"PairTypeVarFirst");
        _m.addEntry(PAIR_TYPE_VAR_SECOND,"PairTypeVarSecond");
    }
    public static void en(TranslationsFile _en){
        _en.add(ENUM_TYPE,"EnumType=$core.$en");
        _en.add(SEED_DOUBLE_GENERATOR,"SeedDoubleGenerator=$core.DoubleGenerator");
        _en.add(SEED_GENERATOR,"SeedGenerator=$core.Generator");
        _en.add(ITERABLE,"Iterable=$core.Iterable");
        _en.add(ITERATOR_TYPE,"IteratorType=$core.Iterator");
        _en.add(ENUM_PARAM,"EnumParam=$core.Enum");
        _en.add(ITERATOR_TABLE_TYPE,"IteratorTableType=$core.IteratorTable");
        _en.add(ITERABLE_TABLE,"IterableTable=$core.IterableTable");
        _en.add(PAIR_TYPE,"PairType=$core.Pair");
        _en.add(ENUM_NAME,"EnumName=name");
        _en.add(ENUM_ORDINAL,"EnumOrdinal=ordinal");
        _en.add(ENUM_PRED_VALUE_OF,"EnumPredValueOf=valueOf");
        _en.add(ENUM_VALUES,"EnumValues=values");
        _en.add(SEED_GET,"SeedGet=get");
        _en.add(ITERATOR,"Iterator=iterator");
        _en.add(HAS_NEXT,"HasNext=hasNext");
        _en.add(NEXT,"Next=next");
        _en.add(ITERATOR_TABLE,"IteratorTable=iteratorTable");
        _en.add(HAS_NEXT_PAIR,"HasNextPair=hasNextPair");
        _en.add(NEXT_PAIR,"NextPair=nextPair");
        _en.add(GET_FIRST,"GetFirst=getFirst");
        _en.add(GET_SECOND,"GetSecond=getSecond");
        _en.add(ENUM_PARAM_VAR,"EnumParamVar=T");
        _en.add(ITERABLE_VAR,"IterableVar=T");
        _en.add(ITERATOR_TYPE_VAR,"IteratorTypeVar=T");
        _en.add(ITERABLE_TABLE_VAR_FIRST,"IterableTableVarFirst=T");
        _en.add(ITERABLE_TABLE_VAR_SECOND,"IterableTableVarSecond=U");
        _en.add(ITERATOR_TABLE_TYPE_VAR_FIRST,"IteratorTableTypeVarFirst=T");
        _en.add(ITERATOR_TABLE_TYPE_VAR_SECOND,"IteratorTableTypeVarSecond=U");
        _en.add(PAIR_TYPE_VAR_FIRST,"PairTypeVarFirst=T");
        _en.add(PAIR_TYPE_VAR_SECOND,"PairTypeVarSecond=U");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(ENUM_TYPE,"EnumType=$coeur.$en");
        _fr.add(SEED_DOUBLE_GENERATOR,"SeedDoubleGenerator=$coeur.GenerateurDec");
        _fr.add(SEED_GENERATOR,"SeedGenerator=$coeur.Generateur");
        _fr.add(ITERABLE,"Iterable=$coeur.Iterable");
        _fr.add(ITERATOR_TYPE,"IteratorType=$coeur.Iterateur");
        _fr.add(ENUM_PARAM,"EnumParam=$coeur.Enum");
        _fr.add(ITERATOR_TABLE_TYPE,"IteratorTableType=$coeur.IterateurTable");
        _fr.add(ITERABLE_TABLE,"IterableTable=$coeur.IterableTable");
        _fr.add(PAIR_TYPE,"PairType=$coeur.Pair");
        _fr.add(ENUM_NAME,"EnumName=nom");
        _fr.add(ENUM_ORDINAL,"EnumOrdinal=ordinal");
        _fr.add(ENUM_PRED_VALUE_OF,"EnumPredValueOf=valeurDe");
        _fr.add(ENUM_VALUES,"EnumValues=valeurs");
        _fr.add(SEED_GET,"SeedGet=val");
        _fr.add(ITERATOR,"Iterator=iterateur");
        _fr.add(HAS_NEXT,"HasNext=aSuivant");
        _fr.add(NEXT,"Next=suivant");
        _fr.add(ITERATOR_TABLE,"IteratorTable=iterateurTable");
        _fr.add(HAS_NEXT_PAIR,"HasNextPair=aSuivantPair");
        _fr.add(NEXT_PAIR,"NextPair=suivantPair");
        _fr.add(GET_FIRST,"GetFirst=valPremier");
        _fr.add(GET_SECOND,"GetSecond=valDeuxieme");
        _fr.add(ENUM_PARAM_VAR,"EnumParamVar=T");
        _fr.add(ITERABLE_VAR,"IterableVar=T");
        _fr.add(ITERATOR_TYPE_VAR,"IteratorTypeVar=T");
        _fr.add(ITERABLE_TABLE_VAR_FIRST,"IterableTableVarFirst=T");
        _fr.add(ITERABLE_TABLE_VAR_SECOND,"IterableTableVarSecond=U");
        _fr.add(ITERATOR_TABLE_TYPE_VAR_FIRST,"IteratorTableTypeVarFirst=T");
        _fr.add(ITERATOR_TABLE_TYPE_VAR_SECOND,"IteratorTableTypeVarSecond=U");
        _fr.add(PAIR_TYPE_VAR_FIRST,"PairTypeVarFirst=T");
        _fr.add(PAIR_TYPE_VAR_SECOND,"PairTypeVarSecond=U");
    }

    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(ENUM_TYPE), getAliasEnumType());
        list_.addEntry(_mapping.getVal(SEED_DOUBLE_GENERATOR), getAliasSeedDoubleGenerator());
        list_.addEntry(_mapping.getVal(SEED_GENERATOR), getAliasSeedGenerator());
        list_.addEntry(_mapping.getVal(ITERABLE), getAliasIterable());
        list_.addEntry(_mapping.getVal(ITERATOR_TYPE), getAliasIteratorType());
        list_.addEntry(_mapping.getVal(ENUM_PARAM), getAliasEnumParam());
        list_.addEntry(_mapping.getVal(ITERATOR_TABLE_TYPE), getAliasIteratorTableType());
        list_.addEntry(_mapping.getVal(ITERABLE_TABLE), getAliasIterableTable());
        list_.addEntry(_mapping.getVal(PAIR_TYPE), getAliasPairType());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasEnumParam(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ENUM_PARAM_VAR),getAliasEnumParamVar())));
        map_.addEntry(getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERABLE_VAR),getAliasIterableVar())));
        map_.addEntry(getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERATOR_TYPE_VAR),getAliasIteratorTypeVar())));
        map_.addEntry(getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERABLE_TABLE_VAR_FIRST),getAliasIterableTableVarFirst()),
                new KeyValueMemberName(_mapping.getVal(ITERABLE_TABLE_VAR_SECOND),getAliasIterableTableVarSecond())));
        map_.addEntry(getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERATOR_TABLE_TYPE_VAR_FIRST),getAliasIteratorTableTypeVarFirst()),
                new KeyValueMemberName(_mapping.getVal(ITERATOR_TABLE_TYPE_VAR_SECOND),getAliasIteratorTableTypeVarSecond())));
        map_.addEntry(getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PAIR_TYPE_VAR_FIRST),getAliasPairTypeVarFirst()),
                new KeyValueMemberName(_mapping.getVal(PAIR_TYPE_VAR_SECOND),getAliasPairTypeVarSecond())));
        return map_;
    }
    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping) {
        return new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERATOR),getAliasIterator()),
                new KeyValueMemberName(_mapping.getVal(HAS_NEXT),getAliasHasNext()),
                new KeyValueMemberName(_mapping.getVal(NEXT),getAliasNext()),
                new KeyValueMemberName(_mapping.getVal(ITERATOR_TABLE),getAliasIteratorTable()),
                new KeyValueMemberName(_mapping.getVal(HAS_NEXT_PAIR),getAliasHasNextPair()),
                new KeyValueMemberName(_mapping.getVal(NEXT_PAIR),getAliasNextPair()),
                new KeyValueMemberName(_mapping.getVal(GET_FIRST),getAliasGetFirst()),
                new KeyValueMemberName(_mapping.getVal(GET_SECOND),getAliasGetSecond()),
                new KeyValueMemberName(_mapping.getVal(ENUM_ORDINAL),getAliasEnumOrdinal()),
                new KeyValueMemberName(_mapping.getVal(ENUM_NAME),getAliasEnumName()),
                new KeyValueMemberName(_mapping.getVal(SEED_GET),getAliasSeedGet())
        );
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasEnumType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ENUM_NAME), getAliasEnumName()),
                new KeyValueMemberName(_mapping.getVal(ENUM_ORDINAL), getAliasEnumOrdinal()),
                new KeyValueMemberName(_mapping.getVal(ENUM_PRED_VALUE_OF), getAliasEnumPredValueOf()),
                new KeyValueMemberName(_mapping.getVal(ENUM_VALUES), getAliasEnumValues())));
        map_.addEntry(getAliasSeedDoubleGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SEED_GET), getAliasSeedGet())));
        map_.addEntry(getAliasSeedGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SEED_GET), getAliasSeedGet())));
        map_.addEntry(getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERATOR),getAliasIterator())));
        map_.addEntry(getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(HAS_NEXT),getAliasHasNext()),
                new KeyValueMemberName(_mapping.getVal(NEXT),getAliasNext())));
        map_.addEntry(getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITERATOR_TABLE),getAliasIteratorTable())));
        map_.addEntry(getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(HAS_NEXT_PAIR),getAliasHasNextPair()),
                new KeyValueMemberName(_mapping.getVal(NEXT_PAIR),getAliasNextPair())));
        map_.addEntry(getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_FIRST),getAliasGetFirst()),
                new KeyValueMemberName(_mapping.getVal(GET_SECOND),getAliasGetSecond())));
        return map_;
    }
    public String getAliasIterable() {
        return aliasIterable;
    }
    public void setAliasIterable(String _aliasIterable) {
        aliasIterable = _aliasIterable;
    }
    public String getAliasIterator() {
        return aliasIterator;
    }
    public void setAliasIterator(String _aliasIterator) {
        aliasIterator = _aliasIterator;
    }
    public String getAliasIteratorType() {
        return aliasIteratorType;
    }
    public void setAliasIteratorType(String _aliasIteratorType) {
        aliasIteratorType = _aliasIteratorType;
    }
    public String getAliasNext() {
        return aliasNext;
    }
    public void setAliasNext(String _aliasNext) {
        aliasNext = _aliasNext;
    }
    public String getAliasHasNext() {
        return aliasHasNext;
    }
    public void setAliasHasNext(String _aliasHasNext) {
        aliasHasNext = _aliasHasNext;
    }
    public String getAliasIterableTable() {
        return aliasIterableTable;
    }
    public void setAliasIterableTable(String _aliasIterableTable) {
        aliasIterableTable = _aliasIterableTable;
    }
    public String getAliasIteratorTable() {
        return aliasIteratorTable;
    }
    public void setAliasIteratorTable(String _aliasIteratorTable) {
        aliasIteratorTable = _aliasIteratorTable;
    }
    public String getAliasIteratorTableType() {
        return aliasIteratorTableType;
    }
    public void setAliasIteratorTableType(String _aliasIteratorTableType) {
        aliasIteratorTableType = _aliasIteratorTableType;
    }
    public String getAliasHasNextPair() {
        return aliasHasNextPair;
    }
    public void setAliasHasNextPair(String _aliasHasNextPair) {
        aliasHasNextPair = _aliasHasNextPair;
    }
    public String getAliasNextPair() {
        return aliasNextPair;
    }
    public void setAliasNextPair(String _aliasNextPair) {
        aliasNextPair = _aliasNextPair;
    }
    public String getAliasPairType() {
        return aliasPairType;
    }
    public void setAliasPairType(String _aliasPairType) {
        aliasPairType = _aliasPairType;
    }
    public String getAliasGetFirst() {
        return aliasGetFirst;
    }
    public void setAliasGetFirst(String _aliasGetFirst) {
        aliasGetFirst = _aliasGetFirst;
    }
    public String getAliasGetSecond() {
        return aliasGetSecond;
    }
    public void setAliasGetSecond(String _aliasGetSecond) {
        aliasGetSecond = _aliasGetSecond;
    }
    public String getAliasEnumParam() {
        return aliasEnumParam;
    }
    public void setAliasEnumParam(String _aliasEnumParam) {
        aliasEnumParam = _aliasEnumParam;
    }
    public String getAliasEnumType() {
        return aliasEnumType;
    }
    public void setAliasEnumType(String _aliasEnum) {
        aliasEnumType = _aliasEnum;
    }
    public String getAliasEnumName() {
        return aliasEnumName;
    }
    public void setAliasEnumName(String _aliasEnumName) {
        aliasEnumName = _aliasEnumName;
    }
    public String getAliasEnumOrdinal() {
        return aliasEnumOrdinal;
    }
    public void setAliasEnumOrdinal(String _aliasEnumOrdinal) {
        aliasEnumOrdinal = _aliasEnumOrdinal;
    }
    public String getAliasEnumValues() {
        return aliasEnumValues;
    }
    public void setAliasEnumValues(String _aliasValues) {
        aliasEnumValues = _aliasValues;
    }
    public String getAliasEnumPredValueOf() {
        return aliasEnumPredValueOf;
    }
    public void setAliasEnumPredValueOf(String _aliasEnumPredValueOf) {
        aliasEnumPredValueOf = _aliasEnumPredValueOf;
    }

    public String getAliasIterableVar() {
        return aliasIterableVar;
    }

    public void setAliasIterableVar(String _aliasIterableVar) {
        this.aliasIterableVar = _aliasIterableVar;
    }

    public String getAliasIteratorTypeVar() {
        return aliasIteratorTypeVar;
    }

    public void setAliasIteratorTypeVar(String _aliasIteratorTypeVar) {
        this.aliasIteratorTypeVar = _aliasIteratorTypeVar;
    }

    public String getAliasIterableTableVarFirst() {
        return aliasIterableTableVarFirst;
    }

    public void setAliasIterableTableVarFirst(String _aliasIterableTableVarFirst) {
        this.aliasIterableTableVarFirst = _aliasIterableTableVarFirst;
    }

    public String getAliasIterableTableVarSecond() {
        return aliasIterableTableVarSecond;
    }

    public void setAliasIterableTableVarSecond(String _aliasIterableTableVarSecond) {
        this.aliasIterableTableVarSecond = _aliasIterableTableVarSecond;
    }

    public String getAliasIteratorTableTypeVarFirst() {
        return aliasIteratorTableTypeVarFirst;
    }

    public void setAliasIteratorTableTypeVarFirst(String _aliasIteratorTableTypeVarFirst) {
        this.aliasIteratorTableTypeVarFirst = _aliasIteratorTableTypeVarFirst;
    }

    public String getAliasIteratorTableTypeVarSecond() {
        return aliasIteratorTableTypeVarSecond;
    }

    public void setAliasIteratorTableTypeVarSecond(String _aliasIteratorTableTypeVarSecond) {
        this.aliasIteratorTableTypeVarSecond = _aliasIteratorTableTypeVarSecond;
    }

    public String getAliasPairTypeVarFirst() {
        return aliasPairTypeVarFirst;
    }

    public void setAliasPairTypeVarFirst(String _aliasPairTypeVarFirst) {
        this.aliasPairTypeVarFirst = _aliasPairTypeVarFirst;
    }

    public String getAliasPairTypeVarSecond() {
        return aliasPairTypeVarSecond;
    }

    public void setAliasPairTypeVarSecond(String _aliasPairTypeVarSecond) {
        this.aliasPairTypeVarSecond = _aliasPairTypeVarSecond;
    }

    public String getAliasEnumParamVar() {
        return aliasEnumParamVar;
    }

    public void setAliasEnumParamVar(String _aliasEnumParamVar) {
        this.aliasEnumParamVar = _aliasEnumParamVar;
    }

    public String getAliasSeedDoubleGenerator() {
        return aliasSeedDoubleGenerator;
    }

    public void setAliasSeedDoubleGenerator(String _aliasSeedDoubleGenerator) {
        this.aliasSeedDoubleGenerator = _aliasSeedDoubleGenerator;
    }

    public String getAliasSeedGenerator() {
        return aliasSeedGenerator;
    }

    public void setAliasSeedGenerator(String _aliasSeedGenerator) {
        this.aliasSeedGenerator = _aliasSeedGenerator;
    }

    public String getAliasSeedGet() {
        return aliasSeedGet;
    }

    public void setAliasSeedGet(String _aliasSeedGet) {
        this.aliasSeedGet = _aliasSeedGet;
    }

    public AliasParamPredefinedTypes getParams() {
        return params;
    }
}
