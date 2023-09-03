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

public final class DependantPointsForm {
    private AbsPanel excFrom;
    private AbsPanel stdForm;
    private AbsPanel wpForm;
    private AbsPanel metForm;
    private AbsPanel bpForm;
    private AbsScrollPane view;
    private AbsCompoFactory compoFactory;
    private final IdList<BreakPointCondition> selected = new IdList<BreakPointCondition>();
    private final CustList<AbsCustCheckBox> checks = new IdList<AbsCustCheckBox>();

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
        init(_res);
        selected.clear();
        for (BreakPointCondition b: _current.getOthers().elts()) {
            selected.add(b);
        }
    }

    public void init(ResultContext _res) {
        view.setNullViewportView();
        refreshExc(_res);
        refreshStdMethod(_res);
        refreshWatch(_res);
        refreshMethod(_res);
        refreshBp(_res);
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
        page_.add(check("std",_p.getValue().getResultStd()));
        page_.add(check("instance",_p.getValue().getResultInstance()));
        page_.add(check("static",_p.getValue().getResultStatic()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(ExcPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check("thrown",_p.getValue().getResultThrown()));
        page_.add(check("caught",_p.getValue().getResultCaught()));
        page_.add(check("propagated",_p.getValue().getResultPropagated()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(MethodPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check("entry",_p.getValue().getResultEntry()));
        page_.add(check("exit",_p.getValue().getResultExit()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(StdMethodPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check("entry",_p.getValue().getResultEntry()));
        page_.add(check("exit",_p.getValue().getResultExit()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(WatchPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check("read",_p.getValue().getResultRead()));
        page_.add(check("write",_p.getValue().getResultWrite()));
        page_.add(check("compound read",_p.getValue().getResultCompoundRead()));
        page_.add(check("compound write",_p.getValue().getResultCompoundWrite()));
        page_.add(check("compound wrtie err",_p.getValue().getResultCompoundWriteErr()));
        view.setViewportView(page_);
    }

    private AbsCustCheckBox check(String _title, BreakPointCondition _cond) {
        AbsCustCheckBox ch_ = compoFactory.newCustCheckBox(_title,selected.containsObj(_cond));
        checks.add(ch_);
        ch_.addActionListener(new AddRemoveBpcEvent(ch_,this,_cond));
        return ch_;
    }

    public IdList<BreakPointCondition> getSelected() {
        return selected;
    }

    public CustList<AbsCustCheckBox> getChecks() {
        return checks;
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
