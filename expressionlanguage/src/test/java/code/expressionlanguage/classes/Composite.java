package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

@SuppressWarnings("static-method")
public class Composite {

    @AccEl
    private int integer;

    private int privateInt;

    @AccEl
    private StringList strings;

    @AccEl
    private TreeMap<String,Integer> tree;

    @AccEl
    private StringMap<Integer> map;

    @AccEl
    private MyMap<String, Integer> myMap;

    @AccEl
    private String string;

    @AccEl
    private char myChar = 't';

    @AccEl
    private boolean displayed = true;

    public Composite() {
        this(0);
    }

    @AccEl
    private Composite(String _string) {
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
    public int getInteger() {
        return integer;
    }

    public void setInteger(int _integer) {
        integer = _integer;
    }

    public int getPrivateInt() {
        return privateInt;
    }

    public void setPrivateInt(int _privateInt) {
        privateInt = _privateInt;
    }

    public String varArgsParam(String _format, Object... _args) {
        return StringList.simpleFormat(_format, _args);
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

    @AccEl
    private StringList keys() {
        StringList list_ = new StringList(map.getKeys());
        list_.sort();
        return list_;
    }

    public MyMap<String, Integer> getMyMap() {
        return myMap;
    }

    public void setMyMap(MyMap<String, Integer> _myMap) {
        myMap = _myMap;
    }

    @AccEl
    String internMethod() {
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
    public String toString() {
        return integer+","+privateInt+","+strings+","+tree+","+map+","+myMap;
    }
}
