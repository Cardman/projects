package code.expressionlanguage.classes;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class BeanSeven {


    private Composite composite = new Composite();


    private NatTreeMap<EnumNumber, String> translations = new NatTreeMap<EnumNumber, String>();


    private NatTreeMap<String,Numbers<Integer>> numbers = new NatTreeMap<String,Numbers<Integer>>();


    private EnumNumber chosenNumber = EnumNumber.ONE;

    private NatTreeMap<String, Integer> tree = new NatTreeMap<String, Integer>();


    private StringMap<Integer> map = new StringMap<Integer>();

    private CustList<Composite> composites = new CustList<Composite>();

    private StringList strings = new StringList();


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
        return StringList.concatNbs("bean",_a+_b);
    }

    public String getSpanClasses(Long _one, Long _two, Long _three) {
        return StringList.concat("a",_one.toString(),"b",_two.toString(),"c",_three.toString());
    }

    public String getSpanClass(Long _one) {
        return StringList.concatNbs("a",_one);
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


    StringList getKeys() {
        StringList list_ = new StringList(map.getKeys());
        list_.sort();
        return list_;
    }


    int getDouble(Long _index) {
        return 2 * _index.intValue();
    }


    Numbers<Integer> getList(Long _index) {
        if (_index >= numbers.size()) {
            return numbers.getValue(numbers.size() - 1);
        }
        return numbers.getValue(_index.intValue());
    }


    public EnumNumber getDefaultChoice() {
        if (chosenNumber == null) {
            return EnumNumber.THREE;
        }
        return EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1];
    }


    String goToPage() {
        return "page";
    }


    String goToNullPage() {
        return null;
    }


    String goToPage(Long _index) {
        return StringList.concatNbs("page",_index);
    }
}
