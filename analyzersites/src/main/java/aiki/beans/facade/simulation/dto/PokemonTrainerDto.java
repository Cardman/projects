package aiki.beans.facade.simulation.dto;
import code.bean.Accessible;
import code.util.StringList;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.enums.Gender;

public final class PokemonTrainerDto {

    /***/
    @Accessible
    private String name;

    /***/
    @Accessible
    private short level;

    /***/
    @Accessible
    private Gender gender;

    /**non modifiable une fois affecte a l'objet.*/
    @Accessible
    private String ability;

    /**si la chaine de caractere est vide alors le pokemon ne porte pas d'objet, sinon cette chaine vaut le nom de l'objet. */
    @Accessible
    private String item;

    @Accessible
    private StringList moves = new StringList();

    @Accessible
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
        pk_.setLevel(level);
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

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
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
