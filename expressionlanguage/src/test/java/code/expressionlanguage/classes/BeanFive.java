package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class BeanFive {

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

    @AccEl
    private StringList selectedStrings = new StringList("ONE","FOUR");

    @AccEl
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
    public CustList<EnumNumber> getDefaultChoices() {
        if (chosenNumber == null) {
            return new CustList<EnumNumber>(EnumNumber.THREE, EnumNumber.TWO);
        }
        return new CustList<EnumNumber>(EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1]);
    }

    @AccEl
    String goToPage() {
        return "page";
    }

    @AccEl
    String go() {
        if (selectedStrings.size() <= 2) {
            return "change";
        }
        return "no_change";
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
