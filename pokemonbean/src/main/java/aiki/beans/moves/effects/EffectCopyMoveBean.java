package aiki.beans.moves.effects;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
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
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesNotToBeCopied_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesNotToBeCopied = movesNotToBeCopied_;
        displayName = translatedMoves_.getVal(getMove());
        defaultMove = data_.getDefaultMove();
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
            movesTransforming_.sortElts(new ComparatorTrStrings(translatedMoves_));
            movesTransforming = movesTransforming_;
        } else {
            movesTransforming = new StringList();
        }
    }
    public boolean copyMoveForUser() {
        return copyingMoveForUser > 0;
    }
    public String clickMove(Long _index) {
        String move_ = movesNotToBeCopied.get(_index.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }
    public String getTrMove(Long _index) {
        String move_ = movesNotToBeCopied.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveTrans(Long _index) {
        String move_ = movesTransforming.get(_index.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }
    public String getTrMoveTrans(Long _index) {
        String move_ = movesTransforming.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickDefaultMove() {
        getForms().put(MOVE, defaultMove);
        return MOVE;
    }
    public String getTrDefaultMove() {
        DataBase data_ = (DataBase) getDataBase();
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