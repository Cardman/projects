package code.sml;

import code.util.CustList;
import code.util.IntTreeMap;

public final class Text extends NavigableNode {

    private String textContent;

    public Text(Document _ownerDocument) {
        super(_ownerDocument);
    }

    public void appendData(String _arg) {
        String textContent_ = getTextContent();
        StringBuilder str_ = new StringBuilder(textContent_.length() + _arg.length());
        str_.append(textContent_);
        str_.append(_arg);
        setTextContent(str_.toString());
    }

    public void deleteData(int _offset, int _count) {
        String textContent_ = getTextContent();
        String first_ = textContent_.substring(0, _offset);
        String last_ = textContent_.substring(_offset + _count);
        StringBuilder str_ = new StringBuilder(textContent_.length());
        str_.append(first_);
        str_.append(last_);
        setTextContent(str_.toString());
    }

    public String getData() {
        return getTextContent();
    }

    public int getLength() {
        return textContent.length();
    }

    public void insertData(int _offset, String _arg) {
        String textContent_ = getTextContent();
        String first_ = textContent_.substring(0, _offset);
        String last_ = textContent_.substring(_offset);
        StringBuilder str_ = new StringBuilder(textContent_.length() + _arg.length());
        str_.append(first_);
        str_.append(_arg);
        str_.append(last_);
        setTextContent(str_.toString());
    }

    public void replaceData(int _offset, int _count, String _arg) {
        String textContent_ = getTextContent();
        String first_ = textContent_.substring(0, _offset);
        String last_ = textContent_.substring(_offset + _count);
        StringBuilder str_ = new StringBuilder(textContent_.length());
        str_.append(first_);
        str_.append(_arg);
        str_.append(last_);
        setTextContent(str_.toString());
    }

    public void setData(String _data) {
        setTextContent(_data);
    }

    public String substringData(int _offset, int _count) {
        return getTextContent().substring(_offset, _offset + _count);
    }

    @Override
    public ElementList getChildElements() {
        return new ElementList();
    }

    @Override
    public boolean hasChildNodes() {
        return false;
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String _textContent) {
        textContent = _textContent;
    }

    public void setEscapedTextContent(int _from, String _escapedTextContent, CustList<EncodedChar> _encodes, IntTreeMap<Integer> _found) {
        textContent = DocumentBuilder.transformSpecialChars(_from, _escapedTextContent, _encodes, _found);
    }

}
