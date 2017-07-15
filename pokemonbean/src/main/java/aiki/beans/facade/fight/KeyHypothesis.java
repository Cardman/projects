package aiki.beans.facade.fight;
import code.maths.Rate;
import code.util.Numbers;
import code.util.comparators.ComparatorBoolean;
import code.util.ints.Cmp;
import aiki.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import aiki.game.fight.TeamPosition;


public final class KeyHypothesis implements Cmp<KeyHypothesis> {

    private static final String SEPARATOR = " ";

    private String playerPokemon;

    private int numberPlayer;

    private String move;

    private String targetPokemon;

    private boolean belongsToUser;

    private int numberTarget;

    private Rate damage;

    public KeyHypothesis(FacadeGame _facade, TeamPosition _playerPk, String _move, TeamPosition _targetPk) {
        DataBase data_ = _facade.getData();
        Fight fight_ = _facade.getGame().getFight();
        String name_ = fight_.getFighter(_playerPk).getName();
        playerPokemon = data_.translatePokemon(name_);
        numberPlayer = _playerPk.getPosition();
        move = data_.translateMove(_move);
        name_ = fight_.getFighter(_targetPk).getName();
        targetPokemon = data_.translatePokemon(name_);
        belongsToUser = Numbers.eq(_targetPk.getTeam(), Fight.PLAYER);
        numberTarget = _targetPk.getPosition();
    }

    @Override
    public String toString() {
        return getPlayerPokemon()+SEPARATOR+getMove()+SEPARATOR+getTargetPokemon()+SEPARATOR+isBelongsToUser()+SEPARATOR+getNumberTarget();
    }

    public String getPlayerPokemon() {
        return playerPokemon+SEPARATOR+numberPlayer;
    }

    public String getMove() {
        return move;
    }

    public String getTargetPokemon() {
        return targetPokemon+SEPARATOR;
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

    @Override
    public boolean eq(KeyHypothesis _g) {
        int res_ = playerPokemon.compareTo(_g.getPlayerPokemon());
        if (res_ != 0) {
            return false;
        }
        res_ = Numbers.compare(numberPlayer, _g.numberPlayer);
        if (res_ != 0) {
            return false;
        }
        res_ = move.compareTo(_g.getMove());
        if (res_ != 0) {
            return false;
        }
        res_ = targetPokemon.compareTo(_g.getTargetPokemon());
        if (res_ != 0) {
            return false;
        }
//        res_ = Boolean.compare(belongsToUser, o_.belongsToUser);
        res_ = ComparatorBoolean.cmp(belongsToUser, _g.belongsToUser);
        if (res_ != 0) {
            return false;
        }
        res_ = Numbers.compare(numberTarget, _g.numberTarget);
        return res_ == 0;
    }

    @Override
    public int cmp(KeyHypothesis _o) {
        int res_ = playerPokemon.compareTo(_o.getPlayerPokemon());
        if (res_ != 0) {
            return res_;
        }
        res_ = Numbers.compare(numberPlayer, _o.numberPlayer);
        if (res_ != 0) {
            return res_;
        }
        res_ = move.compareTo(_o.getMove());
        if (res_ != 0) {
            return res_;
        }
        res_ = targetPokemon.compareTo(_o.getTargetPokemon());
        if (res_ != 0) {
            return res_;
        }
//        res_ = Boolean.compare(belongsToUser, _o.belongsToUser);
        res_ = ComparatorBoolean.cmp(belongsToUser, _o.belongsToUser);
        if (res_ != 0) {
            return res_;
        }
        return Numbers.compare(numberTarget, _o.numberTarget);
    }


}
