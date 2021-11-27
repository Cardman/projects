package code.vi.prot.impl.variant;

import code.gui.GraphicComboGrInt;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class DefAdvGraphicComboBoxGenerator implements AbstractGraphicComboBoxGenerator {
    @Override
    public GraphicComboGrInt createCombo(AbstractImageFactory _fact, StringList _list, int _selectedIndex, AbsCompoFactory _compo) {
        return new DefCustComboBox(_list, _selectedIndex);
    }
}
