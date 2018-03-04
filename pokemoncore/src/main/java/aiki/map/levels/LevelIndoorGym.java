package aiki.map.levels;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.tree.LevelArea;
import aiki.util.Point;
import code.serialize.CheckedData;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public final class LevelIndoorGym extends Level {

    private ObjectMap<Point,GymTrainer> gymTrainers;

    @CheckedData
    private Point gymLeaderCoords;

    private GymLeader gymLeader;

    @Override
    public void validate(DataBase _data,LevelArea _level) {
        if (!_level.allAccessible()) {
            throw new DataException();
        }
        super.validate(_data, _level);
        for (EntryCust<Point,GymTrainer> e: gymTrainers.entryList()) {
            if (!_level.isValid(e.getKey(),true)) {
                throw new DataException();
            }
            e.getValue().validate(_data);
        }
        if (!_level.isValid(gymLeaderCoords,true)) {
            throw new DataException();
        }
        gymLeader.validate(_data);
        if (gymTrainers.contains(gymLeaderCoords)) {
            throw new DataException();
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        for (EntryCust<Point,GymTrainer> e: gymTrainers.entryList()) {
            e.getValue().validate(_data);
        }
        gymLeader.validate(_data);
        if (gymTrainers.contains(gymLeaderCoords)) {
            throw new DataException();
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
    public void clearElements(Point _point) {
        gymTrainers.removeKey( _point);
    }

    @Override
    public void translateByLine(short _y,short _dir) {
        super.translateByLine(_y, _dir);
        Level.translateGymTrainerLineData(gymTrainers, _y, _dir);
        if (gymLeaderCoords.gety() > _y) {
            gymLeaderCoords.sety((short) (gymLeaderCoords.gety()+_dir));
        }
    }
    @Override
    public void translateByColumn(short _x,short _dir) {
        super.translateByColumn(_x, _dir);
        Level.translateGymTrainerColumnData(gymTrainers, _x, _dir);
        if (gymLeaderCoords.getx() > _x) {
            gymLeaderCoords.setx((short) (gymLeaderCoords.getx()+_dir));
        }
    }

    @Override
    public void translateElement(Point _id, Point _target) {
        if (!isEmptyForAdding(_target)) {
            return;
        }
        if (Point.eq(gymLeaderCoords, _id)) {
            gymLeaderCoords.affect(_target);
            return;
        }
        if (gymTrainers.contains(_id)) {
            gymTrainers.move(_id, _target);
        }
    }

    @Override
    public boolean hasValidImage(DataBase _data) {
        if (!super.hasValidImage(_data)) {
            return false;
        }
        for (GymTrainer g: gymTrainers.values()) {
            if (!g.hasValidImage(_data)) {
                return false;
            }
        }
        if (!gymLeader.hasValidImage(_data)) {
            return false;
        }
        return true;
    }

    public ObjectMap<Point,GymTrainer> getGymTrainers() {
        return gymTrainers;
    }

    public void setGymTrainers(ObjectMap<Point,GymTrainer> _gymTrainers) {
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
