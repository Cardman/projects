package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.BreakPointOutputInfo;
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
        super(_r,_par);
    }
    AbsTreeGui buildReturn(CustList<RenderPointPair> _renderList, AbsCompoFactory _compo, AbstractThreadFactory _th, ArgumentWrapper _val) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode("");
        DbgAbsNodeStruct result_;
        if (_val.getWrapper() != null) {
            result_ = new DbgRetVarStruct(this, _val.getWrapper());
        } else {
            result_ = new DbgParentStruct(this,_val.getValue().getStruct());
        }
        getChildren().add(result_);
        getNode().add(result_.getNode());
        root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(result_)));
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(_renderList,tree_, this, _compo,_th));
        return tree_;
    }
    AbsTreeGui build(CustList<RenderPointPair> _renderList, AbsCompoFactory _compo, AbstractThreadFactory _th, ViewPage _stView, BreakPointOutputInfo _infos) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode("");
        ContextEl s_ = _infos.getSubContext();
        if (s_ != null) {
            Struct o_ = _infos.getWatchedObject();
            if (o_ != null) {
                DbgWatchStruct pt_ = new DbgWatchStruct(s_,this, o_);
                getChildren().add(pt_);
                getNode().add(pt_.getNode());
                root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_)));
            }
            Struct t_ = _infos.getWatchedTrace();
            if (t_ != null) {
                DbgWatchStruct pt_ = new DbgWatchStruct(s_,this, t_);
                getChildren().add(pt_);
                getNode().add(pt_.getNode());
                root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_)));
            }
        }
        DbgCallStruct pt_ = new DbgCallStruct(this, _stView.getInstance());
        root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_)));
        getChildren().add(pt_);
        getNode().add(pt_.getNode());
        CustList<ViewVariable> ls_ = _stView.getVars();
        for (ViewVariable f: ls_) {
            DbgVarStruct nodeVar_ = new DbgVarStruct(this, f);
            getNode().add(nodeVar_.getNode());
            getChildren().add(nodeVar_);
            root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(nodeVar_)));
        }
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(_renderList,tree_, this, _compo,_th));
        return tree_;
    }

    @Override
    public Struct value() {
        return null;
    }

    @Override
    public String str() {
        return "";
    }

}
