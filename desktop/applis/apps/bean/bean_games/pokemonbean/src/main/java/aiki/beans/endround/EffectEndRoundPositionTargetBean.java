package aiki.beans.endround;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public class EffectEndRoundPositionTargetBean extends EffectEndRoundBean {

    private CustList<TranslatedKey> movesSameCategory;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        movesSameCategory = listTrStringsMv(moves(),getFacade());
    }

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_POSITIONTARGET,MessagesDataEndroundPositiontarget.M_P_9_EFFECT);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,movesSameCategory,MessagesPkBean.ENDROUND_POSITIONTARGET,MessagesDataEndroundPositiontarget.M_P_9_ANTICIPE);
    }

    private StringList moves() {
        DataBase data_ = getDataBase();
        StringList moves_;
        moves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectEndRoundPositionTargetRelation) {
                    moves_.add(m);
                }
            }
        }
        moves_.removeDuplicates();
        return moves_;
    }

    public CustList<TranslatedKey> getMovesSameCategory() {
        return movesSameCategory;
    }

    public String getTrTargetRelationMove(int _index) {
        return movesSameCategory.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_;
//        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(movesSameCategory.get(_index));
    }

    public String clickTargetRelationMove(int _index) {
        return tryRedirect(movesSameCategory.get(_index));
    }
}