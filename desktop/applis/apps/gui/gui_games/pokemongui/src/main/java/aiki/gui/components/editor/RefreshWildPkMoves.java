package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class RefreshWildPkMoves implements SubscribedTranslation, AbsChangeListener, ListSelection {
    private final FacadeGame facadeGame;
    private final FormWildPk grid;

    public RefreshWildPkMoves(FacadeGame _f, FormWildPk _g) {
        this.facadeGame = _f;
        this.grid = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        actPack();
    }

    @Override
    public void stateChanged() {
        actPack();
    }

    private void actPack() {
        act(grid.getApi(), facadeGame, grid);
        grid.getFrame().pack();
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        act(_api, facadeGame, grid);
    }

    public static void act(AbstractProgramInfos _api, FacadeGame _facadeGame, FormWildPk _grid) {
        PokemonData pokemon_ = _facadeGame.getData().getPokemon(_grid.getName().tryRet());
        if (pokemon_ == null) {
            return;
        }
        StringList movesAtLevel_ = pokemon_.getMovesAtLevel(_grid.getLevel().valueLong(), _facadeGame.getData().getNbMaxMoves());
        AbsPanel absPanel_ = _api.getCompoFactory().newPageBox();
        absPanel_.setTitledBorder(SubscribedTranslationList.formatTxt(_api,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MOVES));
        StringList all_ = new StringList();
        for (String m: movesAtLevel_) {
            all_.add(_facadeGame.getData().getTranslatedMoves().getVal(_api.getLanguage()).getVal(m));
        }
        all_.sort();
        for (String m: all_) {
            absPanel_.add(_api.getCompoFactory().newPlainLabel(m));
        }
        _grid.getView().setViewportView(absPanel_);
    }
}
