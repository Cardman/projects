package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;
import code.util.core.NumberUtil;

public final class AnimationAutoEffect implements AnimationInt {

    private int index;

    private final AutoEffectKind autoEffectKind;

    private TargetCoords user;

    private boolean koUser;

    public AnimationAutoEffect(AutoEffectKind _autoEffectKind) {
        autoEffectKind = _autoEffectKind;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index+getIndex();
    }

    public boolean isPlayer() {
        return NumberUtil.eq(user.getTeam(), Fight.PLAYER);
    }

    public TargetCoords getUser() {
        return user;
    }

    public void setUser(TargetCoords _user) {
        user = _user;
    }

    public AutoEffectKind getAutoEffectKind() {
        return autoEffectKind;
    }

    public boolean isKoUser() {
        return koUser;
    }

    public void setKoUser(boolean _koUser) {
        koUser = _koUser;
    }
}
