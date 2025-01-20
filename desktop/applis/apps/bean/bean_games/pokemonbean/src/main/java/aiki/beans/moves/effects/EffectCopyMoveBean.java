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
    private long copyingMoveForUser;
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesNotToBeCopied = movesNotToBeCopied(effect_);
        displayName = translatedMoves_.getVal(getMove());
        defaultMove = data_.getDefMove();
        StringList movesTransforming_ = movesTransforming(data_);
        movesTransforming_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesTransforming = movesTransforming_;
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
    private StringList movesNotToBeCopied(int _eff) {
        EffectCopyMove effect_ = (EffectCopyMove) getEffect(_eff);
        return movesNotToBeCopied(effect_);
    }

    private StringList movesNotToBeCopied(EffectCopyMove _eff) {
        StringList movesNotToBeCopied_;
        movesNotToBeCopied_ = new StringList();
        for (String m: _eff.getMovesNotToBeCopied()) {
            movesNotToBeCopied_.add(m);
        }
        DataBase data_ = getDataBase();
        movesNotToBeCopied_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return movesNotToBeCopied_;
    }

    public boolean copyMoveForUser() {
        return copyingMoveForUser > 0;
    }
    public String clickMove(int _eff, int _index) {
        return tryRedirectMv(movesNotToBeCopied(_eff).get(_index));
    }
    public String getTrMove(int _index) {
        String move_ = movesNotToBeCopied.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveTrans(int _index) {
        String move_ = movesTransforming.get(_index);
        return tryRedirectMv(move_);
    }
    public String getTrMoveTrans(int _index) {
        String move_ = movesTransforming.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickDefaultMove() {
        return tryRedirectMv(defaultMove);
    }
    public String getTrDefaultMove() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(defaultMove);
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

    public StringList getMovesTransforming() {
        return movesTransforming;
    }

    public StringList getMovesNotToBeCopied() {
        return movesNotToBeCopied;
    }
}