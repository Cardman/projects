package aiki.gui.components.editor;

import code.util.*;

public final class IntListConvertId<E> implements IntListConvert<E, IdList<E>> {
    @Override
    public IdList<E> fromList(CustList<E> _ls) {
        return new IdList<E>(_ls);
    }

    @Override
    public CustList<E> toList(IdList<E> _ls) {
        return _ls;
    }
}
