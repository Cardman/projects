package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.levels.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelSubscribeLevelLeague {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private final CrudGeneFormLevel cave;
    private LevelLeague edited = Instances.newLevelLeague();
    private final ContentComponentModelLevelLeague wild = new ContentComponentModelLevelLeague();
    public GeneComponentModelSubscribeLevelLeague(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, CrudGeneFormLevel _c) {
        api = _core;
        facadeGame = _fac;
        factory = _fact;
        frame = _f;
        cave = _c;
    }
    public AbsCustComponent geneEnum() {
        AbsCompoFactory compoFactory_ = api.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        edited = Instances.newLevelLeague();
        AbsCustComponent compo_ = wild.form(api, facadeGame, factory, frame);
        wild.setupGridDims(AbsContentComponentModelLevelLinks.coords(cave.getSelectedPlace(), cave.getSelectedLevel(), null), cave.getPlace(),edited);
        sc_.setViewportView(compo_);
        page_.add(sc_);
        return page_;
    }

    public LevelLeague tryRet() {
        wild.buildEntity(edited);
        return edited;
    }

    public void setupValue(LevelLeague _value) {
        LevelLeague lc_ = ConverterCommonMapUtil.copyLevelLeague(_value);
        wild.setupGridDims(AbsContentComponentModelLevelLinks.coords(cave.getSelectedPlace(), cave.getSelectedLevel(), null), cave.getPlace(), lc_);
        edited = lc_;
    }


    public ContentComponentModelLevelLeague getWild() {
        return wild;
    }
}
