package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.TrainerLeague;
import aiki.map.tree.LevelArea;
import aiki.util.Point;


public final class LevelLeague extends Level {

    private Point trainerCoords;

    private TrainerLeague trainer;

    private Point accessPoint;

    private Point nextLevelTarget;

    private String fileName;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        if (!_level.allAccessible()) {
            _data.setError(true);
        }
        super.validate(_data, _level);
        if (!isEmpty(accessPoint)) {
            _data.setError(true);
        }
        if (!_level.isValid(accessPoint, true)) {
            _data.setError(true);
        }
        if (!_level.isValid(trainerCoords, true)) {
            _data.setError(true);
        }
        trainer.validate(_data);
    }

    @Override
    public boolean isEmpty(Point _point) {
        return isEmptyForAdding(_point);
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        if (Point.eq(trainerCoords, _point)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = true;
        if (!super.hasValidImage(_data)) {
            val_ = false;
        }
        if (!trainer.hasValidImage(_data)) {
            val_ = false;
        }
        if (_data.getLink(fileName).length == 0) {
            val_ = false;
        }
        return val_;
    }


    public Point getTrainerCoords() {
        return trainerCoords;
    }

    public void setTrainerCoords(Point _trainer) {
        trainerCoords = _trainer;
    }

    public TrainerLeague getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerLeague _trainer) {
        trainer = _trainer;
    }

    public Point getAccessPoint() {
        return accessPoint;
    }

    public void setAccessPoint(Point _accessPoint) {
        accessPoint = _accessPoint;
    }

    public Point getNextLevelTarget() {
        return nextLevelTarget;
    }

    public void setNextLevelTarget(Point _nextLevelTarget) {
        nextLevelTarget = _nextLevelTarget;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }
}
