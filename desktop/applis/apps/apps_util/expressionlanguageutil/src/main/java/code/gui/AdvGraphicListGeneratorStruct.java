package code.gui;

import code.expressionlanguage.guicompos.*;

public final class AdvGraphicListGeneratorStruct implements AbstractAdvGraphicListGeneratorStruct {
    @Override
    public GraphicListIntStruct newListMult(GuiContextEl _ctx, String _className) {
        return new GraphicListStruct(_ctx,_className,false);
    }

    @Override
    public GraphicListIntStruct newListSimple(GuiContextEl _ctx, String _className) {
        return new GraphicListStruct(_ctx,_className,true);
    }
}

