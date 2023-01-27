package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.structs.*;
import code.gui.*;

public interface GraphicListIntStruct extends Struct {
    void add(int _index, Struct _img, Struct _elt);
    void add(GuiAliases _aliases, ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall, Struct _elt);
    void add(GuiAliases _aliases, ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall, int _index, Struct _elt);
    void set(int _index, Struct _img, Struct _elt);
    AbsGraphicListStr getGrList();
    Struct getRender();
    void setRender(Struct _r);
    void set(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt);
    void setSelectedIndexes(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,Struct _selectedIndexes);
    void clearSelection(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall);
}
