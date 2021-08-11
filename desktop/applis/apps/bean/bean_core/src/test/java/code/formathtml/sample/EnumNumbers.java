package code.formathtml.sample;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.ints.Listable;

public final class EnumNumbers extends CustList<EnumNumber> {

    public EnumNumbers() {
    }

    public EnumNumbers(EnumNumber... _elements) {
        super(_elements);
    }

    public EnumNumbers(Listable<EnumNumber> _c) {
        super(_c);
    }

    public EnumNumbers(CollCapacity _capacity) {
        super(_capacity);
    }

}
