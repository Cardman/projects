package aiki.game.fight;
import code.maths.Rate;
import aiki.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.PkTrainer;

public class PseudoFoeFighter extends PseudoFighter {

    private boolean fought;

    public PseudoFoeFighter(PkTrainer _pokemon) {
        super(_pokemon.getName(), _pokemon.getLevel());
    }

    Rate pointsFoe(byte _mult,Difficulty _diff, DataBase _import) {
        PokemonData fPk_ = _import.getPokemon(getName());
        Rate points_=new Rate(fPk_.getExpRate()*getLevel()*_mult);
        points_.multiplyBy(_diff.getWinTrainerExp());
        points_.multiplyBy(_diff.getRateWinningExpPtsFight());
        return points_;
    }

    public boolean isFought() {
        return fought;
    }

    public void setFought(boolean _fought) {
        fought = _fought;
    }
}
