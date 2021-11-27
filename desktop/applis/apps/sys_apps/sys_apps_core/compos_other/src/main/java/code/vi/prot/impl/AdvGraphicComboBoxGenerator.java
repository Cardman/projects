package code.vi.prot.impl;

import code.vi.prot.impl.other.CustComboBox;
import code.gui.GraphicComboGrInt;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class AdvGraphicComboBoxGenerator implements AbstractGraphicComboBoxGenerator {
    @Override
    public GraphicComboGrInt createCombo(AbstractImageFactory _fact, StringList _list, int _selectedIndex, AbsCompoFactory _compo) {
        return new CustComboBox(_list, _selectedIndex);
    }
}
