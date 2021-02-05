package applications.main.other;

import aiki.main.AikiFactory;
import applications.main.LaunchingApplications;
import cards.main.CardFactories;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.gui.initialize.AbstractProgramInfos;
import code.sys.impl.AdvGraphicComboBoxGenerator;
import code.sys.impl.AdvGraphicListGenerator;
import code.sys.impl.AdvGraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public final class MainClass extends LaunchingApplications {
    public MainClass(AbstractProgramInfos _frames, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFactory) {
        super(_frames, _cardFactories, _aikiFactory, _guiFactory);
    }

    public static void main(String... _args) {
        loadLaungage(_args, new LaunchingApplications(new ProgramInfos(new AdvGraphicStringListGenerator(), new AdvGraphicComboBoxGenerator()), new CardFactories(new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>()), new AikiFactory(new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>(), new AdvGraphicListGenerator<>()), new GuiFactroy(new AdvGraphicListGenerator<>())));
    }
}
