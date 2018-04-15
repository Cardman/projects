package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;
import code.util.Numbers;

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
