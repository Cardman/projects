package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.PokemonData;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryPk extends SubscribedTranslationMessagesFactoryCommonParam<PokemonData> {

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
}
