package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class GuiStackForm {
    private AbsCustCheckBox hit;
    private AbsCustCheckBox enabledSub;
    private AbsCustCheckBox disabledWhenHit;
    private AbsCustCheckBox disableAgain;
    private AbsCustCheckBox suspend;
    private AbsCustCheckBox stackLog;
    private AbsCustCheckBox stackErrLog;
    private AbsCustCheckBox stackResErrLog;
    private AbsTextArea conditional;
    private AbsTextArea logs;
    private AbsTextArea watches;
    private AbsSpinner count;
    private AbsSpinner countSub;
    private final StackConstraintsForm stackConstraintsForm;
    private AbsScrollPane staScIncExc;
    private AbsSpinner pref;
    private final CrudGeneForm prefs;
    private final DependantPointsForm dependantPointsForm;
    public GuiStackForm(AbstractProgramInfos _c) {
        prefs = new CrudGeneForm(_c);
        dependantPointsForm = new DependantPointsForm(_c);
        stackConstraintsForm = new StackConstraintsForm(_c);
    }

    public AbsScrollPane guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointForm(_d.getFrames().currentLg());
        pref = _d.getFrames().getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        pref.setVisible(false);
        enabledSub = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_SPEC)));
        enabledSub.setSelected(true);
        hit = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_HIT)));
        disabledWhenHit = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_DIS_HIT)));
        disableAgain = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_DIS_AGAIN)));
        suspend = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_DIS_SUSPEND)));
        stackLog = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_LOG_ST)));
        stackErrLog = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_LOG_ST_ERR)));
        stackResErrLog = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_LOG_ST_RES_ERR)));
        conditional = _d.getFrames().getCompoFactory().newTextArea();
        logs = _d.getFrames().getCompoFactory().newTextArea();
        watches = _d.getFrames().getCompoFactory().newTextArea();
        count = _d.getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        countSub = _d.getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        AbsTabbedPane tab_ = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        AbsSplitPane panel_ = stackConstraintsForm.guiBuild(_d,null);
        AbsPanel staIncExc_ = _d.getFrames().getCompoFactory().newPageBox();
        staIncExc_.add(pref);
        prefs.initForm();
        prefs.getGroup().setTitledBorder(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_PREF_IMPL));
        AbsPanel g_ = prefs.getGroup();
        g_.setVisible(false);
        staIncExc_.add(g_);
        staIncExc_.add(enabledSub);
        staIncExc_.add(hit);
        staIncExc_.add(disabledWhenHit);
        staIncExc_.add(disableAgain);
        staIncExc_.add(suspend);
        staIncExc_.add(stackLog);
        staIncExc_.add(count);
        staIncExc_.add(countSub);
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_MAIN)),staIncExc_);
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_COND)),_d.getFrames().getCompoFactory().newAbsScrollPane(conditional));
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_LOGS)),_d.getFrames().getCompoFactory().newAbsScrollPane(logs));
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_WATCHES)),_d.getFrames().getCompoFactory().newAbsScrollPane(watches));
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_CONST)),panel_);
        tab_.addIntTab(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_DEPS)),_d.getFrames().getCompoFactory().newAbsScrollPane(dependantPointsForm.guiBuild(_d)));
        staScIncExc = _d.getFrames().getCompoFactory().newAbsScrollPane(tab_);
        return staScIncExc;
    }

    public static void initPrefs(CustList<BreakPointCondition> _bpc, CrudGeneForm _f) {
        StringMap<String> mes_ = MessagesIde.valPointForm(_f.getFactory().currentLg());
        AbsButton pref_ = _f.getFactory().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_PREF)));
        pref_.addActionListener(new ValuePrefEvent(_f, _bpc));
        _f.getButtons().add(pref_);
    }

    public void refresh(StringMap<String> _files, String _folderToVisit, ResultContext _r, AbsDebuggerGui _d) {
        stackConstraintsForm.refresh(_files, _folderToVisit, _r, _d);
    }

    public void showPrefs() {
        getPref().setVisible(true);
        getPrefs().getGroup().setVisible(true);
    }
    public void actualiseLists(AbsCommonFrame _c) {
        stackConstraintsForm.actualiseLists(_c);
    }

    public StackConstraintsForm getStackConstraintsForm() {
        return stackConstraintsForm;
    }

    public AbsSpinner getPref() {
        return pref;
    }

    public CrudGeneForm getPrefs() {
        return prefs;
    }

    public AbsScrollPane getStaScIncExc() {
        return staScIncExc;
    }

    public AbsPanel getIncludedFileIndex() {
        return getStackConstraintsForm().getIncludedFileIndex();
    }

    public AbsPanel getExcludedFileIndex() {
        return getStackConstraintsForm().getExcludedFileIndex();
    }

    public CustList<AbsCallContraints> getMustBe() {
        return getStackConstraintsForm().getMustBe();
    }

    public CustList<AbsCallContraints> getMustNotBe() {
        return getStackConstraintsForm().getMustNotBe();
    }

    public AbsButton getBpAddFile() {
        return getStackConstraintsForm().getBpAddFile();
    }

    public AbsButton getBpRemoveFile() {
        return getStackConstraintsForm().getBpRemoveFile();
    }

    public AbsTreeGui getBpFolderSystem() {
        return getStackConstraintsForm().getBpFolderSystem();
    }

    public ReadOnlyFormTabEditor getReadOnlyFormTabEditor() {
        return getStackConstraintsForm().getReadOnlyFormTabEditor();
    }

    public AbsCustCheckBox getSingleCaret() {
        return getStackConstraintsForm().getSingleCaret();
    }

    public AbsCustCheckBox getEnabledSub() {
        return enabledSub;
    }

    public AbsCustCheckBox getHit() {
        return hit;
    }

    public AbsCustCheckBox getDisabledWhenHit() {
        return disabledWhenHit;
    }

    public AbsCustCheckBox getDisableAgain() {
        return disableAgain;
    }

    public AbsCustCheckBox getSuspend() {
        return suspend;
    }

    public AbsCustCheckBox getStackLog() {
        return stackLog;
    }

    public AbsCustCheckBox getStackErrLog() {
        return stackErrLog;
    }

    public AbsCustCheckBox getStackResErrLog() {
        return stackResErrLog;
    }

    public AbsTextArea getConditional() {
        return conditional;
    }

    public AbsTextArea getLogs() {
        return logs;
    }

    public AbsTextArea getWatches() {
        return watches;
    }

    public AbsSpinner getCount() {
        return count;
    }

    public AbsSpinner getCountSub() {
        return countSub;
    }

    public DependantPointsForm getDependantPointsForm() {
        return dependantPointsForm;
    }
}
