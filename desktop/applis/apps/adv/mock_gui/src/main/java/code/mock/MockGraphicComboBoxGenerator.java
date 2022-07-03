package code.mock;

import code.gui.GraphicComboGrInt;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.util.StringList;

public final class MockGraphicComboBoxGenerator implements AbstractGraphicComboBoxGenerator {
    @Override
    public GraphicComboGrInt createCombo(AbstractImageFactory _fact, StringList _list, int _selectedIndex, AbsCompoFactory _compo) {
        return new MockComboBox(_list,_selectedIndex);
    }
}
