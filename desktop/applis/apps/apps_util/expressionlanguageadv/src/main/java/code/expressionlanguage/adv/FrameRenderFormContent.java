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

public final class FrameRenderFormContent {
    private AbsTextArea renderText;
    private RenderPointPair selectedExc;
    private AbsTextField clName;
    private final ExactMatchingTypeForm exactForm = new ExactMatchingTypeForm();
    private AbsCustCheckBox enabledExc;
    private AbsCustCheckBox enabledExcGlobal;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;
    private AbsSpinner pref;
    private final CrudGeneForm<String,Integer> prefs;
    public FrameRenderFormContent(AbstractProgramInfos _list) {
        prefs = new CrudGeneForm<String,Integer>(_list,new NaturalComparator());
    }

    public void guiBuild(AbsDebuggerGui _d) {
        pref = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        pref.setVisible(false);
        enabledExc = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled custom render");
        enabledExcGlobal = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled global");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        clName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        exactForm.guiBuild(_d);
        renderText = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(pref);
        bpForm_.add(prefs.getGroup());
        bpForm_.add(exactForm.getPanel());
        bpForm_.add(clName);
        bpForm_.add(enabledExc);
        bpForm_.add(enabledExcGlobal);
        bpForm_.add(_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(renderText));
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
            getEnabledExcGlobal().setSelected(exc_.isGlobalEnabled());
            renderText.setText(exc_.getRender().getResultStr());
        } else {
            updatePref(_bpc,_c,_r);
            getEnabledExc().setSelected(true);
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
        AbsPlainButton pref_ = _f.getFactory().getCompoFactory().newPlainButton("pref");
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

    public AbsCustCheckBox getEnabledExcGlobal() {
        return enabledExcGlobal;
    }

    public AbsTextArea getRenderText() {
        return renderText;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

}
