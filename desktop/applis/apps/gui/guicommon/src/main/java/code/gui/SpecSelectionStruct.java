package code.gui;

import code.expressionlanguage.Argument;
import code.util.CustList;

import java.awt.Rectangle;

public interface SpecSelectionStruct {
    Argument execute(CustList<Argument> _args, Rectangle _rect);
}
