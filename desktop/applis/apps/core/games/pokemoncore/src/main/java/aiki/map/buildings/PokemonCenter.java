package aiki.map.buildings;
import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.tree.BuildingArea;


public final class PokemonCenter extends Building {

    private LevelIndoorPokemonCenter level;

    @Override
    public void validate(DataBase _data,BuildingArea _buildingArea) {
        super.validate(_data, _buildingArea);
//        if (_buildingArea == null) {
//            _data.setError(true);
//            return;
//        }
        level.validate(_data, _buildingArea.getLevel());
        validatePoints(_data, _buildingArea, level.validateLinks());
    }

    @Override
    public Level getLevel() {
        return getIndoor();
    }
    public LevelIndoorPokemonCenter getIndoor() {
        return level;
    }

    public void setLevel(LevelIndoorPokemonCenter _level) {
        level = _level;
    }
}
