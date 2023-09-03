package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;

public final class DependantPointsForm {
    public static final String ENTRY = "entry";
    public static final String EXIT = "exit";
    public static final String STD = "std";
    public static final String STATIC = "static";
    public static final String INSTANCE = "instance";
    public static final String THROWN = "thrown";
    public static final String CAUGHT = "caught";
    public static final String PROPAGATED = "propagated";
    public static final String READ = "read";
    public static final String WRITE = "write";
    public static final String COMPOUND_READ = "compound read";
    public static final String COMPOUND_WRITE = "compound write";
    public static final String COMPOUND_WRITE_ERR = "compound write err";
    private AbsPanel excFrom;
    private AbsPanel stdForm;
    private AbsPanel wpForm;
    private AbsPanel metForm;
    private AbsPanel bpForm;
    private AbsScrollPane view;
    private AbsCompoFactory compoFactory;
    private final IdList<BreakPointCondition> selected = new IdList<BreakPointCondition>();
    private final Ints selectedCurrent = new Ints();
    private final CustList<AbsCustCheckBox> checks = new IdList<AbsCustCheckBox>();
    private final CustList<AbsCustCheckBox> checksCurrent = new IdList<AbsCustCheckBox>();

    public AbsPanel guiBuild(AbsDebuggerGui _d) {
        compoFactory = _d.getCommonFrame().getFrames().getCompoFactory();
        view = compoFactory.newAbsScrollPane();
        AbsPanel all_ = compoFactory.newLineBox();
        excFrom = compoFactory.newPageBox();
        stdForm = compoFactory.newPageBox();
        wpForm = compoFactory.newPageBox();
        metForm = compoFactory.newPageBox();
        bpForm = compoFactory.newPageBox();
        all_.add(bpForm);
        all_.add(wpForm);
        all_.add(excFrom);
        all_.add(metForm);
        all_.add(stdForm);
        all_.add(view);
        return all_;
    }
    public void init(ResultContext _res, BreakPointCondition _current) {
        init(_res,-1);
        selected.clear();
        for (BreakPointCondition b: _current.getOthers().elts()) {
            selected.add(b);
        }
    }

    public void init(ResultContext _res, int _add) {
        selected.clear();
        checksCurrent.clear();
        view.setNullViewportView();
        refreshExc(_res);
        refreshStdMethod(_res);
        refreshWatch(_res);
        refreshMethod(_res);
        refreshBp(_res);
        if (_add == 0) {
            bpForm.add(check(STD,0));
            bpForm.add(check(STATIC,1));
            bpForm.add(check(INSTANCE,2));
        } else if (_add == 1) {
            excFrom.add(check(THROWN,0));
            excFrom.add(check(CAUGHT,1));
            excFrom.add(check(PROPAGATED,2));
        } else if (_add == 2) {
            wpForm.add(check(READ,0));
            wpForm.add(check(WRITE,1));
            wpForm.add(check(COMPOUND_READ,2));
            wpForm.add(check(COMPOUND_WRITE,3));
            wpForm.add(check(COMPOUND_WRITE_ERR,4));
        } else if (_add == 3) {
            metForm.add(check(ENTRY,0));
            metForm.add(check(EXIT,1));
        } else if (_add == 4) {
            stdForm.add(check(ENTRY,0));
            stdForm.add(check(EXIT,1));
        }
    }

    public void refreshBp(ResultContext _res) {
        bpForm.removeAll();
        for (BreakPointBlockPair p: _res.bpList().elts()) {
            AbsPlainButton but_ = compoFactory.newPlainButton(ExecFileBlock.name(p.getBp().getFile())+":"+p.getBp().getOffset());
            but_.addActionListener(new BreakPointBlockPairChecksEvent(this,p));
            bpForm.add(but_);
        }
    }
    public void refreshExc(ResultContext _res) {
        excFrom.removeAll();
        for (ExcPointBlockPair p: _res.getContext().excList().elts()) {
            AbsPlainButton but_ = compoFactory.newPlainButton();
            if (p.getEp().isExact()) {
                but_.setText("exact "+p.getEp().getClName());
            } else {
                but_.setText("inherit "+p.getEp().getClName());
            }
            but_.addActionListener(new ExcPointBlockPairChecksEvent(this,p));
            excFrom.add(but_);
        }
    }

