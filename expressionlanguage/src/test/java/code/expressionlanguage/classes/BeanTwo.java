package code.expressionlanguage.classes;
import code.util.CustList;

@SuppressWarnings("static-method")
public class BeanTwo {


    private boolean checked;


    private String typedString;


    private EnumNumber chosenNumber;


    private int typedInt;


    private Rate rate;


    private Boolean nullableCheckbox;


    private Long nullableInt;


    private String newOld = "";


    private String field;

    CustList<EnumNumber> getChosenNumbers() {
        CustList<EnumNumber> enums_ = new CustList<EnumNumber>();
        enums_.add(EnumNumber.ONE);
        enums_.add(EnumNumber.TWO);
        enums_.add(EnumNumber.THREE);
        return enums_;
    }


    String choose(Long _index) {
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
