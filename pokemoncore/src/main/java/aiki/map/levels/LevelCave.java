package aiki.map.levels;

import aiki.db.DataBase;
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

    public ObjectMap<Point, Link> getLinksOtherLevels() {
        return linksOtherLevels;
    }

    public void setLinksOtherLevels(ObjectMap<Point, Link> _linksOtherLevels) {
        linksOtherLevels = _linksOtherLevels;
    }

}
