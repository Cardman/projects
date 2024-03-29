package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.TargetCoords;
import code.util.core.NumberUtil;

public class AnimationHealing extends AnimationInt {

    private TargetCoords healed;

    public TargetCoords getHealed() {
        return healed;
    }

    public void setHealed(TargetCoords _healed) {
        healed = _healed;
    }

    public boolean isPlayer() {
        return NumberUtil.eq(healed.getTeam(), Fight.CST_PLAYER);
    }

    /**For now, it is difficult to gear drawing i back or team*/
    public boolean isBackOrTeam() {
        return NumberUtil.eq(healed.getPosition(), Fighter.BACK);
    }
}
