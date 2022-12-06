package aiki.gui.dialogs;



import aiki.sml.Resources;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.network.WindowNetWork;
import code.network.enums.IpType;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class DialogServerAiki implements AbstractDialogServer{
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogserver";

    private static final String TITLE = "title";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String CREATE_SERVER = "createServer";
    private static final String JOIN_SERVER = "joinServer";
    private static final String CANCEL = "cancel";
    private final AbsDialog absDialog;
    private AbsTextField ipOrHostName;
    private ComboBox<IpType> ipType;
    private boolean create;
    private boolean join;
    private StringMap<String> messages;

//    private Map<String,String> messagesIp;

    private IdMap<IpType,String> messagesIpEnum;

    public DialogServerAiki(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(WindowNetWork _fenetre) {
        _fenetre.getDialogServerAiki().init(_fenetre);
    }
    private void init(WindowNetWork _fenetre) {
        absDialog.setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        join = false;
        create = false;
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _fenetre.getLanguageKey(), absDialog.getAccessFile());
//        messagesIp = FormatHtml.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), IpType.class);
        messagesIpEnum = new IdMap<IpType,String>();
//        for (String i: messagesIp.getKeys()) {
//            messagesIpEnum.put(IpType.valueOf(i), messagesIp.getVal(i));
//        }
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        absDialog.setLocationRelativeTo(_fenetre.getCommonFrame());
        absDialog.setResizable(false);
        absDialog.setTitle(messages.getVal(TITLE));
        ipOrHostName = _fenetre.getCompoFactory().newTextField();
        AbsPanel pane_ = _fenetre.getCompoFactory().newGrid(0, 1);
        AbsPanel panel_ = _fenetre.getCompoFactory().newGrid(0, 2);
        AbsPlainLabel ipServer_ = _fenetre.getCompoFactory().newPlainLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        IdList<IpType> list_ = new IdList<IpType>(IpType.values());
        ipType = new ComboBox<IpType>(_fenetre.getFrames().getGeneComboBox().createCombo(_fenetre.getImageFactory(),new StringList(), -1, _fenetre.getCompoFactory()));
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType.self());
        pane_.add(panel_);
        panel_ = _fenetre.getCompoFactory().newLineBox();
        AbsPlainButton button_ = _fenetre.getCompoFactory().newPlainButton(messages.getVal(CREATE_SERVER));
        button_.addActionListener(new CreateServerEvent(this));
        panel_.add(button_);
        button_ = _fenetre.getCompoFactory().newPlainButton(messages.getVal(JOIN_SERVER));
        button_.addActionListener(new JoinServerEvent(this));
        panel_.add(button_);
        button_ = _fenetre.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        button_.addActionListener(new ClosingDialogEvent(absDialog));
        panel_.add(button_);
        pane_.add(panel_);
        absDialog.setContentPane(pane_);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

    @Override
    public void createServerChoice() {
        join = false;
        create = true;
        closeWindow();
    }

    @Override
    public void joinServerChoice() {
        join = true;
        create = false;
        closeWindow();
    }

    public void closeWindow() {
        absDialog.closeWindow();
    }

    public static String getIpOrHostName(DialogServerAiki _dialog) {
        _dialog.absDialog.setVisible(true);
        return _dialog.ipOrHostName.getText();
    }

    public static IpType getIpType(DialogServerAiki _dialog) {
        return _dialog.ipType.getCurrent();
    }

    public static boolean isCreate(DialogServerAiki _dialog) {
        return _dialog.create;
    }

    public static boolean isChoosen(DialogServerAiki _dialog) {
        return _dialog.create || _dialog.join;
    }

}
