package code.gui;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import code.util.CustList;
import code.util.StringList;
import code.xml.XmlParser;

public class EditorPane extends JTextPane {

    private static final String TITLE = "title";
    private static final String SPAN = "span";
    private static final String ANCHOR = "a";
    private static final String RETURN_LINE2 = "\r\n";
    private static final String RETURN_LINE = "\n";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String ATTRIBUTE_TITLE = TITLE;
    private static final String ATTRIBUTE_HREF = "href";
    private static final String TAG_A = ANCHOR;
//    private static final String EMPTY_STRING = "";

    private StringList tooltips = new StringList();
    private HTMLEditorKit htmlKit;
    private JLabel tooltip;

    public void setTooltipLabel(JLabel _tooltip) {
        setTooltip(_tooltip);
    }

    @Override
    public String getToolTipText(MouseEvent _event) {
        String text_ = null;
        int pos_ = viewToModel(_event.getPoint());
        if (pos_ >= 0) {
            if (!(getDocument() instanceof StyledDocument)) {
                return null;
            }
            StyledDocument hdoc_ = getStyledDocument();
            Element e_ = hdoc_.getCharacterElement(pos_);
            AttributeSet a_ = e_.getAttributes();

            Enumeration<?> enumm_ = a_.getAttributeNames();
            while (enumm_.hasMoreElements()) {
                Object o_ = enumm_.nextElement();
                Object value_ = a_.getAttribute(o_);
                boolean found_ = false;
                if (o_.toString().equalsIgnoreCase(ANCHOR)) {
                    found_ = true;
                } else if (o_.toString().equalsIgnoreCase(SPAN)) {
                    found_ = true;
                }
                if (value_ instanceof SimpleAttributeSet && found_) {
                    Enumeration<?> at_ = ((SimpleAttributeSet)value_).getAttributeNames();
                    while (at_.hasMoreElements()) {
                        Object att_ = at_.nextElement();
                        if (att_.toString().equalsIgnoreCase(TITLE)) {
                            return ((SimpleAttributeSet)value_).getAttribute(att_).toString();
                        }
                    }
                }
            }

//            SimpleAttributeSet value_ = (SimpleAttributeSet) a_.getAttribute(Tag.A);
//            if (value_ != null) {
//                String href_ = (String) value_.getAttribute(Attribute.TITLE);
//                if (href_ != null) {
//                    text_ = href_;
//                }
//            }
//            if (text_ == null) {
//                value_ = (SimpleAttributeSet) a_.getAttribute(Tag.SPAN);
//                if (value_ != null) {
//                    String href_ = (String) value_.getAttribute(Attribute.TITLE);
//                    if (href_ != null) {
//                        text_ = href_;
//                    }
//                }
//            }
        }
        return text_;
    }

    public void initializeFixedPage(String _formattedText,
            boolean _autoSubmission) {
        setup(_formattedText, _autoSubmission);
    }

    private void setup(String _text, boolean _autoSubmission) {
        String textToDisplay_ = _text;
//        textToDisplay_ = textToDisplay_.replace(RETURN_LINE2, EMPTY_STRING);
//        textToDisplay_ = textToDisplay_.replace(RETURN_LINE, EMPTY_STRING);
        textToDisplay_ = StringList.removeStrings(textToDisplay_, RETURN_LINE2, RETURN_LINE);
        setHtmlKit(new HTMLEditorKit());
        setupText(_text, _autoSubmission);
    }

    void setupText(String _text, boolean _autoSubmission) {
        tooltips.clear();
        Document doc_ = XmlParser.parseSaxHtml(_text, false, true);
        NodeList nodes_ = doc_.getElementsByTagName(TAG_A);
        int size_ = nodes_.getLength();
        for (int i = CustList.FIRST_INDEX;i<size_;i++) {
            Node node_ = nodes_.item(i);
            NamedNodeMap map_ = node_.getAttributes();
            if (map_ == null) {
                continue;
            }
            Node href_ = map_.getNamedItem(ATTRIBUTE_HREF);
            if (href_ == null) {
                continue;
            }
            Node title_ = map_.getNamedItem(ATTRIBUTE_TITLE);
            if (title_ == null) {
                continue;
            }
            tooltips.add(title_.getNodeValue());
        }
//        Reader stringReader_ = new StringReader(_text);
        getHtmlKit().setAutoFormSubmission(_autoSubmission);
        setContentType(CONTENT_TYPE);
        setEditorKit(getHtmlKit());
        setText(_text);
    }
    protected StringList getTooltips() {
        return tooltips;
    }

    protected HTMLEditorKit getHtmlKit() {
        return htmlKit;
    }

    protected void setHtmlKit(HTMLEditorKit _htmlKit) {
        htmlKit = _htmlKit;
    }

    protected JLabel getTooltip() {
        return tooltip;
    }

    protected void setTooltip(JLabel _tooltip) {
        tooltip = _tooltip;
    }
}
