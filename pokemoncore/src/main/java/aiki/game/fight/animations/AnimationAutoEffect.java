package aiki.game.fight.animations;
import code.util.Numbers;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;

public class AnimationAutoEffect implements AnimationInt {

    private TargetCoords user;

    private boolean koUser;

    public boolean isPlayer() {
        return Numbers.eq(user.getTeam(), Fight.PLAYER);
    }

    public TargetCoords getUser() {
        return user;
    }

    public void setUser(TargetCoords _user) {
        user = _user;
    }

    public boolean isKoUser() {
        return koUser;
    }

    public void setKoUser(boolean _koUser) {
        koUser = _koUser;
    }
}
