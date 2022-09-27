package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.tree.LevelArea;
import aiki.util.CommonParam;
import aiki.util.DataInfoChecker;
import aiki.util.Point;
import aiki.util.Points;


public final class LevelCave extends LevelWithWildPokemon {

    private Points< Link> linksOtherLevels;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        validateLevelWithWildPokemon(_data, _level);
        for (CommonParam<Point,Link> e : linksOtherLevels.entryList()) {
            DataInfoChecker.checkKey(_data,_level,e.getKey(),true);
            DataInfoChecker.checkLink(_data,e.getValue());
        }
    }

    public Points< Link> getLinksOtherLevels() {
        return linksOtherLevels;
    }

    public void setLinksOtherLevels(Points< Link> _linksOtherLevels) {
        linksOtherLevels = _linksOtherLevels;
    }

}
