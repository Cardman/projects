package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.util.CustList;
import code.util.StringMap;

public final class AliasPredefinedTypes {
    public static final String ITERATOR_TYPE = "IteratorType";
    public static final String ENUM_PARAM = "EnumParam";
    public static final String ITERATOR_TABLE_TYPE_VAR_FIRST = "IteratorTableTypeVarFirst";
    public static final String ITERATOR_TABLE_TYPE_VAR_SECOND = "IteratorTableTypeVarSecond";
    public static final String ITERATOR = "Iterator";
    public static final String ENUM_TYPE = "EnumType";
    public static final String ITERABLE = "Iterable";
    public static final String HAS_NEXT = "HasNext";
    public static final String PAIR_TYPE = "PairType";
    public static final String NEXT = "Next";
    public static final String GET_FIRST = "GetFirst";
    public static final String NEXT_PAIR = "NextPair";
    public static final String ITERATOR_TABLE_TYPE = "IteratorTableType";
    public static final String ITERATOR_TABLE = "IteratorTable";
    public static final String ITERABLE_TABLE = "IterableTable";
    public static final String HAS_NEXT_PAIR = "HasNextPair";
    public static final String GET_SECOND = "GetSecond";
    public static final String ITERABLE_TABLE_VAR_SECOND = "IterableTableVarSecond";
    public static final String ENUM_VALUES = "EnumValues";
    public static final String ENUM_PRED_VALUE_OF = "EnumPredValueOf";
    public static final String ITERATOR_TYPE_VAR = "IteratorTypeVar";
    public static final String ITERABLE_TABLE_VAR_FIRST = "IterableTableVarFirst";
    public static final String PAIR_TYPE_VAR_FIRST = "PairTypeVarFirst";
    public static final String ENUM_PARAM_VAR = "EnumParamVar";
    public static final String SEED_GENERATOR = "SeedGenerator";
    public static final String SEED_DOUBLE_GENERATOR = "SeedDoubleGenerator";
    public static final String SEED_GET = "SeedGet";
    public static final String PAIR_TYPE_VAR_SECOND = "PairTypeVarSecond";
    public static final String ITERABLE_VAR = "IterableVar";
    public static final String ENUM_NAME = "EnumName";
    public static final String ENUM_ORDINAL = "EnumOrdinal";
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
    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasIteratorType(LgNamesContent.get(_util,_cust, ITERATOR_TYPE));
        setAliasEnumParam(LgNamesContent.get(_util,_cust, ENUM_PARAM));
        setAliasIteratorTableTypeVarFirst(LgNamesContent.get(_util,_cust, ITERATOR_TABLE_TYPE_VAR_FIRST));
        setAliasIteratorTableTypeVarSecond(LgNamesContent.get(_util,_cust, ITERATOR_TABLE_TYPE_VAR_SECOND));
        setAliasIterator(LgNamesContent.get(_util,_cust, ITERATOR));
        setAliasEnumType(LgNamesContent.get(_util,_cust, ENUM_TYPE));
        setAliasIterable(LgNamesContent.get(_util,_cust, ITERABLE));
        setAliasHasNext(LgNamesContent.get(_util,_cust, HAS_NEXT));
        setAliasPairType(LgNamesContent.get(_util,_cust, PAIR_TYPE));
        setAliasNext(LgNamesContent.get(_util,_cust, NEXT));
        setAliasGetFirst(LgNamesContent.get(_util,_cust, GET_FIRST));
        setAliasNextPair(LgNamesContent.get(_util,_cust, NEXT_PAIR));
        setAliasIteratorTableType(LgNamesContent.get(_util,_cust, ITERATOR_TABLE_TYPE));
        setAliasIteratorTable(LgNamesContent.get(_util,_cust, ITERATOR_TABLE));
        setAliasIterableTable(LgNamesContent.get(_util,_cust, ITERABLE_TABLE));
        setAliasHasNextPair(LgNamesContent.get(_util,_cust, HAS_NEXT_PAIR));
        setAliasGetSecond(LgNamesContent.get(_util,_cust, GET_SECOND));
        setAliasIterableTableVarSecond(LgNamesContent.get(_util,_cust, ITERABLE_TABLE_VAR_SECOND));
        setAliasEnumValues(LgNamesContent.get(_util,_cust, ENUM_VALUES));
        setAliasEnumPredValueOf(LgNamesContent.get(_util,_cust, ENUM_PRED_VALUE_OF));
        setAliasIteratorTypeVar(LgNamesContent.get(_util,_cust, ITERATOR_TYPE_VAR));
        setAliasIterableTableVarFirst(LgNamesContent.get(_util,_cust, ITERABLE_TABLE_VAR_FIRST));
        setAliasPairTypeVarFirst(LgNamesContent.get(_util,_cust, PAIR_TYPE_VAR_FIRST));
        setAliasEnumParamVar(LgNamesContent.get(_util,_cust, ENUM_PARAM_VAR));
        setAliasSeedGenerator(LgNamesContent.get(_util,_cust, SEED_GENERATOR));
        setAliasSeedDoubleGenerator(LgNamesContent.get(_util,_cust, SEED_DOUBLE_GENERATOR));
        setAliasSeedGet(LgNamesContent.get(_util,_cust, SEED_GET));
        setAliasPairTypeVarSecond(LgNamesContent.get(_util,_cust, PAIR_TYPE_VAR_SECOND));
        setAliasIterableVar(LgNamesContent.get(_util,_cust, ITERABLE_VAR));
        setAliasEnumName(LgNamesContent.get(_util,_cust, ENUM_NAME));
        setAliasEnumOrdinal(LgNamesContent.get(_util,_cust, ENUM_ORDINAL));
    }

    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(ENUM_TYPE, getAliasEnumType());
        list_.addEntry(SEED_DOUBLE_GENERATOR, getAliasSeedDoubleGenerator());
        list_.addEntry(SEED_GENERATOR, getAliasSeedGenerator());
        list_.addEntry(ITERABLE, getAliasIterable());
        list_.addEntry(ITERATOR_TYPE, getAliasIteratorType());
        list_.addEntry(ENUM_PARAM, getAliasEnumParam());
        list_.addEntry(ITERATOR_TABLE_TYPE, getAliasIteratorTableType());
        list_.addEntry(ITERABLE_TABLE, getAliasIterableTable());
        list_.addEntry(PAIR_TYPE, getAliasPairType());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasEnumParam(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_PARAM_VAR,getAliasEnumParamVar())));
        map_.addEntry(getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERABLE_VAR,getAliasIterableVar())));
        map_.addEntry(getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TYPE_VAR,getAliasIteratorTypeVar())));
        map_.addEntry(getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERABLE_TABLE_VAR_FIRST,getAliasIterableTableVarFirst()),
                new KeyValueMemberName(ITERABLE_TABLE_VAR_SECOND,getAliasIterableTableVarSecond())));
        map_.addEntry(getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TABLE_TYPE_VAR_FIRST,getAliasIteratorTableTypeVarFirst()),
                new KeyValueMemberName(ITERATOR_TABLE_TYPE_VAR_SECOND,getAliasIteratorTableTypeVarSecond())));
        map_.addEntry(getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PAIR_TYPE_VAR_FIRST,getAliasPairTypeVarFirst()),
                new KeyValueMemberName(PAIR_TYPE_VAR_SECOND,getAliasPairTypeVarSecond())));
        return map_;
    }
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = new CustList<CustList<KeyValueMemberName>>();
        list_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getAliasIterator()),
                new KeyValueMemberName(HAS_NEXT,getAliasHasNext()),
                new KeyValueMemberName(NEXT,getAliasNext()),
                new KeyValueMemberName(ITERATOR_TABLE,getAliasIteratorTable()),
                new KeyValueMemberName(HAS_NEXT_PAIR,getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getAliasNextPair()),
                new KeyValueMemberName(GET_FIRST,getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getAliasGetSecond()),
                new KeyValueMemberName(ENUM_ORDINAL,getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_NAME,getAliasEnumName()),
                new KeyValueMemberName(SEED_GET,getAliasSeedGet())
        ));
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasEnumType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_NAME, getAliasEnumName()),
                new KeyValueMemberName(ENUM_ORDINAL, getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_PRED_VALUE_OF, getAliasEnumPredValueOf()),
                new KeyValueMemberName(ENUM_VALUES, getAliasEnumValues())));
        map_.addEntry(getAliasSeedDoubleGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SEED_GET, getAliasSeedGet())));
        map_.addEntry(getAliasSeedGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SEED_GET, getAliasSeedGet())));
        map_.addEntry(getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getAliasIterator())));
        map_.addEntry(getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(HAS_NEXT,getAliasHasNext()),
                new KeyValueMemberName(NEXT,getAliasNext())));
        map_.addEntry(getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TABLE,getAliasIteratorTable())));
        map_.addEntry(getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(HAS_NEXT_PAIR,getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getAliasNextPair())));
        map_.addEntry(getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FIRST,getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getAliasGetSecond())));
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
