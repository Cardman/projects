package code.gui;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;

import code.sml.Attr;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.NamedNodeMap;
import code.sml.Node;
import code.sml.NodeList;
import code.util.CustList;
import code.util.StringList;

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

            for (Object e: Collections.list(a_.getAttributeNames())) {
                Object value_ = a_.getAttribute(e);
                boolean found_ = false;
                if (e.toString().equalsIgnoreCase(ANCHOR)) {
                    found_ = true;
                } else if (e.toString().equalsIgnoreCase(SPAN)) {
                    found_ = true;
                }
                if (!(value_ instanceof SimpleAttributeSet)) {
                    continue;
                }
                SimpleAttributeSet sas_ = (SimpleAttributeSet) value_;
                if (found_) {
                    for (Object a: Collections.list(sas_.getAttributeNames())) {
                        if (a.toString().equalsIgnoreCase(TITLE)) {
                            return ((SimpleAttributeSet)value_).getAttribute(a).toString();
                        }
                    }
                }
            }
        }
        return text_;
    }

    public void initializeFixedPage(String _formattedText,
            boolean _autoSubmission) {
        setup(_formattedText, _autoSubmission);
    }

    private void setup(String _text, boolean _autoSubmission) {
        String textToDisplay_ = _text;
        textToDisplay_ = StringList.removeStrings(textToDisplay_, RETURN_LINE2, RETURN_LINE);
        setHtmlKit(new HTMLEditorKit());
        setupText(_text, _autoSubmission);
    }

    void setupText(String _text, boolean _autoSubmission) {
        tooltips.clear();
        Document doc_ = DocumentBuilder.parseSax(_text);
        NodeList nodes_ = doc_.getElementsByTagName(TAG_A);
        int size_ = nodes_.getLength();
        for (int i = CustList.FIRST_INDEX;i<size_;i++) {
            Node node_ = nodes_.item(i);
            NamedNodeMap map_ = node_.getAttributes();
            if (map_ == null) {
                continue;
            }
            Attr href_ = map_.getNamedItem(ATTRIBUTE_HREF);
            if (href_ == null) {
                continue;
            }
            Attr title_ = map_.getNamedItem(ATTRIBUTE_TITLE);
            if (title_ == null) {
                continue;
            }
            tooltips.add(title_.getValue());
        }
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
