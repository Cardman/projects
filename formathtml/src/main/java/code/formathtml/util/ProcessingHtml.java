package code.formathtml.util;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.xml.RowCol;
import code.xml.XmlParser;

public final class ProcessingHtml {

    private ObjectMap<NodeAttribute,NatTreeMap<Integer,Integer>> encodedChars;

    private String html;

    private Node processingNode;

    private boolean lookForAttrValue;

    private Element root;

    public RowCol getRowCol(String _attribute, int _offset, int _tabWidth) {
        int delta_ = 0;
        if (encodedChars != null && _attribute != null){
            NatTreeMap<Integer, Integer> esc_ = getEscapedChars(_attribute);
            if (esc_ != null) {
                int nbIndexes_ = getIndexesCount(esc_, _offset);
//                NodeAttribute na_ = new NodeAttribute();
//                na_.setNode(processingNode);
//                na_.setAttribue(processingAttribute);
                for (int i = 0; i < nbIndexes_; i++) {
                    delta_ += esc_.getValue(i);
                }
            }
        }
        return XmlParser.getRowColOfNodeOrAttribute(html, processingNode, _offset+delta_, _attribute, _tabWidth, lookForAttrValue);
    }

    private static int getIndexesCount(NatTreeMap<Integer, Integer> _t, int _offset) {
        int delta_ = 0;
        int count_ = 0;
        for (EntryCust<Integer, Integer> e: _t.entryList()) {
            if (e.getKey() - delta_ >= _offset) {
                return count_;
            }
            delta_ += e.getValue();
            count_++;
        }
        return count_;
    }
    private NatTreeMap<Integer, Integer> getEscapedChars(String _attribute) {
        for (EntryCust<NodeAttribute, NatTreeMap<Integer, Integer>> t: encodedChars.entryList()) {
            NodeAttribute c_ = t.getKey();
            if (c_.getNode() != processingNode) {
                continue;
            }
            if (!StringList.quickEq(c_.getAttribue(), _attribute)) {
                continue;
            }
            return t.getValue();
        }
        return null;
    }

    public ObjectMap<NodeAttribute,NatTreeMap<Integer,Integer>> getEncodedChars() {
        return encodedChars;
    }

    public void setEncodedChars(
            ObjectMap<NodeAttribute,NatTreeMap<Integer,Integer>> _encodedChars) {
        encodedChars = _encodedChars;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
    }

    public Node getProcessingNode() {
        return processingNode;
    }

    public void setProcessingNode(Node _processingNode) {
        processingNode = _processingNode;
    }

    public boolean isLookForAttrValue() {
        return lookForAttrValue;
    }

    public void setLookForAttrValue(boolean _lookForAttrValue) {
        lookForAttrValue = _lookForAttrValue;
    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element _root) {
        root = _root;
    }
}
