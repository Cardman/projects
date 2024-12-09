package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.images.*;

public final class GeneComponentModelImgType extends GeneComponentModelEntity<String> {
    private AbsSpinner red;
    private AbsSpinner green;
    private AbsSpinner blue;

    public GeneComponentModelImgType(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fr, _core, _facade, _sub);
    }

    @Override
    public AbsCustComponent gene(int _select) {
        buildKey(_select, getSubscribedTranslationList().getFactoryTy(), getFacade().getData().getTypesColors().getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey());
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        red = compoFactory_.newSpinner(0,0,255,1);
        form_.add(red);
        green = compoFactory_.newSpinner(0,0,255,1);
        form_.add(green);
        blue = compoFactory_.newSpinner(0,0,255,1);
        form_.add(blue);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String, String> value() {
        return new EditedCrudPair<String, String>(getGeneComponentModelSelectKey().tryRet(), red.getValue()+DataBase.SEPARATOR_RGB+green.getValue()+DataBase.SEPARATOR_RGB+blue.getValue());
    }

    @Override
    public void value(EditedCrudPair<String, String> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        int i_ = ConverterBufferedImage.getIntColor(_v.getValue(), DataBase.SEPARATOR_RGB);
        red.setValue(GuiConstants.redPart(i_));
        green.setValue(GuiConstants.greenPart(i_));
        blue.setValue(GuiConstants.bluePart(i_));
    }

    public AbsSpinner getRed() {
        return red;
    }

    public AbsSpinner getGreen() {
        return green;
    }

    public AbsSpinner getBlue() {
        return blue;
    }


}
