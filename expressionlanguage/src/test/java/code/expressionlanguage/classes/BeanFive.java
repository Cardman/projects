package code.expressionlanguage.classes;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

@SuppressWarnings("static-method")
public class BeanFive {


    private Composite composite = new Composite();


    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnum<EnumNumber>());


    private NatStringTreeMap<Numbers<Integer>> numbers = new NatStringTreeMap<Numbers<Integer>>();


    private EnumNumber chosenNumber = EnumNumber.ONE;

    private NatStringTreeMap< Integer> tree = new NatStringTreeMap< Integer>();


    private StringMap<Integer> map = new StringMap<Integer>();


    private StringList selectedStrings = new StringList("ONE","FOUR");


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


    String invokeMethod(Long _index) {
        composite.getStrings().add(_index.toString());
        return "returned value";
    }

    public NatStringTreeMap< Integer> getTree() {
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


    public CustList<EnumNumber> getDefaultChoices() {
        if (chosenNumber == null) {
            return new CustList<EnumNumber>(EnumNumber.THREE, EnumNumber.TWO);
        }
        return new CustList<EnumNumber>(EnumNumber.values()[EnumNumber.values().length - chosenNumber.ordinal() - 1]);
    }


    String goToPage() {
        return "page";
    }


    String go() {
        if (selectedStrings.size() <= 2) {
            return "change";
        }
        return "no_change";
    }


    String goToNullPage() {
        return null;
    }


    String goToPage(Long _index) {
        return StringList.concatNbs("page",_index);
    }
}
