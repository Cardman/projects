package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelImg extends GeneComponentModelEntity<ImageArrayBaseSixtyFour> {
    private final ContentGeneComponentModelImg content = new ContentGeneComponentModelImg();
    private final SubscribedTranslationMessagesFactoryCoreMessages<String> allMessages;
    private final SubscribedTranslationMessagesFactoryImg imgMess;
    public GeneComponentModelImg(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub, SubscribedTranslationMessagesFactoryCoreMessages<String> _a, SubscribedTranslationMessagesFactoryImg _i) {
        super(_fr, _core, _facade, _sub);
        allMessages = _a;
        imgMess = _i;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        buildKey(_select,allMessages,imgMess.all(getFacade()).getKeys());
        return content.gene(getCompoFactory(),SubscribedTranslationList.line(getCompoFactory(),MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(getCompoFactory().currentLg())),MessagesEditorSelect.IMG_ENT_NAME,geneComponentModelSelectKey()));
    }

    @Override
    public EditedCrudPair<String, ImageArrayBaseSixtyFour> value() {
        return new EditedCrudPair<String, ImageArrayBaseSixtyFour>(getGeneComponentModelSelectKey().tryRet(), content.edited(getCompoFactory()));
    }

    @Override
    public void value(EditedCrudPair<String, ImageArrayBaseSixtyFour> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        content.updateImg(ConverterCommonMapUtil.copyImageArrayBaseSixtyFour(_v.getValue()),getCompoFactory());
    }

    public ContentGeneComponentModelImg getContent() {
        return content;
    }

}
