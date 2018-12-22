package aiki.beans.endround;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.EndTurnType;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import aiki.fight.moves.effects.EffectEndRoundGlobal;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import code.util.CustList;
import code.util.StringList;

public class EndRoundBean extends CommonBean {

    private static final String PAGE_EFF = "web/html/endround/eff.html";
    private static final String PAGE_GLOBAL = "web/html/endround/global.html";
    private static final String PAGE_INDIVIDUAL = "web/html/endround/individual.html";
    private static final String PAGE_STATUSRELATION = "web/html/endround/statusrelation.html";
    private static final String PAGE_STATUS = "web/html/endround/status.html";
    private static final String PAGE_SINGLERELATION = "web/html/endround/singlerelation.html";
    private static final String PAGE_FOE = "web/html/endround/foe.html";
    private static final String PAGE_TEAM = "web/html/endround/team.html";
    private static final String PAGE_MULTIRELATION = "web/html/endround/multirelation.html";
    private static final String PAGE_POSITIONRELATION = "web/html/endround/positionrelation.html";
    private static final String PAGE_POSITIONTARGET = "web/html/endround/positiontarget.html";
    public String getPage(Long _index) {
        CustList<EndRoundMainElements> evts_ = getEvts();
        EndRoundMainElements elt_ = evts_.get(_index.intValue());
        if (elt_.isIncrementNumberOfRounds()) {
            return PAGE_EFF;
        }
        DataBase data_ = (DataBase) getDataBase();
        EffectEndRound effect_ = null;
        if (elt_.getEndRoundType() == EndTurnType.ATTAQUE) {
            String move_ = elt_.getElement();
            for (Effect e: data_.getMove(move_).getEffects()) {
                if (e instanceof EffectEndRound) {
                    effect_ = (EffectEndRound) e;
                    break;
                }
            }
        } else if (elt_.getEndRoundType() == EndTurnType.CAPACITE) {
            String ability_ = elt_.getElement();
            effect_ = data_.getAbility(ability_).getEffectEndRound().first();
        } else if (elt_.getEndRoundType() == EndTurnType.OBJET) {
            String item_ = elt_.getElement();
            Item it_ = data_.getItem(item_);
            ItemForBattle itBat_ = (ItemForBattle) it_;
            effect_ = itBat_.getEffectEndRound().first();
        } else if (elt_.getEndRoundType() == EndTurnType.ATTAQUE_COMBI) {
            StringList moves_ = StringList.splitStrings(elt_.getElement(), DataBase.SEPARATOR_MOVES);
//            if (data_.getCombos().getEffects().getVal(moves_).getEffectEndRound().isEmpty()) {
//                return PAGE_EFF;
//            }
            effect_ = data_.getCombos().getEffects().getVal(moves_).getEffectEndRound().first();
        } else {
            String status_ = elt_.getElement();
            if (data_.getStatus(status_).getEffectEndRound().isEmpty()) {
                return PAGE_EFF;
            }
            effect_ = data_.getStatus(status_).getEffectEndRound().first();
        }
        if (effect_ == null) {
            return PAGE_EFF;
        }
        if (effect_ instanceof EffectEndRoundGlobal) {
            return PAGE_GLOBAL;
        }
        if (effect_ instanceof EffectEndRoundIndividual) {
            return PAGE_INDIVIDUAL;
        }
        if (effect_ instanceof EffectEndRoundStatusRelation) {
            return PAGE_STATUSRELATION;
        }
        if (effect_ instanceof EffectEndRoundStatus) {
            return PAGE_STATUS;
        }
        if (effect_ instanceof EffectEndRoundSingleRelation) {
            return PAGE_SINGLERELATION;
        }
        if (effect_ instanceof EffectEndRoundFoe) {
            return PAGE_FOE;
        }
        if (effect_ instanceof EffectEndRoundTeam) {
            return PAGE_TEAM;
        }
        if (effect_ instanceof EffectEndRoundMultiRelation) {
            return PAGE_MULTIRELATION;
        }
        if (effect_ instanceof EffectEndRoundPositionRelation) {
            return PAGE_POSITIONRELATION;
        }
        if (effect_ instanceof EffectEndRoundPositionTargetRelation) {
            return PAGE_POSITIONTARGET;
        }
        return DataBase.EMPTY_STRING;
    }
    public CustList<EndRoundMainElements> getEvts() {
        DataBase data_ = (DataBase) getDataBase();
        return data_.getEvtEndRound();
    }
}