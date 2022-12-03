package code.sml;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NavigationCore {

    private static final String REF_TAG = "#";

    private static final String EMPTY_STRING = "";
    private String htmlText = "";

    private String referenceScroll = "";

    private String title = EMPTY_STRING;
    private Document document;
    private String currentUrl = "";

    private String language = "";
    private StringList languages = new StringList();

    private StringMap<String> files = new StringMap<String>();
    private String currentBeanName = "";
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
