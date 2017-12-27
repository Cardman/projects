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
    public abstract void insertData(int _offset, String _arg);
    public abstract void replaceData(int _offset, int _count, String _arg);
    public abstract void setData(String _data);
    public abstract String substringData(int _offset, int _count);
}
