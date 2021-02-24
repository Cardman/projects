package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import aiki.util.PointParam;
import aiki.util.Points;
import code.util.EntryCust;
import code.util.ObjectMap;


public final class LevelCave extends LevelWithWildPokemon {

    private Points< Link> linksOtherLevels;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        validateLevelWithWildPokemon(_data, _level);
        for (PointParam<Link> e : linksOtherLevels.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            if (!e.getValue().isValid(_data)) {
                _data.setError(true);
            }
        }
    }

    public Points< Link> getLinksOtherLevels() {
        return linksOtherLevels;
    }

    public void setLinksOtherLevels(Points< Link> _linksOtherLevels) {
        linksOtherLevels = _linksOtherLevels;
    }

}
