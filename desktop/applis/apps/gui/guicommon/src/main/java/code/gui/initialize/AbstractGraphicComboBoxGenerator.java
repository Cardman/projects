package code.gui.initialize;

import code.gui.GraphicComboGrInt;
import code.gui.images.AbstractImageFactory;
import code.util.StringList;

public interface AbstractGraphicComboBoxGenerator {
    GraphicComboGrInt createCombo(AbstractImageFactory _fact, StringList _list, int _selectedIndex, AbsCompoFactory _compo);
}
