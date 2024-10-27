package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class StructScrollCustomComboList extends AbsScrollCustomCombo<String> {
    private final String actionList;
    private final CustList<ActionGraphicListenerStruct> actionGraphicListenerStructs = new CustList<ActionGraphicListenerStruct>();
    public StructScrollCustomComboList(AbsCompoFactory _compo, AbstractImageFactory _img, String _actionListener) {
        super(_compo, _img, new AlwaysActionListenerAct(),new StrCustCellRenderGeneImpl(_compo, _img));
        actionList = _actionListener;
        buildActions();
        setEnabled(true);
    }

    @Override
    protected String adj(String _i) {
        return StringUtil.nullToEmpty(_i);
    }

    @Override
    protected AbsEnabledAction moveComboSelectEvent(int _d) {
        return struct(new MoveComboSelectEvent<String>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectPageEvent(int _d) {
        return struct(new MoveComboSelectPageEvent<String>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboSelectBoundEvent(int _d) {
        return struct(new MoveComboSelectBoundEvent<String>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveComboEnterEvent() {
        return struct(new MoveComboEnterEvent<String>(this));
    }

    @Override
    protected AbsEnabledAction moveComboToggleEvent() {
        return struct(new MoveComboToggleEvent<String>(this));
    }

    @Override
    protected AbsEnabledAction moveComboHideEvent() {
        return struct(new MoveComboHideEvent<String>(this));
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
