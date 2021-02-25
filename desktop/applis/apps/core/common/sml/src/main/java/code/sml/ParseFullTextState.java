package code.sml;

public final class ParseFullTextState extends AbstractParseTextState {

    private final StringBuilder currentText = new StringBuilder();

    ParseFullTextState(FullDocument _doc, FullElement _currentElement,String _input, int _index) {
        super(_doc,_currentElement,_input,_index);
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
        Text attr_ = getDoc().createEscapedTextNode(currentText.toString());
        currentText.delete(0, currentText.length());
        getCurrentElement().appendChild(attr_);
        return processAfterText(_len);
    }


}
