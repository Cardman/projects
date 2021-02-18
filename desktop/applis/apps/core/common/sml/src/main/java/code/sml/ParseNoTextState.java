package code.sml;

public final class ParseNoTextState extends AbstractParseTextState {


    private static final char SLASH = '/';

    private static final char LT_CHAR = '<';
    private static final char GT_CHAR = '>';

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

}
