package code.vm.applications.main.young;

import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.gui.initialize.AbstractProgramInfos;
import code.vi.sys.impl.*;
import code.vi.sys.impl.variant.DefLaunchingApplicationsSysOther;

public final class MainClass {

    public static void main(String... _args) {
        DefLaunchingApplicationsSysOther.loadLaungage(_args);
    }
}
