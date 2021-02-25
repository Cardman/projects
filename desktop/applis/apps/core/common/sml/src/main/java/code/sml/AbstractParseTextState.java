package code.sml;

import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AbstractParseTextState {

    protected static final char LT_CHAR = '<';
    protected static final char GT_CHAR = '>';
    private static final char ENCODED = '&';

    private static final char SLASH = '/';

    private static final char QUOT_CHAR = '"';
    private static final char APOS_CHAR = '\'';
    private static final char EQUALS = '=';

    private static final char TAB = '\t';

    private static final char LINE_RETURN = '\n';

    private final CoreDocument doc;
    private final String input;
    private int indexFoot;
    private ReadingState state = ReadingState.HEADER;
    private boolean addChild = true;
    private char delimiterAttr;
    private final StringBuilder attributeName = new StringBuilder();
    private final StringBuilder tagName = new StringBuilder();
    private final StringList stack = new StringList();
    private Element currentElement;
    private boolean finished;
    private final StringBuilder attributeValue = new StringBuilder();
    private CustList<Attr> attrs = new CustList<Attr>();
    private int index;
    AbstractParseTextState(CoreDocument _doc, Element _currentElement,String _input, int _index) {
        doc = _doc;
        currentElement = _currentElement;
        input = _input;
        index = _index;
    }

    static DocumentResult parseCommon(DocumentResult _res, CoreDocument _doc, String _input, int _len, AbstractParseTextState _st) {
        while (_st.index < _len) {
            if (_st.exit()) {
                break;
            }
        }
        if (!_st.finished) {
            return processErr(_res, _input, _len, _st.index, _doc.getTabWidth());
        }
        _res.setDocument(_doc);
        return _res;
    }

    private static DocumentResult processErr(DocumentResult _res, String _input, int _len, int _i, int _tabWidth) {
        int max_;
        if (_i < _len) {
            max_ = _i;
        } else {
            max_ = _len - 1;
        }
        int row_ = 1;
        int col_ = 1;
        int j_ = IndexConstants.FIRST_INDEX;
        while (j_ <= max_) {
            char curChar_ = _input.charAt(j_);
            if (curChar_ == LINE_RETURN) {
                row_++;
                col_ = 1;
            } else {
                col_++;
                if (curChar_ == TAB) {
                    col_ += _tabWidth - 1;
                }
            }
            j_++;
        }
        RowCol rc_ = new RowCol();
        rc_.setRow(row_);
        rc_.setCol(col_);
        _res.setLocation(rc_);
        return _res;
    }
    private boolean exit() {
        int len_ = input.length();
        char curChar_ = input.charAt(index);
        if (state == ReadingState.HEADER) {
            return !processHeader(len_, curChar_);
        }
        if (state == ReadingState.ATTR_NAME) {
            return !processAttrName(len_, curChar_);
        }
        if (state == ReadingState.ATTR_VALUE) {
            return !processAttrValue(len_, curChar_);
        }
        if (state == ReadingState.TEXT) {
            return !processText(len_, curChar_);
        }
        int indexTag_ = index - indexFoot;
        String lastTag_ = stack.last();
        if (lastTag_.charAt(indexTag_) != curChar_) {
            return true;
        }
        if (curChar_ == GT_CHAR) {
            return !processEndTag(len_);
        }
        index++;
        return false;
    }

    protected boolean processAfterText(int _len) {
        if (index + 1 >= _len) {
            return false;
        }
        if (input.charAt(index + 1) == SLASH) {
            index++;
            index++;
            indexFoot = index;
            this.state = ReadingState.FOOTER;
            return true;
        }
        index++;
        this.state = ReadingState.HEADER;
        this.addChild = true;
        return true;
    }

    private boolean processHeader(int _len, char _curChar) {
        if (koTagHeader(_curChar)) {
            return false;
        }
        if (tagName.length() == 0) {
            if (koTagHeaderNoTagName(_curChar)) {
                return false;
            }
            tagName.append(_curChar);
            index++;
            return true;
        }
        if (_curChar == GT_CHAR) {
            return endTagCommon(_len);
        }
        if (StringUtil.isWhitespace(_curChar)) {
            return processSpace(_len);
        }
        if (_curChar != SLASH) {
            tagName.append(_curChar);
            index++;
            return true;
        }
        if (koEndTagHeader(input, _len, index + 1)) {
            return false;
        }
        addChild = false;
        index++;
        return true;
    }

    private boolean processSpace(int _len) {
        int nextPrintable_ = nextPrintable(_len, index);
        if (nextPrintable_ == _len) {
            return false;
        }
        state = ReadingState.ATTR_NAME;
        index = nextPrintable_;
        return true;
    }

    private boolean processAttrName(int _len, char _curChar) {
        if (koAttrName(_curChar)) {
            return false;
        }
        if (!StringUtil.isWhitespace(_curChar) && _curChar != EQUALS) {
            attributeName.append(_curChar);
            index++;
            return true;
        }
        if (_curChar != EQUALS) {
            //Character.isWhitespace(curChar_)
            int nextPrintable_ = nextPrintable(_len, index);
            if (koAfterAttrName(input, _len, nextPrintable_)) {
                return false;
            }
            index = nextPrintable_;
        }
        if (index + 1 >= _len) {
            return false;
        }
        return processAttrNameHeader(_len);
    }

    private boolean processAttrNameHeader(int _len) {
        char nextEq_ = input.charAt(index + 1);
        if (notDelAttr(nextEq_)) {
            if (!StringUtil.isWhitespace(nextEq_)) {
                return false;
            }
            int nextPrintable_ = nextPrintable(_len, index + 1);
            if (nextPrintable_ == _len) {
                return false;
            }
            char nextCharDel_ = input.charAt(nextPrintable_);
            if (notDelAttr(nextCharDel_)) {
                return false;
            }
            index = nextPrintable_;
        } else {
            index++;
        }
        return checkDuplicate();
    }

    private int nextPrintable(int _len, int _i) {
        int nextPrintable_ = _i;
        while (nextPrintable_ < _len) {
            char next_ = input.charAt(nextPrintable_);
            if (!StringUtil.isWhitespace(next_)) {
                break;
            }
            nextPrintable_++;
        }
        return nextPrintable_;
    }

    private boolean checkDuplicate() {
        String foundName_ = attributeName.toString();
        boolean ok_ = true;
        for (Attr a: attrs) {
            if (StringUtil.quickEq(a.getName(), foundName_)) {
                ok_ = false;
                break;
            }
        }
        if (!ok_) {
            return false;
        }
        delimiterAttr = input.charAt(index);
        state = ReadingState.ATTR_VALUE;
        index++;
        return true;
    }

    private boolean processAttrValue(int _len, char _curChar) {
        if (koAttrValue(_curChar)) {
            return false;
        }
        if (_curChar != delimiterAttr) {
            attributeValue.append(_curChar);
            index++;
            return true;
        }
        Attr attr_ = CoreDocument.createAttribute(attributeName.toString(),attributeValue.toString());
        attrs.add(attr_);
        attributeName.delete(0, attributeName.length());
        attributeValue.delete(0, attributeValue.length());
        int nextPrintable_ = nextPrintable(_len, index + 1);
        if (nextPrintable_ == _len) {
            return false;
        }
        char nextPr_ = input.charAt(nextPrintable_);
        boolean endHead_ = false;
        if (nextPr_ == SLASH) {
            index = nextPrintable_ + 1;
            if (koEndTagHeader(input, _len, index)) {
                return false;
            }
            endHead_ = true;
            addChild = false;
        }
        if (nextPr_ == GT_CHAR) {
            index = nextPrintable_;
            endHead_ = true;
        }
        if (endHead_) {
            return endTagCommon(_len);
        }
        state = ReadingState.ATTR_NAME;
        index = nextPrintable_;
        return true;
    }

    private boolean endTagCommon(int _len) {
        Element element_ = doc.createElement(tagName.toString());
        element_.setAttributes(new NamedNodeMap(attrs));
        attrs = new CustList<Attr>();
        commonAppend(doc, currentElement, element_);
        if (addChild) {
            currentElement = element_;
            stack.add(tagName.append(GT_CHAR).toString());
        } else {
            if (stack.isEmpty()) {
                finished = true;
                return false;
            }
        }
        tagName.delete(IndexConstants.FIRST_INDEX, tagName.length());
        if (index + 2 >= _len) {
            return false;
        }
        if (input.charAt(index + 1) == LT_CHAR) {
            if (input.charAt(index + 2) == SLASH) {
                index++;
                //input.charAt(index) == '<'
                index++;
                //input.charAt(index) == '/'
                index++;
                //input.charAt(index) is the first character of end tag
                state = ReadingState.FOOTER;
                indexFoot = index;
                return true;
            }
            index++;
            index++;
            state = ReadingState.HEADER;
            addChild = true;
            return true;
        }
        state = ReadingState.TEXT;
        index++;
        return true;
    }


    private boolean processEndTag(int _len) {
        //end tag
        stack.removeQuicklyLast();
        Element parent_ = currentElement.getParentNode();
        if (parent_ != null) {
            currentElement = parent_;
        }
        if (stack.isEmpty()) {
            finished = true;
            return false;
        }
        if (index + 2 >= _len) {
            return false;
        }
        if (input.charAt(index + 1) == LT_CHAR) {
            if (input.charAt(index + 2) == SLASH) {
                index++;
                //input.charAt(index) == '<'
                index++;
                //input.charAt(index) == '/'
                index++;
                //input.charAt(index) is the first character of end tag
                indexFoot = index;
                state = ReadingState.FOOTER;
                return true;
            }
            index++;
            index++;
            state = ReadingState.HEADER;
            addChild = true;
            return true;
        }
        state = ReadingState.TEXT;
        index++;
        return true;
    }
    protected void incr() {
        index++;
    }
    protected abstract boolean processText(int _len, char _curChar);

    protected CoreDocument getDoc() {
        return doc;
    }

    protected Element getCurrentElement() {
        return currentElement;
    }

    private static boolean koAttrValue(char _curChar) {
        return _curChar == LT_CHAR || _curChar == GT_CHAR;
    }

    private static boolean notDelAttr(char _nextCharDel) {
        return _nextCharDel != APOS_CHAR && _nextCharDel != QUOT_CHAR;
    }

    private static boolean koEndTagHeader(String _input, int _len, int _i) {
        return _i >= _len || _input.charAt(_i) != GT_CHAR;
    }

    private static boolean koTagHeader(char _curChar) {
        return _curChar == LT_CHAR || _curChar == ENCODED;
    }

    private static boolean koTagHeaderNoTagName(char _curChar) {
        return _curChar == GT_CHAR || _curChar == SLASH || StringUtil.isWhitespace(_curChar);
    }

    private static boolean koAfterAttrName(String _input, int _len, int _nextPrintable) {
        return _nextPrintable == _len || _input.charAt(_nextPrintable) != EQUALS;
    }

    private static boolean koAttrName(char _curChar) {
        return _curChar == LT_CHAR || _curChar == ENCODED || _curChar == GT_CHAR || _curChar == SLASH;
    }

    private static void commonAppend(CoreDocument _doc, Element _currentElement, Element _element) {
        if (_doc.getDocumentElement() == null) {
            _doc.appendChild(_element);
        } else {
            _currentElement.appendChild(_element);
        }
    }

}
