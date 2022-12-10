package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.WindowStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctWindowCloseAll implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        closeAll(_cont, _stackCall);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

    public static void closeAll(ContextEl _cont, StackCall _stackCall) {
        CustList<WindowStruct> windows_ = new CustList<WindowStruct>();
        for (Struct s: ((GuiContextEl) _cont).getGuiInit().getWindows().toSnapshotArray(_cont, _stackCall).list()) {
            if (!(s instanceof WindowStruct)) {
                continue;
            }
            ((WindowStruct)s).setVisible(false);
            windows_.add((WindowStruct) s);
            ((WindowStruct)s).dispose();
        }
        for (WindowStruct w: windows_) {
            ((GuiContextEl) _cont).getGuiInit().getWindows().remove(w,false);
        }
    }
}
