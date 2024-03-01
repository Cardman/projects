package cards.gui.animations;

import cards.consts.DisplayingCommon;
import code.util.IdList;

public interface IntSortingSummedTwoHands<T> {
    IdList<T> sorted(IdList<T> _one, IdList<T> _two, DisplayingCommon _dis);
}
