package aiki.beans.endround;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.EndTurnType;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;

public class EffectEndRoundBean extends CommonBean {
    static final String END_ROUND_HTML="web/html/endround/eff.html";

    private EffectEndRound effect;
    private long index;

    private EndRoundMainElements element;
    private String move;
    private String ability;
    private String status;
    private String item;
    private StringList moves;
    private int endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        element = data_.getEvtEndRound().get((int) index);
        move = DataBase.EMPTY_STRING;
        ability = DataBase.EMPTY_STRING;
        status = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
        moves = new StringList();
        if (element.getEndRoundType() == EndTurnType.ATTAQUE) {
            move = data_.translateMove(element.getElement());
        } else if (element.getEndRoundType() == EndTurnType.CAPACITE) {
            ability = data_.translateAbility(element.getElement());
        } else if (element.getEndRoundType() == EndTurnType.OBJET) {
            item = data_.translateItem(element.getElement());
        } else if (element.getEndRoundType() == EndTurnType.ATTAQUE_COMBI) {
            StringList moves_ = StringList.splitStrings(element.getElement(), DataBase.SEPARATOR_MOVES);
            for (String m: moves_) {
                moves.add(data_.translateMove(m));
            }
        } else {
            status = data_.translateStatus(element.getElement());
        }
        if (element.isIncrementNumberOfRounds()) {
            endRoundRank = element.getNumberIncrement();
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
            return;
        }
        EffectEndRound effect_ = getEffect(index);
        if (effect_ == null) {
            endRoundRank = element.getNumberIncrement();
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
            return;
        }
        effect = effect_;
        endRoundRank = element.getNumberIncrement();
        StringList reasons_;
        reasons_ = new StringList();
        reasonsEndRound = reasons_;
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getFailEndRound(),getLanguage());
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
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
    public String clickMoves(Long _indexOne, Long _indexTwo) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_indexOne.intValue());
        StringList moves_ = StringList.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        getForms().put(MOVE, moves_.get(_indexTwo.intValue()));
        return MOVE;
    }
    public String getTrMoves(Long _indexTwo) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get((int) index);
        StringList moves_ = StringList.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        return translatedMoves_.getVal(moves_.get(_indexTwo.intValue()));
    }
    public String clickMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(MOVE, element_.getElement());
        return MOVE;
    }
    public String clickAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(ABILITY, element_.getElement());
        return ABILITY;
    }
    public String clickItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(ITEM, element_.getElement());
        return ITEM;
    }
    public String clickStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index.intValue());
        getForms().put(STATUS, element_.getElement());
        return STATUS;
    }

    private StringList getFailEndRoundReasons() {
        EffectEndRound effect_ = effect;
        return getReasons(effect_.getFailEndRound());
    }

    public String getMove() {
        return move;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public String getAbility() {
        return ability;
    }

    public String getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public StringList getMoves() {
        return moves;
    }

    public int getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
    }

}