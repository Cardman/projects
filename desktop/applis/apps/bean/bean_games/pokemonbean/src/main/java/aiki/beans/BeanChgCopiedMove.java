package aiki.beans;

import aiki.db.*;
import aiki.game.fight.util.*;

public class BeanChgCopiedMove implements IntBeanChgCopiedMove {

    private String move = DataBase.EMPTY_STRING;
    private long nbTurn;

    @Override
    public CopiedMove genericValue() {
        return valCp();
    }

    @Override
    public CopiedMove valCp() {
        CopiedMove ac_ = new CopiedMove();
        ac_.setMove(move);
        ac_.setPp(nbTurn);
        return ac_;
    }

    @Override
    public void valCp(CopiedMove _v) {
        move = _v.getMove();
        nbTurn = _v.getPp();
    }
}
