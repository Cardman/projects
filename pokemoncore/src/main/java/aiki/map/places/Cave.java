package aiki.map.places;

import aiki.DataBase;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.Level;
import aiki.map.levels.LevelCave;
import aiki.map.levels.Link;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public final class Cave extends Campaign {

    private String name;

    private NumberMap<Byte, LevelCave> levels;

    private ObjectMap<LevelPoint, Link> linksWithOtherPlaces;

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        if (name == null) {
            _data.setError(true);
            return;

        }
        if (levels.isEmpty()) {
            _data.setError(true);
            return;

        }
        if (linksWithOtherPlaces.isEmpty()) {
            _data.setError(true);
            return;

        }
        levels.getKeys().getMinimum().byteValue();
        int nbLevels_ = levels.size();
        for (byte i = CustList.FIRST_INDEX; i < nbLevels_; i++) {
            LevelCave level_ = levels.getVal(i);
            level_.validate(_data, _placeArea.getLevel(i));
            for (EntryCust<Point, Link> e : level_.getLinksOtherLevels()
                    .entryList()) {
                Link link_ = e.getValue();
                if (!link_.isValid(_data)) {
                    _data.setError(true);
                    return;

                }
                if (!level_.isEmptyForAdding(e.getKey())) {
                    _data.setError(true);
                    return;

                }
                LevelPoint target_ = link_.getCoords().getLevel();
                LevelArea levelArea_ = _placeArea.getLevel(target_
                        .getLevelIndex());
                if (!levelArea_.isValid(target_.getPoint(), true)) {
                    _data.setError(true);
                    return;

                }
                LevelCave levelTarget_ = levels.getVal(target_.getLevelIndex());
                if (!levelTarget_.isEmptyForAdding(target_.getPoint())) {
                    _data.setError(true);
                    return;

                }
            }
        }
        for (EntryCust<LevelPoint, Link> e : linksWithOtherPlaces.entryList()) {
            Link link_ = e.getValue();
            if (!link_.isValid(_data)) {
                _data.setError(true);
                return;

            }
            LevelPoint k_ = e.getKey();
            // if
            // (!_placeArea.getLevel(k_.getLevelIndex()).isValid(k_.getPoint(),
            // true)) {
            // _data.setError(true);

            // }
            if (!_placeArea.getLevel(k_.getLevelIndex()).isValid(k_.getPoint(),
                    false)) {
                _data.setError(true);
                return;

            }
            Coords c_ = link_.getCoords();
            Place tar_ = _data.getMap().getPlaces().getVal(c_.getNumberPlace());
            Level tarLevel_ = tar_.getLevelByCoords(c_);
            if (!tarLevel_.isEmptyForAdding(c_.getLevel().getPoint())) {
                _data.setError(true);
                return;

            }
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        if (levels.isEmpty()) {
            _data.setError(true);
            return;

        }
        for (LevelCave l : levels.values()) {
            l.validateForEditing(_data);
            for (EntryCust<Point, Link> e : l.getLinksOtherLevels().entryList()) {
                Link link_ = e.getValue();
                link_.validateForEditing(_data);
            }
        }
        for (EntryCust<LevelPoint, Link> e : linksWithOtherPlaces.entryList()) {
            e.getValue().validateForEditing(_data);
        }
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    public boolean validLinks(short _place, Tree _tree) {
        int nbLevels_ = levels.size();
        EqList<LevelPoint> ids_ = new EqList<LevelPoint>();
        for (byte i = CustList.FIRST_INDEX; i < nbLevels_; i++) {
            LevelCave level_ = levels.getVal(i);
            for (EntryCust<Point, Link> e : level_.getLinksOtherLevels()
                    .entryList()) {
                Link link_ = e.getValue();
                Coords coords_ = link_.getCoords();
                if (!Numbers.eq(coords_.getNumberPlace(), _place)) {
                    return false;
                }
                LevelPoint lPoint_ = coords_.getLevel();
                LevelCave otherLevel_ = levels.getVal(lPoint_.getLevelIndex());
                if (!otherLevel_.getLinksOtherLevels().contains(
                        lPoint_.getPoint())) {
                    return false;
                }
                Link otherLink_ = otherLevel_.getLinksOtherLevels().getVal(
                        lPoint_.getPoint());
                Coords otherCoords_ = otherLink_.getCoords();
                LevelPoint otherLevelPoint_ = otherCoords_.getLevel();
                if (!Numbers.eq(otherLevelPoint_.getLevelIndex(), i)) {
                    return false;
                }
                if (!Point.eq(otherLevelPoint_.getPoint(), e.getKey())) {
                    return false;
                }
                LevelPoint id_ = new LevelPoint();
                id_.setLevelIndex(i);
                id_.setPoint(e.getKey());
                ids_.add(id_);
            }
        }
        for (EntryCust<LevelPoint, Link> e : linksWithOtherPlaces.entryList()) {
            Link link_ = e.getValue();
            if (!_tree.isValid(link_.getCoords(), true)) {
                return false;
            }
            ids_.add(e.getKey());
        }
        int len_ = ids_.size();
        ids_.removeDuplicates();
        if (len_ != ids_.size()) {
            return false;
        }
        return true;
    }

    public void addNewLevel() {
        LevelCave level_ = new LevelCave();
        level_.setBlocks(new ObjectMap<Point, Block>());
        level_.setCharacters(new ObjectMap<Point, CharacterInRoadCave>());
        level_.setDualFights(new ObjectMap<Point, DualFight>());
        level_.setHm(new ObjectMap<Point, Short>());
        level_.setTm(new ObjectMap<Point, Short>());
        level_.setItems(new ObjectMap<Point, String>());
        level_.setLegendaryPks(new ObjectMap<Point, WildPk>());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        level_.setLinksOtherLevels(new ObjectMap<Point, Link>());
        levels.put(indexOfAddedLevel(), level_);
    }

    byte indexOfAddedLevel() {
        Numbers<Byte> keys_ = new Numbers<Byte>(levels.getKeys());
        if (keys_.isEmpty()) {
            return (short) CustList.FIRST_INDEX;
        }
        byte max_ = keys_.getMaximum();
        for (byte s = CustList.FIRST_INDEX; s < max_; s++) {
            if (keys_.containsObj(s)) {
                continue;
            }
            return s;
        }
        return (byte) (max_ + 1);
    }

    public void clearElements(LevelPoint _point) {
        LevelCave l_ = levels.getVal(_point.getLevelIndex());
        l_.clearElements(_point.getPoint());
    }

    public void clearLink(LevelPoint _point) {
        for (LevelCave l : levels.values()) {
            for (Point p : l.getLinksOtherLevels().getKeys()) {
                Link link_ = l.getLinksOtherLevels().getVal(p);
                if (LevelPoint.eq(link_.getCoords().getLevel(), _point)) {
                    l.getLinksOtherLevels().removeKey(p);
                }
            }
        }
        LevelCave l_ = levels.getVal(_point.getLevelIndex());
        l_.getLinksOtherLevels().removeKey(_point.getPoint());
    }

    @Override
    public LevelCave getLevelByCoords(Coords _coords) {
        return levels.getVal(_coords.getLevel().getLevelIndex());
    }

    @Override
    public NumberMap<Byte, Level> getLevelsMap() {
        NumberMap<Byte, Level> levels_ = new NumberMap<Byte, Level>();
        for (EntryCust<Byte, LevelCave> e : levels.entryList()) {
            levels_.put(e.getKey(), e.getValue());
        }
        return levels_;
    }

    public NumberMap<Byte, LevelCave> getLevels() {
        return levels;
    }

    @Override
    public CustList<Level> getLevelsList() {
        CustList<Level> levels_ = new CustList<Level>();
        for (LevelCave l : levels.values()) {
            levels_.add(l);
        }
        return levels_;
    }

    @Override
    public boolean containsPokemon(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.containsPokemon(_coords.getLevel().getPoint());
    }

    @Override
    public void addPokemon(Coords _coords, WildPk _pk) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.getLegendaryPks().put(_coords.getLevel().getPoint(), _pk);
    }

    @Override
    public WildPk getPokemon(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.getPokemon(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsObject(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.containsObject(_coords.getLevel().getPoint());
    }

    @Override
    public void addObject(Coords _coords, String _object) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.getItems().put(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public String getObject(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.getObject(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsPerson(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.containsPerson(_coords.getLevel().getPoint());
    }

    @Override
    public void addPerson(Coords _coords, Person _person) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.getCharacters().put(_coords.getLevel().getPoint(),
                (CharacterInRoadCave) _person);
    }

    @Override
    public Person getPerson(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.getPerson(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsDualFight(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.containsDualFight(_coords.getLevel().getPoint());
    }

    @Override
    public void addDualFight(Coords _coords, DualFight _dualFight) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.getDualFights().put(_coords.getLevel().getPoint(), _dualFight);
    }

    @Override
    public DualFight getDualFight(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.getDualFight(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsHm(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.containsHm(_coords.getLevel().getPoint());
    }

    @Override
    public void addHm(Coords _coords, short _hm) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.getHm().put(_coords.getLevel().getPoint(), _hm);
    }

    @Override
    public short getHm(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.getHm(_coords.getLevel().getPoint());
    }

    @Override
    public boolean containsTm(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.containsTm(_coords.getLevel().getPoint());
    }

    @Override
    public void addTm(Coords _coords, short _tm) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.getTm().put(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public short getTm(Coords _coords) {
        LevelCave level_ = getLevelByCoords(_coords);
        return level_.getTm(_coords.getLevel().getPoint());
    }

    @Override
    public void setItem(Coords _coords, String _object) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.setItem(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public void setTm(Coords _coords, short _tm) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.setTm(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public void setHm(Coords _coords, short _object) {
        LevelCave level_ = getLevelByCoords(_coords);
        level_.setHm(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public void initializeWildPokemon() {
        for (LevelCave l : levels.values()) {
            l.initializeWildPokemon();
        }
    }

    public void setLevels(NumberMap<Byte, LevelCave> _levels) {
        levels = _levels;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    public ObjectMap<LevelPoint, Link> getLinksWithOtherPlaces() {
        return linksWithOtherPlaces;
    }

    public void setLinksWithOtherPlaces(
            ObjectMap<LevelPoint, Link> _linksWithOtherPlaces) {
        linksWithOtherPlaces = _linksWithOtherPlaces;
    }
}
