package code.expressionlanguage.stds;

import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;

public final class BadClass extends AbstractStandardClass {

    public BadClass() {
        super("", new CustList<StandardConstructor>(), new CustList<StandardMethod>(), "", (MethodModifier) null, null);
    }

}
