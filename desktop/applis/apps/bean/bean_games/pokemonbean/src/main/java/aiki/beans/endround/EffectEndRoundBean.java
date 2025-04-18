package aiki.beans.endround;

import aiki.beans.CommonBean;
import aiki.beans.TranslatedKey;
import aiki.db.DataBase;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.EndTurnType;
import aiki.fight.moves.effects.EffectEndRound;
import code.scripts.pages.aiki.MessagesDataEffendround;
import code.scripts.pages.aiki.MessagesDataEndroundEvent;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class EffectEndRoundBean extends CommonBean {
//    static final String END_ROUND_HTML="web/html/endround/eff.html";

    private EffectEndRound effect;
    private int index;

    private EndRoundMainElements element;
    private String move;
    private String ability;
    private String status;
    private String item;
//    private StringList moves;
    private long endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private TranslatedKey translatedKey;
    private CustList<TranslatedKey> translatedKeys;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        element = data_.getEvtEndRound().get(index);
        move = DataBase.EMPTY_STRING;
        ability = DataBase.EMPTY_STRING;
        status = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
        trKey();
        translatedKeys = getForms().getEvtsGroups().get(index);
        if (element.getEndRoundType() == EndTurnType.ATTAQUE) {
            move = translatedKey.getTranslation();
        } else if (element.getEndRoundType() == EndTurnType.CAPACITE) {
            ability = translatedKey.getTranslation();
        } else if (element.getEndRoundType() == EndTurnType.OBJET) {
            item = translatedKey.getTranslation();
        } else if (element.getEndRoundType() == EndTurnType.STATUT) {
            status = translatedKey.getTranslation();
        }
//        move = DataBase.EMPTY_STRING;
//        ability = DataBase.EMPTY_STRING;
//        status = DataBase.EMPTY_STRING;
//        item = DataBase.EMPTY_STRING;
//        moves = new StringList();
//        if (element.getEndRoundType() == EndTurnType.ATTAQUE) {
//            move = data_.translateMove(element.getElement());
//        } else if (element.getEndRoundType() == EndTurnType.CAPACITE) {
//            ability = data_.translateAbility(element.getElement());
//        } else if (element.getEndRoundType() == EndTurnType.OBJET) {
//            item = data_.translateItem(element.getElement());
//        } else if (element.getEndRoundType() == EndTurnType.ATTAQUE_COMBI) {
//            StringList moves_ = StringUtil.splitStrings(element.getElement(), DataBase.SEPARATOR_MOVES);
//            for (String m: moves_) {
//                moves.add(data_.translateMove(m));
//            }
//        } else {
//            status = data_.translateStatus(element.getElement());
//        }
        EffectEndRound effect_ = getEffect(index);
        if (effect_ == null) {
            endRoundRank = element.getNumberIncrement();
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
            return;
        }
        effect = effect_;
        endRoundRank = element.getNumberIncrement();
        reasonsEndRound = CommonBean.getFormattedReasons(data_, effect_.getFailEndRound(), getLanguage());
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getFailEndRound(),getLanguage());
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailEndRound = mapVarsFail_;
    }
    public void buildSub() {
        formatMessageDir(getTranslatedKey());
        for (TranslatedKey f:getTranslatedKeys()) {
            formatMessageDir(f);
        }
        formatMessage(MessagesPkBean.ENDROUND_EVENT, MessagesDataEndroundEvent.M_P_4_RANK,Long.toString(getEndRoundRank()));
        displayStringList(getReasonsEndRound(),MessagesPkBean.EFF_ENDROUND, MessagesDataEffendround.M_P_47_REASONS);
        mapVarsInit(getMapVarsFailEndRound());
    }

    private void trKey() {
        translatedKey = getForms().getEvts().get(getIndex());
    }

    public TranslatedKey getTranslatedKey() {
        return translatedKey;
    }

    protected EffectEndRound getEffect() {
        return effect;
    }

    protected EndRoundMainElements getElement() {
        return element;
    }

    protected EffectEndRound getEffect(int _index) {
        DataBase data_ = getDataBase();
        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index);
        return element_.getEff();
    }
    public String clickMoves(int _indexTwo) {
        return tryRedirect(translatedKeys.get(_indexTwo));
//        DataBase data_ = getDataBase();
//        EndRoundMainElements element_ = data_.getEvtEndRound().get(_indexOne);
//        StringList moves_ = StringUtil.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return tryRedirectMv(moves_.get(_indexTwo));
    }
    public String getTrMoves(int _indexTwo) {
        return translatedKeys.get(_indexTwo).getTranslation();
//        DataBase data_ = getDataBase();
//        EndRoundMainElements element_ = data_.getEvtEndRound().get(index);
//        StringList moves_ = StringUtil.splitStrings(element_.getElement(), DataBase.SEPARATOR_MOVES);
//        StringMap<String> translatedMoves_;
//        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return translatedMoves_.getVal(moves_.get(_indexTwo));
    }
    public String clickMove() {
        return tryRedirect(translatedKey);
//        DataBase data_ = getDataBase();
//        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index);
//        return tryRedirectMv(element_.getElement());
    }
    public String clickAbility() {
        return tryRedirect(translatedKey);
//        DataBase data_ = getDataBase();
//        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index);
//        return tryRedirectAb(element_.getElement());
    }
    public String clickItem() {
        return tryRedirect(translatedKey);
//        DataBase data_ = getDataBase();
//        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index);
//        return tryRedirectIt(element_.getElement());
    }
    public String clickStatus() {
        return tryRedirect(translatedKey);
//        DataBase data_ = getDataBase();
//        EndRoundMainElements element_ = data_.getEvtEndRound().get(_index);
//        return tryRedirectSt(element_.getElement());
    }

    public String getMove() {
        return move;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
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
        StringList trs_ = new StringList();
        for (TranslatedKey e: translatedKeys) {
            trs_.add(e.getTranslation());
        }
        return trs_;
    }

    public CustList<TranslatedKey> getTranslatedKeys() {
        return translatedKeys;
    }

    public long getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
    }

}