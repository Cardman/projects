package code.formathtml.util;
import code.sml.Element;

public interface BlockHtml {

    Element getReadNode();

    void setReadNode(Element _readNode);

    Element getWriteNode();

    void setWriteNode(Element _writeNode);
}
