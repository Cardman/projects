package aiki.map.characters;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.util.*;
import code.util.StringList;


public final class DealerItem extends Person implements CharacterInRoadCave {

    private StringList items;

    private Shorts technicalMoves;

    @Override
    public void validate(DataBase _data) {
        DataInfoChecker.checkStringListContains(_data.getItems().getKeys(),items,_data);
        DataInfoChecker.checkShortsContains(_data.getTm().getKeys(),technicalMoves,_data);
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
