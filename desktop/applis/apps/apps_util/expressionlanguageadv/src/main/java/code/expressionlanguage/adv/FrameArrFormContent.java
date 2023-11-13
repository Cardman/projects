package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.dbg.ArrPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

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
        length = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("thrown");
        intGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("get");
        intSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("set");
        intCompoundGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound get");
        intCompoundSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound set");
        intCompoundSetErr = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("compound set err");
        rangeGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("range get");
        rangeSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("range set");
        rangeCompoundGet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("range compound get");
        rangeCompoundSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("range compound set");
        intGetSet = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("get set");
        initArray = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("init array");
        clone = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("clone");
        enabledExc = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
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
        bpForm_.add(guiLengthStackForm.guiBuild(_d));
        bpForm_.add(guiIntGetStackForm.guiBuild(_d));
        bpForm_.add(guiIntSetStackForm.guiBuild(_d));
        bpForm_.add(guiIntCompoundGetStackForm.guiBuild(_d));
        bpForm_.add(guiIntCompoundSetStackForm.guiBuild(_d));
        bpForm_.add(guiIntCompoundSetErrStackForm.guiBuild(_d));
        bpForm_.add(guiRangeGetStackForm.guiBuild(_d));
        bpForm_.add(guiRangeSetStackForm.guiBuild(_d));
        bpForm_.add(guiRangeCompoundGetStackForm.guiBuild(_d));
        bpForm_.add(guiRangeCompoundSetStackForm.guiBuild(_d));
        bpForm_.add(guiIntGetSetStackForm.guiBuild(_d));
        bpForm_.add(guiInitArrayStackForm.guiBuild(_d));
        bpForm_.add(guiCloneStackForm.guiBuild(_d));
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
            BreakPointFormEvent.specific(getGuiLengthStackForm(), true, exc_.getValue().getResultLength(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_LENGTH), _f,_r);
            getLength().setSelected(exc_.getValue().isLength());
            BreakPointFormEvent.specific(getGuiIntGetStackForm(), true, exc_.getValue().getResultIntGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_GET), _f,_r);
            getIntGet().setSelected(exc_.getValue().isIntGet());
            BreakPointFormEvent.specific(getGuiIntSetStackForm(), true, exc_.getValue().getResultIntSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_SET), _f,_r);
            getIntSet().setSelected(exc_.getValue().isIntSet());
            BreakPointFormEvent.specific(getGuiIntCompoundGetStackForm(), true, exc_.getValue().getResultIntCompoundGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_COMPOUND_GET), _f,_r);
            getIntCompoundGet().setSelected(exc_.getValue().isIntCompoundGet());
            BreakPointFormEvent.specific(getGuiIntCompoundSetStackForm(), true, exc_.getValue().getResultIntCompoundSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_COMPOUND_SET), _f,_r);
            getIntCompoundSet().setSelected(exc_.getValue().isIntCompoundSet());
            BreakPointFormEvent.specific(getGuiIntCompoundSetErrStackForm(), true, exc_.getValue().getResultIntCompoundSetErr(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_COMPOUND_SET_ERR), _f,_r);
            getIntCompoundSetErr().setSelected(exc_.getValue().isIntCompoundSetErr());
            BreakPointFormEvent.specific(getGuiRangeGetStackForm(), true, exc_.getValue().getResultRangeGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_GET), _f,_r);
            getRangeGet().setSelected(exc_.getValue().isRangeGet());
            BreakPointFormEvent.specific(getGuiRangeSetStackForm(), true, exc_.getValue().getResultRangeSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_SET), _f,_r);
            getRangeSet().setSelected(exc_.getValue().isRangeSet());
            BreakPointFormEvent.specific(getGuiRangeCompoundGetStackForm(), true, exc_.getValue().getResultRangeCompoundGet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_COMPOUND_GET), _f,_r);
            getRangeCompoundGet().setSelected(exc_.getValue().isRangeCompoundGet());
            BreakPointFormEvent.specific(getGuiRangeCompoundSetStackForm(), true, exc_.getValue().getResultRangeCompoundSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_RANGE_COMPOUND_SET), _f,_r);
            getRangeCompoundSet().setSelected(exc_.getValue().isRangeCompoundSet());
            BreakPointFormEvent.specific(getGuiIntGetSetStackForm(), true, exc_.getValue().getResultIntGetSet(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INT_GET_SET), _f,_r);
            getIntGetSet().setSelected(exc_.getValue().isIntGetSet());
            BreakPointFormEvent.specific(getGuiInitArrayStackForm(), true, exc_.getValue().getResultInitArray(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_INIT), _f,_r);
            getInitArray().setSelected(exc_.getValue().isInitArray());
            BreakPointFormEvent.specific(getGuiCloneStackForm(), true, exc_.getValue().getResultClone(), BreakPointBlockList.prefsArr(_r.getContext().arrList(), ArrPoint.BPC_CLONE), _f,_r);
            getClone().setSelected(exc_.getValue().isInitArray());
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
        getGuiLengthStackForm().refresh(_v, "", _r, _d);
        getGuiIntGetStackForm().refresh(_v, "", _r, _d);
        getGuiIntSetStackForm().refresh(_v, "", _r, _d);
        getGuiIntCompoundGetStackForm().refresh(_v, "", _r, _d);
        getGuiIntCompoundSetStackForm().refresh(_v, "", _r, _d);
        getGuiIntCompoundSetErrStackForm().refresh(_v, "", _r, _d);
        getGuiRangeGetStackForm().refresh(_v, "", _r, _d);
        getGuiRangeSetStackForm().refresh(_v, "", _r, _d);
        getGuiRangeCompoundGetStackForm().refresh(_v, "", _r, _d);
        getGuiRangeCompoundSetStackForm().refresh(_v, "", _r, _d);
        getGuiIntGetSetStackForm().refresh(_v, "", _r, _d);
        getGuiInitArrayStackForm().refresh(_v, "", _r, _d);
        getGuiCloneStackForm().refresh(_v, "", _r, _d);
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
