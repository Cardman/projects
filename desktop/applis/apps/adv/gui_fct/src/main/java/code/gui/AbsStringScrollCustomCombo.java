package code.gui;

import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public abstract class AbsStringScrollCustomCombo<T> extends AbsScrollCustomCombo<T> {
    private final AbsActionListenerAct guard;
    protected AbsStringScrollCustomCombo(AbsCompoFactory _compo, AbstractImageFactory _img, AbsActionListenerAct _act, AbsCustCellRenderGeneImpl<T> _rend) {
        super(_compo, _img, _act,_rend);
        guard = _act;
        buildActions();
        setEnabled(true);
    }
    @Override
    protected AbsEnabledAction moveComboSelectEvent(int _d) {
        return getCompoFactory().wrap(guard,new MoveComboSelectEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectPageEvent(int _d) {
        return getCompoFactory().wrap(guard,new MoveComboSelectPageEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectBoundEvent(int _d) {
        return getCompoFactory().wrap(guard,new MoveComboSelectBoundEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboHideEvent() {
        return getCompoFactory().wrap(guard,new MoveComboHideEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveComboToggleEvent() {
        return getCompoFactory().wrap(guard,new MoveComboToggleEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveComboEnterEvent() {
        return getCompoFactory().wrap(guard,new MoveComboEnterEvent<T>(this));
    }
}
