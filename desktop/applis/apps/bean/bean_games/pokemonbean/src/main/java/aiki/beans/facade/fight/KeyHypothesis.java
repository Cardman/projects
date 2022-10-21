package aiki.beans.facade.fight;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import aiki.game.fight.TeamPosition;
import code.maths.Rate;
import code.util.comparators.ComparatorBoolean;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class KeyHypothesis {

    private static final String SEPARATOR = " ";

    private final String playerPokemon;

    private final int numberPlayer;

    private final String move;

    private final String targetPokemon;

    private final boolean belongsToUser;

    private final int numberTarget;

    private Rate damage;
    private Rate damageSecond;

    public KeyHypothesis(FacadeGame _facade, TeamPosition _playerPk, String _move, TeamPosition _targetPk) {
        DataBase data_ = _facade.getData();
        Fight fight_ = _facade.getGame().getFight();
        String name_ = fight_.getFighter(_playerPk).getName();
        playerPokemon = data_.translatePokemon(name_);
        numberPlayer = _playerPk.getPosition();
        move = data_.translateMove(_move);
        name_ = fight_.getFighter(_targetPk).getName();
        targetPokemon = data_.translatePokemon(name_);
        belongsToUser = NumberUtil.eq(_targetPk.getTeam(), Fight.CST_PLAYER);
        numberTarget = _targetPk.getPosition();
    }

    public String getPlayerPokemon() {
        StringBuilder str_ = new StringBuilder(playerPokemon);
        str_.append(SEPARATOR);
        str_.append(numberPlayer);
        return str_.toString();
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public String getMove() {
        return move;
    }

    public String getTargetPokemon() {
        StringBuilder str_ = new StringBuilder(targetPokemon);
        str_.append(SEPARATOR);
        str_.append(numberTarget);
        return str_.toString();
    }

    public boolean isBelongsToUser() {
        return belongsToUser;
    }

    public int getNumberTarget() {
        return numberTarget;
    }

    public Rate getDamage() {
        return damage;
    }

    public void setDamage(Rate _damage) {
        damage = _damage;
    }

    public Rate getDamageSecond() {
        return damageSecond;
    }

    public void setDamageSecond(Rate _d) {
        this.damageSecond = _d;
    }
//    public boolean eq(KeyHypothesis _g) {
//        int res_ = StringUtil.compareStrings(playerPokemon,_g.getPlayerPokemon());
//        if (res_ != 0) {
//            return false;
//        }
//        res_ = NumberUtil.compareLg(numberPlayer, _g.numberPlayer);
//        if (res_ != 0) {
//            return false;
//        }
//        res_ = StringUtil.compareStrings(move,_g.getMove());
//        if (res_ != 0) {
//            return false;
//        }
//        res_ = StringUtil.compareStrings(targetPokemon,_g.getTargetPokemon());
//        if (res_ != 0) {
//            return false;
//        }
////        res_ = Boolean.compare(belongsToUser, o_.belongsToUser);
//        res_ = ComparatorBoolean.cmp(belongsToUser, _g.belongsToUser);
//        if (res_ != 0) {
//            return false;
//        }
//        res_ = NumberUtil.compareLg(numberTarget, _g.numberTarget);
//        return res_ == 0;
//    }

    public int cmp(KeyHypothesis _o) {
        int res_ = StringUtil.compareStrings(playerPokemon,_o.playerPokemon);
        if (res_ != 0) {
            return res_;
        }
        res_ = NumberUtil.compareLg(numberPlayer, _o.numberPlayer);
        if (res_ != 0) {
            return res_;
        }
        res_ = StringUtil.compareStrings(move,_o.move);
        if (res_ != 0) {
            return res_;
        }
        res_ = ComparatorBoolean.cmp(belongsToUser, _o.belongsToUser);
        if (res_ != 0) {
            return res_;
        }
        res_ = StringUtil.compareStrings(targetPokemon,_o.targetPokemon);
        if (res_ != 0) {
            return res_;
        }
        return NumberUtil.compareLg(numberTarget, _o.numberTarget);
////        res_ = Boolean.compare(belongsToUser, _o.belongsToUser);
//        res_ = ComparatorBoolean.cmp(belongsToUser, _o.belongsToUser);
//        if (res_ != 0) {
//            return res_;
//        }
//        return NumberUtil.compareLg(numberTarget, _o.numberTarget);
    }

//    public String display() {
//        StringBuilder str_ = new StringBuilder(getPlayerPokemon());
//        str_.append(SEPARATOR);
//        str_.append(getMove());
//        str_.append(SEPARATOR);
//        str_.append(getTargetPokemon());
//        str_.append(SEPARATOR);
//        str_.append(isBelongsToUser());
//        str_.append(SEPARATOR);
//        str_.append(getNumberTarget());
//        return str_.toString();
//    }


}