package aiki.beans;

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
    public IntBeanChgStringList newStringList(AbsMap<String, String> _map) {
        return new BeanChgStringList();
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
        return new BeanChgActivityOfMove();
    }

    @Override
    public IntBeanChgChoiceOfEvolutionAndMoves newChoice(AbsMap<String,String> _pk,AbsMap<String,String> _mv,AbsMap<String,String> _ab) {
        return new BeanChgChoiceOfEvolutionAndMoves();
    }
}
