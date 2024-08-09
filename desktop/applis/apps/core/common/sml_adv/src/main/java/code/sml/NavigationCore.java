package code.sml;

import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NavigationCore {

    public static final String EMPTY_STRING = "";
    public static final String REF_TAG = "#";

    public static final char EQUAL = '=';
    public static final String LINE_RETURN = "\n";
    public static final String TAB = "\t";
    public static final String BEFORE_LINE_RETURN = "\r\n";
    private String htmlText = "";

    private String referenceScroll = "";

    private String title = EMPTY_STRING;
    private Document document;
    private String currentUrl = "";

    private String language = "";
    private StringList languages = new StringList();

    private StringMap<String> files = new StringMap<String>();
    private String currentBeanName = "";

    public static StringMap<String> getMessages(String _content) {
        String lastKey_ = EMPTY_STRING;
        StringMap<String> messages_ = new StringMap<String>();
        for (String l: StringUtil.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
            if (l.isEmpty()) {
                continue;
            }
            if (l.startsWith(TAB)) {
                String text_ = messages_.getVal(lastKey_);
                if (text_ != null) {
                    text_ = StringUtil.concat(text_,l.substring(1));
                    messages_.put(lastKey_, text_);
                }
            } else {
                int indexSep_ = l.indexOf(EQUAL);
                lastKey_ = l.substring(0,indexSep_);
                messages_.put(lastKey_, l.substring(indexSep_+1));
            }
        }
        return messages_;
    }

    public static void appendText(String _fileContent, Document _ownerDocument, Element _eltStyle) {
        CustList<Node> chNode_ = _eltStyle.getChildNodes();
        if (chNode_.isEmpty()) {
            Text text_ = _ownerDocument.createTextNode(_fileContent);
            _eltStyle.appendChild(text_);
        } else {
            Text text_ = (Text) chNode_.last();
            text_.appendData(_fileContent);
        }
    }

    public static Element appendChild(Document _doc, Element _read) {
        String tagName_ = _read.getTagName();
        Element currentNode_ = _doc.createElement(tagName_);
        setNormalAttributes(_read, currentNode_);
        return currentNode_;
    }

    public static void simpleAppendChild(Document _doc, RendReadWrite _rw, Node _currentNode) {
        simpleAppendChild(_doc,_rw.getWrite(),_currentNode);
    }

    public static void simpleAppendChild(Document _doc, Element _parent, Node _currentNode) {
        if (_parent == null) {
            _doc.appendChild(_currentNode);
        } else {
            _parent.appendChild(_currentNode);
        }
    }

    public static Element getParentNode(RendReadWrite _rw) {
        Element wr_ = _rw.getWrite();
        return getParentNode(wr_);
    }

    public static Element getParentNode(Element _elt) {
        if (_elt == null) {
            return null;
        }
        return _elt.getParentNode();
    }

    public static void setNormalAttributes(Element _read, Element _write) {
        NamedNodeMap map_ = _read.getAttributes();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Attr at_ = map_.item(i);
            String name_ = at_.getName();
            String value_ = at_.getValue();
            _write.setAttribute(name_, value_);
        }
    }

    public static boolean prImg(ConfigurationCore _cont, RendKeyWordsAttrs _attrs, Element _nextWrite, String _link) {
        String file_ = StringUtil.nullToEmpty(_cont.getFiles().getVal(_link));
        if (file_.isEmpty()) {
            return false;
        }
        _nextWrite.setAttribute(_attrs.getAttrSrc(),file_);
        return true;
    }

    public static void prHeader(RendKeyWordsTags _cont, String _fileContent, Document _ownerDocument, Element _head) {
        CustList<Element> children_ = new CustList<Element>();
        for (Element c: _head.getChildElements()) {
            if (!StringUtil.quickEq(c.getTagName(), _cont.getKeyWordStyle())) {
                continue;
            }
            children_.add(c);
        }
        boolean successAdd_ = children_.isEmpty();
        if (!successAdd_) {
            Element eltStyle_ = children_.last();
            appendText(_fileContent, _ownerDocument, eltStyle_);
        } else {
            Element eltStyle_ = _ownerDocument.createElement(_cont.getKeyWordStyle());
            Text text_ = _ownerDocument.createTextNode(_fileContent);
            eltStyle_.appendChild(text_);
            _head.appendChild(eltStyle_);
        }
    }

