package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormPk extends CrudGeneFormSub<String, PokemonData> {
    private GeneComponentModelPokemonData geneComponentModelPokemonData;

    public CrudGeneFormPk(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact,_facade,_sub);
        initForm();
    }
    public void initForm(AbsCommonFrame _fr, AbstractProgramInfos _core) {
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        StringMap<String> messages_ = new StringMap<String>(facadeGame_.getData().getTranslatedPokemon().getVal(_core.getLanguage()));
        geneComponentModelPokemonData = new GeneComponentModelPokemonData(_fr,_core, facadeGame_, getCrudGeneFormSubContent().getSubscription());
        subscribeAll();
        initForm(messages_, getGeneKey(), geneComponentModelPokemonData, facadeGame_.getData().getPokedex());
        setFrame(_fr);
    }

    @Override
    protected void afterModif(int _index, String _key, PokemonData _value) {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            int old_ = facadeGame_.getData().getPokedex().size();
            StringMap<StringMap<String>> bk_ = ConverterCommonMapUtil.backUp(facadeGame_.getData().getTranslatedPokemon());
            facadeGame_.getData().deletePokemon(_key);
            if (old_ > facadeGame_.getData().getPokedex().size()) {
                facadeGame_.getData().getTranslatedPokemon().clear();
                facadeGame_.getData().getTranslatedPokemon().addAllEntries(bk_);
                getList().remove(_index);
                afterChange();
            }
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame_.getData().completeQuickMembers(_key,_value);
            afterChange();
            return;
        }
        facadeGame_.getData().getPokedex().set(_key, _value);
        afterChange();
    }

    @Override
    protected void afterChange() {
        getCrudGeneFormSubContent().removeOpenSub();
        subscribeAll();
        afterModif();
    }

    @Override
    public void cancel() {
        geneComponentModelPokemonData.getEvolutions().getCrudGeneFormSubContent().removeOpenSub();
        super.cancel();
    }

    @Override
    protected IdList<SubscribedTranslation> subscribe() {
        GeneComponentModelEltStrSub key_ = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame());
        setGeneKey(key_.getSelectUniq());
        return new IdList<SubscribedTranslation>(key_.subsPk());
    }

    @Override
    public void updateDisplayEntry(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        setDisplayEntry(new DisplayKeyOnly<String, PokemonData>(messages_));
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return geneComponentModelPokemonData.all();
    }

}
