package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class FormDataMap {
    private final WindowPkEditor window;
    private final FacadeGame facadeGame;
    private final AbsSpinner screenWidth;
    private final AbsSpinner screenHeight;
    private final AbsSpinner spaceBetweenLeftAndHeros;
    private final AbsSpinner spaceBetweenTopAndHeros;
    private final AbsSpinner sideLength;
    private final AbsScrollPane wildPkContent;
    private final AbsButton applyMapModif;
    private final AbsPanel form;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private final FormWildPk formWildPk;

    public FormDataMap(WindowPkEditor _ed, FacadeGame _facade, SubscribedTranslationList _subscriptions) {
        formWildPk = new FormWildPk(_ed, _facade, _subscriptions);
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
        wildPkContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(wildPkContent);
    }

    public void updateValues() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        screenWidth.setValue(dm_.getScreenWidth());
        screenHeight.setValue(dm_.getScreenHeight());
        spaceBetweenLeftAndHeros.setValue(dm_.getSpaceBetweenLeftAndHeros());
        spaceBetweenTopAndHeros.setValue(dm_.getSpaceBetweenTopAndHeros());
        sideLength.setValue(dm_.getSideLength());
        formWildPk.feedForm(dm_.getFirstPokemon());
        formWildPk.feedSubs(translations);
        wildPkContent.setViewportView(formWildPk.getForm());
    }
    public void update() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        dm_.setScreenWidth(screenWidth.getValue());
        dm_.setScreenHeight(screenHeight.getValue());
        dm_.setSpaceBetweenLeftAndHeros(spaceBetweenLeftAndHeros.getValue());
        dm_.setSpaceBetweenTopAndHeros(spaceBetweenTopAndHeros.getValue());
        dm_.setSideLength(sideLength.getValue());
        WildPk f_ = Instances.newWildPk();
        formWildPk.buildEntity(f_);
        dm_.setFirstPokemon(f_);
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

    public FormWildPk getFormWildPk() {
        return formWildPk;
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

    public IdList<SubscribedTranslation> getTranslations() {
        return translations;
    }
}
