package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class GuiBaseUtil {
    private GuiBaseUtil() {

    }

    public static void recalculate(AbsCustComponent _compo) {
        _compo.setSize(_compo.getPreferredSizeValue());
        AbsCustComponent curr_ = _compo;
        while(curr_ != null) {
            curr_.recalculate();
            if (curr_ instanceof AbsScrollPane) {
                ((AbsScrollPane)curr_).recalculateViewport();
            }

            AbsCustComponent child_ = childAt(curr_, 0);
            if (child_ != null) {
                curr_ = child_;
            } else {
                while(curr_ != null) {
                    AbsCustComponent par_ = curr_.getParent();
                    int index_ = indexOf(par_, curr_);
                    AbsCustComponent next_ = childAt(par_, index_ + 1);
                    if (next_ != null) {
                        curr_ = next_;
                        break;
                    }

                    curr_ = par_;
                }
            }
        }
    }

    private static AbsCustComponent childAt(AbsCustComponent _elt, int _index) {
        if (_elt == null) {
            return null;
        }
        CustList<AbsCustComponent> children_ = _elt.getChildren();
        if (!children_.isValidIndex(_index)) {
            return null;
        }
        return children_.get(_index);
    }

    private static int indexOf(AbsCustComponent _par, AbsCustComponent _elt) {
        if ( _par == null) {
            return -1;
        }
        return indexOf(_par.getChildren(), _elt);
    }

    public static int indexOf(CustList<AbsCustComponent> _list, AbsCustComponent _elt) {
        int len_ = _list.size();

        for(int i = 0; i < len_; i++) {
            AbsCustComponent c = _list.get(i);
            if (c == _elt) {
                return i;
            }
        }

        return -1;
    }

    public static AbsPreparedLabel prep(AbstractImageFactory _img) {
        return _img.newImageArgb(1,1).newAbsPreparedLabel();
    }

    public static void invokeLater(Runnable _r, AbstractLightProgramInfos _compoFactory) {
        _compoFactory.getCompoFactory().invokeLater(_r);
    }

    public static void setOrient(AbsSlider _slider,int _o) {
        if (GuiConstants.getOrient(_o) == GuiConstants.HORIZONTAL) {
            _slider.setHorizontal();
        } else {
            _slider.setVertical();
        }
    }

    public static void setHorizProg(AbsProgressBar _bar, boolean _horiz) {
        if (_horiz) {
            _bar.setHorizontal();
        } else {
            _bar.setVertical();
        }
    }

    public static void setSelectTable(AbsTableGui _table, boolean _mult) {
        if (_mult) {
            _table.setMultiSelect();
        } else {
            _table.setSingleSelect();
        }
    }

    public static boolean eq(AbstractImage _imgOne, AbstractImage _imgTwo) {
        if (_imgOne.getWidth() != _imgTwo.getWidth()) {
            return false;
        }
        if (_imgOne.getHeight() != _imgTwo.getHeight()) {
            return false;
        }
        int w_ = _imgOne.getWidth();
        int h_ = _imgOne.getHeight();
        for (int i = IndexConstants.FIRST_INDEX; i < w_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < h_; j++) {
                if (_imgOne.getRGB(i, j) != _imgTwo.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
