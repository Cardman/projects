package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

import code.util.comparators.*;

public final class CrudGeneFormPkTr extends AbsCrudGeneFormMap<String, StringMap<String>> {
    private final FacadeGame facadeGame;
    private final AbsTextField destination;
    private final SubscribedTranslationList subscribedTranslations;
    public CrudGeneFormPkTr(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact);
        subscribedTranslations = _sub;
        facadeGame = _facade;
        destination = _fact.getCompoFactory().newTextField();
        destination.addActionListener(new RenameEntPkEvent(this));
    }
    public void initForm(AbsCommonFrame _fr, AbstractProgramInfos _core) {
        initForm(_fr,new DisplayKeyOnlyDirect<StringMap<String>>(),new GeneComponentModelString(_core,new StringList(),new DefValidateText()),new GeneComponentModelTr(_core,facadeGame),new NaturalComparator(),ConverterCommonMapUtil.toEntityLg(facadeGame.getData().getTranslatedPokemon()));
        getButtons().add(destination);
    }
    @Override
    protected void afterModif(int _index, String _key, StringMap<String> _value) {
        if (_index > -1) {
            if (facadeGame.getData().getPokedex().contains(_key)) {
                return;
            }
            getList().remove(_index);
            ConverterCommonMapUtil.removeKey(_key, facadeGame.getData().getTranslatedPokemon());
            afterModif();
            update();
            return;
        }
        if (getSelectedIndex() < 0) {
            ConverterCommonMapUtil.addKey(_key, _value, facadeGame.getData().getTranslatedPokemon());
            afterModif();
            update();
            return;
        }
        ConverterCommonMapUtil.setKey(_key, _value, facadeGame.getData().getTranslatedPokemon());
        afterModif();
        update();
    }

    @Override
    protected boolean invalidKey(String _key) {
        return !DataBase.isCorrectIdentifier(_key) || super.invalidKey(_key);
    }

    public void tryRename() {
        if (getSelectedIndex() >= 0) {
            String next_ = destination.getText();
            if (!DataBase.isCorrectIdentifier(next_)) {
                return;
            }
            String old_ = getList().getKey(getSelectedIndex());
            facadeGame.getData().renamePokemon(old_, next_);
            if (!facadeGame.getData().getPokedex().contains(old_)) {
                getList().move(old_,next_);
                refresh();
                afterModif();
                update();
            }
        }
    }

    public AbsTextField getDestination() {
        return destination;
    }

    private void update() {
        subscribedTranslations.update(getFrame());
    }

}
