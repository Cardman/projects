package code.sml;


public interface Document {

    int getTabWidth();

    Element createElement(String _tagName);

    Element getDocumentElement();

    Text createTextNode(String _data);
    Text createEscapedTextNode(String _data);
    void appendChild(Node _newChild);
    void renameNode(Node _node, String _name);

    String export();
    NodeList getDescNodes();
    NodeList getElementsByTagName();

    ElementList getElementsByTagName(String _tagName);

    String getTextContent();

}
