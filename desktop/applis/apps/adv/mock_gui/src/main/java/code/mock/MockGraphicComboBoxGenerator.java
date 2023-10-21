package code.mock;

import code.util.StringList;

public final class MockGraphicComboBoxGenerator {

    public MockComboBox createCombo(StringList _list, int _selectedIndex) {
        return new MockComboBox(_list,_selectedIndex);
    }
}
