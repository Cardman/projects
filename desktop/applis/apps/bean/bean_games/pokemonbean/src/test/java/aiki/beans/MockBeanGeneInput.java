package aiki.beans;

import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.util.*;
import aiki.map.pokemon.enums.*;
import code.util.*;

public final class MockBeanGeneInput implements IntBeanGeneInput {
    @Override
    public IntBeanChgBool newBool() {
        return new BeanChgBool();
    }

    @Override
    public IntBeanChgLong newLong() {
        return new BeanChgLong();
    }

    @Override
    public IntBeanChgRate newRate() {
        return new BeanChgRate();
    }

    @Override
    public IntBeanChgLgInt newLgInt() {
        return new BeanChgLgInt();
    }

    @Override
    public IntBeanChgString newString(AbsMap<String,String> _map) {
        return new BeanChgString();
    }

    @Override
    public IntBeanChgList<String> newStringList(AbsMap<String, String> _map) {
        return new BeanChgList<String>();
    }

    @Override
    public IntBeanChgList<Integer> newInts(AbsMap<Integer, String> _map) {
        return new BeanChgList<Integer>();
    }

    @Override
    public IntBeanChgInt newInt(AbsMap<Integer, String> _map) {
        return new BeanChgInt();
    }

    @Override
    public IntBeanChgString newText() {
        return new BeanChgString();
    }

    @Override
    public IntBeanChgSubmit newSubmit(String _text) {
        return new AbsBeanChgSubmit();
    }

    @Override
    public IntBeanChgActivityOfMove newAc() {
        return newAc(true);
    }

    @Override
    public IntBeanChgActivityOfMove newAc(boolean _incrCount) {
        return new BeanChgActivityOfMove(_incrCount);
    }

    @Override
    public IntBeanChgAffectedMove newAff(AbsMap<String, String> _mv) {
        return new BeanChgAffectedMove();
    }

    @Override
    public IntBeanChgCopiedMove newCp(AbsMap<String, String> _mv) {
        return new BeanChgCopiedMove();
    }

    @Override
    public IntBeanChgStackOfUses newStack() {
        return new BeanChgStackOfUses();
    }

    @Override
    public IntBeanChgUsesOfMove newUse() {
        return new BeanChgUsesOfMove();
    }

    @Override
    public IntBeanChgAnticipation newAnt(AbsMap<TargetCoords, String> _pk) {
        return new BeanChgAnticipation();
    }

    @Override
    public IntBeanChgTargetCoords newTc(AbsMap<TargetCoords, String> _pk) {
        return new BeanChgTargetCoords();
    }

    @Override
    public IntBeanChgChoiceOfEvolutionAndMoves newChoice(AbsMap<String,String> _pk,AbsMap<String,String> _mv,AbsMap<String,String> _ab) {
        return new BeanChgChoiceOfEvolutionAndMoves();
    }

    @Override
    public IntBeanChgMovesAbilities newEvo(AbsMap<String, String> _mv, AbsMap<String, String> _ab) {
        return new BeanChgMovesAbilities();
    }

    @Override
    public IntBeanChgMoveTarget newMt(AbsMap<MoveTarget, String> _pk) {
        return new BeanChgMoveTarget();
    }

    @Override
    public IntBeanChgKindAction newKa(AbsMap<KindAction, String> _map) {
        return new BeanChgKindAction();
    }

    @Override
    public IntBeanChgGender newGender(AbsMap<Gender, String> _pk) {
        return new BeanChgGender();
    }
}
