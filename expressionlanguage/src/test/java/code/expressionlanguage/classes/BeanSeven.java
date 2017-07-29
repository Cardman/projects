package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class BeanSeven {

    @AccEl
    private Composite composite = new Composite();

    @AccEl
    private CustList<EnumNumber> combobox = new CustList<EnumNumber>(EnumNumber.values());

    @AccEl
    private NatTreeMap<EnumNumber, String> translations = new NatTreeMap<EnumNumber, String>();

    @AccEl
    private NatTreeMap<String,Numbers<Integer>> numbers = new NatTreeMap<String,Numbers<Integer>>();

    @AccEl
    private EnumNumber chosenNumber = EnumNumber.ONE;

    @AccEl
    private CustList<EnumNumber> chosenNumbers = new CustList<EnumNumber>(EnumNumber.ONE,EnumNumber.FOUR);

    @AccEl
    private String message="Test {0}";

    @AccEl
    private NatTreeMap<String, Integer> tree = new NatTreeMap<String, Integer>();

    @AccEl
    private StringMap<Integer> map = new StringMap<Integer>();

    @AccEl
    private String selectedString = "ONE";

    private CustList<Composite> composites = new CustList<Composite>();

    @AccEl
    private String commonClass = "abba";

    private StringList strings = new StringList();

    @AccEl
    private int[] arrayInt = new int[2];
    private int[][] arrayInts = new int[2][2];

    public BeanSeven() {
        composite.setStrings(new StringList());
        for (EnumNumber e: EnumNumber.values()) {
            translations.put(e, Integer.toString(e.ordinal() + 1));
        }
        numbers.put("ONE", new Numbers<Integer>(1));
        numbers.put("TWO", new Numbers<Integer>(2,3));
        numbers.put("THREE", new Numbers<Integer>(4,5,6));
        for (int i = 0; i < 2; i++) {
            Composite c_ = new Composite();
            c_.setString(String.valueOf(i));
            composites.add(c_);
        }
        strings.add("FIRST");
        strings.add("SECOND");
        tree.put("keyone", 1);
        tree.put("keytwo", 2);
        arrayInt[0]=1;
        arrayInt[1]=3;
        arrayInts[0] = new int[2];
        arrayInts[1] = new int[2];
    }

    public void validateStrings() {
    }

    public void validateMap() {
    }
    public StringList getStrings() {
        return strings;
    }
    public int[] getArrayInt() {
        return arrayInt;
    }
    public int[][] getArrayInts() {
        return arrayInts;
    }

    public double getDouble(Double _double) {
        return 2 * _double;
    }

    public String goTwoArgs(int _a, int _b) {
        return "bean"+(_a+_b);
    }

    public String getSpanClasses(Long _one, Long _two, Long _three) {
        return "a"+_one+"b"+_two+"c"+_three;
    }

    public String getSpanClass(Long _one) {
        return "a"+_one;
    }

    public StringList sortedNumberKeys() {
        return new StringList(numbers.getKeys());
    }

    public CustList<Composite> getComposites() {
        return composites;
    }

    public String getTrans(Long _index) {
        return translations.getValue(_index.intValue());
    }

    public Composite getComposite() {
        return composite;
    }

    public boolean hasMoreThanOne() {
        return composite.getStrings().size() > 1;
    }

    public boolean hasMoreThanZero() {
        return composite.getStrings().size() > 0;
    }

    public void setComposite(Composite _composite) {
        composite = _composite;
    }

    @AccEl
    String invokeMethod(Long _index) {
        composite.getStrings().add(_index.toString());
        return "returned value";
    }

    public NatTreeMap<String, Integer> getTree() {
        return tree;
    }

    public StringMap<Integer> getMap() {
        return map;
    }

    @AccEl
    StringList getKeys() {
        StringList list_ = new StringList(map.getKeys());
        list_.sort();
        return list_;
    }

    @AccEl
    int getDouble(Long _index) {
        return 2 * _index.intValue();
    }

    @AccEl
    Numbers<Integer> getList(Long _index) {
        if (_index >= numbers.size()) {
            return numbers.getValue(numbers.size() - 1);
        }
        return numbers.getValue(_index.intValue());
    }

    @AccEl
    public EnumNumber getDefaultChoice() {
        if (chosenNumber == null) {
            return EnumNumber.THREE;
        }
        return EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1];
    }

    @AccEl
    String goToPage() {
        return "page";
    }

    @AccEl
    String goToNullPage() {
        return null;
    }

    @AccEl
    String goToPage(Long _index) {
        return "page"+_index;
    }
}
