package code.formathtml.classes;
import code.bean.Bean;
import code.formathtml.util.ValueChangeEvent;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class BeanFive extends Bean {

    private Composite composite = new Composite();

    private EnumNumbers combobox = new EnumNumbers(EnumNumber.values());

    private NatTreeMap<EnumNumber, String> translations = new NatTreeMap<EnumNumber, String>();

    private NatTreeMap<String,Numbers<Integer>> numbers = new NatTreeMap<String,Numbers<Integer>>();

    private EnumNumber chosenNumber = EnumNumber.ONE;

    private EnumNumbers chosenNumbers = new EnumNumbers(EnumNumber.ONE,EnumNumber.FOUR);

    private String message="Test {0}";

    private ValueChangeEvent changing;

    private NatTreeMap<String, Integer> tree = new NatTreeMap<String, Integer>();

    private StringMap<Integer> map = new StringMap<Integer>();

    private String selectedString = "ONE";

    private StringList selectedStrings = new StringList("ONE","FOUR");

    private EnumNumbers chosenNumbersNull;

    public BeanFive() {
        composite.setStrings(new StringList());
        for (EnumNumber e: EnumNumber.values()) {
            translations.put(e, Integer.toString(e.ordinal() + 1));
        }
        numbers.put("ONE", new Numbers<Integer>(1));
        numbers.put("TWO", new Numbers<Integer>(2,3));
        numbers.put("THREE", new Numbers<Integer>(4,5,6));
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

    public void updateValue(ValueChangeEvent _changing) {
        composite.getStrings().add(StringList.concat(((Integer)_changing.getNewValue()).toString()," ",((Integer)_changing.getOldValue()).toString()));
        changing = _changing;
    }

    public String invokeMethod(Long _index) {
        composite.getStrings().add(_index.toString());
        return "returned value";
    }

    public ValueChangeEvent getChanging() {
        return changing;
    }

    public NatTreeMap<String, Integer> getTree() {
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

    public NatTreeMap<EnumNumber, String> getTranslations() {
        return translations;
    }

    public void setTranslations(NatTreeMap<EnumNumber, String> _translations) {
        translations = _translations;
    }

    public NatTreeMap<String, Numbers<Integer>> getNumbers() {
        return numbers;
    }

    public void setNumbers(NatTreeMap<String, Numbers<Integer>> _numbers) {
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

    public EnumNumbers getChosenNumbersNull() {
        return chosenNumbersNull;
    }

    public void setChosenNumbersNull(EnumNumbers _chosenNumbersNull) {
        chosenNumbersNull = _chosenNumbersNull;
    }

    public void setChanging(ValueChangeEvent _changing) {
        changing = _changing;
    }

    public void setTree(NatTreeMap<String, Integer> _tree) {
        tree = _tree;
    }

    public void setMap(StringMap<Integer> _map) {
        map = _map;
    }

}
