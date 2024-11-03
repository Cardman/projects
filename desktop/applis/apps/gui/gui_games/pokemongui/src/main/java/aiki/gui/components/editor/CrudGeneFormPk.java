package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormPk extends CrudGeneFormSub<String, PokemonData> {
    private GeneComponentModelEltStrSub geneComponentModelSelectKey;
    private GeneComponentModelPokemonData geneComponentModelPokemonData;

    public CrudGeneFormPk(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact,_facade,_sub, _fr);
    }
    public void initForm(AbstractProgramInfos _core) {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        StringMap<String> messages_ = subscription().getFactoryPk().buildMessages(_core,getCrudGeneFormSubContent().getFacadeGame());
//        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame(),subscription());
        geneComponentModelPokemonData = new GeneComponentModelPokemonData(getFrame(),_core, facadeGame_, getCrudGeneFormSubContent().getSubscription());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        getCrudGeneFormSubContent().addSubRoot(new SubscribedTranslationMessages(messages_,subscription().getFactoryPk()));
        initForm(messages_, getGeneKey(), geneComponentModelPokemonData, facadeGame_.getData().getPokedex());
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
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
    public void cancel() {
        geneComponentModelPokemonData.getEvolutions().getCrudGeneFormSubContent().removeOpenSub();
        geneComponentModelPokemonData.getLevMoves().getCrudGeneFormSubContent().removeOpenSub();
        super.cancel();
    }

    @Override
    protected void build() {
        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame(),subscription());
        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelSelectKey.getSubs());
        all_.addAllElts(geneComponentModelPokemonData.all());
        return all_;
    }

}
