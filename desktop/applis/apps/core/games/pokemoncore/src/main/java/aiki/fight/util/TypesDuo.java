package aiki.fight.util;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class TypesDuo implements Displayable {

    private static final char SEPARATOR = ';';

    private String damageType;

    private String pokemonType;

    public TypesDuo(){
    }

    public TypesDuo(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        damageType = elements_.first();
        pokemonType = elements_.last();
    }

    public TypesDuo(String _damageType, String _pokemonType) {
        damageType = _damageType;
        pokemonType = _pokemonType;
    }

    
    public static TypesDuo newTypesDuo(String _string) {
        return new TypesDuo(_string);
    }

    public boolean eq(TypesDuo _obj) {
        if (!StringUtil.quickEq(damageType, _obj.damageType)) {
            return false;
        }
        return StringUtil.quickEq(pokemonType, _obj.pokemonType);
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

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(damageType);
        str_.append(SEPARATOR);
        str_.append(pokemonType);
        return str_.toString();
    }
}
