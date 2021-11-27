package code.vi.prot.impl.other;

import code.adv.SafeRemoveAdv;

import javax.swing.*;

public final class SafeRemoveImpl implements SafeRemoveAdv {
    private final JComboBox<String> combo;

    public SafeRemoveImpl(JComboBox<String> _combo) {
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
