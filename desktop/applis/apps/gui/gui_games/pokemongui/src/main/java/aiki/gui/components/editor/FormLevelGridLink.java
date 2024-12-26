package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class FormLevelGridLink extends FormLevelGridCommon {
    private AbsPaintableLabel grid;
    private AbsPanel form;

    public FormLevelGridLink(AbstractProgramInfos _a, FacadeGame _f, AbsCommonFrame _fr, SubscribedTranslationList _i) {
        super(_a,_f, _fr,_i);
    }
    public void setupGridDims(Points<Block> _bk, Points<int[][]> _f) {
        setupForeground(_f);
        setEdited(_bk);
        setupGrid();
    }

    public void setupGrid() {
        AbsCompoFactory c_ = getApi().getCompoFactory();
        AbsPanel form_ = c_.newPageBox();
        grid = c_.newAbsPaintableLabel();
        refreshImg();
        grid.setLineBorder(GuiConstants.BLACK);
        form_.add(getGrid());
        form = form_;
    }

    public void refreshImg() {
        Limits limits_ = Level.limits(getEdited());
        Point topLeft_ = limits_.getTopLeft();
        Point bottomRight_ = limits_.getBottomRight();
        int rowsCount_ = NumberUtil.max(1, bottomRight_.gety() - topLeft_.gety());
        int colsCount_ = NumberUtil.max(1, bottomRight_.getx() - topLeft_.getx());
        AbstractImageFactory imgFact_ = getApi().getImageFactory();
        AbstractImage img_ = ConverterCommonMapUtil.buildImg(getApi(), getFacadeGame(), getEdited(), getTopLeftRel(), rowsCount_, colsCount_);
        img_.drawImage(ConverterCommonMapUtil.buildImgFore(getApi(), getFacadeGame(),limits_, getForeground(), getTopLeftRel(), rowsCount_, colsCount_),0, 0);
        img_.drawImage(ConverterCommonMapUtil.buildImgFore(getApi(), getFacadeGame(),limits_, getForegroundEdited(), getTopLeftRel(), rowsCount_, colsCount_),0, 0);
        grid.setIcon(imgFact_,img_);
    }

    public AbsPanel getForm() {
        return form;
    }

    public AbsPaintableLabel getGrid() {
        return grid;
    }


}
