package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.tree.LevelArea;
import aiki.util.*;
import code.util.*;


public final class LevelIndoorGym extends Level {

    private Points< GymTrainer> gymTrainers;

    private NullablePoint gymLeaderCoords;

    private GymLeader gymLeader;

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        for (EntryCust<Point,GymTrainer> e : gymTrainers.entryList()) {
            e.getValue().validate(_data);
        }
        gymLeader.validate(_data);
    }
    public CustList<NullablePoint> validateLinks() {
        CustList<NullablePoint> keys_ = new CustList<NullablePoint>();
        for (EntryCust<Point,GymTrainer> e : gymTrainers.entryList()) {
            keys_.add(new NullablePoint(e.getKey()));
        }
        keys_.add(gymLeaderCoords);
        return keys_;
    }
    @Override
    public boolean isEmpty(Point _point) {
        return isEmptyForAdding(_point);
    }

    @Override
    public boolean isEmptyForAdding(Point _point) {
        if (Point.eq(gymLeaderCoords, _point)) {
            return false;
        }
        return !gymTrainers.contains(_point);
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        boolean val_ = super.hasValidImage(_data);
        for (GymTrainer g : gymTrainers.values()) {
            if (!g.hasValidImage(_data)) {
                val_ = false;
            }
        }
        if (!gymLeader.hasValidImage(_data)) {
            val_ = false;
        }
        return val_;
    }

    public Points< GymTrainer> getGymTrainers() {
        return gymTrainers;
    }

    public void setGymTrainers(Points< GymTrainer> _gymTrainers) {
        gymTrainers = _gymTrainers;
    }

    public NullablePoint getGymLeaderCoords() {
        return gymLeaderCoords;
    }

    public void setGymLeaderCoords(Point _gymLeader) {
        setGymLeaderCoords(new NullablePoint(_gymLeader));
    }

    public void setGymLeaderCoords(NullablePoint _gymLeaderCoords) {
        this.gymLeaderCoords = _gymLeaderCoords;
    }

    public GymLeader getGymLeader() {
        return gymLeader;
    }

    public void setGymLeader(GymLeader _gymLeader) {
        gymLeader = _gymLeader;
    }
}
