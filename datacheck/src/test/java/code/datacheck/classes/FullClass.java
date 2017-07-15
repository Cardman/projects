package code.datacheck.classes;
import java.io.Serializable;

import code.datacheck.CheckedData;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

@RwXml
public class FullClass {

    private StringMap<Integer> map;

    private StringList listString;

    private CustList<StringList> listListString;

    private Pair<Primitive, String> pair;

    private Pair<PseudoPrimitive, String> otherPair;

    private Pair<Pair<Primitive, String>, Pair<Integer, StringList>> pairs;

    private int integer;

    private Integer integerObj;

    private TemplateList tempList;

    private TemplateString<Boolean> tempStrings;

    private Template<String, EnumNumbers> temp;

    private int[] arrayInts;

    private String[][] arraysStrings;

    private Listable<int[]> listArray;

    private boolean bit;

    private String str;

    private BooleanList bits;

    private Pair<CheckedPrimitive, CheckedClass> score;

    private EnumNumbers enumEx;

    private Pair<Pair<Boolean,StringMap<String>>,String> three;

    private Primitive primitive;

    private Listable<?>[] arrayLists;

    private MyList<Integer> myIntegers;

    private Snapshot<String> snap;

    private MyMap<Integer> mapIntegers;

    @CheckedData
    private StringList checked;

    private CheckedClass checkedClass;

    private UncheckedClass uncheckedClass;

    private CustList<UncheckedClass> container;

    private StringMap<CheckedPrimitive> checkedPrim;

    private CustList<ComplexMap<String,Character,Serializable>> listMaps;

//    private java.util.Map<String,Boolean> interfMap;
    private ListableEntries<String,Boolean> interfMap;

//    private java.util.List<String> interfList;
    private Listable<String> interfList;

    public StringMap<Integer> getMap() {
        return map;
    }

    public void setMap(StringMap<Integer> _map) {
        map = _map;
    }

    public StringList getListString() {
        return listString;
    }

    public void setListString(StringList _listString) {
        listString = _listString;
    }

    public CustList<StringList> getListListString() {
        return listListString;
    }

    public void setListListString(CustList<StringList> _listListString) {
        listListString = _listListString;
    }

    public Pair<Primitive, String> getPair() {
        return pair;
    }

    public void setPair(Pair<Primitive, String> _pair) {
        pair = _pair;
    }

    public Pair<PseudoPrimitive, String> getOtherPair() {
        return otherPair;
    }

    public void setOtherPair(Pair<PseudoPrimitive, String> _otherPair) {
        otherPair = _otherPair;
    }

    public Pair<Pair<Primitive, String>, Pair<Integer, StringList>> getPairs() {
        return pairs;
    }

    public void setPairs(Pair<Pair<Primitive, String>, Pair<Integer, StringList>> _pairs) {
        pairs = _pairs;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int _integer) {
        integer = _integer;
    }

    public Integer getIntegerObj() {
        return integerObj;
    }

    public void setIntegerObj(Integer _integerObj) {
        integerObj = _integerObj;
    }

    public TemplateList getTempList() {
        return tempList;
    }

    public void setTempList(TemplateList _tempList) {
        tempList = _tempList;
    }

    public TemplateString<Boolean> getTempStrings() {
        return tempStrings;
    }

    public void setTempStrings(TemplateString<Boolean> _tempStrings) {
        tempStrings = _tempStrings;
    }

    public Template<String, EnumNumbers> getTemp() {
        return temp;
    }

    public void setTemp(Template<String, EnumNumbers> _temp) {
        temp = _temp;
    }

    public int[] getArrayInts() {
        return arrayInts;
    }

    public void setArrayInts(int[] _arrayInts) {
        arrayInts = _arrayInts;
    }

    public String[][] getArraysStrings() {
        return arraysStrings;
    }

    public void setArraysStrings(String[][] _arraysStrings) {
        arraysStrings = _arraysStrings;
    }

    public Listable<int[]> getListArray() {
        return listArray;
    }

    public void setListArray(Listable<int[]> _listArray) {
        listArray = _listArray;
    }

    public boolean isBit() {
        return bit;
    }

    public void setBit(boolean _bit) {
        bit = _bit;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String _str) {
        str = _str;
    }

    public BooleanList getBits() {
        return bits;
    }

    public void setBits(BooleanList _bits) {
        bits = _bits;
    }

    public Pair<CheckedPrimitive, CheckedClass> getScore() {
        return score;
    }

    public void setScore(Pair<CheckedPrimitive, CheckedClass> _score) {
        score = _score;
    }

    public EnumNumbers getEnumEx() {
        return enumEx;
    }

    public void setEnumEx(EnumNumbers _enumEx) {
        enumEx = _enumEx;
    }

    public Pair<Pair<Boolean,StringMap<String>>,String> getThree() {
        return three;
    }

    public void setThree(Pair<Pair<Boolean,StringMap<String>>,String> _three) {
        three = _three;
    }

    public Primitive getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Primitive _primitive) {
        primitive = _primitive;
    }

    public Listable<?>[] getArrayLists() {
        return arrayLists;
    }

    public void setArrayLists(Listable<?>[] _arrayLists) {
        arrayLists = _arrayLists;
    }

    public MyList<Integer> getMyIntegers() {
        return myIntegers;
    }

    public void setMyIntegers(MyList<Integer> _myIntegers) {
        myIntegers = _myIntegers;
    }

    public StringList getChecked() {
        return checked;
    }

    public void setChecked(StringList _checked) {
        checked = _checked;
    }

    public Snapshot<String> getSnap() {
        return snap;
    }

    public void setSnap(Snapshot<String> _snap) {
        snap = _snap;
    }

    public MyMap<Integer> getMapIntegers() {
        return mapIntegers;
    }

    public void setMapIntegers(MyMap<Integer> _mapIntegers) {
        mapIntegers = _mapIntegers;
    }

    public CheckedClass getCheckedClass() {
        return checkedClass;
    }

    public void setCheckedClass(CheckedClass _checkedClass) {
        checkedClass = _checkedClass;
    }

    public UncheckedClass getUncheckedClass() {
        return uncheckedClass;
    }

    public void setUncheckedClass(UncheckedClass _uncheckedClass) {
        uncheckedClass = _uncheckedClass;
    }

    public CustList<UncheckedClass> getContainer() {
        return container;
    }

    public void setContainer(CustList<UncheckedClass> _container) {
        container = _container;
    }

    public StringMap<CheckedPrimitive> getCheckedPrim() {
        return checkedPrim;
    }

    public void setCheckedPrim(StringMap<CheckedPrimitive> _checkedPrim) {
        checkedPrim = _checkedPrim;
    }

    public CustList<ComplexMap<String,Character,Serializable>> getListMaps() {
        return listMaps;
    }

    public void setListMaps(CustList<ComplexMap<String,Character,Serializable>> _listMaps) {
        listMaps = _listMaps;
    }

    public ListableEntries<String, Boolean> getInterfMap() {
        return interfMap;
    }

    public void setInterfMap(ListableEntries<String, Boolean> _interfMap) {
        interfMap = _interfMap;
    }

    public Listable<String> getInterfList() {
        return interfList;
    }

    public void setInterfList(Listable<String> _interfList) {
        interfList = _interfList;
    }
}
