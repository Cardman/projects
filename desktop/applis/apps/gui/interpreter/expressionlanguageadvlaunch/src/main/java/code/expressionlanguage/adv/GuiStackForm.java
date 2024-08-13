package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.comparators.NaturalComparator;

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
    private final CrudGeneForm<String,Integer> prefs;
    private final DependantPointsForm dependantPointsForm;
    public GuiStackForm(AbstractProgramInfos _c) {
        prefs = new CrudGeneForm<String,Integer>(_c,new NaturalComparator());
        dependantPointsForm = new DependantPointsForm(_c);
        stackConstraintsForm = new StackConstraintsForm();
    }

    public AbsScrollPane guiBuild(AbsDebuggerGui _d) {
        pref = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        pref.setVisible(false);
        enabledSub = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("specific enabled");
        enabledSub.setSelected(true);
        hit = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("hit");
        disabledWhenHit = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("disabled when hit");
        disableAgain = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("disable again");
        suspend = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("suspend");
        stackLog = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("log stack trace");
        stackErrLog = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("log stack trace if conditional err");
        stackResErrLog = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("log stack trace result if conditional err");
        conditional = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        logs = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        watches = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        count = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        countSub = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        AbsTabbedPane tab_ = _d.getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        AbsSplitPane panel_ = stackConstraintsForm.guiBuild(_d,null);
        AbsPanel staIncExc_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        staIncExc_.add(pref);
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
        tab_.addIntTab("main elements",staIncExc_);
        tab_.addIntTab("conditional",_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(conditional));
        tab_.addIntTab("logs",_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(logs));
        tab_.addIntTab("watches",_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(watches));
        tab_.addIntTab("contraints pass",panel_);
        tab_.addIntTab("deps",_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(dependantPointsForm.guiBuild(_d)));
        staScIncExc = _d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(tab_);
        return staScIncExc;
    }

    public static void initPrefs(CustList<BreakPointCondition> _bpc, CrudGeneForm<String, Integer> _f) {
        AbsButton pref_ = _f.getFactory().getCompoFactory().newPlainButton("pref");
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

    public CrudGeneForm<String, Integer> getPrefs() {
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
