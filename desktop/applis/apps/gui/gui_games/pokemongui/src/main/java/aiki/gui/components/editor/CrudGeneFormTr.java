package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.comparators.*;

public final class CrudGeneFormTr extends AbsCrudGeneFormMap<String, StringMap<String>> {
    private final FacadeGame facadeGame;
    private AbsTextField destination;
    private final SubscribedTranslationList subscribedTranslations;
    private final SubscribedTranslationMessagesFactory factoryMessage;
    public CrudGeneFormTr(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesFactory _facto) {
        super(_fact);
        factoryMessage = _facto;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        setFrame(_fr);
    }
    public void initForm(AbstractProgramInfos _core) {
        destination = getFactory().getCompoFactory().newTextField();
        destination.addActionListener(new RenameEntPkEvent(this));
        initForm();
        initForm(new DisplayKeyOnlyDirect<StringMap<String>>(), new GeneComponentModelString(_core,new StringList(),new DefValidateText()), new GeneComponentModelTr(_core,facadeGame), new NaturalComparator(), ConverterCommonMapUtil.toEntityLg(factoryMessage.buildMessages(facadeGame)));
        getButtons().add(destination);
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }
    @Override
    protected void afterModif(int _index, String _key, StringMap<String> _value) {
        if (_index > -1) {
            if (factoryMessage.contains(facadeGame,_key)) {
                return;
            }
            getList().remove(_index);
            ConverterCommonMapUtil.removeKey(_key, factoryMessage.buildMessages(facadeGame));
            afterModif();
            update();
            return;
        }
        if (getSelectedIndex() < 0) {
            ConverterCommonMapUtil.addKey(_key, _value, factoryMessage.buildMessages(facadeGame));
            afterModif();
            update();
            return;
        }
        ConverterCommonMapUtil.setKey(_key, _value, factoryMessage.buildMessages(facadeGame));
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
            factoryMessage.rename(facadeGame,old_,next_);
            if (!factoryMessage.contains(facadeGame,old_)) {
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
        subscribedTranslations.update();
    }

}
