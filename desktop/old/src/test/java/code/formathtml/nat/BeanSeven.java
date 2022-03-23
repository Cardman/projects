package code.formathtml.sample;
import code.bean.Bean;
import code.bean.nat.StringMapObjectSample;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.StringUtil;


public class BeanSeven extends Bean {

    private Composite composite = new Composite();

    private CustList<EnumNumber> combobox = new CustList<EnumNumber>(EnumNumber.values());

    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnumNumber());

    private NatStringTreeMap<Ints> numbers = new NatStringTreeMap<Ints>();

    private EnumNumber chosenNumber = EnumNumber.ONE;

    private CustList<EnumNumber> chosenNumbers = new CustList<EnumNumber>(EnumNumber.ONE,EnumNumber.FOUR);

    private String message="Test {0}";

    private NatTreeMapStringInteger tree = new NatTreeMapStringInteger();

    private StringMap<Integer> map = new StringMap<Integer>();

    private String selectedString = "ONE";

    private final CustList<Composite> composites = new CustList<Composite>();

    private String commonClass = "abba";

    private StringList strings = new StringList();

    private final Ints arrayInt;
    private StringMapObjectSample forms;

    public BeanSeven() {
        composite.setStrings(new StringList());
        for (EnumNumber e: EnumNumber.values()) {
            translations.put(e, Integer.toString(e.ordinal() + 1));
        }
        numbers.put("ONE", new Ints(1));
        numbers.put("TWO", new Ints(2,3));
        numbers.put("THREE", new Ints(4,5,6));
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

    public StringMapObjectSample getForms() {
        return forms;
    }

    public void setForms(StringMapObjectSample _forms) {
        forms = _forms;
    }

//    public void validateStringsSave() {
//        getForms().put("strings", new StringList(strings));
//    }

//    public void validateIntsSave() {
//        Ints nbs_ = new Ints();
//        for (Object i: arrayInt.list().toArray()) {
//            nbs_.add((Integer) i);
//        }
//        getForms().put("numbers", nbs_);
//    }
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
        return StringUtil.concatNbs("bean",_a+_b);
    }

    public String getSpanClasses(Long _one, Long _two, Long _three) {
        return StringUtil.concat("a",_one.toString(),"b",_two.toString(),"c",_three.toString());
    }

    public String getSpanClass(Long _one) {
        return StringUtil.concatNbs("page",_one);
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

    public void setComposite(Composite _composite) {
        composite = _composite;
    }

    public NatTreeMapStringInteger getTree() {
        return tree;
    }

    public StringMap<Integer> getMap() {
        return map;
    }

    public int getDouble(Long _index) {
        return 2 * _index.intValue();
    }

    public Ints getList(Long _index) {
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
        return StringUtil.concatNbs("page",_index);
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

    public NatStringTreeMap<Ints> getNumbers() {
        return numbers;
    }

    public void setNumbers(NatStringTreeMap<Ints> _numbers) {
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

    public void setTree(NatTreeMapStringInteger _tree) {
        tree = _tree;
    }

    public void setMap(StringMap<Integer> _map) {
        map = _map;
    }

    public void setStrings(StringList _strings) {
        strings = _strings;
    }

    @Override
    public void beforeDisplaying() {

    }
}
