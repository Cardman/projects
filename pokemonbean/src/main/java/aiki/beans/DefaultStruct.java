package aiki.beans;

import aiki.beans.effects.ComboDto;
import aiki.beans.facade.dto.MoveLine;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.map.characters.Ally;
import aiki.map.characters.Person;
import aiki.map.levels.AreaApparition;
import aiki.map.places.Place;
import aiki.map.pokemon.Pokemon;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.RealInstanceStruct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.Bytes;
import code.util.Ints;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleIterable;

public final class DefaultStruct implements RealInstanceStruct {

    private final Object instance;

    private final String className;

    private DefaultStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(StringList _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(Rate _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(LgInt _instance, String _className) {
        instance = _instance;
        className = _className;
    }
    public DefaultStruct(Displayable _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(SimpleIterable _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public DefaultStruct(SimpleEntries _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public static DefaultStruct newInstance(Object _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(MoveLine _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(Ally _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(Person _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(AreaApparition _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(Pokemon _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(Place _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(ComboDto _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(EffectWhileSending _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newInstance(EffectPartnerStatus _instance, String _className) {
        return new DefaultStruct(_instance, _className);
    }
    public static DefaultStruct newListInt(Ints _instance, String _className) {
        return new DefaultStruct((Object)_instance, _className);
    }
    public static DefaultStruct newListByte(Bytes _instance, String _className) {
        return new DefaultStruct((Object)_instance, _className);
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

}
