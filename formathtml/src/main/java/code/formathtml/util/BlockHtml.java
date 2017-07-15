package code.formathtml.util;
import org.w3c.dom.Element;

public interface BlockHtml {

    Element getReadNode();

    void setReadNode(Element _readNode);

    Element getWriteNode();

    void setWriteNode(Element _writeNode);
}
