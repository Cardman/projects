package aiki.beans;

import aiki.game.fight.util.*;
import code.util.*;

public interface IntBeanGeneInput {
    IntBeanChgBool newBool();
    IntBeanChgLong newLong();
    IntBeanChgRate newRate();
    IntBeanChgLgInt newLgInt();
    IntBeanChgString newString(AbsMap<String,String> _map);
    IntBeanChgList<String> newStringList(AbsMap<String,String> _map);
    IntBeanChgList<Integer> newInts(AbsMap<Integer,String> _map);
    IntBeanChgInt newInt(AbsMap<Integer,String> _map);
    IntBeanChgString newText();
    IntBeanChgSubmit newSubmit(String _text);
    IntBeanChgActivityOfMove newAc();
    IntBeanChgChoiceOfEvolutionAndMoves newChoice(AbsMap<String,String> _pk,AbsMap<String,String> _mv,AbsMap<String,String> _ab);
    IntBeanChgMoveTarget newMt(AbsMap<MoveTarget,String> _pk);
}
