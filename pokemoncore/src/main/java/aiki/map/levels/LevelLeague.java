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
            return;

        }
        super.validate(_data, _level);
        if (!isEmpty(accessPoint)) {
            _data.setError(true);
            return;

        }
        if (!_level.isValid(accessPoint, true)) {
            _data.setError(true);
            return;

        }
        if (!_level.isValid(trainerCoords, true)) {
            _data.setError(true);
            return;

        }
        trainer.validate(_data);
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
        if (!super.hasValidImage(_data)) {
            return false;
        }
        if (!trainer.hasValidImage(_data)) {
            return false;
        }
        if (_data.getLink(fileName).length == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty(Point _point) {
        if (Point.eq(trainerCoords, _point)) {
            return false;
        }
        return true;
    }

    @Override
    public void clearElements(Point _point) {
        if (Point.eq(accessPoint, _point)) {
            accessPoint = new Point();
        }
    }

    @Override
    public void translateByLine(short _y, short _dir) {
        super.translateByLine(_y, _dir);
        if (trainerCoords.gety() > _y) {
            trainerCoords.sety((short) (trainerCoords.gety() + _dir));
        }
        if (accessPoint.gety() > _y) {
            accessPoint.sety((short) (accessPoint.gety() + _dir));
        }
    }

    @Override
    public void translateByColumn(short _x, short _dir) {
        super.translateByColumn(_x, _dir);
        if (trainerCoords.getx() > _x) {
            trainerCoords.setx((short) (trainerCoords.getx() + _dir));
        }
        if (accessPoint.getx() > _x) {
            accessPoint.setx((short) (accessPoint.getx() + _dir));
        }
    }

    @Override
    public void translateElement(Point _id, Point _target) {
        if (!isEmptyForAdding(_target)) {
            return;
        }
        if (Point.eq(trainerCoords, _id)) {
            trainerCoords.affect(_target);
        }
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
