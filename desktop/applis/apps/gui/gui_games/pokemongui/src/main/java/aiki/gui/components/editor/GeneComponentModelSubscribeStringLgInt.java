package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class GeneComponentModelSubscribeStringLgInt implements AbsGeneComponentModelSubscribe<EditedCrudPair<String,LgInt>> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final GeneComponentModelText key;
    private final GeneComponentModelLgInt value;
    public GeneComponentModelSubscribeStringLgInt(AbstractProgramInfos _fact, FacadeGame _fac) {
        api = _fact;
        facadeGame = _fac;
        key = new GeneComponentModelText(_fact);
        value = new GeneComponentModelLgInt(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = api.getCompoFactory().newLineBox();
        form_.add(line(api,MessagesDataEffdamage.M_P_45_EVENT,key.geneString()));
        key.addComplete(facadeGame);
        form_.add(line(api,MessagesDataEffdamage.M_P_45_RATE_EVENT,value.geneLgInt()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            key.getTextPane().setEditable(false);
        }
        return form_;
    }
    private AbsCustComponent line(AbstractProgramInfos _core, String _key, AbsCustComponent _input) {
        return SubscribedTranslationList.lineDir(_core,formatTxt(_core,_key),_input);
    }

    private String formatTxt(AbstractProgramInfos _core,String _key) {
        return SubscribedTranslationList.formatTxt(_core, MessagesPkBean.EFF_DAMAGE, _key);
    }
    @Override
    public EditedCrudPair<String,LgInt> tryRet() {
        return new EditedCrudPair<String, LgInt>(key.valueString(), value.valueLgInt());
    }

    @Override
    public void setupValue(EditedCrudPair<String,LgInt> _value) {
        key.valueString(_value.getKey());
        value.valueLgInt(_value.getValue());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationRenamingId(key.getTextPane()));
        return ids_;
    }

}
