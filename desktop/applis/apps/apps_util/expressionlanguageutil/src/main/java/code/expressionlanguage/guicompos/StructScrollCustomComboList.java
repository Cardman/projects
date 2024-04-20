package code.expressionlanguage.guicompos;

import code.expressionlanguage.ActionGraphicListenerStruct;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.structs.NullStruct;
import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsEnabledAction;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class StructScrollCustomComboList extends ScrollCustomCombo {
    private final String actionList;
    private final CustList<ActionGraphicListenerStruct> actionGraphicListenerStructs = new CustList<ActionGraphicListenerStruct>();
    public StructScrollCustomComboList(AbsCompoFactory _compo, AbstractImageFactory _img, String _actionListener) {
        super(_compo, _img, new AlwaysActionListenerAct());
        actionList = _actionListener;
        buildActions();
        setEnabled(true);
    }

    @Override
    protected AbsEnabledAction moveComboSelectEvent(int _d) {
        return struct(new MoveComboSelectEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectPageEvent(int _d) {
        return struct(new MoveComboSelectPageEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectBoundEvent(int _d) {
        return struct(new MoveComboSelectBoundEvent(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboEnterEvent() {
        return struct(new MoveComboEnterEvent(this));
    }

    @Override
    protected AbsEnabledAction moveComboToggleEvent() {
        return struct(new MoveComboToggleEvent(this));
    }

    @Override
    protected AbsEnabledAction moveComboHideEvent() {
        return struct(new MoveComboHideEvent(this));
    }

    private AbsEnabledAction struct(AbsActionListener _act) {
        ActionGraphicListenerStruct ac_ = new ActionGraphicListenerStruct(actionList, new CustList<ClassFieldStruct>(), NullStruct.NULL_VALUE, "", -1, "", _act);
        actionGraphicListenerStructs.add(ac_);
        return getCompoFactory().wrap(ac_);
    }

    public CustList<ActionGraphicListenerStruct> getActionGraphicListenerStructs() {
        return actionGraphicListenerStructs;
    }
}
