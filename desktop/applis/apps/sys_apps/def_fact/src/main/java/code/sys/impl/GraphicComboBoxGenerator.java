package code.sys.impl;

import code.gui.GraphicCombo;
import code.gui.GraphicComboGrInt;
import code.gui.GraphicStringList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class GraphicComboBoxGenerator implements AbstractGraphicComboBoxGenerator {
    @Override
    public GraphicComboGrInt createCombo(AbstractImageFactory _fact, StringList _list, int _selectedIndex) {
        return new GraphicCombo(_fact,new GraphicStringList(_fact,_list),_selectedIndex);
    }
}
