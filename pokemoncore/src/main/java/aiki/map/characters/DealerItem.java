package aiki.map.characters;

import aiki.db.DataBase;
import code.util.Numbers;
import code.util.StringList;


public final class DealerItem extends Person implements CharacterInRoadCave {

    private StringList items;

    private Numbers<Short> technicalMoves;

    @Override
    public void validate(DataBase _data) {
        if (!_data.getItems().containsAllAsKeys(items)) {
            _data.setError(true);
            return;

        }
        if (!_data.getTm().containsAllAsKeys(technicalMoves)) {
            _data.setError(true);
            return;

        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        items.retainAllElements(_data.getItems().getKeys());
        technicalMoves.retainAllElements(_data.getTm().getKeys());
    }

    public StringList getItems() {
        return items;
    }

    public void setItems(StringList _items) {
        items = _items;
    }

    public Numbers<Short> getTechnicalMoves() {
        return technicalMoves;
    }

    public void setTechnicalMoves(Numbers<Short> _technicalMoves) {
        technicalMoves = _technicalMoves;
    }
}
