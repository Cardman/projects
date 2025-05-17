package aiki.gui.components.editor;

import aiki.db.ImageArrayBaseSixtyFour;
import aiki.facade.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelImgSelect {
    private final AbstractProgramInfos api;
    private final FacadeGame facade;
    private final SubscribedTranslationMessagesFactoryImgName imgRetrieverSub;
    private AbsPaintableLabel loaded;
    private GeneComponentModelEltEnumSub<String> name;

    public GeneComponentModelImgSelect(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationMessagesFactoryImgName _i) {
        api = _core;
        facade = _facade;
        imgRetrieverSub = _i;
    }
    public AbsCustComponent gene() {
        AbsCompoFactory compoFactory_ = api.getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        name = ConverterCommonMapUtil.buildImg(api,facade, imgRetrieverSub);
        form_.add(SubscribedTranslationList.line(api,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(api.currentLg())),MessagesEditorSelect.FILE_IMG_NAME,name.geneEnum()));
        loaded = compoFactory_.newAbsPaintableLabel();
        loaded.setEmptyIcon();
        form_.add(SubscribedTranslationList.line(api,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(api.currentLg())),MessagesEditorSelect.LOADED_IMG,loaded));
        return form_;
    }

    public GeneComponentModelEltEnumSub<String> getName() {
        return name;
    }
    public void updateValue(String _str) {
        getName().setupValue(_str);
        getName().getSelectUniq().getSelect().events(null);
    }

    public void updateImg() {
        ImageArrayBaseSixtyFour val_ = imgRetrieverSub.getImgRetriever().all(facade).getVal(name.tryRet());
        if (val_ == null) {
            loaded.setEmptyIcon();
            return;
        }
        loaded.setIcon(api.getImageFactory(), ConverterGraphicBufferedImage.decodeToImage(api.getImageFactory(), val_.getImage()));
    }
    public IdList<SubscribedTranslation> subs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getName().getSubs());
        ids_.add(new SubscribedTranslationSelectChangeText(getName().getSelectUniq(), imgRetrieverSub.getImgRetriever()));
        ids_.add(new SubscribedTranslationSelectChangeEvtsText(getName().getSelectUniq().getSelect()));
        return ids_;
    }
}
