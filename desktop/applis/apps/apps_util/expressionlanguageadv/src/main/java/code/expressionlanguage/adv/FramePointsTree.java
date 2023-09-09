package code.expressionlanguage.adv;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsPlainButton;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;

public final class FramePointsTree {
    public static final int SORT_BP = 0;
    public static final int SORT_WP = 1;
    public static final int SORT_WP_ANNOT = 2;
    public static final int SORT_EP = 3;
    public static final int SORT_MP = 4;
    public static final int SORT_SP = 5;
    private final AbsCompoFactory compoFactory;
    private AbsTreeGui tree;
    private AbsPlainButton create;
    private AbstractMutableTreeNode instruction;
    private AbstractMutableTreeNode watchField;
    private AbstractMutableTreeNode watchFieldAnnot;
    private AbstractMutableTreeNode exception;
    private AbstractMutableTreeNode customMethod;
    private AbstractMutableTreeNode standardMethod;
    private final NatStringTreeMap<CustList<BreakPointBlockPair>> bpList = new NatStringTreeMap<CustList<BreakPointBlockPair>>();
    private final NatStringTreeMap<CustList<WatchPointBlockPair>> wpList = new NatStringTreeMap<CustList<WatchPointBlockPair>>();
    private final NatStringTreeMap<CustList<WatchPointBlockPair>> wpListAnnot = new NatStringTreeMap<CustList<WatchPointBlockPair>>();
    private final NatStringTreeMap<CustList<ExcPointBlockPair>> excList = new NatStringTreeMap<CustList<ExcPointBlockPair>>();
    private final NatStringTreeMap<MethodPointBlockPair> metList = new NatStringTreeMap<MethodPointBlockPair>();
    private final NatStringTreeMap<CustList<StdMethodPointBlockPair>> stdList = new NatStringTreeMap<CustList<StdMethodPointBlockPair>>();
    public FramePointsTree(AbsCompoFactory _c) {
        compoFactory = _c;
    }

    public void guiBuild() {
        AbstractMutableTreeNode root_ = compoFactory.newMutableTreeNode("break points");
        instruction = compoFactory.newMutableTreeNode("instruction");
        root_.add(instruction);
        watchField = compoFactory.newMutableTreeNode("watch field");
        root_.add(watchField);
        watchFieldAnnot = compoFactory.newMutableTreeNode("watch annotation method");
        root_.add(watchFieldAnnot);
        exception = compoFactory.newMutableTreeNode("exception");
        root_.add(exception);
        customMethod = compoFactory.newMutableTreeNode("custom method");
        root_.add(customMethod);
        standardMethod = compoFactory.newMutableTreeNode("standard method");
        root_.add(standardMethod);
        tree = compoFactory.newTreeGui(root_);
        create = compoFactory.newPlainButton("+");
        create.setEnabled(false);
    }
    public void init(FramePoints _f, ResultContext _res) {
        refreshList(_res);
        GuiBaseUtil.removeActionListeners(create);
        create.addActionListener(new TreeBreakPointBlockPairAddEvent(_f,this,_res));
        listenerSelect(_f, _res);
    }

    public void refreshList(ResultContext _res) {
        refreshException(_res);
        refreshStdMethod(_res);
        refreshWp(_res);
        refreshWpAnnot(_res);
        refreshMethod(_res);
        refreshBp(_res);
        tree.reloadRoot();
    }

    public void listenerSelect(FramePoints _f, ResultContext _r) {
        GuiBaseUtil.removeTreeSelectionListeners(tree);
        tree.addTreeSelectionListener(new TreeBreakPointBlockPairEvent(_f,this,_r));
    }

