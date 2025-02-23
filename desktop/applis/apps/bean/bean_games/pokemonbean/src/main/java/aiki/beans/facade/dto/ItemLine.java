package aiki.beans.facade.dto;

import aiki.beans.TranslatedKey;

public final class ItemLine {
    private String displayName;
    private TranslatedKey name;
    private String descriptionClass;
    private long price;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String _displayName) {
        displayName = _displayName;
    }

    public TranslatedKey getName() {
        return name;
    }

    public void setName(TranslatedKey _name) {
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