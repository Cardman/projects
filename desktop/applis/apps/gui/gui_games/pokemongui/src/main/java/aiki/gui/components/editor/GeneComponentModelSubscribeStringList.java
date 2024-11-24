package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStringList implements AbsGeneComponentModelSubscribe<StringList> {
    private final CrudGeneFormSimpleElementSub<String> crud;
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationMessagesFactory factory;

    public GeneComponentModelSubscribeStringList(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesFactory _f) {
        crud = new CrudGeneFormSimpleElementSub<String>(_fact, _facade, _sub, _fr);
        api = _fact;
        facadeGame = _facade;
        factory = _f;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        crud.initForm(new DisplayEntryCustSubElementSimpleImpl<String>(factory,api,facadeGame,new StringMap<String>()),buildPart(api,facadeGame,factory,new StringMap<String>()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            crud.getCrud().getAdd().setEnabled(false);
        }
        return crud.getGroup();
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    @Override
    public StringList tryRet() {
        return new StringList(crud.getList());
    }

    @Override
    public void setupValue(StringList _value) {
        crud.setupValues(_value);
        if (!crud.getCrud().getAdd().isEnabled()) {
            for (AbsButton b: crud.getCrud().getAllButtons()){
                b.setEnabled(false);
            }
        }
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud().subscribeButtons();
    }

    public CrudGeneFormSimpleElement<String> crud() {
        return crud.getCrud();
    }

    public CrudGeneFormSimpleElementSub<String> getCrud() {
        return crud;
    }
}
