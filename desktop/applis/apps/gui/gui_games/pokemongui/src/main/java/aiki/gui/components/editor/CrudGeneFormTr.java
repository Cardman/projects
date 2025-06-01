package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTr extends AbsCrudGeneFormList<EditedCrudPair<String, StringMap<String>>> implements AbsCrudGeneFormTrCstOpen {
    private final FacadeGame facadeGame;
    private AbsTextField destination;
    private final SubscribedTranslationList subscribedTranslations;
    private final SubscribedTranslationMessagesFactoryCommon factoryMessage;
    public CrudGeneFormTr(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesFactoryCommon _facto) {
        super(_fact, null);
        factoryMessage = _facto;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        setFrame(_fr);
    }

    @Override
    public void initFormAll() {
        initForm(getFactory());
    }

    public void initForm(AbstractProgramInfos _core) {
        destination = getFactory().getCompoFactory().newTextField();
        destination.addActionListener(new RenameEntPkEvent(this));
        initForm();
        ValidateElementPairId<StringMap<String>> val_ = new ValidateElementPairId<StringMap<String>>();
        ComparingStringKey<StringMap<String>> cmp_ = new ComparingStringKey<StringMap<String>>();
        CustList<EditedCrudPair<String, StringMap<String>>> ls_ = new MapToEntriesListUtil<String, StringMap<String>>().build(ConverterCommonMapUtil.toEntityLg(factoryMessage.buildMessages(facadeGame)));
        initForm(new DisplayKeyOnlyDirect<StringMap<String>>(), new GeneComponentModelTr(_core,facadeGame), cmp_, val_);
        setupValues(ls_);
        getButtons().add(SubscribedTranslationList.line(getFactory(),MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(getFactory().currentLg())),MessagesEditorSelect.DEST_TR,destination));
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }
    @Override
    public void afterModif(int _index, EditedCrudPair<String, StringMap<String>> _value) {
        String key_ = _value.getKey();
        if (_index > -1) {
            if (factoryMessage.contains(facadeGame,key_)) {
                return;
            }
            getList().remove(_index);
            ConverterCommonMapUtil.removeKey(key_, factoryMessage.buildMessages(facadeGame));
            afterModif();
            update(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
            return;
        }
        if (getSelectedIndex() < 0) {
            ConverterCommonMapUtil.addKey(key_, _value.getValue(), factoryMessage.buildMessages(facadeGame));
            afterModif();
            update(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
            return;
        }
        ConverterCommonMapUtil.setKey(key_, _value.getValue(), factoryMessage.buildMessages(facadeGame));
        afterModif();
        update(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
    }

//    @Override
//    protected boolean invalidKey(String _key) {
//        return !DataBase.isCorrectIdentifier(_key) || super.invalidKey(_key);
//    }

    public void tryRename() {
        if (getSelectedIndex() >= 0) {
            String next_ = destination.getText();
            if (!DataBase.isCorrectIdentifier(next_)) {
                return;
            }
            EditedCrudPair<String, StringMap<String>> entry_ = getList().get(getSelectedIndex());
            String old_ = entry_.getKey();
            factoryMessage.rename(facadeGame,old_,next_);
            if (!factoryMessage.contains(facadeGame,old_)) {
                factoryMessage.renameExp(facadeGame, old_, next_);
                getList().remove(getSelectedIndex());
                getList().add(new EditedCrudPair<String, StringMap<String>>(next_, entry_.getValue()));
                possibleSort();
                refresh();
                afterModif();
                update(old_,next_);
            }
        }
    }

    public AbsTextField getDestination() {
        return destination;
    }

    private void update(String _oldId, String _newId) {
        subscribedTranslations.updateRenamingId(_oldId,_newId,factoryMessage.mids(facadeGame));
        subscribedTranslations.update();
    }

}
