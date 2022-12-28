package code.gui;

import code.expressionlanguage.guicompos.*;

public final class CustGraphicListGeneratorStruct implements AbstractAdvGraphicListGeneratorStruct {
    @Override
    public GraphicListIntStruct newListSimple(GuiContextEl _ctx, String _className) {
        return new CustGraphicListStruct(_ctx,_className,true);
    }

    @Override
    public GraphicListIntStruct newListMult(GuiContextEl _ctx, String _className) {
        return new CustGraphicListStruct(_ctx,_className,false);
    }
}
