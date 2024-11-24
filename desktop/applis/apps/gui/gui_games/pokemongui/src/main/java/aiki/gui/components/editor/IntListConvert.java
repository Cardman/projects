package aiki.gui.components.editor;

import code.util.*;

public interface IntListConvert<E,F> {
    F fromList(CustList<E> _ls);
    CustList<E> toList(F _ls);
}
