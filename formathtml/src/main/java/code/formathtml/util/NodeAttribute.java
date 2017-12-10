package code.formathtml.util;
import code.xml.components.Node;

import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.XmlParser;

public final class NodeAttribute implements Equallable<NodeAttribute> {

    private Node node;

    private String attribue;

    @Override
    public String toString() {
        return XmlParser.getIndexes(node)+attribue;
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
