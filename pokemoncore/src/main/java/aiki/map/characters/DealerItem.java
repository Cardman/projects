package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.util.Numbers;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class DealerItem extends Person implements CharacterInRoadCave{

    private StringList items;

    private Numbers<Short> technicalMoves;

    @Override
    public void validate(DataBase _data) {
        if (!_data.getItems().containsAllAsKeys(items)) {
            throw new DataException();
        }
        if (!_data.getTm().containsAllAsKeys(technicalMoves)) {
            throw new DataException();
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
