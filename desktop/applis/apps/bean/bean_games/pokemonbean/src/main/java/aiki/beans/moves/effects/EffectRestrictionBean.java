package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import code.scripts.pages.aiki.MessagesDataEffrestriction;
import code.scripts.pages.aiki.MessagesPkBean;

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

    @Override
    public void buildSubEffPre() {
        displayBoolTrue(toInt(getForbidTargetUsingItem()), MessagesPkBean.EFF_RESTRICTION, MessagesDataEffrestriction.M_P_57_EFFECT_ITEM);
        displayBoolTrue(toInt(getForbidTargetUsingItem()), MessagesPkBean.EFF_RESTRICTION, MessagesDataEffrestriction.M_P_57_EFFECT_ITEM_2);
        displayBoolTrue(toInt(forbid()), MessagesPkBean.EFF_RESTRICTION, MessagesDataEffrestriction.M_P_57_EFFECT_MOVE);
    }

    @Override
    public void buildSubEff() {
        procMoveChoiceRestrictionType(getChoiceRestriction(),MoveChoiceRestrictionType.CATEGORIE_AUTRE, MessagesDataEffrestriction.M_P_57_FORBID_STATUS_MOVE);
        procMoveChoiceRestrictionType(getChoiceRestriction(),MoveChoiceRestrictionType.DER,MessagesDataEffrestriction.M_P_57_FORBID_LAST_MOVE);
        procMoveChoiceRestrictionType(getChoiceRestriction(),MoveChoiceRestrictionType.LANCEUR_ATTAQUES,MessagesDataEffrestriction.M_P_57_FORBID_USER_MOVES);
        procMoveChoiceRestrictionType(getChoiceRestriction(),MoveChoiceRestrictionType.FORBIDDEN,MessagesDataEffrestriction.M_P_57_FORBID_USE_LAST_MOVE);
        procMoveChoiceRestrictionType(getChoiceRestriction(),MoveChoiceRestrictionType.FORCE,MessagesDataEffrestriction.M_P_57_FORCE_USE_LAST_MOVE);
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

    public MoveChoiceRestrictionType getChoiceRestriction() {
        return choiceRestriction;
    }

    public boolean getForbidTargetUsingItem() {
        return forbidTargetUsingItem;
    }
}