package aiki.map.characters;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.util.*;
import code.util.StringList;


public final class DealerItem extends Person implements CharacterInRoadCave {

    private StringList items;

    private Ints technicalMoves;

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

    public Ints getTechnicalMoves() {
        return technicalMoves;
    }

    public void setTechnicalMoves(Ints _technicalMoves) {
        technicalMoves = _technicalMoves;
    }
}
