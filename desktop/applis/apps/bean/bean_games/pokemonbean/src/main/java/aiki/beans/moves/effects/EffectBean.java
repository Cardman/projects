package aiki.beans.moves.effects;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.enums.TargetChoice;
import code.util.NatStringTreeMap;
import code.util.StringList;

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
        reasons = getFormattedReasons(data_, getFailReasons(), getLanguage());
        mapVarsFail = getMapVarsFail(data_, effect.getFail(), getLanguage());
        needSuccessFirstEffect = effect.getRequiredSuccessfulEffects().containsObj(move_.indexOfPrimaryEffect());
    }

    protected Effect getEffect() {
        return effect;
    }

    protected Effect getEffect(int _indexEffect) {
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(getMove());
        return move_.getEffet(_indexEffect);
    }

    public String getMove() {
        return move;
    }

    private StringList getFailReasons() {
        return getReasons(effect.getFail());
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