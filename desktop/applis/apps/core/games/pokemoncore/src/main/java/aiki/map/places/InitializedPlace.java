package aiki.map.places;
import aiki.map.levels.Level;
import aiki.map.levels.Link;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;
import code.util.ObjectMap;

public interface InitializedPlace {

    ObjectMap<Point,Link> getLinksWithCaves();

    Level getLevel();

    void addSavedLink(PlaceInterConnect _key, Coords _value);

    ObjectMap<PlaceInterConnect,Coords> getSavedlinks();

    void setSavedlinks(ObjectMap<PlaceInterConnect,Coords> _savedlinks);

    ObjectMap<PlaceInterConnect,Coords> getPointsWithCitiesAndOtherRoads();

    void setPointsWithCitiesAndOtherRoads(
            ObjectMap<PlaceInterConnect,Coords> _linksWithCitiesAndOtherRoads);
}
