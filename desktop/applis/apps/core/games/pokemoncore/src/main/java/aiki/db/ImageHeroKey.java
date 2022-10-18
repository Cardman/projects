package aiki.db;

import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;

public final class ImageHeroKey {

    private final EnvironmentType type;

    private final Direction direction;

    private final Sex sex;

    public ImageHeroKey(EnvironmentType _type, Sex _sex) {
        this(_type,null,_sex);
    }

    public ImageHeroKey(EnvironmentType _type, Direction _direction, Sex _sex) {
        type = _type;
        direction = _direction;
        sex = _sex;
    }

    public static ImageHeroKey direct(EnvironmentType _type, Player _pl) {
        return new ImageHeroKey(_type,_pl.getSex());
    }

    public static ImageHeroKey opposite(EnvironmentType _type, Player _pl) {
        return new ImageHeroKey(_type,_pl.getOppositeSex());
    }


    public static ImageHeroKey direct(EnvironmentType _type, Direction _direction, Player _pl) {
        return new ImageHeroKey(_type,_direction,_pl.getSex());
    }

    public boolean eq(ImageHeroKey _obj) {
        if (type != _obj.type) {
            return false;
        }
        if (direction != _obj.direction) {
            return false;
        }
        return sex == _obj.sex;
    }

    public EnvironmentType getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public Sex getSex() {
        return sex;
    }

}
