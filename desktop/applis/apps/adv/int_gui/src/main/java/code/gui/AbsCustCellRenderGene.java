package code.gui;

import code.gui.images.AbstractImage;

public interface AbsCustCellRenderGene<T> {
   AbstractImage getListCellRendererComponent(
                                     int _index, T _info, boolean _isSelected, boolean _cellHasFocus, AbsPreparedLabel _lab, ColorsGroupList _colors);

}
