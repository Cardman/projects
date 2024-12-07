package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.maths.*;

public final class TypeDamageBoostTechnicalCopier implements AbsTechnicalCopier<TypeDamageBoost>{
    public TypeDamageBoost copy(TypeDamageBoost _e){
        return new TypeDamageBoost(_e.getType(), new Rate(_e.getBoost()));
    }
}
