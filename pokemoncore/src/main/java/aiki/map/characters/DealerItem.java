package aiki.map.characters;

import aiki.db.DataBase;
import code.util.*;
import code.util.StringList;


public final class DealerItem extends Person implements CharacterInRoadCave {

    private StringList items;

    private Shorts technicalMoves;

    @Override
    public void validate(DataBase _data) {
        if (!_data.getItems().containsAllAsKeys(items)) {
            _data.setError(true);
        }
        if (!_data.getTm().containsAllAsKeys(technicalMoves)) {
            _data.setError(true);
        }
    }

    public StringList getItems() {
        return items;
    }

    public void setItems(StringList _items) {
        items = _items;
    }

    public Shorts getTechnicalMoves() {
        return technicalMoves;
    }

    public void setTechnicalMoves(Shorts _technicalMoves) {
        technicalMoves = _technicalMoves;
    }
}
