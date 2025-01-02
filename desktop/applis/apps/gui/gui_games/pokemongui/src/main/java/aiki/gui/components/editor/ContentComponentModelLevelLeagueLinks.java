package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelLevelLeagueLinks {

    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private AbsButton close;
    private NullablePoint selectSecond = new NullablePoint();

    private FormLevelGridLink level;
    private League cave;
    private int selectedPlace;
    private int selectedFirstLevel;

    public void selectIndexes(League _cave, int _nbPlace, int _nbLevel) {
        selectedPlace = _nbPlace;
        selectedFirstLevel = _nbLevel;
        cave = _cave;
    }

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        map_.setViewportView(viewLeft(_core, _fac, _fact, _f));
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        close = _core.getCompoFactory().newPlainButton("\u23F9");
        form_.add(close);
        selectSecond = cave.getRooms().get(selectedFirstLevel).getNextLevelTarget();
        buildParts(form_,_core, _fac, _fact, _f);
        return _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
    }

    private AbsCustComponent viewLeft(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        return AbsContentComponentModelLevelLinks.build(_core, _fac, _fact, _fr, cave, AbsContentComponentModelLevelLinks.coords(selectedPlace,selectedFirstLevel,null), translationsGrid).getForm();
    }

    private void buildParts(AbsPanel _form, AbstractProgramInfos _c, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        int len_ = cave.getRooms().size();
        int next_ = (selectedFirstLevel + 1) % len_;
        level = AbsContentComponentModelLevelLinks.build(_c, _fac, _fact, _fr, cave, AbsContentComponentModelLevelLinks.coords(selectedPlace,next_,null), translationsGrid);
        level.getGrid().addMouseListener(new SelectOrDeselectNextPtEvent(this));
        _form.add(level.getForm());
    }

    public AbsButton getClose() {
        return close;
    }

    public void selectOrDeselect(int _x, int _y) {
        Point pt_ = level.toPt(_x, _y);
        NullablePoint bk_ = selectSecond;
        selectSecond = new NullablePoint();
        if (!Point.eq(bk_, pt_)) {
            selectSecond = new NullablePoint(pt_);
        }
        if (selectSecond.isDefined() && FormLevelGridCommon.edited(selectSecond.getPoint(), level.getForeground(), level.getForegroundEdited())) {
            selectSecond = bk_;
            return;
        }
        if (selectSecond.isDefined()) {
            ContentComponentModelLevelWithWild.trySet(null, level.getForegroundEdited(), selectSecond.getPoint());
        } else {
            level.getForegroundEdited().removeKey(bk_.getPoint());
        }
        cave.getRooms().get(selectedFirstLevel).setNextLevelTarget(selectSecond);
    }

    public FormLevelGridLink getLevel() {
        return level;
    }

}
