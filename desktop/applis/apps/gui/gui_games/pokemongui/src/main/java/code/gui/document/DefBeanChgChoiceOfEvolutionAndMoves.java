package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import code.gui.*;
import code.util.*;

public final class DefBeanChgChoiceOfEvolutionAndMoves extends BeanChgChoiceOfEvolutionAndMoves {

    private final GeneComponentModelElt<String> name;
    private final GeneComponentModelLs<String> keptMoves;

    private final GeneComponentModelElt<String> ability;
    public DefBeanChgChoiceOfEvolutionAndMoves(GeneComponentModelElt<String> _n, GeneComponentModelLs<String> _k, GeneComponentModelElt<String> _a) {
        name = _n;
        keptMoves = _k;
        ability = _a;
    }

    @Override
    public ChoiceOfEvolutionAndMoves valueChoice() {
        ChoiceOfEvolutionAndMoves ac_ = new ChoiceOfEvolutionAndMoves();
        ac_.setName(name.tryRet());
        ac_.setKeptMoves(new StringList(keptMoves.tryRet()));
        ac_.setAbility(ability.tryRet());
        return ac_;
    }

    @Override
    public void valueChoice(ChoiceOfEvolutionAndMoves _v) {
        name.setupValue(_v.getName());
        keptMoves.setupValue(_v.getKeptMoves());
        ability.setupValue(_v.getAbility());
    }

}
