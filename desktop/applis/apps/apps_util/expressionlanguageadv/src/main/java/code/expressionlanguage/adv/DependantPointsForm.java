package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
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
    public static final String LENGTH = "length";
    public static final String CAUGHT = "caught";
    public static final String PROPAGATED = "propagated";
    public static final String READ = "read";
    public static final String WRITE = "write";
    public static final String COMPOUND_READ = "compound read";
    public static final String COMPOUND_WRITE = "compound write";
    public static final String COMPOUND_WRITE_ERR = "compound write err";
    private final FramePointsTree framePointsTree;
    private AbsPanel excFrom;
    private AbsPanel stdForm;
    private AbsPanel wpForm;
    private AbsPanel metForm;
    private AbsPanel bpForm;
    private AbsPanel arrForm;
    private AbsPanel parForm;
    private AbsPanel operNatForm;
    private AbsPanel operNatCompoForm;
    private AbsPanel typeForm;
    private AbsScrollPane view;
    private AbsCompoFactory compoFactory;
    private final IdList<BreakPointCondition> selected = new IdList<BreakPointCondition>();
    private final Ints selectedCurrent = new Ints();
    private final CustList<AbsCustCheckBox> checks = new IdList<AbsCustCheckBox>();
    private final CustList<AbsCustCheckBox> checksCurrent = new IdList<AbsCustCheckBox>();
    public DependantPointsForm(AbsCompoFactory _c) {
        framePointsTree = new FramePointsTree(_c);
    }

    public AbsPanel guiBuild(AbsDebuggerGui _d) {
        compoFactory = _d.getCommonFrame().getFrames().getCompoFactory();
        view = compoFactory.newAbsScrollPane();
        framePointsTree.guiBuild();
        AbsPanel all_ = compoFactory.newLineBox();
        excFrom = compoFactory.newPageBox();
        stdForm = compoFactory.newPageBox();
        wpForm = compoFactory.newPageBox();
        metForm = compoFactory.newPageBox();
        bpForm = compoFactory.newPageBox();
        arrForm = compoFactory.newPageBox();
        parForm = compoFactory.newPageBox();
        operNatForm = compoFactory.newPageBox();
        operNatCompoForm = compoFactory.newPageBox();
        typeForm = compoFactory.newPageBox();
        all_.add(bpForm);
        all_.add(wpForm);
        all_.add(excFrom);
        all_.add(metForm);
        all_.add(stdForm);
        all_.add(arrForm);
        all_.add(parForm);
        all_.add(operNatForm);
        all_.add(operNatCompoForm);
        all_.add(typeForm);
        all_.add(compoFactory.newAbsScrollPane(framePointsTree.getTree()));
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
        bpForm.removeAll();
        excFrom.removeAll();
        wpForm.removeAll();
        metForm.removeAll();
        stdForm.removeAll();
        arrForm.removeAll();
        parForm.removeAll();
        operNatForm.removeAll();
        operNatCompoForm.removeAll();
        typeForm.removeAll();
        selected.clear();
        checksCurrent.clear();
        view.setNullViewportView();
        framePointsTree.refreshList(_res);
        framePointsTree.listenerSelect(this);
//        refreshExc(_res);
//        refreshStdMethod(_res);
//        refreshWatch(_res);
//        refreshMethod(_res);
//        refreshBp(_res);
        if (_add == BreakPoint.BP) {
            bpForm.add(check(STD,BreakPoint.BPC_STD));
        } else if (_add == ExcPoint.EP) {
            excFrom.add(check(THROWN,ExcPoint.BPC_THROWN));
            excFrom.add(check(CAUGHT,ExcPoint.BPC_CAUGHT));
            excFrom.add(check(PROPAGATED,ExcPoint.BPC_PROPAGATED));
        } else if (_add == WatchPoint.WP) {
            wpForm.add(check(READ,WatchPoint.BPC_READ));
            wpForm.add(check(WRITE,WatchPoint.BPC_WRITE));
            wpForm.add(check(COMPOUND_READ,WatchPoint.BPC_COMPOUND_READ));
            wpForm.add(check(COMPOUND_WRITE,WatchPoint.BPC_COMPOUND_WRITE));
            wpForm.add(check(COMPOUND_WRITE_ERR,WatchPoint.BPC_COMPOUND_WRITE_ERR));
        } else if (_add == MethodPointBlockPair.CMP) {
            metForm.add(check(ENTRY,MethodPoint.BPC_ENTRY));
            metForm.add(check(EXIT,MethodPoint.BPC_EXIT));
        } else if (_add == StdMethodPointBlockPair.SMP) {
            stdForm.add(check(ENTRY,MethodPoint.BPC_ENTRY));
            stdForm.add(check(EXIT,MethodPoint.BPC_EXIT));
        } else if (_add == ArrPoint.AP) {
            arrForm.add(check(LENGTH,ArrPoint.BPC_LENGTH));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INT_GET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INT_SET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INT_COMPOUND_GET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INT_COMPOUND_SET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INT_COMPOUND_SET_ERR));
            arrForm.add(check(LENGTH,ArrPoint.BPC_RANGE_GET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_RANGE_SET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_RANGE_COMPOUND_GET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_RANGE_COMPOUND_SET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INT_GET_SET));
            arrForm.add(check(LENGTH,ArrPoint.BPC_INIT));
            arrForm.add(check(LENGTH,ArrPoint.BPC_CLONE));
        } else if (_add == ParPoint.PP) {
            parForm.add(check(THROWN,ParPoint.BPC_GET));
        } else if (_add == OperNatPoint.OP) {
            operNatForm.add(check(THROWN,OperNatPoint.BPC_SIMPLE));
        } else if (_add == FramePointsTree.SORT_CP) {
            operNatCompoForm.add(check(THROWN,OperNatPoint.BPC_SIMPLE));
            operNatCompoForm.add(check(THROWN,OperNatPoint.BPC_COMPOUND));
        } else if (_add == FramePointsTree.SORT_TP) {
            typeForm.add(check(STATIC,BreakPoint.BPC_STATIC));
            typeForm.add(check(INSTANCE,BreakPoint.BPC_INSTANCE));
        }
    }

    public void guiContentBuild(BreakPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(STD,_p.getValue().getResultStd()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(TypePointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(INSTANCE,_p.getValue().getResultInstance()));
        page_.add(check(STATIC,_p.getValue().getResultStatic()));
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

    public void guiContentBuild(ParPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(THROWN,_p.getValue().getResultGet()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(OperNatPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(THROWN,_p.getValue().getResultSimple()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(CompoOperNatPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(THROWN,_p.getValue().getResultSimple()));
        page_.add(check(THROWN,_p.getValue().getResultCompound()));
        view.setViewportView(page_);
    }

    public void guiContentBuild(ArrPointBlockPair _p) {
        checks.clear();
        AbsPanel page_ = compoFactory.newPageBox();
        page_.add(check(LENGTH,_p.getValue().getResultLength()));
        page_.add(check(LENGTH,_p.getValue().getResultIntGet()));
        page_.add(check(LENGTH,_p.getValue().getResultIntSet()));
        page_.add(check(LENGTH,_p.getValue().getResultIntCompoundGet()));
        page_.add(check(LENGTH,_p.getValue().getResultIntCompoundSet()));
        page_.add(check(LENGTH,_p.getValue().getResultIntCompoundSetErr()));
        page_.add(check(LENGTH,_p.getValue().getResultRangeGet()));
        page_.add(check(LENGTH,_p.getValue().getResultRangeSet()));
        page_.add(check(LENGTH,_p.getValue().getResultRangeCompoundGet()));
        page_.add(check(LENGTH,_p.getValue().getResultRangeCompoundSet()));
        page_.add(check(LENGTH,_p.getValue().getResultIntGetSet()));
        page_.add(check(LENGTH,_p.getValue().getResultInitArray()));
        page_.add(check(LENGTH,_p.getValue().getResultClone()));
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

    public FramePointsTree getFramePointsTree() {
        return framePointsTree;
    }

}
