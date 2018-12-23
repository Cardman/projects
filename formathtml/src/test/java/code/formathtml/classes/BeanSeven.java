package code.formathtml.classes;
import code.bean.Bean;
import code.expressionlanguage.structs.NumberStruct;
import code.formathtml.util.ValueChangeEvent;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

@SuppressWarnings("static-method")
public class BeanSeven extends Bean {

    private Composite composite = new Composite();

    private CustList<EnumNumber> combobox = new CustList<EnumNumber>(EnumNumber.values());

    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnum<EnumNumber>());

    private NatStringTreeMap<Numbers<Integer>> numbers = new NatStringTreeMap<Numbers<Integer>>();

    private EnumNumber chosenNumber = EnumNumber.ONE;

    private CustList<EnumNumber> chosenNumbers = new CustList<EnumNumber>(EnumNumber.ONE,EnumNumber.FOUR);

    private String message="Test {0}";

    private ValueChangeEvent changing;

    private NatTreeMapStringInteger tree = new NatTreeMapStringInteger();

    private StringMap<Integer> map = new StringMap<Integer>();

    private String selectedString = "ONE";

    private CustList<Composite> composites = new CustList<Composite>();

    private String commonClass = "abba";

    private StringList strings = new StringList();

    private Ints arrayInt;

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
        arrayInt = new Ints();
        arrayInt.add(1);
        arrayInt.add(3);
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
    public Ints getArrayInt() {
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
        composite.getStrings().add(StringList.concat(((NumberStruct)_changing.getNewValue()).getInstance().toString()," ",((NumberStruct)_changing.getOldValue()).getInstance().toString()));
        changing = _changing;
    }

    public String invokeMethod(Long _index) {
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

    public StringList getKeys() {
        StringList list_ = new StringList(map.getKeys());
        list_.sort();
        return list_;
    }

    public int getDouble(Long _index) {
        return 2 * _index.intValue();
    }

    public Numbers<Integer> getList(Long _index) {
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

    public String goToPage() {
        return "page";
    }

    public String goToNullPage() {
        return null;
    }

    public String goToPage(Long _index) {
        return StringList.concatNbs("page",_index);
    }

    public CustList<EnumNumber> getCombobox() {
        return combobox;
    }

    public void setCombobox(CustList<EnumNumber> _combobox) {
        combobox = _combobox;
    }

    public TreeMap<EnumNumber, String> getTranslations() {
        return translations;
    }

    public void setTranslations(TreeMap<EnumNumber, String> _translations) {
        translations = _translations;
    }

    public NatStringTreeMap< Numbers<Integer>> getNumbers() {
        return numbers;
    }

    public void setNumbers(NatStringTreeMap< Numbers<Integer>> _numbers) {
        numbers = _numbers;
    }

    public EnumNumber getChosenNumber() {
        return chosenNumber;
    }

    public void setChosenNumber(EnumNumber _chosenNumber) {
        chosenNumber = _chosenNumber;
    }

    public CustList<EnumNumber> getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(CustList<EnumNumber> _chosenNumbers) {
        chosenNumbers = _chosenNumbers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String _message) {
        message = _message;
    }

    public String getSelectedString() {
        return selectedString;
    }

    public void setSelectedString(String _selectedString) {
        selectedString = _selectedString;
    }

    public String getCommonClass() {
        return commonClass;
    }

    public void setCommonClass(String _commonClass) {
        commonClass = _commonClass;
    }

    public void setChanging(ValueChangeEvent _changing) {
        changing = _changing;
    }

    public void setTree(NatTreeMapStringInteger _tree) {
        tree = _tree;
    }

    public void setMap(StringMap<Integer> _map) {
        map = _map;
    }

    public void setComposites(CustList<Composite> _composites) {
        composites = _composites;
    }

    public void setStrings(StringList _strings) {
        strings = _strings;
    }
}
