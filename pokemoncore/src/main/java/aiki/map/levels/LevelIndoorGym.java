package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import code.util.EntryCust;
import code.util.ObjectMap;


public final class LevelIndoorGym extends Level {

    private ObjectMap<Point, GymTrainer> gymTrainers;

    private Point gymLeaderCoords;

    private GymLeader gymLeader;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        if (!_level.allAccessible()) {
            _data.setError(true);
        }
        super.validate(_data, _level);
        for (EntryCust<Point, GymTrainer> e : gymTrainers.entryList()) {
            if (!_level.isValid(e.getKey(), true)) {
                _data.setError(true);
            }
            e.getValue().validate(_data);
        }
        if (!_level.isValid(gymLeaderCoords, true)) {
            _data.setError(true);
        }
        gymLeader.validate(_data);
        if (gymTrainers.contains(gymLeaderCoords)) {
            _data.setError(true);
        }
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        if (Point.eq(gymLeaderCoords, _point)) {
            return false;
        }
        if (gymTrainers.contains(_point)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty(Point _point) {
        if (Point.eq(gymLeaderCoords, _point)) {
            return false;
        }
        if (gymTrainers.contains(_point)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        for (GymTrainer g : gymTrainers.values()) {
            if (!g.hasValidImage(_data)) {
                return false;
            }
        }
        if (!gymLeader.hasValidImage(_data)) {
            return false;
        }
        return true;
    }

    public ObjectMap<Point, GymTrainer> getGymTrainers() {
        return gymTrainers;
    }

    public void setGymTrainers(ObjectMap<Point, GymTrainer> _gymTrainers) {
        gymTrainers = _gymTrainers;
    }

    public Point getGymLeaderCoords() {
        return gymLeaderCoords;
    }

    public void setGymLeaderCoords(Point _gymLeader) {
        gymLeaderCoords = _gymLeader;
    }

    public GymLeader getGymLeader() {
        return gymLeader;
    }

    public void setGymLeader(GymLeader _gymLeader) {
        gymLeader = _gymLeader;
    }
}
