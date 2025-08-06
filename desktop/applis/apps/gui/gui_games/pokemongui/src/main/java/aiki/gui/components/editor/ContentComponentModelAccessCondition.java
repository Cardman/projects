package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.comparators.*;
import code.util.core.*;

public final class ContentComponentModelAccessCondition {

    private AbstractProgramInfos api;
    private AbsCommonFrame frame;
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private Coords selected = new Coords();
    private AbsButton validateAccess;
    private AbsButton clearAccess;
    private AbsButton close;
    private FacadeGame facadeGame;
    private final CustList<FormLevelGridLink> levels = new CustList<FormLevelGridLink>();
    private final CoordssBoolVal trainers = new CoordssBoolVal();
    private AbsPanel trainersForm;
    private final boolean beginGame;

    public ContentComponentModelAccessCondition(boolean _b) {
        this.beginGame = _b;
    }

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, CrudGeneFormEntPlace _parent) {
        api = _core;
        frame = _f;
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        facadeGame = _fac;
        StringMap<String> tf_ = MessagesPkEditor.getMessagesEditorSelectButtonsTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping();
        validateAccess = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.ACC_COND_VALIDATE));
        validateAccess.addActionListener(new ValidateAccessConditionEvent(this));
        form_.add(validateAccess);
        if (!beginGame) {
            validateAccess.setEnabled(false);
            clearAccess = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.ACC_COND_CLEAR));
            clearAccess.addActionListener(new ClearAccessConditionEvent(this));
            form_.add(clearAccess);
        }
        close = _core.getCompoFactory().newPlainButton("\u23F9");
        close.addActionListener(new CloseLinksFormEvent(_parent, _fact, translationsGrid, _f));
        form_.add(getClose());
        if (beginGame) {
            buildParts(form_,_core, _fac, _fact, _f);
            return _core.getCompoFactory().newAbsScrollPane(form_);
        }
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        trainersForm = _core.getCompoFactory().newPageBox();
        viewRight();
        map_.setViewportView(trainersForm);
        buildParts(form_,_core, _fac, _fact, _f);
        return _core.getCompoFactory().newHorizontalSplitPane(_core.getCompoFactory().newAbsScrollPane(form_),map_);
    }

    private void viewRight() {
        trainers.clear();
        for (Coords c: viewRight(facadeGame.getMap().getPlaces())) {
            trainers.addEntry(c,BoolVal.FALSE);
        }
    }

    public static Condition viewRight(CustList<Place> _places) {
        Condition dest_ = new Condition();
        int len_ = _places.size();
        for (int i = 0; i < len_; i++) {
            viewRight(_places,i,dest_);
        }
        return dest_;
    }
    private static void viewRight(CustList<Place> _places, int _i, CustList<Coords> _dest) {
        Place place_ = _places.get(_i);
        CustList<Level> ls_ = place_.getLevelsList();
        int levs_ = ls_.size();
        for (int j = 0; j < levs_; j++) {
            Level lv_ = ls_.get(j);
            if (lv_ instanceof LevelWithWildPokemon) {
                for (Point k: ((LevelWithWildPokemon)lv_).getDualFights().getKeys()) {
                    _dest.add(AbsContentComponentModelLevelLinks.coords(_i,j,null,k));
                }
            }
        }
        if (place_ instanceof City) {
            for (EntryCust<Point, Building> b : ((City)place_)
                    .getBuildings().entryList()) {
                if (b.getValue() instanceof Gym && ((Gym) b.getValue()).getIndoor()
                        .getGymLeaderCoords().isDefined()) {
                    _dest.add(AbsContentComponentModelLevelLinks.coords(_i,0,b.getKey(),((Gym) b.getValue()).getIndoor()
                            .getGymLeaderCoords().getPoint()));
                }
            }
        }
        if (place_ instanceof League && ((League) place_).getBegin().isDefined()) {
            _dest.add(AbsContentComponentModelLevelLinks.coords(_i,0,null,((League) place_).getBegin().getPoint()));
        }
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
                g_.getForm().setTitledBorder(i+"/"+(len_-1)+"-"+j+"/"+(levs_-1));
                levels.add(g_);
                g_.getGrid().addMouseListener(new SelectOrDeselectAccessConditionEvent(this, g_));
                _form.add(g_.getForm());
            }

        }
    }

    public void selectOrDeselectAccess(FormLevelGridLink _g,int _x, int _y) {
        Point pt_ = _g.toPt(_x, _y);
        Coords key_ = _g.build(pt_);
        if (beginGame) {
            facadeGame.getMap().setBegin(key_);
            return;
        }
        selected = key_;
        for (EntryCust<Coords, BoolVal> c:trainers.entryList()) {
            c.setValue(BoolVal.FALSE);
        }
        for (Coords c:facadeGame.getMap().getAccessCondition().getVal(key_)) {
            trainers.put(c,BoolVal.TRUE);
        }
        trainersForm.removeAll();
        for (EntryCust<Coords, BoolVal> c:trainers.entryList()) {
            AbsCustCheckBox ch_ = api.getCompoFactory().newCustCheckBox(c.getKey().display());
            ch_.setSelected(c.getValue() == BoolVal.TRUE);
            ch_.addActionListener(new ChangeAccessConditionEvent(this,ch_,c.getKey()));
            trainersForm.add(ch_);
        }
        validateAccess.setEnabled(true);
        frame.pack();
    }

    public void changeSelectCondition(AbsCustCheckBox _check, Coords _key) {
        trainers.put(_key, ComparatorBoolean.of(_check.isSelected()));
    }
    public void validateAccess() {
        Condition res_ = new Condition();
        for (EntryCust<Coords, BoolVal> c:trainers.entryList()) {
            if (c.getValue() == BoolVal.TRUE) {
                res_.add(c.getKey());
            }
        }
        if (res_.isEmpty()) {
            facadeGame.getMap().getAccessCondition().removeKey(selected);
        } else {
            facadeGame.getMap().getAccessCondition().put(selected, res_);
        }
    }
    public void clearAccess() {
        for (EntryCust<Coords, BoolVal> c:trainers.entryList()) {
            c.setValue(BoolVal.FALSE);
        }
        facadeGame.getMap().getAccessCondition().getList().clear();
        validateAccess.setEnabled(false);
        selected = new Coords();
        trainersForm.removeAll();
        frame.pack();
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

    public AbsPanel getTrainersForm() {
        return trainersForm;
    }

    public AbsButton getValidateAccess() {
        return validateAccess;
    }

    public AbsButton getClearAccess() {
        return clearAccess;
    }
}
