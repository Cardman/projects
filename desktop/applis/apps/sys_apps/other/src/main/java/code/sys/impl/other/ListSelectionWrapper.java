package code.sys.impl.other;

import code.gui.AbstractSelectionListener;
import code.gui.LabelButtonUtil;
import code.gui.ListSelection;

public final class ListSelectionWrapper {
    private final ListSelection wrapped;

    public ListSelectionWrapper(ListSelection _wrapped) {
        this.wrapped = _wrapped;
    }

    public boolean match(AbstractSelectionListener _other) {
        return LabelButtonUtil.match(_other,wrapped);
    }
}
