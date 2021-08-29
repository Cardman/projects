package code.sys.impl;

import code.gui.GraphicComboGrInt;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class GraphicComboBoxGenerator implements AbstractGraphicComboBoxGenerator {
    @Override
    public GraphicComboGrInt createCombo(AbstractImageFactory _fact, StringList _list, int _selectedIndex, AbsCompoFactory _compo) {
        return new GraphicCombo(_fact,_compo,new GraphicStringList(_fact,_compo,_list),_selectedIndex);
    }
}
