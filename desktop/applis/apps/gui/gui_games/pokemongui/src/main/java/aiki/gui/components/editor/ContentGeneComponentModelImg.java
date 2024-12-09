package aiki.gui.components.editor;

import aiki.db.*;
import aiki.sml.*;
import code.gui.*;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.images.*;
import code.sml.*;
import code.stream.*;

public final class ContentGeneComponentModelImg {
    private ImageArrayBaseSixtyFour edited;
    private FileOpenDialogContent fileDialogContent;
    private AbsPaintableLabel loaded;
    private AbsTextField baseEncode;

    public AbsCustComponent gene(AbstractProgramInfos _core, AbsCustComponent _key) {
        edited = ImageArrayBaseSixtyFour.instance();
        fileDialogContent = new FileOpenDialogContent(_core.getThreadFactory().newAtomicBoolean(), _core.getThreadFactory().newAtomicBoolean(),_core);
        fileDialogContent.setFileOpenDialog(true,_core.getHomePath(),new DefPostFileFrameEventImg(fileDialogContent),new DefButtonsOpenPanelAct(new LoadDocImgEvent(_core,this)));
        AbsCompoFactory compoFactory_ = _core.getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        if (_key != null) {
            page_.add(_key);
        }
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(fileDialogContent.getContentPane());
        loaded = compoFactory_.newAbsPaintableLabel();
        loaded.setEmptyIcon();
        form_.add(loaded);
        baseEncode = compoFactory_.newTextField(64);
        form_.add(baseEncode);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    public void tryLoad(AbstractProgramInfos _core, String _path) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(StreamTextFile.contentsOfFile(_path, _core.getStreams()));
        if (doc_ == null) {
            loaded.setEmptyIcon();
            return;
        }
        String valueImg_ = doc_.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG);
        String baseMerged_ = BaseSixtyFourUtil.checkBase(doc_.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG_BASE), GamesPk.baseEncode(_core.getTranslations()));
        updateImg(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_),_core);
    }

    public void updateImg(ImageArrayBaseSixtyFour _i,AbstractProgramInfos _core) {
        setEdited(_i);
        baseEncode.setText(edited.getBase());
        int[][] img_ = edited.getImage();
        if (img_.length == 0) {
            loaded.setEmptyIcon();
        } else {
            loaded.setIcon(_core.getImageFactory(), ConverterGraphicBufferedImage.decodeToImage(_core.getImageFactory(), img_));
        }
    }
    public ImageArrayBaseSixtyFour edited(AbstractProgramInfos _core) {
        getEdited().setBase(BaseSixtyFourUtil.checkBase(getBaseEncode().getText(),GamesPk.baseEncode(_core.getTranslations())));
        return getEdited();
    }

    public ImageArrayBaseSixtyFour getEdited() {
        return edited;
    }

    public void setEdited(ImageArrayBaseSixtyFour _e) {
        this.edited = _e;
    }

    public AbsTextField getBaseEncode() {
        return baseEncode;
    }

    public FileOpenDialogContent getFileDialogContent() {
        return fileDialogContent;
    }

}
