package code.sml;

public abstract class CharacterData extends Node {
    protected CharacterData(Document _ownerDocument) {
        super(_ownerDocument);
    }
    public abstract void appendData(String _arg);
    public abstract void deleteData(int _offset, int _count);
    public abstract String getData();
    public abstract void setTextContent(String _textContent);
    public abstract int getLength();
    public abstract void insertData(int offset, String arg);
    public abstract void replaceData(int offset, int count, String arg);
    public abstract void setData(String data);
    public abstract String substringData(int offset, int count);
}
