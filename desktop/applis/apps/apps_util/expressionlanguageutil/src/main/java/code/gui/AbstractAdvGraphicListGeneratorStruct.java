package code.gui;

import code.expressionlanguage.guicompos.*;

public interface AbstractAdvGraphicListGeneratorStruct {
    GraphicListIntStruct newListSimple(GuiContextEl _ctx, String _className);
    GraphicListIntStruct newListMult(GuiContextEl _ctx, String _className);
}
