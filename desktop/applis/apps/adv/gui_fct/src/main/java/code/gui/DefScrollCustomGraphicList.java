package code.gui;

import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;

public final class DefScrollCustomGraphicList<T> extends ScrollCustomGraphicList<T> {
    private final AbsCustCellRenderGene<T> custCellRenderGene;
    public DefScrollCustomGraphicList(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<T> _render, boolean _s) {
        super(_compo, _img, _s);
        buildActions();
        setEnabled(true);
        custCellRenderGene = _render;
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectShiftEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectShiftEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectPageEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectPageEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectBoundEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectBoundEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectShiftBoundEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectShiftBoundEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicFocusBoundEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicFocusBoundEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicAnchorBoundEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicAnchorBoundEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectShiftPageEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectShiftPageEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicFocusPageEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicFocusPageEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicAnchorPageEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicAnchorPageEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicFocusEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicFocusEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicAnchorEvent(int _d) {
        return getCompoFactory().wrap(new MoveGraphicAnchorEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectAddAncIntervalEvent(boolean _d) {
        return getCompoFactory().wrap(new MoveGraphicSelectAddAncIntervalEvent<T>(this,_d));
    }

    @Override
    protected AbsEnabledAction moveGraphicDeselectAllAction() {
        return getCompoFactory().wrap(new MoveGraphicSelectDeSelectAllEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicSelectAllAction() {
        return getCompoFactory().wrap(new MoveGraphicSelectSelectAllEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicAddTo() {
        return getCompoFactory().wrap(new MoveGraphicSelectAddIntervalEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicChange() {
        return getCompoFactory().wrap(new MoveGraphicSelectChangeEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicExtend() {
        return getCompoFactory().wrap(new MoveGraphicSelectExtendToEvent<T>(this));
    }

    @Override
    protected AbsEnabledAction moveGraphicToggle() {
        return getCompoFactory().wrap(new MoveGraphicSelectToggleIntervalEvent<T>(this));
    }

    public AbstractImage generateImg(MetaFont _lab, int _index, T _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored) {
        return custCellRenderGene.getListCellRendererComponent(_index, _info, _isSelected, _cellHasFocus, _cellIsAnchored, _lab,colorGroup());
    }
}
