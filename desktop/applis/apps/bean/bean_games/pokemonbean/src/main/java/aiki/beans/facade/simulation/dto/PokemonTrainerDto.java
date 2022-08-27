package aiki.beans.facade.simulation.dto;
import aiki.map.pokemon.PkTrainer;
import code.util.StringList;

public final class PokemonTrainerDto {
    private final PkTrainer pkTrainer = new PkTrainer();
    private int index;
    public PokemonTrainerDto() {
        pkTrainer.setMoves(new StringList());
    }

    public static PokemonTrainerDto fromPokemonTrainer(PkTrainer _pk) {
        PokemonTrainerDto pk_;
        pk_ = new PokemonTrainerDto();
        pk_.getPkTrainer().setName(_pk.getName());
        pk_.getPkTrainer().setLevel(_pk.getLevel());
        pk_.getPkTrainer().setAbility(_pk.getAbility());
        pk_.getPkTrainer().setItem(_pk.getItem());
        pk_.getPkTrainer().setGender(_pk.getGender());
        pk_.getPkTrainer().setMoves(new StringList(_pk.getMoves()));
        return pk_;
    }

    public PkTrainer toPokemonTrainer() {
        PkTrainer pk_;
        pk_ = new PkTrainer();
        pk_.setName(pkTrainer.getName());
        pk_.setLevel(pkTrainer.getLevel());
        pk_.setAbility(pkTrainer.getAbility());
        pk_.setItem(pkTrainer.getItem());
        pk_.setGender(pkTrainer.getGender());
        pk_.setMoves(new StringList(pkTrainer.getMoves()));
        return pk_;
    }

    public PkTrainer getPkTrainer() {
        return pkTrainer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}