//    public static void adjustMap(StringMap<StringMap<String>> _mes) {
//        for (StringMap<String> m: _mes.values()) {
//            adjust(m);
//        }
//    }

//    public static void adjust(StringMap<String> _mes) {
//        for (EntryCust<String,String> e: _mes.entryList()) {
//            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(),true,true));
//        }
//    }

    public static String oneElt(StringList _element) {
        String v_;
        if (_element.isEmpty()) {
            v_ = null;
        } else {
            v_ = _element.first();
        }
        return StringUtil.nullToEmpty(v_);
    }

    public static String getId(ConfigurationCore _cont, Element _write, RendKeyWordsGroup _k) {
        String id_ = _write.getAttribute(_k.getKeyWordsAttrs().getAttrId());
        if (id_.isEmpty()) {
            id_ = _write.getAttribute(StringUtil.concat(_cont.getPrefix(), _k.getKeyWordsAttrs().getAttrGroupId()));
        }
        return id_;
    }

    public static void procDefValue(Element _elt, String _strObj, RendKeyWordsGroup _k) {
        if (StringUtil.quickEq(_elt.getAttribute(_k.getKeyWordsAttrs().getAttrValue()), _strObj)) {
            _elt.setAttribute(_k.getKeyWordsAttrs().getAttrChecked(), _k.getKeyWordsAttrs().getAttrChecked());
        } else {
            _elt.removeAttribute(_k.getKeyWordsAttrs().getAttrChecked());
        }
    }

    public static String getInputClass(ConfigurationCore _cont, Element _write, FieldUpdates _f, RendKeyWordsGroup _k) {
        String class_ = _write.getAttribute(StringUtil.concat(_cont.getPrefix(), _k.getKeyWordsAttrs().getAttrClassName()));
        if (class_.isEmpty()) {
            class_ = _f.getClassName();
        }
        return class_;
    }

    public boolean setupText(String _text, Document _document, String _head, String _title) {
        if (_text.isEmpty()) {
            return false;
        }
        document = _document;
        setupText(_text,_head,_title);
        return true;
    }
    public void setupText(String _text, String _head, String _title) {
        ElementList nodes_;
        title = EMPTY_STRING;
        nodes_ = document.getElementsByTagName(_head);
        int size_ = nodes_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
            Element node_ = nodes_.item(i);
            ElementList subNodes_ = node_.getElementsByTagName(_title);
            int subListSize_ = subNodes_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < subListSize_; j++) {
                Element subNode_ = subNodes_.item(j);
                title = subNode_.getTextContent().trim();
            }
        }
        htmlText = _text;
        StringList tokens_ = StringUtil.splitStrings(getCurrentUrl(), REF_TAG);
        if (tokens_.size() > IndexConstants.ONE_ELEMENT) {
            referenceScroll = tokens_.get(IndexConstants.SECOND_INDEX);
            setCurrentUrl(tokens_.first());
        } else {
            referenceScroll = EMPTY_STRING;
        }
    }
    public void setLanguage(String _language) {
        language = _language;
    }


    public StringMap<String> getFiles() {
        return files;
    }
    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }
    public String getLanguage() {
        return language;
    }
    public String getHtmlText() {
        return htmlText;
    }

    public Document getDocument() {
        return document;
    }

    public String getReferenceScroll() {
        return referenceScroll;
    }

    public String getTitle() {
        return title;
    }


    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String _v) {
        this.currentUrl = _v;
    }

    public String getCurrentBeanName() {
        return currentBeanName;
    }

    public void setCurrentBeanName(String _v) {
        this.currentBeanName = _v;
    }
}
