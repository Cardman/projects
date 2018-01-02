package code.formathtml.classes;
import code.bean.Accessible;
import code.formathtml.util.ValueChangeEvent;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Displayable;

@SuppressWarnings("static-method")
public class Composite implements Displayable {

    @Accessible
    private int integer;

    private int privateInt;

    private ValueChangeEvent changing;

    @Accessible
    private StringList strings;

    @Accessible
    private TreeMap<String,Integer> tree;

    @Accessible
    private StringMap<Integer> map;

    @Accessible
    private MyMap<String, Integer> myMap;

    @Accessible
    private String string;

    @Accessible
    private char myChar = 't';

    @Accessible
    private boolean displayed = true;

    public Composite() {
        this(0);
    }

    @Accessible
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

    public void updateValue(ValueChangeEvent _changing) {
        getStrings().add(StringList.concat(((Integer)_changing.getNewValue()).toString()," ",((Integer)_changing.getOldValue()).toString()));
        changing = _changing;
    }

    public String getValue(ValueChangeEvent _changing) {
        return StringList.concat(((Integer)_changing.getNewValue()).toString()," ",((Integer)_changing.getOldValue()).toString());
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

    @Accessible
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

    @Accessible
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
    public String display() {
        return integer+","+privateInt+","+strings.display()+","+tree+","+map+","+myMap;
    }
}
