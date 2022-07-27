package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;
import code.util.core.NumberUtil;

public final class AnimationAutoEffect extends AnimationInt {

    private final AutoEffectKind autoEffectKind;

    private TargetCoords user;

    private boolean koUser;

    public AnimationAutoEffect(AutoEffectKind _autoEffectKind) {
        autoEffectKind = _autoEffectKind;
    }

    public boolean isPlayer() {
        return NumberUtil.eq(user.getTeam(), Fight.CST_PLAYER);
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
