package code.sml;

public interface Node {

    Node getNextSibling();

    Node getPreviousSibling();

    Node getFirstChild();

    Node getLastChild();

    Document getOwnerDocument();
    Element getParentNode();
    NamedNodeMap getAttributes();
    NodeList getChildNodes();
    ElementList getChildElements();


    boolean hasChildNodes();

    boolean hasAttributes();

    String getTextContent();

}
