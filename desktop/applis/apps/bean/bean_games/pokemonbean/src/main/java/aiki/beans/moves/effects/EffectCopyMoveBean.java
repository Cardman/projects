package aiki.beans.moves.effects;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectCopyFighter;
import aiki.fight.moves.effects.EffectCopyMove;
import code.util.StringList;
import code.util.StringMap;


public class EffectCopyMoveBean extends EffectBean {
    private short copyingMoveForUser;
    private boolean copyingMoveForUserDef;
    private StringList movesNotToBeCopied;
    private StringList movesTransforming;
    private String displayName;
    private String defaultMove;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCopyMove effect_ = (EffectCopyMove) getEffect();
        copyingMoveForUser = effect_.getCopyingMoveForUser();
        copyingMoveForUserDef = effect_.getCopyingMoveForUserDef();
        StringList movesNotToBeCopied_;
        movesNotToBeCopied_ = new StringList();
        for (String m: effect_.getMovesNotToBeCopied()) {
            movesNotToBeCopied_.add(m);
        }
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesNotToBeCopied_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesNotToBeCopied = movesNotToBeCopied_;
        displayName = translatedMoves_.getVal(getMove());
        defaultMove = data_.getDefMove();
        if (copyingMoveForUserDef) {
            StringList movesTransforming_;
            movesTransforming_ = new StringList();
            for (String m: data_.getMoves().getKeys()) {
                MoveData move_ = data_.getMove(m);
                for (Effect e: move_.getEffects()) {
                    if (!(e instanceof EffectCopyFighter)) {
                        continue;
                    }
                    movesTransforming_.add(m);
                }
            }
            movesTransforming_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            movesTransforming = movesTransforming_;
        } else {
            movesTransforming = new StringList();
        }
    }
    public boolean copyMoveForUser() {
        return copyingMoveForUser > 0;
    }
    public String clickMove(int _index) {
        String move_ = movesNotToBeCopied.get(_index);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String getTrMove(int _index) {
        String move_ = movesNotToBeCopied.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveTrans(int _index) {
        String move_ = movesTransforming.get(_index);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String getTrMoveTrans(int _index) {
        String move_ = movesTransforming.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickDefaultMove() {
        getForms().put(CST_MOVE, defaultMove);
        return CST_MOVE;
    }
    public String getTrDefaultMove() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(defaultMove);
    }

    public String getDisplayName() {
        return displayName;
    }

    public short getCopyingMoveForUser() {
        return copyingMoveForUser;
    }

    public boolean getCopyingMoveForUserDef() {
        return copyingMoveForUserDef;
    }

    public StringList getMovesTransforming() {
        return movesTransforming;
    }

    public StringList getMovesNotToBeCopied() {
        return movesNotToBeCopied;
    }
}