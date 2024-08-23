package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;
import code.util.core.StringUtil;

public final class FrameRenderFormContent {
    private AbsTextArea renderText;
    private AbsTextArea expandText;
    private AbsTextArea renderExpandText;
    private AbsTextArea expandRenderText;
    private RenderPointPair selectedExc;
    private AbsTextField clName;
    private final ExactMatchingTypeForm exactForm = new ExactMatchingTypeForm();
    private AbsCustCheckBox enabledExc;
    private AbsCustCheckBox enabledExpand;
    private AbsCustCheckBox enabledExpandRender;
    private AbsCustCheckBox enabledRenderExpand;
    private AbsCustCheckBox expandFirst;
    private AbsCustCheckBox expandRenderChoice;
    private AbsCustCheckBox enabledExcGlobal;
    private AbsButton ok;
    private AbsButton remove;
    private AbsPanel contentPane;
    private AbsSpinner pref;
    private final CrudGeneForm<String,Integer> prefs;
    public FrameRenderFormContent(AbstractProgramInfos _list) {
        prefs = new CrudGeneForm<String,Integer>(_list,new NaturalComparator());
    }

    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valRendForm(_d.getFrames().currentLg());
        pref = _d.getFrames().getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        pref.setVisible(false);
        enabledExc = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EN_REND)));
        enabledExpand = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EN_EXP)));
        enabledExpandRender = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EN_BOTH_EXP)));
        enabledRenderExpand = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EN_BOTH_REND)));
        expandFirst = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EXP)));
        expandRenderChoice = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EXP_REND)));
        enabledExcGlobal = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_EN_GL)));
        ok = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_VALIDATE)));
        remove = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_REMOVE)));
        clName = _d.getFrames().getCompoFactory().newTextField();
        exactForm.guiBuild(_d);
        renderText = _d.getFrames().getCompoFactory().newTextArea();
        expandText = _d.getFrames().getCompoFactory().newTextArea();
        renderExpandText = _d.getFrames().getCompoFactory().newTextArea();
        expandRenderText = _d.getFrames().getCompoFactory().newTextArea();
        AbsPanel bpForm_ = _d.getFrames().getCompoFactory().newPageBox();
        bpForm_.add(pref);
        bpForm_.add(prefs.getGroup());
        bpForm_.add(exactForm.getPanel());
        bpForm_.add(clName);
        bpForm_.add(enabledExc);
        bpForm_.add(enabledExpand);
        bpForm_.add(enabledExpandRender);
        bpForm_.add(enabledRenderExpand);
        bpForm_.add(expandFirst);
        bpForm_.add(expandRenderChoice);
        bpForm_.add(enabledExcGlobal);
        bpForm_.add(_d.getFrames().getCompoFactory().newAbsScrollPane(renderText));
        bpForm_.add(_d.getFrames().getCompoFactory().newAbsScrollPane(expandText));
        bpForm_.add(_d.getFrames().getCompoFactory().newAbsScrollPane(renderExpandText));
        bpForm_.add(_d.getFrames().getCompoFactory().newAbsScrollPane(expandRenderText));
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(CustList<RenderPointPair> _bpc,RenderPointPair _s, AbsCommonFrame _c, ResultContext _r) {
        setSelectedExc(_s);
        RenderPointPair exc_ = getSelectedExc();
        if (exc_ != null) {
            exactForm.updateValue(exc_.getExcPointBlockPair().getEp());
        } else {
            exactForm.updateValue(null);
        }
        if (exc_ != null) {
            getPref().setValue(exc_.getPref());
            GuiBaseUtil.initStringMapInt(_c,prefs,exc_.getPrefs(),new StringList(_r.getContext().getClasses().getClassesBodies().getKeys()),new StrictTypeFromFilter(_r));
            initPrefs(_bpc, prefs);
            clName.setEnabled(false);
            clName.setText(exc_.getExcPointBlockPair().getEp().getClName());
            getEnabledExc().setSelected(exc_.getExcPointBlockPair().getValue().isEnabled());
            getEnabledExpand().setSelected(exc_.isEnableExpand());
            getEnabledExpandRender().setSelected(exc_.isEnableBothExpand());
            getExpandFirst().setSelected(exc_.isExpandFirst());
            getExpandRenderChoice().setSelected(exc_.isExpandRenderFirst());
            getEnabledRenderExpand().setSelected(exc_.isEnableBothRender());
            getEnabledExcGlobal().setSelected(exc_.isGlobalEnabled());
            renderText.setText(exc_.getRender().getResultStr());
            expandText.setText(exc_.getExpand().getResultStr());
            renderExpandText.setText(exc_.getRenderExpand().getResultStr());
            expandRenderText.setText(exc_.getExpandRender().getResultStr());
        } else {
            updatePref(_bpc,_c,_r);
            getEnabledExc().setSelected(true);
            getEnabledExpand().setSelected(true);
            getEnabledExpandRender().setSelected(true);
            getExpandFirst().setSelected(true);
            getExpandRenderChoice().setSelected(true);
            getEnabledRenderExpand().setSelected(true);
            getEnabledExcGlobal().setSelected(true);
            clName.setEnabled(true);
        }
    }

    public void updatePref(CustList<RenderPointPair> _bpc, AbsCommonFrame _c, ResultContext _r) {
        getPref().setValue(BreakPointBlockList.pref(retrieve(_bpc)));
        GuiBaseUtil.initStringMapInt(_c,getPrefs(),new StringMap<Integer>(),new StringList(_r.getContext().getClasses().getClassesBodies().getKeys()),new StrictTypeFromFilter(_r));
        initPrefs(_bpc, getPrefs());
    }

    private static Ints retrieve(CustList<RenderPointPair> _ls) {
        Ints values_ = new Ints();
        for (RenderPointPair m: _ls) {
            values_.add(m.getPref());
        }
        return values_;
    }
    public void initPrefs(CustList<RenderPointPair> _bpc, CrudGeneForm<String, Integer> _f) {
        StringMap<String> mes_ = MessagesIde.valRendForm(_f.getFactory().currentLg());
        AbsButton pref_ = _f.getFactory().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_REND_FORM_PREF)));
        pref_.addActionListener(new ValuePrefRendEvent(_f, _bpc));
        _f.getButtons().add(pref_);
    }
    public void refresh(ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkRenderFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveRenderFormEvent(_d,this, _p));
    }

    public AbsSpinner getPref() {
        return pref;
    }

    public CrudGeneForm<String, Integer> getPrefs() {
        return prefs;
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public RenderPointPair getSelectedExc() {
        return selectedExc;
    }

    public void setSelectedExc(RenderPointPair _s) {
        this.selectedExc = _s;
    }

    public ExactMatchingTypeForm getExactForm() {
        return exactForm;
    }

    public AbsTextField getClName() {
        return clName;
    }

    public AbsCustCheckBox getEnabledExc() {
        return enabledExc;
    }

    public AbsCustCheckBox getEnabledExpand() {
        return enabledExpand;
    }

    public AbsCustCheckBox getEnabledExpandRender() {
        return enabledExpandRender;
    }

    public AbsCustCheckBox getExpandFirst() {
        return expandFirst;
    }

    public AbsCustCheckBox getEnabledRenderExpand() {
        return enabledRenderExpand;
    }

    public AbsCustCheckBox getExpandRenderChoice() {
        return expandRenderChoice;
    }

    public AbsCustCheckBox getEnabledExcGlobal() {
        return enabledExcGlobal;
    }

    public AbsTextArea getRenderText() {
        return renderText;
    }

    public AbsTextArea getExpandText() {
        return expandText;
    }

    public AbsTextArea getRenderExpandText() {
        return renderExpandText;
    }

    public AbsTextArea getExpandRenderText() {
        return expandRenderText;
    }

    public AbsButton getOk() {
        return ok;
    }

    public AbsButton getRemove() {
        return remove;
    }

}
