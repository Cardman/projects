package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;
import code.util.*;

public class AnimationEffect implements AnimationInt {

    private TargetCoords fromFighter;

    private TargetCoords toFighter;

    private boolean koTarget;

    private boolean koUser;

    public TargetCoords getFromFighter() {
        return fromFighter;
    }

    public void setFromFighter(TargetCoords _user) {
        fromFighter = _user;
    }

    public boolean isPlayerFromFighter() {
        return Numbers.eq(fromFighter.getTeam(), Fight.PLAYER);
    }

    public TargetCoords getToFighter() {
        return toFighter;
    }

    public void setToFighter(TargetCoords _target) {
        toFighter = _target;
    }

    public boolean isKoToFighter() {
        return koTarget;
    }

    public void setKoToFighter(boolean _koTarget) {
        koTarget = _koTarget;
    }

    public boolean isKoFromFighter() {
        return koUser;
    }

    public void setKoFromFighter(boolean _koUser) {
        koUser = _koUser;
    }

    public boolean isPlayerToFighter() {
        return Numbers.eq(toFighter.getTeam(), Fight.PLAYER);
    }
}