    public void refreshStdMethod(ResultContext _res) {
        stdForm.removeAll();
        for (StdMethodPointBlockPair p: _res.getContext().stdList().elts()) {
            AbsPlainButton but_ = compoFactory.newPlainButton();
            but_.setText(p.getSm().keyStr());
            but_.addActionListener(new StdPointBlockPairChecksEvent(this,p));
            stdForm.add(but_);
        }
    }

    public void refreshMethod(ResultContext _res) {
        metForm.removeAll();
        for (MethodPointBlockPair p: _res.getContext().metList().elts()) {
            AbsPlainButton but_ = compoFactory.newPlainButton();
            but_.setText(p.getSgn());
            but_.addActionListener(new PointBlockPairChecksEvent(this,p));
            metForm.add(but_);
        }
    }
    public void refreshWatch(ResultContext _res) {
        wpForm.removeAll();
        for (WatchPointBlockPair p: _res.getContext().watchList().elts()) {
            AbsPlainButton but_ = compoFactory.newPlainButton(FramePoints.displayWatch(p));
            but_.addActionListener(new WpPointBlockPairChecksEvent(this,p));
            wpForm.add(but_);
        }
    }

    public void guiContentBuild(BreakPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        if (_p.getValue().isEnabledChgtType()) {
            page_.add(check(INSTANCE,_p.getValue().getResultInstance()));
            page_.add(check(STATIC,_p.getValue().getResultStatic()));
        } else {
            page_.add(check(STD,_p.getValue().getResultStd()));
        }
        view.setViewportView(page_);
    }

    public void guiContentBuild(ExcPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(THROWN,_p.getValue().getResultThrown()));
        page_.add(check(CAUGHT,_p.getValue().getResultCaught()));
        page_.add(check(PROPAGATED,_p.getValue().getResultPropagated()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(MethodPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(ENTRY,_p.getValue().getResultEntry()));
        page_.add(check(EXIT,_p.getValue().getResultExit()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(StdMethodPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(ENTRY,_p.getValue().getResultEntry()));
        page_.add(check(EXIT,_p.getValue().getResultExit()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(WatchPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(READ,_p.getValue().getResultRead()));
        page_.add(check(WRITE,_p.getValue().getResultWrite()));
        page_.add(check(COMPOUND_READ,_p.getValue().getResultCompoundRead()));
        page_.add(check(COMPOUND_WRITE,_p.getValue().getResultCompoundWrite()));
        page_.add(check(COMPOUND_WRITE_ERR,_p.getValue().getResultCompoundWriteErr()));
        view.setViewportView(page_);
    }

    private AbsCustCheckBox check(String _title, BreakPointCondition _cond) {
        AbsCustCheckBox ch_ = compoFactory.newCustCheckBox(_title,selected.containsObj(_cond));
        checks.add(ch_);
        ch_.addActionListener(new AddRemoveBpcEvent(ch_,this,_cond));
        return ch_;
    }

    private AbsCustCheckBox check(String _title, int _cond) {
        AbsCustCheckBox ch_ = compoFactory.newCustCheckBox(_title);
        checksCurrent.add(ch_);
        ch_.addActionListener(new AddRemoveBpcFirstEvent(ch_,this,_cond));
        return ch_;
    }

    public IdList<BreakPointCondition> getSelected() {
        return selected;
    }

    public Ints getSelectedCurrent() {
        return selectedCurrent;
    }

    public CustList<AbsCustCheckBox> getChecks() {
        return checks;
    }

    public CustList<AbsCustCheckBox> getChecksCurrent() {
        return checksCurrent;
    }

    public AbsPanel getBpForm() {
        return bpForm;
    }

    public AbsPanel getMetForm() {
        return metForm;
    }

    public AbsPanel getWpForm() {
        return wpForm;
    }

    public AbsPanel getExcFrom() {
        return excFrom;
    }

    public AbsPanel getStdForm() {
        return stdForm;
    }
}
