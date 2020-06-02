package code.sml;

public interface Node {

    MutableNode getNextSibling();

    MutableNode getPreviousSibling();

    MutableNode getFirstChild();

    MutableNode getLastChild();

    Document getOwnerDocument();
    Element getParentNode();
    NamedNodeMap getAttributes();
    NodeList getChildNodes();
    ElementList getChildElements();


    boolean hasChildNodes();

    boolean hasAttributes();

    String getTextContent();

}
