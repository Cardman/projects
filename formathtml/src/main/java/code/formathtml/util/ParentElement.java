package code.formathtml.util;
import org.w3c.dom.Element;

public final class ParentElement {

    private final Element element;

    public ParentElement(Element _element) {
        element = _element;
    }

    public Element getElement() {
        return element;
    }
}
