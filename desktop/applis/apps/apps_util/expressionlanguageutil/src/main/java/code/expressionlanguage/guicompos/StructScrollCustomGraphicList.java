package code.expressionlanguage.guicompos;

import code.expressionlanguage.ActionGraphicListenerStruct;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.ScrollCustomGraphicList;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsEnabledAction;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class StructScrollCustomGraphicList extends ScrollCustomGraphicList<Struct> {
    private final GraphicListStruct first;
    private final String actionList;
    private final CustList<ActionGraphicListenerStruct> actionGraphicListenerStructs = new CustList<ActionGraphicListenerStruct>();
    public StructScrollCustomGraphicList(AbsCompoFactory _compo, AbstractImageFactory _img, String _actionListener, boolean _s, GraphicListStruct _f) {
        super(_compo, _img, _s, new AlwaysActionListenerAct());
        actionList = _actionListener;
        first = _f;
        buildActions();
        setEnabled(true);
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectEvent(int _d) {
        return struct(new MoveGraphicSelectEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectShiftEvent(int _d) {
        return struct(new MoveGraphicSelectShiftEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectPageEvent(int _d) {
        return struct(new MoveGraphicSelectPageEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectBoundEvent(int _d) {
        return struct(new MoveGraphicSelectBoundEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectShiftBoundEvent(int _d) {
        return struct(new MoveGraphicSelectShiftBoundEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicFocusBoundEvent(int _d) {
        return struct(new MoveGraphicFocusBoundEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicAnchorBoundEvent(int _d) {
        return struct(new MoveGraphicAnchorBoundEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectShiftPageEvent(int _d) {
        return struct(new MoveGraphicSelectShiftPageEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicFocusPageEvent(int _d) {
        return struct(new MoveGraphicFocusPageEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicAnchorPageEvent(int _d) {
        return struct(new MoveGraphicAnchorPageEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicFocusEvent(int _d) {
        return struct(new MoveGraphicFocusEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicAnchorEvent(int _d) {
        return struct(new MoveGraphicAnchorEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectAddAncIntervalEvent(boolean _d) {
        return struct(new MoveGraphicSelectAddAncIntervalEvent<Struct>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicDeselectAllAction() {
        return struct(new MoveGraphicSelectDeSelectAllEvent<Struct>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectAllAction() {
        return struct(new MoveGraphicSelectSelectAllEvent<Struct>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicAddTo() {
        return struct(new MoveGraphicSelectAddIntervalEvent<Struct>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicChange() {
        return struct(new MoveGraphicSelectChangeEvent<Struct>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicExtend() {
        return struct(new MoveGraphicSelectExtendToEvent<Struct>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicToggle() {
        return struct(new MoveGraphicSelectToggleIntervalEvent<Struct>(this));
    }
    @Override
    public AbstractImage generateImg(MetaFont _lab, int _index, Struct _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored) {
        return first.generateImg(_lab, _index, _info, _isSelected, _cellHasFocus, _cellIsAnchored);
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
