package code.sml;

public final class ParseNoTextState extends AbstractParseTextState {

    ParseNoTextState(NoTextDocument _doc, NotTextElement _currentElement,String _input, int _index) {
        super(_doc,_currentElement,_input,_index);
    }

    @Override
    protected boolean processText(int _len, char _curChar) {
        if (_curChar == GT_CHAR) {
            return false;
        }
        if (_curChar != LT_CHAR) {
//                    currentText_.append(curChar_);
            incr();
            return true;
        }
//                Text attr_ = doc.createEscapedTextNode(currentText_.toString());
//                currentText_.delete(0, currentText_.length());
//                currentElement.appendChild(attr_);
        return processAfterText(_len);
    }

}
