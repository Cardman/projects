package aiki.map.buildings;
import aiki.db.DataBase;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.tree.BuildingArea;
import aiki.util.Point;


public final class PokemonCenter extends Building {

    private LevelIndoorPokemonCenter level;

    @Override
    public void validate(DataBase _data,BuildingArea _buildingArea) {
        super.validate(_data, _buildingArea);
        level.validate(_data, _buildingArea.getLevel());
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        level.validateForEditing(_data);
    }

    @Override
    public void clearElements(Point _point) {
        level.clearElements(_point);
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
