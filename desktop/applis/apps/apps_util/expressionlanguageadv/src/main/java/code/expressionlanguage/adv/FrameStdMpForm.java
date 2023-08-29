package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.StdMethodPointBlockPair;
import code.expressionlanguage.functionid.AbsractIdentifiableCommon;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.StringMap;

public final class FrameStdMpForm  extends AdvFrameMpForm{
    private final FrameStdMpFormContent frameMpFormContent;
    private AbsTreeGui treeStd;
    private AbsScrollPane treeStdScroll;
    private MutableTreeNodeNav root;
    private final StringMap<IdList<StandardNamedFunction>> listFct = new StringMap<IdList<StandardNamedFunction>>();
    private AbsPlainLabel label;

    public FrameStdMpForm() {
        frameMpFormContent = new FrameStdMpFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d, FramePoints _p) {
        treeStd = _d.getCommonFrame().getFrames().getCompoFactory().newTreeGui( _d.getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        label = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel("");
        root = new MutableTreeNodeNav();
        frameMpFormContent.guiBuildBase(_d);
        frameMpFormContent.getOk().addActionListener(new OkStdMpFormEvent(_d,this, _p));
        frameMpFormContent.getRemove().addActionListener(new OkRemoveStdFormEvent(_d, this, _p));
    }
    public void tree(AbsDebuggerGui _d, FramePoints _p) {
        listFct.clear();
        root = new MutableTreeNodeNav();
        AbsCompoFactory cf_ = _d.getCommonFrame().getFrames().getCompoFactory();
        AbstractMutableTreeNode root_ = cf_.newMutableTreeNode("");
        for (EntryCust<String, StandardType> t: _d.getCurrentResult().getPageEl().getStandardsTypes().entryList()) {
            AbstractMutableTreeNode rootType_ = cf_.newMutableTreeNode(t.getKey());
            MetaStdType mt_ = new MetaStdType(t.getValue());
            MutableTreeNodeCoreUtil.add(root, mt_);
            IdList<StandardNamedFunction> std_ = new IdList<StandardNamedFunction>();
            for (StandardConstructor c: t.getValue().getConstructors()) {
                std_.add(c);
            }
            for (StandardMethod c: t.getValue().getMethods()) {
                std_.add(c);
            }
            for (StandardNamedFunction c:std_) {
                if (c instanceof StandardConstructor) {
                    rootType_.add(cf_.newMutableTreeNode("constructor "+c.getSignature(_d.getCurrentResult().getPageEl().getDisplayedStrings())));
                } else {
                    rootType_.add(cf_.newMutableTreeNode("method "+c.getSignature(_d.getCurrentResult().getPageEl().getDisplayedStrings())));
                }
                MetaStdFunction m_ = new MetaStdFunction(t.getValue(), c);
                MutableTreeNodeCoreUtil.add(mt_, m_);
            }
            root_.add(rootType_);
            listFct.addEntry(t.getKey(), std_);
        }
        treeStd = cf_.newTreeGui(root_);
        label = cf_.newPlainLabel("");
        treeStd.addTreeSelectionListener(new SelectedStdFctTreeEvent(_d, root,this,_p));
        treeStdScroll = cf_.newAbsScrollPane(treeStd);
    }
    public void selectTree(AbsDebuggerGui _d, String _cl, AbsractIdentifiableCommon _id) {
        int index_ = _d.getCurrentResult().getPageEl().getStandardsTypes().indexOfEntry(_cl);
        IdList<StandardNamedFunction> lsType_ = listFct.getVal(_cl);
        if (lsType_ == null) {
            treeStd.select(treeStd.getRoot());
        } else {
            StandardType stType_ = _d.getCurrentResult().getPageEl().getStandardsTypes().getValue(index_);
            CustList<StandardNamedFunction> stds_ = listFct(_id, stType_);
            int in_ = index(stds_, lsType_);
            if (in_ < 0) {
                treeStd.select(treeStd.getRoot().getChildAt(index_));
            } else {
                treeStd.select(MutableTreeNodeCoreUtil.getChildAt(treeStd.getRoot().getChildAt(index_),in_));
            }
        }
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
    public void initForm(StdMethodPointBlockPair _s, AbsCommonFrame _f) {
        AbsPanel f_ = frameMpFormContent.getContentPaneForm();
        frameMpFormContent.setSelectedMp(_s);
        StdMethodPointBlockPair exc_ = frameMpFormContent.getSelectedMp();
        frameMpFormContent.getContentPane().removeAll();
        if (exc_ != null) {
            label.setText(exc_.getSm().keyStr());
            getEnabledMp().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getFrameMpFormContent().getGuiEnterStackForm(), true, exc_.getValue().getResultEntry(), _f);
            BreakPointFormEvent.specific(getFrameMpFormContent().getGuiExitStackForm(), true, exc_.getValue().getResultExit(), _f);
            getFrameMpFormContent().getEnterFunction().setSelected(exc_.getValue().isEntry());
            getFrameMpFormContent().getExitFunction().setSelected(exc_.getValue().isExit());
            frameMpFormContent.getContentPane().add(label);
            frameMpFormContent.getContentPane().add(f_);
            frameMpFormContent.getContentPane().add(frameMpFormContent.getOk());
            frameMpFormContent.getContentPane().add(frameMpFormContent.getRemove());
        } else {
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
