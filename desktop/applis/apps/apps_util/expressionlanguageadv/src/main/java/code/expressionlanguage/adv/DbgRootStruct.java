package code.expressionlanguage.adv;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.exec.variables.ViewVariable;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;
import code.gui.MutableTreeNodeCoreUtil;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class DbgRootStruct extends DbgAbsNodeStruct {

    public DbgRootStruct(ResultContext _r) {
        super(_r);
    }
    AbsTreeGui buildReturn(AbsCompoFactory _compo, ArgumentWrapper _val) {
        AbstractMutableTreeNode root_ = _compo.newMutableTreeNode("");
        DbgAbsNodeStruct result_;
        if (_val.getWrapper() != null) {
            result_ = new DbgRetVarStruct(getResult(), _val.getWrapper());
        } else {
            result_ = new DbgParentStruct(getResult(),_val.getValue().getStruct());
        }
        getChildren().add(result_);
        result_.setParentStruct(this);
        MutableTreeNodeCoreUtil.add(this, result_);
        root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(this,result_)));
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(this,tree_));
        return tree_;
    }
    AbsTreeGui build(AbsCompoFactory _compo, ViewPage _stView) {
        AbstractMutableTreeNode root_ = _compo.newMutableTreeNode("");
        DbgCallStruct pt_ = new DbgCallStruct(getResult(), _stView.getInstance());
        root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(this,pt_)));
        pt_.setParentStruct(this);
        getChildren().add(pt_);
        MutableTreeNodeCoreUtil.add(this, pt_);
        CustList<ViewVariable> ls_ = _stView.getVars();
        for (ViewVariable f: ls_) {
            DbgVarStruct nodeVar_ = new DbgVarStruct(getResult(), f);
            nodeVar_.setParentStruct(this);
            MutableTreeNodeCoreUtil.add(this, nodeVar_);
            getChildren().add(nodeVar_);
            root_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(this,nodeVar_)));
        }
        AbsTreeGui tree_ = _compo.newTreeGui(root_);
        tree_.addTreeSelectionListener(new DbgSelectNodeEvent(this,tree_));
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
