package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.TrainerLeague;
import aiki.map.tree.LevelArea;
import aiki.util.*;


public final class LevelLeague extends Level {

    private NullablePoint trainerCoords;

    private TrainerLeague trainer;

    private NullablePoint accessPoint;

    private NullablePoint nextLevelTarget;

    private String fileName;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        if (!_level.allAccessible()) {
            _data.setError(true);
        }
        super.validate(_data, _level);
        DataInfoChecker.checkEmpty(_data,this,accessPoint);
        DataInfoChecker.checkKey(_data,_level,accessPoint,true);
        DataInfoChecker.checkKey(_data,_level,trainerCoords,true);
        trainer.validate(_data);
    }

    @Override
    public boolean isEmpty(Point _point) {
        return isEmptyForAdding(_point);
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        return !Point.eq(trainerCoords, _point);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = super.hasValidImage(_data);
        if (!trainer.hasValidImage(_data)) {
            val_ = false;
        }
        if (_data.getLink(fileName).length == 0) {
            val_ = false;
        }
        return val_;
    }

    public NullablePoint getTrainerCoords() {
        return trainerCoords;
    }

    public void setTrainerCoords(Point _trainer) {
        setTrainerCoords(new NullablePoint(_trainer));
    }

    public void setTrainerCoords(NullablePoint _trainer) {
        trainerCoords = _trainer;
    }

    public TrainerLeague getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerLeague _trainer) {
        trainer = _trainer;
    }

    public NullablePoint getAccessPoint() {
        return accessPoint;
    }

    public void setAccessPoint(Point _accessPoint) {
        setAccessPoint(new NullablePoint(_accessPoint));
    }

    public void setAccessPoint(NullablePoint _accessPoint) {
        accessPoint = _accessPoint;
    }

    public NullablePoint getNextLevelTarget() {
        return nextLevelTarget;
    }

    public void setNextLevelTarget(Point _nextLevelTarget) {
        setNextLevelTarget(new NullablePoint(_nextLevelTarget));
    }

    public void setNextLevelTarget(NullablePoint _nextLevelTarget) {
        nextLevelTarget = _nextLevelTarget;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }
}
