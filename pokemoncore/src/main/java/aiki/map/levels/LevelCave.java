package aiki.map.levels;

import aiki.DataBase;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import code.util.EntryCust;
import code.util.ObjectMap;


public final class LevelCave extends LevelWithWildPokemon {

    private ObjectMap<Point, Link> linksOtherLevels;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        for (EntryCust<Point, Link> e : linksOtherLevels.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
                return;

            }
            if (!e.getValue().isValid(_data)) {
                _data.setError(true);
                return;

            }
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        for (EntryCust<Point, Link> e : linksOtherLevels.entryList()) {
            e.getValue().validateForEditing(_data);
        }
    }

    public ObjectMap<Point, Link> getLinksOtherLevels() {
        return linksOtherLevels;
    }

    public void setLinksOtherLevels(ObjectMap<Point, Link> _linksOtherLevels) {
        linksOtherLevels = _linksOtherLevels;
    }

    @Override
    public void clearElements(Point _point) {
        super.clearElements(_point);
        linksOtherLevels.removeKey(_point);
    }

    @Override
    public void translateByColumn(short _x, short _dir) {
        super.translateByColumn(_x, _dir);
        Level.translateLinkColumnData(linksOtherLevels, _x, _dir);
    }

    @Override
    public void translateByLine(short _y, short _dir) {
        super.translateByLine(_y, _dir);
        Level.translateLinkLineData(linksOtherLevels, _y, _dir);
    }
}
