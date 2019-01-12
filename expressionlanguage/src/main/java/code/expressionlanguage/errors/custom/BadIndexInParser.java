package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class BadIndexInParser extends FoundErrorInterpret {

    private int index;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes)," at ",Integer.toString(index));
    }

    public void setIndex(int _index) {
        index = _index;
    }

    
}
