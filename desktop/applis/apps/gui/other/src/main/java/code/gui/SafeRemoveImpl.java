package code.gui;

import code.util.core.NumberUtil;
import code.util.ints.SafeRemove;

import javax.swing.*;

public final class SafeRemoveImpl implements SafeRemove {
    private final JComboBox<String> combo;

    public SafeRemoveImpl(JComboBox<String> _combo) {
        this.combo = _combo;
    }

    @Override
    public boolean ok(int _index) {
        return NumberUtil.isValidIndex(_index,combo.getItemCount());
    }

    @Override
    public void removeItemAt(int _index) {
        combo.removeItemAt(_index);
    }
}
