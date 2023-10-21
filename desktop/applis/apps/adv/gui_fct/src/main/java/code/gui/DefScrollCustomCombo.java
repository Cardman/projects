package code.gui;

import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public final class DefScrollCustomCombo extends ScrollCustomCombo {
    public DefScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img) {
        super(_compo, _img);
        buildActions();
        setEnabled(true);
    }

    @Override
    protected AbsEnabledAction moveComboSelectEvent(int _d) {
        return getCompoFactory().wrap(new MoveComboSelectEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectPageEvent(int _d) {
        return getCompoFactory().wrap(new MoveComboSelectPageEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectBoundEvent(int _d) {
        return getCompoFactory().wrap(new MoveComboSelectBoundEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboHideEvent() {
        return getCompoFactory().wrap(new MoveComboHideEvent(this));
    }

    @Override
    protected AbsEnabledAction moveComboToggleEvent() {
        return getCompoFactory().wrap(new MoveComboToggleEvent(this));
    }

    @Override
    protected AbsEnabledAction moveComboEnterEvent() {
        return getCompoFactory().wrap(new MoveComboEnterEvent(this));
    }

}
