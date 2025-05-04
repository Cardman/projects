package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelLeagueLinks {

    private GeneComponentModelImgSelect linkFileName;
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private NullablePoint begin = new NullablePoint();
    private NullablePoint access = new NullablePoint();
    private AbsButton close;
    private FacadeGame facadeGame;
    private FormLevelGridLink level;
    private final CustList<FormLevelGridLink> levels = new CustList<FormLevelGridLink>();
    private League cave;
    private int selectedPlace;

    public void selectIndexes(League _cave, int _nbPlace) {
        selectedPlace = _nbPlace;
        cave = _cave;
    }

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, CrudGeneFormEntPlace _par) {
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        facadeGame = _fac;
        if (cave.getRooms().isEmpty()) {
            return form_;
        }
        close = _core.getCompoFactory().newPlainButton("\u23F9");
        close.addActionListener(new CloseLinksFormEvent(_par));
        form_.add(getClose());
        linkFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverLinksSub());
        form_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI,linkFileName.gene()));
        FormDataMap.baseSelectImage(linkFileName);
        linkFileName.getName().getSelectUniq().getSelect().addListener(new ChangeFileNameLeagueEvent(this));
        linkFileName.getName().setupValue(cave.getFileName());
        linkFileName.getName().getSelectUniq().getSelect().events(null);
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        map_.setViewportView(viewLeft(_core, _fac, _fact, _f));
        begin = cave.getBegin();
        access = new NullablePoint();
        buildParts(form_,_core, _fac, _fact, _f);
        return _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
    }

    private AbsCustComponent viewLeft(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        level = AbsContentComponentModelLevelLinks.build(_core, _fac, _fact, _fr, cave, AbsContentComponentModelLevelLinks.coords(selectedPlace, 0, null), getTranslationsGrid());
        level.getGrid().addMouseListener(new SelectOrDeselectBeginLeagueEvent(this));
        return level.getForm();
    }

    private void buildParts(AbsPanel _form, AbstractProgramInfos _c, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        levels.clear();
        CustList<Place> places_ = facadeGame.getMap().getPlaces();
        int len_ = places_.size();
        for (int i = 0; i < len_; i++) {
            CustList<Level> ls_ = places_.get(i).getLevelsList();
            int levs_ = ls_.size();
            for (int j = 0; j < levs_; j++) {
                FormLevelGridLink g_ = AbsContentComponentModelLevelLinks.build(_c, _fac, _fact, _fr, places_.get(i), AbsContentComponentModelLevelLinks.coords(i, j, null), getTranslationsGrid());
                levels.add(g_);
                g_.getGrid().addMouseListener(new SelectOrDeselectAccessLeagueEvent(this, g_));
                _form.add(g_.getForm());
                Coords acc_ = new Coords(cave.getAccessCoords());
                acc_.getLevel().setPoint(new Point(0, 0));
                if (Coords.eq(acc_,g_.build(new Point(0, 0)))) {
                    Point pt_ = cave.getAccessCoords().getLevel().getPoint();
                    access = new NullablePoint(pt_);
                }
            }

        }
    }

    public void selectOrDeselectBeginLeague(int _x, int _y) {
        Point pt_ = level.toPt(_x, _y);
        NullablePoint bk_ = begin;
        begin = new NullablePoint();
        if (!Point.eq(bk_, pt_)) {
            begin = new NullablePoint(pt_);
        }
        if (begin.isDefined() && FormLevelGridCommon.edited(begin.getPoint(), level.getForeground(), level.getForegroundEdited())) {
            begin = bk_;
            return;
        }
        if (begin.isDefined()) {
            ContentComponentModelLevelWithWild.trySet(null, level.getForegroundEdited(), begin.getPoint());
        } else {
            level.getForegroundEdited().removeKey(bk_.getPoint());
        }
        if (cave.getBegin().isDefined()) {
            Coords prev_ = AbsContentComponentModelLevelLinks.coords(selectedPlace,0,null, new Point(cave.getBegin().getPoint()));
            if (begin.isDefined()) {
                Coords next_ = new Coords(prev_);
                next_.getLevel().setPoint(new Point(begin.getPoint()));
                ConverterCommonMapUtil.replaceValues(facadeGame.getMap().getAccessCondition(),prev_,next_);
            } else {
                ConverterCommonMapUtil.removeValues(facadeGame.getMap().getAccessCondition(),prev_);
            }
        }
        cave.setBegin(begin);
    }

    public void selectOrDeselectAccessLeague(FormLevelGridLink _selected,int _x, int _y) {
        Point pt_ = _selected.toPt(_x, _y);
        NullablePoint bk_ = access;
        access = new NullablePoint();
        if (!Point.eq(bk_, pt_)) {
            access = new NullablePoint(pt_);
        }
        if (access.isDefined() && FormLevelGridCommon.edited(access.getPoint(), _selected.getForeground(), _selected.getForegroundEdited())) {
            access = bk_;
            return;
        }
        for (FormLevelGridLink f: levels) {
            f.getForegroundEdited().clear();
        }
        if (access.isDefined()) {
            ContentComponentModelLevelWithWild.trySet(null, _selected.getForegroundEdited(), access.getPoint());
            cave.setAccessCoords(_selected.build(access.getPoint()));
        } else {
            _selected.getForegroundEdited().removeKey(bk_.getPoint());
            cave.setAccessCoords(new Coords());
        }
        ContentComponentModelPlaceCaveLinks.refreshList(levels);
    }
    public void updateFileName() {
        cave.setFileName(linkFileName.getName().tryRet());
    }

    public FormLevelGridLink getLevel() {
        return level;
    }

    public CustList<FormLevelGridLink> getLevels() {
        return levels;
    }

    public IdList<SubscribedTranslation> getTranslationsGrid() {
        return translationsGrid;
    }

    public AbsButton getClose() {
        return close;
    }

}
