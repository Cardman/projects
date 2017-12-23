package code.expressionlanguage.classes;

import java.math.BigInteger;

import code.util.ints.Displayable;

public class CustBigInt implements Displayable {

    private BigInteger val;

    public CustBigInt(BigInteger _val) {
        val = _val;
    }

    @Override
    public String display() {
        return val.toString();
    }

}
