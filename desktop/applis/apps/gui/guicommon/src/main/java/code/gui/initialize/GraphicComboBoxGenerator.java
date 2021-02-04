package code.gui.initialize;

import code.gui.GraphicCombo;
import code.gui.GraphicComboGrInt;
import code.gui.GraphicStringList;
import code.util.StringList;

public final class GraphicComboBoxGenerator implements AbstractGraphicComboBoxGenerator {
    @Override
    public GraphicComboGrInt createCombo(StringList _list, int _selectedIndex) {
        return new GraphicCombo(new GraphicStringList(_list),_selectedIndex);
    }
}
