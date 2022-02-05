package code.expressionlanguage.analyze.instr;

public final class ResultAfterOperators {
    private ResultAfterInstKeyWord doubleDotted;
    private StackOperators parsBrackets;
    private boolean constTextString;
    private boolean constTextChar;
    private boolean constString;
    private boolean constChar;
    private boolean constText;
    private boolean partOfString;
    private int nbChars;
    private int fieldNumber;
    public ResultAfterInstKeyWord getDoubleDotted() {
        return doubleDotted;
    }

    public void setDoubleDotted(ResultAfterInstKeyWord _doubleDotted) {
        doubleDotted = _doubleDotted;
    }

    public StackOperators getParsBrackets() {
        return parsBrackets;
    }

    public void setParsBrackets(StackOperators _parsBrackets) {
        parsBrackets = _parsBrackets;
    }

    public boolean isConstTextString() {
        return constTextString;
    }

    public void setConstTextString(boolean _constTextString) {
        constTextString = _constTextString;
    }

    public boolean isConstTextChar() {
        return constTextChar;
    }

    public void setConstTextChar(boolean _constTextChar) {
        constTextChar = _constTextChar;
    }

    public boolean isConstString() {
        return constString;
    }

    public void setConstString(boolean _constString) {
        constString = _constString;
    }

    public boolean isConstChar() {
        return constChar;
    }

    public void setConstChar(boolean _constChar) {
        constChar = _constChar;
    }

    public boolean isConstText() {
        return constText;
    }

    public void setConstText(boolean _constText) {
        constText = _constText;
    }

    public int getNbChars() {
        return nbChars;
    }

    public void setNbChars(int _nbChars) {
        nbChars = _nbChars;
    }

    public boolean isPartOfString() {
        return partOfString;
    }

    public void setPartOfString(boolean _partOfString) {
        partOfString = _partOfString;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(int _fieldNumber) {
        this.fieldNumber = _fieldNumber;
    }
}
