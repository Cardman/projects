package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.TargetCoords;
import code.util.core.NumberUtil;

public class AnimationEffect implements AnimationInt {

    private int index;

    private final EffectKind effectKind;

    private TargetCoords fromFighter;

    private TargetCoords toFighter;

    private boolean koTarget;

    private boolean koUser;

    public AnimationEffect() {
        effectKind = EffectKind.SIMPLE;
    }

    public AnimationEffect(EffectKind _effectKind) {
        effectKind = _effectKind;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index+getIndex();
    }

    public EffectKind getEffectKind() {
        return effectKind;
    }

    public TargetCoords getFromFighter() {
        return fromFighter;
    }

    public void setFromFighter(TargetCoords _user) {
        fromFighter = _user;
    }

    public boolean isPlayerFromFighter() {
        return NumberUtil.eq(fromFighter.getTeam(), Fight.PLAYER);
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
        return NumberUtil.eq(toFighter.getTeam(), Fight.PLAYER);
    }
}
