package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormPk extends AbsCrudGeneFormMap<String, PokemonData> {
    private final FacadeGame facadeGame;
    private final IdList<SubscribedTranslation> subscribedTranslations;
    private final IdList<SubscribedTranslation> subscribedTranslationsForm;
    private GeneComponentModelPokemonData geneComponentModelPokemonData;

    public CrudGeneFormPk(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact);
        facadeGame = _facade;
        subscribedTranslations = _sub.getSubscribedTranslations();
        subscribedTranslationsForm = new IdList<SubscribedTranslation>();
    }
    public void initForm(AbsCommonFrame _fr, AbstractProgramInfos _core) {
        subscribedTranslationsForm.clear();
        StringMap<String> messages_ = new StringMap<String>(facadeGame.getData().getTranslatedPokemon().getVal(_core.getLanguage()));
        ComparatorTr<String> cmp_ = new ComparatorTr<String>(messages_);
        geneComponentModelPokemonData = new GeneComponentModelPokemonData(_core, facadeGame);
        GeneComponentModelEltStr geneKey_ = subscribe(_core);
        initForm(_fr,new DisplayKeyOnly<String,PokemonData>(messages_), geneKey_, geneComponentModelPokemonData,cmp_,facadeGame.getData().getPokedex());
    }

    @Override
    protected void afterModif(int _index, String _key, PokemonData _value) {
        if (_index > -1) {
            int old_ = facadeGame.getData().getPokedex().size();
            StringMap<StringMap<String>> bk_ = ConverterCommonMapUtil.backUp(facadeGame.getData().getTranslatedPokemon());
            facadeGame.getData().deletePokemon(_key);
            if (old_ > facadeGame.getData().getPokedex().size()) {
                facadeGame.getData().getTranslatedPokemon().clear();
                facadeGame.getData().getTranslatedPokemon().addAllEntries(bk_);
                getList().remove(_index);
                afterChange();
            }
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame.getData().completeQuickMembers(_key,_value);
            afterChange();
            return;
        }
        facadeGame.getData().getPokedex().set(_key, _value);
        afterChange();
    }

    private void afterChange() {
        subscribedTranslations.removeAllElements(subscribedTranslationsForm);
        GeneComponentModelEltStr gSel_ = subscribe(getFactory());
        setGeneKey(gSel_);
        afterModif();
    }

    private GeneComponentModelEltStr subscribe(AbstractProgramInfos _core) {
        GeneComponentModelEltStr geneKey_ = ConverterCommonMapUtil.buildPk(_core, facadeGame);
        SubscribedTranslationPkSelect selectKey_ = new SubscribedTranslationPkSelect(geneKey_);
        subscribedTranslations.add(selectKey_);
        subscribedTranslationsForm.add(selectKey_);
        SubscribedTranslationPkKey selectForm_ = new SubscribedTranslationPkKey(this);
        subscribedTranslations.add(selectForm_);
        subscribedTranslationsForm.add(selectForm_);
        return geneKey_;
    }

    @Override
    public void selectOrAdd() {
        for (SubscribedTranslation s: geneComponentModelPokemonData.all()) {
            subscribedTranslations.add(s);
            subscribedTranslationsForm.add(s);
        }
        super.selectOrAdd();
    }

    @Override
    public void cancel() {
        subscribedTranslations.removeAllElements(subscribedTranslationsForm);
        super.cancel();
    }
}
