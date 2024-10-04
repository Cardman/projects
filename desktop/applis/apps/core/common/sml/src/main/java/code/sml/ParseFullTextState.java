package code.sml;

import code.util.CustList;
import code.util.StringMap;

public final class ParseFullTextState extends AbstractParseTextState {

    private final StringBuilder currentText = new StringBuilder();

    ParseFullTextState(CoreDocument _doc, Element _currentElement, String _input, int _index, StringMap<String> _e, CustList<EncodedChar> _encoded) {
        super(_doc,_currentElement,_input,_index, _e, _encoded);
    }


    @Override
    protected boolean processText(int _len, char _curChar) {
        if (_curChar == GT_CHAR) {
            return false;
        }
        if (_curChar != LT_CHAR) {
            currentText.append(_curChar);
            incr();
            return true;
        }
        Text attr_ = new Text(getDoc());
        attr_.setEscapedTextContent(currentText.toString(), getChs());
        currentText.delete(0, currentText.length());
        getCurrentElement().appendChild(attr_);
        return processAfterText(_len);
    }


}
