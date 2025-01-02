package aiki.map.places;
import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.Link;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnectCoords;
import aiki.map.util.PlaceInterConnects;
import aiki.util.*;
import code.util.*;

public abstract class Place {

    public boolean hasValidImage(DataBase _data) {
        for (Level l : getLevelsList()) {
            if (!l.hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    protected void validateLinksWithCaves(DataBase _data, LevelArea _levelArea, Points<Link> _linksWithCaves) {
        for (Point p : _linksWithCaves.getKeys()) {
            DataInfoChecker.checkKey(_data,_levelArea,p,true);
            Coords c_ = _linksWithCaves.getVal(p).getCoords();
            if (!_data.getMap().existCoords(c_)) {
                _data.setError(true);
                continue;
            }
            Level tarLevel_ = _data.getMap().getLevelByCoords(c_);
            DataInfoChecker.checkEmptyForAdding(_data,tarLevel_,c_.getLevel().getPoint());
        }
    }
    protected boolean checkLinks(Tree _tree, PlaceInterConnects _linksPointsWithCitiesAndOtherRoads, Points<Link> _linksWithCaves) {
        for (PlaceInterConnectCoords e : _linksPointsWithCitiesAndOtherRoads
                .entryList()) {
            if (!_tree.isValid(e.getCoords(), false)) {
                return false;
            }
        }
        for (EntryCust<Point,Link> e : _linksWithCaves.entryList()) {
            Link link_ = e.getValue();
            if (!_tree.isValid(link_.getCoords(), true)) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean validLinks(int _place, Tree _tree);

    public abstract void validate(DataBase _data,PlaceArea _placeArea);
    public abstract boolean isEmptyForAdding(Coords _coords);

    public abstract Level getLevelByCoords(Coords _coords);
    public abstract IntMap<Level> getLevelsMap();
    public abstract CustList<Level> getLevelsList();

    public abstract void setName(String _name);
    public abstract String getName();
}
