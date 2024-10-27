package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.ConcreteInteger;
import code.util.AbsMap;
import code.util.CustList;

public class GeneComponentModelEltEnumSample extends GeneComponentModelEltEnum<ConcreteInteger> {
    public GeneComponentModelEltEnumSample(AbstractProgramInfos _c, AbsMap<ConcreteInteger, String> _messages, CustList<ConcreteInteger> _elts) {
        super(_c, _messages, _elts);
    }

    @Override
    protected ConcreteInteger defValue() {
        return null;
    }
}
