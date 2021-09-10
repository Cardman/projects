package code.vi.sys.impl;

import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.expressionlanguage.guicompos.GuiFactroy;

public final class LaunchingApplicationsSysOther extends LaunchingApplications {
    public LaunchingApplicationsSysOther() {
        super(new OtherProgramInfos(), new CardFactories(new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>()), new AikiFactory(new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>()), new GuiFactroy(new AdvGraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSysOther());
    }
}
