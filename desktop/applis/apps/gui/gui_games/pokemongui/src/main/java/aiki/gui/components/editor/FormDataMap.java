package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.*;
import code.gui.*;
import code.gui.initialize.*;

public final class FormDataMap {
    private final WindowPkEditor window;
    private final FacadeGame facadeGame;
    private final AbsSpinner screenWidth;
    private final AbsSpinner screenHeight;
    private final AbsSpinner spaceBetweenLeftAndHeros;
    private final AbsSpinner spaceBetweenTopAndHeros;
    private final AbsSpinner sideLength;
    private final AbsButton applyMapModif;
    private final AbsPanel form;
    public FormDataMap(WindowPkEditor _ed, FacadeGame _facade) {
        window = _ed;
        facadeGame = _facade;
        AbstractProgramInfos frames_ = getWindow().getFrames();
        screenWidth = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        screenHeight = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        spaceBetweenLeftAndHeros = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        spaceBetweenTopAndHeros = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        sideLength = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        applyMapModif = frames_.getCompoFactory().newPlainButton("_");
        applyMapModif.addActionListener(new ApplyModifDataMapDimsEvent(this));
        form = frames_.getCompoFactory().newPageBox();
        form.add(screenWidth);
        form.add(screenHeight);
        form.add(spaceBetweenLeftAndHeros);
        form.add(spaceBetweenTopAndHeros);
        form.add(sideLength);
        form.add(applyMapModif);
    }

    public void updateValues() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        screenWidth.setValue(dm_.getScreenWidth());
        screenHeight.setValue(dm_.getScreenHeight());
        spaceBetweenLeftAndHeros.setValue(dm_.getSpaceBetweenLeftAndHeros());
        spaceBetweenTopAndHeros.setValue(dm_.getSpaceBetweenTopAndHeros());
        sideLength.setValue(dm_.getSideLength());
    }
    public void update() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        dm_.setScreenWidth(screenWidth.getValue());
        dm_.setScreenHeight(screenHeight.getValue());
        dm_.setSpaceBetweenLeftAndHeros(spaceBetweenLeftAndHeros.getValue());
        dm_.setSpaceBetweenTopAndHeros(spaceBetweenTopAndHeros.getValue());
        dm_.setSideLength(sideLength.getValue());
    }
    public AbsSpinner getScreenWidth() {
        return screenWidth;
    }

    public AbsSpinner getScreenHeight() {
        return screenHeight;
    }

    public AbsSpinner getSpaceBetweenLeftAndHeros() {
        return spaceBetweenLeftAndHeros;
    }

    public AbsSpinner getSpaceBetweenTopAndHeros() {
        return spaceBetweenTopAndHeros;
    }

    public AbsSpinner getSideLength() {
        return sideLength;
    }

    public AbsButton getApplyMapModif() {
        return applyMapModif;
    }

    public AbsPanel getForm() {
        return form;
    }

    public WindowPkEditor getWindow() {
        return window;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

}
