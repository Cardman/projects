package aiki.beans.endround;
import code.bean.Accessible;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.EndTurnType;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;

public class EffectEndRoundBean extends CommonBean {

    @Accessible
    private final String endRoundHtml="web/html/endround/eff.html";

    private EffectEndRound effect;

    @Accessible
    private long index;

    private EndRoundMainElements element;

    @Accessible
    private String move;

    @Accessible
    private String ability;

    @Accessible
    private String status;

    @Accessible
    private String item;

    @Accessible
    private StringList moves;

    @Accessible
    private int endRoundRank;

    @Accessible
    private StringList reasonsEndRound;

    @Accessible
    private NatTreeMap<String,String> mapVarsFailEndRound;

    @Override
    public void beforeDisplaying() {
        //super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        element = data_.getEvtEndRound().get((int) index);
        move = DataBase.EMPTY_STRING;
        ability = DataBase.EMPTY_STRING;
        status = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
        moves = new StringList();
        if (element.getEndRoundType() == EndTurnType.ATTAQUE) {
//            move = element.getElement();
            move = data_.translateMove(element.getElement());
//            for (Effect e: data_.getMove(move).getEffects()) {
//                if (e instanceof EffectEndRound) {
//                    effect_ = (EffectEndRound) e;
//                    break;
//                }
//            }
        } else if (element.getEndRoundType() == EndTurnType.CAPACITE) {
            ability = data_.translateAbility(element.getElement());
            //effect_ = data_.getAbility(ability).getEffectEndRound().first();
        } else if (element.getEndRoundType() == EndTurnType.OBJET) {
            item = data_.translateItem(element.getElement());
//            Item it_ = data_.getItem(item);
//            ItemForBattle itBat_ = (ItemForBattle) it_;
//            effect_ = itBat_.getEffectEndRound().first();
        } else if (element.getEndRoundType() == EndTurnType.ATTAQUE_COMBI) {
            StringList moves_ = StringList.splitStrings(element.getElement(), DataBase.SEPARATOR_MOVES);
            for (String m: moves_) {
                moves.add(data_.translateMove(m));
            }
//            Map<String,String> translatedMoves_;
//            translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//            moves.sort(new ComparatorTrString<>(translatedMoves_));
            //effect_ = data_.getCombos().getEffects().getVal(moves_).getEffectEndRound().first();
        } else {
            status = data_.translateStatus(element.getElement());
            //effect_ = data_.getStatus(status).getEffectEndRound().first();
        }
        if (element.isIncrementNumberOfRounds()) {
            endRoundRank = element.getNumberIncrement();
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatTreeMap<String,String>();
            return;
        }
        EffectEndRound effect_ = getEffect((long) index);
        if (effect_ == null) {
            endRoundRank = element.getNumberIncrement();
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatTreeMap<String,String>();
            return;
        }
        effect = effect_;
        endRoundRank = element.getNumberIncrement();
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_ = CommonBean.getFormattedReasons(data_, getFailEndRoundReasons(), getLanguage());
        reasons_ = new StringList();
//        for (String f: getFailEndRoundReasons()) {
//            String formula_ = data_.getFormula(f, getLanguage());
////            formula_ = StringList.replace(formula_, locHtml_);
////            formula_ = formula_.replace(EAMP, E_AMP);
////            formula_ = formula_.replace(EGT, E_GT);
////            formula_ = formula_.replace(ELT, E_LT);
////            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
////            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            reasons_.add(formula_);
//        }
        reasonsEndRound = reasons_;
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(effect_.getFailEndRound(),getLanguage());
        NatTreeMap<String,String> mapVarsFail_ = new NatTreeMap<String,String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailEndRound = mapVarsFail_;
    }

    protected EffectEndRound getEffect() {
        return effect;
    }

    protected EndRoundMainElements getElement() {
        return element;
    }

    protected EffectEndRound getEffect(Long _index) {
        EffectEndRound effect_ = null;
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        if (element_.getEndRoundType() == EndTurnType.ATTAQUE) {
            String move_ = element_.getElement();
            for (Effect e: data_.getMove(move_).getEffects()) {
                if (e instanceof EffectEndRound) {
                    effect_ = (EffectEndRound) e;
                    break;
                }
            }
        } else if (element_.getEndRoundType() == EndTurnType.CAPACITE) {
            String ability_ = element_.getElement();
            effect_ = data_.getAbility(ability_).getEffectEndRound().first();
        } else if (element_.getEndRoundType() == EndTurnType.OBJET) {
            String item_ = element_.getElement();
            Item it_ = data_.getItem(item_);
            ItemForBattle itBat_ = (ItemForBattle) it_;
            effect_ = itBat_.getEffectEndRound().first();
        } else if (element_.getEndRoundType() == EndTurnType.ATTAQUE_COMBI) {
            StringList moves_ = StringList.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
//            if (data_.getCombos().getEffects().getVal(moves_).getEffectEndRound().isEmpty()) {
//                return null;
//            }
            effect_ = data_.getCombos().getEffects().getVal(moves_).getEffectEndRound().first();
        } else {
            String status_ = element_.getElement();
            if (data_.getStatus(status_).getEffectEndRound().isEmpty()) {
                return null;
            }
            effect_ = data_.getStatus(status_).getEffectEndRound().first();
        }
        return effect_;
    }

    @Accessible
    private String clickMoves(Long _indexOne, Long _indexTwo) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_indexOne.intValue());
        StringList moves_ = StringList.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        getForms().put(MOVE, moves_.get(_indexTwo.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMoves(Long _indexTwo) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get((int) index);
        StringList moves_ = StringList.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        return translatedMoves_.getVal(moves_.get(_indexTwo.intValue()));
    }

    @Accessible
    private String clickMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(MOVE, element_.getElement());
        return MOVE;
    }

    @Accessible
    private String clickAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(ABILITY, element_.getElement());
        return ABILITY;
    }

    @Accessible
    private String clickItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(ITEM, element_.getElement());
        return ITEM;
    }

    @Accessible
    private String clickStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(STATUS, element_.getElement());
        return STATUS;
    }

    private StringList getFailEndRoundReasons() {
        EffectEndRound effect_ = effect;
        return getReasons(effect_.getFailEndRound());
    }
}
