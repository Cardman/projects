package aiki;

import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import code.util.ints.Equallable;

public final class ImageHeroKey implements Equallable<ImageHeroKey> {

    private EnvironmentType type;

    private Direction direction;

    private Sex sex;

    public ImageHeroKey() {
    }

    public ImageHeroKey(EnvironmentType _type, Sex _sex) {
        type = _type;
        sex = _sex;
    }

    public ImageHeroKey(EnvironmentType _type, Direction _direction, Sex _sex) {
        type = _type;
        direction = _direction;
        sex = _sex;
    }

    @Override
    public boolean eq(ImageHeroKey _obj) {
        if (type != _obj.type) {
            return false;
        }
        if (direction != _obj.direction) {
            return false;
        }
        if (sex != _obj.sex) {
            return false;
        }
        return true;
    }

    public EnvironmentType getType() {
        return type;
    }

    public void setType(EnvironmentType _type) {
        type = _type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction _direction) {
        direction = _direction;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex _sex) {
        sex = _sex;
    }
}
