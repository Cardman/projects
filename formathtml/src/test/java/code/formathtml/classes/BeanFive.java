package code.formathtml.classes;
import code.bean.Bean;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;


public class BeanFive extends Bean {

    private Composite composite = new Composite();

    private EnumNumbers combobox = new EnumNumbers(EnumNumber.values());

    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnumNumber());

    private NatStringTreeMap<Ints> numbers = new NatStringTreeMap<Ints>();

    private EnumNumber chosenNumber = EnumNumber.ONE;

    private EnumNumbers chosenNumbers = new EnumNumbers(EnumNumber.ONE,EnumNumber.FOUR);

    private String message="Test {0}";

    private NatStringTreeMap< Integer> tree = new NatStringTreeMap< Integer>();

    private StringMap<Integer> map = new StringMap<Integer>();

    private String selectedString = "ONE";

    private StringList selectedStrings = new StringList("ONE","FOUR");

    private StringList chosenNumbersNull;

    public BeanFive() {
        composite.setStrings(new StringList());
        for (EnumNumber e: EnumNumber.values()) {
            translations.put(e, Integer.toString(e.ordinal() + 1));
        }
        numbers.put("ONE", new Ints(1));
        numbers.put("TWO", new Ints(2,3));
        numbers.put("THREE", new Ints(4,5,6));
        setClassName("code.formathtml.classes.BeanFive");
    }

    public String getTrans(Long _index) {
        return translations.getValue(_index.intValue());
    }

    public Composite getComposite() {
        return composite;
    }

    public void setComposite(Composite _composite) {
        composite = _composite;
    }

    public String invokeMethod(Long _index) {
        composite.getStrings().add(_index.toString());
        return "returned value";
    }

    public NatStringTreeMap< Integer> getTree() {
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

    public CustList<EnumNumber> getDefaultChoices() {
        if (chosenNumber == null) {
            return new CustList<EnumNumber>(EnumNumber.THREE, EnumNumber.TWO);
        }
        return new CustList<EnumNumber>(EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1]);
    }

    public String goToPage() {
        return "page";
    }

    public String go() {
        getForms().put("selectedStrings", selectedStrings);
        getForms().put("chosenNumbers", chosenNumbers);
        getForms().put("chosenNumbersNull", chosenNumbersNull);
        if (selectedStrings.size() <= 2) {
            return "change";
        }
        return "no_change";
    }

    public String goToNullPage() {
        return null;
    }

    public String goToPage(Long _index) {
        return StringList.concatNbs("page",_index);
    }

    public EnumNumbers getCombobox() {
        return combobox;
    }

    public void setCombobox(EnumNumbers _combobox) {
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

    public EnumNumbers getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(EnumNumbers _chosenNumbers) {
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

    public StringList getSelectedStrings() {
        return selectedStrings;
    }

    public void setSelectedStrings(StringList _selectedStrings) {
        selectedStrings = _selectedStrings;
    }

    public StringList getChosenNumbersNull() {
        return chosenNumbersNull;
    }

    public void setChosenNumbersNull(StringList _chosenNumbersNull) {
        chosenNumbersNull = _chosenNumbersNull;
    }

    public void setTree(NatStringTreeMap< Integer> _tree) {
        tree = _tree;
    }

    public void setMap(StringMap<Integer> _map) {
        map = _map;
    }

    @Override
    public void beforeDisplaying() {

    }
}
