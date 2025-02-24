package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.*;
import code.scripts.pages.aiki.MessagesDataEffbatonpass;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public class EffectBatonPassBean extends EffectBean {
    private CustList<TranslatedKey> moves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        StringList moves_;
        moves_ = new StringList();
        moves_.addAllElts(data_.getMovesEffectIndiv());
        moves_.addAllElts(data_.getMovesEffectProt());
        moves_.addAllElts(data_.getMovesEffEndRoundIndiv());
        moves_.addAllElts(data_.getMovesEffectUnprot());
        moves_.addAllElts(data_.getTrappingMoves());
        moves_.removeDuplicates();
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        moves = listTrStringsMv(moves_,getFacade());
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_BATONPASS,MessagesDataEffbatonpass.M_P_39_EFFECT);
    }

    @Override
    public void buildSubEff() {
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getMoves(), MessagesPkBean.EFF_BATONPASS, MessagesDataEffbatonpass.M_P_39_EFFECT_2);
    }

    public String getTrMove(int _index) {
        return moves.get(_index).getTranslation();
//        String move_ = moves.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }
    public String clickMove(int _index) {
        return tryRedirect(moves.get(_index));
    }

    public CustList<TranslatedKey> getMoves() {
        return moves;
    }
}