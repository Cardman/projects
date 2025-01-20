package aiki.beans.facade.dto;

public final class ItemLine {
    private String displayName;
    private String name;
    private String descriptionClass;
    private long price;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String _displayName) {
        displayName = _displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getDescriptionClass() {
        return descriptionClass;
    }

    public void setDescriptionClass(String _descriptionClass) {
        descriptionClass = _descriptionClass;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long _price) {
        price = _price;
    }
}