package code.formathtml.classes;
import code.bean.Accessible;
import code.bean.Bean;
import code.formathtml.util.ValueChangeEvent;
import code.util.CustList;
import code.util.StringList;

@SuppressWarnings("static-method")
public class BeanTwo extends Bean {

    @Accessible
    private boolean checked;

    @Accessible
    private String typedString;

    @Accessible
    private EnumNumber chosenNumber;

    @Accessible
    private int typedInt;

    @Accessible
    private Rate rate;

    @Accessible
    private Boolean nullableCheckbox;

    @Accessible
    private Long nullableInt;

    @Accessible
    private String newOld = "";

    @Accessible
    private String field;

    @Accessible
    private String choose;//1_2;

    public BeanTwo() {
        setClassName("code.formathtml.classes.BeanTwo");
    }

    @Override
    public void beforeDisplaying() {
        chosenNumber = EnumNumber.TWO;
        if (typedString == null) {
            typedString = "TYPED_STRING";
        } else {
            typedString = StringList.concatNbs(typedString,2);
        }
    }

    @Accessible
    CustList<EnumNumber> getChosenNumbers() {
        CustList<EnumNumber> enums_ = new CustList<EnumNumber>();
        enums_.add(EnumNumber.ONE);
        enums_.add(EnumNumber.TWO);
        enums_.add(EnumNumber.THREE);
        return enums_;
    }

    @Accessible
    String go(Long _index) {
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

    @Accessible
    String go() {
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

    @Accessible
    String goTextArea() {
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

    @Accessible
    void validate() {
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

    @Accessible
    String choose(Long _index) {
        return Long.toString(_index);
    }

    public void changeText(ValueChangeEvent _event) {
        newOld = StringList.concat(((Integer)_event.getNewValue()).toString()," ",((Integer)_event.getOldValue()).toString());
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
}
