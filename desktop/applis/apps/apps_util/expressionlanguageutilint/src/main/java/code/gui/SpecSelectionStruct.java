package code.gui;

import code.expressionlanguage.Argument;
import code.gui.images.MetaDimension;
import code.util.CustList;

public interface SpecSelectionStruct {
    Argument execute(CustList<Argument> _args, MetaDimension _rect);
}
