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
import code.util.EqList;
import code.util.NumberMap;
import code.util.ObjectMap;

public class Step {

    private ObjectMap<Coords,EqList<GenderName>> caughtPokemon;

    private ObjectMap<PlaceLevel,EqList<GenderName>> caughtPokemonPlaceLevel;

    private EqList<Coords> importantsTrainers;

    private EqList<Coords> allImportantsTrainers;

    private EqList<Coords> accessibleCoords;

    private boolean keep = true;

    private Step() {
    }

    public Step(ObjectMap<Coords, Condition> _accessibility, NumberMap<Short,Place> _places, Tree _tree) {
        caughtPokemon = new ObjectMap<Coords,EqList<GenderName>>();
        importantsTrainers = new EqList<Coords>();
        caughtPokemonPlaceLevel = new ObjectMap<PlaceLevel,EqList<GenderName>>();
        allImportantsTrainers = new EqList<Coords>();
        accessibleCoords = new EqList<Coords>();
        for (Coords c: _accessibility.getKeys()) {
            if (!_accessibility.getVal(c).isEmpty()) {
                continue;
            }
            accessibleCoords.add(c);
        }
        calculatePkTrainers(_places, _tree);
        allImportantsTrainers.addAllElts(importantsTrainers);
    }

    public Step nextStep(ObjectMap<Coords, Condition> _accessibility, NumberMap<Short,Place> _places, Tree _tree) {
        Step step_ = new Step();
        step_.caughtPokemonPlaceLevel = new ObjectMap<PlaceLevel,EqList<GenderName>>();
        step_.caughtPokemon = new ObjectMap<Coords,EqList<GenderName>>();
        step_.importantsTrainers = new EqList<Coords>();
        step_.allImportantsTrainers = new EqList<Coords>(allImportantsTrainers);
        step_.accessibleCoords = new EqList<Coords>();
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

    void calculatePkTrainers(NumberMap<Short,Place> _places, Tree _tree) {
        for (Coords c: accessibleCoords) {
            PlaceArea plArea_ = _tree.getPlace(c.getNumberPlace());
            LevelArea levArea_ = plArea_.getLevel(c.getLevel().getLevelIndex());
            caughtPokemon.put(c, levArea_.getPokemon(c.getLevel().getPoint()));
        }
        for (Coords c: accessibleCoords) {
            Place pl_ = _places.getVal(c.getNumberPlace());
            Level level_ = pl_.getLevelByCoords(c);
            if (level_ instanceof LevelWithWildPokemon) {
                LevelWithWildPokemon lev_ = (LevelWithWildPokemon) level_;
                if (lev_.containsPokemon(c.getLevel().getPoint())) {
                    WildPk pk_ = lev_.getPokemon(c.getLevel().getPoint());
                    caughtPokemon.getVal(c).add(new GenderName(pk_.getGender(), pk_.getName()));
                }
            }
        }
        EqList<Coords> empty_ = new EqList<Coords>();
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
                caughtPokemonPlaceLevel.put(key_, new EqList<GenderName>(caughtPokemon.getVal(c)));
            }
        }
        for (PlaceLevel key_: caughtPokemonPlaceLevel.getKeys()) {
            caughtPokemonPlaceLevel.getVal(key_).removeDuplicates();
        }
        for (Coords c: accessibleCoords) {
            Coords c_ = new Coords(c);
            Place pl_ = _places.getVal(c.getNumberPlace());
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

    public EqList<Coords> getAccessibleCoords() {
        return accessibleCoords;
    }

    public ObjectMap<Coords,EqList<GenderName>> getCaughtPokemon() {
        return caughtPokemon;
    }

    public ObjectMap<PlaceLevel,EqList<GenderName>> getCaughtPokemonPlaceLevel() {
        return caughtPokemonPlaceLevel;
    }

    public EqList<Coords> getImportantsTrainers() {
        return importantsTrainers;
    }
}
