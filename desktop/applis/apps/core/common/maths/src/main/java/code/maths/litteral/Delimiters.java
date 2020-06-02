package code.maths.litteral;
import code.util.CustList;
import code.util.Ints;
import code.util.*;
import code.util.StringList;

public class Delimiters {

    private Ints allowedOperatorsIndexes = new Ints();
    private Ints delStringsChars = new Ints();
    private Ints delNumbers = new Ints();
    private CustList<StringList> stringInfo = new CustList<StringList>();
    private CustList<StringBuilder> nbInfos = new CustList<StringBuilder>();
    private CustList<VariableInfo> variables = new CustList<VariableInfo>();

    public Ints getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public Ints getDelStringsChars() {
        return delStringsChars;
    }

    public CustList<StringList> getStringInfo() {
        return stringInfo;
    }

    public Ints getDelNumbers() {
        return delNumbers;
    }

    public CustList<StringBuilder> getNbInfos() {
        return nbInfos;
    }

    public CustList<VariableInfo> getVariables() {
        return variables;
    }
}
