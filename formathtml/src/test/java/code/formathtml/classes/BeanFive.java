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
public class BeanFive extends Bean {

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
    private NatTreeMap<String, Integer> tree = new NatTreeMap<String, Integer>();

    @Accessible
    private StringMap<Integer> map = new StringMap<Integer>();

    @Accessible
    private String selectedString = "ONE";

    @Accessible
    private StringList selectedStrings = new StringList("ONE","FOUR");

    @Accessible
    private CustList<EnumNumber> chosenNumbersNull;

    public BeanFive() {
        composite.setStrings(new StringList());
        for (EnumNumber e: EnumNumber.values()) {
            translations.put(e, Integer.toString(e.ordinal() + 1));
        }
        numbers.put("ONE", new Numbers<Integer>(1));
        numbers.put("TWO", new Numbers<Integer>(2,3));
        numbers.put("THREE", new Numbers<Integer>(4,5,6));
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
        composite.getStrings().add(_changing.getNewValue()+" "+_changing.getOldValue());
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

    public NatTreeMap<String, Integer> getTree() {
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
    public CustList<EnumNumber> getDefaultChoices() {
        if (chosenNumber == null) {
            return new CustList<EnumNumber>(EnumNumber.THREE, EnumNumber.TWO);
        }
        return new CustList<EnumNumber>(EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1]);
    }

    @Accessible
    String goToPage() {
        return "page";
    }

    @Accessible
    String go() {
        getForms().put("selectedStrings", selectedStrings);
        getForms().put("chosenNumbers", chosenNumbers);
        getForms().put("chosenNumbersNull", chosenNumbersNull);
        if (selectedStrings.size() <= 2) {
            return "change";
        }
        return "no_change";
    }

    @Accessible
    String goToNullPage() {
        return null;
    }

    @Accessible
    String goToPage(Long _index) {
        return "page"+_index;
    }
}
