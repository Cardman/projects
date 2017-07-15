package aiki.map.levels;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.tree.LevelArea;
import aiki.util.Point;

@RwXml
public class LevelCave extends LevelWithWildPokemon {

    private ObjectMap<Point,Link> linksOtherLevels;

    @Override
    public void validate(DataBase _data,LevelArea _level) {
        super.validate(_data, _level);
        for (EntryCust<Point,Link> e: linksOtherLevels.entryList()) {
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            if (!e.getValue().isValid(_data)) {
                throw new DataException();
            }
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        for (EntryCust<Point,Link> e: linksOtherLevels.entryList()) {
            e.getValue().validateForEditing(_data);
        }
    }

    public ObjectMap<Point,Link> getLinksOtherLevels() {
        return linksOtherLevels;
    }

    public void setLinksOtherLevels(ObjectMap<Point,Link> _linksOtherLevels) {
        linksOtherLevels = _linksOtherLevels;
    }
    @Override
    public void clearElements(Point _point) {
        super.clearElements(_point);
        linksOtherLevels.removeKey( _point);
    }
    @Override
    public void translateByColumn(short _x, short _dir) {
        super.translateByColumn(_x, _dir);
        Level.<Link>translateColumnData(linksOtherLevels, _x, _dir);
    }
    @Override
    public void translateByLine(short _y, short _dir) {
        super.translateByLine(_y, _dir);
        Level.<Link>translateLineData(linksOtherLevels, _y, _dir);
    }
}
