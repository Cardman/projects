package code.sml;

import code.util.CustList;
import code.util.StringMap;

public final class ParseNoTextState extends AbstractParseTextState {

    ParseNoTextState(CoreDocument _doc, Element _currentElement, String _input, int _index, StringMap<String> _e, CustList<EncodedChar> _encoded) {
        super(_doc,_currentElement,_input,_index, _e, _encoded);
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
