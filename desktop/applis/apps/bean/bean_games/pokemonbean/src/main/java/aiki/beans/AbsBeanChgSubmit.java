package aiki.beans;

import code.util.*;

public class AbsBeanChgSubmit implements IntBeanChgSubmit {
    private final CustList<IntBeanAction> evts = new CustList<IntBeanAction>();

    @Override
    public void addEvt(IntBeanAction _action) {
        getEvts().add(_action);
    }

    public CustList<IntBeanAction> getEvts() {
        return evts;
    }
}
