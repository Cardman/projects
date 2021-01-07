package aiki.beans.facade.simulation.dto;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;

public final class PokemonTrainerDto {
    private String name;
    private int level;
    private Gender gender;
    private String ability;
    private String item;
    private StringList moves = new StringList();
    private int index;

    public static PokemonTrainerDto fromPokemonTrainer(PkTrainer _pk) {
        PokemonTrainerDto pk_;
        pk_ = new PokemonTrainerDto();
        pk_.setName(_pk.getName());
        pk_.setLevel(_pk.getLevel());
        pk_.setAbility(_pk.getAbility());
        pk_.setItem(_pk.getItem());
        pk_.setGender(_pk.getGender());
        pk_.setMoves(new StringList(_pk.getMoves()));
        return pk_;
    }

    public PkTrainer toPokemonTrainer() {
        PkTrainer pk_;
        pk_ = new PkTrainer();
        pk_.setName(name);
        pk_.setLevel((short) level);
        pk_.setAbility(ability);
        pk_.setItem(item);
        pk_.setGender(gender);
        pk_.setMoves(new StringList(moves));
        return pk_;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int _level) {
        level = _level;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }

    public StringList getMoves() {
        return moves;
    }

    public void setMoves(StringList _moves) {
        moves = _moves;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}