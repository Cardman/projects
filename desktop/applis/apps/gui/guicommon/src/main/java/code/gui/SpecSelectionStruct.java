package code.gui;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

import java.awt.*;

public interface SpecSelectionStruct extends Struct {
    Argument execute(CustList<Argument> _args, Rectangle _rect);
}
