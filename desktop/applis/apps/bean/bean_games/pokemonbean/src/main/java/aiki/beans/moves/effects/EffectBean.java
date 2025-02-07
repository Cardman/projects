package aiki.beans.moves.effects;
import aiki.beans.*;
import aiki.db.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import code.util.*;

public class EffectBean extends CommonBean {

    private Effect effect;
    private String move;
    private int index;
    private StringList reasons;
    private NatStringTreeMap<String> mapVarsFail;
    private boolean needSuccessFirstEffect;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(move);
        effect = move_.getEffects().get(index);
        reasons = getFormattedReasons(data_, effect.getFail(), getLanguage());
        mapVarsFail = getMapVarsFail(data_, effect.getFail(), getLanguage());
        needSuccessFirstEffect = effect.getRequiredSuccessfulEffects().containsObj(move_.indexOfPrimaryEffect());
    }

    protected Effect getEffect() {
        return effect;
    }

    public String getMove() {
        return move;
    }

    public boolean isAdjAdv() {
        return effect.getTargetChoice() == TargetChoice.ADJ_ADV;
    }
    public boolean isAdjMult() {
        return effect.getTargetChoice() == TargetChoice.ADJ_MULT;
    }
    public boolean isAdjUniq() {
        return effect.getTargetChoice() == TargetChoice.ADJ_UNIQ;
    }
    public boolean isAllie() {
        return effect.getTargetChoice() == TargetChoice.ALLIE;
    }
    public boolean isAllies() {
        return effect.getTargetChoice() == TargetChoice.ALLIES;
    }
    public boolean isAnyFoe() {
        return effect.getTargetChoice() == TargetChoice.ANY_FOE;
    }
    public boolean isAutreUniq() {
        return effect.getTargetChoice() == TargetChoice.AUTRE_UNIQ;
    }
    public boolean isGlobale() {
        return effect.getTargetChoice() == TargetChoice.GLOBALE;
    }
    public boolean isLanceur() {
        return effect.getTargetChoice() == TargetChoice.LANCEUR;
    }
    public boolean isPseudoGlobale() {
        return effect.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE;
    }
    public boolean isTousAdv() {
        return effect.getTargetChoice() == TargetChoice.TOUS_ADV;
    }
    public boolean isUniqueImporte() {
        return effect.getTargetChoice() == TargetChoice.UNIQUE_IMPORTE;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public int getIndex() {
        return index;
    }

    public void setMove(String _move) {
        move = _move;
    }

    public StringList getReasons() {
        return reasons;
    }

    public NatStringTreeMap<String> getMapVarsFail() {
        return mapVarsFail;
    }

    public boolean getNeedSuccessFirstEffect() {
        return needSuccessFirstEffect;
    }

}