package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class CrudGeneFormPk extends AbsCrudGeneFormMap<String, PokemonData> {
    private final FacadeGame facadeGame;
    public CrudGeneFormPk(AbstractProgramInfos _fact, Comparing<String> _cmp, FacadeGame _facade) {
        super(_fact, _cmp);
        facadeGame = _facade;
    }
    public void initForm(AbsCommonFrame _fr, AbstractProgramInfos _core) {
        StringMap<String> messages_ = new StringMap<String>(facadeGame.getData().getTranslatedPokemon().getVal(_core.getLanguage()));
        ComparatorTr<String> cmp_ = new ComparatorTr<String>(messages_);
        initForm(_fr,new DisplayKeyOnly<String,PokemonData>(messages_),ConverterCommonMapUtil.buildPk(_core,facadeGame),new GeneComponentModelPokemonData(_core,facadeGame),cmp_,facadeGame.getData().getPokedex());
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
                setGeneKey(ConverterCommonMapUtil.buildPk(getFactory(),facadeGame));
                afterModif();
            }
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame.getData().completeQuickMembers(_key,_value);
            setGeneKey(ConverterCommonMapUtil.buildPk(getFactory(),facadeGame));
            afterModif();
            return;
        }
        facadeGame.getData().getPokedex().set(_key, _value);
        afterModif();
    }

}
