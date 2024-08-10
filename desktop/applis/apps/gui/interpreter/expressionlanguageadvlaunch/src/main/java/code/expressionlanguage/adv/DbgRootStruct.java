package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.BreakPointOutputInfo;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.exec.variables.ViewVariable;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class DbgRootStruct extends DbgAbsNodeStruct {

    public DbgRootStruct(ContextEl _r, DbgAbsNodeStruct _par) {
        super(_r,_r,_par);
    }
    AbsTreeGui buildReturn(AbsDebuggerGui _win, CustList<RenderPointPair> _renderList, AbsCompoFactory _compo, AbstractThreadFactory _th, ArgumentWrapper _val) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode(AbsEditorTabList.EMPTY_STRING);
        setAssociated(root_);
        DbgAbsNodeStruct result_;
        if (_val.getWrapper() != null) {
            result_ = new DbgRetVarStruct(this, _val.getWrapper());
        } else {
            result_ = new DbgParentStruct(this,_val.getValue().getStruct());
        }
        getChildren().add(result_);
        getNode().add(result_.getNode());
        AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(result_));
        result_.setAssociated(sub_);
        root_.add(sub_);
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(_win,_renderList,tree_, this, _compo,_th));
        return tree_;
    }
    AbsTreeGui build(AbsDebuggerGui _win, CustList<RenderPointPair> _renderList, AbsCompoFactory _compo, AbstractThreadFactory _th, ViewPage _stView, BreakPointOutputInfo _infos) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode(AbsEditorTabList.EMPTY_STRING);
        setAssociated(root_);
        addWatches(_compo, root_, _infos.getWatchResults());
        DbgCallStruct pt_ = new DbgCallStruct(this, _stView.getInstance());
        AbstractMutableTreeNodeCore<String> subCall_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_));
        root_.add(subCall_);
        pt_.setAssociated(subCall_);
        getChildren().add(pt_);
        getNode().add(pt_.getNode());
        CustList<ViewVariable> ls_ = _stView.getVars();
        for (ViewVariable f: ls_) {
            DbgVarStruct nodeVar_ = new DbgVarStruct(this, f);
            getNode().add(nodeVar_.getNode());
            getChildren().add(nodeVar_);
            AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(nodeVar_));
            root_.add(sub_);
            nodeVar_.setAssociated(sub_);
        }
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(_win,_renderList,tree_, this, _compo,_th));
        return tree_;
    }
    AbsTreeGui buildDynamic(AbsDebuggerGui _win, CustList<RenderPointPair> _renderList, AbsCompoFactory _compo, AbstractThreadFactory _th) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode("vars");
        setAssociated(root_);
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(_win,_renderList,tree_, this, _compo,_th));
        return tree_;
    }

    void addWatches(AbsCompoFactory _compo, AbstractMutableTreeNodeCore<String> _root, WatchResults _wr) {
        ContextEl s_ = _wr.getSubContext();
        if (s_ != null) {
            Struct o_ = _wr.getWatchedObject();
            if (o_ != null) {
                DbgWatchStruct pt_ = new DbgWatchStruct(s_,this, o_);
                getChildren().add(pt_);
                getNode().add(pt_.getNode());
                AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_));
                pt_.setAssociated(sub_);
                _root.add(sub_);
            }
            Struct t_ = _wr.getWatchedTrace();
            if (t_ != null) {
                DbgWatchStruct pt_ = new DbgWatchStruct(s_,this, t_);
                getChildren().add(pt_);
                getNode().add(pt_.getNode());
                AbstractMutableTreeNodeCore<String> sub_ = _compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_));
                pt_.setAssociated(sub_);
                _root.add(sub_);
            }
        }
    }

    @Override
    public Struct value() {
        return null;
    }

    @Override
    public String str() {
        return AbsEditorTabList.EMPTY_STRING;
    }

}
