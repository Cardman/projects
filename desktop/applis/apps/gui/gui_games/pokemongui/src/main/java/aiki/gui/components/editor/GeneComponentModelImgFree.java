package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelImgFree implements GeneComponentModel<EditedCrudPair<String, ImageArrayBaseSixtyFour>> {
    private final AbstractProgramInfos compoFactory;
    private final ContentGeneComponentModelImg content = new ContentGeneComponentModelImg();
    private AbsTextField key;
    public GeneComponentModelImgFree(AbstractProgramInfos _core) {
        compoFactory = _core;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        key = compoFactory.getCompoFactory().newTextField(64);
        return content.gene(compoFactory,SubscribedTranslationList.line(compoFactory,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(compoFactory.currentLg())),MessagesEditorSelect.IMG_FREE_NAME,key));
    }

    @Override
    public EditedCrudPair<String, ImageArrayBaseSixtyFour> value() {
        return new EditedCrudPair<String, ImageArrayBaseSixtyFour>(key.getText(), content.edited(compoFactory));
    }

    @Override
    public void value(EditedCrudPair<String, ImageArrayBaseSixtyFour> _v) {
        key.setText(_v.getKey());
        content.updateImg(ConverterCommonMapUtil.copyImageArrayBaseSixtyFour(_v.getValue()),compoFactory);
    }

    public AbsTextField getKey() {
        return key;
    }

    public ContentGeneComponentModelImg getContent() {
        return content;
    }

}
