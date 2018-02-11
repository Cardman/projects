package aiki.beans.moves.effects;
import code.bean.Accessible;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;

public class EffectRestrictionBean extends EffectBean {

    @Accessible
    private boolean forbidTargetUsingItem;

    private MoveChoiceRestrictionType choiceRestriction;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectRestriction effect_ = (EffectRestriction) getEffect();
        forbidTargetUsingItem = effect_.getForbidTargetUsingItem();
        choiceRestriction = effect_.getChoiceRestriction();
    }

    @Accessible
    private boolean forbid() {
        return choiceRestriction != MoveChoiceRestrictionType.NOTHING;
    }

    @Accessible
    private boolean forbidStatusMove() {
        return choiceRestriction == MoveChoiceRestrictionType.CATEGORIE_AUTRE;
    }

    @Accessible
    private boolean forbidLastMove() {
        return choiceRestriction == MoveChoiceRestrictionType.DER;
    }

    @Accessible
    private boolean forbidUserMoves() {
        return choiceRestriction == MoveChoiceRestrictionType.LANCEUR_ATTAQUES;
    }

    @Accessible
    private boolean forbidUseMove() {
        return choiceRestriction == MoveChoiceRestrictionType.FORBIDDEN;
    }

    @Accessible
    private boolean forceUseMove() {
        return choiceRestriction == MoveChoiceRestrictionType.FORCE;
    }
}