    public void listenerSelect(DependantPointsForm _f) {
        GuiBaseUtil.removeTreeSelectionListeners(tree);
        tree.addTreeSelectionListener(new TreeBreakPointBlockPairDepsEvent(_f,this));
    }
    public void refreshBp(ResultContext _res) {
        instruction.removeAllChildren();
        bpList.clear();
        for (BreakPointBlockPair p: _res.bpList().elts()) {
            String fileName_ = ExecFileBlock.name(p.getBp().getFile());
            CustList<BreakPointBlockPair> alreadyInst_ = bpList.getVal(fileName_);
            if (alreadyInst_ == null) {
                CustList<BreakPointBlockPair> local_ = new CustList<BreakPointBlockPair>();
                local_.add(p);
                bpList.put(fileName_, local_);
            } else {
                alreadyInst_.add(p);
            }
        }
        for (EntryCust<String, CustList<BreakPointBlockPair>> p: bpList.entryList()) {
            CustList<BreakPointBlockPair> list_ = p.getValue();
            list_.sortElts(new CmpLocalFileBreakPoint());
            AbstractMutableTreeNode file_ = compoFactory.newMutableTreeNode(p.getKey());
            for (BreakPointBlockPair l: list_) {
                file_.add(compoFactory.newMutableTreeNode(Long.toString(l.getBp().getOffset())));
            }
            instruction.add(file_);
        }
        tree.reload(instruction);
    }
    public void refreshWp(ResultContext _res) {
        refreshWp(wpList,watchField,_res,true);
    }
    public void refreshWpAnnot(ResultContext _res) {
        refreshWp(wpListAnnot,watchFieldAnnot,_res,false);
    }
    private void refreshWp(NatStringTreeMap<CustList<WatchPointBlockPair>> _m,AbstractMutableTreeNode _node,ResultContext _res, boolean _trueField) {
        String suff_;
        if (_trueField) {
            suff_ = "";
        } else {
            suff_ = "()";
        }
        _m.clear();
        _node.removeAllChildren();
        for (WatchPointBlockPair p: filter(_res, _trueField)) {
            String className_ = p.getRoot().getFullName();
            CustList<WatchPointBlockPair> alreadyWatch_ = _m.getVal(className_);
            if (alreadyWatch_ == null) {
                CustList<WatchPointBlockPair> local_ = new CustList<WatchPointBlockPair>();
                local_.add(p);
                _m.put(className_, local_);
            } else {
                alreadyWatch_.add(p);
            }
        }
        for (EntryCust<String, CustList<WatchPointBlockPair>> p: _m.entryList()) {
            CustList<WatchPointBlockPair> list_ = p.getValue();
            list_.sortElts(new CmpLocalFileWatchPoint());
            AbstractMutableTreeNode file_ = compoFactory.newMutableTreeNode(p.getKey());
            for (WatchPointBlockPair l: list_) {
                file_.add(compoFactory.newMutableTreeNode(l.getWp().fieldName()+suff_));
            }
            _node.add(file_);
        }
        tree.reload(_node);
    }
    private static CustList<WatchPointBlockPair> filter(ResultContext _res, boolean _trueField) {
        CustList<WatchPointBlockPair> out_ = new CustList<WatchPointBlockPair>();
        for (WatchPointBlockPair p: _res.getContext().watchList().elts()) {
            if (p.getWp().isTrueField() == _trueField) {
                out_.add(p);
            }
        }
        return out_;
    }
    public void refreshException(ResultContext _res) {
        exception.removeAllChildren();
        excList.clear();
        for (ExcPointBlockPair p: _res.getContext().excList().elts()) {
            String className_ = StringExpUtil.getIdFromAllTypes(p.getEp().getClName());
            CustList<ExcPointBlockPair> alreadyExc_ = excList.getVal(className_);
            if (alreadyExc_ == null) {
                CustList<ExcPointBlockPair> local_ = new CustList<ExcPointBlockPair>();
                local_.add(p);
                excList.put(className_, local_);
            } else {
                alreadyExc_.add(p);
            }
        }
        for (EntryCust<String, CustList<ExcPointBlockPair>> p: excList.entryList()) {
            CustList<ExcPointBlockPair> list_ = p.getValue();
            CustList<ExcPointBlockPair> listId_ = new CustList<ExcPointBlockPair>();
            CustList<ExcPointBlockPair> listExact_ = new CustList<ExcPointBlockPair>();
            for (ExcPointBlockPair e: list_) {
                if (!e.getEp().isExact()) {
                    listId_.add(e);
                } else {
                    listExact_.add(e);
                }
            }
            listId_.sortElts(new CmpLocalFileExcPoint());
            listExact_.sortElts(new CmpLocalFileExcPoint());
            AbstractMutableTreeNode file_ = compoFactory.newMutableTreeNode(p.getKey());
            for (ExcPointBlockPair l: listId_) {
                file_.add(compoFactory.newMutableTreeNode("all types family in "+l.getEp().getClName()));
            }
            for (ExcPointBlockPair l: listExact_) {
                file_.add(compoFactory.newMutableTreeNode("exact type "+l.getEp().getClName()));
            }
            exception.add(file_);
        }
        tree.reload(exception);
    }
    public void refreshMethod(ResultContext _res) {
        customMethod.removeAllChildren();
        metList.clear();
        for (MethodPointBlockPair p: _res.getContext().metList().elts()) {
            metList.put(p.getSgn(), p);
        }
        for (EntryCust<String, MethodPointBlockPair> p: metList.entryList()) {
            customMethod.add(compoFactory.newMutableTreeNode(p.getKey()));
        }
        tree.reload(customMethod);
    }
    public void refreshStdMethod(ResultContext _res) {
        standardMethod.removeAllChildren();
        stdList.clear();
        for (StdMethodPointBlockPair p: _res.getContext().stdList().elts()) {
            String className_ = p.getSm().getType().getFullName();
            CustList<StdMethodPointBlockPair> alreadyStd_ = stdList.getVal(className_);
            if (alreadyStd_ == null) {
                CustList<StdMethodPointBlockPair> local_ = new CustList<StdMethodPointBlockPair>();
                local_.add(p);
                stdList.put(className_, local_);
            } else {
                alreadyStd_.add(p);
            }
        }
        for (EntryCust<String, CustList<StdMethodPointBlockPair>> p: stdList.entryList()) {
            CustList<StdMethodPointBlockPair> list_ = p.getValue();
            list_.sortElts(new CmpLocalFileStdPoint());
            AbstractMutableTreeNode file_ = compoFactory.newMutableTreeNode(p.getKey());
            for (StdMethodPointBlockPair l: list_) {
                file_.add(compoFactory.newMutableTreeNode(l.getSm().keyStr()));
            }
            standardMethod.add(file_);
        }
        tree.reload(standardMethod);
    }
    public AbsTreeGui getTree() {
        return tree;
    }

    public NatStringTreeMap<CustList<BreakPointBlockPair>> getBpList() {
        return bpList;
    }

    public NatStringTreeMap<CustList<WatchPointBlockPair>> getWpList() {
        return wpList;
    }

    public NatStringTreeMap<CustList<WatchPointBlockPair>> getWpListAnnot() {
        return wpListAnnot;
    }

    public NatStringTreeMap<CustList<ExcPointBlockPair>> getExcList() {
        return excList;
    }

    public NatStringTreeMap<MethodPointBlockPair> getMetList() {
        return metList;
    }

    public NatStringTreeMap<CustList<StdMethodPointBlockPair>> getStdList() {
        return stdList;
    }

    public AbsPlainButton getCreate() {
        return create;
    }

}
