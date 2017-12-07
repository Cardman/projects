package code.xml.components;

import code.util.StringList;

public final class Text extends CharacterData {

    private String textContent;

    //set for example by setting from the parent element
    private String escapedTextContent;

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
    public String getNodeName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNodeValue() {
        return getTextContent();
    }

    @Override
    public void appendChild(ChangeableChild _newChild) {
    }

    @Override
    public void removeChild(ChangeableChild _oldChild) {
    }

    @Override
    public void replaceChild(ChangeableChild _newChild, ChangeableChild _oldChild) {
    }

    @Override
    public void insertBefore(ChangeableChild _newChild, ChangeableChild _refChild) {
    }

    @Override
    public void insertAfter(ChangeableChild _newChild, ChangeableChild _refChild) {
    }

    @Override
    public boolean hasChildNodes() {
        return false;
    }

    @Override
    public String getNamespace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPrefix() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPrefix(String _prefix) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public long compareDocumentPosition(Node _other) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getTextContent() {
        return textContent;
    }

    @Override
    public void setTextContent(String _textContent) {
        textContent = _textContent;
        escapedTextContent = DocumentBuilder.escape(_textContent, false);
    }

    protected String getEscapedTextContent() {
        return escapedTextContent;
    }

    protected void setEscapedTextContent(String _escapedTextContent) {
        escapedTextContent = _escapedTextContent;
        textContent = DocumentBuilder.transformSpecialChars(_escapedTextContent);
    }

    @Override
    public String lookupPrefix(String _namespace) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDefaultNamespace(String _namespace) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String lookupNamespace(String _prefix) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEqualNode(Node _arg) {
        if (!(_arg instanceof Text)) {
            return false;
        }
        Text attr_ = (Text) _arg;
        if (!StringList.quickEq(attr_.getTextContent(), getTextContent())) {
            return false;
        }
        return true;
    }

}
