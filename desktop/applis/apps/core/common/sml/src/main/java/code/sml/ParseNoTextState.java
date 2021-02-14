package code.sml;

import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ParseNoTextState extends AbstractParseTextState {


    private static final char SLASH = '/';

    private static final char LT_CHAR = '<';
    private static final char GT_CHAR = '>';

    private static final char EQUALS = '=';
    private final NoTextDocument doc;
    private final String input;
    private int indexFoot;
    private ReadingState state = ReadingState.HEADER;
    private boolean addChild = true;
    private char delimiterAttr;
    private final StringBuilder attributeName = new StringBuilder();
    private final StringBuilder tagName = new StringBuilder();
    private final StringList stack = new StringList();
    private NotTextElement currentElement;
    private boolean finished;
    private final StringBuilder attributeValue = new StringBuilder();
    private CustList<Attr> attrs = new CustList<Attr>();
    private int index;
    ParseNoTextState(NoTextDocument _doc, NotTextElement _currentElement,String _input, int _index) {
        doc = _doc;
        currentElement = _currentElement;
        input = _input;
        index = _index;
    }
    boolean keep() {
        int len_ = input.length();
        char curChar_ = input.charAt(index);
        if (state == ReadingState.HEADER) {
            return processHeader(len_, curChar_);
        }
        if (state == ReadingState.ATTR_NAME) {
            return processAttrName(len_, curChar_);
        }
        if (state == ReadingState.ATTR_VALUE) {
            return processAttrValue(len_, curChar_);
        }
        if (state == ReadingState.TEXT) {
            return processText(len_, curChar_);
        }
        int indexTag_ = index - indexFoot;
        String lastTag_ = stack.last();
        if (lastTag_.charAt(indexTag_) != input.charAt(index)) {
            return false;
        }
        if (input.charAt(index) == GT_CHAR) {
            return processEndTag(len_);
        }
        index++;
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

    private boolean processEndHeaderTag(int _len) {
        NotTextElement element_ = (NotTextElement) doc.createElement(tagName.toString());
        element_.setAttributes(new NamedNodeMap(attrs));
        attrs = new CustList<Attr>();
        appendCh(doc, currentElement, element_);
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
        return procAttrKeyHeader(_len);
    }

    private boolean procAttrKeyHeader(int _len) {
        if (index + 1 >= _len) {
            return false;
        }
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
        NotTextElement element_ = (NotTextElement) doc.createElement(tagName.toString());
        element_.setAttributes(new NamedNodeMap(attrs));
        attrs = new CustList<Attr>();
        appendCh(doc, currentElement, element_);
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
            state = ReadingState.HEADER;
            addChild = true;
            index++;
            index++;
            return true;
        }
        state = ReadingState.TEXT;
        index++;
        return true;
    }

    private boolean processText(int _len, char _curChar) {
        if (_curChar == GT_CHAR) {
            return false;
        }
        if (_curChar != LT_CHAR) {
//                    currentText_.append(curChar_);
            index++;
            return true;
        }
//                Text attr_ = doc.createEscapedTextNode(currentText_.toString());
//                currentText_.delete(0, currentText_.length());
//                currentElement.appendChild(attr_);
        if (index + 1 >= _len) {
            return false;
        }
        if (input.charAt(index + 1) == SLASH) {
            index++;
            index++;
            indexFoot = index;
            state = ReadingState.FOOTER;
            return true;
        }
        index++;
        state = ReadingState.HEADER;
        addChild = true;
        return true;
    }

    private boolean processEndTag(int _len) {
        //end tag
        stack.removeQuicklyLast();
        Element parent_ = currentElement.getParentNode();
        if (parent_ instanceof NotTextElement) {
            currentElement = (NotTextElement) parent_;
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

    public int getIndex() {
        return index;
    }

    public boolean isFinished() {
        return finished;
    }

    private static void appendCh(NoTextDocument _doc, NotTextElement _currentElement, NotTextElement _element) {
        commonAppend(_doc, _currentElement, _element);
    }

}
