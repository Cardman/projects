package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.dbg.ArrPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FrameArrFormContent {
    private final GuiStackForm guiLengthStackForm;
    private final GuiStackForm guiIntGetStackForm;
    private final GuiStackForm guiIntSetStackForm;
    private final GuiStackForm guiIntCompoundGetStackForm;
    private final GuiStackForm guiIntCompoundSetStackForm;
    private final GuiStackForm guiIntCompoundSetErrStackForm;
    private final GuiStackForm guiRangeGetStackForm;
    private final GuiStackForm guiRangeSetStackForm;
    private final GuiStackForm guiRangeCompoundGetStackForm;
    private final GuiStackForm guiRangeCompoundSetStackForm;
    private final GuiStackForm guiIntGetSetStackForm;
    private final GuiStackForm guiInitArrayStackForm;
    private final GuiStackForm guiCloneStackForm;
    private ArrPointBlockPair selectedArr;
    private AbsTextField clName;
    private final ExactMatchingTypeForm exactForm = new ExactMatchingTypeForm();
    private AbsCustCheckBox length;
    private AbsCustCheckBox intGet;
    private AbsCustCheckBox intSet;
    private AbsCustCheckBox intCompoundGet;
    private AbsCustCheckBox intCompoundSet;
    private AbsCustCheckBox intCompoundSetErr;
    private AbsCustCheckBox rangeGet;
    private AbsCustCheckBox rangeSet;
    private AbsCustCheckBox rangeCompoundGet;
    private AbsCustCheckBox rangeCompoundSet;
    private AbsCustCheckBox intGetSet;
    private AbsCustCheckBox initArray;
    private AbsCustCheckBox clone;
    private AbsCustCheckBox enabledExc;
    private AbsButton ok;
    private AbsButton remove;
    private AbsPanel contentPane;

    public FrameArrFormContent(AbstractProgramInfos _c) {
        guiLengthStackForm = new GuiStackForm(_c);
        guiIntGetStackForm = new GuiStackForm(_c);
        guiIntSetStackForm = new GuiStackForm(_c);
        guiIntCompoundGetStackForm = new GuiStackForm(_c);
        guiIntCompoundSetStackForm = new GuiStackForm(_c);
        guiIntCompoundSetErrStackForm = new GuiStackForm(_c);
        guiRangeGetStackForm = new GuiStackForm(_c);
        guiRangeSetStackForm = new GuiStackForm(_c);
        guiRangeCompoundGetStackForm = new GuiStackForm(_c);
        guiRangeCompoundSetStackForm = new GuiStackForm(_c);
        guiIntGetSetStackForm = new GuiStackForm(_c);
        guiInitArrayStackForm = new GuiStackForm(_c);
        guiCloneStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        length = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_LENGTH)));
        intGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_GET)));
        intSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_SET)));
        intCompoundGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_COMPOUND_GET)));
        intCompoundSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_COMPOUND_SET)));
        intCompoundSetErr = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_COMPOUND_SET_ERR)));
        rangeGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_GET)));
        rangeSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_SET)));
        rangeCompoundGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_COMPOUND_GET)));
        rangeCompoundSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_COMPOUND_SET)));
        intGetSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_GET_SET)));
        initArray = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INIT_ARRAY)));
        clone = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_CLONE)));
        enabledExc = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        clName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        exactForm.guiBuild(_d);
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(exactForm.getPanel());
        bpForm_.add(clName);
        bpForm_.add(enabledExc);
        bpForm_.add(length);
        bpForm_.add(intGet);
        bpForm_.add(intSet);
        bpForm_.add(intCompoundGet);
        bpForm_.add(intCompoundSet);
        bpForm_.add(intCompoundSetErr);
        bpForm_.add(rangeGet);
        bpForm_.add(rangeSet);
        bpForm_.add(rangeCompoundGet);
        bpForm_.add(rangeCompoundSet);
        bpForm_.add(intGetSet);
        bpForm_.add(initArray);
        bpForm_.add(clone);
        AbsTabbedPane tab_ = _d.getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(_d, tab_, guiLengthStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_LENGTH)));
        putStForm(_d, tab_, guiIntGetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_GET)));
        putStForm(_d, tab_, guiIntSetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_SET)));
        putStForm(_d, tab_, guiIntCompoundGetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_COMPOUND_GET)));
        putStForm(_d, tab_, guiIntCompoundSetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_COMPOUND_SET)));
        putStForm(_d, tab_, guiIntCompoundSetErrStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_COMPOUND_SET_ERR)));
        putStForm(_d, tab_, guiRangeGetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_GET)));
        putStForm(_d, tab_, guiRangeSetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_SET)));
        putStForm(_d, tab_, guiRangeCompoundGetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_COMPOUND_GET)));
        putStForm(_d, tab_, guiRangeCompoundSetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_RANGE_COMPOUND_SET)));
        putStForm(_d, tab_, guiIntGetSetStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INT_GET_SET)));
        putStForm(_d, tab_, guiInitArrayStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_INIT_ARRAY)));
        putStForm(_d, tab_, guiCloneStackForm, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_CLONE)));
        bpForm_.add(tab_);
        getGuiLengthStackForm().showPrefs();
        getGuiIntGetStackForm().showPrefs();
        getGuiIntSetStackForm().showPrefs();
        getGuiIntCompoundGetStackForm().showPrefs();
        getGuiIntCompoundSetStackForm().showPrefs();
        getGuiIntCompoundSetErrStackForm().showPrefs();
        getGuiRangeGetStackForm().showPrefs();
        getGuiRangeSetStackForm().showPrefs();
        getGuiRangeCompoundGetStackForm().showPrefs();
        getGuiRangeCompoundSetStackForm().showPrefs();
        getGuiIntGetSetStackForm().showPrefs();
        getGuiInitArrayStackForm().showPrefs();
        getGuiCloneStackForm().showPrefs();
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    private void putStForm(AbsDebuggerGui _d, AbsTabbedPane _tab, GuiStackForm _target, String _title) {
        _tab.addIntTab(_title,_target.guiBuild(_d));
    }

    public void initForm(ArrPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedArr(_s);
        ArrPointBlockPair exc_ = getSelectedArr();
        if (exc_ != null) {
            exactForm.updateValue(exc_.getEp());
        } else {
            exactForm.updateValue(null);
        }
        if (exc_ != null) {
            clName.setEnabled(false);
            clName.setText(exc_.getEp().getClName());
            remove.setEnabled(true);
            getEnabledExc().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiLengthStackForm(), exc_.getValue().getResultLength(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_LENGTH), _f,_r);
            getLength().setSelected(exc_.getValue().isLength());
            BreakPointFormEvent.specific(getGuiIntGetStackForm(), exc_.getValue().getResultIntGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_GET), _f,_r);
            getIntGet().setSelected(exc_.getValue().isIntGet());
            BreakPointFormEvent.specific(getGuiIntSetStackForm(), exc_.getValue().getResultIntSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_SET), _f,_r);
            getIntSet().setSelected(exc_.getValue().isIntSet());
            BreakPointFormEvent.specific(getGuiIntCompoundGetStackForm(), exc_.getValue().getResultIntCompoundGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_COMPOUND_GET), _f,_r);
            getIntCompoundGet().setSelected(exc_.getValue().isIntCompoundGet());
            BreakPointFormEvent.specific(getGuiIntCompoundSetStackForm(), exc_.getValue().getResultIntCompoundSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_COMPOUND_SET), _f,_r);
            getIntCompoundSet().setSelected(exc_.getValue().isIntCompoundSet());
            BreakPointFormEvent.specific(getGuiIntCompoundSetErrStackForm(), exc_.getValue().getResultIntCompoundSetErr(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_COMPOUND_SET_ERR), _f,_r);
            getIntCompoundSetErr().setSelected(exc_.getValue().isIntCompoundSetErr());
            BreakPointFormEvent.specific(getGuiRangeGetStackForm(), exc_.getValue().getResultRangeGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_GET), _f,_r);
            getRangeGet().setSelected(exc_.getValue().isRangeGet());
            BreakPointFormEvent.specific(getGuiRangeSetStackForm(), exc_.getValue().getResultRangeSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_SET), _f,_r);
            getRangeSet().setSelected(exc_.getValue().isRangeSet());
            BreakPointFormEvent.specific(getGuiRangeCompoundGetStackForm(), exc_.getValue().getResultRangeCompoundGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_COMPOUND_GET), _f,_r);
            getRangeCompoundGet().setSelected(exc_.getValue().isRangeCompoundGet());
            BreakPointFormEvent.specific(getGuiRangeCompoundSetStackForm(), exc_.getValue().getResultRangeCompoundSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_COMPOUND_SET), _f,_r);
            getRangeCompoundSet().setSelected(exc_.getValue().isRangeCompoundSet());
            BreakPointFormEvent.specific(getGuiIntGetSetStackForm(), exc_.getValue().getResultIntGetSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_GET_SET), _f,_r);
            getIntGetSet().setSelected(exc_.getValue().isIntGetSet());
            BreakPointFormEvent.specific(getGuiInitArrayStackForm(), exc_.getValue().getResultInitArray(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INIT), _f,_r);
            getInitArray().setSelected(exc_.getValue().isInitArray());
            BreakPointFormEvent.specific(getGuiCloneStackForm(), exc_.getValue().getResultClone(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_CLONE), _f,_r);
            getClone().setSelected(exc_.getValue().isInitArray());
            PackingWindowAfter.pack(_f);
        } else {
            getGuiLengthStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiIntGetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiIntSetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiIntCompoundGetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiIntCompoundSetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiIntCompoundSetErrStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiRangeGetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiRangeSetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiRangeCompoundGetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiRangeCompoundSetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiIntGetSetStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiInitArrayStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            getGuiCloneStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_LENGTH),getGuiLengthStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INT_GET),getGuiIntGetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INT_SET),getGuiIntSetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INT_COMPOUND_GET),getGuiIntCompoundGetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INT_COMPOUND_SET),getGuiIntCompoundSetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INT_COMPOUND_SET_ERR),getGuiIntCompoundSetErrStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_RANGE_GET),getGuiRangeGetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_RANGE_SET),getGuiRangeSetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_RANGE_COMPOUND_GET),getGuiRangeCompoundGetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_RANGE_COMPOUND_SET),getGuiRangeCompoundSetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INT_GET_SET),getGuiIntGetSetStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_INIT),getGuiInitArrayStackForm(),_f,_r);
            FrameMpForm.updatePref(BreakPointBlockList.prefsArr(_r.getContext().arrList(),ArrPoint.BPC_CLONE),getGuiCloneStackForm(),_f,_r);
            clName.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkArrFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveArrFormEvent(this, _p, _r));
        getGuiLengthStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiIntGetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiIntSetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiIntCompoundGetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiIntCompoundSetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiIntCompoundSetErrStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiRangeGetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiRangeSetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiRangeCompoundGetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiRangeCompoundSetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiIntGetSetStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiInitArrayStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiCloneStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public ArrPointBlockPair getSelectedArr() {
        return selectedArr;
    }

    public void setSelectedArr(ArrPointBlockPair _s) {
        this.selectedArr = _s;
    }

    public ExactMatchingTypeForm getExactForm() {
        return exactForm;
    }

    public AbsTextField getClName() {
        return clName;
    }

    public AbsCustCheckBox getLength() {
        return length;
    }

    public AbsCustCheckBox getIntGet() {
        return intGet;
    }

    public AbsCustCheckBox getIntSet() {
        return intSet;
    }

    public AbsCustCheckBox getIntCompoundGet() {
        return intCompoundGet;
    }

    public AbsCustCheckBox getIntCompoundSet() {
        return intCompoundSet;
    }

    public AbsCustCheckBox getIntCompoundSetErr() {
        return intCompoundSetErr;
    }

    public AbsCustCheckBox getRangeGet() {
        return rangeGet;
    }

    public AbsCustCheckBox getRangeSet() {
        return rangeSet;
    }

    public AbsCustCheckBox getRangeCompoundGet() {
        return rangeCompoundGet;
    }

    public AbsCustCheckBox getRangeCompoundSet() {
        return rangeCompoundSet;
    }

    public AbsCustCheckBox getIntGetSet() {
        return intGetSet;
    }

    public AbsCustCheckBox getInitArray() {
        return initArray;
    }

    public AbsCustCheckBox getClone() {
        return clone;
    }

    public AbsCustCheckBox getEnabledExc() {
        return enabledExc;
    }

    public AbsButton getOk() {
        return ok;
    }

    public AbsButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiLengthStackForm() {
        return guiLengthStackForm;
    }

    public GuiStackForm getGuiIntGetStackForm() {
        return guiIntGetStackForm;
    }

    public GuiStackForm getGuiIntSetStackForm() {
        return guiIntSetStackForm;
    }

    public GuiStackForm getGuiIntCompoundGetStackForm() {
        return guiIntCompoundGetStackForm;
    }

    public GuiStackForm getGuiIntCompoundSetStackForm() {
        return guiIntCompoundSetStackForm;
    }

    public GuiStackForm getGuiIntCompoundSetErrStackForm() {
        return guiIntCompoundSetErrStackForm;
    }

    public GuiStackForm getGuiRangeGetStackForm() {
        return guiRangeGetStackForm;
    }

    public GuiStackForm getGuiRangeSetStackForm() {
        return guiRangeSetStackForm;
    }

    public GuiStackForm getGuiRangeCompoundGetStackForm() {
        return guiRangeCompoundGetStackForm;
    }

    public GuiStackForm getGuiRangeCompoundSetStackForm() {
        return guiRangeCompoundSetStackForm;
    }

    public GuiStackForm getGuiIntGetSetStackForm() {
        return guiIntGetSetStackForm;
    }

    public GuiStackForm getGuiInitArrayStackForm() {
        return guiInitArrayStackForm;
    }

    public GuiStackForm getGuiCloneStackForm() {
        return guiCloneStackForm;
    }
}
