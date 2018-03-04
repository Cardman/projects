package aiki.game;
import aiki.DataBase;
import aiki.comments.Comment;
import aiki.exceptions.GameLoadException;
import aiki.map.pokemon.PokemonPlayer;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@RwXml
public final class HostPokemonDuo {

    private PokemonPlayer firstPokemon;

    private PokemonPlayer secondPokemon;

    @CheckedData
    private int nbSteps;

    public void validate(DataBase _data) {
        if (isFree()) {
            if (nbSteps != 0) {
                throw new GameLoadException();
            }
            return;
        }
        firstPokemon.validate(_data);
//        if (!firstPokemon.isValid(_data)) {
//            throw new GameLoadException();
//        }
        secondPokemon.validate(_data);
//        if (!secondPokemon.isValid(_data)) {
//            throw new GameLoadException();
//        }
        firstPokemon.fullHeal(_data);
        secondPokemon.fullHeal(_data);
        if (nbSteps < 0) {
            throw new GameLoadException();
        }
        if (Game.canStoreThesePokemonToHost(new Comment(), firstPokemon, secondPokemon, _data)) {
            return;
        }
//        PokemonData fPkFirst_ = _data.getPokemon(firstPokemon.getName());
//        if (fPkFirst_.getEggGroups().containsStr(_data.constNotNum(DataBase.DEFAULT_EGG_GROUP))) {
//            return true;
//        }
//        List<Gender> gendersWithSex_ = Gender.getGendersWithSex();
//        if (gendersWithSex_.containsObj(firstPokemon.getGender())) {
//            PokemonData fPk_ = _data.getPokemon(secondPokemon.getName());
//            if (gendersWithSex_.containsObj(secondPokemon.getGender())) {
//                //common group
//                boolean vide_=true;
//                for(String e:fPkFirst_.getEggGroups()){
//                    if(fPk_.getEggGroups().containsObj(e)){
//                        vide_=false;
//                        break;
//                    }
//                }
//                if (vide_) {
//                    return false;
//                }
//                if (firstPokemon.getGender() == secondPokemon.getGender()) {
//                    return false;
//                }
//                return true;
//            }
//            if (!fPk_.getEggGroups().containsStr(_data.constNotNum(DataBase.DEFAULT_EGG_GROUP))) {
//                return false;
//            }
//            return true;
//        }
//        PokemonData fPk_ = _data.getPokemon(secondPokemon.getName());
//        if (!fPk_.getEggGroups().containsStr(_data.constNotNum(DataBase.DEFAULT_EGG_GROUP))) {
//            return false;
//        }
//        return true;
        throw new GameLoadException();
    }
    public boolean isFree() {
        return firstPokemon.hasJustBeenCreated() && secondPokemon.hasJustBeenCreated();
    }
    public PokemonPlayer getFirstPokemon() {
        return firstPokemon;
    }

    public void setFirstPokemon(PokemonPlayer _firstPokemon) {
        firstPokemon = _firstPokemon;
    }

    public PokemonPlayer getSecondPokemon() {
        return secondPokemon;
    }

    public void setSecondPokemon(PokemonPlayer _secondPokemon) {
        secondPokemon = _secondPokemon;
    }

    public int getNbSteps() {
        return nbSteps;
    }

    public void setNbSteps(int _nbSteps) {
        nbSteps = _nbSteps;
    }

}
