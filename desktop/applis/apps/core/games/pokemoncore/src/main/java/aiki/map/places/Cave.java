package aiki.map.places;

import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.util.*;
import code.util.ByteMap;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;


public final class Cave extends Campaign {

    private String name;

    private CustList<LevelCave> levels;

    private LevelPoints linksWithOtherPlaces;

    @Override
    public void validate(DataBase _data, PlaceArea _placeArea) {
        if (linksWithOtherPlaces.isEmpty()) {
            _data.setError(true);
        }
        if (levels.isEmpty()) {
            _data.setError(true);
        }
        int nbLevels_ = levels.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbLevels_; i++) {
            LevelCave level_ = levels.get(i);
            level_.validate(_data, _placeArea.getLevel(i));
            for (CommonParam<Point,Link> e : level_.getLinksOtherLevels()
                    .entryList()) {
                validateLinkLevels(_data, _placeArea, level_, e);
            }
        }
        for (LevelPointLink e : linksWithOtherPlaces.entryList()) {
            validateLinkOtherPlaces(_data, _placeArea, e);
        }
    }

    private void validateLinkOtherPlaces(DataBase _data, PlaceArea _placeArea, LevelPointLink _e) {
        Link link_ = _e.getLink();
        DataInfoChecker.checkLink(_data,link_);
        LevelPoint k_ = _e.getLevelPoint();
        if (!_placeArea.isValidLevel(k_
                .getLevelIndex())) {
            _data.setError(true);
        } else {
            DataInfoChecker.checkKey(_data,_placeArea.getLevel(k_.getLevelIndex()),k_.getPoint(), false);
            Coords c_ = link_.getCoords();
            if (!_data.getMap().existCoords(c_)) {
                _data.setError(true);
            } else {
                Place tar_ = _data.getMap().getPlace(c_.getNumberPlace());
                Level tarLevel_ = tar_.getLevelByCoords(c_);
                DataInfoChecker.checkEmptyForAdding(_data,tarLevel_,c_.getLevel().getPoint());
            }
        }
    }

    private void validateLinkLevels(DataBase _data, PlaceArea _placeArea, LevelCave _level, CommonParam<Point, Link> _e) {
        Link link_ = _e.getValue();
        DataInfoChecker.checkLink(_data,link_);
        DataInfoChecker.checkEmptyForAdding(_data,_level,_e.getKey());
        LevelPoint target_ = link_.getCoords().getLevel();
        if (!_placeArea.isValidLevel(target_
                .getLevelIndex())) {
            _data.setError(true);
        } else {
            LevelArea levelArea_ = _placeArea.getLevel(target_
                    .getLevelIndex());
            DataInfoChecker.checkKey(_data,levelArea_,target_.getPoint(), true);
            LevelCave levelTarget_ = getLevelCave(target_);
            DataInfoChecker.checkEmptyForAdding(_data,levelTarget_,target_.getPoint());
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
        LevelPointEqList ids_ = new LevelPointEqList();
        boolean valid_ = true;
        for (byte i = IndexConstants.FIRST_INDEX; i < nbLevels_; i++) {
            LevelCave level_ = levels.get(i);
            for (CommonParam<Point,Link> e : level_.getLinksOtherLevels()
                    .entryList()) {
                valid_ = checkIdOtherLevel(_place, ids_, valid_, i, e);
            }
        }
        for (LevelPointLink e : linksWithOtherPlaces.entryList()) {
            Link link_ = e.getLink();
            if (!_tree.isValid(link_.getCoords(), true)) {
                valid_ = false;
            }
            ids_.add(e.getLevelPoint());
        }
        if (ids_.hasDuplicates()) {
            valid_ = false;
        }
        return valid_;
    }

    private boolean checkIdOtherLevel(short _place, LevelPointEqList _ids, boolean _valid, byte _i, CommonParam<Point, Link> _e) {
        boolean valid_ = _valid;
        Link link_ = _e.getValue();
        Coords coords_ = link_.getCoords();
        if (!NumberUtil.eq(coords_.getNumberPlace(), _place)) {
            valid_ = false;
        }
        LevelPoint lPoint_ = coords_.getLevel();
        byte levelIndex_ = lPoint_.getLevelIndex();
        if (!levels.isValidIndex(levelIndex_)) {
            addKey(_i, _e, _ids);
            return false;
        }
        LevelCave otherLevel_ = levels.get(levelIndex_);
        if (!otherLevel_.getLinksOtherLevels().contains(
                lPoint_.getPoint())) {
            addKey(_i, _e, _ids);
            return false;
        }
        Link otherLink_ = otherLevel_.getLinksOtherLevels().getVal(
                lPoint_.getPoint());
        Coords otherCoords_ = otherLink_.getCoords();
        LevelPoint otherLevelPoint_ = otherCoords_.getLevel();
        LevelPoint current_ = new LevelPoint();
        current_.setLevelIndex(_i);
        current_.setPoint(_e.getKey());
        if (!LevelPoint.eq(otherLevelPoint_,current_)) {
            valid_ = false;
        }
        addKey(_i, _e, _ids);
        return valid_;
    }

    private void addKey(byte _i, CommonParam<Point, Link> _e, LevelPointEqList _ids) {
        LevelPoint id_ = new LevelPoint();
        id_.setLevelIndex(_i);
        id_.setPoint(_e.getKey());
        _ids.add(id_);
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
        return levels.get(_level.getLevelIndex());
    }

    @Override
    public ByteMap< Level> getLevelsMap() {
        ByteMap< Level> levels_ = new ByteMap< Level>();
        byte i_ = 0;
        for (LevelCave l : levels) {
            levels_.addEntry(i_, l);
            i_++;
        }
        return levels_;
    }

    public CustList<LevelCave> getLevels() {
        return levels;
    }

    @Override
    public CustList<Level> getLevelsList() {
        CustList<Level> levels_ = new CustList<Level>();
        for (LevelCave l : levels) {
            levels_.add(l);
        }
        return levels_;
    }

    @Override
    public void initializeWildPokemon() {
        for (LevelCave l : levels) {
            l.initializeWildPokemon();
        }
    }

    public void setLevels(CustList<LevelCave> _levels) {
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

    public LevelPoints getLinksWithOtherPlaces() {
        return linksWithOtherPlaces;
    }

    public void setLinksWithOtherPlaces(
            LevelPoints _linksWithOtherPlaces) {
        linksWithOtherPlaces = _linksWithOtherPlaces;
    }
}
