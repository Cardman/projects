package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

public interface AbsCustCellRenderGene<T> {
   AbstractImage getListCellRendererComponent(
           int _index, T _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors);

}
