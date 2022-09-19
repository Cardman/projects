package aiki.map.places;
import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.Link;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnectCoords;
import aiki.map.util.PlaceInterConnects;
import aiki.util.CommonParam;
import aiki.util.Coords;
import aiki.util.Point;
import aiki.util.Points;
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

    protected void validateLinksWithCaves(DataBase _data, LevelArea _levelArea, Points<Link> _linksWithCaves) {
        for (Point p : _linksWithCaves.getKeys()) {
            if (!_levelArea.isValid(p, false)) {
                _data.setError(true);
            }
            Coords c_ = _linksWithCaves.getVal(p).getCoords();
            if (!_data.getMap().existCoords(c_)) {
                _data.setError(true);
                continue;
            }
            Place tar_ = _data.getMap().getPlace(c_.getNumberPlace());
            Level tarLevel_ = tar_.getLevelByCoords(c_);
            if (!tarLevel_.isEmptyForAdding(c_.getLevel().getPoint())) {
                _data.setError(true);
            }
        }
    }
    protected boolean checkLinks(Tree _tree, PlaceInterConnects _linksPointsWithCitiesAndOtherRoads, Points<Link> _linksWithCaves) {
        for (PlaceInterConnectCoords e : _linksPointsWithCitiesAndOtherRoads
                .entryList()) {
            if (!_tree.isValid(e.getCoords(), false)) {
                return false;
            }
        }
        for (CommonParam<Point,Link> e : _linksWithCaves.entryList()) {
            Link link_ = e.getValue();
            if (!_tree.isValid(link_.getCoords(), true)) {
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
