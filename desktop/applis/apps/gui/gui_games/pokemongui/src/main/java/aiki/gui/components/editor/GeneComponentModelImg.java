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

public abstract class GeneComponentModelImg extends GeneComponentModelEntity<ImageArrayBaseSixtyFour> {
    private ImageArrayBaseSixtyFour edited;
    private FileOpenDialogContent fileDialogContent;
    private AbsPaintableLabel loaded;
    protected GeneComponentModelImg(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fr, _core, _facade, _sub);
    }

    @Override
    public AbsCustComponent gene(int _select) {
        buildSelector(_select);
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
        int[][] img_ = edited.getImage();
        if (img_.length == 0) {
            loaded.setEmptyIcon();
        } else {
            loaded.setIcon(getCompoFactory().getImageFactory(), ConverterGraphicBufferedImage.decodeToImage(getCompoFactory().getImageFactory(), img_));
        }
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
    }

    public ImageArrayBaseSixtyFour getEdited() {
        return edited;
    }

    protected abstract void buildSelector(int _select);

    public FileOpenDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
}
