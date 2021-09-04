package applications.main.other;

import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.gui.initialize.AbstractProgramInfos;
import code.sys.impl.*;
import code.sys.impl.variant.DefLaunchingApplicationsSysOther;

public final class MainClass {

    public static void main(String... _args) {
        DefLaunchingApplicationsSysOther.loadLaungage(_args);
    }
}
