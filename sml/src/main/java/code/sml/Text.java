package code.sml;

public final class Text extends CharacterData {

    private String textContent;

    protected Text(Document _ownerDocument) {
        super(_ownerDocument);
    }

    @Override
    public void appendData(String _arg) {
        String textContent_ = getTextContent();
        StringBuilder str_ = new StringBuilder(textContent_.length() + _arg.length());
        str_.append(textContent_);
        str_.append(_arg);
        setTextContent(str_.toString());
    }

    @Override
    public void deleteData(int _offset, int _count) {
        String textContent_ = getTextContent();
        String first_ = textContent_.substring(0, _offset);
        String last_ = textContent_.substring(_offset + _count);
        StringBuilder str_ = new StringBuilder(textContent_.length());
        str_.append(first_);
        str_.append(last_);
        setTextContent(str_.toString());
    }

    @Override
    public String getData() {
        return getTextContent();
    }

    @Override
    public int getLength() {
        return textContent.length();
    }

    @Override
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

    @Override
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

    @Override
    public void setData(String _data) {
        setTextContent(_data);
    }

    @Override
    public String substringData(int _offset, int _count) {
        return getTextContent().substring(_offset, _offset + _count);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override
    public NodeList getChildNodes() {
        return new NodeList();
    }

    @Override
    public ElementList getChildElements() {
        return new ElementList();
    }

    @Override
    public void appendChild(MutableNode _newChild) {
    }

    @Override
    public void removeChild(MutableNode _oldChild) {
    }

    @Override
    public void replaceChild(MutableNode _newChild, MutableNode _oldChild) {
    }

    @Override
    public void insertBefore(MutableNode _newChild, MutableNode _refChild) {
    }

    @Override
    public void insertAfter(MutableNode _newChild, MutableNode _refChild) {
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

    @Override
    public void setTextContent(String _textContent) {
        textContent = _textContent;
    }

    protected void setEscapedTextContent(String _escapedTextContent) {
        textContent = DocumentBuilder.transformSpecialCharsLtGt(_escapedTextContent);
    }

}
