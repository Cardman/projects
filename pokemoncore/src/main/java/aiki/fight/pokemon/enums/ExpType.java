package aiki.fight.pokemon.enums;
import code.serialize.CheckedData;
import code.util.StringList;


@CheckedData
public enum ExpType {
    E, F, L, M, P, R;
    public static ExpType getExpTypeByName(String _env) {
        for (ExpType e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return null;
    }
}
