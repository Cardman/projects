package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.util.IdList;

public final class TreeStruct extends CustComponentStruct {
    private final AbsTreeGui tree;
    private final IdList<Struct> actionsTree = new IdList<Struct>();
    public TreeStruct(String _className, AbsTreeGui _tree) {
        super(_className);
        tree = _tree;
    }

    public BooleanStruct isRootVisible() {
        return BooleanStruct.of(tree.isRootVisible());
    }

    public void setRootVisible(Struct _rootVisible) {
        tree.setRootVisible(BooleanStruct.isTrue(_rootVisible));
    }

    public Struct getLastSelectedPathComponent() {
        return TreeNodeStruct.nodeOrNull(tree.selectEvt());
    }
    public void select(Struct _node) {
        if (_node instanceof TreeNodeStruct) {
            tree.select(((TreeNodeStruct) _node).getTreeNode());
        }
    }

    public void addTreeSelectionListener(Struct _tsl, StackCall _stackCall) {
        if (_tsl instanceof AbsShortListTree) {
            if (_stackCall.getStopper().getLogger() != null) {
                tree.addTreeSelectionListenerMap((AbsShortListTree) _tsl);
            } else {
                tree.addTreeSelectionListener((AbsShortListTree) _tsl);
            }
            actionsTree.add(_tsl);
        }
    }

    public void removeTreeSelectionListener(Struct _tsl, StackCall _stackCall) {
        if (_tsl instanceof AbsShortListTree) {
            if (_stackCall.getStopper().getLogger() != null) {
                tree.removeTreeSelectionListenerMap((AbsShortListTree) _tsl);
            } else {
                tree.removeTreeSelectionListener((AbsShortListTree) _tsl);
            }
            actionsTree.removeObj(_tsl);
        }
    }

    public ArrayStruct getActions(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasTreeListener();
        return nulls(aliasMouseListener_, actionsTree);
    }

    public void reload() {
        MutableTreeNodeUtil.reload(tree);
    }

    public IdList<Struct> getActionsTree() {
        return actionsTree;
    }

    @Override
    protected AbsCustComponent getComponent() {
        return tree;
    }
}
