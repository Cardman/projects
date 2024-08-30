package code.vi.sys;

import applications.code.converterimages.main.LaunchingConverter;
import code.converterimages.gui.MessagesConverter;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingConverterSys extends LaunchingConverter {
    public LaunchingConverterSys() {
        super(DefProgramInfos.build(MessagesConverter.APPS_CONVERTER));
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingConverterSys().loadLanguage(_args);
    }
}
