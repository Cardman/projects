package aiki.beans.facade.dto;

public final class ItemTypeLine {
    private String item;
    private String type;

    public ItemTypeLine() {
    }

    public ItemTypeLine(String _item, String _type) {
        item = _item;
        type = _type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }
}