package code.formathtml.util;
import code.sml.Node;
import code.util.EnumList;

public class NodeAction {

    private Node node;

    private EnumList<ActionNext> actions = new EnumList<ActionNext>();

    public Node getNode() {
        return node;
    }

    public void setNode(Node _node) {
        node = _node;
    }

    public EnumList<ActionNext> getActions() {
        return actions;
    }

    public void setActions(EnumList<ActionNext> _actions) {
        actions = _actions;
    }
}
