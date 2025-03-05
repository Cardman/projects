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
    public IntBeanChgString newString(AbsMap<String,String> _map) {
        return new BeanChgString();
    }

    @Override
    public IntBeanChgString newText() {
        return new BeanChgString();
    }

    @Override
    public IntBeanChgSubmit newSubmit(String _text) {
        return new AbsBeanChgSubmit();
    }

}
