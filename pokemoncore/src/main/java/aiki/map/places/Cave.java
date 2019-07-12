package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.*;
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
import code.util.*;
import code.util.ObjectMap;


public final class Cave extends Campaign {

    private String name;

    private ByteMap< LevelCave> levels;

    private ObjectMap<LevelPoint, Link> linksWithOtherPlaces;

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        if (linksWithOtherPlaces.isEmpty()) {
            _data.setError(true);
        }
        if (levels.isEmpty()) {
            _data.setError(true);
        }
        int nbLevels_ = levels.size();
        for (byte i = CustList.FIRST_INDEX; i < nbLevels_; i++) {
            LevelCave level_ = levels.getVal(i);
            level_.validate(_data, _placeArea.getLevel(i));
            for (EntryCust<Point, Link> e : level_.getLinksOtherLevels()
                    .entryList()) {
                Link link_ = e.getValue();
                if (!link_.isValid(_data)) {
                    _data.setError(true);
                }
                if (!level_.isEmptyForAdding(e.getKey())) {
                    _data.setError(true);
                }
                LevelPoint target_ = link_.getCoords().getLevel();
                if (!_placeArea.isValidLevel(target_
                        .getLevelIndex())) {
                    _data.setError(true);
                    continue;
                }
                LevelArea levelArea_ = _placeArea.getLevel(target_
                        .getLevelIndex());
                if (!levelArea_.isValid(target_.getPoint(), true)) {
                    _data.setError(true);
                }
                LevelCave levelTarget_ = getLevelCave(target_);
                if (!levelTarget_.isEmptyForAdding(target_.getPoint())) {
                    _data.setError(true);
                }
            }
        }
        for (EntryCust<LevelPoint, Link> e : linksWithOtherPlaces.entryList()) {
            Link link_ = e.getValue();
            if (!link_.isValid(_data)) {
                _data.setError(true);
            }
            LevelPoint k_ = e.getKey();
            if (!_placeArea.isValidLevel(k_
                    .getLevelIndex())) {
                _data.setError(true);
                continue;
            }
            if (!_placeArea.getLevel(k_.getLevelIndex()).isValid(k_.getPoint(),
                    false)) {
                _data.setError(true);
            }
            Coords c_ = link_.getCoords();
            if (!_data.getMap().existCoords(c_)) {
                _data.setError(true);
                continue;
            }
            Place tar_ = _data.getMap().getPlaces().getVal(c_.getNumberPlace());
            Level tarLevel_ = tar_.getLevelByCoords(c_);
            if (!tarLevel_.isEmptyForAdding(c_.getLevel().getPoint())) {
                _data.setError(true);
            }
        }
    }

    @Override
    public boolean isEmptyForAdding(Coords _coords) {
        Level level_ = getLevelByCoords(_coords);
        return level_.isEmptyForAdding(_coords.getLevel().getPoint());
    }

    @Override
    public boolean validLinks(short _place, Tree _tree) {
        int nbLevels_ = levels.size();
        EqList<LevelPoint> ids_ = new EqList<LevelPoint>();
        boolean valid_ = true;
        for (byte i = CustList.FIRST_INDEX; i < nbLevels_; i++) {
            LevelCave level_ = levels.getVal(i);
            for (EntryCust<Point, Link> e : level_.getLinksOtherLevels()
                    .entryList()) {
                Link link_ = e.getValue();
                Coords coords_ = link_.getCoords();
                if (!Numbers.eq(coords_.getNumberPlace(), _place)) {
                    valid_ = false;
                }
                LevelPoint lPoint_ = coords_.getLevel();
                LevelCave otherLevel_ = getLevelCave(lPoint_);
                if (otherLevel_ == null) {
                    valid_ = false;
                    LevelPoint id_ = new LevelPoint();
                    id_.setLevelIndex(i);
                    id_.setPoint(e.getKey());
                    ids_.add(id_);
                    continue;
                }
                if (!otherLevel_.getLinksOtherLevels().contains(
                        lPoint_.getPoint())) {
                    valid_ = false;
                    LevelPoint id_ = new LevelPoint();
                    id_.setLevelIndex(i);
                    id_.setPoint(e.getKey());
                    ids_.add(id_);
                    continue;
                }
                Link otherLink_ = otherLevel_.getLinksOtherLevels().getVal(
                        lPoint_.getPoint());
                Coords otherCoords_ = otherLink_.getCoords();
                LevelPoint otherLevelPoint_ = otherCoords_.getLevel();
                LevelPoint current_ = new LevelPoint();
                current_.setLevelIndex(i);
                current_.setPoint(e.getKey());
                if (!LevelPoint.eq(otherLevelPoint_,current_)) {
                    valid_ = false;
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
                valid_ = false;
            }
            ids_.add(e.getKey());
        }
        int len_ = ids_.size();
        ids_.removeDuplicates();
        if (len_ != ids_.size()) {
            valid_ = false;
        }
        return valid_;
    }

    @Override
    public Level getLevelByCoords(Coords _coords) {
        return getLevelCompaignByCoords(_coords);
    }

    @Override
    public LevelWithWildPokemon getLevelCompaignByCoords(Coords _coords) {
        return getLevelCave(_coords.getLevel());
    }

    public LevelCave getLevelCave(LevelPoint _level) {
        return levels.getVal(_level.getLevelIndex());
    }

    @Override
    public ByteMap< Level> getLevelsMap() {
        ByteMap< Level> levels_ = new ByteMap< Level>();
        for (EntryCust<Byte, LevelCave> e : levels.entryList()) {
            levels_.put(e.getKey(), e.getValue());
        }
        return levels_;
    }

    public ByteMap< LevelCave> getLevels() {
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
    public void addObject(Coords _coords, String _object) {
        LevelCave level_ = getLevelCave(_coords.getLevel());
        level_.getItems().put(_coords.getLevel().getPoint(), _object);
    }

    @Override
    public void addPerson(Coords _coords, Person _person) {
        LevelCave level_ = getLevelCave(_coords.getLevel());
        level_.getCharacters().put(_coords.getLevel().getPoint(),
                (CharacterInRoadCave) _person);
    }

    @Override
    public void addDualFight(Coords _coords, DualFight _dualFight) {
        LevelCave level_ = getLevelCave(_coords.getLevel());
        level_.getDualFights().put(_coords.getLevel().getPoint(), _dualFight);
    }

    @Override
    public void addHm(Coords _coords, short _hm) {
        LevelCave level_ = getLevelCave(_coords.getLevel());
        level_.getHm().put(_coords.getLevel().getPoint(), _hm);
    }

    @Override
    public void addTm(Coords _coords, short _tm) {
        LevelCave level_ = getLevelCave(_coords.getLevel());
        level_.getTm().put(_coords.getLevel().getPoint(), _tm);
    }

    @Override
    public void initializeWildPokemon() {
        for (LevelCave l : levels.values()) {
            l.initializeWildPokemon();
        }
    }

    public void setLevels(ByteMap< LevelCave> _levels) {
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
