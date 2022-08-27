package aiki.map.places;
import aiki.map.levels.Level;
import aiki.map.levels.Link;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.PlaceInterConnects;
import aiki.util.Coords;
import aiki.util.Point;
import aiki.util.Points;


public interface InitializedPlace {

    Points<Link> getLinksWithCaves();

    Level getLevel();

    void addSavedLink(PlaceInterConnect _key, Coords _value);

    PlaceInterConnects getSavedlinks();

    void setSavedlinks(PlaceInterConnects _savedlinks);

    PlaceInterConnects getPointsWithCitiesAndOtherRoads();

    void setPointsWithCitiesAndOtherRoads(
            PlaceInterConnects _linksWithCitiesAndOtherRoads);
}
