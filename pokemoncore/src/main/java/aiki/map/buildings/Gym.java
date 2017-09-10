package aiki.map.buildings;
import aiki.DataBase;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.tree.BuildingArea;
import aiki.util.Point;
import code.util.annot.RwXml;

@RwXml
public class Gym extends Building {

    private LevelIndoorGym level;

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
    public LevelIndoorGym getLevel() {
        return level;
    }

    public void setLevel(LevelIndoorGym _level) {
        level = _level;
    }



}
