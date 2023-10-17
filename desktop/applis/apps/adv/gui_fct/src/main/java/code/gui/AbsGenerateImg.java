package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

public interface AbsGenerateImg<T> {
    AbstractImage generateImg(MetaFont _lab, int _index, T _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored);
}
