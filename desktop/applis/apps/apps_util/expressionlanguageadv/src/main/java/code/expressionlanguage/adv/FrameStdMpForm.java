package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.expressionlanguage.functionid.AbsractIdentifiableCommon;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class FrameStdMpForm  extends AdvFrameMpForm{
    private final FrameStdMpFormContent frameMpFormContent;
    private AbsTreeGui treeStd;
    private AbsScrollPane treeStdScroll;
    private MutableTreeNodeNav<AbsMetaStdType> root;
    private final StringMap<IdList<StandardNamedFunction>> listFct = new StringMap<IdList<StandardNamedFunction>>();
    private AbsPlainLabel label;

    public FrameStdMpForm(AbstractProgramInfos _c) {
        frameMpFormContent = new FrameStdMpFormContent(_c);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        treeStd = _d.getCommonFrame().getFrames().getCompoFactory().newTreeGui( _d.getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        label = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel("");
        root = new MutableTreeNodeNav<AbsMetaStdType>();
        frameMpFormContent.guiBuildBase(_d);
    }
    public void tree(AbsDebuggerGui _d, FramePoints _p, ResultContext _res) {
        listFct.clear();
        root = new MutableTreeNodeNav<AbsMetaStdType>();
        AbsCompoFactory cf_ = _d.getCommonFrame().getFrames().getCompoFactory();
        AbstractMutableTreeNodeCore<String> root_ = cf_.newMutableTreeNode("");
        for (EntryCust<String, StandardType> t: _res.getPageEl().getStandardsTypes().entryList()) {
            AbstractMutableTreeNodeCore<String> rootType_ = cf_.newMutableTreeNode(t.getKey());
            MetaStdType mt_ = new MetaStdType(t.getValue());
            root.add(mt_.getElt());
            IdList<StandardNamedFunction> std_ = new IdList<StandardNamedFunction>();
            for (StandardConstructor c: t.getValue().getConstructors()) {
                std_.add(c);
            }
            for (StandardMethod c: t.getValue().getMethods()) {
                std_.add(c);
            }
            for (StandardNamedFunction c:std_) {
                if (c instanceof StandardConstructor) {
                    rootType_.add(cf_.newMutableTreeNode("constructor "+c.getSignature(_res.getPageEl().getDisplayedStrings())));
                } else {
                    rootType_.add(cf_.newMutableTreeNode("method "+c.getSignature(_res.getPageEl().getDisplayedStrings())));
                }
                MetaStdFunction m_ = new MetaStdFunction(t.getValue(), c);
                mt_.getElt().add(m_.getElt());
            }
            root_.add(rootType_);
            listFct.addEntry(t.getKey(), std_);
        }
        treeStd = cf_.newTreeGui(root_);
        label = cf_.newPlainLabel("");
        treeStd.addTreeSelectionListener(new SelectedStdFctTreeEvent(_d, root,this,_p, _res));
        treeStdScroll = cf_.newAbsScrollPane(treeStd);
    }
    public void selectTree(String _cl, AbsractIdentifiableCommon _id) {
        treeStd.select(retrieve(_cl, _id));
    }

    public AbstractMutableTreeNodeCore<String> retrieve(String _cl, AbsractIdentifiableCommon _id) {
        return node(treeStd.getRoot().getElt(indexes(_cl, _id, listFct.getVal(_cl), ((SelectedStdFctTreeEvent)treeStd.getTreeSelectionListeners().get(0)).getCurrentResult())));
    }

    public AbstractMutableTreeNodeCore<String> node(AbstractMutableTreeNodeCore<String> _e) {
        if (_e == null) {
            return rootTree();
        }
        return _e;
    }

    public AbsTreeGui getTreeStd() {
        return treeStd;
    }

    public AbstractMutableTreeNodeCore<String> rootTree() {
        return getTreeStd().getRoot();
    }

    private static Ints indexes(String _cl, AbsractIdentifiableCommon _id, IdList<StandardNamedFunction> _ls, ResultContext _res) {
        IdList<StandardNamedFunction> lsType_ = allFcts(_ls);
        int index_ = _res.getPageEl().getStandardsTypes().indexOfEntry(_cl);
        CustList<StandardNamedFunction> stds_ = stds(_id, index_, _res);
        if (index_ < 0) {
            return Ints.newList();
        }
        int in_ = index(stds_, lsType_);
        if (in_ < 0) {
            return Ints.newList(index_);
        }
        return Ints.newList(index_,in_);
    }

    private static CustList<StandardNamedFunction> stds(AbsractIdentifiableCommon _id, int _index, ResultContext _res) {
        CustList<StandardNamedFunction> stds_;
        if (_res.getPageEl().getStandardsTypes().isValidIndex(_index)) {
            stds_ = listFct(_id, _res.getPageEl().getStandardsTypes().getValue(_index));
        } else {
            stds_ = new CustList<StandardNamedFunction>();
        }
        return stds_;
    }

    private static IdList<StandardNamedFunction> allFcts(IdList<StandardNamedFunction> _ls) {
        if (_ls == null) {
            return new IdList<StandardNamedFunction>();
        }
        return _ls;
    }

    private static CustList<StandardNamedFunction> listFct(AbsractIdentifiableCommon _id, StandardType _type) {
        if (_id == null){
            return new CustList<StandardNamedFunction>();
        }
        return _id.look(_type);
    }
    private static int index(CustList<StandardNamedFunction> _id, IdList<StandardNamedFunction> _ls) {
        if (_id.isEmpty()){
            return -1;
        }
        return _ls.indexOfObj(_id.get(0));
    }
    public void initForm(StdMethodPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        AbsPanel f_ = frameMpFormContent.getContentPaneForm();
        frameMpFormContent.setSelectedMp(_s);
        StdMethodPointBlockPair exc_ = frameMpFormContent.getSelectedMp();
        frameMpFormContent.getContentPane().removeAll();
        if (exc_ != null) {
            label.setText(exc_.getSm().keyStr());
            getEnabledMp().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getFrameMpFormContent().getGuiEnterStackForm(), true, exc_.getValue().getResultEntry(), new CustList<BreakPointCondition>(), _f,_r);
            BreakPointFormEvent.specific(getFrameMpFormContent().getGuiExitStackForm(), true, exc_.getValue().getResultExit(), new CustList<BreakPointCondition>(), _f,_r);
            getFrameMpFormContent().getEnterFunction().setSelected(exc_.getValue().isEntry());
            getFrameMpFormContent().getExitFunction().setSelected(exc_.getValue().isExit());
            frameMpFormContent.getContentPane().add(label);
            frameMpFormContent.getContentPane().add(f_);
            frameMpFormContent.getContentPane().add(frameMpFormContent.getOk());
            frameMpFormContent.getContentPane().add(frameMpFormContent.getRemove());
        } else {
            getGuiEnterStackForm().getDependantPointsForm().init(_r,StdMethodPointBlockPair.SMP);
            getGuiExitStackForm().getDependantPointsForm().init(_r,StdMethodPointBlockPair.SMP);
            frameMpFormContent.getContentPane().add(f_);
            frameMpFormContent.getContentPane().add(treeStdScroll);
        }
    }

    public AbsPanel getContentPane() {
        return frameMpFormContent.getContentPane();
    }

    @Override
    public AbsFrameMpFormContent form() {
        return getFrameMpFormContent();
    }

    public FrameStdMpFormContent getFrameMpFormContent() {
        return frameMpFormContent;
    }

    public StdMethodPointBlockPair getSelectedMp() {
        return getFrameMpFormContent().getSelectedMp();
    }

    public void setSelectedMp(StdMethodPointBlockPair _s) {
        this.getFrameMpFormContent().setSelectedMp(_s);
    }

}
