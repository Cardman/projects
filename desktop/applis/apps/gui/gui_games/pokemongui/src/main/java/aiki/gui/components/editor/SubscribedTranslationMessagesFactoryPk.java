package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.PokemonData;
import code.gui.AbsCommonFrame;
import code.gui.EditedCrudPair;
import code.gui.GeneComponentModel;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryPk extends SubscribedTranslationMessagesFactoryCommonParam<PokemonData> {

    private GeneComponentModelPokemonData geneComponentModelPokemonData;

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedPokemon();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renamePokemon(_previous,_next);
    }

    @Override
    public StringMap<PokemonData> all(FacadeGame _facade) {
        return _facade.getData().getPokedex();
    }

    @Override
    public void delete(FacadeGame _facade, String _key) {
        _facade.getData().deletePokemon(_key);
    }

    @Override
    public GeneComponentModel<EditedCrudPair<String,PokemonData>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String,PokemonData>> _facade) {
        geneComponentModelPokemonData = new GeneComponentModelPokemonData(_frame, _core, _facade.getFacadeGame(), _facade.getSubscription());
        return geneComponentModelPokemonData;
    }

    @Override
    public void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String,PokemonData>> _base) {
        geneComponentModelPokemonData.getEvolutions().getCrudGeneFormSubContent().removeOpenSub();
        geneComponentModelPokemonData.getLevMoves().getCrudGeneFormSubContent().removeOpenSub();
        _base.removeOpenSub();
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return geneComponentModelPokemonData.all();
    }

    @Override
    public StringList mids(FacadeGame _facade) {
        return new StringList();
    }
}
