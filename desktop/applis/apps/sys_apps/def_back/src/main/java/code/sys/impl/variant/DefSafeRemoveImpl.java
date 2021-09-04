package code.sys.impl.variant;

import code.adv.SafeRemoveAdv;

import javax.swing.*;

public final class DefSafeRemoveImpl implements SafeRemoveAdv {
    private final JComboBox combo;

    public DefSafeRemoveImpl(JComboBox _combo) {
        this.combo = _combo;
    }

    @Override
    public int size() {
        return combo.getItemCount();
    }

    @Override
    public void removeItemAt(int _index) {
        combo.removeItemAt(_index);
    }
}
