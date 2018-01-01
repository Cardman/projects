package code.formathtml.util;
import code.sml.DocumentBuilder;
import code.sml.Node;
import code.util.StringList;
import code.util.ints.Equallable;

public final class NodeAttribute implements Equallable<NodeAttribute> {

    private Node node;

    private String attribue;

    @Override
    public String toString() {
        return StringList.concat(DocumentBuilder.getIndexes(node).display(),attribue);
    }

    @Override
    public boolean eq(NodeAttribute _obj) {
        if (_obj.node != node) {
            return false;
        }
        return StringList.quickEq(_obj.attribue, attribue);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node _node) {
        node = _node;
    }

    public String getAttribue() {
        return attribue;
    }

    public void setAttribue(String _attribue) {
        attribue = _attribue;
    }

}
