package code.maths.litteral;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class MbDelimiters {

    private final Ints allowedOperatorsIndexes = new Ints();
    private final Ints delStringsChars = new Ints();
    private final Ints delNumbers = new Ints();
    private final CustList<StringList> stringInfo = new CustList<StringList>();
    private final CustList<StringBuilder> nbInfos = new CustList<StringBuilder>();
    private final CustList<MbVariableInfo> variables = new CustList<MbVariableInfo>();

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

    public CustList<MbVariableInfo> getVariables() {
        return variables;
    }
}
