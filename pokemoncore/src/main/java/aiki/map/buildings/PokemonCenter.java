package aiki.map.buildings;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.tree.BuildingArea;
import aiki.util.Point;

@RwXml
public class PokemonCenter extends Building {

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
    public LevelIndoorPokemonCenter getLevel() {
        return level;
    }

    public void setLevel(LevelIndoorPokemonCenter _level) {
        level = _level;
    }
}
