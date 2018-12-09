package code.formathtml.classes;

import code.util.CustList;
import code.util.StringList;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

public class EncapsFields {

    private CustList<EnumNumber> combobox = new CustList<EnumNumber>(EnumNumber.values());

    private TreeMap<EnumNumber, String> comboboxMap = new TreeMap<EnumNumber, String>(new ComparatorEnum<EnumNumber>());
    
    private EnumNumber comboNumber = EnumNumber.ONE;

    private EnumNumber comboNumberTwo = EnumNumber.THREE;

    private EnumNumbers comboNumbers = new EnumNumbers(EnumNumber.ONE, EnumNumber.FOUR);

    private EnumNumber radioNumber = EnumNumber.ONE;

    private long radioLong = -1;

    private StringList selectedStrings = new StringList();

    private String selectedString = "ONE";

    private String typedString = "ONE";

    private String typedText = "ONE";

    private boolean checkBox;

    public CustList<EnumNumber> getCombobox() {
        return combobox;
    }

    public void setCombobox(CustList<EnumNumber> _combobox) {
        combobox = _combobox;
    }

    public TreeMap<EnumNumber, String> getComboboxMap() {
        return comboboxMap;
    }

    public void setComboboxMap(TreeMap<EnumNumber, String> _comboboxMap) {
        comboboxMap = _comboboxMap;
    }

    public EnumNumber getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(EnumNumber _comboNumber) {
        comboNumber = _comboNumber;
    }

    public EnumNumber getComboNumberTwo() {
        return comboNumberTwo;
    }

    public void setComboNumberTwo(EnumNumber _comboNumberTwo) {
        comboNumberTwo = _comboNumberTwo;
    }

    public EnumNumbers getComboNumbers() {
        return comboNumbers;
    }

    public void setComboNumbers(EnumNumbers _comboNumbers) {
        comboNumbers = _comboNumbers;
    }

    public EnumNumber getRadioNumber() {
        return radioNumber;
    }

    public void setRadioNumber(EnumNumber _radioNumber) {
        radioNumber = _radioNumber;
    }

    public long getRadioLong() {
        return radioLong;
    }

    public void setRadioLong(long _radioLong) {
        radioLong = _radioLong;
    }

    public StringList getSelectedStrings() {
        return selectedStrings;
    }

    public void setSelectedStrings(StringList _selectedStrings) {
        selectedStrings = _selectedStrings;
    }

    public String getSelectedString() {
        return selectedString;
    }

    public void setSelectedString(String _selectedString) {
        selectedString = _selectedString;
    }

    public String getTypedString() {
        return typedString;
    }

    public void setTypedString(String _typedString) {
        typedString = _typedString;
    }

    public String getTypedText() {
        return typedText;
    }

    public void setTypedText(String _typedText) {
        typedText = _typedText;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean _checkBox) {
        checkBox = _checkBox;
    }
}
