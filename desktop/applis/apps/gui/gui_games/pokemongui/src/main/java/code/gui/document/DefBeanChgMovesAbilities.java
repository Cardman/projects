package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.util.*;
import code.gui.*;
import code.util.*;

public final class DefBeanChgMovesAbilities extends BeanChgMovesAbilities {

    private final GeneComponentModelLs<String> moves;
    private final GeneComponentModelLs<String> abilities;

    public DefBeanChgMovesAbilities(GeneComponentModelLs<String> _m, GeneComponentModelLs<String> _a) {
        this.moves = _m;
        this.abilities = _a;
    }

    @Override
    public MovesAbilities valEvo() {
        return new MovesAbilities(new StringList(moves.tryRet()),new StringList(abilities.tryRet()));
    }

    @Override
    public void valEvo(MovesAbilities _v) {
        moves.setupValue(_v.getMoves());
        abilities.setupValue(_v.getAbilities());
    }

}
