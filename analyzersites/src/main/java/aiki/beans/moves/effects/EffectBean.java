package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.util.NatTreeMap;
import code.util.StringList;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.enums.TargetChoice;

public class EffectBean extends CommonBean {

    private Effect effect;

    @Accessible
    private final String effectBean="web/html/moves/effects/eff.html";

    @Accessible
    private String move;

    @Accessible
    private int index;

    @Accessible
    private StringList reasons;

    @Accessible
    private NatTreeMap<String,String> mapVarsFail;

    @Accessible
    private boolean needSuccessFirstEffect;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(move);
        effect = move_.getEffects().get(index);
        StringList reasons_ = getFormattedReasons(data_, getFailReasons(), getLanguage());
        reasons = reasons_;
        mapVarsFail = getMapVarsFail(data_, effect.getFail(), getLanguage());
        needSuccessFirstEffect = effect.getRequiredSuccessfulEffects().containsObj(move_.indexOfPrimaryEffect());
    }

    protected Effect getEffect() {
        return effect;
    }

    protected Effect getEffect(int _indexEffect) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(getMove());
        return move_.getEffet(_indexEffect);
    }

    protected String getMove() {
        return move;
    }

    private StringList getFailReasons() {
        return getReasons(effect.getFail());
    }

    /*public static StringList getFormattedReasons(DataBase _data, StringList _reasons, String _language) {
        StringList reasons_;
        reasons_ = new StringList();
        for (String f: _reasons) {
            String formula_ = _data.getFormula(f, _language);
            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            formula_ = formula_.replace(EAMP, E_AMP);
            formula_ = formula_.replace(EGT, E_GT);
            formula_ = formula_.replace(ELT, E_LT);
            reasons_.add(formula_);
        }
        return reasons_;
    }

    public static TreeMap<String,String> getMapVarsFail(DataBase _data, String _fail, String _language) {
        Map<String,String> mapVars_ = _data.getDescriptions(_fail, _language);
        TreeMap<String,String> mapVarsFail_ = new TreeMap<>(new);
        StringList desc_ = new StringList(mapVars_.keySet());
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        return mapVarsFail_;
    }

    public static StringList getReasons(String _booleanString) {
        StringList reasons_;
        reasons_ = new StringList();
        int i_ = List.FIRST_INDEX;
        int iPostSep_ = List.FIRST_INDEX;
        int nbLeftPar_ = 0;
        int nbRightPar_ = 0;
        String fail_ = _booleanString;
        while (i_ < fail_.length()) {
            if (fail_.charAt(i_) == LEFT_PAR) {
                nbLeftPar_ ++;
            }
            if (fail_.charAt(i_) == RIGHT_PAR) {
                nbRightPar_ ++;
            }
            if (fail_.charAt(i_) == PIPE_CHAR) {
                if (nbLeftPar_ == nbRightPar_) {
                    reasons_.add(fail_.substring(iPostSep_, i_));
                    iPostSep_ = i_ + 1;
                }
            }
            i_ ++;
        }
        if (iPostSep_ < fail_.length()) {
            reasons_.add(fail_.substring(iPostSep_));
        }
        return reasons_;
    }*/

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
    public boolean isNothing() {
        return effect.getTargetChoice() == TargetChoice.NOTHING;
    }
    @Accessible
    private TargetChoice getTargetChoice() {
        return effect.getTargetChoice();
    }
}
