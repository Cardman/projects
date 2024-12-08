package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.sml.*;
import code.gui.*;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.images.*;
import code.sml.*;
import code.stream.*;

public final class GeneComponentModelImg extends GeneComponentModelEntity<ImageArrayBaseSixtyFour> {
    private ImageArrayBaseSixtyFour edited;
    private FileOpenDialogContent fileDialogContent;
    private AbsPaintableLabel loaded;
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
        edited = ImageArrayBaseSixtyFour.instance();
        fileDialogContent = new FileOpenDialogContent(getCompoFactory().getThreadFactory().newAtomicBoolean(), getCompoFactory().getThreadFactory().newAtomicBoolean(),getCompoFactory());
        fileDialogContent.setFileOpenDialog(true,getCompoFactory().getHomePath(),new DefPostFileFrameEventImg(fileDialogContent),new DefButtonsOpenPanelAct(new LoadDocImgEvent(this)));
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey());
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(fileDialogContent.getContentPane());
        loaded = compoFactory_.newAbsPaintableLabel();
        loaded.setEmptyIcon();
        form_.add(loaded);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    public void tryLoad(String _path) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(StreamTextFile.contentsOfFile(_path, getCompoFactory().getStreams()));
        if (doc_ == null) {
            loaded.setEmptyIcon();
            return;
        }
        String valueImg_ = doc_.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG);
        String baseMerged_ = BaseSixtyFourUtil.checkBase(doc_.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG_BASE), GamesPk.baseEncode(getCompoFactory().getTranslations()));
        edited = ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_);
        updateImg();
    }

    @Override
    public EditedCrudPair<String, ImageArrayBaseSixtyFour> value() {
        return new EditedCrudPair<String, ImageArrayBaseSixtyFour>(getGeneComponentModelSelectKey().tryRet(),edited);
    }

    @Override
    public void value(EditedCrudPair<String, ImageArrayBaseSixtyFour> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        edited = _v.getValue();
        updateImg();
    }

    private void updateImg() {
        int[][] img_ = edited.getImage();
        if (img_.length == 0) {
            loaded.setEmptyIcon();
        } else {
            loaded.setIcon(getCompoFactory().getImageFactory(), ConverterGraphicBufferedImage.decodeToImage(getCompoFactory().getImageFactory(), img_));
        }
    }

    public ImageArrayBaseSixtyFour getEdited() {
        return edited;
    }

    public FileOpenDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
}
