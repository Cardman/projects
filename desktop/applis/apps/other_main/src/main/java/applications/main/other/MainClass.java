package applications.main.other;

import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.gui.initialize.AbstractProgramInfos;
import code.sys.impl.*;

public final class MainClass extends LaunchingApplicationsSysOther {

    public static void main(String... _args) {
        loadLaungage(_args, new LaunchingApplicationsSysOther());
    }
}
