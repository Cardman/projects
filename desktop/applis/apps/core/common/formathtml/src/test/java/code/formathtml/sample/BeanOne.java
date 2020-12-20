package code.formathtml.sample;
import code.bean.Bean;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.StringUtil;


public class BeanOne extends Bean {

    private Composite composite = new Composite();

    private EnumNumbers combobox = new EnumNumbers(EnumNumber.values());

    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnumNumber());

    private NatStringTreeMap<Ints> numbers = new NatStringTreeMap<Ints>();

    private String chosenNumber = EnumNumber.ONE.name();

    private EnumNumbers chosenNumbers = new EnumNumbers(EnumNumber.ONE,EnumNumber.FOUR);

    private String message="Test {0}";

    private NatStringTreeMap< Integer> tree = new NatStringTreeMap< Integer>();

    private StringMap<Integer> map = new StringMap<Integer>();

    private String selectedString = "ONE";

    private CustList<Composite> composites = new CustList<Composite>();

    private String commonClass = "abba";

    private StringList strings = new StringList();

    public BeanOne() {
        getMap().put("ONE",1);
        getMap().put("TWO",2);
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
        setClassName("code.formathtml.classes.BeanOne");
    }

    public void validateStrings() {
        strings.clear();
        for (Composite c: composites) {
            strings.add(c.getString());
        }
    }

    public StringList getStrings() {
        return strings;
    }

    public String getSpanClasses(Long _one, Long _two, Long _three) {
        return StringUtil.concat("a",_one.toString(),"b",_two.toString(),"c",_three.toString());
    }

    public String getSpanClass(Long _one) {
        return StringUtil.concatNbs("a",_one);
    }

    public String getStandard(String _obj) {
        return _obj;
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

    public int getList(Integer _index) {
        return _index;
    }

    public EnumNumber getDefaultChoice() {
        return EnumNumber.THREE;
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

    public String getChosenNumber() {
        return chosenNumber;
    }

    public void setChosenNumber(String _chosenNumber) {
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

    public String getCommonClass() {
        return commonClass;
    }

    public void setCommonClass(String _commonClass) {
        commonClass = _commonClass;
    }

    public void setTree(NatStringTreeMap< Integer> _tree) {
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

    @Override
    public void beforeDisplaying() {

    }
}
