package aiki.fight.util;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class TypesDuo implements Equallable<TypesDuo> {

    private static final char SEPARATOR = ';';

    private String damageType;

    private String pokemonType;

    public TypesDuo(){
    }

    public TypesDuo(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        damageType = elements_.first();
        pokemonType = elements_.last();
    }

    public TypesDuo(String _damageType, String _pokemonType) {
        damageType = _damageType;
        pokemonType = _pokemonType;
    }

    @FromAndToString
    public static TypesDuo newTypesDuo(String _string) {
        return new TypesDuo(_string);
    }

    @Override
    public boolean eq(TypesDuo _obj) {
        if (!StringList.quickEq(damageType, _obj.damageType)) {
            return false;
        }
        if (!StringList.quickEq(pokemonType, _obj.pokemonType)) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return damageType+SEPARATOR+pokemonType;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String _damageType) {
        damageType = _damageType;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(String _pokemonType) {
        pokemonType = _pokemonType;
    }
}
