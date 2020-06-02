package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;

public class EffectRestrictionBean extends EffectBean {
    private boolean forbidTargetUsingItem;

    private MoveChoiceRestrictionType choiceRestriction;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectRestriction effect_ = (EffectRestriction) getEffect();
        forbidTargetUsingItem = effect_.getForbidTargetUsingItem();
        choiceRestriction = effect_.getChoiceRestriction();
    }
    public boolean forbid() {
        return choiceRestriction != MoveChoiceRestrictionType.NOTHING;
    }
    public boolean forbidStatusMove() {
        return choiceRestriction == MoveChoiceRestrictionType.CATEGORIE_AUTRE;
    }
    public boolean forbidLastMove() {
        return choiceRestriction == MoveChoiceRestrictionType.DER;
    }
    public boolean forbidUserMoves() {
        return choiceRestriction == MoveChoiceRestrictionType.LANCEUR_ATTAQUES;
    }
    public boolean forbidUseMove() {
        return choiceRestriction == MoveChoiceRestrictionType.FORBIDDEN;
    }
    public boolean forceUseMove() {
        return choiceRestriction == MoveChoiceRestrictionType.FORCE;
    }

    public boolean getForbidTargetUsingItem() {
        return forbidTargetUsingItem;
    }
}