package code.formathtml.util;
import code.xml.components.Element;

public interface BlockHtml {

    Element getReadNode();

    void setReadNode(Element _readNode);

    Element getWriteNode();

    void setWriteNode(Element _writeNode);
}
