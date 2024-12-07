package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class TypesDuoTechnicalCopier implements AbsTechnicalCopier<TypesDuo>{
    public TypesDuo copy(TypesDuo _e){
        TypesDuo cp_ = new TypesDuo();
        cp_.setDamageType(_e.getDamageType());
        cp_.setPokemonType(_e.getPokemonType());
        return cp_;
    }
}
