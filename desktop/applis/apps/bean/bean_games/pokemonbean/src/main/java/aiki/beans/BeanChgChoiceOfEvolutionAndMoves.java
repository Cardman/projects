package aiki.beans;

import aiki.db.*;
import aiki.game.fight.*;
import code.util.*;

public class BeanChgChoiceOfEvolutionAndMoves implements IntBeanChgChoiceOfEvolutionAndMoves {

    private String name = DataBase.EMPTY_STRING;

    private StringList keptMoves = new StringList();

    private String ability = DataBase.EMPTY_STRING;

    @Override
    public ChoiceOfEvolutionAndMoves genericValue() {
        return valueChoice();
    }

    @Override
    public ChoiceOfEvolutionAndMoves valueChoice() {
        ChoiceOfEvolutionAndMoves ac_ = new ChoiceOfEvolutionAndMoves();
        ac_.setName(name);
        ac_.setKeptMoves(keptMoves);
        ac_.setAbility(ability);
        return ac_;
    }

    @Override
    public void valueChoice(ChoiceOfEvolutionAndMoves _v) {
        name = _v.getName();
        keptMoves = _v.getKeptMoves();
        ability = _v.getAbility();
    }
}
