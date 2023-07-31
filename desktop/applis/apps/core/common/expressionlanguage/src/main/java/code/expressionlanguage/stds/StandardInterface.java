package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneType;
import code.util.CustList;

public final class StandardInterface extends StandardType implements GeneType {

    public StandardInterface(String _name,
                             CustList<StandardMethod> _methods) {
        super(_name, new CustList<StandardConstructor>(), _methods, null);
    }

}
