package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrOtherCstList implements AbsCrudGeneFormTrCstOpen {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsCommonFrame frame;
    private final StringMap<AbsTextField> fields = new StringMap<AbsTextField>();
    private final StringMap<AbsButton> buttons = new StringMap<AbsButton>();

    public CrudGeneFormTrOtherCstList(AbstractProgramInfos _core, FacadeGame _facade) {
        api = _core;
        facadeGame = _facade;
        frame = _core.getFrameFactory().newCommonFrame();
    }

    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        fields.clear();
        buttons.clear();
        line(page_, DataBase.DEF_MOVE, facadeGame.getData().getDefMove());
        line(page_, DataBase.RATE_BOOST, facadeGame.getData().getRateBoost());
        line(page_, DataBase.RATE_BOOST_CRITICAL_HIT, facadeGame.getData().getRateBoostCriticalHit());
        line(page_, DataBase.RATE_FLEEING, facadeGame.getData().getRateFleeing());
        line(page_, DataBase.RATE_CATCHING, facadeGame.getData().getRateCatching());
        line(page_, DataBase.BALL_DEF, facadeGame.getData().getBallDef());
        line(page_, DataBase.DEFAULT_EGG_GROUP, facadeGame.getData().getDefaultEggGroup());
        line(page_, DataBase.DAMAGE_FORMULA, facadeGame.getData().getDamageFormula());
        line(page_, DataBase.DEF_CAT, facadeGame.getData().getDefCategory());
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    private void line(AbsPanel _page, String _key, String _value) {
        AbsPanel line_ = api.getCompoFactory().newLineBox();
        AbsTextField field_ = api.getCompoFactory().newTextField(16);
        field_.setText(_value);
        line_.add(field_);
        AbsButton but_ = api.getCompoFactory().newPlainButton(_key);
        but_.addActionListener(new RenameCstOtherEvent(this, _key, field_));
        _page.add(line_);
        _page.add(but_);
        fields.addEntry(_key, field_);
        buttons.addEntry(_key, but_);
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public StringMap<AbsTextField> getFields() {
        return fields;
    }

    public StringMap<AbsButton> getButtons() {
        return buttons;
    }

    public void apply(String _k, AbsTextField _f) {
        String typed_ = _f.getText();
        facadeGame.getData().initValue(_k, typed_);
    }
}
