package code.sml;

import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AbstractParseTextState {

    private static final char ENCODED = '&';

    private static final char SLASH = '/';

    private static final char LT_CHAR = '<';
    private static final char GT_CHAR = '>';
    private static final char QUOT_CHAR = '"';
    private static final char APOS_CHAR = '\'';
    private static final char EQUALS = '=';
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
    boolean exit() {
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
        if (lastTag_.charAt(indexTag_) != input.charAt(index)) {
            return true;
        }
        if (input.charAt(index) == GT_CHAR) {
            return !processEndTag(len_);
        }
        index++;
        return false;
    }

    protected boolean processAfterText(int _len) {
        if (getIndex() + 1 >= _len) {
            return false;
        }
        if (getInput().charAt(getIndex() + 1) == SLASH) {
            incr();
            incr();
            setIndexFoot(getIndex());
            setState(ReadingState.FOOTER);
            return true;
        }
        incr();
        setState(ReadingState.HEADER);
        setAddChild(true);
        return true;
    }

    private boolean processHeader(int _len, char _curChar) {
        if (directResChar2(_curChar)) {
            return false;
        }
        if (tagName.length() == 0) {
            if (directResChar(_curChar)) {
                return false;
            }
            tagName.append(_curChar);
            index++;
            return true;
        }
        if (_curChar == GT_CHAR) {
            return processEndHeaderTag(_len);
        }
        if (StringUtil.isWhitespace(_curChar)) {
            return processSpace(_len);
        }
        if (_curChar != SLASH) {
            tagName.append(_curChar);
            index++;
            return true;
        }
        if (notGt(input, _len, index)) {
            return false;
        }
        addChild = false;
        index++;
        return true;
    }

    private boolean processEndHeaderTag(int _len) {
        return endTagCommon(_len);
    }

    private boolean processSpace(int _len) {
        int nextPrintable_ = index;
        while (nextPrintable_ < _len) {
            char next_ = input.charAt(nextPrintable_);
            if (!StringUtil.isWhitespace(next_)) {
                break;
            }
            nextPrintable_++;
        }
        if (nextPrintable_ == _len) {
            return false;
        }
        state = ReadingState.ATTR_NAME;
        index = nextPrintable_;
        return true;
    }

    private boolean processAttrName(int _len, char _curChar) {
        if (directResChar3(_curChar)) {
            return false;
        }
        if (!StringUtil.isWhitespace(_curChar) && _curChar != EQUALS) {
            attributeName.append(_curChar);
            index++;
            return true;
        }
        if (_curChar != EQUALS) {
            //Character.isWhitespace(curChar_)
            int nextPrintable_ = index;
            while (nextPrintable_ < _len) {
                char next_ = input.charAt(nextPrintable_);
                if (!StringUtil.isWhitespace(next_)) {
                    break;
                }
                nextPrintable_++;
            }
            if (notEq(input, _len, nextPrintable_)) {
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
            int nextPrintable_ = index + 1;
            while (nextPrintable_ < _len) {
                char next_ = input.charAt(nextPrintable_);
                if (!StringUtil.isWhitespace(next_)) {
                    break;
                }
                nextPrintable_++;
            }
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
        if (directResChar4(_curChar)) {
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
        int nextPrintable_ = index + 1;
        while (nextPrintable_ < _len) {
            char next_ = input.charAt(nextPrintable_);
            if (!StringUtil.isWhitespace(next_)) {
                break;
            }
            nextPrintable_++;
        }
        if (nextPrintable_ == _len) {
            return false;
        }
        char nextPr_ = input.charAt(nextPrintable_);
        boolean endHead_ = false;
        if (nextPr_ == SLASH) {
            index = nextPrintable_ + 1;
            if (notGt2(input, _len, index)) {
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
            return processLastAttrValue(_len);
        }
        state = ReadingState.ATTR_NAME;
        index = nextPrintable_;
        return true;
    }

    private boolean processLastAttrValue(int _len) {
        return endTagCommon(_len);
    }

    private boolean endTagCommon(int _len) {
        Element element_ = doc.createElement(tagName.toString());
        element_.setAttributes(new NamedNodeMap(attrs));
        attrs = new CustList<Attr>();
        appendChFull(doc, currentElement, element_);
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

    private static void appendChFull(CoreDocument _doc, Element _currentElement, Element _element) {
        commonAppend(_doc, _currentElement, _element);
    }

    public boolean isFinished() {
        return finished;
    }

    public int getIndex() {
        return index;
    }

    protected CoreDocument getDoc() {
        return doc;
    }

    protected Element getCurrentElement() {
        return currentElement;
    }

    protected String getInput() {
        return input;
    }

    protected void setState(ReadingState _state) {
        this.state = _state;
    }

    protected void setIndexFoot(int _indexFoot) {
        indexFoot = _indexFoot;
    }

    protected void setAddChild(boolean _addChild) {
        this.addChild = _addChild;
    }

    protected static boolean directResChar4(char _curChar) {
        return _curChar == LT_CHAR || _curChar == GT_CHAR;
    }

    protected static boolean notDelAttr(char _nextCharDel) {
        return _nextCharDel != APOS_CHAR && _nextCharDel != QUOT_CHAR;
    }

    protected static boolean notGt(String _input, int _len, int _i) {
        return notGt2(_input, _len, _i + 1);
    }

    protected static boolean notGt2(String _input, int _len, int _i) {
        return _i >= _len || _input.charAt(_i) != GT_CHAR;
    }

    protected static boolean directResChar2(char _curChar) {
        return _curChar == LT_CHAR || _curChar == ENCODED;
    }

    protected static boolean directResChar(char _curChar) {
        return _curChar == GT_CHAR || _curChar == SLASH || StringUtil.isWhitespace(_curChar);
    }

    protected static boolean notEq(String _input, int _len, int _nextPrintable) {
        return _nextPrintable == _len || _input.charAt(_nextPrintable) != EQUALS;
    }

    protected static boolean directResChar3(char _curChar) {
        return _curChar == LT_CHAR || _curChar == ENCODED || _curChar == GT_CHAR || _curChar == SLASH;
    }

    protected static void commonAppend(CoreDocument _doc, Element _currentElement, Element _element) {
        if (_doc.getDocumentElement() == null) {
            _doc.appendChild(_element);
        } else {
            _currentElement.appendChild(_element);
        }
    }

}
