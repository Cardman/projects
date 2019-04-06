package code.maths.litteral;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public class Delimiters {

    private int indexBegin;
    private Numbers<Integer> allowedOperatorsIndexes = new Numbers<Integer>();
    private Numbers<Integer> delStringsChars = new Numbers<Integer>();
    private Numbers<Integer> delNumbers = new Numbers<Integer>();
    private CustList<StringList> stringInfo = new CustList<StringList>();
    private CustList<StringBuilder> nbInfos = new CustList<StringBuilder>();
    private CustList<VariableInfo> variables = new CustList<VariableInfo>();

    public Numbers<Integer> getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public int getIndexBegin() {
        return indexBegin;
    }
    public void setIndexBegin(int _indexBegin) {
        indexBegin = _indexBegin;
    }

    public Numbers<Integer> getDelStringsChars() {
        return delStringsChars;
    }

    public CustList<StringList> getStringInfo() {
        return stringInfo;
    }

    public Numbers<Integer> getDelNumbers() {
        return delNumbers;
    }

    public CustList<StringBuilder> getNbInfos() {
        return nbInfos;
    }

    public CustList<VariableInfo> getVariables() {
        return variables;
    }
}
