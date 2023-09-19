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
import code.util.CustList;

public final class DbgRootStruct extends DbgAbsNodeStruct {

    public DbgRootStruct(ContextEl _r) {
        super(_r);
    }
    AbsTreeGui buildReturn(AbsCompoFactory _compo, ArgumentWrapper _val) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode("");
        DbgAbsNodeStruct result_;
        if (_val.getWrapper() != null) {
            result_ = new DbgRetVarStruct(getResult(), _val.getWrapper());
        } else {
            result_ = new DbgParentStruct(getResult(),_val.getValue().getStruct());
        }
        getChildren().add(result_);
        result_.setParentStruct(this);
        getNode().add(result_.getNode());
        root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(result_, this.getResult())));
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(this,tree_,_compo));
        return tree_;
    }
    AbsTreeGui build(AbsCompoFactory _compo, ViewPage _stView, BreakPointOutputInfo _infos) {
        AbstractMutableTreeNodeCore<String> root_ = _compo.newMutableTreeNode("");
        ContextEl s_ = _infos.getSubContext();
        if (s_ != null) {
            Struct o_ = _infos.getWatchedObject();
            if (o_ != null) {
                DbgParentStruct pt_ = new DbgParentStruct(s_, o_);
                pt_.setParentStruct(this);
                getChildren().add(pt_);
                getNode().add(pt_.getNode());
                root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_, s_)));
            }
            Struct t_ = _infos.getWatchedTrace();
            if (t_ != null) {
                DbgParentStruct pt_ = new DbgParentStruct(s_, t_);
                pt_.setParentStruct(this);
                getChildren().add(pt_);
                getNode().add(pt_.getNode());
                root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_, s_)));
            }
        }
        DbgCallStruct pt_ = new DbgCallStruct(getResult(), _stView.getInstance());
        root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(pt_, this.getResult())));
        pt_.setParentStruct(this);
        getChildren().add(pt_);
        getNode().add(pt_.getNode());
        CustList<ViewVariable> ls_ = _stView.getVars();
        for (ViewVariable f: ls_) {
            DbgVarStruct nodeVar_ = new DbgVarStruct(getResult(), f);
            nodeVar_.setParentStruct(this);
            getNode().add(nodeVar_.getNode());
            getChildren().add(nodeVar_);
            root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(nodeVar_, this.getResult())));
        }
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(this,tree_,_compo));
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
