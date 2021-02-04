package code.gui.initialize;

import code.gui.GraphicComboGrInt;
import code.util.StringList;

public interface AbstractGraphicComboBoxGenerator {
    GraphicComboGrInt createCombo(StringList _list, int _selectedIndex);
}
