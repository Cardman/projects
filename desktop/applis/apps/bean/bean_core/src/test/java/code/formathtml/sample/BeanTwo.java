package code.formathtml.sample;
import code.bean.Bean;
import code.formathtml.nat.StringMapObject;
import code.util.CustList;
import code.util.core.StringUtil;


public class BeanTwo extends Bean {

    private boolean checked;

    private Composite composite = new Composite();

    private String typedString;

    private EnumNumber chosenNumber;

    private int typedInt;
    private short typedShort;

    private Rate rate;

    private Boolean nullableCheckbox;

    private Long nullableInt;

    private String newOld = "";

    private String field;

    private String choose;
    private StringMapObject forms = new StringMapObject();

    public BeanTwo() {
        setClassName("code.formathtml.classes.BeanTwo");
    }

    public StringMapObject getForms() {
        return forms;
    }

    public void setForms(StringMapObject _forms) {
        forms = _forms;
    }
    @Override
    public void beforeDisplaying() {
        chosenNumber = EnumNumber.TWO;
        if (typedString == null) {
            typedString = "TYPED_STRING";
        } else {
            typedString = StringUtil.concatNbs(typedString,2);
        }
    }

    public CustList<EnumNumber> getChosenNumbers() {
        CustList<EnumNumber> enums_ = new CustList<EnumNumber>();
        enums_.add(EnumNumber.ONE);
        enums_.add(EnumNumber.TWO);
        enums_.add(EnumNumber.THREE);
        return enums_;
    }

    public String go(Long _index) {
        getForms().put("checked", checked);
        getForms().put("typedString", typedString);
        getForms().put("chosenNumber", chosenNumber);
        getForms().put("typedInt", typedInt);
        getForms().put("rate", rate);
        getForms().put("nullableCheckbox", nullableCheckbox);
        getForms().put("nullableInt", nullableInt);
        getForms().put("field", field);
        if (typedString.length() + _index.intValue() > 5) {
            return "change";
        }
        return "no_change";
    }

    public String go() {
        getForms().put("checked", checked);
        getForms().put("typedString", typedString);
        getForms().put("chosenNumber", chosenNumber);
        getForms().put("typedInt", typedInt);
        getForms().put("rate", rate);
        getForms().put("nullableCheckbox", nullableCheckbox);
        getForms().put("nullableInt", nullableInt);
        getForms().put("field", field);
        if (typedString.length() > 5) {
            return "change";
        }
        return "no_change";
    }

    public String goTextArea() {
        getForms().put("checked", checked);
        getForms().put("typedString", typedString);
        getForms().put("chosenNumber", chosenNumber);
        getForms().put("typedInt", typedInt);
        getForms().put("rate", rate);
        getForms().put("nullableCheckbox", nullableCheckbox);
        getForms().put("nullableInt", nullableInt);
        getForms().put("field", field);
        if (typedString.length() > 5) {
            return "change";
        }
        return "no_change";
    }

    public void validate() {
        if (typedString == null) {
            typedString = "";
        }
        getForms().put("checked", checked);
        getForms().put("typedString", typedString);
        getForms().put("chosenNumber", chosenNumber);
        getForms().put("typedInt", typedInt);
        getForms().put("rate", rate);
        getForms().put("nullableCheckbox", nullableCheckbox);
        getForms().put("nullableInt", nullableInt);
        getForms().put("field", field);
    }

    public Composite getComposite() {
        return composite;
    }

    public String choose(Long _index) {
        return Long.toString(_index);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean _checked) {
        checked = _checked;
    }

    public String getTypedString() {
        return typedString;
    }

    public void setTypedString(String _typedString) {
        typedString = _typedString;
    }


    public void setChosenNumber(EnumNumber _chosenNumber) {
        chosenNumber = _chosenNumber;
    }

    public EnumNumber getChosenNumber() {
        return chosenNumber;
    }

    public void setRate(Rate _rate) {
        rate = _rate;
    }

    public Rate getRate() {
        return rate;
    }

    public String getNewOld() {
        return newOld;
    }

    public short getTypedShort() {
        return typedShort;
    }

    public void setTypedShort(short _typedShort) {
        typedShort = _typedShort;
    }

    public int getTypedInt() {
        return typedInt;
    }

    public void setTypedInt(int _typedInt) {
        typedInt = _typedInt;
    }

    public Boolean getNullableCheckbox() {
        return nullableCheckbox;
    }

    public void setNullableCheckbox(Boolean _nullableCheckbox) {
        nullableCheckbox = _nullableCheckbox;
    }

    public Long getNullableInt() {
        return nullableInt;
    }

    public void setNullableInt(Long _nullableInt) {
        nullableInt = _nullableInt;
    }

    public String getField() {
        return field;
    }

    public void setField(String _field) {
        field = _field;
    }

    public void setNewOld(String _newOld) {
        newOld = _newOld;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String _choose) {
        choose = _choose;
    }
}
