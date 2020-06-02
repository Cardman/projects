package aiki.game.fight.animations;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.TargetCoords;
import code.util.*;

public class AnimationHealing implements AnimationInt {

    private TargetCoords healed;

    public TargetCoords getHealed() {
        return healed;
    }

    public void setHealed(TargetCoords _healed) {
        healed = _healed;
    }

    public boolean isPlayer() {
        return Numbers.eq(healed.getTeam(), Fight.PLAYER);
    }

    /**For now, it is difficult to gear drawing i back or team*/
    public boolean isBackOrTeam() {
        return Numbers.eq(healed.getPosition(), Fighter.BACK);
    }
}
