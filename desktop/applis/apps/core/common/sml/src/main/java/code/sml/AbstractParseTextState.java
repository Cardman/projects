package code.sml;

import code.util.core.StringUtil;

public abstract class AbstractParseTextState {

    private static final char ENCODED = '&';

    private static final char SLASH = '/';

    private static final char LT_CHAR = '<';
    private static final char GT_CHAR = '>';
    private static final char QUOT_CHAR = '"';
    private static final char APOS_CHAR = '\'';
    private static final char EQUALS = '=';
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
