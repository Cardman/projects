package code.formathtml.util;
import code.sml.Element;

public class BeanElement {

    private String beanName;

    private Element root;

    private String url;

    private String html;

    private String prefix;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element _root) {
        root = _root;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String _url) {
        url = _url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
    }

    public final String getPrefix() {
        return prefix;
    }

    public final void setPrefix(String _prefix) {
        prefix = _prefix;
    }

}
