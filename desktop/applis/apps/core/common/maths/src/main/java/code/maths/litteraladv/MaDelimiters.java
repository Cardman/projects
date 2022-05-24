package code.maths.litteraladv;

import code.maths.litteralcom.MatVariableInfo;
import code.util.CustList;
import code.util.Ints;

public final class MaDelimiters {

    private final CustList<MaIndexStackOperator> operatorsIndexes = new CustList<MaIndexStackOperator>();
    private final Ints numbers = new Ints();
    private final CustList<StringBuilder> nbParts = new CustList<StringBuilder>();
    private final CustList<MatVariableInfo> varParts = new CustList<MatVariableInfo>();

    public CustList<MaIndexStackOperator> getOperatorsIndexes() {
        return operatorsIndexes;
    }

    public Ints getNumbers() {
        return numbers;
    }

    public CustList<StringBuilder> getNbParts() {
        return nbParts;
    }

    public CustList<MatVariableInfo> getVarParts() {
        return varParts;
    }
}
