package aiki.beans;

import aiki.game.fight.util.*;
import code.util.*;

public class BeanChgMovesAbilities implements IntBeanChgMovesAbilities {

    private StringList moves = new StringList();
    private StringList abilities = new StringList();

    @Override
    public MovesAbilities genericValue() {
        return valEvo();
    }

    @Override
    public MovesAbilities valEvo() {
        return new MovesAbilities(moves,abilities);
    }

    @Override
    public void valEvo(MovesAbilities _v) {
        moves = _v.getMoves();
        abilities = _v.getAbilities();
    }
}
