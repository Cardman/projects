package aiki.map;
import aiki.fight.pokemon.GenderName;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.PlaceLevel;
import aiki.util.Coords;
import aiki.util.CoordssCondition;
import aiki.util.CoordssCustListGenderName;
import aiki.util.PlaceLevelsCustListGenderName;
import code.util.EqList;
import code.util.*;


public class Step {

    private CoordssCustListGenderName caughtPokemon;

    private PlaceLevelsCustListGenderName caughtPokemonPlaceLevel;

    private Condition importantsTrainers;

    private Condition allImportantsTrainers;

    private Condition accessibleCoords;

    private boolean keep = true;

    private Step() {
    }

    public Step(CoordssCondition _accessibility, CustList<Place> _places, Tree _tree) {
        caughtPokemon = new CoordssCustListGenderName();
        importantsTrainers = new Condition();
        caughtPokemonPlaceLevel = new PlaceLevelsCustListGenderName();
        allImportantsTrainers = new Condition();
        accessibleCoords = new Condition();
        for (Coords c: _accessibility.getKeys()) {
            if (!_accessibility.getVal(c).isEmpty()) {
                continue;
            }
            accessibleCoords.add(c);
        }
        calculatePkTrainers(_places, _tree);
        allImportantsTrainers.addAllElts(importantsTrainers);
    }

    public Step nextStep(CoordssCondition _accessibility, CustList<Place> _places, Tree _tree) {
        Step step_ = new Step();
        step_.caughtPokemonPlaceLevel = new PlaceLevelsCustListGenderName();
        step_.caughtPokemon = new CoordssCustListGenderName();
        step_.importantsTrainers = new Condition();
        step_.allImportantsTrainers = new Condition(allImportantsTrainers);
        step_.accessibleCoords = new Condition();
        boolean keep_ = true;
        for (Coords c: _accessibility.getKeys()) {
            if (accessibleCoords.containsObj(c)) {
                continue;
            }
            if (!allImportantsTrainers.containsAllObj(_accessibility.getVal(c))) {
                continue;
            }
            step_.accessibleCoords.add(c);
        }
        if (step_.accessibleCoords.isEmpty()) {
            keep_ = false;
        }
        step_.keep = keep_;
        step_.calculatePkTrainers(_places, _tree);
        step_.allImportantsTrainers.addAllElts(step_.importantsTrainers);
        step_.accessibleCoords.addAllElts(accessibleCoords);
        return step_;
    }

    void calculatePkTrainers(CustList<Place> _places, Tree _tree) {
        for (Coords c: accessibleCoords) {
            PlaceArea plArea_ = _tree.getPlace(c.getNumberPlace());
            LevelArea levArea_ = plArea_.getLevel(c.getLevel().getLevelIndex());
            caughtPokemon.put(c, levArea_.getPokemon(c.getLevel().getPoint()));
        }
        for (Coords c: accessibleCoords) {
            Place pl_ = _places.get(c.getNumberPlace());
            Level level_ = pl_.getLevelByCoords(c);
            if (level_ instanceof LevelWithWildPokemon) {
                LevelWithWildPokemon lev_ = (LevelWithWildPokemon) level_;
                if (lev_.containsPokemon(c.getLevel().getPoint())) {
                    WildPk pk_ = lev_.getPokemon(c.getLevel().getPoint());
                    caughtPokemon.getVal(c).add(new GenderName(pk_.getGender(), pk_.getName()));
                }
            }
        }
        Condition empty_ = new Condition();
        for (Coords c: caughtPokemon.getKeys()) {
            if (!caughtPokemon.getVal(c).isEmpty()) {
                continue;
            }
            empty_.add(c);
        }
        for (Coords c: empty_) {
            caughtPokemon.removeKey(c);
        }
        for (Coords c: caughtPokemon.getKeys()) {
            PlaceLevel key_;
            key_ = new PlaceLevel(c.getNumberPlace(),c.getLevel().getLevelIndex());
            if (caughtPokemonPlaceLevel.contains(key_)) {
                caughtPokemonPlaceLevel.getVal(key_).addAllElts(caughtPokemon.getVal(c));
            } else {
                caughtPokemonPlaceLevel.put(key_, new CustList<GenderName>(caughtPokemon.getVal(c)));
            }
        }
        for (Coords c: accessibleCoords) {
            Coords c_ = new Coords(c);
            Place pl_ = _places.get(c.getNumberPlace());
            Level level_ = pl_.getLevelByCoords(c);
            if (level_ instanceof LevelIndoorGym) {
                LevelIndoorGym lev_ = (LevelIndoorGym) level_;
                c_.getLevel().getPoint().affect(lev_.getGymLeaderCoords());
                importantsTrainers.add(c_);
            }
            if (level_ instanceof LevelLeague) {
                League league_ = (League) pl_;
                c_.getLevel().setLevelIndex((byte) 0);
                c_.getLevel().getPoint().affect(league_.getBegin());
                importantsTrainers.add(c_);
            }
            if (level_ instanceof LevelWithWildPokemon) {
                LevelWithWildPokemon lev_ = (LevelWithWildPokemon) level_;
                if (lev_.containsDualFight(c.getLevel().getPoint())) {
                    importantsTrainers.add(c);
                }
            }
        }
        importantsTrainers.removeDuplicates();
    }

    public boolean keepSteps() {
        return keep;
    }

    public Condition getAccessibleCoords() {
        return accessibleCoords;
    }

    public CoordssCustListGenderName getCaughtPokemon() {
        return caughtPokemon;
    }

    public PlaceLevelsCustListGenderName getCaughtPokemonPlaceLevel() {
        return caughtPokemonPlaceLevel;
    }

    public Condition getImportantsTrainers() {
        return importantsTrainers;
    }
}
