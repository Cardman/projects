package code.expressionlanguage.classes;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

@SuppressWarnings("static-method")
public class Composite {


    private int integer;

    private int privateInt;


    private StringList strings;


    private TreeMap<String,Integer> tree;


    private StringMap<Integer> map;


    private String string;

    public Composite() {
        this(0);
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

    public String getOverridenFour() {
        return "Long";
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

    public StringList getStrings() {
        return strings;
    }

    public void setStrings(StringList _strings) {
        strings = _strings;
    }


    public String getString() {
        return string;
    }

    public void setString(String _string) {
        string = _string;
    }
}
