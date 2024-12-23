package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.levels.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeLevelCave implements AbsGeneComponentModelSubscribe<LevelCave> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private final CrudGeneFormLevelCave cave;
    private LevelCave edited = Instances.newLevelCave();
    private final ContentComponentModelLevelWithWild wild = new ContentComponentModelLevelWithWild();
    public GeneComponentModelSubscribeLevelCave(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, CrudGeneFormLevelCave _c) {
        api = _core;
        facadeGame = _fac;
        factory = _fact;
        frame = _f;
        cave = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsCompoFactory compoFactory_ = api.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        AbsCustComponent compo_ = wild.form(api, facadeGame, factory, frame);
        compo_.setVisible(true);
        sc_.setViewportView(compo_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public LevelCave tryRet() {
        edited.setBlocks(wild.getLevel().getEdited());
        edited.setWildPokemonAreas(wild.getAreas().getList());
        return edited;
    }

    @Override
    public void setupValue(LevelCave _value) {
        wild.setupGridDims(_value.getBlocks(), (short) cave.getIndexCave(), (byte) cave.getSelectedIndex(), cave.getCave(),_value);
        edited = _value;
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
