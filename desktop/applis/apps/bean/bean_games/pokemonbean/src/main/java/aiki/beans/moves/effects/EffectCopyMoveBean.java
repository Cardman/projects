package aiki.beans.moves.effects;
import aiki.beans.*;
import aiki.db.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import code.util.*;


public class EffectCopyMoveBean extends EffectBean {
    private long copyingMoveForUser;
    private boolean copyingMoveForUserDef;
    private CustList<TranslatedKey> movesNotToBeCopied;
    private CustList<TranslatedKey> movesTransforming;
    private String displayName;
    private TranslatedKey defaultMove;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCopyMove effect_ = (EffectCopyMove) getEffect();
        copyingMoveForUser = effect_.getCopyingMoveForUser();
        copyingMoveForUserDef = effect_.getCopyingMoveForUserDef();
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesNotToBeCopied = listTrStringsMv(effect_.getMovesNotToBeCopied(),getFacade());
        displayName = translatedMoves_.getVal(getMove());
        defaultMove = buildMv(getFacade(),data_.getDefMove());
        movesTransforming = listTrStringsMv(movesTransforming(data_),getFacade());
    }

    public static StringList movesTransforming(DataBase _data) {
        StringList movesTransforming_;
        movesTransforming_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData move_ = _data.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectCopyFighter)) {
                    continue;
                }
                movesTransforming_.add(m);
            }
        }
        return movesTransforming_;
    }

    public boolean copyMoveForUser() {
        return copyingMoveForUser > 0;
    }
    public String clickMove(int _eff, int _index) {
        return tryRedirect(((EffectCopyMoveBean)getForms().getCurrentBean().get(_eff)).movesNotToBeCopied.get(_index));
    }
    public String getTrMove(int _index) {
        return movesNotToBeCopied.get(_index).getTranslation();
//        String move_ = movesNotToBeCopied.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }
    public String clickMoveTrans(int _index) {
        return tryRedirect(movesTransforming.get(_index));
    }
    public String getTrMoveTrans(int _index) {
        return movesTransforming.get(_index).getTranslation();
//        String move_ = movesTransforming.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }
    public String clickDefaultMove() {
        return tryRedirect(defaultMove);
    }
    public String getTrDefaultMove() {
        return defaultMove.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(defaultMove);
    }

    public TranslatedKey getDefaultMove() {
        return defaultMove;
    }

    public String getDisplayName() {
        return displayName;
    }

    public long getCopyingMoveForUser() {
        return copyingMoveForUser;
    }

    public boolean getCopyingMoveForUserDef() {
        return copyingMoveForUserDef;
    }

    public CustList<TranslatedKey> getMovesTransforming() {
        return movesTransforming;
    }

    public CustList<TranslatedKey> getMovesNotToBeCopied() {
        return movesNotToBeCopied;
    }
}