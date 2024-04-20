package code.gui;

import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsEnabledAction;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public final class DefScrollCustomCombo extends ScrollCustomCombo {
    private final AbsActionListenerAct guard;
    public DefScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img) {
        this(_compo,_img,new AlwaysActionListenerAct());
    }
    public DefScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img, AbsActionListenerAct _act) {
        super(_compo, _img, _act);
        guard = _act;
        buildActions();
        setEnabled(true);
    }
    @Override
    protected AbsEnabledAction moveComboSelectEvent(int _d) {
        return getCompoFactory().wrap(guard,new MoveComboSelectEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectPageEvent(int _d) {
        return getCompoFactory().wrap(guard,new MoveComboSelectPageEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectBoundEvent(int _d) {
        return getCompoFactory().wrap(guard,new MoveComboSelectBoundEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboHideEvent() {
        return getCompoFactory().wrap(guard,new MoveComboHideEvent(this));
    }

    @Override
    protected AbsEnabledAction moveComboToggleEvent() {
        return getCompoFactory().wrap(guard,new MoveComboToggleEvent(this));
    }

    @Override
    protected AbsEnabledAction moveComboEnterEvent() {
        return getCompoFactory().wrap(guard,new MoveComboEnterEvent(this));
    }

}
