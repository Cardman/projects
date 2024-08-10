package code.expressionlanguage.adv;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsButton;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
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
    public static final int SORT_AP = 6;
    public static final int SORT_PP = 7;
    public static final int SORT_OP = 8;
    public static final int SORT_CP = 9;
    public static final int SORT_TP = 10;
    private final AbsCompoFactory compoFactory;
    private AbsTreeGui tree;
    private AbsButton create;
    private AbstractMutableTreeNodeCore<String> instruction;
    private AbstractMutableTreeNodeCore<String> watchField;
    private AbstractMutableTreeNodeCore<String> watchFieldAnnot;
    private AbstractMutableTreeNodeCore<String> exception;
    private AbstractMutableTreeNodeCore<String> customMethod;
    private AbstractMutableTreeNodeCore<String> standardMethod;
    private AbstractMutableTreeNodeCore<String> arrayPoints;
    private AbstractMutableTreeNodeCore<String> parentPoints;
    private AbstractMutableTreeNodeCore<String> operNatPoints;
    private AbstractMutableTreeNodeCore<String> operNatCompoPoints;
    private AbstractMutableTreeNodeCore<String> typePoints;
    private final NatStringTreeMap<CustList<BreakPointBlockPair>> bpList = new NatStringTreeMap<CustList<BreakPointBlockPair>>();
    private final NatStringTreeMap<CustList<WatchPointBlockPair>> wpList = new NatStringTreeMap<CustList<WatchPointBlockPair>>();
    private final NatStringTreeMap<CustList<WatchPointBlockPair>> wpListAnnot = new NatStringTreeMap<CustList<WatchPointBlockPair>>();
    private final NatStringTreeMap<CustList<ExcPointBlockPair>> excList = new NatStringTreeMap<CustList<ExcPointBlockPair>>();
    private final NatStringTreeMap<CustList<ParPointBlockPair>> parList = new NatStringTreeMap<CustList<ParPointBlockPair>>();
    private final NatStringTreeMap<OperNatPointBlockPair> operNatList = new NatStringTreeMap<OperNatPointBlockPair>();
    private final NatStringTreeMap<OperNatPointBlockPair> operNatCompoList = new NatStringTreeMap<OperNatPointBlockPair>();
    private final NatStringTreeMap<MethodPointBlockPair> metList = new NatStringTreeMap<MethodPointBlockPair>();
    private final NatStringTreeMap<CustList<StdMethodPointBlockPair>> stdList = new NatStringTreeMap<CustList<StdMethodPointBlockPair>>();
    private final NatStringTreeMap<CustList<ArrPointBlockPair>> arrList = new NatStringTreeMap<CustList<ArrPointBlockPair>>();
    private final NatStringTreeMap<CustList<TypePointBlockPair>> tpList = new NatStringTreeMap<CustList<TypePointBlockPair>>();
    public FramePointsTree(AbsCompoFactory _c) {
        compoFactory = _c;
    }

    public void guiBuild() {
        AbstractMutableTreeNodeCore<String> root_ = compoFactory.newMutableTreeNode("break points");
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
        arrayPoints = compoFactory.newMutableTreeNode("array point");
        root_.add(arrayPoints);
        parentPoints = compoFactory.newMutableTreeNode("parent point");
        root_.add(parentPoints);
        operNatPoints = compoFactory.newMutableTreeNode("native operator point");
        root_.add(operNatPoints);
        operNatCompoPoints = compoFactory.newMutableTreeNode("native compound operator point");
        root_.add(operNatCompoPoints);
        typePoints = compoFactory.newMutableTreeNode("type points");
        root_.add(typePoints);
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
        refreshArray(_res);
        refreshException(_res);
        refreshParent(_res);
        refreshOperNat(_res);
        refreshOperNatCompo(_res);
        refreshStdMethod(_res);
        refreshWp(_res);
        refreshWpAnnot(_res);
        refreshMethod(_res);
        refreshBp(_res);
        refreshTp(_res);
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
            AbstractMutableTreeNodeCore<String> file_ = compoFactory.newMutableTreeNode(p.getKey());
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
    private void refreshWp(NatStringTreeMap<CustList<WatchPointBlockPair>> _m,AbstractMutableTreeNodeCore<String> _node,ResultContext _res, boolean _trueField) {
        String suff_;
        if (_trueField) {
            suff_ = AbsEditorTabList.EMPTY_STRING;
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
            AbstractMutableTreeNodeCore<String> file_ = compoFactory.newMutableTreeNode(p.getKey());
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
        for (EntryCust<String, CustList<ExcPointBlockKey>> p: sortedExc(excList, _res.getContext().excList()).entryList()) {
            AbstractMutableTreeNodeCore<String> file_ = node(p.getKey(), p.getValue(), compoFactory);
            exception.add(file_);
        }
        tree.reload(exception);
    }
    public void refreshParent(ResultContext _res) {
        parentPoints.removeAllChildren();
        for (EntryCust<String, CustList<ExcPointBlockKey>> p: sortedPar(_res).entryList()) {
            AbstractMutableTreeNodeCore<String> file_ = node(p.getKey(), p.getValue(), compoFactory);
            parentPoints.add(file_);
        }
        tree.reload(parentPoints);
    }

    public void refreshOperNat(ResultContext _res) {
        operNatPoints.removeAllChildren();
        operNatList.clear();
        for (OperNatPointBlockPair p: _res.getContext().operNatList().elts()) {
            if (!p.getValue().isPossibleComp()) {
                operNatList.put(p.getOn().keyStr(), p);
            }
        }
        for (EntryCust<String, OperNatPointBlockPair> p: operNatList.entryList()) {
            operNatPoints.add(compoFactory.newMutableTreeNode(p.getValue().getOn().getFirst()+" "+p.getValue().getSymbol()+" "+p.getValue().getOn().getSecond()));
        }
        tree.reload(operNatPoints);
    }

    public void refreshOperNatCompo(ResultContext _res) {
        operNatCompoPoints.removeAllChildren();
        operNatCompoList.clear();
        for (OperNatPointBlockPair p: _res.getContext().operNatList().elts()) {
            if (p.getValue().isPossibleComp()) {
                operNatCompoList.put(p.getOn().keyStr(), p);
            }
        }
        for (EntryCust<String, OperNatPointBlockPair> p: operNatCompoList.entryList()) {
            operNatCompoPoints.add(compoFactory.newMutableTreeNode(p.getValue().getOn().getFirst()+" "+p.getValue().getSymbol()+" "+p.getValue().getOn().getSecond()));
        }
        tree.reload(operNatCompoPoints);
    }

    public void refreshTp(ResultContext _res) {
        typePoints.removeAllChildren();
        tpList.clear();
        for (TypePointBlockPair p: _res.tpList().elts()) {
            String fileName_ = ExecFileBlock.name(p.getBp().getFile());
            CustList<TypePointBlockPair> alreadyInst_ = tpList.getVal(fileName_);
            if (alreadyInst_ == null) {
                CustList<TypePointBlockPair> local_ = new CustList<TypePointBlockPair>();
                local_.add(p);
                tpList.put(fileName_, local_);
            } else {
                alreadyInst_.add(p);
            }
        }
        for (EntryCust<String, CustList<TypePointBlockPair>> p: tpList.entryList()) {
            CustList<TypePointBlockPair> list_ = p.getValue();
            list_.sortElts(new CmpLocalFileTypePoint());
            AbstractMutableTreeNodeCore<String> file_ = compoFactory.newMutableTreeNode(p.getKey());
            for (TypePointBlockPair l: list_) {
                file_.add(compoFactory.newMutableTreeNode(Long.toString(l.getBp().getOffset())));
            }
            typePoints.add(file_);
        }
        tree.reload(typePoints);
    }
    public void refreshArray(ResultContext _res) {
        arrayPoints.removeAllChildren();
        for (EntryCust<String, CustList<ExcPointBlockKey>> p: sortedArr(_res).entryList()) {
            AbstractMutableTreeNodeCore<String> file_ = node(p.getKey(), p.getValue(), compoFactory);
            arrayPoints.add(file_);
        }
        tree.reload(arrayPoints);
    }

    static AbstractMutableTreeNodeCore<String> node(String _key, CustList<ExcPointBlockKey> _value, AbsCompoFactory _compo) {
        CustList<ExcPointBlockKey> listInh_ = new CustList<ExcPointBlockKey>();
        CustList<ExcPointBlockKey> listId_ = new CustList<ExcPointBlockKey>();
        CustList<ExcPointBlockKey> listExact_ = new CustList<ExcPointBlockKey>();
        for (ExcPointBlockKey e: _value) {
            feedList(listInh_,listId_, listExact_, e);
        }
        listInh_.sortElts(new CmpLocalFileExcPoint());
        listId_.sortElts(new CmpLocalFileExcPoint());
        listExact_.sortElts(new CmpLocalFileExcPoint());
        return node(listInh_,listId_, listExact_, _key, _compo);
    }

    static NatStringTreeMap<CustList<ExcPointBlockKey>> sortedExc(NatStringTreeMap<CustList<ExcPointBlockPair>> _exListMap, AbsCollection<ExcPointBlockPair> _exCollection) {
        _exListMap.clear();
        for (ExcPointBlockPair p: _exCollection.elts()) {
            String className_ = StringExpUtil.getIdFromAllTypes(p.getEp().getClName());
            CustList<ExcPointBlockPair> alreadyExc_ = _exListMap.getVal(className_);
            if (alreadyExc_ == null) {
                CustList<ExcPointBlockPair> local_ = new CustList<ExcPointBlockPair>();
                local_.add(p);
                _exListMap.put(className_, local_);
            } else {
                alreadyExc_.add(p);
            }
        }
        NatStringTreeMap<CustList<ExcPointBlockKey>> excList_ = new NatStringTreeMap<CustList<ExcPointBlockKey>>();
        for (EntryCust<String,CustList<ExcPointBlockPair>> e: _exListMap.entryList()) {
            CustList<ExcPointBlockKey> synth_ = new CustList<ExcPointBlockKey>();
            for (ExcPointBlockPair f: e.getValue()) {
                synth_.add(f.getEp());
            }
            excList_.addEntry(e.getKey(), synth_);
        }
        return excList_;
    }

    static NatStringTreeMap<CustList<ExcPointBlockKey>> sortedRend(NatStringTreeMap<CustList<RenderPointPair>> _reListMap, CustList<RenderPointPair> _exCollection) {
        _reListMap.clear();
        for (RenderPointPair p: _exCollection) {
            String className_ = StringExpUtil.getIdFromAllTypes(p.getExcPointBlockPair().getEp().getClName());
            CustList<RenderPointPair> alreadyRec_ = _reListMap.getVal(className_);
            if (alreadyRec_ == null) {
                CustList<RenderPointPair> local_ = new CustList<RenderPointPair>();
                local_.add(p);
                _reListMap.put(className_, local_);
            } else {
                alreadyRec_.add(p);
            }
        }
        NatStringTreeMap<CustList<ExcPointBlockKey>> recList_ = new NatStringTreeMap<CustList<ExcPointBlockKey>>();
        for (EntryCust<String,CustList<RenderPointPair>> e: _reListMap.entryList()) {
            CustList<ExcPointBlockKey> synth_ = new CustList<ExcPointBlockKey>();
            for (RenderPointPair f: e.getValue()) {
                synth_.add(f.getExcPointBlockPair().getEp());
            }
            recList_.addEntry(e.getKey(), synth_);
        }
        return recList_;
    }

    private NatStringTreeMap<CustList<ExcPointBlockKey>> sortedPar(ResultContext _res) {
        parList.clear();
        for (ParPointBlockPair p: _res.getContext().parList().elts()) {
            String className_ = StringExpUtil.getIdFromAllTypes(p.getPp().getClName());
            CustList<ParPointBlockPair> alreadyExc_ = parList.getVal(className_);
            if (alreadyExc_ == null) {
                CustList<ParPointBlockPair> local_ = new CustList<ParPointBlockPair>();
                local_.add(p);
                parList.put(className_, local_);
            } else {
                alreadyExc_.add(p);
            }
        }
        NatStringTreeMap<CustList<ExcPointBlockKey>> excList_ = new NatStringTreeMap<CustList<ExcPointBlockKey>>();
        for (EntryCust<String, CustList<ParPointBlockPair>> e: parList.entryList()) {
            CustList<ExcPointBlockKey> synth_ = new CustList<ExcPointBlockKey>();
            for (ParPointBlockPair f: e.getValue()) {
                synth_.add(f.getPp());
            }
            excList_.addEntry(e.getKey(), synth_);
        }
        return excList_;
    }

    private NatStringTreeMap<CustList<ExcPointBlockKey>> sortedArr(ResultContext _res) {
        arrList.clear();
        for (ArrPointBlockPair p: _res.getContext().arrList().elts()) {
            String className_ = StringExpUtil.getIdFromAllTypes(p.getEp().getClName());
            CustList<ArrPointBlockPair> alreadyExc_ = arrList.getVal(className_);
            if (alreadyExc_ == null) {
                CustList<ArrPointBlockPair> local_ = new CustList<ArrPointBlockPair>();
                local_.add(p);
                arrList.put(className_, local_);
            } else {
                alreadyExc_.add(p);
            }
        }
        NatStringTreeMap<CustList<ExcPointBlockKey>> excList_ = new NatStringTreeMap<CustList<ExcPointBlockKey>>();
        for (EntryCust<String,CustList<ArrPointBlockPair>> e: arrList.entryList()) {
            CustList<ExcPointBlockKey> synth_ = new CustList<ExcPointBlockKey>();
            for (ArrPointBlockPair f: e.getValue()) {
                synth_.add(f.getEp());
            }
            excList_.addEntry(e.getKey(), synth_);
        }
        return excList_;
    }
    static AbstractMutableTreeNodeCore<String> node(CustList<ExcPointBlockKey> _listInherit, CustList<ExcPointBlockKey> _listId, CustList<ExcPointBlockKey> _listExact, String _key, AbsCompoFactory _compo) {
        AbstractMutableTreeNodeCore<String> file_ = _compo.newMutableTreeNode(_key);
        for (ExcPointBlockKey l: _listInherit) {
            file_.add(_compo.newMutableTreeNode("inherit from "+l.getClName()));
        }
        for (ExcPointBlockKey l: _listId) {
            file_.add(_compo.newMutableTreeNode("all types family in "+l.getClName()));
        }
        for (ExcPointBlockKey l: _listExact) {
            file_.add(_compo.newMutableTreeNode("exact type "+l.getClName()));
        }
        return file_;
    }

    static void feedList(CustList<ExcPointBlockKey> _listInherit,CustList<ExcPointBlockKey> _listId, CustList<ExcPointBlockKey> _listExact, ExcPointBlockKey _ep) {
        if (_ep.isExact() == ExcPointBlockKey.INHERIT) {
            _listInherit.add(_ep);
        } else if (_ep.isExact() == ExcPointBlockKey.SAME_FAMILY) {
            _listId.add(_ep);
        } else {
            _listExact.add(_ep);
        }
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
            AbstractMutableTreeNodeCore<String> file_ = compoFactory.newMutableTreeNode(p.getKey());
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

    public NatStringTreeMap<CustList<TypePointBlockPair>> getTpList() {
        return tpList;
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

    public NatStringTreeMap<CustList<ArrPointBlockPair>> getArrList() {
        return arrList;
    }

    public NatStringTreeMap<CustList<ParPointBlockPair>> getParList() {
        return parList;
    }

    public NatStringTreeMap<OperNatPointBlockPair> getOperNatList() {
        return operNatList;
    }

    public NatStringTreeMap<OperNatPointBlockPair> getOperNatCompoList() {
        return operNatCompoList;
    }

    public NatStringTreeMap<MethodPointBlockPair> getMetList() {
        return metList;
    }

    public NatStringTreeMap<CustList<StdMethodPointBlockPair>> getStdList() {
        return stdList;
    }

    public AbsButton getCreate() {
        return create;
    }

}
