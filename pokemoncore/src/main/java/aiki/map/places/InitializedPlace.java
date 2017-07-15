package aiki.map.places;
import code.util.ObjectMap;
import aiki.map.characters.Person;
import aiki.map.levels.Level;
import aiki.map.levels.Link;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.Point;

public interface InitializedPlace {

    void addPerson(Coords _coords, Person _person);
    boolean validLinks(Tree _tree);

    ObjectMap<Point,Link> getLinksWithCaves();

    Level getLevel();

    void addSavedLink(PlaceInterConnect _key, Coords _value);
    void deleteSavedLink(PlaceInterConnect _key);

    ObjectMap<PlaceInterConnect,Coords> getSavedlinks();

    void setSavedlinks(ObjectMap<PlaceInterConnect,Coords> _savedlinks);

    ObjectMap<PlaceInterConnect,Coords> getPointsWithCitiesAndOtherRoads();

    void setPointsWithCitiesAndOtherRoads(
            ObjectMap<PlaceInterConnect,Coords> _linksWithCitiesAndOtherRoads);
}
