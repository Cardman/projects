package code.formathtml.classes;
import code.bean.Accessible;
import code.bean.Bean;
import code.formathtml.util.ValueChangeEvent;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class BeanSeven extends Bean {

    @Accessible
    private Composite composite = new Composite();

    @Accessible
    private CustList<EnumNumber> combobox = new CustList<EnumNumber>(EnumNumber.values());

    @Accessible
    private NatTreeMap<EnumNumber, String> translations = new NatTreeMap<EnumNumber, String>();

    @Accessible
    private NatTreeMap<String,Numbers<Integer>> numbers = new NatTreeMap<String,Numbers<Integer>>();

    @Accessible
    private EnumNumber chosenNumber = EnumNumber.ONE;

    @Accessible
    private CustList<EnumNumber> chosenNumbers = new CustList<EnumNumber>(EnumNumber.ONE,EnumNumber.FOUR);

    @Accessible
    private String message="Test {0}";

    private ValueChangeEvent changing;

    @Accessible
    private NatTreeMapStringInteger tree = new NatTreeMapStringInteger();

    @Accessible
    private StringMap<Integer> map = new StringMap<Integer>();

    @Accessible
    private String selectedString = "ONE";

    private CustList<Composite> composites = new CustList<Composite>();

    @Accessible
    private String commonClass = "abba";

    private StringList strings = new StringList();

    @Accessible
    private int[] arrayInt = new int[2];

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
        setClassName("code.formathtml.classes.BeanSeven");
    }

    @Override
    public void beforeDisplaying() {
//        strings.clear();
//        if (!getForms().contains("strings")) {
//            strings.add("FIRST");
//            strings.add("SECOND");
//        } else {
//            strings.addAll((StringList)getForms().getVal("strings"));
//        }
    }

    public void validateStrings() {
    }

    public void validateStringsSave() {
        getForms().put("strings", new StringList(strings));
    }

    public void validateMap() {
    }
    public void validateIntsSave() {
        Ints nbs_ = new Ints();
        for (int i: arrayInt) {
            nbs_.add(i);
        }
        getForms().put("numbers", nbs_);
    }
    public StringList getStrings() {
        return strings;
    }
    public int[] getArrayInt() {
        return arrayInt;
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
        return StringList.concatNbs("page",_one);
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

    public void updateValue(ValueChangeEvent _changing) {
        composite.getStrings().add(StringList.concat(((Integer)_changing.getNewValue()).toString()," ",((Integer)_changing.getOldValue()).toString()));
        changing = _changing;
    }

    @Accessible
    String invokeMethod(Long _index) {
        composite.getStrings().add(_index.toString());
        return "returned value";
    }

    public ValueChangeEvent getChanging() {
        return changing;
    }

    public NatTreeMapStringInteger getTree() {
        return tree;
    }

    public StringMap<Integer> getMap() {
        return map;
    }

    @Accessible
    StringList getKeys() {
        StringList list_ = new StringList(map.getKeys());
        list_.sort();
        return list_;
    }

    @Accessible
    int getDouble(Long _index) {
        return 2 * _index.intValue();
    }

    @Accessible
    Numbers<Integer> getList(Long _index) {
        if (_index >= numbers.size()) {
            return numbers.getValue(numbers.size() - 1);
        }
        return numbers.getValue(_index.intValue());
    }

    @Accessible
    public EnumNumber getDefaultChoice() {
        if (chosenNumber == null) {
            return EnumNumber.THREE;
        }
        return EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1];
    }

    @Accessible
    String goToPage() {
        return "page";
    }

    @Accessible
    String goToNullPage() {
        return null;
    }

    @Accessible
    String goToPage(Long _index) {
        return StringList.concatNbs("page",_index);
    }
}
