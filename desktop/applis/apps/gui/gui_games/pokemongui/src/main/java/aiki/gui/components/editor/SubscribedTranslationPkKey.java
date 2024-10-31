package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationPkKey implements SubscribedTranslation {
    private final CrudGeneFormPk crud;

    public SubscribedTranslationPkKey(CrudGeneFormPk _c) {
        this.crud = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        crud.setDisplayEntry(new DisplayKeyOnly<String, PokemonData>(messages_));
        crud.refresh();
    }
}
