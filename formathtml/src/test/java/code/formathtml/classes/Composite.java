package code.formathtml.classes;
import code.expressionlanguage.structs.NumberStruct;
import code.formathtml.util.ValueChangeEvent;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Displayable;


public class Composite implements Displayable {

    private int integer;

    private Integer objInteger;

    private CompositeSec composite = new CompositeSec();

    private int privateInt;

    private ValueChangeEvent changing;

    private StringList strings;

    private TreeMap<String,Integer> tree;

    private StringMap<Integer> map;

    private String string;

    private char myChar = 't';

    private boolean displayed = true;

    public Composite() {
        this(0);
    }

    public Composite(String _string) {
        string = _string;
    }

    private Composite(int _privateInt) {
        privateInt = _privateInt;
    }

    public Composite(String..._strings) {
        strings = new StringList(_strings);
    }

    public Composite(int _param, String..._strings) {
        privateInt = _param;
        strings = new StringList(_strings);
    }

    public CompositeSec getComposite() {
        return composite;
    }

    public void setComposite(CompositeSec _composite) {
        composite = _composite;
    }

    public void updateValue(ValueChangeEvent _changing) {
        getStrings().add(StringList.concat(Integer.toString(((NumberStruct)_changing.getNewValue()).intStruct())," ",Integer.toString(((NumberStruct)_changing.getOldValue()).intStruct())));
        changing = _changing;
    }

    public String getValue(ValueChangeEvent _changing) {
        return StringList.concat(Integer.toString(((NumberStruct)_changing.getNewValue()).intStruct())," ",Integer.toString(((NumberStruct)_changing.getOldValue()).intStruct()));
    }

    public ValueChangeEvent getChanging() {
        return changing;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int _integer) {
        integer = _integer;
    }

    public Integer getObjInteger() {
        return objInteger;
    }

    public void setObjInteger(Integer _objInteger) {
        objInteger = _objInteger;
    }

    public int getPrivateInt() {
        return privateInt;
    }

    public void setPrivateInt(int _privateInt) {
        privateInt = _privateInt;
    }

    public long varArgsNoParam(long... _args) {
        long l_ = 0;
        for (long l: _args) {
            l_ += l;
        }
        return l_;
    }

    public long varArgsArrays(long[]... _args) {
        long l_ = 0;
        for (long[] l: _args) {
            l_ += l.length;
        }
        return l_;
    }

    public int summum(int _other) {
        return integer + _other;
    }

    public int sum(Long _other) {
        return integer + _other.intValue();
    }

    public int sum(Long _other, Long _otherTwo) {
        return integer + _other.intValue() + _otherTwo.intValue();
    }

    public String getOverridenOne(String _string) {
        return "one";
    }
    public String getOverridenOne(Object _string) {
        return "two";
    }
    public String getOverridenOne(Boolean _string) {
        return "three";
    }
    public String getOverridenTwo(String _string) {
        return "one";
    }
    public String getOverridenTwo(Object _string) {
        return "two";
    }
    public String getOverridenThree(Double _double) {
        return "Double";
    }
    public String getOverridenThree(Long _double) {
        return "Long";
    }
    public String getOverridenThree(double _double) {
        return "double";
    }
    public String getOverridenThree(long _double) {
        return "long";
    }
    public String getOverridenFour(Long _double) {
        return "Long";
    }
    public String getOverridenFour(long _double) {
        return "long";
    }
    public String getOverridenFive(Long _double) {
        return "Long";
    }
    public String getOverridenFive(double _double) {
        return "double";
    }
    public String getOverridenSix(Long _double) {
        return "Long";
    }
    public String getOverridenSix(long _double) {
        return "long";
    }
    public String getOverridenSix(Double _double) {
        return "Double";
    }
    int sum() {
        return integer + privateInt;
    }

    public String getStringElt(int _ind) {
        return strings.get(_ind);
    }

    public StringList getStrings() {
        return strings;
    }

    public void setStrings(StringList _strings) {
        strings = _strings;
    }

    public TreeMap<String, Integer> getTree() {
        return tree;
    }

    public void setTree(TreeMap<String, Integer> _tree) {
        tree = _tree;
    }

    public StringMap<Integer> getMap() {
        return map;
    }

    public void setMap(StringMap<Integer> _map) {
        map = _map;
    }

    public StringList keys() {
        StringList list_ = new StringList(map.getKeys());
        list_.sort();
        return list_;
    }

    public String internMethod() {
        return "sample";
    }

    String privateMethod() {
        return "sample";
    }

    public String getString() {
        return string;
    }

    public void setString(String _string) {
        string = _string;
    }

    @Override
    public String display() {
        return new StringBuilder().append(integer).append(",").append(privateInt).append(",").append(strings.display()).toString();
    }

    public char getMyChar() {
        return myChar;
    }

    public void setMyChar(char _myChar) {
        myChar = _myChar;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean _displayed) {
        displayed = _displayed;
    }

    public void setChanging(ValueChangeEvent _changing) {
        changing = _changing;
    }
}
