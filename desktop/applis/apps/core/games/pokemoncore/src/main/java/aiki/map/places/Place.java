package aiki.map.places;
import aiki.db.DataBase;
import aiki.map.characters.Person;
import aiki.map.levels.Level;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.util.Coords;
import code.util.CustList;
import code.util.*;

public abstract class Place {

    public boolean hasValidImage(DataBase _data) {
        for (Level l : getLevelsMap().values()) {
            if (!l.hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean validLinks(short _place, Tree _tree);

    public abstract void validate(DataBase _data,PlaceArea _placeArea);
    public abstract boolean isEmptyForAdding(Coords _coords);

    public abstract Level getLevelByCoords(Coords _coords);
    public abstract ByteMap<Level> getLevelsMap();
    public abstract CustList<Level> getLevelsList();

    public abstract void setName(String _name);
    public abstract String getName();
}
