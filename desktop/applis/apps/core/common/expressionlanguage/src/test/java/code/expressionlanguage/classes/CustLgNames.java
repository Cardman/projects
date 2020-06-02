package code.expressionlanguage.classes;

import code.expressionlanguage.stds.LgNames;
import code.maths.montecarlo.DefaultGenerator;

public final class CustLgNames extends LgNames {

    public CustLgNames() {
        super(new DefaultGenerator());
    }

    @Override
    public void buildOther() {
    }

}
