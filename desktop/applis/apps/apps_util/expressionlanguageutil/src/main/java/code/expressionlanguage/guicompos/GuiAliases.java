package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.OtherConfirmDialog;
import code.scripts.messages.gui.MessCdmGuiGr;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class GuiAliases {
    public static final String RESOURCES_LG_GUI_CHANGE_EVENT_TXT = "resources_lg_gui/change_event.txt";
    public static final String RESOURCES_LG_GUI_TREE_EVENT_TXT = "resources_lg_gui/tree_event.txt";
    public static final String RESOURCES_LG_GUI_TABLE_EVENT_TXT = "resources_lg_gui/table_event.txt";
    public static final String RESOURCES_LG_GUI_MOUSE_EVENT_TXT = "resources_lg_gui/mouse_event.txt";
    public static final String RESOURCES_LG_GUI_WHEEL_EVENT_TXT = "resources_lg_gui/wheel_event.txt";
    public static final String RESOURCES_LG_GUI_KEY_EVENT_TXT = "resources_lg_gui/key_event.txt";
    public static final String RESOURCES_LG_GUI_WINDOW_EVENT_TXT = "resources_lg_gui/window_event.txt";
    public static final String RESOURCES_LG_GUI_ACTION_EVENT_TXT = "resources_lg_gui/action_event.txt";
    public static final String RESOURCES_LG_GUI_LIST_EVENT_TXT = "resources_lg_gui/list_event.txt";
    public static final String RESOURCES_LG_GUI_REPAINT_TXT = "resources_lg_gui/repaint.txt";

    public static final String KW_PUBLIC = "{public}";
    public static final String KW_INTERFACE = "{interface}";
    public static final String KW_VOID = "{void}";
    public static final String METHOD_VALUE_CHANGED = "{valueChanged}";
    public static final String TYPE_INT = "{int}";
    public static final String TYPE_BOOLEAN = "{boolean}";
    public static final String KW_ABSTRACT = "{abstract}";
    public static final String KW_FINAL = "{final}";
    public static final String KW_CLASS = "{class}";
    public static final String TYPE_PAINT = "{Paint}";
    public static final String METHOD_PAINT = "{paint}";
    public static final String PARAM_8 = "{paintPar}";
    public static final String METHOD_ADD = "{add}";
    public static final String METHOD_SET = "{set}";
    public static final String PARAM_9 = "{setPar0}";
    public static final String PARAM_10 = "{setPar1}";
    public static final String PARAM_11 = "{setPar2}";
    public static final String PARAM_12 = "{addPar00}";
    public static final String PARAM_13 = "{addPar01}";
    public static final String PARAM_14 = "{addPar02}";
    public static final String PARAM_15 = "{addPar10}";
    public static final String PARAM_16 = "{addPar11}";
    public static final String PARAM_17 = "{refreshPar}";
    public static final String METHOD_REFRESH = "{refresh}";
    public static final String METHOD_REFRESH_ONE = "{refreshOne}";
    public static final String PARAM_18 = "{refreshPar0}";
    public static final String PARAM_19 = "{w}";
    public static final String PARAM_20 = "{h0}";
    public static final String PARAM_21 = "{o0}";
    public static final String PARAM_22 = "{s}";
    public static final String KW_STATIC = "{static}";
    public static final String KW_IF = "{if}";
    public static final String KW_ELSEIF = "{elseif}";
    public static final String KW_TRUE = "{true}";
    public static final String KW_FALSE = "{false}";
    public static final String KW_RETURN = "{return}";
    public static final String KW_BREAK = "{break}";
    public static final String KW_CONTINUE = "{continue}";
    public static final String KW_IS = "{is}";
    public static final String KW_WHILE = "{while}";
    public static final String KW_NULL = "{null}";
    public static final String KW_CAST = "{cast}";
    public static final String TYPE_COMPONENT = "{Component}";
    public static final String TYPE_PANEL = "{Panel}";
    public static final String TYPE_FCT = "{Fct}";
    public static final String METHOD_CALL = "{call}";
    public static final String METHOD_GET_COMPONENT = "{getComponent}";
    public static final String METHOD_NEXT = "{next}";
    public static final String METHOD_GET_PARENT = "{getParent}";
    public static final String METHOD_GET_PAINTING = "{getPainting}";
    public static final String PARAM_23 = "p";
    public static final String PARAM_24 = "r";
    public static final String PARAM_25 = "o";
    public static final String PARAM_26 = "la";
    public static final String PARAM_27 = "h";
    public static final String PARAM_28 = "hf";
    public static final String PARAM_29 = "lf";
    public static final String PARAM_30 = "pf";
    public static final String PARAM_31 = "par";
    public static final String PARAM_32 = "pan";
    public static final String PARAM_33 = "fct";
    public static final String PARAM_34 = "nb";
    public static final String PARAM_35 = "img";
    public static final String PARAM_36 = "ha";
    public static final String PARAM_37 = "ft";
    public static final String PARAM_38 = "text";
    public static final String PARAM_39 = "mw";
    public static final String PARAM_40 = "er";
    public static final String METHOD_GET_RENDER = "{getRender}";
    public static final String TYPE_MATH = "{Math}";
    public static final String METHOD_MAX = "{max}";
    public static final String TYPE_STRING_UTIL = "{StringUtil}";
    public static final String METHOD_VALUE_OF = "{valueOf}";
    public static final String METHOD_WIDTH_STR = "{widthStr}";
    public static final String METHOD_SET_COLOR = "{setColor}";
    public static final String METHOD_FILL_RECT = "{fillRect}";
    public static final String METHOD_DRAW_STRING = "{drawString}";
    public static final String METHOD_GET_FONT = "{getFont}";
    public static final String METHOD_SIZE = "{size}";
    public static final String TYPE_COLOR = "{Color}";
    public static final String METHOD_GET_COMP_WIDTH = "{getCompWidth}";
    public static final String KW_ELSE = "{else}";
    public static final String METHOD_SET_FONT = "{setFont}";
    public static final String METHOD_GET_HEIGHT = "{getHeight}";
    public static final String METHOD_GET_WIDTH = "{getWidth}";
    public static final String METHOD_GET_PAINT = "{getPaint}";
    public static final String TYPE_IMAGE = "{Image}";
    public static final String TYPE_IMAGE_LABEL = "{ImageLabel}";
    public static final String METHOD_ADD_COMPO = "{addCompo}";
    public static final String METHOD_SET_COMPO = "{setCompo}";
    public static final String METHOD_GET_VIEW = "{getView}";
    public static final String METHOD_GET_SELECTED = "{getSelected}";
    public static final String METHOD_UPDATE_GRAPHICS = "{updateGraphics}";
    public static final String METHOD_LENGTH = "{length}";
    public static final String KW_FOR = "{for}";
    public static final String KW_NEW = "{new}";
    public static final String KW_VAR = "{var}";
    public static final String TYPE_GR_LIST = "{GrList}";
    public static final String TYPE_OBJECT = "{Object}";
    public static final String METHOD_DISPOSE = "{dispose}";
    public static final String TYPE_ACTION_LISTENER = "{ActionListener}";
    public static final String METHOD_ACTION_PERFORMED = "{actionPerformed}";
    public static final String TYPE_ACTION_EVENT = "{ActionEvent}";
    public static final String TYPE_CHANGE_LISTENER = "{ChangeListener}";
    public static final String METHOD_STATE_CHANGED = "{stateChanged}";
    public static final String TYPE_TREE_LISTENER = "{TreeListener}";
    public static final String TYPE_TREE_NODE = "{TreeNode}";
    public static final String TYPE_TABLE_LISTENER = "{TableListener}";
    public static final String TYPE_MOUSE_LISTENER = "{MouseListener}";
    public static final String METHOD_MOUSE_CLICKED = "{mouseClicked}";
    public static final String METHOD_MOUSE_PRESSED = "{mousePressed}";
    public static final String METHOD_MOUSE_RELEASED = "{mouseReleased}";
    public static final String METHOD_MOUSE_ENTERED = "{mouseEntered}";
    public static final String METHOD_MOUSE_EXITED = "{mouseExited}";
    public static final String METHOD_MOUSE_DRAGGED = "{mouseDragged}";
    public static final String METHOD_MOUSE_MOVED = "{mouseMoved}";
    public static final String TYPE_MOUSE_EVENT = "{MouseEvent}";
    public static final String TYPE_WHEEL_LISTENER = "{WheelListener}";
    public static final String METHOD_MOUSE_MOVE_WHEEL = "{mouseMoveWheel}";
    public static final String TYPE_MOUSE_WHEEL_EVENT = "{MouseWheelEvent}";
    public static final String TYPE_KEY_LISTENER = "{KeyListener}";
    public static final String METHOD_KEY_PRESSED = "{keyPressed}";
    public static final String METHOD_KEY_TYPED = "{keyTyped}";
    public static final String METHOD_KEY_RELEASED = "{keyReleased}";
    public static final String TYPE_KEY_EVENT = "{KeyEvent}";
    public static final String TYPE_WINDOW_LISTENER = "{WindowListener}";
    public static final String METHOD_WINDOW_OPENED = "{windowOpened}";
    public static final String METHOD_WINDOW_CLOSING = "{windowClosing}";
    public static final String METHOD_WINDOW_CLOSED = "{windowClosed}";
    public static final String METHOD_WINDOW_ICONIFIED = "{windowIconified}";
    public static final String METHOD_WINDOW_DEICONIFIED = "{windowDeiconified}";
    public static final String METHOD_WINDOW_ACTIVATED = "{windowActivated}";
    public static final String METHOD_WINDOW_DEACTIVATED = "{windowDeactivated}";
    public static final String TYPE_WINDOW_EVENT = "{WindowEvent}";
    public static final String TYPE_LIST_SELECTION = "{ListSelection}";
    public static final String PARAM_1 = "{a}";
    public static final String PARAM_2 = "{b}";
    public static final String PARAM_3 = "c";
    public static final String PARAM_4 = "{d}";
    public static final String PARAM_5 = "{e}";
    public static final String PARAM_6 = "{f}";
    public static final String PARAM_7 = "{g}";

    private static final String WINDOW_SET="_____1272";
    private static final String FRAME="825";
    private static final String CONFIRM="_____1229";
    private static final String DIALOG="_____1230";
    private static final String WINDOW_TYPE="826";
    private static final String COMPONENT="827";
    private static final String ACTION_EVENT="___1060";
    private static final String MOUSE_EVENT="___1032";
    private static final String TABLE_LISTENER="_____1231";
    private static final String TABLE_GUI="_____1232";
    private static final String TREE_LISTENER="_____1233";
    private static final String TREE="_____1234";
    private static final String TREE_NODE="_____1235";
    private static final String KEY_EVENT="_____1236";
    private static final String WINDOW_EVENT="_____1237";
    private static final String PANEL="828";
    private static final String TABBED_PANE="_____1238";
    private static final String PANEL_BORDER="829";
    private static final String BUTTON="__1023";
    private static final String PROG_BAR="_____1239";
    private static final String CHECK_BOX="_____1240";
    private static final String RADIO="_____1241";
    private static final String TEXT_LABEL="830";
    private static final String IMAGE="831";
    private static final String IMAGE_LABEL="832";
    private static final String COLOR="833";
    private static final String INPUT="__1024";
    private static final String FONT="834";
    private static final String TEXT_AREA="_____1242";
    private static final String TEXT_FIELD="_____1243";
    private static final String GR_LIST="_____1244";
    private static final String COMBO="_____1245";
    private static final String BUTTON_GROUP="_____1246";
    private static final String RENDER="_____1247";
    private static final String POPUP_MENU="_____1248";
    private static final String DIMENSION="835";
    private static final String KEY_LISTENER="_____1249";
    private static final String MOUSE_LISTENER="___1031";
    private static final String WHEEL_LISTENER="___1106";
    private static final String WHEEL_EVENT="___1107";
    private static final String ACTION_LISTENER="___1059";
    private static final String ACTION="___1086";
    private static final String CHANGE_LISTENER="_____1250";
    private static final String WINDOW_LISTENER="_____1251";
    private static final String SCROLL_PANE="_____1252";
    private static final String SPLIT_PANE="_____1253";
    private static final String LIST_SELECTION="_____1254";
    private static final String PAINT="_____1255";
    private static final String MENU_BAR="_____1256";
    private static final String ABS_MENU="_____1257";
    private static final String MENU="_____1258";
    private static final String ABS_MENU_ITEM="_____1259";
    private static final String MENU_ITEM="_____1260";
    private static final String MENU_ITEM_CHECK="_____1261";
    private static final String SPINNER="_____1262";
    private static final String SLIDER="_____1263";
    private static final String COMMAND="___1085";
    private static final String CONFIRM_FIELD="_____1264";
    private static final String CONFIRM_FULL="_____1265";
    private static final String CONFIRM_MESSAGE="_____1266";
    private static final String CONFIRM_OK="_____1267";
    private static final String CONFIRM_YES_NO="_____1268";
    private static final String PACK="__918";
    private static final String ADD_WINDOW_LISTENER="_____1269";
    private static final String REMOVE_WINDOW_LISTENER="_____1270";
    private static final String GET_WINDOW_LISTENERS="_____1271";
    private static final String DISPOSE="___________2135";
    private static final String WINDOW_TYPE_RELATIVE="__919";
    private static final String IS_VISIBLE="__920";
    private static final String SET_VISIBLE="__921";
    private static final String SET_CONTENT="__922";
    private static final String GET_MENU_BAR="__923";
    private static final String SET_MENU_BAR="__924";
    private static final String WINDOW="null";
    private static final String WINDOW_SET_ADD="_____1273";
    private static final String WINDOW_SET_ALL="_____1274";
    private static final String WINDOW_SET_CONTAINS="_____1275";
    private static final String WINDOW_SET_REMOVE="_____1276";
    private static final String WINDOW_SET_SNAPSHOT="_____1277";
    private static final String DIALOG_IS_MODAL="_____1278";
    private static final String DIALOG_SET_MODAL="_____1279";
    private static final String CLOSE_ALL="__930";
    private static final String ACTION_EVENT_IS_ALT="___1063";
    private static final String ACTION_EVENT_IS_SHIFT="___1064";
    private static final String ACTION_EVENT_IS_CTRL="___1065";
    private static final String ACTION_EVENT_COMMAND="___1066";
    private static final String MOUSE_EVENT_GET_FIRST="___1047";
    private static final String MOUSE_EVENT_GET_SECOND="___1048";
    private static final String MOUSE_EVENT_GET_CLICKS="___1049";
    private static final String MOUSE_EVENT_IS_ALT="___1050";
    private static final String MOUSE_EVENT_IS_SHIFT="___1051";
    private static final String MOUSE_EVENT_IS_CTRL="___1052";
    private static final String MOUSE_EVENT_IS_LEFT="___1053";
    private static final String MOUSE_EVENT_IS_RIGHT="___1054";
    private static final String MOUSE_EVENT_IS_MIDDLE="___1055";
    private static final String WHEEL_ROTATED_CLICKS="___1110";
    private static final String KEY_EVENT_CODE="_____1280";
    private static final String KEY_EVENT_CHAR="_____1281";
    private static final String KEY_EVENT_IS_ALT="_____1282";
    private static final String KEY_EVENT_IS_SHIFT="_____1283";
    private static final String KEY_EVENT_IS_CTRL="_____1284";
    private static final String COUNT="836";
    private static final String GET_INDEX_COMPO="837";
    private static final String ADD_COMPO="838";
    private static final String REMOVE_COMPO="839";
    private static final String PANEL_ABSOLUTE="840";
    private static final String PANEL_FLOW="841";
    private static final String PANEL_PAGE_BOX="842";
    private static final String PANEL_GRID="843";
    private static final String PANEL_VALIDATE="844";
    private static final String REMOVE_ALL="845";
    private static final String GET_PARENT_COMPO="846";
    private static final String GET_NEXT_COMPO="847";
    private static final String COMPONENT_REPAINT="_____1285";
    private static final String COMPONENT_GET_PAINT="_____1286";
    private static final String COMPONENT_SET_PAINT="_____1287";
    private static final String GET_FONT="848";
    private static final String SET_FONT="849";
    private static final String COMPONENT_GET_HEIGHT="850";
    private static final String COMPONENT_GET_WIDTH="851";
    private static final String COMPONENT_IS_AUTOSCROLLS="852";
    private static final String COMPONENT_SET_AUTOSCROLLS="853";
    private static final String COMPONENT_GET_PREFERRED_SIZE="854";
    private static final String COMPONENT_SET_PREFERRED_SIZE="855";
    private static final String COMPONENT_SET_SIZE="856";
    private static final String COMPONENT_IS_VISIBLE="857";
    private static final String COMPONENT_SET_VISIBLE="858";
    private static final String COMPONENT_INVOKE_LATER="_____1288";
    private static final String ADD_KEY_LISTENER="_____1289";
    private static final String REMOVE_KEY_LISTENER="_____1290";
    private static final String GET_KEY_LISTENERS="_____1291";
    private static final String ADD_WHEEL_LISTENER="___1101";
    private static final String REMOVE_WHEEL_LISTENER="___1102";
    private static final String GET_WHEEL_LISTENERS="___1103";
    private static final String ADD_LISTENER="___1029";
    private static final String REMOVE_LISTENER="___1030";
    private static final String GET_LISTENERS="___1058";
    private static final String REQUEST_FOCUS="___1081";
    private static final String COMP_BACK="859";
    private static final String COMPO_REL_LEFT="860";
    private static final String COMPO_REL_RIGHT="861";
    private static final String COMPO_REL_TOP="862";
    private static final String COMPO_REL_BOTTOM="863";
    private static final String COMPO_REL_CENT_HORIZ="864";
    private static final String COMPO_REL_CENT_VERT="865";
    private static final String COMP_FOCUSABLE="866";
    private static final String COMP_FORE="867";
    private static final String COMP_GET_FIRST_POS="868";
    private static final String COMP_GET_SECOND_POS="869";
    private static final String COMP_OPAQUE="870";
    private static final String COMP_TOOL_TIP="871";
    private static final String COMP_LOC="872";
    private static final String COMP_BOR_LINE="873";
    private static final String COMP_BOR_LOWER="874";
    private static final String COMP_BOR_RAISE="875";
    private static final String COMP_BOR_TITLE="876";
    private static final String COMPONENT_BIND="___1082";
    private static final String COMPONENT_UNBIND="___1083";
    private static final String COMPONENT_COMMANDS="___1084";
    private static final String TABBED_PANE_NB="_____1292";
    private static final String TABBED_PANE_ADD="_____1293";
    private static final String TABBED_PANE_REMOVE="_____1294";
    private static final String TABBED_PANE_SEL_INDEX="_____1295";
    private static final String TABBED_PANE_INDEX="_____1296";
    private static final String TABBED_PANE_GET="_____1297";
    private static final String TABBED_PANE_GET_TITLE="_____1298";
    private static final String TABBED_PANE_SET="_____1299";
    private static final String TABBED_PANE_SET_TITLE="_____1300";
    private static final String SET_LABEL_TEXT="_907";
    private static final String SET_LABEL_IMAGE="_908";
    private static final String PROG_BAR_OR="_____1301";
    private static final String PROG_BAR_VALUE="_____1302";
    private static final String PROG_BAR_MIN="_____1303";
    private static final String PROG_BAR_MAX="_____1304";
    private static final String SCROLL_PANE_HORIZONTAL_VALUE="_____1305";
    private static final String SCROLL_PANE_VERTICAL_VALUE="_____1306";
    private static final String SCROLL_PANE_GET_VIEW="_____1307";
    private static final String SCROLL_PANE_SET_VIEW="_____1308";
    private static final String SCROLL_PANE_VALIDATE="_____1309";
    private static final String SPLIT_PANE_GET_DIVIDER_LOCATION="_____1310";
    private static final String SPLIT_PANE_SET_DIVIDER_LOCATION="_____1311";
    private static final String SPLIT_PANE_GET_DIVIDER_SIZE="_____1312";
    private static final String SPLIT_PANE_SET_DIVIDER_SIZE="_____1313";
    private static final String SPLIT_PANE_GET_LEFT="_____1314";
    private static final String SPLIT_PANE_SET_LEFT="_____1315";
    private static final String SPLIT_PANE_GET_RIGHT="_____1316";
    private static final String SPLIT_PANE_SET_RIGHT="_____1317";
    private static final String SPLIT_PANE_IS_CONTINUOUS_LAYOUT="_____1318";
    private static final String SPLIT_PANE_SET_CONTINUOUS_LAYOUT="_____1319";
    private static final String SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE="_____1320";
    private static final String SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE="_____1321";
    private static final String SPLIT_PANE_VALIDATE="_____1322";
    private static final String INPUT_IS_ENABLED="__1025";
    private static final String INPUT_SET_ENABLED="__1026";
    private static final String CHECK_BOX_ADD_ACTION="_____1323";
    private static final String CHECK_BOX_GET_TEXT="_____1324";
    private static final String CHECK_BOX_SET_TEXT="_____1325";
    private static final String CHECK_BOX_IS_SELECTED="_____1326";
    private static final String CHECK_BOX_SET_SELECTED="_____1327";
    private static final String SPINNER_GET_MAX="_____1328";
    private static final String SPINNER_GET_MIN="_____1329";
    private static final String SPINNER_GET_STEP="_____1330";
    private static final String SPINNER_GET_VALUE="_____1331";
    private static final String SPINNER_SET_MAX="_____1332";
    private static final String SPINNER_SET_MIN="_____1333";
    private static final String SPINNER_SET_STEP="_____1334";
    private static final String SPINNER_SET_VALUE="_____1335";
    private static final String SPINNER_SET_RANGE="_____1336";
    private static final String SPINNER_SET_RANGE_VALUE="_____1337";
    private static final String ADD_CHANGE="_____1338";
    private static final String SLIDER_GET_MAX="_____1339";
    private static final String SLIDER_GET_MIN="_____1340";
    private static final String SLIDER_GET_ORIENTATION="_____1341";
    private static final String SLIDER_GET_VALUE="_____1342";
    private static final String SLIDER_SET_MAX="_____1343";
    private static final String SLIDER_SET_MIN="_____1344";
    private static final String SLIDER_SET_ORIENTATION="_____1345";
    private static final String SLIDER_SET_VALUE="_____1346";
    private static final String RADIO_GET_TEXT="_____1347";
    private static final String RADIO_SET_TEXT="_____1348";
    private static final String RADIO_IS_SELECTED="_____1349";
    private static final String RADIO_SET_SELECTED="_____1350";
    private static final String TEXT_FIELD_ADD_ACTION="_____1351";
    private static final String TEXT_FIELD_ADD_POPUP="_____1352";
    private static final String TEXT_FIELD_GET_TEXT="_____1353";
    private static final String TEXT_FIELD_SET_TEXT="_____1354";
    private static final String TEXT_AREA_APPEND="_____1355";
    private static final String TEXT_AREA_INSERT="_____1356";
    private static final String TEXT_AREA_REPLACE_RANGE="_____1357";
    private static final String TEXT_AREA_REPLACE_SELECTION="_____1358";
    private static final String TEXT_AREA_GET_SELECTED_TEXT="_____1359";
    private static final String TEXT_AREA_SET_SELECTION_START="_____1360";
    private static final String TEXT_AREA_SET_SELECTION_END="_____1361";
    private static final String TEXT_AREA_GET_TAB_SIZE="_____1362";
    private static final String TEXT_AREA_SET_TAB_SIZE="_____1363";
    private static final String TEXT_AREA_GET_TEXT="_____1364";
    private static final String TEXT_AREA_SET_TEXT="_____1365";
    private static final String TEXT_AREA_SELECT="_____1366";
    private static final String TEXT_AREA_SELECT_ALL="_____1367";
    private static final String COMBO_ADD_ITEM="_____1368";
    private static final String COMBO_GET_ITEM_COUNT="_____1369";
    private static final String COMBO_GET_LISTENERS="_____1370";
    private static final String COMBO_GET_SELECTED_INDEX="_____1371";
    private static final String COMBO_GET_SELECTED_INDEXES="_____1372";
    private static final String COMBO_GET_SELECTED_ITEM="_____1373";
    private static final String COMBO_REMOVE_ALL_ITEMS="_____1374";
    private static final String COMBO_REMOVE_ITEM="_____1375";
    private static final String COMBO_SELECT_ITEM="_____1376";
    private static final String COMBO_ADD_LISTENER="_____1377";
    private static final String COMBO_REMOVE_LISTENER="_____1378";
    private static final String GR_LIST_ADD="_____1379";
    private static final String GR_LIST_CLEAR="_____1380";
    private static final String GR_LIST_CLEAR_SELECTION="_____1381";
    private static final String GR_LIST_GET_LIST_VIEW="_____1382";
    private static final String GR_LIST_GET_RENDER="_____1383";
    private static final String GR_LIST_GET_SELECTED_INDEXES="_____1384";
    private static final String GR_LIST_GET_SELECTIONS="_____1385";
    private static final String GR_LIST_GET_VISIBLE_ROW_COUNT="_____1386";
    private static final String GR_LIST_REMOVE="_____1387";
    private static final String GR_LIST_SET="_____1388";
    private static final String GR_LIST_SET_RENDER="_____1389";
    private static final String GR_LIST_SET_SELECTED_INDEXES="_____1390";
    private static final String GR_LIST_ADD_SELECTION="_____1391";
    private static final String GR_LIST_REMOVE_SELECTION="_____1392";
    private static final String GR_LIST_SET_VISIBLE_ROW_COUNT="_____1393";
    private static final String GR_LIST_UPDATE_GRAPHICS="_____1394";
    private static final String POPUP_MENU_ADD="_____1395";
    private static final String POPUP_MENU_ADD_MENU="_____1396";
    private static final String POPUP_MENU_GET_COMP="_____1397";
    private static final String POPUP_MENU_REMOVE_COMP="_____1398";
    private static final String POPUP_MENU_NB_COMP="_____1399";
    private static final String POPUP_MENU_GET_MENU="_____1400";
    private static final String POPUP_MENU_REMOVE_MENU="_____1401";
    private static final String POPUP_MENU_NB_MENU="_____1402";
    private static final String POPUP_MENU_SHOW="_____1403";
    private static final String TREE_ADD_TREE_LISTENER="_____1404";
    private static final String TREE_GET_SELECTED="_____1405";
    private static final String TREE_IS_ROOT_VISIBLE="_____1406";
    private static final String TREE_SET_ROOT_VISIBLE="_____1407";
    private static final String TREE_RELOAD="_____1408";
    private static final String TABLE_ADD_HEADER="_____1409";
    private static final String TABLE_ADD_SELECT="_____1410";
    private static final String TABLE_APPLY_CHANGES="_____1411";
    private static final String TABLE_ADD_INTERVAL="_____1412";
    private static final String TABLE_REMOVE_INTERVAL="_____1413";
    private static final String TABLE_MOVE_COLUMN="_____1414";
    private static final String TABLE_GET_COLUMN_AT_POINT="_____1415";
    private static final String TABLE_GET_COLUMN_COUNT="_____1416";
    private static final String TABLE_GET_COLUMN_NAME="_____1417";
    private static final String TABLE_GET_ROW_AT_POINT="_____1418";
    private static final String TABLE_GET_ROW_COUNT="_____1419";
    private static final String TABLE_GET_SELECTED_ROW="_____1420";
    private static final String TABLE_GET_SELECTED_ROW_COUNT="_____1421";
    private static final String TABLE_GET_SELECTED_ROWS="_____1422";
    private static final String TABLE_GET_VALUE="_____1423";
    private static final String TABLE_SET_COLUMNS="_____1424";
    private static final String TABLE_SET_MULTIPLE="_____1425";
    private static final String TABLE_SET_REORDER="_____1426";
    private static final String TABLE_SET_ROW_COUNT="_____1427";
    private static final String TABLE_SET_VALUE="_____1428";
    private static final String TABLE_IS_MULTIPLE="_____1429";
    private static final String TABLE_IS_REORDER="_____1430";
    private static final String DIMENSION_GET_HEIGHT="877";
    private static final String DIMENSION_GET_WIDTH="878";
    private static final String FONT_GET_NAME="879";
    private static final String FONT_GET_SIZE="880";
    private static final String FONT_IS_BOLD="881";
    private static final String FONT_IS_ITALIC="882";
    private static final String FONT_STRING_WIDTH="883";
    private static final String BUTTON_GROUP_ADD="_____1431";
    private static final String RENDER_GET_HEIGHT="_____1432";
    private static final String RENDER_GET_PAINT="_____1433";
    private static final String RENDER_GET_WIDTH="_____1434";
    private static final String RENDER_SET_HEIGHT="_____1435";
    private static final String RENDER_SET_PAINT="_____1436";
    private static final String RENDER_SET_WIDTH="_____1437";
    private static final String COLOR_ALPHA="884";
    private static final String COLOR_BLUE="885";
    private static final String COLOR_RED="886";
    private static final String COLOR_GREEN="887";
    private static final String COLOR_IS_TRANSPARENT="888";
    private static final String IMAGE_DRAW="889";
    private static final String IMAGE_DRAW_LINE="890";
    private static final String IMAGE_DRAW_OVAL="891";
    private static final String IMAGE_DRAW_POLYGON="892";
    private static final String IMAGE_DRAW_RECT="893";
    private static final String IMAGE_FILL_OVAL="894";
    private static final String IMAGE_FILL_POLYGON="895";
    private static final String IMAGE_FILL_RECT="896";
    private static final String IMAGE_GET="897";
    private static final String IMAGE_SET="898";
    private static final String IMAGE_EQ="899";
    private static final String IMAGE_GET_COLOR="900";
    private static final String IMAGE_SET_COLOR="901";
    private static final String IMAGE_GET_FONT="902";
    private static final String IMAGE_SET_FONT="903";
    private static final String IMAGE_GET_HEIGHT="904";
    private static final String IMAGE_GET_WIDTH="905";
    private static final String IMAGE_IS_WITH_ALPHA="906";
    private static final String IMAGE_DISPOSE="__929";
    private static final String ACTION_PERFORMED="___1061";
    private static final String ACTION_WRAP="___1087";
    private static final String ACTION_ENABLED="___1088";
    private static final String ACTION_ARG="___1089";
    private static final String STATE_CHANGED="_____1438";
    private static final String WHEEL_MOVE="___1108";
    private static final String MOUSE_CLICKED="___1033";
    private static final String MOUSE_ENTERED="___1034";
    private static final String MOUSE_EXITED="___1035";
    private static final String MOUSE_PRESSED="___1036";
    private static final String MOUSE_DRAGGED="___1038";
    private static final String MOUSE_MOVED="___1039";
    private static final String MOUSE_RELEASED="___1037";
    private static final String KEY_TYPED="_____1439";
    private static final String KEY_RELEASED="_____1440";
    private static final String KEY_PRESSED="_____1441";
    private static final String WINDOW_ACTIVATED="_____1442";
    private static final String WINDOW_ICONIFIED="_____1443";
    private static final String WINDOW_DEACTIVATED="_____1444";
    private static final String WINDOW_DEICONIFIED="_____1445";
    private static final String WINDOW_OPENED="_____1446";
    private static final String WINDOW_CLOSING="_____1447";
    private static final String WINDOW_CLOSED="_____1448";
    private static final String VALUE_CHANGED="_____1449";
    private static final String TREE_LISTENER_VALUE_CHANGED="_____1450";
    private static final String TREE_NODE_ADD="_____1451";
    private static final String TREE_NODE_INSERT="_____1452";
    private static final String TREE_NODE_REMOVE="_____1453";
    private static final String TREE_NODE_REMOVE_FROM_PARENT="_____1454";
    private static final String TREE_NODE_REMOVE_ALL_CHILDREN="_____1455";
    private static final String TREE_NODE_SET_USER_OBJECT="_____1456";
    private static final String TREE_NODE_GET_USER_OBJECT="_____1457";
    private static final String TREE_NODE_EQ="_____1458";
    private static final String TREE_NODE_NB="_____1459";
    private static final String TREE_NODE_GET_FIRST_CHILD="_____1460";
    private static final String TREE_NODE_GET_LAST_CHILD="_____1461";
    private static final String TREE_NODE_GET_NEXT_SIBLING="_____1462";
    private static final String TREE_NODE_GET_PREVIOUS_SIBLING="_____1463";
    private static final String TREE_NODE_GET_PARENT_NODE="_____1464";
    private static final String TREE_NODE_IS_ANCESTOR="_____1465";
    private static final String TREE_NODE_IS_DESCENDANT="_____1466";
    private static final String TABLE_VALUE_TABLE_CHANGED="_____1467";
    private static final String PAINT_METHOD="_____1468";
    private static final String PAINT_ADD="_____1469";
    private static final String PAINT_SET="_____1470";
    private static final String PAINT_REFRESH="_____1471";
    private static final String PAINT_REFRESH_ONE="_____1472";
    private static final String MENU_BAR_ADD="_____1473";
    private static final String MENU_BAR_GET="_____1474";
    private static final String MENU_BAR_REMOVE="_____1475";
    private static final String MENU_BAR_NB="_____1476";
    private static final String ABS_MENU_GET_PARENT="_____1477";
    private static final String ABS_MENU_GET_TEXT="_____1478";
    private static final String ABS_MENU_IS_ENABLED="_____1479";
    private static final String ABS_MENU_SET_DEEP_ENABLED="_____1480";
    private static final String ABS_MENU_SET_ENABLED="_____1481";
    private static final String ABS_MENU_SET_TEXT="_____1482";
    private static final String MENU_ADD="_____1483";
    private static final String MENU_GET="_____1484";
    private static final String MENU_REMOVE="_____1485";
    private static final String MENU_NB="_____1486";
    private static final String MENU_ADD_SEPARATOR="_____1487";
    private static final String ABS_MENU_ITEM_ADD_ACTION="_____1488";
    private static final String MENU_ITEM_CHECK_IS_SELECTED="_____1489";
    private static final String MENU_ITEM_CHECK_SET_SELECTED="_____1490";
    private static final String COMMAND_BINDING="___1092";
    private static final String COMMAND_ACTION="___1093";
    private static final String CONFIRM_FIELD_OK="_____1493";
    private static final String CONFIRM_FIELD_YES="_____1494";
    private static final String CONFIRM_FIELD_NO="_____1495";
    private static final String CONFIRM_FIELD_CANCEL="_____1496";
    private static final String PANEL_BORDER_AFTER_LAST="__909";
    private static final String PANEL_BORDER_AFTER_LINE_ENDS="__910";
    private static final String PANEL_BORDER_BEFORE_FIRST="__911";
    private static final String PANEL_BORDER_BEFORE_LINE_BEGINS="__912";
    private static final String PANEL_BORDER_EAST="__913";
    private static final String PANEL_BORDER_WEST="__914";
    private static final String PANEL_BORDER_NORTH="__915";
    private static final String PANEL_BORDER_SOUTH="__916";
    private static final String PANEL_BORDER_CENTER="__917";
    private String aliasActionListener;
    private String aliasAction;
    private String aliasActionWrap;
    private String aliasActionEnabled;
    private String aliasActionArg;
    private String aliasActionPerformed;
    private String aliasActionEvent;
    private String aliasActionEventIsAlt;
    private String aliasActionEventIsCtrl;
    private String aliasActionEventIsShift;
    private String aliasActionEventCommand;
    private String aliasWheelListener;
    private String aliasWheelMove;
    private String aliasWheelEvent;
    private String aliasWheelRotatedClicks;
    private String aliasAddChange;
    private String aliasTreeListener;
    private String aliasTreeListenerValueChanged;
    private String aliasTreeNode;
    private String aliasTreeNodeAdd;
    private String aliasTreeNodeInsert;
    private String aliasTreeNodeRemove;
    private String aliasTreeNodeRemoveFromParent;
    private String aliasTreeNodeRemoveAllChildren;
    private String aliasTreeNodeSetUserObject;
    private String aliasTreeNodeEq;
    private String aliasTreeNodeNb;
    private String aliasTreeNodeGetFirstChild;
    private String aliasTreeNodeGetLastChild;
    private String aliasTreeNodeGetNextSibling;
    private String aliasTreeNodeGetPreviousSibling;
    private String aliasTreeNodeGetParentNode;
    private String aliasTreeNodeGetUserObject;
    private String aliasTreeNodeIsAncestor;
    private String aliasTreeNodeIsDescendant;
    private String aliasTree;
    private String aliasTreeSetRootVisible;
    private String aliasTreeIsRootVisible;
    private String aliasTreeGetSelected;
    private String aliasTreeAddTreeListener;
    private String aliasTreeReload;
    private String aliasTableListener;
    private String aliasTableValueTableChanged;
    private String aliasTableGui;
    private String aliasTableGetSelectedRow;
    private String aliasTableGetSelectedRows;
    private String aliasTableGetSelectedRowCount;
    private String aliasTableGetRowCount;
    private String aliasTableSetRowCount;
    private String aliasTableGetColumnCount;
    private String aliasTableSetColumns;
    private String aliasTableGetColumnName;
    private String aliasTableGetValue;
    private String aliasTableSetValue;
    private String aliasTableGetRowAtPoint;
    private String aliasTableGetColumnAtPoint;
    private String aliasTableIsMultiple;
    private String aliasTableSetMultiple;
    private String aliasTableIsReorder;
    private String aliasTableSetReorder;
    private String aliasTableMoveColumn;
    private String aliasTableAddInterval;
    private String aliasTableRemoveInterval;
    private String aliasTableApplyChanges;
    private String aliasTableAddHeader;
    private String aliasTableAddSelect;
    private String aliasChangeListener;
    private String aliasStateChanged;
    private String aliasMouseListener;
    private String aliasMouseClicked;
    private String aliasMousePressed;
    private String aliasMouseReleased;
    private String aliasMouseEntered;
    private String aliasMouseExited;
    private String aliasMouseDragged;
    private String aliasMouseMoved;
    private String aliasMouseEvent;
    private String aliasMouseEventGetFirst;
    private String aliasMouseEventGetSecond;
    private String aliasMouseEventGetClicks;
    private String aliasMouseEventIsAlt;
    private String aliasMouseEventIsCtrl;
    private String aliasMouseEventIsShift;
    private String aliasMouseEventIsLeft;
    private String aliasMouseEventIsMiddle;
    private String aliasMouseEventIsRight;
    private String aliasWindowListener;
    private String aliasWindowOpened;
    private String aliasWindowClosed;
    private String aliasWindowClosing;
    private String aliasWindowIconified;
    private String aliasWindowDeiconified;
    private String aliasWindowActivated;
    private String aliasWindowDeactivated;
    private String aliasWindowEvent;
    private String aliasListSelection;
    private String aliasValueChanged;
    private String aliasRequestFocus;
    private String aliasCompToolTip;
    private String aliasCompFocusable;
    private String aliasCompOpaque;
    private String aliasCompBack;
    private String aliasCompoRelLeft;
    private String aliasCompoRelRight;
    private String aliasCompoRelTop;
    private String aliasCompoRelBottom;
    private String aliasCompoRelCentHoriz;
    private String aliasCompoRelCentVert;
    private String aliasCompFore;
    private String aliasCompGetFirstPos;
    private String aliasCompGetSecondPos;
    private String aliasCompLoc;
    private String aliasCompBorLine;
    private String aliasCompBorTitle;
    private String aliasCompBorLower;
    private String aliasCompBorRaise;
    private String aliasAddWheelListener;
    private String aliasRemoveWheelListener;
    private String aliasGetWheelListeners;
    private String aliasAddKeyListener;
    private String aliasRemoveKeyListener;
    private String aliasGetKeyListeners;
    private String aliasKeyListener;
    private String aliasKeyPressed;
    private String aliasKeyTyped;
    private String aliasKeyReleased;
    private String aliasKeyEvent;
    private String aliasKeyEventChar;
    private String aliasKeyEventCode;
    private String aliasKeyEventIsShift;
    private String aliasKeyEventIsAlt;
    private String aliasKeyEventIsCtrl;

    private String aliasConfirm;
    private String aliasConfirmMessage;
    private String aliasConfirmField;
    private String aliasConfirmOk;
    private String aliasConfirmYesNo;
    private String aliasConfirmFull;
    private String aliasConfirmFieldOk;
    private String aliasConfirmFieldCancel;
    private String aliasConfirmFieldYes;
    private String aliasConfirmFieldNo;
    private String aliasFrame;
    private String aliasDialog;
    private String aliasDialogIsModal;
    private String aliasDialogSetModal;
    private String aliasWindowType;
    private String aliasWindowTypeRelative;
    private String aliasRemoveWindowListener;
    private String aliasGetWindowListeners;
    private String aliasPanel;
    private String aliasPanelFlow;
    private String aliasPanelAbsolute;
    private String aliasPanelBorder;
    private String aliasPanelBorderNorth;
    private String aliasPanelBorderSouth;
    private String aliasPanelBorderEast;
    private String aliasPanelBorderWest;
    private String aliasPanelBorderCenter;
    private String aliasPanelBorderBeforeFirst;
    private String aliasPanelBorderBeforeLineBegins;
    private String aliasPanelBorderAfterLast;
    private String aliasPanelBorderAfterLineEnds;
    private String aliasPanelGrid;
    private String aliasPanelPageBox;
    private String aliasPanelValidate;
    private String aliasTabbedPane;
    private String aliasTabbedPaneNb;
    private String aliasTabbedPaneAdd;
    private String aliasTabbedPaneGet;
    private String aliasTabbedPaneGetTitle;
    private String aliasTabbedPaneSet;
    private String aliasTabbedPaneSetTitle;
    private String aliasTabbedPaneRemove;
    private String aliasTabbedPaneIndex;
    private String aliasTabbedPaneSelIndex;
    private String aliasButton;
    private String aliasProgBar;
    private String aliasProgBarValue;
    private String aliasProgBarMax;
    private String aliasProgBarMin;
    private String aliasProgBarOr;
    private String aliasImageLabel;
    private String aliasTextLabel;
    private String aliasScrollPane;
    private String aliasScrollPaneGetView;
    private String aliasScrollPaneSetView;
    private String aliasScrollPaneHorizontalValue;
    private String aliasScrollPaneVerticalValue;
    private String aliasScrollPaneValidate;
    private String aliasSplitPane;
    private String aliasSplitPaneGetDividerLocation;
    private String aliasSplitPaneSetDividerLocation;
    private String aliasSplitPaneGetDividerSize;
    private String aliasSplitPaneSetDividerSize;
    private String aliasSplitPaneIsContinuousLayout;
    private String aliasSplitPaneSetContinuousLayout;
    private String aliasSplitPaneIsOneTouchExpandable;
    private String aliasSplitPaneSetOneTouchExpandable;
    private String aliasSplitPaneGetLeft;
    private String aliasSplitPaneSetLeft;
    private String aliasSplitPaneGetRight;
    private String aliasSplitPaneSetRight;
    private String aliasSplitPaneValidate;
    private String aliasGetFont;
    private String aliasSetFont;
    private String aliasFont;
    private String aliasFontGetName;
    private String aliasFontGetSize;
    private String aliasFontIsBold;
    private String aliasFontIsItalic;
    private String aliasFontStringWidth;
    private String aliasImage;
    private String aliasImageGetWidth;
    private String aliasImageGetHeight;
    private String aliasImageGet;
    private String aliasImageSet;
    private String aliasImageIsWithAlpha;
    private String aliasImageEq;
    private String aliasImageGetColor;
    private String aliasImageSetColor;
    private String aliasImageGetFont;
    private String aliasImageSetFont;
    private String aliasImageDraw;
    private String aliasImageDrawLine;
    private String aliasImageDrawRect;
    private String aliasImageDrawOval;
    private String aliasImageFillRect;
    private String aliasImageFillOval;
    private String aliasImageDrawPolygon;
    private String aliasImageFillPolygon;
    private String aliasImageDispose;
    private String aliasColor;
    private String aliasColorRed;
    private String aliasColorGreen;
    private String aliasColorBlue;
    private String aliasColorAlpha;
    private String aliasColorIsTransparent;
    private String aliasComponentSetPaint;
    private String aliasComponentGetPaint;
    private String aliasComponentRepaint;
    private String aliasComponentSetAutoscrolls;
    private String aliasComponentIsAutoscrolls;
    private String aliasComponentGetWidth;
    private String aliasComponentGetHeight;
    private String aliasComponentGetPreferredSize;
    private String aliasComponentSetPreferredSize;
    private String aliasComponentSetSize;
    private String aliasComponentIsVisible;
    private String aliasComponentSetVisible;
    private String aliasComponentInvokeLater;
    private String aliasComponent;
    private String aliasDimension;
    private String aliasDimensionGetHeight;
    private String aliasDimensionGetWidth;
    private String aliasSetContent;
    private String aliasAddCompo;
    private String aliasGetParentCompo;
    private String aliasGetNextCompo;
    private String aliasGetIndexCompo;
    private String aliasRemoveAll;
    private String aliasAddListener;
    private String aliasRemoveListener;
    private String aliasGetListeners;
    private String aliasAddWindowListener;
    private String aliasSetLabelText;
    private String aliasSetLabelImage;
    private String aliasPaint;
    private String aliasPaintMethod;
    private String aliasPaintAdd;
    private String aliasPaintSet;
    private String aliasPaintRefresh;
    private String aliasPaintRefreshOne;
    private String aliasRemoveCompo;
    private String aliasCount;
    private String aliasIsVisible;
    private String aliasSetVisible;
    private String aliasWindow;
    private String aliasWindowSet;
    private String aliasWindowSetAll;
    private String aliasWindowSetAdd;
    private String aliasWindowSetContains;
    private String aliasWindowSetRemove;
    private String aliasWindowSetSnapshot;
//    private String aliasArgs;
    private String aliasPack;
    private String aliasDispose;
    private String aliasCloseAll;
    private String aliasInput;
    private String aliasInputIsEnabled;
    private String aliasInputSetEnabled;

    private String aliasRender;
    private String aliasRenderGetHeight;
    private String aliasRenderGetWidth;
    private String aliasRenderGetPaint;
    private String aliasRenderSetHeight;
    private String aliasRenderSetWidth;
    private String aliasRenderSetPaint;
    private String aliasGrList;
    private String aliasGrListAdd;
    private String aliasGrListSet;
    private String aliasGrListGetListView;
    private String aliasGrListGetSelectedIndexes;
    private String aliasGrListSetSelectedIndexes;
    private String aliasGrListClearSelection;
    private String aliasGrListClear;
    private String aliasGrListRemove;
    private String aliasGrListUpdateGraphics;
    private String aliasGrListGetRender;
    private String aliasGrListSetRender;
    private String aliasGrListGetSelections;
    private String aliasGrListAddSelection;
    private String aliasGrListRemoveSelection;
    private String aliasGrListGetVisibleRowCount;
    private String aliasGrListSetVisibleRowCount;

    private String aliasCombo;
    private String aliasComboGetSelectedItem;
    private String aliasComboAddItem;
    private String aliasComboGetItemCount;
    private String aliasComboSelectItem;
    private String aliasComboAddListener;
    private String aliasComboRemoveListener;
    private String aliasComboGetListeners;
    private String aliasComboGetSelectedIndexes;
    private String aliasComboGetSelectedIndex;
    private String aliasComboRemoveAllItems;
    private String aliasComboRemoveItem;

    private String aliasButtonGroup;
    private String aliasButtonGroupAdd;
    private String aliasRadio;
    private String aliasRadioIsSelected;
    private String aliasRadioSetSelected;
    private String aliasRadioGetText;
    private String aliasRadioSetText;
    private String aliasPopupMenu;
    private String aliasPopupMenuAdd;
    private String aliasPopupMenuGetComp;
    private String aliasPopupMenuRemoveComp;
    private String aliasPopupMenuNbComp;
    private String aliasPopupMenuAddMenu;
    private String aliasPopupMenuGetMenu;
    private String aliasPopupMenuRemoveMenu;
    private String aliasPopupMenuNbMenu;
    private String aliasPopupMenuShow;
    private String aliasTextField;
    private String aliasTextFieldGetText;
    private String aliasTextFieldSetText;
    private String aliasTextFieldAuto;
    private String aliasTextFieldAddAction;
    private String aliasTextFieldAddDocument;
    private String aliasTextFieldAddPopup;
    private String aliasTextArea;
    private String aliasTextAreaGetText;
    private String aliasTextAreaSetText;
    private String aliasTextAreaGetTabSize;
    private String aliasTextAreaSetTabSize;
    private String aliasTextAreaAppend;
    private String aliasTextAreaInsert;
    private String aliasTextAreaReplaceRange;
    private String aliasTextAreaReplaceSelection;
    private String aliasTextAreaGetSelectedText;
    private String aliasTextAreaSetSelectionStart;
    private String aliasTextAreaSetSelectionEnd;
    private String aliasTextAreaSelect;
    private String aliasTextAreaSelectAll;
    private String aliasCheckBox;
    private String aliasCheckBoxGetText;
    private String aliasCheckBoxSetText;
    private String aliasCheckBoxIsSelected;
    private String aliasCheckBoxSetSelected;
    private String aliasCheckBoxAddAction;
    private String aliasSpinner;
    private String aliasSpinnerSetRange;
    private String aliasSpinnerSetRangeValue;
    private String aliasSpinnerGetValue;
    private String aliasSpinnerSetValue;
    private String aliasSpinnerGetMax;
    private String aliasSpinnerSetMax;
    private String aliasSpinnerGetMin;
    private String aliasSpinnerSetMin;
    private String aliasSpinnerGetStep;
    private String aliasSpinnerSetStep;
    private String aliasSlider;
    private String aliasSliderGetValue;
    private String aliasSliderSetValue;
    private String aliasSliderGetMax;
    private String aliasSliderSetMax;
    private String aliasSliderGetMin;
    private String aliasSliderSetMin;
    private String aliasSliderGetOrientation;
    private String aliasSliderSetOrientation;
    private String aliasGetMenuBar;
    private String aliasSetMenuBar;
    private String aliasMenuBar;
    private String aliasMenuBarAdd;
    private String aliasMenuBarGet;
    private String aliasMenuBarRemove;
    private String aliasMenuBarNb;
    private String aliasAbsMenu;
    private String aliasAbsMenuGetParent;
    private String aliasAbsMenuIsEnabled;
    private String aliasAbsMenuSetEnabled;
    private String aliasAbsMenuSetDeepEnabled;
    private String aliasAbsMenuGetText;
    private String aliasAbsMenuSetText;
    private String aliasMenu;
    private String aliasMenuAdd;
    private String aliasMenuGet;
    private String aliasMenuRemove;
    private String aliasMenuNb;
    private String aliasMenuAddSeparator;
    private String aliasAbsMenuItem;
    private String aliasAbsMenuItemAddAction;
    private String aliasMenuItem;
    private String aliasMenuItemCheck;
    private String aliasMenuItemCheckIsSelected;
    private String aliasMenuItemCheckSetSelected;
    private String aliasCommand;
    private String aliasCommandBinding;
    private String aliasCommandAction;
    private String aliasComponentBind;
    private String aliasComponentUnbind;
    private String aliasComponentCommands;
    private final GuiAliasParameters guiAliasParameters = new GuiAliasParameters();
    private final StringMap<String> propertiesGui;
    private final boolean light;
    public GuiAliases() {
        this(false);
    }
    public GuiAliases(boolean _l) {
        light = _l;
        if (_l) {
            propertiesGui = new StringMap<String>();
        } else {
            propertiesGui = MessCdmGuiGr.ms();
        }
    }
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content) {
        StringMap<String> stds_ = new StringMap<String>();
        if (light) {
            return stds_;
        }
        String content_ = res(RESOURCES_LG_GUI_ACTION_EVENT_TXT);
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String public_ = _keyWords.getKeyWordPublic();
        String interface_ = _keyWords.getKeyWordInterface();
        String class_ = _keyWords.getKeyWordClass();
        String abstract_ = _keyWords.getKeyWordAbstract();
        String static_ = _keyWords.getKeyWordStatic();
        String if_ = _keyWords.getKeyWordIf();
        String elseif_ = _keyWords.getKeyWordElseif();
        String while_ = _keyWords.getKeyWordWhile();
        String final_ = _keyWords.getKeyWordFinal();
        String return_ = _keyWords.getKeyWordReturn();
        String continue_ = _keyWords.getKeyWordContinue();
        String break_ = _keyWords.getKeyWordBreak();
        String for_ = _keyWords.getKeyWordFor();
        String new_ = _keyWords.getKeyWordNew();
        String null_ = _keyWords.getKeyWordNull();
        String cast_ = _keyWords.getKeyWordCast();
        String true_ = _keyWords.getKeyWordTrue();
        String false_ = _keyWords.getKeyWordFalse();
        String is_ = _keyWords.getKeyWordInstanceof();
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_ACTION_LISTENER, aliasActionListener);
        map_.put(METHOD_ACTION_PERFORMED, aliasActionPerformed);
        map_.put(TYPE_ACTION_EVENT, aliasActionEvent);
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_5, guiAliasParameters.getAliasActionListener0ActionPerformed0());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasActionListener, content_);
        content_ = res(RESOURCES_LG_GUI_CHANGE_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_CHANGE_LISTENER, aliasChangeListener);
        map_.put(METHOD_STATE_CHANGED, aliasStateChanged);
        map_.put(KW_VOID, co_.getAliasVoid());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasChangeListener, content_);
        content_ = res(RESOURCES_LG_GUI_TREE_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_TREE_LISTENER, aliasTreeListener);
        map_.put(METHOD_VALUE_CHANGED, aliasTreeListenerValueChanged);
        map_.put(TYPE_TREE_NODE, aliasTreeNode);
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_5, guiAliasParameters.getAliasTreeListener0TreeListenerValueChanged0());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasTreeListener, content_);
        content_ = res(RESOURCES_LG_GUI_TABLE_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_TABLE_LISTENER, aliasTableListener);
        map_.put(METHOD_VALUE_CHANGED, aliasTableValueTableChanged);
        map_.put(TYPE_INT, primTypes_.getAliasPrimInteger());
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_5, guiAliasParameters.getAliasTableListener0TableValueTableChanged0());
        map_.put(PARAM_6, guiAliasParameters.getAliasTableListener0TableValueTableChanged1());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasTableListener, content_);
        content_ = res(RESOURCES_LG_GUI_MOUSE_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_MOUSE_LISTENER, aliasMouseListener);
        map_.put(METHOD_MOUSE_CLICKED, aliasMouseClicked);
        map_.put(METHOD_MOUSE_PRESSED, aliasMousePressed);
        map_.put(METHOD_MOUSE_RELEASED, aliasMouseReleased);
        map_.put(METHOD_MOUSE_ENTERED, aliasMouseEntered);
        map_.put(METHOD_MOUSE_EXITED, aliasMouseExited);
        map_.put(METHOD_MOUSE_DRAGGED, aliasMouseDragged);
        map_.put(METHOD_MOUSE_MOVED, aliasMouseMoved);
        map_.put(TYPE_MOUSE_EVENT, aliasMouseEvent);
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_1, guiAliasParameters.getAliasMouseListener0MouseClicked0());
        map_.put(PARAM_2, guiAliasParameters.getAliasMouseListener0MousePressed0());
        map_.put(wrap(PARAM_3), guiAliasParameters.getAliasMouseListener0MouseReleased0());
        map_.put(PARAM_4, guiAliasParameters.getAliasMouseListener0MouseEntered0());
        map_.put(PARAM_5, guiAliasParameters.getAliasMouseListener0MouseExited0());
        map_.put(PARAM_6, guiAliasParameters.getAliasMouseListener0MouseDragged0());
        map_.put(PARAM_7, guiAliasParameters.getAliasMouseListener0MouseMoved0());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasMouseListener, content_);
        content_ = res(RESOURCES_LG_GUI_WHEEL_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_WHEEL_LISTENER, aliasWheelListener);
        map_.put(METHOD_MOUSE_MOVE_WHEEL, aliasWheelMove);
        map_.put(TYPE_MOUSE_WHEEL_EVENT, aliasWheelEvent);
        map_.put(PARAM_5, guiAliasParameters.getAliasWheelListener0WheelMove0());
        map_.put(KW_VOID, co_.getAliasVoid());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasWheelListener, content_);
        content_ = res(RESOURCES_LG_GUI_KEY_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_KEY_LISTENER, aliasKeyListener);
        map_.put(METHOD_KEY_PRESSED, aliasKeyPressed);
        map_.put(METHOD_KEY_TYPED, aliasKeyTyped);
        map_.put(METHOD_KEY_RELEASED, aliasKeyReleased);
        map_.put(TYPE_KEY_EVENT, aliasKeyEvent);
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_1, guiAliasParameters.getAliasKeyListener0KeyPressed0());
        map_.put(PARAM_2, guiAliasParameters.getAliasKeyListener0KeyTyped0());
        map_.put(wrap(PARAM_3), guiAliasParameters.getAliasKeyListener0KeyReleased0());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasKeyListener, content_);
        content_ = res(RESOURCES_LG_GUI_WINDOW_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_WINDOW_LISTENER, aliasWindowListener);
        map_.put(METHOD_WINDOW_OPENED, aliasWindowOpened);
        map_.put(METHOD_WINDOW_CLOSING, aliasWindowClosing);
        map_.put(METHOD_WINDOW_CLOSED, aliasWindowClosed);
        map_.put(METHOD_WINDOW_ICONIFIED, aliasWindowIconified);
        map_.put(METHOD_WINDOW_DEICONIFIED, aliasWindowDeiconified);
        map_.put(METHOD_WINDOW_ACTIVATED, aliasWindowActivated);
        map_.put(METHOD_WINDOW_DEACTIVATED, aliasWindowDeactivated);
        map_.put(TYPE_WINDOW_EVENT, aliasWindowEvent);
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_1, guiAliasParameters.getAliasWindowListener0WindowOpened0());
        map_.put(PARAM_2, guiAliasParameters.getAliasWindowListener0WindowClosing0());
        map_.put(wrap(PARAM_3), guiAliasParameters.getAliasWindowListener0WindowClosed0());
        map_.put(PARAM_4, guiAliasParameters.getAliasWindowListener0WindowIconified0());
        map_.put(PARAM_5, guiAliasParameters.getAliasWindowListener0WindowDeiconified0());
        map_.put(PARAM_6, guiAliasParameters.getAliasWindowListener0WindowActivated0());
        map_.put(PARAM_7, guiAliasParameters.getAliasWindowListener0WindowDeactivated0());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasWindowListener, content_);
        content_ = res(RESOURCES_LG_GUI_LIST_EVENT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_INTERFACE, interface_);
        map_.put(TYPE_LIST_SELECTION, aliasListSelection);
        map_.put(METHOD_VALUE_CHANGED, aliasValueChanged);
        map_.put(TYPE_INT, primTypes_.getAliasPrimInteger());
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(PARAM_5, guiAliasParameters.getAliasListSelection0ValueChanged0());
        map_.put(PARAM_6, guiAliasParameters.getAliasListSelection0ValueChanged1());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasListSelection, content_);
        content_ = res(RESOURCES_LG_GUI_REPAINT_TXT);
        map_ = new StringMap<String>();
        map_.put(KW_PUBLIC, public_);
        map_.put(KW_ABSTRACT, abstract_);
        map_.put(KW_FINAL, final_);
        map_.put(KW_CLASS, class_);
        map_.put(TYPE_PAINT, aliasPaint);
        map_.put(METHOD_PAINT, aliasPaintMethod);
        map_.put(PARAM_8, guiAliasParameters.getAliasPaint0PaintMethod0());
        map_.put(METHOD_ADD, aliasPaintAdd);
        map_.put(METHOD_SET, aliasPaintSet);
        map_.put(PARAM_9, guiAliasParameters.getAliasPaint0TabbedPaneSet0());
        map_.put(PARAM_10, guiAliasParameters.getAliasPaint0TabbedPaneSet1());
        map_.put(PARAM_11, guiAliasParameters.getAliasPaint0TabbedPaneSet2());
        map_.put(PARAM_12, guiAliasParameters.getAliasPaint0TabbedPaneAdd0());
        map_.put(PARAM_13, guiAliasParameters.getAliasPaint0TabbedPaneAdd1());
        map_.put(PARAM_14, guiAliasParameters.getAliasPaint0TabbedPaneAdd2());
        map_.put(PARAM_15, guiAliasParameters.getAliasPaint1TabbedPaneAdd0());
        map_.put(PARAM_16, guiAliasParameters.getAliasPaint1TabbedPaneAdd1());
        map_.put(PARAM_17, guiAliasParameters.getAliasPaint0PaintRefresh0());
        map_.put(METHOD_REFRESH, aliasPaintRefresh);
        map_.put(METHOD_REFRESH_ONE, aliasPaintRefreshOne);
        map_.put(PARAM_18, guiAliasParameters.getAliasPaint0PaintRefreshOne0());
        map_.put(PARAM_19, guiAliasParameters.getAliasPaint0PaintRefreshOne1());
        map_.put(PARAM_20, guiAliasParameters.getAliasPaint0PaintRefreshOne2());
        map_.put(PARAM_21, guiAliasParameters.getAliasPaint0PaintRefreshOne3());
        map_.put(PARAM_6, guiAliasParameters.getAliasPaint0PaintRefreshOne4());
        map_.put(PARAM_22, guiAliasParameters.getAliasPaint0PaintRefreshOne5());
        map_.put(KW_STATIC, static_);
        map_.put(KW_IF, if_);
        map_.put(KW_ELSEIF, elseif_);
        map_.put(KW_TRUE, true_);
        map_.put(KW_FALSE, false_);
        map_.put(KW_RETURN, return_);
        map_.put(KW_BREAK, break_);
        map_.put(KW_CONTINUE, continue_);
        map_.put(KW_IS, is_);
        map_.put(KW_WHILE, while_);
        map_.put(KW_NULL, null_);
        map_.put(KW_CAST, cast_);
        map_.put(TYPE_COMPONENT, aliasComponent);
        map_.put(TYPE_PANEL, aliasPanel);
        map_.put(TYPE_FCT, _content.getReflect().getAliasFct());
        map_.put(KW_VOID, co_.getAliasVoid());
        map_.put(METHOD_CALL, _content.getReflect().getAliasCall());
        map_.put(METHOD_GET_COMPONENT, aliasGetIndexCompo);
        map_.put(METHOD_NEXT, aliasGetNextCompo);
        map_.put(METHOD_GET_PARENT, aliasGetParentCompo);
        map_.put(METHOD_GET_PAINTING, aliasComponentGetPaint);
        map_.put(wrap(PARAM_3), tr(PARAM_3, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_23), tr(PARAM_23, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_24), tr(PARAM_24, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_25), tr(PARAM_25, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_26), tr(PARAM_26, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_27), tr(PARAM_27, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_28), tr(PARAM_28, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_29), tr(PARAM_29, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_30), tr(PARAM_30, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_31), tr(PARAM_31, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_32), tr(PARAM_32, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_33), tr(PARAM_33, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_34), tr(PARAM_34, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_35), tr(PARAM_35, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_36), tr(PARAM_36, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_37), tr(PARAM_37, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_38), tr(PARAM_38, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_39), tr(PARAM_39, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(wrap(PARAM_40), tr(PARAM_40, _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put(METHOD_GET_RENDER,aliasGrListGetRender);
        map_.put(TYPE_MATH,_content.getMathRef().getAliasMath());
        map_.put(METHOD_MAX,_content.getMathRef().getAliasMax());
        map_.put(TYPE_STRING_UTIL,_content.getCoreNames().getAliasStringUtil());
        map_.put(METHOD_VALUE_OF,_content.getCoreNames().getAliasStringUtilValueOf());
        map_.put(METHOD_WIDTH_STR,aliasFontStringWidth);
        map_.put(METHOD_SET_COLOR,aliasImageSetColor);
        map_.put(METHOD_FILL_RECT,aliasImageFillRect);
        map_.put(METHOD_DRAW_STRING,aliasImageDraw);
        map_.put(METHOD_GET_FONT,aliasGetFont);
        map_.put(METHOD_SIZE,aliasFontGetSize);
        map_.put(TYPE_COLOR,aliasColor);
        map_.put(METHOD_GET_COMP_WIDTH,aliasComponentGetWidth);
        map_.put(KW_ELSE,_keyWords.getKeyWordElse());
        map_.put(METHOD_SET_FONT,aliasImageSetFont);
        map_.put(METHOD_GET_HEIGHT,aliasRenderGetHeight);
        map_.put(METHOD_GET_WIDTH,aliasRenderGetWidth);
        map_.put(METHOD_GET_PAINT,aliasRenderGetPaint);
        map_.put(TYPE_IMAGE,aliasImage);
        map_.put(TYPE_IMAGE_LABEL,aliasImageLabel);
        map_.put(METHOD_ADD_COMPO,aliasGrListAdd);
        map_.put(METHOD_SET_COMPO,aliasGrListSet);
        map_.put(METHOD_GET_VIEW,aliasGrListGetListView);
        map_.put(METHOD_GET_SELECTED,aliasGrListGetSelectedIndexes);
        map_.put(METHOD_UPDATE_GRAPHICS,aliasGrListUpdateGraphics);
        map_.put(METHOD_LENGTH,_content.getCharSeq().getAliasLength());
        map_.put(KW_FOR,for_);
        map_.put(KW_NEW,new_);
        map_.put(KW_VAR, _keyWords.getKeyWordVar());
        map_.put(TYPE_INT,primTypes_.getAliasPrimInteger());
        map_.put(TYPE_GR_LIST,aliasGrList);
        map_.put(TYPE_OBJECT,co_.getAliasObject());
        map_.put(METHOD_DISPOSE,aliasImageDispose);
        map_.put(TYPE_BOOLEAN,primTypes_.getAliasPrimBoolean());
        content_ = StringUtil.formatQuote(content_, map_);
        stds_.put(aliasPaint, content_);
        return stds_;
    }
    private static String wrap(String _element) {
        return '{'+_element+'}';
    }

    public String res(String _file) {
        return StringUtil.nullToEmpty(propertiesGui.getVal(_file));
    }

    public void buildOther(LgNamesContent _content, CustAliases _cust, GuiExecutingBlocks _guiEx) {
        if (light) {
            return;
        }
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasWindowType, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasSetContent, params_, aliasPanel, false, MethodModifier.FINAL, new FctWindowGetContentPane());
        methods_.add( method_);
        params_ = new StringList(aliasPanel);
        method_ = new StandardMethod(aliasSetContent, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0SetContent0()), new FctWindowContentPane());
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasWindowTypeRelative, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0WindowTypeRelative0()), new FctWindowRelative());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPack, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctWindowPack());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVisible, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctWindowIsVisible());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetVisible, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0ComponentSetVisible0()), new FctWindowSetVisible());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMenuBar, params_, aliasMenuBar, false, MethodModifier.FINAL, new FctWindowGetMenuBar());
        methods_.add( method_);
        params_ = new StringList(aliasMenuBar);
        method_ = new StandardMethod(aliasSetMenuBar, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0SetMenuBar0()), new FctWindowSetMenuBar());
        methods_.add( method_);
        params_ = new StringList(aliasWindowListener);
        method_ = new StandardMethod(aliasAddWindowListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0AddWindowListener0()), new FctWindowAddList(_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasWindowListener);
        method_ = new StandardMethod(aliasRemoveWindowListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0RemoveWindowListener0()), new FctWindowRemoveList(_guiEx));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetWindowListeners, params_, StringExpUtil.getPrettyArrayType(aliasWindowListener), false, MethodModifier.FINAL, new FctWindowGetList(aliasWindowListener));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDispose, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctWindowDispose());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCloseAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctWindowCloseAll());
        methods_.add( method_);
        StandardType std_ = stdcl_;
        _content.getStandards().addEntry(aliasWindowType, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasConfirm, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.HYPER_ABSTRACT);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmField, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmField0(),guiAliasParameters.getAliasConfirm0ConfirmField1(),guiAliasParameters.getAliasConfirm0ConfirmField2(),guiAliasParameters.getAliasConfirm0ConfirmField3(),guiAliasParameters.getAliasConfirm0ConfirmField4(),guiAliasParameters.getAliasConfirm0ConfirmField5(),guiAliasParameters.getAliasConfirm0ConfirmField6()), new FctConfirmField1(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmField, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmField0(),guiAliasParameters.getAliasConfirm1ConfirmField1(),guiAliasParameters.getAliasConfirm1ConfirmField2(),guiAliasParameters.getAliasConfirm1ConfirmField3(),guiAliasParameters.getAliasConfirm1ConfirmField4(),guiAliasParameters.getAliasConfirm1ConfirmField5()), new FctConfirmField0(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmFull, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmFull0(),guiAliasParameters.getAliasConfirm0ConfirmFull1(),guiAliasParameters.getAliasConfirm0ConfirmFull2(),guiAliasParameters.getAliasConfirm0ConfirmFull3(),guiAliasParameters.getAliasConfirm0ConfirmFull4(),guiAliasParameters.getAliasConfirm0ConfirmFull5(),guiAliasParameters.getAliasConfirm0ConfirmFull6()), new FctConfirmFull1(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmFull, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmFull0(),guiAliasParameters.getAliasConfirm1ConfirmFull1(),guiAliasParameters.getAliasConfirm1ConfirmFull2(),guiAliasParameters.getAliasConfirm1ConfirmFull3(),guiAliasParameters.getAliasConfirm1ConfirmFull4(),guiAliasParameters.getAliasConfirm1ConfirmFull5()), new FctConfirmFull0(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmYesNo, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmYesNo0(),guiAliasParameters.getAliasConfirm0ConfirmYesNo1(),guiAliasParameters.getAliasConfirm0ConfirmYesNo2(),guiAliasParameters.getAliasConfirm0ConfirmYesNo3(),guiAliasParameters.getAliasConfirm0ConfirmYesNo4(),guiAliasParameters.getAliasConfirm0ConfirmYesNo5()), new FctConfirmYesNo1(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmYesNo, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmYesNo0(),guiAliasParameters.getAliasConfirm1ConfirmYesNo1(),guiAliasParameters.getAliasConfirm1ConfirmYesNo2(),guiAliasParameters.getAliasConfirm1ConfirmYesNo3(),guiAliasParameters.getAliasConfirm1ConfirmYesNo4()), new FctConfirmYesNo0(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmOk, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmOk0(),guiAliasParameters.getAliasConfirm0ConfirmOk1(),guiAliasParameters.getAliasConfirm0ConfirmOk2(),guiAliasParameters.getAliasConfirm0ConfirmOk3(),guiAliasParameters.getAliasConfirm0ConfirmOk4()), new FctConfirmOk1(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmOk, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmOk0(),guiAliasParameters.getAliasConfirm1ConfirmOk1(),guiAliasParameters.getAliasConfirm1ConfirmOk2(),guiAliasParameters.getAliasConfirm1ConfirmOk3()), new FctConfirmOk0(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmMessage, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmMessage0(),guiAliasParameters.getAliasConfirm0ConfirmMessage1(),guiAliasParameters.getAliasConfirm0ConfirmMessage2(),guiAliasParameters.getAliasConfirm0ConfirmMessage3(),guiAliasParameters.getAliasConfirm0ConfirmMessage4()), new FctConfirmMessage1(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmMessage, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmMessage0(),guiAliasParameters.getAliasConfirm1ConfirmMessage1(),guiAliasParameters.getAliasConfirm1ConfirmMessage2(),guiAliasParameters.getAliasConfirm1ConfirmMessage3()), new FctConfirmMessage0(_cust,_guiEx));
        methods_.add( method_);
        fields_.add(new CstFieldInfo(aliasConfirmFieldOk,_content.getPrimTypes().getAliasPrimInteger()));
        fields_.add(new CstFieldInfo(aliasConfirmFieldYes,_content.getPrimTypes().getAliasPrimInteger()));
        fields_.add(new CstFieldInfo(aliasConfirmFieldNo,_content.getPrimTypes().getAliasPrimInteger()));
        fields_.add(new CstFieldInfo(aliasConfirmFieldCancel,_content.getPrimTypes().getAliasPrimInteger()));
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasConfirm, std_);
        StandardConstructor ctor_;

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWindowSet, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfWindowSet(_cust));
        params_ = new StringList(aliasWindowType);
        method_ = new StandardMethod(aliasWindowSetAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowSet0TabbedPaneAdd0()), new FctWindowSetAdd());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindowSetAll, params_, aliasWindowSet, false, MethodModifier.STATIC, new FctWindowSetAll(_cust));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType);
        method_ = new StandardMethod(aliasWindowSetContains, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowSet0WindowSetContains0()), new FctWindowSetContains());
        methods_.add( method_);
        params_ = new StringList(aliasWindowType);
        method_ = new StandardMethod(aliasWindowSetRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowSet0TreeNodeRemove0()), new FctWindowSetRemove());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindowSetSnapshot, params_, StringExpUtil.getPrettyArrayType(aliasWindowType), false, MethodModifier.FINAL, new FctWindowSetArray());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctWindowSet(_cust));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWindowSet, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasFrame, fields_, constructors_, methods_, aliasWindowType, MethodModifier.FINAL, new DfFrame(_cust,_guiEx));
//        params_ = new StringList();
//        method_ = new StandardMethod(aliasWindow, params_, aliasFrame, false, MethodModifier.STATIC, new FctFrameWindow(_cust,_guiEx));
//        methods_.add( method_);
//        params_ = new StringList();
//        method_ = new StandardMethod(aliasArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC, new FctFrameArgs(_cust,_guiEx));
//        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctFrame(_cust,_guiEx));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasFrame, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasDialog, fields_, constructors_, methods_, aliasWindowType, MethodModifier.FINAL, new DfDialog(_cust,_guiEx));
        params_ = new StringList();
        method_ = new StandardMethod(aliasDialogIsModal, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctDialogIsModal());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasDialogSetModal, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasDialog0DialogSetModal0()), new FctDialogSetModal());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctDialog(_cust,_guiEx));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasDialog, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasComponent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParentCompo, params_, aliasComponent, false, MethodModifier.FINAL, new FctCompoGetParentCompo());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNextCompo, params_, aliasComponent, false, MethodModifier.FINAL, new FctCompoGetNextCompo());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFont, params_, aliasFont, false, MethodModifier.FINAL, new FctCompoGetFont());
        methods_.add( method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasSetFont, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0SetFont0()), new FctCompoSetFont());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBack, params_, aliasColor, false, MethodModifier.FINAL, new FctCompoBack0());
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompBack, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompBack0()), new FctCompoBack1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompFore, params_, aliasColor, false, MethodModifier.FINAL, new FctCompoFore0());
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompFore, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompFore0()), new FctCompoFore1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompFocusable, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctCompoFocusable0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompFocusable, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompFocusable0()), new FctCompoFocusable1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompOpaque, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctCompoOpaque0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompOpaque, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompOpaque0()), new FctCompoOpaque1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompToolTip, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctCompoToolTip0());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCompToolTip, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompToolTip0()), new FctCompoToolTip1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompGetFirstPos, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctCompoGetFirstPos());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompGetSecondPos, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctCompoGetSecondPos());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompLoc, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompLoc0(),guiAliasParameters.getAliasComponent0CompLoc1()), new FctCompoLoc());
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompBorLine, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompBorLine0()), new FctCompoBorLine0());
        methods_.add( method_);
        params_ = new StringList(aliasColor,_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompBorLine, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent1CompBorLine0(),guiAliasParameters.getAliasComponent1CompBorLine1()), new FctCompoBorLine1());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCompBorTitle, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompBorTitle0()), new FctCompoBorTitle());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBorLower, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoBorLower());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBorRaise, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoBorRaise());
        methods_.add( method_);

        params_ = new StringList();
        String type_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasComponent,",?>");
        method_ = new StandardMethod(aliasComponentGetPaint, params_, type_, false, MethodModifier.FINAL, new FctCompoGetPaint());
        methods_.add( method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetPaint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetPaint0()), new FctCompoSetPaint());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentRepaint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoRepaint(_guiEx,aliasPaint));
        methods_.add( method_);
        type_ = _content.getPrimTypes().getAliasPrimBoolean();
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsAutoscrolls, params_, type_, false, MethodModifier.FINAL, new FctCompoIsAutoscrolls());
        methods_.add( method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetAutoscrolls, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetAutoscrolls0()), new FctCompoSetAutoscrolls());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetPreferredSize, params_, aliasDimension, false, MethodModifier.FINAL, new FctCompoGetPreferredSize());
        methods_.add( method_);
        params_ = new StringList(aliasDimension);
        method_ = new StandardMethod(aliasComponentSetPreferredSize, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetPreferredSize0()), new FctCompoSetPreferredSize());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsVisible, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctCompoIsVisible());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasComponentSetVisible, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetVisible0()), new FctCompoSetVisible());
        methods_.add( method_);
        params_ = new StringList(_cust.getAliasRunnable());
        method_ = new StandardMethod(aliasComponentInvokeLater, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasComponent0ComponentInvokeLater0()), new FctCompoInvokeLater(_cust,_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasKeyListener);
        method_ = new StandardMethod(aliasAddKeyListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0AddKeyListener0()), new FctCompoAddKeyListener());
        methods_.add( method_);
        params_ = new StringList(aliasKeyListener);
        method_ = new StandardMethod(aliasRemoveKeyListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0RemoveKeyListener0()), new FctCompoRemoveKeyListener());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetKeyListeners, params_, StringExpUtil.getPrettyArrayType(aliasKeyListener), false, MethodModifier.FINAL,new StringList(), new FctCompoGetKeyListeners());
        methods_.add( method_);
        params_ = new StringList(aliasWheelListener);
        method_ = new StandardMethod(aliasAddWheelListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0AddWheelListener0()), new FctCompoAddWheelListener());
        methods_.add( method_);
        params_ = new StringList(aliasWheelListener);
        method_ = new StandardMethod(aliasRemoveWheelListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0RemoveWheelListener0()), new FctCompoRemoveWheelListener());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetWheelListeners, params_, StringExpUtil.getPrettyArrayType(aliasWheelListener), false, MethodModifier.FINAL,new StringList(), new FctCompoGetWheelListeners());
        methods_.add( method_);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasAddListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0AddListener0()), new FctCompoAddMouseListener());
        methods_.add( method_);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasRemoveListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0RemoveListener0()), new FctCompoRemoveMouseListener());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetListeners, params_, StringExpUtil.getPrettyArrayType(aliasMouseListener), false, MethodModifier.FINAL,new StringList(), new FctCompoGetMouseListeners());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRequestFocus, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoRequestFocus());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetHeight, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctCompoGetHeight());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctCompoGetWidth());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompoRelLeft, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoLeft());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompoRelRight, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoRight());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompoRelTop, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoTop());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompoRelBottom, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoBottom());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompoRelCentHoriz, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoCentHoriz());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompoRelCentVert, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctCompoCentVert());
        methods_.add( method_);
        params_ = new StringList(aliasAction,_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasComponentBind, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0Bind0(),guiAliasParameters.getAliasComponent0Bind1(),guiAliasParameters.getAliasComponent0Bind2()), new FctCompoBind());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasComponentUnbind, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new StringList(guiAliasParameters.getAliasComponent0Unbind0(),guiAliasParameters.getAliasComponent0Unbind1()), new FctCompoUnbind());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentCommands, params_, StringExpUtil.getPrettyArrayType(aliasCommand), false, MethodModifier.FINAL, new FctCompoCommands());
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasComponent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasDimension, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetHeight, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctDimensionGetHeight());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctDimensionGetWidth());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasDimension0Dimension0(),guiAliasParameters.getAliasDimension0Dimension1()), new FctDimension1());
        constructors_.add(ctor_);
        params_ = new StringList(aliasDimension);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasDimension1Dimension0()), new FctDimension0());
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasDimension, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTreeNode, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeAdd0()), new FctTreeNodeAdd());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeInsert, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeInsert0(),guiAliasParameters.getAliasTreeNode0TreeNodeInsert1()),new FctTreeNodeInsert());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTreeNodeRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeRemove0()), new FctTreeNodeRemove0());
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode1TreeNodeRemove0()), new FctTreeNodeRemove1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeRemoveFromParent, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTreeNodeRemoveFromParent());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeRemoveAllChildren, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTreeNodeRemoveAllChildren());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTreeNodeSetUserObject, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeSetUserObject0()),new FctTreeNodeSetUserObject());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetUserObject, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new FctTreeNodeGetUserObject());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeNb, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTreeNodeNb());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetFirstChild, params_,aliasTreeNode, false, MethodModifier.FINAL, new FctTreeNodeGetFirstChild());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetLastChild, params_,aliasTreeNode, false, MethodModifier.FINAL, new FctTreeNodeGetLastChild());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetPreviousSibling, params_,aliasTreeNode, false, MethodModifier.FINAL, new FctTreeNodeGetPreviousSibling());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetNextSibling, params_,aliasTreeNode, false, MethodModifier.FINAL, new FctTreeNodeGetNextSibling());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetParentNode, params_,aliasTreeNode, false, MethodModifier.FINAL, new FctTreeNodeGetParentNode());
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeIsAncestor, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeIsAncestor0()),new FctTreeNodeIsAncestor());
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeIsDescendant, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeIsDescendant0()),new FctTreeNodeIsDescendant());
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode,aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeEq, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeEq0(),guiAliasParameters.getAliasTreeNode0TreeNodeEq1()), new FctTreeNodeEq());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctTreeNode0(_cust,_guiEx));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTreeNode0TreeNode0()), new FctTreeNode1(_cust,_guiEx));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTreeNode, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTree, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasTreeListener);
        method_ = new StandardMethod(aliasTreeAddTreeListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTree0TreeAddTreeListener0()), new FctTreeAddTreeListener());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTreeSetRootVisible, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTree0TreeSetRootVisible0()), new FctTreeSetRootVisible());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeIsRootVisible, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctTreeIsRootVisible());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeGetSelected, params_, aliasTreeNode, false, MethodModifier.FINAL, new FctTreeGetSelected0());
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeGetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTree0TreeGetSelected0()), new FctTreeGetSelected1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeReload, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTreeReload());
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTree0Tree0()), new FctTree(_cust,_guiEx,aliasTree));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTree, std_);


        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTableGui, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasTableAddHeader, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableAddHeader0()), new FctTableAddHeader());
        methods_.add( method_);
        params_ = new StringList(aliasTableListener);
        method_ = new StandardMethod(aliasTableAddSelect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableAddSelect0()), new FctTableAddSelect());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableIsMultiple, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctTableIsMultiple());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTableSetMultiple, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetMultiple0()), new FctTableSetMultiple());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableIsReorder, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctTableIsReorder());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTableSetReorder, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetReorder0()), new FctTableSetReorder());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetColumnName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableGetColumnName0()), new FctTableGetColumnName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetColumnCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTableGetColumnCount());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetColumnAtPoint, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableGetColumnAtPoint0(),guiAliasParameters.getAliasTableGui0TableGetColumnAtPoint1()), new FctTableGetColumnAtPoint());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetRowAtPoint, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableGetRowAtPoint0(),guiAliasParameters.getAliasTableGui0TableGetRowAtPoint1()), new FctTableGetRowAtPoint());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetRowCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTableGetRowCount());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableSetRowCount, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetRowCount0()), new FctTableSetRowCount());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableSetColumns, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetColumns0()), new FctTableSetColumns());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRow, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTableGetSelectedRow());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRowCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTableGetSelectedRowCount());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRows, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger()), false, MethodModifier.FINAL, new FctTableGetSelectedRows());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableMoveColumn, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableMoveColumn0(),guiAliasParameters.getAliasTableGui0TableMoveColumn1()), new FctTableMoveColumn());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableAddInterval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableAddInterval0(),guiAliasParameters.getAliasTableGui0TableAddInterval1()), new FctTableAddInterval());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableRemoveInterval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableRemoveInterval0(),guiAliasParameters.getAliasTableGui0TableRemoveInterval1()), new FctTableRemoveInterval());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableApplyChanges, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTableApplyChanges());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableSetValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TreeNodeSetUserObject0(),guiAliasParameters.getAliasTableGui0TreeNodeSetUserObject1(),guiAliasParameters.getAliasTableGui0TreeNodeSetUserObject2()), new FctTableSetValue());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetValue, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TreeNodeGetUserObject0(),guiAliasParameters.getAliasTableGui0TreeNodeGetUserObject1()), new FctTableGetValue());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,true,new StringList(guiAliasParameters.getAliasTableGui0TableGui0()), new FctTableGrid(_cust,_guiEx,aliasTableGui));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTableGui, std_);

        buildEvents(_content,_cust,_guiEx);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasPanel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL, new DfPanel(_cust,_guiEx,aliasPanel));
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasAddCompo, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel0TabbedPaneAdd0()),new FctPanelAddCompo0());
        methods_.add( method_);
        params_ = new StringList(aliasComponent,_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddCompo, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel1TabbedPaneAdd0(),guiAliasParameters.getAliasPanel1TabbedPaneAdd1()),new FctPanelAddCompo1());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasRemoveCompo, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel0RemoveCompo0()), new FctPanelRemove1());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveCompo, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel1RemoveCompo0()), new FctPanelRemove0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetIndexCompo, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel0TreeNodeGetUserObject0()), new FctPanelGetIndexCompo());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctPanelCount());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelFlow, params_, aliasPanel, false, MethodModifier.STATIC, new FctPanelFlow(_cust,_guiEx,aliasPanel));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelAbsolute, params_, aliasPanel, false, MethodModifier.STATIC, new FctPanelAbsolute(_cust,_guiEx,aliasPanel));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelPageBox, params_, aliasPanel, false, MethodModifier.STATIC, new FctPanelPageBox(_cust,_guiEx,aliasPanel));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPanelGrid, params_, aliasPanel, false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasPanel0PanelGrid0(),guiAliasParameters.getAliasPanel0PanelGrid1()), new FctPanelGrid(_cust,_guiEx,aliasPanel));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctPanelRemoveAll());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelValidate, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctPanelValidate());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctPanel(_cust,_guiEx,aliasPanel));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasPanel, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasPanelBorder, fields_, constructors_, methods_, aliasPanel, MethodModifier.FINAL, new DfPanelBorder(_cust,_guiEx,aliasPanelBorder));
        params_ = new StringList(aliasComponent,_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasAddCompo, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanelBorder0TabbedPaneAdd0(),guiAliasParameters.getAliasPanelBorder0TabbedPaneAdd1()), new FctPanelBorder());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctPanelBorderInst(_cust,_guiEx,aliasPanelBorder));
        constructors_.add(ctor_);
        fields_.add(new CstFieldInfo(aliasPanelBorderNorth,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderSouth,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderWest,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderEast,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderCenter,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderBeforeFirst,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderBeforeLineBegins,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderAfterLineEnds,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderAfterLast,_content.getCharSeq().getAliasString()));
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasPanelBorder, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTabbedPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL, new DfTabbedPane(_cust,_guiEx,aliasTabbedPane));
        params_ = new StringList(_content.getCharSeq().getAliasString(),aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneAdd0(),guiAliasParameters.getAliasTabbedPane0TabbedPaneAdd1()), new FctTabbedPaneAdd());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTabbedPaneSelIndex, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTabbedPaneSelIndex0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneSelIndex, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneSelIndex0()), new FctTabbedPaneSelIndex1());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneIndex, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneIndex0()), new FctTabbedPaneIndex());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneRemove, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TreeNodeRemove0()), new FctTabbedPaneRemove1());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneRemove, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane1TreeNodeRemove0()), new FctTabbedPaneRemove0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TreeNodeSetUserObject0(),guiAliasParameters.getAliasTabbedPane0TreeNodeSetUserObject1()), new FctTabbedPaneSet());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTabbedPaneSetTitle, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneSetTitle0(),guiAliasParameters.getAliasTabbedPane0TabbedPaneSetTitle1()), new FctTabbedPaneSetTitle());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneGet, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TreeNodeGetUserObject0()), new FctTabbedPaneGet());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneGetTitle, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneGetTitle0()), new FctTabbedPaneGetTitle());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTabbedPaneNb, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTabbedPaneNb());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTabbedPaneRemoveAll());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctTabbedPane(_cust,_guiEx,aliasTabbedPane));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTabbedPane, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasScrollPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL, new DfScrollPane(_cust,_guiEx,aliasScrollPane));
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneHorizontalValue, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctScrollPaneHorizontalValue0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasScrollPaneHorizontalValue, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPaneHorizontalValue0()), new FctScrollPaneHorizontalValue1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneVerticalValue, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctScrollPaneVerticalValue0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasScrollPaneVerticalValue, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPaneVerticalValue0()), new FctScrollPaneVerticalValue1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneGetView, params_,aliasComponent, false, MethodModifier.FINAL, new FctScrollPaneGetView());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneValidate, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctScrollPaneValidate());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasScrollPaneSetView, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPaneSetView0()), new FctScrollPaneSetView());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctScrollPane0(_cust,_guiEx,aliasScrollPane));
        constructors_.add(ctor_);
        params_ = new StringList(aliasComponent);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPane0()), new FctScrollPane1(_cust,_guiEx,aliasScrollPane));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasScrollPane, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSplitPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetLeft, params_,aliasComponent, false, MethodModifier.FINAL, new FctSplitPaneGetLeft());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasSplitPaneSetLeft, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetLeft0()), new FctSplitPaneSetLeft());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetRight, params_,aliasComponent, false, MethodModifier.FINAL, new FctSplitPaneGetRight());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasSplitPaneSetRight, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetRight0()), new FctSplitPaneSetRight());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetDividerLocation, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSplitPaneGetDividerLocation());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSplitPaneSetDividerLocation, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetDividerLocation0()), new FctSplitPaneSetDividerLocation());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetDividerSize, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSplitPaneGetDividerSize());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSplitPaneSetDividerSize, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetDividerSize0()), new FctSplitPaneSetDividerSize());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneIsOneTouchExpandable, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctSplitPaneIsOneTouchExpandable());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSplitPaneSetOneTouchExpandable, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetOneTouchExpandable0()), new FctSplitPaneSetOneTouchExpandable());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneIsContinuousLayout, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctSplitPaneIsContinuousLayout());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSplitPaneSetContinuousLayout, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetContinuousLayout0()), new FctSplitPaneSetContinuousLayout());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneValidate, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctSplitPaneValidate());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasComponent,aliasComponent);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSplitPane0SplitPane0(),guiAliasParameters.getAliasSplitPane0SplitPane1(),guiAliasParameters.getAliasSplitPane0SplitPane2()), new FctSplitPane(_cust,_guiEx,aliasSplitPane));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasSplitPane, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasInput, fields_, constructors_, methods_, aliasComponent, StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInputIsEnabled, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctInputIsEnabled());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasInputSetEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasInput0InputSetEnabled0()), new FctInputSetEnabled());
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasInput, std_);


        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasButton, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL, new DfButton(_cust,_guiEx,aliasButton));
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAddListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasButton0AddListener0()), new FctButtonAddAction());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctButton0(_cust,_guiEx,aliasButton));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasButton0Button0()), new FctButton1(_cust,_guiEx,aliasButton));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasButton, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasProgBar, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarMin, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0ProgBarMin0()), new FctProgBarMin1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarMin, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctProgBarMin0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0TreeNodeGetUserObject0()), new FctProgBarValue1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarValue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctProgBarValue0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarMax, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0ProgBarMax0()), new FctProgBarMax1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarMax, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctProgBarMax0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasProgBarOr, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0ProgBarOr0()), new FctProgBarHoriz1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarOr, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctProgBarHoriz0());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctProgBar(_cust,_guiEx,aliasProgBar));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasProgBar, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTextLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL, new DfTextLabel(_cust,_guiEx,aliasTextLabel));
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasSetLabelText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextLabel0SetLabelText0()), new FctTextLabel());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctTextLabel0(_cust,_guiEx,aliasTextLabel));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextLabel0TextLabel0()), new FctTextLabel1(_cust,_guiEx,aliasTextLabel));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTextLabel, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasImageLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL, new DfImageLabel(_cust,_guiEx,aliasImageLabel));
        params_ = new StringList(aliasImage);
        method_ = new StandardMethod(aliasSetLabelImage, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImageLabel0SetLabelImage0()), new FctImageLabel(_guiEx));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctImageLabel0(_cust,_guiEx,aliasImageLabel));
        constructors_.add(ctor_);
        params_ = new StringList(aliasImage);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasImageLabel0ImageLabel0()), new FctImageLabel1(_cust,_guiEx,aliasImageLabel));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasImageLabel, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasFont, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfFont());
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctFontGetName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetSize, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctFontGetSize());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsBold, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctFontIsBold());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsItalic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctFontIsItalic());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasFont0FontStringWidth0()), new FctFontStringWidth1(_guiEx));
        methods_.add( method_);
        params_ = new StringList(aliasImage,_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasFont1FontStringWidth0(),guiAliasParameters.getAliasFont1FontStringWidth1()), new FctFontStringWidth0(_guiEx));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctFont0());
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasFont0Font0()), new FctFont1());
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasFont1Font0(),guiAliasParameters.getAliasFont1Font1(),guiAliasParameters.getAliasFont1Font2(),guiAliasParameters.getAliasFont1Font3()), new FctFont2());
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasFont, stdcl_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasColor, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorAlpha, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctColorAlpha());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorBlue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctColorBlue());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorRed, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctColorRed());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorGreen, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctColorGreen());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorIsTransparent, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctColorTransparent());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor0Color0()),new FctColor0());
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor1Color0(),guiAliasParameters.getAliasColor1Color1()), new FctColor1());
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor2Color0(),guiAliasParameters.getAliasColor2Color1(),guiAliasParameters.getAliasColor2Color2()), new FctColor2());
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor3Color0(),guiAliasParameters.getAliasColor3Color1(),guiAliasParameters.getAliasColor3Color2(),guiAliasParameters.getAliasColor3Color3()), new FctColor3());
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasColor, stdcl_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasImage, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageIsWithAlpha, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctImageIsWithAlpha());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetHeight, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctImageGetHeight());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctImageGetWidth());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageGet, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0TreeNodeGetUserObject0(),guiAliasParameters.getAliasImage0TreeNodeGetUserObject1()), new FctImageGet());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(), _content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0TreeNodeSetUserObject0(),guiAliasParameters.getAliasImage0TreeNodeSetUserObject1(),guiAliasParameters.getAliasImage0TreeNodeSetUserObject2()), new FctImageSet());
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasImage);
        method_ = new StandardMethod(aliasImageEq, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC, new StringList(guiAliasParameters.getAliasImage0ImageEq0(),guiAliasParameters.getAliasImage0ImageEq1()), new FctImageEq());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetColor, params_, aliasColor, false, MethodModifier.FINAL, new FctImageGetColor());
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasImageSetColor, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageSetColor0()), new FctImageSetColor());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetFont, params_, aliasFont, false, MethodModifier.FINAL, new FctImageGetFont());
        methods_.add( method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasImageSetFont, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0SetFont0()), new FctImageSetFont());
        methods_.add( method_);

        params_ = new StringList(aliasImage,_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDraw0(),guiAliasParameters.getAliasImage0ImageDraw1(),guiAliasParameters.getAliasImage0ImageDraw2()), new FctImageDraw0());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage1ImageDraw0(),guiAliasParameters.getAliasImage1ImageDraw1(),guiAliasParameters.getAliasImage1ImageDraw2()), new FctImageDraw1());
        methods_.add( method_);

        String arrInt_ = StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger());
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawLine, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawLine0(),guiAliasParameters.getAliasImage0ImageDrawLine1(),guiAliasParameters.getAliasImage0ImageDrawLine2(),guiAliasParameters.getAliasImage0ImageDrawLine3()), new FctImageDrawLine());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawRect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawRect0(),guiAliasParameters.getAliasImage0ImageDrawRect1(),guiAliasParameters.getAliasImage0ImageDrawRect2(),guiAliasParameters.getAliasImage0ImageDrawRect3()), new FctImageDrawRect());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawOval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawOval0(),guiAliasParameters.getAliasImage0ImageDrawOval1(),guiAliasParameters.getAliasImage0ImageDrawOval2(),guiAliasParameters.getAliasImage0ImageDrawOval3()), new FctImageDrawOval());
        methods_.add( method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageDrawPolygon, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawPolygon0(),guiAliasParameters.getAliasImage0ImageDrawPolygon1()), new FctImageDrawPolygon());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillRect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageFillRect0(),guiAliasParameters.getAliasImage0ImageFillRect1(),guiAliasParameters.getAliasImage0ImageFillRect2(),guiAliasParameters.getAliasImage0ImageFillRect3()), new FctImageFillRect());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillOval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageFillOval0(),guiAliasParameters.getAliasImage0ImageFillOval1(),guiAliasParameters.getAliasImage0ImageFillOval2(),guiAliasParameters.getAliasImage0ImageFillOval3()), new FctImageFillOval());
        methods_.add( method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageFillPolygon, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageFillPolygon0(),guiAliasParameters.getAliasImage0ImageFillPolygon1()), new FctImageFillPolygon());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageDispose, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctImageDispose());
        methods_.add( method_);

        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasImage0Image0(),guiAliasParameters.getAliasImage0Image1(),guiAliasParameters.getAliasImage0Image2()), new FctImage(_guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasImage, stdcl_);
        buildInputs(_content,_cust,_guiEx);
        buildMenus(_content,_cust,_guiEx);
    }
    private void buildEvents(LgNamesContent _content, CustAliases _cust, GuiExecutingBlocks _guiEx) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasActionEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasActionEventIsAlt, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctActionEventIsAlt());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasActionEventIsCtrl, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctActionEventIsCtrl());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasActionEventIsShift, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctActionEventIsShift());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasActionEventCommand, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctActionEventCommand());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getCharSeq().getAliasString());
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new StringList(guiAliasParameters.getAliasActionEvent0ActionEvent0(), guiAliasParameters.getAliasActionEvent0ActionEvent1(), guiAliasParameters.getAliasActionEvent0ActionEvent2(), guiAliasParameters.getAliasActionEvent0ActionEvent3()), new FctActionEvent(_cust, _guiEx, aliasActionEvent));
        constructors_.add(ctor_);
        StandardClass std_ = stdcl_;
        _content.getStandards().addEntry(aliasActionEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAction, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasActionWrap, params_, aliasAction, false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasAction0Wrap0()), new FctActionWrap(aliasAction,_guiEx.getCompoFactory()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasActionEnabled, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctActionEnabled0());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasActionEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new StringList(guiAliasParameters.getAliasAction0Enabled0()), new FctActionEnabled1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasActionArg, params_, aliasActionListener, false, MethodModifier.FINAL, new FctActionArg());
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasAction, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCommand, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL,new DfCommand(aliasCommand));
        params_ = new StringList();
        method_ = new StandardMethod(aliasCommandBinding, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctCommandBinding0());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCommandBinding, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new StringList(guiAliasParameters.getAliasCommand0Binding0()),new FctCommandBinding1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCommandAction, params_, aliasAction, false, MethodModifier.FINAL, new FctCommandAction0());
        methods_.add( method_);
        params_ = new StringList(aliasAction);
        method_ = new StandardMethod(aliasCommandAction, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new StringList(guiAliasParameters.getAliasCommand0Action0()), new FctCommandAction1());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, new FctCommand(aliasCommand));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasCommand, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWindowEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctWindowEvent(_cust,_guiEx,aliasWindowEvent));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWindowEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMouseEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsAlt, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMouseEventIsAlt());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsCtrl, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMouseEventIsCtrl());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsShift, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMouseEventIsShift());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsLeft, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMouseEventIsLeft());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsMiddle, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMouseEventIsMiddle());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsRight, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMouseEventIsRight());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetClicks, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctMouseEventGetClicks());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetFirst, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctMouseEventGetFirst());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetSecond, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctMouseEventGetSecond());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMouseEvent0MouseEvent0(),guiAliasParameters.getAliasMouseEvent0MouseEvent1(),guiAliasParameters.getAliasMouseEvent0MouseEvent2(),guiAliasParameters.getAliasMouseEvent0MouseEvent3(),guiAliasParameters.getAliasMouseEvent0MouseEvent4(),guiAliasParameters.getAliasMouseEvent0MouseEvent5(),guiAliasParameters.getAliasMouseEvent0MouseEvent6(),guiAliasParameters.getAliasMouseEvent0MouseEvent7(),guiAliasParameters.getAliasMouseEvent0MouseEvent8()), new FctMouseEvent(_cust,_guiEx,aliasMouseEvent));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasMouseEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWheelEvent, fields_, constructors_, methods_, aliasMouseEvent, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWheelRotatedClicks, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctWheelRotatedClicks());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasWheelEvent0WheelEvent0(),guiAliasParameters.getAliasWheelEvent0WheelEvent1(),guiAliasParameters.getAliasWheelEvent0WheelEvent2(),guiAliasParameters.getAliasWheelEvent0WheelEvent3(),guiAliasParameters.getAliasWheelEvent0WheelEvent4(),guiAliasParameters.getAliasWheelEvent0WheelEvent5(),guiAliasParameters.getAliasWheelEvent0WheelEvent6(),guiAliasParameters.getAliasWheelEvent0WheelEvent7(),guiAliasParameters.getAliasWheelEvent0WheelEvent8(),guiAliasParameters.getAliasWheelEvent0WheelEvent9()), new FctWheelEvent(_cust,_guiEx,aliasWheelEvent));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWheelEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasKeyEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsAlt, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctKeyEventIsAlt());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsCtrl, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctKeyEventIsCtrl());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsShift, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctKeyEventIsShift());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventChar, params_, _content.getPrimTypes().getAliasPrimChar(), false, MethodModifier.FINAL, new FctKeyEventChar());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventCode, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctKeyEventCode());
        methods_.add( method_);
        params_ = new StringList(
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasKeyEvent0KeyEvent0(),guiAliasParameters.getAliasKeyEvent0KeyEvent1(),guiAliasParameters.getAliasKeyEvent0KeyEvent2(),guiAliasParameters.getAliasKeyEvent0KeyEvent3(),guiAliasParameters.getAliasKeyEvent0KeyEvent4()), new FctKeyEvent(_cust,_guiEx,aliasKeyEvent));
        constructors_.add(ctor_);

        std_ = stdcl_;
        _content.getStandards().addEntry(aliasKeyEvent, std_);
    }
    private void buildMenus(LgNamesContent _content, CustAliases _cust, GuiExecutingBlocks _guiEx) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasMenuBar, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        StringList params_ = new StringList(aliasMenu);
        StandardMethod method_ = new StandardMethod(aliasMenuBarAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new StringList(guiAliasParameters.getAliasMenuBar0TabbedPaneAdd0()), new FctMenuBarAdd());
        methods_.add( method_);
        params_ = new StringList(aliasMenu);
        method_ = new StandardMethod(aliasMenuBarRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuBar0TreeNodeRemove0()), new FctMenuBarRemove());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasMenuBarGet, params_, aliasMenu, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuBar0TreeNodeGetUserObject0()), new FctMenuBarGet());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuBarNb, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctMenuBarNb());
        methods_.add( method_);
        params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new FctMenuBar(_cust, _guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenuBar, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAbsMenu, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuGetParent, params_, aliasMenu, false, MethodModifier.FINAL, new FctAbsMenuGetParentMenu());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasAbsMenuSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenu0AbsMenuSetText0()), new FctAbsMenuSetText());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctAbsMenuGetText());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasAbsMenuSetEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenu0InputSetEnabled0()), new FctAbsMenuSetEnabled());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuIsEnabled, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctAbsMenuIsEnabled());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasAbsMenuSetDeepEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenu0AbsMenuSetDeepEnabled0()), new FctAbsMenuSetDeepEnabled());
        methods_.add( method_);
        _content.getStandards().addEntry(aliasAbsMenu, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenu, fields_, constructors_, methods_, aliasAbsMenu, MethodModifier.FINAL);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasMenuAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenu0TabbedPaneAdd0()), new FctMenuAdd());
        methods_.add( method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasMenuRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenu0TreeNodeRemove0()), new FctMenuRemove());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasMenuGet, params_, aliasAbsMenu, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenu0TreeNodeGetUserObject0()), new FctMenuGetMenu());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuNb, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctMenuNb());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuAddSeparator, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctMenuAddSeparator());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctMenu0(_cust,_guiEx));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMenu0Menu0()), new FctMenu1(_cust,_guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenu, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAbsMenuItem, fields_, constructors_, methods_, aliasAbsMenu, StdClassModifier.ABSTRACT);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAbsMenuItemAddAction, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenuItem0TabbedPaneAdd0()), new FctAbsMenuItemAddActionListener());
        methods_.add( method_);
        _content.getStandards().addEntry(aliasAbsMenuItem, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenuItem, fields_, constructors_, methods_, aliasAbsMenuItem, MethodModifier.FINAL);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctMenuItem0(_cust,_guiEx));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMenuItem0MenuItem0()), new FctMenuItem1(_cust,_guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenuItem, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenuItemCheck, fields_, constructors_, methods_, aliasAbsMenuItem, MethodModifier.FINAL);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasMenuItemCheckSetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuItemCheck0RadioSetSelected0()), new FctMenuItemCheckSetSelected());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuItemCheckIsSelected, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctMenuItemCheckIsSelected());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctMenuItemCheck0(_cust,_guiEx));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMenuItemCheck0MenuItemCheck0()), new FctMenuItemCheck1(_cust,_guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenuItemCheck, stdcl_);

    }
    private void buildInputs(LgNamesContent _content, CustAliases _cust, GuiExecutingBlocks _guiEx) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasRender, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfRender(_cust, _guiEx));
        String typeHeight_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasGrList,",",_content.getCoreNames().getAliasObject(),",",_content.getPrimTypes().getAliasPrimInteger(),",",_content.getPrimTypes().getAliasPrimBoolean(),",",_content.getPrimTypes().getAliasPrimInteger(),","+_content.getPrimTypes().getAliasPrimInteger()+">");
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasRenderGetHeight, params_, typeHeight_, false, MethodModifier.FINAL, new FctRenderGetHeight());
        methods_.add( method_);
        params_ = new StringList(typeHeight_);
        method_ = new StandardMethod(aliasRenderSetHeight, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRender0RenderSetHeight0()), new FctRenderSetHeight());
        methods_.add( method_);
        String typeWidth_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasGrList,",",_content.getCoreNames().getAliasObject(),",",_content.getPrimTypes().getAliasPrimInteger(),",",_content.getPrimTypes().getAliasPrimBoolean(),",",_content.getPrimTypes().getAliasPrimInteger(),","+_content.getPrimTypes().getAliasPrimInteger()+">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetWidth, params_, typeWidth_, false, MethodModifier.FINAL, new FctRenderGetWidth());
        methods_.add( method_);
        params_ = new StringList(typeWidth_);
        method_ = new StandardMethod(aliasRenderSetWidth, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRender0RenderSetWidth0()), new FctRenderSetWidth());
        methods_.add( method_);
        String typePaint_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasGrList,",",_content.getCoreNames().getAliasObject(),",",_content.getPrimTypes().getAliasPrimInteger(),",",_content.getPrimTypes().getAliasPrimBoolean(),",",aliasImage,",?>");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetPaint, params_, typePaint_, false, MethodModifier.FINAL, new FctRenderGetPaint());
        methods_.add( method_);
        params_ = new StringList(typePaint_);
        method_ = new StandardMethod(aliasRenderSetPaint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRender0ComponentSetPaint0()), new FctRenderSetPaint());
        methods_.add( method_);
        params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new FctRender(_cust, _guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasRender, stdcl_);


        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasGrList, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL, new DfGrList(_cust,_guiEx,aliasGrList));
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetRender, params_, aliasRender, false, MethodModifier.FINAL, new FctGrListGetRender());
        methods_.add( method_);
        params_ = new StringList(aliasRender);
        method_ = new StandardMethod(aliasGrListSetRender, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListSetRender0()), new FctGrListSetRender());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelections, params_, StringExpUtil.getPrettyArrayType(aliasListSelection), false, MethodModifier.FINAL, new FctGrListGetSelections());
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasGrListAddSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListAddSelection0()), new FctGrListAddSelection());
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasGrListRemoveSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListRemoveSelection0()), new FctGrListRemoveSelection());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetVisibleRowCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctGrListGetVisibleRowCount());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetVisibleRowCount, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListSetVisibleRowCount0()), new FctGrListSetVisibleRowCount());
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList2TabbedPaneAdd0()), new FctGrListAdd2(this,_guiEx));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0TabbedPaneAdd0(),guiAliasParameters.getAliasGrList0TabbedPaneAdd1()), new FctGrListAdd0(this,_guiEx));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasImageLabel,_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList1TabbedPaneAdd0(),guiAliasParameters.getAliasGrList1TabbedPaneAdd1(),guiAliasParameters.getAliasGrList1TabbedPaneAdd2()), new FctGrListAdd1());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList1TreeNodeSetUserObject0(),guiAliasParameters.getAliasGrList1TreeNodeSetUserObject1()), new FctGrListSet0(this,_guiEx));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasImageLabel,_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0TreeNodeSetUserObject0(),guiAliasParameters.getAliasGrList0TreeNodeSetUserObject1(),guiAliasParameters.getAliasGrList0TreeNodeSetUserObject2()), new FctGrListSet1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetListView, params_, StringExpUtil.getPrettyArrayType(_content.getCoreNames().getAliasObject()), false, MethodModifier.FINAL, new FctGrListGetListView());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelectedIndexes, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger()), false, MethodModifier.FINAL, new FctGrListGetSelectedIndexes());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetSelectedIndexes, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListSetSelectedIndexes0()), new FctGrListSetSelectedIndexes(this,_guiEx));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClearSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctGrListClearSelection(this,_guiEx));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListUpdateGraphics, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctGrListUpdateGraphics());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0RemoveCompo0()), new FctGrListRemove());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClear, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctGrListClear());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasGrList0GrList0()), new FctGrList(_cust,_guiEx,aliasGrList));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasGrList, stdcl_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCombo, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL, new DfCombo(_cust,_guiEx,aliasCombo));
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetListeners, params_, StringExpUtil.getPrettyArrayType(aliasListSelection), false, MethodModifier.FINAL, new FctComboGetListeners());
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasComboAddListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboAddListener0()), new FctComboAddListener());
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasComboRemoveListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboRemoveListener0()), new FctComboRemoveListener());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasComboAddItem, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0TabbedPaneAdd0()), new FctComboAddItem());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboSelectItem, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboSelectItem0()), new FctComboSelectItem());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetItemCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctComboGetItemCount());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndex, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctComboGetSelectedIndex());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndexes, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger()), false, MethodModifier.FINAL,new FctComboGetSelectedIndexes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedItem, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctComboGetSelectedItem());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboRemoveItem, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboRemoveItem0()), new FctComboRemoveItem());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboRemoveAllItems, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctComboRemoveAllItems());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctCombo0(_cust,_guiEx,aliasCombo));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,true,new StringList(guiAliasParameters.getAliasCombo0Combo0()), new FctCombo1(_cust,_guiEx,aliasCombo));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,true,new StringList(guiAliasParameters.getAliasCombo1Combo0(),guiAliasParameters.getAliasCombo1Combo1()), new FctCombo2(_cust,_guiEx,aliasCombo));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasCombo, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasButtonGroup, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasRadio);
        method_ = new StandardMethod(aliasButtonGroupAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasButtonGroup0TabbedPaneAdd0()), new FctButtonGroupAdd());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctButtonGroup(_cust,_guiEx));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasButtonGroup, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasPopupMenu, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0TabbedPaneAdd0()), new FctPopupMenuAdd());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuGetComp, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuGetComp0()), new FctPopupMenuGetComp());
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuRemoveComp, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuRemoveComp0()), new FctPopupMenuRemoveComp());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPopupMenuNbComp, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctPopupMenuNbComp());
        methods_.add( method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasPopupMenuAddMenu, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuAddMenu0()), new FctPopupMenuAddMenu());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuGetMenu, params_, aliasAbsMenu, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuGetMenu0()), new FctPopupMenuGetMenu());
        methods_.add( method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasPopupMenuRemoveMenu, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuRemoveMenu0()), new FctPopupMenuRemoveMenu());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPopupMenuNbMenu, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctPopupMenuNbMenu());
        methods_.add( method_);
        params_ = new StringList(aliasComponent,_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuShow, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuShow0(),guiAliasParameters.getAliasPopupMenu0PopupMenuShow1(),guiAliasParameters.getAliasPopupMenu0PopupMenuShow2()), new FctPopupMenuShow());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctPopupMenu(_cust,_guiEx,aliasPopupMenu));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasPopupMenu, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasRadio, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioIsSelected, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctRadioIsSelected());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasRadioSetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRadio0RadioSetSelected0()), new FctRadioSetSelected());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctRadioGetText());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasRadioSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRadio0SetLabelText0()), new FctRadioSetText());
        methods_.add( method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRadio0AddChange0()), new FctRadioAddChange());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctRadio0(_cust,_guiEx,aliasRadio));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasRadio0Radio0()), new FctRadio1(_cust,_guiEx,aliasRadio));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasRadio1Radio0(),guiAliasParameters.getAliasRadio1Radio1()), new FctRadio2(_cust,_guiEx,aliasRadio));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasRadio, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCheckBox, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxIsSelected, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctCheckBoxIsSelected());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCheckBoxSetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCheckBox0RadioSetSelected0()), new FctCheckBoxSetSelected());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctCheckBoxGetText());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCheckBoxSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCheckBox0SetLabelText0()), new FctCheckBoxSetText());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctCheckBox0(_cust,_guiEx,aliasCheckBox));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasCheckBox0CheckBox0()), new FctCheckBox1(_cust,_guiEx,aliasCheckBox));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasCheckBox1CheckBox0(),guiAliasParameters.getAliasCheckBox1CheckBox1()), new FctCheckBox2(_cust,_guiEx,aliasCheckBox));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasCheckBox, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTextField, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextFieldGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctTextFieldGetText());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextFieldSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextField0SetLabelText0()), new FctTextFieldSetText());
        methods_.add( method_);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasTextFieldAddAction, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextField0TextFieldAddAction0()), new FctTextFieldAddAction());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctTextField0(_cust,_guiEx,aliasTextField));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextField0TextField0()), new FctTextField1(_cust,_guiEx,aliasTextField));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextField1TextField0()), new FctTextField2(_cust,_guiEx,aliasTextField));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextField2TextField0(),guiAliasParameters.getAliasTextField2TextField1()), new FctTextField3(_cust,_guiEx,aliasTextField));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasTextField, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTextArea, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctTextAreaGetText());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextAreaSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0SetLabelText0()), new FctTextAreaSetText());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextAreaAppend, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TabbedPaneAdd0()), new FctTextAreaAppend());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaInsert, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TreeNodeInsert0(),guiAliasParameters.getAliasTextArea0TreeNodeInsert1()), new FctTextAreaInsert());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetSelectedText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctTextAreaGetSelectedText());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionStart, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaSetSelectionStart0()), new FctTextAreaSetSelectionStart());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionEnd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaSetSelectionEnd0()), new FctTextAreaSetSelectionEnd());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetTabSize, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTextAreaGetTabSize());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetTabSize, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaSetTabSize0()), new FctTextAreaSetTabSize());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaReplaceRange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaReplaceRange0(),guiAliasParameters.getAliasTextArea0TextAreaReplaceRange1(),guiAliasParameters.getAliasTextArea0TextAreaReplaceRange2()), new FctTextAreaReplaceRange());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextAreaReplaceSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaReplaceSelection0()), new FctTextAreaReplaceSelection());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSelect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TreeGetSelected0(),guiAliasParameters.getAliasTextArea0TreeGetSelected1()), new FctTextAreaSelect());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaSelectAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTextAreaSelectAll());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctTextArea0(_cust,_guiEx,aliasTextArea));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextArea0TextArea0()), new FctTextArea1(_cust,_guiEx,aliasTextArea));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextArea1TextArea0(),guiAliasParameters.getAliasTextArea1TextArea1()), new FctTextArea2(_cust,_guiEx,aliasTextArea));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextArea2TextArea0(),guiAliasParameters.getAliasTextArea2TextArea1(),guiAliasParameters.getAliasTextArea2TextArea2()), new FctTextArea3(_cust,_guiEx,aliasTextArea));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasTextArea, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSpinner, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetMax, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSpinnerGetMax());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetMax, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetMax0()), new FctSpinnerSetMax());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetMin, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSpinnerGetMin());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetMin, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetMin0()), new FctSpinnerSetMin());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetValue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSpinnerGetValue());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0TreeNodeSetUserObject0()), new FctSpinnerSetValue());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetStep, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSpinnerGetStep());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetStep, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetStep0()), new FctSpinnerSetStep());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetRange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetRange0(),guiAliasParameters.getAliasSpinner0SpinnerSetRange1()), new FctSpinnerSetRange());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetRangeValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetRangeValue0(),guiAliasParameters.getAliasSpinner0SpinnerSetRangeValue1(),guiAliasParameters.getAliasSpinner0SpinnerSetRangeValue2()), new FctSpinnerSetRangeValue());
        methods_.add( method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0AddChange0()), new FctSpinnerAddChange());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSpinner0Spinner0(),guiAliasParameters.getAliasSpinner0Spinner1(),guiAliasParameters.getAliasSpinner0Spinner2(),guiAliasParameters.getAliasSpinner0Spinner3()), new FctSpinner(_cust,_guiEx,aliasSpinner));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasSpinner, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSlider, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetMax, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSliderGetMax());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetMax, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0SpinnerSetMax0()), new FctSliderSetMax());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetMin, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSliderGetMin());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetMin, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0SpinnerSetMin0()), new FctSliderSetMin());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetValue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSliderGetValue());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0TreeNodeSetUserObject0()), new FctSliderSetValue());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetOrientation, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctSliderGetOrientation());
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetOrientation, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0SliderSetOrientation0()), new FctSliderSetOrientation());
        methods_.add( method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0AddChange0()), new FctSliderAddChange());
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false, new FctSlider0(_cust,_guiEx,aliasSlider));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider0Slider0()), new FctSlider1(_cust,_guiEx,aliasSlider));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider1Slider0(),guiAliasParameters.getAliasSlider1Slider1()), new FctSlider2(_cust,_guiEx,aliasSlider));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider2Slider0(),guiAliasParameters.getAliasSlider2Slider1(),guiAliasParameters.getAliasSlider2Slider2()), new FctSlider3(_cust,_guiEx,aliasSlider));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider3Slider0(),guiAliasParameters.getAliasSlider3Slider1(),guiAliasParameters.getAliasSlider3Slider2(),guiAliasParameters.getAliasSlider3Slider3()), new FctSlider4(_cust,_guiEx,aliasSlider));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasSlider, stdcl_);
    }

    public Struct getInnerSimpleResult(ClassField _classField,LgNamesContent _content) {
        if (StringUtil.quickEq(_classField.getClassName(),aliasConfirm)) {
            return getResultConfirm(_classField);
        }
        if (StringUtil.quickEq(_classField.getClassName(),aliasPanelBorder)) {
            return getResultPanelBorder(_classField);
        }
        return ValidatorStandard.getSimpleResultBase(_classField, _content.getNbAlias());
    }

    private Struct getResultPanelBorder(ClassField _classField) {
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderNorth)) {
            return new StringStruct(PanelBorderStruct.NORTH);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderSouth)) {
            return new StringStruct(PanelBorderStruct.SOUTH);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderWest)) {
            return new StringStruct(PanelBorderStruct.WEST);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderEast)) {
            return new StringStruct(PanelBorderStruct.EAST);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderCenter)) {
            return new StringStruct(PanelBorderStruct.CENTER);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderBeforeFirst)) {
            return new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderBeforeLineBegins)) {
            return new StringStruct(PanelBorderStruct.BEFORE_LINE_BEGINS);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderAfterLineEnds)) {
            return new StringStruct(PanelBorderStruct.AFTER_LINE_ENDS);
        }
        return new StringStruct(PanelBorderStruct.AFTER_LAST_LINE);
    }

    private Struct getResultConfirm(ClassField _classField) {
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,aliasConfirmFieldOk)) {
            return new IntStruct(OtherConfirmDialog.OK_OPTION);
        }
        if (StringUtil.quickEq(fieldName_,aliasConfirmFieldYes)) {
            return new IntStruct(OtherConfirmDialog.YES_OPTION);
        }
        if (StringUtil.quickEq(fieldName_,aliasConfirmFieldNo)) {
            return new IntStruct(OtherConfirmDialog.NO_OPTION);
        }
        return new IntStruct(OtherConfirmDialog.CANCEL_OPTION);
    }

    public void otherAliasGui(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasTreeNode(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE)));
        setAliasTree(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE)));
        setAliasTableGui(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI)));
        setAliasButton(LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON)));
        setAliasConfirm(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM)));
        setAliasFrame(LgNamesContent.get(_util,_cust,_mapping.getVal(FRAME)));
        setAliasDialog(LgNamesContent.get(_util,_cust,_mapping.getVal(DIALOG)));
        setAliasPanel(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL)));
        setAliasProgBar(LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR)));
        setAliasImageSet(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_SET)));
        setAliasKeyEvent(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT)));
        setAliasRadio(LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO)));
        setAliasCheckBox(LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX)));
        setAliasSetFont(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_FONT)));
        setAliasSpinner(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER)));
        setAliasDispose(LgNamesContent.get(_util,_cust,_mapping.getVal(DISPOSE)));
        setAliasCloseAll(LgNamesContent.get(_util,_cust,_mapping.getVal(CLOSE_ALL)));
        setAliasGrList(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST)));
        setAliasGetFont(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_FONT)));
        setAliasImage(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE)));
        setAliasSlider(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER)));
        setAliasPaintAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_ADD)));
        setAliasPaintSet(LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_SET)));
        setAliasCount(LgNamesContent.get(_util,_cust,_mapping.getVal(COUNT)));
        setAliasPack(LgNamesContent.get(_util,_cust,_mapping.getVal(PACK)));
        setAliasColor(LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR)));
        setAliasWindow(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW)));
        setAliasWindowSet(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET)));
        setAliasWindowSetAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_ADD)));
        setAliasWindowSetAll(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_ALL)));
        setAliasWindowSetContains(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_CONTAINS)));
        setAliasWindowSetRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_REMOVE)));
        setAliasWindowSetSnapshot(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_SNAPSHOT)));
        setAliasCompBack(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_BACK)));
        setAliasCompoRelLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPO_REL_LEFT)));
        setAliasCompoRelRight(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPO_REL_RIGHT)));
        setAliasCompoRelTop(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPO_REL_TOP)));
        setAliasCompoRelBottom(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPO_REL_BOTTOM)));
        setAliasCompoRelCentHoriz(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPO_REL_CENT_HORIZ)));
        setAliasCompoRelCentVert(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPO_REL_CENT_VERT)));
        setAliasPaint(LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT)));
        setAliasCompLoc(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_LOC)));
        setAliasImageGet(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_GET)));
        setAliasRender(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER)));
        setAliasCombo(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO)));
        setAliasAddCompo(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_COMPO)));
        setAliasInput(LgNamesContent.get(_util,_cust,_mapping.getVal(INPUT)));
        setAliasFont(LgNamesContent.get(_util,_cust,_mapping.getVal(FONT)));
        setAliasCompFore(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_FORE)));
        setAliasKeyTyped(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_TYPED)));
        setAliasColorRed(LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_RED)));
        setAliasTextArea(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA)));
        setAliasFontGetSize(LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_GET_SIZE)));
        setAliasDimension(LgNamesContent.get(_util,_cust,_mapping.getVal(DIMENSION)));
        setAliasProgBarOr(LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_OR)));
        setAliasFontIsBold(LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_IS_BOLD)));
        setAliasDimensionGetHeight(LgNamesContent.get(_util,_cust,_mapping.getVal(DIMENSION_GET_HEIGHT)));
        setAliasPanelBorderNorth(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_NORTH)));
        setAliasPanelBorderSouth(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_SOUTH)));
        setAliasRemoveWindowListener(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_WINDOW_LISTENER)));
        setAliasWindowTypeRelative(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_RELATIVE)));
        setAliasPanelBorderAfterLast(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_AFTER_LAST)));
        setAliasFontGetName(LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_GET_NAME)));
        setAliasDimensionGetWidth(LgNamesContent.get(_util,_cust,_mapping.getVal(DIMENSION_GET_WIDTH)));
        setAliasComponent(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT)));
        setAliasPanelBorderAfterLineEnds(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_AFTER_LINE_ENDS)));
        setAliasComponentSetAutoscrolls(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_SET_AUTOSCROLLS)));
        setAliasComponentIsAutoscrolls(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_IS_AUTOSCROLLS)));
        setAliasComponentGetWidth(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_GET_WIDTH)));
        setAliasComponentGetHeight(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_GET_HEIGHT)));
        setAliasPanelBorderEast(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_EAST)));
        setAliasProgBarMax(LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_MAX)));
        setAliasGetWindowListeners(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_WINDOW_LISTENERS)));
        setAliasPanelBorderBeforeFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_BEFORE_FIRST)));
        setAliasTextLabel(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_LABEL)));
        setAliasPanelBorderWest(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_WEST)));
        setAliasProgBarMin(LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_MIN)));
        setAliasProgBarValue(LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_VALUE)));
        setAliasPanelBorder(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER)));
        setAliasFontIsItalic(LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_IS_ITALIC)));
        setAliasImageLabel(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_LABEL)));
        setAliasPanelBorderCenter(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_CENTER)));
        setAliasFontStringWidth(LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_STRING_WIDTH)));
        setAliasTableSetColumns(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_SET_COLUMNS)));
        setAliasTreeIsRootVisible(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_IS_ROOT_VISIBLE)));
        setAliasTreeNodeRemoveFromParent(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_REMOVE_FROM_PARENT)));
        setAliasTreeNodeSetUserObject(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_SET_USER_OBJECT)));
        setAliasTreeReload(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_RELOAD)));
        setAliasTreeAddTreeListener(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_ADD_TREE_LISTENER)));
        setAliasTreeNodeEq(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_EQ)));
        setAliasTreeNodeNb(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_NB)));
        setAliasTableIsMultiple(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_IS_MULTIPLE)));
        setAliasTableListener(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_LISTENER)));
        setAliasTableGetRowCount(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_ROW_COUNT)));
        setAliasTreeNodeIsAncestor(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_IS_ANCESTOR)));
        setAliasTreeNodeGetNextSibling(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_GET_NEXT_SIBLING)));
        setAliasTreeNodeGetLastChild(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_GET_LAST_CHILD)));
        setAliasTreeSetRootVisible(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_SET_ROOT_VISIBLE)));
        setAliasTableSetMultiple(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_SET_MULTIPLE)));
        setAliasTreeNodeGetParentNode(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_GET_PARENT_NODE)));
        setAliasTreeNodeRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_REMOVE)));
        setAliasTableGetSelectedRow(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_SELECTED_ROW)));
        setAliasTreeNodeGetFirstChild(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_GET_FIRST_CHILD)));
        setAliasTreeNodeGetUserObject(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_GET_USER_OBJECT)));
        setAliasTableGetSelectedRows(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_SELECTED_ROWS)));
        setAliasTableValueTableChanged(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_VALUE_TABLE_CHANGED)));
        setAliasTableGetColumnCount(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_COLUMN_COUNT)));
        setAliasTableGetRowAtPoint(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_ROW_AT_POINT)));
        setAliasTableSetRowCount(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_SET_ROW_COUNT)));
        setAliasTableGetColumnName(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_COLUMN_NAME)));
        setAliasTableGetValue(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_VALUE)));
        setAliasTreeNodeIsDescendant(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_IS_DESCENDANT)));
        setAliasTableSetValue(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_SET_VALUE)));
        setAliasTreeGetSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_GET_SELECTED)));
        setAliasTableGetSelectedRowCount(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_SELECTED_ROW_COUNT)));
        setAliasTableGetColumnAtPoint(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GET_COLUMN_AT_POINT)));
        setAliasConfirmOk(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_OK)));
        setAliasConfirmYesNo(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_YES_NO)));
        setAliasTableRemoveInterval(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_REMOVE_INTERVAL)));
        setAliasConfirmFieldOk(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_FIELD_OK)));
        setAliasDialogSetModal(LgNamesContent.get(_util,_cust,_mapping.getVal(DIALOG_SET_MODAL)));
        setAliasTableApplyChanges(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_APPLY_CHANGES)));
        setAliasTableIsReorder(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_IS_REORDER)));
        setAliasConfirmFieldYes(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_FIELD_YES)));
        setAliasTableSetReorder(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_SET_REORDER)));
        setAliasConfirmField(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_FIELD)));
        setAliasConfirmFieldNo(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_FIELD_NO)));
        setAliasTableAddSelect(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_ADD_SELECT)));
        setAliasConfirmMessage(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_MESSAGE)));
        setAliasTableAddHeader(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_ADD_HEADER)));
        setAliasConfirmFull(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_FULL)));
        setAliasDialogIsModal(LgNamesContent.get(_util,_cust,_mapping.getVal(DIALOG_IS_MODAL)));
        setAliasWindowType(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE)));
        setAliasTableAddInterval(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_ADD_INTERVAL)));
        setAliasTableMoveColumn(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_MOVE_COLUMN)));
        setAliasConfirmFieldCancel(LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_FIELD_CANCEL)));
        setAliasTextAreaSetSelectionStart(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_SET_SELECTION_START)));
        setAliasTreeListener(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_LISTENER)));
        setAliasTreeNodeAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_ADD)));
        setAliasTreeListenerValueChanged(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_LISTENER_VALUE_CHANGED)));
        setAliasTreeNodeInsert(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_INSERT)));
        setAliasActionEvent(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT)));
        setAliasActionEventIsAlt(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_IS_ALT)));
        setAliasActionEventIsCtrl(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_IS_CTRL)));
        setAliasActionEventIsShift(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_IS_SHIFT)));
        setAliasActionEventCommand(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_COMMAND)));
        setAliasChangeListener(LgNamesContent.get(_util,_cust,_mapping.getVal(CHANGE_LISTENER)));
        setAliasActionListener(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_LISTENER)));
        setAliasAction(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION)));
        setAliasActionWrap(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_WRAP)));
        setAliasActionEnabled(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_ENABLED)));
        setAliasActionArg(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_ARG)));
        setAliasActionPerformed(LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_PERFORMED)));
        setAliasAddChange(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_CHANGE)));
        setAliasStateChanged(LgNamesContent.get(_util,_cust,_mapping.getVal(STATE_CHANGED)));
        setAliasTreeNodeGetPreviousSibling(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_GET_PREVIOUS_SIBLING)));
        setAliasComponentGetPreferredSize(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_GET_PREFERRED_SIZE)));
        setAliasSplitPaneSetDividerLocation(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_SET_DIVIDER_LOCATION)));
        setAliasSplitPaneIsOneTouchExpandable(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE)));
        setAliasSplitPaneSetOneTouchExpandable(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE)));
        setAliasSplitPaneIsContinuousLayout(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_IS_CONTINUOUS_LAYOUT)));
        setAliasScrollPaneHorizontalValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_HORIZONTAL_VALUE)));
        setAliasTreeNodeRemoveAllChildren(LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_REMOVE_ALL_CHILDREN)));
        setAliasPanelBorderBeforeLineBegins(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_BEFORE_LINE_BEGINS)));
        setAliasComponentSetPreferredSize(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_SET_PREFERRED_SIZE)));
        setAliasSplitPaneGetDividerLocation(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_GET_DIVIDER_LOCATION)));
        setAliasSplitPaneSetContinuousLayout(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_SET_CONTINUOUS_LAYOUT)));
        setAliasMouseEventIsCtrl(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_IS_CTRL)));
        setAliasMouseReleased(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_RELEASED)));
        setAliasMouseEventIsShift(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_IS_SHIFT)));
        setAliasScrollPaneGetView(LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_GET_VIEW)));
        setAliasPaintRefresh(LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_REFRESH)));
        setAliasPaintRefreshOne(LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_REFRESH_ONE)));
        setAliasMouseExited(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EXITED)));
        setAliasSplitPane(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE)));
        setAliasSplitPaneSetRight(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_SET_RIGHT)));
        setAliasSetVisible(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_VISIBLE)));
        setAliasRemoveCompo(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_COMPO)));
        setAliasMouseClicked(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_CLICKED)));
        setAliasMouseMoved(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_MOVED)));
        setAliasMouseEvent(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT)));
        setAliasMouseEventGetClicks(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_GET_CLICKS)));
        setAliasMouseEventIsAlt(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_IS_ALT)));
        setAliasMouseEventGetSecond(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_GET_SECOND)));
        setAliasScrollPane(LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE)));
        setAliasScrollPaneValidate(LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_VALIDATE)));
        setAliasSplitPaneSetDividerSize(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_SET_DIVIDER_SIZE)));
        setAliasSplitPaneGetLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_GET_LEFT)));
        setAliasSplitPaneGetDividerSize(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_GET_DIVIDER_SIZE)));
        setAliasSplitPaneValidate(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_VALIDATE)));
        setAliasMouseEntered(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_ENTERED)));
        setAliasScrollPaneSetView(LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_SET_VIEW)));
        setAliasSplitPaneSetLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_SET_LEFT)));
        setAliasMousePressed(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_PRESSED)));
        setAliasMouseListener(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER)));
        setAliasIsVisible(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_VISIBLE)));
        setAliasMouseDragged(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_DRAGGED)));
        setAliasScrollPaneVerticalValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_VERTICAL_VALUE)));
        setAliasSplitPaneGetRight(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_GET_RIGHT)));
        setAliasMouseEventGetFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_GET_FIRST)));
        setAliasGetIndexCompo(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_INDEX_COMPO)));
        setAliasImageFillPolygon(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_FILL_POLYGON)));
        setAliasComponentIsVisible(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_IS_VISIBLE)));
        setAliasImageDrawRect(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_DRAW_RECT)));
        setAliasComponentSetVisible(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_SET_VISIBLE)));
        setAliasComponentSetSize(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_SET_SIZE)));
        setAliasImageEq(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_EQ)));
        setAliasImageGetColor(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_GET_COLOR)));
        setAliasImageDrawOval(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_DRAW_OVAL)));
        setAliasComponentInvokeLater(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_INVOKE_LATER)));
        setAliasImageGetHeight(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_GET_HEIGHT)));
        setAliasImageSetFont(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_SET_FONT)));
        setAliasImageDispose(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_DISPOSE)));
        setAliasImageGetFont(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_GET_FONT)));
        setAliasColorIsTransparent(LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_IS_TRANSPARENT)));
        setAliasImageDrawLine(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_DRAW_LINE)));
        setAliasImageFillRect(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_FILL_RECT)));
        setAliasColorBlue(LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_BLUE)));
        setAliasImageDrawPolygon(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_DRAW_POLYGON)));
        setAliasComponentRepaint(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_REPAINT)));
        setAliasImageIsWithAlpha(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_IS_WITH_ALPHA)));
        setAliasImageGetWidth(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_GET_WIDTH)));
        setAliasColorGreen(LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_GREEN)));
        setAliasComponentSetPaint(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_SET_PAINT)));
        setAliasRemoveAll(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_ALL)));
        setAliasImageFillOval(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_FILL_OVAL)));
        setAliasColorAlpha(LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_ALPHA)));
        setAliasImageDraw(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_DRAW)));
        setAliasImageSetColor(LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_SET_COLOR)));
        setAliasSetContent(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_CONTENT)));
        setAliasGetNextCompo(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_NEXT_COMPO)));
        setAliasComponentGetPaint(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_GET_PAINT)));
        setAliasGetParentCompo(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PARENT_COMPO)));
        setAliasSetLabelText(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_LABEL_TEXT)));
        setAliasSetLabelImage(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_LABEL_IMAGE)));
        setAliasTabbedPaneSetTitle(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_SET_TITLE)));
        setAliasTabbedPaneAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_ADD)));
        setAliasTabbedPaneNb(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_NB)));
        setAliasPanelFlow(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_FLOW)));
        setAliasTabbedPaneRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_REMOVE)));
        setAliasAddWindowListener(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_WINDOW_LISTENER)));
        setAliasPaintMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_METHOD)));
        setAliasPanelPageBox(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_PAGE_BOX)));
        setAliasPanelAbsolute(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_ABSOLUTE)));
        setAliasPanelGrid(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_GRID)));
        setAliasTabbedPaneGet(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_GET)));
        setAliasTabbedPane(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE)));
        setAliasTabbedPaneGetTitle(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_GET_TITLE)));
        setAliasPanelValidate(LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_VALIDATE)));
        setAliasTabbedPaneIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_INDEX)));
        setAliasTabbedPaneSet(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_SET)));
        setAliasTabbedPaneSelIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_SEL_INDEX)));
        setAliasAddListener(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_LISTENER)));
        setAliasRemoveListener(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_LISTENER)));
        setAliasGetListeners(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_LISTENERS)));
        setAliasMenuItemCheck(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM_CHECK)));
        setAliasMenuAddSeparator(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ADD_SEPARATOR)));
        setAliasAbsMenuItem(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_ITEM)));
        setAliasMenuItemCheckIsSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM_CHECK_IS_SELECTED)));
        setAliasAbsMenuSetEnabled(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_SET_ENABLED)));
        setAliasMenuRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_REMOVE)));
        setAliasAbsMenuItemAddAction(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_ITEM_ADD_ACTION)));
        setAliasAbsMenuSetText(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_SET_TEXT)));
        setAliasAbsMenuGetText(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_GET_TEXT)));
        setAliasMenuItemCheckSetSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM_CHECK_SET_SELECTED)));
        setAliasAbsMenuSetDeepEnabled(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_SET_DEEP_ENABLED)));
        setAliasCompToolTip(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_TOOL_TIP)));
        setAliasWindowClosed(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_CLOSED)));
        setAliasMouseEventIsRight(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_IS_RIGHT)));
        setAliasKeyPressed(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_PRESSED)));
        setAliasMouseEventIsLeft(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_IS_LEFT)));
        setAliasWheelListener(LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_LISTENER)));
        setAliasCompBorLine(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_BOR_LINE)));
        setAliasKeyEventCode(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_CODE)));
        setAliasRequestFocus(LgNamesContent.get(_util,_cust,_mapping.getVal(REQUEST_FOCUS)));
        setAliasCompBorRaise(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_BOR_RAISE)));
        setAliasWheelEvent(LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT)));
        setAliasKeyEventIsShift(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_IS_SHIFT)));
        setAliasCompGetFirstPos(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_GET_FIRST_POS)));
        setAliasCompBorTitle(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_BOR_TITLE)));
        setAliasAddKeyListener(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_KEY_LISTENER)));
        setAliasRemoveKeyListener(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_KEY_LISTENER)));
        setAliasGetKeyListeners(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_KEY_LISTENERS)));
        setAliasKeyEventIsAlt(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_IS_ALT)));
        setAliasKeyListener(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_LISTENER)));
        setAliasKeyEventChar(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_CHAR)));
        setAliasWindowListener(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER)));
        setAliasWheelMove(LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_MOVE)));
        setAliasCompGetSecondPos(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_GET_SECOND_POS)));
        setAliasKeyEventIsCtrl(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_IS_CTRL)));
        setAliasMouseEventIsMiddle(LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_IS_MIDDLE)));
        setAliasWheelRotatedClicks(LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_ROTATED_CLICKS)));
        setAliasCompBorLower(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_BOR_LOWER)));
        setAliasWindowOpened(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_OPENED)));
        setAliasCompFocusable(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_FOCUSABLE)));
        setAliasCompOpaque(LgNamesContent.get(_util,_cust,_mapping.getVal(COMP_OPAQUE)));
        setAliasWindowIconified(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_ICONIFIED)));
        setAliasKeyReleased(LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_RELEASED)));
        setAliasAddWheelListener(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_WHEEL_LISTENER)));
        setAliasRemoveWheelListener(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_WHEEL_LISTENER)));
        setAliasGetWheelListeners(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_WHEEL_LISTENERS)));
        setAliasWindowClosing(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_CLOSING)));
        setAliasGrListSet(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_SET)));
        setAliasWindowActivated(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_ACTIVATED)));
        setAliasWindowDeactivated(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_DEACTIVATED)));
        setAliasRenderSetHeight(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_SET_HEIGHT)));
        setAliasListSelection(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_SELECTION)));
        setAliasRenderGetWidth(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_GET_WIDTH)));
        setAliasRenderGetHeight(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_GET_HEIGHT)));
        setAliasGrListGetSelectedIndexes(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_GET_SELECTED_INDEXES)));
        setAliasGrListGetSelections(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_GET_SELECTIONS)));
        setAliasRenderSetWidth(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_SET_WIDTH)));
        setAliasInputIsEnabled(LgNamesContent.get(_util,_cust,_mapping.getVal(INPUT_IS_ENABLED)));
        setAliasGrListUpdateGraphics(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_UPDATE_GRAPHICS)));
        setAliasGrListGetVisibleRowCount(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_GET_VISIBLE_ROW_COUNT)));
        setAliasGrListClearSelection(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_CLEAR_SELECTION)));
        setAliasRenderGetPaint(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_GET_PAINT)));
        setAliasWindowEvent(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_EVENT)));
        setAliasGrListClear(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_CLEAR)));
        setAliasGrListSetRender(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_SET_RENDER)));
        setAliasGrListAddSelection(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_ADD_SELECTION)));
        setAliasGrListRemoveSelection(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_REMOVE_SELECTION)));
        setAliasGrListRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_REMOVE)));
        setAliasInputSetEnabled(LgNamesContent.get(_util,_cust,_mapping.getVal(INPUT_SET_ENABLED)));
        setAliasWindowDeiconified(LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_DEICONIFIED)));
        setAliasValueChanged(LgNamesContent.get(_util,_cust,_mapping.getVal(VALUE_CHANGED)));
        setAliasGrListGetListView(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_GET_LIST_VIEW)));
        setAliasRenderSetPaint(LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_SET_PAINT)));
        setAliasGrListAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_ADD)));
        setAliasGrListGetRender(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_GET_RENDER)));
        setAliasGrListSetSelectedIndexes(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_SET_SELECTED_INDEXES)));
        setAliasGrListSetVisibleRowCount(LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_SET_VISIBLE_ROW_COUNT)));
        setAliasComboGetSelectedItem(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_GET_SELECTED_ITEM)));
        setAliasComboAddItem(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_ADD_ITEM)));
        setAliasPopupMenuGetComp(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_GET_COMP)));
        setAliasComboGetListeners(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_GET_LISTENERS)));
        setAliasComboGetSelectedIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_GET_SELECTED_INDEX)));
        setAliasPopupMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU)));
        setAliasPopupMenuAddMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_ADD_MENU)));
        setAliasPopupMenuNbMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_NB_MENU)));
        setAliasTextFieldAddPopup(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_ADD_POPUP)));
        setAliasPopupMenuNbComp(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_NB_COMP)));
        setAliasComboRemoveAllItems(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_REMOVE_ALL_ITEMS)));
        setAliasPopupMenuShow(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_SHOW)));
        setAliasPopupMenuGetMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_GET_MENU)));
//        setAliasTextFieldAuto(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_AUTO)));
        setAliasRadioGetText(LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_GET_TEXT)));
        setAliasButtonGroupAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_GROUP_ADD)));
        setAliasButtonGroup(LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_GROUP)));
        setAliasPopupMenuAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_ADD)));
        setAliasRadioIsSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_IS_SELECTED)));
        setAliasComboAddListener(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_ADD_LISTENER)));
        setAliasComboRemoveListener(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_REMOVE_LISTENER)));
        setAliasTextField(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD)));
        setAliasComboRemoveItem(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_REMOVE_ITEM)));
        setAliasRadioSetSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_SET_SELECTED)));
        setAliasTextFieldGetText(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_GET_TEXT)));
        setAliasPopupMenuRemoveMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_REMOVE_MENU)));
//        setAliasTextFieldAddDocument(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_ADD_DOCUMENT)));
        setAliasComboGetSelectedIndexes(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_GET_SELECTED_INDEXES)));
        setAliasTextFieldAddAction(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_ADD_ACTION)));
        setAliasTextAreaGetText(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_GET_TEXT)));
        setAliasRadioSetText(LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_SET_TEXT)));
        setAliasComboSelectItem(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_SELECT_ITEM)));
        setAliasComboGetItemCount(LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_GET_ITEM_COUNT)));
        setAliasPopupMenuRemoveComp(LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_REMOVE_COMP)));
        setAliasTextFieldSetText(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_SET_TEXT)));
        setAliasCheckBoxSetSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_SET_SELECTED)));
        setAliasCheckBoxAddAction(LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_ADD_ACTION)));
        setAliasTextAreaSetSelectionEnd(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_SET_SELECTION_END)));
        setAliasSpinnerGetValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_GET_VALUE)));
        setAliasSliderGetMax(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_GET_MAX)));
        setAliasTextAreaSelect(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_SELECT)));
        setAliasTextAreaReplaceRange(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_REPLACE_RANGE)));
        setAliasSpinnerSetStep(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_SET_STEP)));
        setAliasCheckBoxIsSelected(LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_IS_SELECTED)));
        setAliasTextAreaReplaceSelection(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_REPLACE_SELECTION)));
        setAliasSpinnerSetValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_SET_VALUE)));
        setAliasSliderGetValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_GET_VALUE)));
        setAliasSliderSetValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_SET_VALUE)));
        setAliasSliderGetMin(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_GET_MIN)));
        setAliasSliderSetMin(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_SET_MIN)));
        setAliasTextAreaSetTabSize(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_SET_TAB_SIZE)));
        setAliasSpinnerGetMin(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_GET_MIN)));
        setAliasCheckBoxSetText(LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_SET_TEXT)));
        setAliasCheckBoxGetText(LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_GET_TEXT)));
        setAliasTextAreaInsert(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_INSERT)));
        setAliasTextAreaSelectAll(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_SELECT_ALL)));
        setAliasTextAreaAppend(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_APPEND)));
        setAliasSpinnerGetMax(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_GET_MAX)));
        setAliasSpinnerSetMax(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_SET_MAX)));
        setAliasTextAreaGetTabSize(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_GET_TAB_SIZE)));
        setAliasTextAreaSetText(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_SET_TEXT)));
        setAliasSpinnerSetMin(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_SET_MIN)));
        setAliasSpinnerGetStep(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_GET_STEP)));
        setAliasSpinnerSetRangeValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_SET_RANGE_VALUE)));
        setAliasTextAreaGetSelectedText(LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_GET_SELECTED_TEXT)));
        setAliasSliderSetMax(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_SET_MAX)));
        setAliasSpinnerSetRange(LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_SET_RANGE)));
        setAliasMenuBarGet(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_GET)));
        setAliasMenuBarRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_REMOVE)));
        setAliasAbsMenuGetParent(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_GET_PARENT)));
        setAliasAbsMenuIsEnabled(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_IS_ENABLED)));
        setAliasMenuBarAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_ADD)));
        setAliasGetMenuBar(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_MENU_BAR)));
        setAliasSliderGetOrientation(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_GET_ORIENTATION)));
        setAliasMenuBarNb(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_NB)));
        setAliasSetMenuBar(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_MENU_BAR)));
        setAliasSliderSetOrientation(LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_SET_ORIENTATION)));
        setAliasMenuBar(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR)));
        setAliasMenuNb(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_NB)));
        setAliasMenuAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ADD)));
        setAliasMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU)));
        setAliasMenuItem(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM)));
        setAliasAbsMenu(LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU)));
        setAliasMenuGet(LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_GET)));
        setAliasCommand(LgNamesContent.get(_util,_cust,_mapping.getVal(COMMAND)));
        setAliasCommandBinding(LgNamesContent.get(_util,_cust,_mapping.getVal(COMMAND_BINDING)));
        setAliasCommandAction(LgNamesContent.get(_util,_cust,_mapping.getVal(COMMAND_ACTION)));
        setAliasComponentBind(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_BIND)));
        setAliasComponentUnbind(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_UNBIND)));
        setAliasComponentCommands(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_COMMANDS)));
        guiAliasParameters.build(_util, _cust,_mapping);
    }
    public static TranslationsFile en() {
        TranslationsFile en_ = new TranslationsFile();
        en(en_);
        return en_;
    }
    public static void en(TranslationsFile _en){
        _en.add(WINDOW_SET,"WindowSet=$core.WindowSet");
        _en.add(FRAME,"Frame=$core.Frame");
        _en.add(CONFIRM,"Confirm=$core.Confirm");
        _en.add(DIALOG,"Dialog=$core.Dialog");
        _en.add(WINDOW_TYPE,"WindowType=$core.Window");
        _en.add(COMPONENT,"Component=$core.Component");
        _en.add(ACTION_EVENT,"ActionEvent=$core.ActionEvent");
        _en.add(MOUSE_EVENT,"MouseEvent=$core.MouseEvent");
        _en.add(TABLE_LISTENER,"TableListener=$core.TableListener");
        _en.add(TABLE_GUI,"TableGui=$core.GridTable");
        _en.add(TREE_LISTENER,"TreeListener=$core.TreeListener");
        _en.add(TREE,"Tree=$core.Tree");
        _en.add(TREE_NODE,"TreeNode=$core.TreeNode");
        _en.add(KEY_EVENT,"KeyEvent=$core.KeyEvent");
        _en.add(WINDOW_EVENT,"WindowEvent=$core.WindowEvent");
        _en.add(PANEL,"Panel=$core.Panel");
        _en.add(TABBED_PANE,"TabbedPane=$core.TabbedPane");
        _en.add(PANEL_BORDER,"PanelBorder=$core.PanelBorder");
        _en.add(BUTTON,"Button=$core.Button");
        _en.add(PROG_BAR,"ProgBar=$core.ProgBar");
        _en.add(CHECK_BOX,"CheckBox=$core.CheckBox");
        _en.add(RADIO,"Radio=$core.Radio");
        _en.add(TEXT_LABEL,"TextLabel=$core.TextLabel");
        _en.add(IMAGE,"Image=$core.Image");
        _en.add(IMAGE_LABEL,"ImageLabel=$core.ImageLabel");
        _en.add(COLOR,"Color=$core.Color");
        _en.add(INPUT,"Input=$core.Input");
        _en.add(FONT,"Font=$core.Font");
        _en.add(TEXT_AREA,"TextArea=$core.TextArea");
        _en.add(TEXT_FIELD,"TextField=$core.TextField");
        _en.add(GR_LIST,"GrList=$core.GrList");
        _en.add(COMBO,"Combo=$core.ComboBox");
        _en.add(BUTTON_GROUP,"ButtonGroup=$core.ButtonGroup");
        _en.add(RENDER,"Render=$core.Render");
        _en.add(POPUP_MENU,"PopupMenu=$core.Popup");
        _en.add(DIMENSION,"Dimension=$core.Dimension");
        _en.add(KEY_LISTENER,"KeyListener=$core.KeyListener");
        _en.add(MOUSE_LISTENER,"MouseListener=$core.MouseListener");
        _en.add(WHEEL_LISTENER,"WheelListener=$core.MouseWheelListener");
        _en.add(WHEEL_EVENT,"WheelEvent=$core.MouseWheelEvent");
        _en.add(ACTION_LISTENER,"ActionListener=$core.ActionListener");
        _en.add(ACTION,"Action=$core.Action");
        _en.add(CHANGE_LISTENER,"ChangeListener=$core.ChangeListener");
        _en.add(WINDOW_LISTENER,"WindowListener=$core.WindowListener");
        _en.add(SCROLL_PANE,"ScrollPane=$core.Scroll");
        _en.add(SPLIT_PANE,"SplitPane=$core.SplitPane");
        _en.add(LIST_SELECTION,"ListSelection=$core.ListSelection");
        _en.add(PAINT,"Paint=$core.Painting");
        _en.add(MENU_BAR,"MenuBar=$core.MenuBar");
        _en.add(ABS_MENU,"AbsMenu=$core.AbsMenu");
        _en.add(MENU,"Menu=$core.Menu");
        _en.add(ABS_MENU_ITEM,"AbsMenuItem=$core.AbsMenuItem");
        _en.add(MENU_ITEM,"MenuItem=$core.MenuItem");
        _en.add(MENU_ITEM_CHECK,"MenuItemCheck=$core.MenuItemCheck");
        _en.add(SPINNER,"Spinner=$core.Spinner");
        _en.add(SLIDER,"Slider=$core.Slider");
        _en.add(COMMAND,"Command=$core.Command");
        _en.add(CONFIRM_FIELD,"ConfirmField=field");
        _en.add(CONFIRM_FULL,"ConfirmFull=yesNoCancel");
        _en.add(CONFIRM_MESSAGE,"ConfirmMessage=message");
        _en.add(CONFIRM_OK,"ConfirmOk=ok");
        _en.add(CONFIRM_YES_NO,"ConfirmYesNo=yesNo");
        _en.add(PACK,"Pack=pack");
        _en.add(ADD_WINDOW_LISTENER,"AddWindowListener=addWindowListener");
        _en.add(REMOVE_WINDOW_LISTENER,"RemoveWindowListener=removeWindowListener");
        _en.add(GET_WINDOW_LISTENERS,"GetWindowListeners=getWindowListeners");
        _en.add(DISPOSE,"Dispose=dispose");
        _en.add(WINDOW_TYPE_RELATIVE,"WindowTypeRelative=rel");
        _en.add(IS_VISIBLE,"IsVisible=isVisible");
        _en.add(SET_VISIBLE,"SetVisible=setVisible");
        _en.add(SET_CONTENT,"SetContent=setContent");
        _en.add(GET_MENU_BAR,"GetMenuBar=getMenuBar");
        _en.add(SET_MENU_BAR,"SetMenuBar=setMenuBar");
        _en.add(WINDOW,"Window=window");
        _en.add(WINDOW_SET_ADD,"WindowSetAdd=add");
        _en.add(WINDOW_SET_ALL,"WindowSetAll=all");
        _en.add(WINDOW_SET_CONTAINS,"WindowSetContains=contains");
        _en.add(WINDOW_SET_REMOVE,"WindowSetRemove=remove");
        _en.add(WINDOW_SET_SNAPSHOT,"WindowSetSnapshot=snapshot");
        _en.add(DIALOG_IS_MODAL,"DialogIsModal=isModal");
        _en.add(DIALOG_SET_MODAL,"DialogSetModal=setModal");
        _en.add(CLOSE_ALL,"CloseAll=closeAll");
        _en.add(ACTION_EVENT_IS_ALT,"ActionEventIsAlt=isAlt");
        _en.add(ACTION_EVENT_IS_SHIFT,"ActionEventIsShift=isShift");
        _en.add(ACTION_EVENT_IS_CTRL,"ActionEventIsCtrl=isCtrl");
        _en.add(ACTION_EVENT_COMMAND,"ActionEventCommand=command");
        _en.add(MOUSE_EVENT_GET_FIRST,"MouseEventGetFirst=x");
        _en.add(MOUSE_EVENT_GET_SECOND,"MouseEventGetSecond=y");
        _en.add(MOUSE_EVENT_GET_CLICKS,"MouseEventGetClicks=getClicks");
        _en.add(MOUSE_EVENT_IS_ALT,"MouseEventIsAlt=isAlt");
        _en.add(MOUSE_EVENT_IS_SHIFT,"MouseEventIsShift=isShift");
        _en.add(MOUSE_EVENT_IS_CTRL,"MouseEventIsCtrl=isCtrl");
        _en.add(MOUSE_EVENT_IS_LEFT,"MouseEventIsLeft=isLeft");
        _en.add(MOUSE_EVENT_IS_RIGHT,"MouseEventIsRight=isRight");
        _en.add(MOUSE_EVENT_IS_MIDDLE,"MouseEventIsMiddle=isMiddle");
        _en.add(WHEEL_ROTATED_CLICKS,"WheelRotatedClicks=rotatedClicks");
        _en.add(KEY_EVENT_CODE,"KeyEventCode=keyCode");
        _en.add(KEY_EVENT_CHAR,"KeyEventChar=keyChar");
        _en.add(KEY_EVENT_IS_ALT,"KeyEventIsAlt=isAlt");
        _en.add(KEY_EVENT_IS_SHIFT,"KeyEventIsShift=isShift");
        _en.add(KEY_EVENT_IS_CTRL,"KeyEventIsCtrl=isCtrl");
        _en.add(COUNT,"Count=count");
        _en.add(GET_INDEX_COMPO,"GetIndexCompo=get");
        _en.add(ADD_COMPO,"AddCompo=add");
        _en.add(REMOVE_COMPO,"RemoveCompo=remove");
        _en.add(PANEL_ABSOLUTE,"PanelAbsolute=absolute");
        _en.add(PANEL_FLOW,"PanelFlow=horizon");
        _en.add(PANEL_PAGE_BOX,"PanelPageBox=page");
        _en.add(PANEL_GRID,"PanelGrid=grid");
        _en.add(PANEL_VALIDATE,"PanelValidate=validate");
        _en.add(REMOVE_ALL,"RemoveAll=removeAll");
        _en.add(GET_PARENT_COMPO,"GetParentCompo=getParent");
        _en.add(GET_NEXT_COMPO,"GetNextCompo=next");
        _en.add(COMPONENT_REPAINT,"ComponentRepaint=repaint");
        _en.add(COMPONENT_GET_PAINT,"ComponentGetPaint=getPaint");
        _en.add(COMPONENT_SET_PAINT,"ComponentSetPaint=setPaint");
        _en.add(GET_FONT,"GetFont=getFont");
        _en.add(SET_FONT,"SetFont=setFont");
        _en.add(COMPONENT_GET_HEIGHT,"ComponentGetHeight=getHeight");
        _en.add(COMPONENT_GET_WIDTH,"ComponentGetWidth=getWidth");
        _en.add(COMPONENT_IS_AUTOSCROLLS,"ComponentIsAutoscrolls=isAutoscrolls");
        _en.add(COMPONENT_SET_AUTOSCROLLS,"ComponentSetAutoscrolls=setAutoscrolls");
        _en.add(COMPONENT_GET_PREFERRED_SIZE,"ComponentGetPreferredSize=getPreferredSize");
        _en.add(COMPONENT_SET_PREFERRED_SIZE,"ComponentSetPreferredSize=setPreferredSize");
        _en.add(COMPONENT_SET_SIZE,"ComponentSetSize=setSize");
        _en.add(COMPONENT_IS_VISIBLE,"ComponentIsVisible=isVisible");
        _en.add(COMPONENT_SET_VISIBLE,"ComponentSetVisible=setVisible");
        _en.add(COMPONENT_INVOKE_LATER,"ComponentInvokeLater=invokeLater");
        _en.add(ADD_KEY_LISTENER,"AddKeyListener=addKey");
        _en.add(REMOVE_KEY_LISTENER,"RemoveKeyListener=removeKey");
        _en.add(GET_KEY_LISTENERS,"GetKeyListeners=getKeys");
        _en.add(ADD_WHEEL_LISTENER,"AddWheelListener=addWheel");
        _en.add(REMOVE_WHEEL_LISTENER,"RemoveWheelListener=removeWheel");
        _en.add(GET_WHEEL_LISTENERS,"GetWheelListeners=getWheels");
        _en.add(ADD_LISTENER,"AddListener=addList");
        _en.add(REMOVE_LISTENER,"RemoveListener=removeList");
        _en.add(GET_LISTENERS,"GetListeners=getLists");
        _en.add(REQUEST_FOCUS,"RequestFocus=requestFocus");
        _en.add(COMP_BACK,"CompBack=back");
        _en.add(COMPO_REL_LEFT,"CompoRelLeft=left");
        _en.add(COMPO_REL_RIGHT,"CompoRelRight=right");
        _en.add(COMPO_REL_TOP,"CompoRelTop=top");
        _en.add(COMPO_REL_BOTTOM,"CompoRelBottom=bottom");
        _en.add(COMPO_REL_CENT_HORIZ,"CompoRelCentHoriz=centHoriz");
        _en.add(COMPO_REL_CENT_VERT,"CompoRelCentVert=centVert");
        _en.add(COMP_FOCUSABLE,"CompFocusable=focus");
        _en.add(COMP_FORE,"CompFore=fore");
        _en.add(COMP_GET_FIRST_POS,"CompGetFirstPos=x");
        _en.add(COMP_GET_SECOND_POS,"CompGetSecondPos=y");
        _en.add(COMP_OPAQUE,"CompOpaque=opaque");
        _en.add(COMP_TOOL_TIP,"CompToolTip=tooltip");
        _en.add(COMP_LOC,"CompLoc=loc");
        _en.add(COMP_BOR_LINE,"CompBorLine=line");
        _en.add(COMP_BOR_LOWER,"CompBorLower=lower");
        _en.add(COMP_BOR_RAISE,"CompBorRaise=raise");
        _en.add(COMP_BOR_TITLE,"CompBorTitle=title");
        _en.add(COMPONENT_BIND,"ComponentBind=bind");
        _en.add(COMPONENT_UNBIND,"ComponentUnbind=unbind");
        _en.add(COMPONENT_COMMANDS,"ComponentCommands=commands");
        _en.add(TABBED_PANE_NB,"TabbedPaneNb=nb");
        _en.add(TABBED_PANE_ADD,"TabbedPaneAdd=add");
        _en.add(TABBED_PANE_REMOVE,"TabbedPaneRemove=remove");
        _en.add(TABBED_PANE_SEL_INDEX,"TabbedPaneSelIndex=selIndex");
        _en.add(TABBED_PANE_INDEX,"TabbedPaneIndex=index");
        _en.add(TABBED_PANE_GET,"TabbedPaneGet=get");
        _en.add(TABBED_PANE_GET_TITLE,"TabbedPaneGetTitle=getTitle");
        _en.add(TABBED_PANE_SET,"TabbedPaneSet=set");
        _en.add(TABBED_PANE_SET_TITLE,"TabbedPaneSetTitle=setTitle");
        _en.add(SET_LABEL_TEXT,"SetLabelText=setText");
        _en.add(SET_LABEL_IMAGE,"SetLabelImage=setImage");
        _en.add(PROG_BAR_OR,"ProgBarOr=horizontal");
        _en.add(PROG_BAR_VALUE,"ProgBarValue=val");
        _en.add(PROG_BAR_MIN,"ProgBarMin=min");
        _en.add(PROG_BAR_MAX,"ProgBarMax=max");
        _en.add(SCROLL_PANE_HORIZONTAL_VALUE,"ScrollPaneHorizontalValue=hValue");
        _en.add(SCROLL_PANE_VERTICAL_VALUE,"ScrollPaneVerticalValue=vValue");
        _en.add(SCROLL_PANE_GET_VIEW,"ScrollPaneGetView=getView");
        _en.add(SCROLL_PANE_SET_VIEW,"ScrollPaneSetView=setView");
        _en.add(SCROLL_PANE_VALIDATE,"ScrollPaneValidate=validate");
        _en.add(SPLIT_PANE_GET_DIVIDER_LOCATION,"SplitPaneGetDividerLocation=getDividerLocation");
        _en.add(SPLIT_PANE_SET_DIVIDER_LOCATION,"SplitPaneSetDividerLocation=setDividerLocation");
        _en.add(SPLIT_PANE_GET_DIVIDER_SIZE,"SplitPaneGetDividerSize=getDividerSize");
        _en.add(SPLIT_PANE_SET_DIVIDER_SIZE,"SplitPaneSetDividerSize=setDividerSize");
        _en.add(SPLIT_PANE_GET_LEFT,"SplitPaneGetLeft=getLeft");
        _en.add(SPLIT_PANE_SET_LEFT,"SplitPaneSetLeft=setLeft");
        _en.add(SPLIT_PANE_GET_RIGHT,"SplitPaneGetRight=getRight");
        _en.add(SPLIT_PANE_SET_RIGHT,"SplitPaneSetRight=setRight");
        _en.add(SPLIT_PANE_IS_CONTINUOUS_LAYOUT,"SplitPaneIsContinuousLayout=isContinuousLayout");
        _en.add(SPLIT_PANE_SET_CONTINUOUS_LAYOUT,"SplitPaneSetContinuousLayout=setContinuousLayout");
        _en.add(SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE,"SplitPaneIsOneTouchExpandable=isOneTouchExpandable");
        _en.add(SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE,"SplitPaneSetOneTouchExpandable=setOneTouchExpandable");
        _en.add(SPLIT_PANE_VALIDATE,"SplitPaneValidate=validate");
        _en.add(INPUT_IS_ENABLED,"InputIsEnabled=isEnabled");
        _en.add(INPUT_SET_ENABLED,"InputSetEnabled=setEnabled");
        _en.add(CHECK_BOX_ADD_ACTION,"CheckBoxAddAction=addAction");
        _en.add(CHECK_BOX_GET_TEXT,"CheckBoxGetText=getText");
        _en.add(CHECK_BOX_SET_TEXT,"CheckBoxSetText=setText");
        _en.add(CHECK_BOX_IS_SELECTED,"CheckBoxIsSelected=isSelected");
        _en.add(CHECK_BOX_SET_SELECTED,"CheckBoxSetSelected=setSelected");
        _en.add(SPINNER_GET_MAX,"SpinnerGetMax=getMax");
        _en.add(SPINNER_GET_MIN,"SpinnerGetMin=getMin");
        _en.add(SPINNER_GET_STEP,"SpinnerGetStep=getStep");
        _en.add(SPINNER_GET_VALUE,"SpinnerGetValue=getValue");
        _en.add(SPINNER_SET_MAX,"SpinnerSetMax=setMax");
        _en.add(SPINNER_SET_MIN,"SpinnerSetMin=setMin");
        _en.add(SPINNER_SET_STEP,"SpinnerSetStep=setStep");
        _en.add(SPINNER_SET_VALUE,"SpinnerSetValue=setValue");
        _en.add(SPINNER_SET_RANGE,"SpinnerSetRange=setRange");
        _en.add(SPINNER_SET_RANGE_VALUE,"SpinnerSetRangeValue=setRangeValue");
        _en.add(ADD_CHANGE,"AddChange=addChange");
        _en.add(SLIDER_GET_MAX,"SliderGetMax=getMax");
        _en.add(SLIDER_GET_MIN,"SliderGetMin=getMin");
        _en.add(SLIDER_GET_ORIENTATION,"SliderGetOrientation=getOrient");
        _en.add(SLIDER_GET_VALUE,"SliderGetValue=getValue");
        _en.add(SLIDER_SET_MAX,"SliderSetMax=setMax");
        _en.add(SLIDER_SET_MIN,"SliderSetMin=setMin");
        _en.add(SLIDER_SET_ORIENTATION,"SliderSetOrientation=setOrient");
        _en.add(SLIDER_SET_VALUE,"SliderSetValue=setValue");
        _en.add(RADIO_GET_TEXT,"RadioGetText=getText");
        _en.add(RADIO_SET_TEXT,"RadioSetText=setText");
        _en.add(RADIO_IS_SELECTED,"RadioIsSelected=isSelected");
        _en.add(RADIO_SET_SELECTED,"RadioSetSelected=setSelected");
        _en.add(TEXT_FIELD_ADD_ACTION,"TextFieldAddAction=addAction");
        _en.add(TEXT_FIELD_ADD_POPUP,"TextFieldAddPopup=add");
        _en.add(TEXT_FIELD_GET_TEXT,"TextFieldGetText=getText");
        _en.add(TEXT_FIELD_SET_TEXT,"TextFieldSetText=setText");
        _en.add(TEXT_AREA_APPEND,"TextAreaAppend=append");
        _en.add(TEXT_AREA_INSERT,"TextAreaInsert=insert");
        _en.add(TEXT_AREA_REPLACE_RANGE,"TextAreaReplaceRange=replaceRange");
        _en.add(TEXT_AREA_REPLACE_SELECTION,"TextAreaReplaceSelection=replaceSelection");
        _en.add(TEXT_AREA_GET_SELECTED_TEXT,"TextAreaGetSelectedText=getSelectedText");
        _en.add(TEXT_AREA_SET_SELECTION_START,"TextAreaSetSelectionStart=setSelectionStart");
        _en.add(TEXT_AREA_SET_SELECTION_END,"TextAreaSetSelectionEnd=setSelectionEnd");
        _en.add(TEXT_AREA_GET_TAB_SIZE,"TextAreaGetTabSize=getTabSize");
        _en.add(TEXT_AREA_SET_TAB_SIZE,"TextAreaSetTabSize=setTabSize");
        _en.add(TEXT_AREA_GET_TEXT,"TextAreaGetText=getText");
        _en.add(TEXT_AREA_SET_TEXT,"TextAreaSetText=setText");
        _en.add(TEXT_AREA_SELECT,"TextAreaSelect=select");
        _en.add(TEXT_AREA_SELECT_ALL,"TextAreaSelectAll=selectAll");
        _en.add(COMBO_ADD_ITEM,"ComboAddItem=addItem");
        _en.add(COMBO_GET_ITEM_COUNT,"ComboGetItemCount=getItemCount");
        _en.add(COMBO_GET_LISTENERS,"ComboGetListeners=getListeners");
        _en.add(COMBO_GET_SELECTED_INDEX,"ComboGetSelectedIndex=getSelectedIndex");
        _en.add(COMBO_GET_SELECTED_INDEXES,"ComboGetSelectedIndexes=getSelectedIndexes");
        _en.add(COMBO_GET_SELECTED_ITEM,"ComboGetSelectedItem=getSelectedItem");
        _en.add(COMBO_REMOVE_ALL_ITEMS,"ComboRemoveAllItems=removeAllItems");
        _en.add(COMBO_REMOVE_ITEM,"ComboRemoveItem=removeItem");
        _en.add(COMBO_SELECT_ITEM,"ComboSelectItem=selectItem");
        _en.add(COMBO_ADD_LISTENER,"ComboAddListener=addListener");
        _en.add(COMBO_REMOVE_LISTENER,"ComboRemoveListener=removeListener");
        _en.add(GR_LIST_ADD,"GrListAdd=add");
        _en.add(GR_LIST_CLEAR,"GrListClear=clear");
        _en.add(GR_LIST_CLEAR_SELECTION,"GrListClearSelection=clearSelection");
        _en.add(GR_LIST_GET_LIST_VIEW,"GrListGetListView=getView");
        _en.add(GR_LIST_GET_RENDER,"GrListGetRender=getRender");
        _en.add(GR_LIST_GET_SELECTED_INDEXES,"GrListGetSelectedIndexes=getSelectedIndexes");
        _en.add(GR_LIST_GET_SELECTIONS,"GrListGetSelections=getSelections");
        _en.add(GR_LIST_GET_VISIBLE_ROW_COUNT,"GrListGetVisibleRowCount=getVisibleRowCount");
        _en.add(GR_LIST_REMOVE,"GrListRemove=remove");
        _en.add(GR_LIST_SET,"GrListSet=set");
        _en.add(GR_LIST_SET_RENDER,"GrListSetRender=setRender");
        _en.add(GR_LIST_SET_SELECTED_INDEXES,"GrListSetSelectedIndexes=setSelectedIndexes");
        _en.add(GR_LIST_ADD_SELECTION,"GrListAddSelection=addSelection");
        _en.add(GR_LIST_REMOVE_SELECTION,"GrListRemoveSelection=removeSelection");
        _en.add(GR_LIST_SET_VISIBLE_ROW_COUNT,"GrListSetVisibleRowCount=setVisibleRowCount");
        _en.add(GR_LIST_UPDATE_GRAPHICS,"GrListUpdateGraphics=updateGraphics");
        _en.add(POPUP_MENU_ADD,"PopupMenuAdd=add");
        _en.add(POPUP_MENU_ADD_MENU,"PopupMenuAddMenu=addMenu");
        _en.add(POPUP_MENU_GET_COMP,"PopupMenuGetComp=comp");
        _en.add(POPUP_MENU_REMOVE_COMP,"PopupMenuRemoveComp=removeComp");
        _en.add(POPUP_MENU_NB_COMP,"PopupMenuNbComp=nbComps");
        _en.add(POPUP_MENU_GET_MENU,"PopupMenuGetMenu=menu");
        _en.add(POPUP_MENU_REMOVE_MENU,"PopupMenuRemoveMenu=removeMenu");
        _en.add(POPUP_MENU_NB_MENU,"PopupMenuNbMenu=nbMenus");
        _en.add(POPUP_MENU_SHOW,"PopupMenuShow=show");
        _en.add(TREE_ADD_TREE_LISTENER,"TreeAddTreeListener=addTreeListener");
        _en.add(TREE_GET_SELECTED,"TreeGetSelected=selected");
        _en.add(TREE_IS_ROOT_VISIBLE,"TreeIsRootVisible=isRootVisible");
        _en.add(TREE_SET_ROOT_VISIBLE,"TreeSetRootVisible=setRootVisible");
        _en.add(TREE_RELOAD,"TreeReload=reload");
        _en.add(TABLE_ADD_HEADER,"TableAddHeader=addHeader");
        _en.add(TABLE_ADD_SELECT,"TableAddSelect=addSelect");
        _en.add(TABLE_APPLY_CHANGES,"TableApplyChanges=apply");
        _en.add(TABLE_ADD_INTERVAL,"TableAddInterval=addInterval");
        _en.add(TABLE_REMOVE_INTERVAL,"TableRemoveInterval=removeInterval");
        _en.add(TABLE_MOVE_COLUMN,"TableMoveColumn=move");
        _en.add(TABLE_GET_COLUMN_AT_POINT,"TableGetColumnAtPoint=columnAtPoint");
        _en.add(TABLE_GET_COLUMN_COUNT,"TableGetColumnCount=colCount");
        _en.add(TABLE_GET_COLUMN_NAME,"TableGetColumnName=colName");
        _en.add(TABLE_GET_ROW_AT_POINT,"TableGetRowAtPoint=rowAtPoint");
        _en.add(TABLE_GET_ROW_COUNT,"TableGetRowCount=getRowCount");
        _en.add(TABLE_GET_SELECTED_ROW,"TableGetSelectedRow=selectedRow");
        _en.add(TABLE_GET_SELECTED_ROW_COUNT,"TableGetSelectedRowCount=selectedRowCount");
        _en.add(TABLE_GET_SELECTED_ROWS,"TableGetSelectedRows=selectedRows");
        _en.add(TABLE_GET_VALUE,"TableGetValue=get");
        _en.add(TABLE_SET_COLUMNS,"TableSetColumns=setColumns");
        _en.add(TABLE_SET_MULTIPLE,"TableSetMultiple=setMultiple");
        _en.add(TABLE_SET_REORDER,"TableSetReorder=setReorder");
        _en.add(TABLE_SET_ROW_COUNT,"TableSetRowCount=setRowCount");
        _en.add(TABLE_SET_VALUE,"TableSetValue=set");
        _en.add(TABLE_IS_MULTIPLE,"TableIsMultiple=isMultiple");
        _en.add(TABLE_IS_REORDER,"TableIsReorder=isReorder");
        _en.add(DIMENSION_GET_HEIGHT,"DimensionGetHeight=getHeight");
        _en.add(DIMENSION_GET_WIDTH,"DimensionGetWidth=getWidth");
        _en.add(FONT_GET_NAME,"FontGetName=getName");
        _en.add(FONT_GET_SIZE,"FontGetSize=getSize");
        _en.add(FONT_IS_BOLD,"FontIsBold=isBold");
        _en.add(FONT_IS_ITALIC,"FontIsItalic=isItalic");
        _en.add(FONT_STRING_WIDTH,"FontStringWidth=stringWidth");
        _en.add(BUTTON_GROUP_ADD,"ButtonGroupAdd=add");
        _en.add(RENDER_GET_HEIGHT,"RenderGetHeight=getHeight");
        _en.add(RENDER_GET_PAINT,"RenderGetPaint=getPaint");
        _en.add(RENDER_GET_WIDTH,"RenderGetWidth=getWidth");
        _en.add(RENDER_SET_HEIGHT,"RenderSetHeight=setHeight");
        _en.add(RENDER_SET_PAINT,"RenderSetPaint=setPaint");
        _en.add(RENDER_SET_WIDTH,"RenderSetWidth=setWidth");
        _en.add(COLOR_ALPHA,"ColorAlpha=a");
        _en.add(COLOR_BLUE,"ColorBlue=b");
        _en.add(COLOR_RED,"ColorRed=r");
        _en.add(COLOR_GREEN,"ColorGreen=g");
        _en.add(COLOR_IS_TRANSPARENT,"ColorIsTransparent=transparent");
        _en.add(IMAGE_DRAW,"ImageDraw=draw");
        _en.add(IMAGE_DRAW_LINE,"ImageDrawLine=drawLine");
        _en.add(IMAGE_DRAW_OVAL,"ImageDrawOval=drawOval");
        _en.add(IMAGE_DRAW_POLYGON,"ImageDrawPolygon=drawPolygon");
        _en.add(IMAGE_DRAW_RECT,"ImageDrawRect=drawRect");
        _en.add(IMAGE_FILL_OVAL,"ImageFillOval=fillOval");
        _en.add(IMAGE_FILL_POLYGON,"ImageFillPolygon=fillPolygon");
        _en.add(IMAGE_FILL_RECT,"ImageFillRect=fillRect");
        _en.add(IMAGE_GET,"ImageGet=get");
        _en.add(IMAGE_SET,"ImageSet=set");
        _en.add(IMAGE_EQ,"ImageEq=e");
        _en.add(IMAGE_GET_COLOR,"ImageGetColor=getColor");
        _en.add(IMAGE_SET_COLOR,"ImageSetColor=setColor");
        _en.add(IMAGE_GET_FONT,"ImageGetFont=getFont");
        _en.add(IMAGE_SET_FONT,"ImageSetFont=setFont");
        _en.add(IMAGE_GET_HEIGHT,"ImageGetHeight=h");
        _en.add(IMAGE_GET_WIDTH,"ImageGetWidth=w");
        _en.add(IMAGE_IS_WITH_ALPHA,"ImageIsWithAlpha=alpha");
        _en.add(IMAGE_DISPOSE,"ImageDispose=dispose");
        _en.add(ACTION_PERFORMED,"ActionPerformed=actionPerformed");
        _en.add(ACTION_WRAP,"ActionWrap=wrap");
        _en.add(ACTION_ENABLED,"ActionEnabled=enabled");
        _en.add(ACTION_ARG,"ActionArg=action");
        _en.add(STATE_CHANGED,"StateChanged=stateChanged");
        _en.add(WHEEL_MOVE,"WheelMove=mouseWheelMoved");
        _en.add(MOUSE_CLICKED,"MouseClicked=mouseClicked");
        _en.add(MOUSE_ENTERED,"MouseEntered=mouseEntered");
        _en.add(MOUSE_EXITED,"MouseExited=mouseExited");
        _en.add(MOUSE_PRESSED,"MousePressed=mousePressed");
        _en.add(MOUSE_DRAGGED,"MouseDragged=mouseDragged");
        _en.add(MOUSE_MOVED,"MouseMoved=mouseMoved");
        _en.add(MOUSE_RELEASED,"MouseReleased=mouseReleased");
        _en.add(KEY_TYPED,"KeyTyped=keyTyped");
        _en.add(KEY_RELEASED,"KeyReleased=keyReleased");
        _en.add(KEY_PRESSED,"KeyPressed=keyPressed");
        _en.add(WINDOW_ACTIVATED,"WindowActivated=activated");
        _en.add(WINDOW_ICONIFIED,"WindowIconified=iconified");
        _en.add(WINDOW_DEACTIVATED,"WindowDeactivated=deactivated");
        _en.add(WINDOW_DEICONIFIED,"WindowDeiconified=deiconified");
        _en.add(WINDOW_OPENED,"WindowOpened=opened");
        _en.add(WINDOW_CLOSING,"WindowClosing=closing");
        _en.add(WINDOW_CLOSED,"WindowClosed=closed");
        _en.add(VALUE_CHANGED,"ValueChanged=valueChanged");
        _en.add(TREE_LISTENER_VALUE_CHANGED,"TreeListenerValueChanged=valueTreeChanged");
        _en.add(TREE_NODE_ADD,"TreeNodeAdd=add");
        _en.add(TREE_NODE_INSERT,"TreeNodeInsert=insert");
        _en.add(TREE_NODE_REMOVE,"TreeNodeRemove=remove");
        _en.add(TREE_NODE_REMOVE_FROM_PARENT,"TreeNodeRemoveFromParent=removeFromParent");
        _en.add(TREE_NODE_REMOVE_ALL_CHILDREN,"TreeNodeRemoveAllChildren=removeAllChildren");
        _en.add(TREE_NODE_SET_USER_OBJECT,"TreeNodeSetUserObject=set");
        _en.add(TREE_NODE_GET_USER_OBJECT,"TreeNodeGetUserObject=get");
        _en.add(TREE_NODE_EQ,"TreeNodeEq=eq");
        _en.add(TREE_NODE_NB,"TreeNodeNb=nb");
        _en.add(TREE_NODE_GET_FIRST_CHILD,"TreeNodeGetFirstChild=first");
        _en.add(TREE_NODE_GET_LAST_CHILD,"TreeNodeGetLastChild=last");
        _en.add(TREE_NODE_GET_NEXT_SIBLING,"TreeNodeGetNextSibling=next");
        _en.add(TREE_NODE_GET_PREVIOUS_SIBLING,"TreeNodeGetPreviousSibling=previous");
        _en.add(TREE_NODE_GET_PARENT_NODE,"TreeNodeGetParentNode=parent");
        _en.add(TREE_NODE_IS_ANCESTOR,"TreeNodeIsAncestor=isAncestor");
        _en.add(TREE_NODE_IS_DESCENDANT,"TreeNodeIsDescendant=isDescendant");
        _en.add(TABLE_VALUE_TABLE_CHANGED,"TableValueTableChanged=valueTableChanged");
        _en.add(PAINT_METHOD,"PaintMethod=paint");
        _en.add(PAINT_ADD,"PaintAdd=add");
        _en.add(PAINT_SET,"PaintSet=set");
        _en.add(PAINT_REFRESH,"PaintRefresh=refresh");
        _en.add(PAINT_REFRESH_ONE,"PaintRefreshOne=refreshOne");
        _en.add(MENU_BAR_ADD,"MenuBarAdd=add");
        _en.add(MENU_BAR_GET,"MenuBarGet=get");
        _en.add(MENU_BAR_REMOVE,"MenuBarRemove=remove");
        _en.add(MENU_BAR_NB,"MenuBarNb=nb");
        _en.add(ABS_MENU_GET_PARENT,"AbsMenuGetParent=getParent");
        _en.add(ABS_MENU_GET_TEXT,"AbsMenuGetText=getText");
        _en.add(ABS_MENU_IS_ENABLED,"AbsMenuIsEnabled=isEnabled");
        _en.add(ABS_MENU_SET_DEEP_ENABLED,"AbsMenuSetDeepEnabled=setDeepEnabled");
        _en.add(ABS_MENU_SET_ENABLED,"AbsMenuSetEnabled=setEnabled");
        _en.add(ABS_MENU_SET_TEXT,"AbsMenuSetText=setText");
        _en.add(MENU_ADD,"MenuAdd=add");
        _en.add(MENU_GET,"MenuGet=get");
        _en.add(MENU_REMOVE,"MenuRemove=remove");
        _en.add(MENU_NB,"MenuNb=nb");
        _en.add(MENU_ADD_SEPARATOR,"MenuAddSeparator=addSeparator");
        _en.add(ABS_MENU_ITEM_ADD_ACTION,"AbsMenuItemAddAction=add");
        _en.add(MENU_ITEM_CHECK_IS_SELECTED,"MenuItemCheckIsSelected=isSelected");
        _en.add(MENU_ITEM_CHECK_SET_SELECTED,"MenuItemCheckSetSelected=setSelected");
        _en.add(COMMAND_BINDING,"CommandBinding=binding");
        _en.add(COMMAND_ACTION,"CommandAction=action");
        _en.add(CONFIRM_FIELD_OK,"ConfirmFieldOk=OK");
        _en.add(CONFIRM_FIELD_YES,"ConfirmFieldYes=YES");
        _en.add(CONFIRM_FIELD_NO,"ConfirmFieldNo=NO");
        _en.add(CONFIRM_FIELD_CANCEL,"ConfirmFieldCancel=CANCEL");
        _en.add(PANEL_BORDER_AFTER_LAST,"PanelBorderAfterLast=AFTER_LAST_LINE");
        _en.add(PANEL_BORDER_AFTER_LINE_ENDS,"PanelBorderAfterLineEnds=AFTER_LINE_ENDS");
        _en.add(PANEL_BORDER_BEFORE_FIRST,"PanelBorderBeforeFirst=BEFORE_FIRST_LINE");
        _en.add(PANEL_BORDER_BEFORE_LINE_BEGINS,"PanelBorderBeforeLineBegins=BEFORE_LINE_BEGINS");
        _en.add(PANEL_BORDER_EAST,"PanelBorderEast=EAST");
        _en.add(PANEL_BORDER_WEST,"PanelBorderWest=WEST");
        _en.add(PANEL_BORDER_NORTH,"PanelBorderNorth=NORTH");
        _en.add(PANEL_BORDER_SOUTH,"PanelBorderSouth=SOUTH");
        _en.add(PANEL_BORDER_CENTER,"PanelBorderCenter=CENTER");
        GuiAliasParameters.en(_en);
    }
    public static TranslationsFile fr() {
        TranslationsFile fr_ = new TranslationsFile();
        fr(fr_);
        return fr_;
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(WINDOW_SET,"WindowSet=$core.EnsAbsFenetre");
        _fr.add(FRAME,"Frame=$coeur.Fenetre");
        _fr.add(CONFIRM,"Confirm=$coeur.Confirmation");
        _fr.add(DIALOG,"Dialog=$coeur.Dialogue");
        _fr.add(WINDOW_TYPE,"WindowType=$coeur.AbsFenetre");
        _fr.add(COMPONENT,"Component=$coeur.Composant");
        _fr.add(ACTION_EVENT,"ActionEvent=$coeur.ActionEvt");
        _fr.add(MOUSE_EVENT,"MouseEvent=$coeur.SourisEvt");
        _fr.add(TABLE_LISTENER,"TableListener=$coeur.TableEcouteur");
        _fr.add(TABLE_GUI,"TableGui=$coeur.GrilleTable");
        _fr.add(TREE_LISTENER,"TreeListener=$coeur.ArbreEcouteur");
        _fr.add(TREE,"Tree=$coeur.Arbre");
        _fr.add(TREE_NODE,"TreeNode=$coeur.ArbreNoeud");
        _fr.add(KEY_EVENT,"KeyEvent=$coeur.ClavierEvt");
        _fr.add(WINDOW_EVENT,"WindowEvent=$coeur.FenetreEvt");
        _fr.add(PANEL,"Panel=$coeur.Panneau");
        _fr.add(TABBED_PANE,"TabbedPane=$coeur.Onglets");
        _fr.add(PANEL_BORDER,"PanelBorder=$coeur.PanneauBordure");
        _fr.add(BUTTON,"Button=$coeur.Bouton");
        _fr.add(PROG_BAR,"ProgBar=$coeur.BarreProg");
        _fr.add(CHECK_BOX,"CheckBox=$coeur.Case");
        _fr.add(RADIO,"Radio=$coeur.Radio");
        _fr.add(TEXT_LABEL,"TextLabel=$coeur.Etiquette");
        _fr.add(IMAGE,"Image=$coeur.Image");
        _fr.add(IMAGE_LABEL,"ImageLabel=$coeur.EtImage");
        _fr.add(COLOR,"Color=$coeur.Couleur");
        _fr.add(INPUT,"Input=$coeur.Saisie");
        _fr.add(FONT,"Font=$coeur.Police");
        _fr.add(TEXT_AREA,"TextArea=$coeur.Zone");
        _fr.add(TEXT_FIELD,"TextField=$coeur.ChampTxt");
        _fr.add(GR_LIST,"GrList=$coeur.GrListe");
        _fr.add(COMBO,"Combo=$coeur.ListeDeroulante");
        _fr.add(BUTTON_GROUP,"ButtonGroup=$coeur.GroupeBoutons");
        _fr.add(RENDER,"Render=$coeur.Rendu");
        _fr.add(POPUP_MENU,"PopupMenu=$coeur.MenuContextuel");
        _fr.add(DIMENSION,"Dimension=$coeur.Dimension");
        _fr.add(KEY_LISTENER,"KeyListener=$coeur.ClavierEcouteur");
        _fr.add(MOUSE_LISTENER,"MouseListener=$coeur.SourisEcouteur");
        _fr.add(WHEEL_LISTENER,"WheelListener=$coeur.MoletteSourisEcouteur");
        _fr.add(WHEEL_EVENT,"WheelEvent=$coeur.MoletteSourisEvt");
        _fr.add(ACTION_LISTENER,"ActionListener=$coeur.ActionEcouteur");
        _fr.add(ACTION,"Action=$coeur.Action");
        _fr.add(CHANGE_LISTENER,"ChangeListener=$coeur.ChangeEcouteur");
        _fr.add(WINDOW_LISTENER,"WindowListener=$coeur.FenetreEcouteur");
        _fr.add(SCROLL_PANE,"ScrollPane=$coeur.Ascenseur");
        _fr.add(SPLIT_PANE,"SplitPane=$coeur.SepAj");
        _fr.add(LIST_SELECTION,"ListSelection=$coeur.SelectionListe");
        _fr.add(PAINT,"Paint=$coeur.Peinture");
        _fr.add(MENU_BAR,"MenuBar=$coeur.BarreMenu");
        _fr.add(ABS_MENU,"AbsMenu=$coeur.MenuAbs");
        _fr.add(MENU,"Menu=$coeur.Menu");
        _fr.add(ABS_MENU_ITEM,"AbsMenuItem=$coeur.MenuEltAbs");
        _fr.add(MENU_ITEM,"MenuItem=$coeur.MenuElt");
        _fr.add(MENU_ITEM_CHECK,"MenuItemCheck=$coeur.MenuEltCase");
        _fr.add(SPINNER,"Spinner=$coeur.CurseurNum");
        _fr.add(SLIDER,"Slider=$coeur.CurseurBarre");
        _fr.add(COMMAND,"Command=$coeur.Command");
        _fr.add(CONFIRM_FIELD,"ConfirmField=champ");
        _fr.add(CONFIRM_FULL,"ConfirmFull=ouiNonAnnuler");
        _fr.add(CONFIRM_MESSAGE,"ConfirmMessage=message");
        _fr.add(CONFIRM_OK,"ConfirmOk=ok");
        _fr.add(CONFIRM_YES_NO,"ConfirmYesNo=ouiNon");
        _fr.add(PACK,"Pack=cadrer");
        _fr.add(ADD_WINDOW_LISTENER,"AddWindowListener=ajFenetreEcout");
        _fr.add(REMOVE_WINDOW_LISTENER,"RemoveWindowListener=supprFenetreEcout");
        _fr.add(GET_WINDOW_LISTENERS,"GetWindowListeners=valFenetreEcouts");
        _fr.add(DISPOSE,"Dispose=liberer");
        _fr.add(WINDOW_TYPE_RELATIVE,"WindowTypeRelative=rel");
        _fr.add(IS_VISIBLE,"IsVisible=estVisible");
        _fr.add(SET_VISIBLE,"SetVisible=majVisible");
        _fr.add(SET_CONTENT,"SetContent=majContenu");
        _fr.add(GET_MENU_BAR,"GetMenuBar=valBarreMenu");
        _fr.add(SET_MENU_BAR,"SetMenuBar=majBarreMenu");
        _fr.add(WINDOW,"Window=fenetre");
        _fr.add(WINDOW_SET_ADD,"WindowSetAdd=ajout");
        _fr.add(WINDOW_SET_ALL,"WindowSetAll=tous");
        _fr.add(WINDOW_SET_CONTAINS,"WindowSetContains=contient");
        _fr.add(WINDOW_SET_REMOVE,"WindowSetRemove=suppr");
        _fr.add(WINDOW_SET_SNAPSHOT,"WindowSetSnapshot=tab");
        _fr.add(DIALOG_IS_MODAL,"DialogIsModal=estModale");
        _fr.add(DIALOG_SET_MODAL,"DialogSetModal=majModale");
        _fr.add(CLOSE_ALL,"CloseAll=toutFermer");
        _fr.add(ACTION_EVENT_IS_ALT,"ActionEventIsAlt=estAlt");
        _fr.add(ACTION_EVENT_IS_SHIFT,"ActionEventIsShift=estMaj");
        _fr.add(ACTION_EVENT_IS_CTRL,"ActionEventIsCtrl=estCtrl");
        _fr.add(ACTION_EVENT_COMMAND,"ActionEventCommand=commande");
        _fr.add(MOUSE_EVENT_GET_FIRST,"MouseEventGetFirst=x");
        _fr.add(MOUSE_EVENT_GET_SECOND,"MouseEventGetSecond=y");
        _fr.add(MOUSE_EVENT_GET_CLICKS,"MouseEventGetClicks=valClics");
        _fr.add(MOUSE_EVENT_IS_ALT,"MouseEventIsAlt=estAlt");
        _fr.add(MOUSE_EVENT_IS_SHIFT,"MouseEventIsShift=estMaj");
        _fr.add(MOUSE_EVENT_IS_CTRL,"MouseEventIsCtrl=estCtrl");
        _fr.add(MOUSE_EVENT_IS_LEFT,"MouseEventIsLeft=estGauche");
        _fr.add(MOUSE_EVENT_IS_RIGHT,"MouseEventIsRight=estDroite");
        _fr.add(MOUSE_EVENT_IS_MIDDLE,"MouseEventIsMiddle=estMilieu");
        _fr.add(WHEEL_ROTATED_CLICKS,"WheelRotatedClicks=crans");
        _fr.add(KEY_EVENT_CODE,"KeyEventCode=codeTouche");
        _fr.add(KEY_EVENT_CHAR,"KeyEventChar=carTouche");
        _fr.add(KEY_EVENT_IS_ALT,"KeyEventIsAlt=estAlt");
        _fr.add(KEY_EVENT_IS_SHIFT,"KeyEventIsShift=estMaj");
        _fr.add(KEY_EVENT_IS_CTRL,"KeyEventIsCtrl=estCtrl");
        _fr.add(COUNT,"Count=nb");
        _fr.add(GET_INDEX_COMPO,"GetIndexCompo=val");
        _fr.add(ADD_COMPO,"AddCompo=ajout");
        _fr.add(REMOVE_COMPO,"RemoveCompo=supprimer");
        _fr.add(PANEL_ABSOLUTE,"PanelAbsolute=absolu");
        _fr.add(PANEL_FLOW,"PanelFlow=horizon");
        _fr.add(PANEL_PAGE_BOX,"PanelPageBox=page");
        _fr.add(PANEL_GRID,"PanelGrid=grille");
        _fr.add(PANEL_VALIDATE,"PanelValidate=valider");
        _fr.add(REMOVE_ALL,"RemoveAll=toutSuppr");
        _fr.add(GET_PARENT_COMPO,"GetParentCompo=valParent");
        _fr.add(GET_NEXT_COMPO,"GetNextCompo=suivant");
        _fr.add(COMPONENT_REPAINT,"ComponentRepaint=repeindre");
        _fr.add(COMPONENT_GET_PAINT,"ComponentGetPaint=valPeindre");
        _fr.add(COMPONENT_SET_PAINT,"ComponentSetPaint=majPeindre");
        _fr.add(GET_FONT,"GetFont=valPolice");
        _fr.add(SET_FONT,"SetFont=majPolice");
        _fr.add(COMPONENT_GET_HEIGHT,"ComponentGetHeight=valHauteur");
        _fr.add(COMPONENT_GET_WIDTH,"ComponentGetWidth=valLargeur");
        _fr.add(COMPONENT_IS_AUTOSCROLLS,"ComponentIsAutoscrolls=estAutoascenseur");
        _fr.add(COMPONENT_SET_AUTOSCROLLS,"ComponentSetAutoscrolls=majAutoascenseur");
        _fr.add(COMPONENT_GET_PREFERRED_SIZE,"ComponentGetPreferredSize=valTaillePreferee");
        _fr.add(COMPONENT_SET_PREFERRED_SIZE,"ComponentSetPreferredSize=majTaillePreferee");
        _fr.add(COMPONENT_SET_SIZE,"ComponentSetSize=majTaille");
        _fr.add(COMPONENT_IS_VISIBLE,"ComponentIsVisible=estVisible");
        _fr.add(COMPONENT_SET_VISIBLE,"ComponentSetVisible=majVisible");
        _fr.add(COMPONENT_INVOKE_LATER,"ComponentInvokeLater=invoquerPlusTard");
        _fr.add(ADD_KEY_LISTENER,"AddKeyListener=ajClavier");
        _fr.add(REMOVE_KEY_LISTENER,"RemoveKeyListener=supprClavier");
        _fr.add(GET_KEY_LISTENERS,"GetKeyListeners=valClaviers");
        _fr.add(ADD_WHEEL_LISTENER,"AddWheelListener=ajMolette");
        _fr.add(REMOVE_WHEEL_LISTENER,"RemoveWheelListener=supprMolette");
        _fr.add(GET_WHEEL_LISTENERS,"GetWheelListeners=valMolettes");
        _fr.add(ADD_LISTENER,"AddListener=ajEcout");
        _fr.add(REMOVE_LISTENER,"RemoveListener=supprEcout");
        _fr.add(GET_LISTENERS,"GetListeners=valEcouts");
        _fr.add(REQUEST_FOCUS,"RequestFocus=demanderFocus");
        _fr.add(COMP_BACK,"CompBack=arriere");
        _fr.add(COMPO_REL_LEFT,"CompoRelLeft=gauche");
        _fr.add(COMPO_REL_RIGHT,"CompoRelRight=droite");
        _fr.add(COMPO_REL_TOP,"CompoRelTop=tete");
        _fr.add(COMPO_REL_BOTTOM,"CompoRelBottom=pied");
        _fr.add(COMPO_REL_CENT_HORIZ,"CompoRelCentHoriz=centHoriz");
        _fr.add(COMPO_REL_CENT_VERT,"CompoRelCentVert=centVert");
        _fr.add(COMP_FOCUSABLE,"CompFocusable=focus");
        _fr.add(COMP_FORE,"CompFore=avant");
        _fr.add(COMP_GET_FIRST_POS,"CompGetFirstPos=x");
        _fr.add(COMP_GET_SECOND_POS,"CompGetSecondPos=y");
        _fr.add(COMP_OPAQUE,"CompOpaque=opaque");
        _fr.add(COMP_TOOL_TIP,"CompToolTip=infobulle");
        _fr.add(COMP_LOC,"CompLoc=lieu");
        _fr.add(COMP_BOR_LINE,"CompBorLine=ligne");
        _fr.add(COMP_BOR_LOWER,"CompBorLower=bas");
        _fr.add(COMP_BOR_RAISE,"CompBorRaise=haut");
        _fr.add(COMP_BOR_TITLE,"CompBorTitle=titre");
        _fr.add(COMPONENT_BIND,"ComponentBind=lier");
        _fr.add(COMPONENT_UNBIND,"ComponentUnbind=delier");
        _fr.add(COMPONENT_COMMANDS,"ComponentCommands=commandes");
        _fr.add(TABBED_PANE_NB,"TabbedPaneNb=nb");
        _fr.add(TABBED_PANE_ADD,"TabbedPaneAdd=ajout");
        _fr.add(TABBED_PANE_REMOVE,"TabbedPaneRemove=suppr");
        _fr.add(TABBED_PANE_SEL_INDEX,"TabbedPaneSelIndex=selIndice");
        _fr.add(TABBED_PANE_INDEX,"TabbedPaneIndex=indice");
        _fr.add(TABBED_PANE_GET,"TabbedPaneGet=val");
        _fr.add(TABBED_PANE_GET_TITLE,"TabbedPaneGetTitle=valTitre");
        _fr.add(TABBED_PANE_SET,"TabbedPaneSet=maj");
        _fr.add(TABBED_PANE_SET_TITLE,"TabbedPaneSetTitle=majTitre");
        _fr.add(SET_LABEL_TEXT,"SetLabelText=majTexte");
        _fr.add(SET_LABEL_IMAGE,"SetLabelImage=majImage");
        _fr.add(PROG_BAR_OR,"ProgBarOr=horizontal");
        _fr.add(PROG_BAR_VALUE,"ProgBarValue=val");
        _fr.add(PROG_BAR_MIN,"ProgBarMin=min");
        _fr.add(PROG_BAR_MAX,"ProgBarMax=max");
        _fr.add(SCROLL_PANE_HORIZONTAL_VALUE,"ScrollPaneHorizontalValue=hValeur");
        _fr.add(SCROLL_PANE_VERTICAL_VALUE,"ScrollPaneVerticalValue=vValeur");
        _fr.add(SCROLL_PANE_GET_VIEW,"ScrollPaneGetView=valVue");
        _fr.add(SCROLL_PANE_SET_VIEW,"ScrollPaneSetView=majVue");
        _fr.add(SCROLL_PANE_VALIDATE,"ScrollPaneValidate=valider");
        _fr.add(SPLIT_PANE_GET_DIVIDER_LOCATION,"SplitPaneGetDividerLocation=valSepPos");
        _fr.add(SPLIT_PANE_SET_DIVIDER_LOCATION,"SplitPaneSetDividerLocation=majSepPos");
        _fr.add(SPLIT_PANE_GET_DIVIDER_SIZE,"SplitPaneGetDividerSize=valSepTaille");
        _fr.add(SPLIT_PANE_SET_DIVIDER_SIZE,"SplitPaneSetDividerSize=majSepTaille");
        _fr.add(SPLIT_PANE_GET_LEFT,"SplitPaneGetLeft=valGauche");
        _fr.add(SPLIT_PANE_SET_LEFT,"SplitPaneSetLeft=majGauche");
        _fr.add(SPLIT_PANE_GET_RIGHT,"SplitPaneGetRight=valDroite");
        _fr.add(SPLIT_PANE_SET_RIGHT,"SplitPaneSetRight=majDroite");
        _fr.add(SPLIT_PANE_IS_CONTINUOUS_LAYOUT,"SplitPaneIsContinuousLayout=estPositCont");
        _fr.add(SPLIT_PANE_SET_CONTINUOUS_LAYOUT,"SplitPaneSetContinuousLayout=majPositCont");
        _fr.add(SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE,"SplitPaneIsOneTouchExpandable=estExtTouche");
        _fr.add(SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE,"SplitPaneSetOneTouchExpandable=majExtTouche");
        _fr.add(SPLIT_PANE_VALIDATE,"SplitPaneValidate=valider");
        _fr.add(INPUT_IS_ENABLED,"InputIsEnabled=estActif");
        _fr.add(INPUT_SET_ENABLED,"InputSetEnabled=majActif");
        _fr.add(CHECK_BOX_ADD_ACTION,"CheckBoxAddAction=ajoutAction");
        _fr.add(CHECK_BOX_GET_TEXT,"CheckBoxGetText=valTexte");
        _fr.add(CHECK_BOX_SET_TEXT,"CheckBoxSetText=majTexte");
        _fr.add(CHECK_BOX_IS_SELECTED,"CheckBoxIsSelected=estSelect");
        _fr.add(CHECK_BOX_SET_SELECTED,"CheckBoxSetSelected=majSelect");
        _fr.add(SPINNER_GET_MAX,"SpinnerGetMax=valMax");
        _fr.add(SPINNER_GET_MIN,"SpinnerGetMin=valMin");
        _fr.add(SPINNER_GET_STEP,"SpinnerGetStep=valPas");
        _fr.add(SPINNER_GET_VALUE,"SpinnerGetValue=val");
        _fr.add(SPINNER_SET_MAX,"SpinnerSetMax=majMax");
        _fr.add(SPINNER_SET_MIN,"SpinnerSetMin=majMin");
        _fr.add(SPINNER_SET_STEP,"SpinnerSetStep=majPas");
        _fr.add(SPINNER_SET_VALUE,"SpinnerSetValue=maj");
        _fr.add(SPINNER_SET_RANGE,"SpinnerSetRange=majRang");
        _fr.add(SPINNER_SET_RANGE_VALUE,"SpinnerSetRangeValue=majRangValeur");
        _fr.add(ADD_CHANGE,"AddChange=ajChange");
        _fr.add(SLIDER_GET_MAX,"SliderGetMax=valMax");
        _fr.add(SLIDER_GET_MIN,"SliderGetMin=valMin");
        _fr.add(SLIDER_GET_ORIENTATION,"SliderGetOrientation=valOrient");
        _fr.add(SLIDER_GET_VALUE,"SliderGetValue=val");
        _fr.add(SLIDER_SET_MAX,"SliderSetMax=majMax");
        _fr.add(SLIDER_SET_MIN,"SliderSetMin=majMin");
        _fr.add(SLIDER_SET_ORIENTATION,"SliderSetOrientation=majOrient");
        _fr.add(SLIDER_SET_VALUE,"SliderSetValue=maj");
        _fr.add(RADIO_GET_TEXT,"RadioGetText=valTexte");
        _fr.add(RADIO_SET_TEXT,"RadioSetText=majTexte");
        _fr.add(RADIO_IS_SELECTED,"RadioIsSelected=estSelect");
        _fr.add(RADIO_SET_SELECTED,"RadioSetSelected=majSelect");
        _fr.add(TEXT_FIELD_ADD_ACTION,"TextFieldAddAction=ajoutAction");
        _fr.add(TEXT_FIELD_ADD_POPUP,"TextFieldAddPopup=ajout");
        _fr.add(TEXT_FIELD_GET_TEXT,"TextFieldGetText=valTexte");
        _fr.add(TEXT_FIELD_SET_TEXT,"TextFieldSetText=majTexte");
        _fr.add(TEXT_AREA_APPEND,"TextAreaAppend=ajout");
        _fr.add(TEXT_AREA_INSERT,"TextAreaInsert=inserer");
        _fr.add(TEXT_AREA_REPLACE_RANGE,"TextAreaReplaceRange=remplacerRang");
        _fr.add(TEXT_AREA_REPLACE_SELECTION,"TextAreaReplaceSelection=remplacerSelect");
        _fr.add(TEXT_AREA_GET_SELECTED_TEXT,"TextAreaGetSelectedText=valTextSelect");
        _fr.add(TEXT_AREA_SET_SELECTION_START,"TextAreaSetSelectionStart=majDebutSelection");
        _fr.add(TEXT_AREA_SET_SELECTION_END,"TextAreaSetSelectionEnd=majFinSelection");
        _fr.add(TEXT_AREA_GET_TAB_SIZE,"TextAreaGetTabSize=valTailleTab");
        _fr.add(TEXT_AREA_SET_TAB_SIZE,"TextAreaSetTabSize=majTailleTab");
        _fr.add(TEXT_AREA_GET_TEXT,"TextAreaGetText=valTexte");
        _fr.add(TEXT_AREA_SET_TEXT,"TextAreaSetText=majTexte");
        _fr.add(TEXT_AREA_SELECT,"TextAreaSelect=select");
        _fr.add(TEXT_AREA_SELECT_ALL,"TextAreaSelectAll=selectTous");
        _fr.add(COMBO_ADD_ITEM,"ComboAddItem=ajout");
        _fr.add(COMBO_GET_ITEM_COUNT,"ComboGetItemCount=nb");
        _fr.add(COMBO_GET_LISTENERS,"ComboGetListeners=valEcouteurs");
        _fr.add(COMBO_GET_SELECTED_INDEX,"ComboGetSelectedIndex=valIndiceSelect");
        _fr.add(COMBO_GET_SELECTED_INDEXES,"ComboGetSelectedIndexes=valIndicesSelect");
        _fr.add(COMBO_GET_SELECTED_ITEM,"ComboGetSelectedItem=valEltSelect");
        _fr.add(COMBO_REMOVE_ALL_ITEMS,"ComboRemoveAllItems=supprTousElt");
        _fr.add(COMBO_REMOVE_ITEM,"ComboRemoveItem=supprElt");
        _fr.add(COMBO_SELECT_ITEM,"ComboSelectItem=selectElt");
        _fr.add(COMBO_ADD_LISTENER,"ComboAddListener=ajEcouteur");
        _fr.add(COMBO_REMOVE_LISTENER,"ComboRemoveListener=supprEcouteur");
        _fr.add(GR_LIST_ADD,"GrListAdd=ajout");
        _fr.add(GR_LIST_CLEAR,"GrListClear=toutSuppr");
        _fr.add(GR_LIST_CLEAR_SELECTION,"GrListClearSelection=supprimerSelection");
        _fr.add(GR_LIST_GET_LIST_VIEW,"GrListGetListView=valVue");
        _fr.add(GR_LIST_GET_RENDER,"GrListGetRender=valRendu");
        _fr.add(GR_LIST_GET_SELECTED_INDEXES,"GrListGetSelectedIndexes=valIndicesSelection");
        _fr.add(GR_LIST_GET_SELECTIONS,"GrListGetSelections=valSelections");
        _fr.add(GR_LIST_GET_VISIBLE_ROW_COUNT,"GrListGetVisibleRowCount=valNbLignesVisible");
        _fr.add(GR_LIST_REMOVE,"GrListRemove=supprimer");
        _fr.add(GR_LIST_SET,"GrListSet=maj");
        _fr.add(GR_LIST_SET_RENDER,"GrListSetRender=majRendu");
        _fr.add(GR_LIST_SET_SELECTED_INDEXES,"GrListSetSelectedIndexes=majIndicesSelection");
        _fr.add(GR_LIST_ADD_SELECTION,"GrListAddSelection=ajSelection");
        _fr.add(GR_LIST_REMOVE_SELECTION,"GrListRemoveSelection=supprSelection");
        _fr.add(GR_LIST_SET_VISIBLE_ROW_COUNT,"GrListSetVisibleRowCount=majNbLignesVisible");
        _fr.add(GR_LIST_UPDATE_GRAPHICS,"GrListUpdateGraphics=majGraphiques");
        _fr.add(POPUP_MENU_ADD,"PopupMenuAdd=ajout");
        _fr.add(POPUP_MENU_ADD_MENU,"PopupMenuAddMenu=ajMenu");
        _fr.add(POPUP_MENU_GET_COMP,"PopupMenuGetComp=comp");
        _fr.add(POPUP_MENU_REMOVE_COMP,"PopupMenuRemoveComp=supprComp");
        _fr.add(POPUP_MENU_NB_COMP,"PopupMenuNbComp=nbComps");
        _fr.add(POPUP_MENU_GET_MENU,"PopupMenuGetMenu=menu");
        _fr.add(POPUP_MENU_REMOVE_MENU,"PopupMenuRemoveMenu=supprMenu");
        _fr.add(POPUP_MENU_NB_MENU,"PopupMenuNbMenu=nbMenus");
        _fr.add(POPUP_MENU_SHOW,"PopupMenuShow=afficher");
        _fr.add(TREE_ADD_TREE_LISTENER,"TreeAddTreeListener=ajArbreEcout");
        _fr.add(TREE_GET_SELECTED,"TreeGetSelected=select");
        _fr.add(TREE_IS_ROOT_VISIBLE,"TreeIsRootVisible=estRacVisible");
        _fr.add(TREE_SET_ROOT_VISIBLE,"TreeSetRootVisible=majRacVisible");
        _fr.add(TREE_RELOAD,"TreeReload=recharger");
        _fr.add(TABLE_ADD_HEADER,"TableAddHeader=ajEntete");
        _fr.add(TABLE_ADD_SELECT,"TableAddSelect=ajSelect");
        _fr.add(TABLE_APPLY_CHANGES,"TableApplyChanges=appliquer");
        _fr.add(TABLE_ADD_INTERVAL,"TableAddInterval=ajInterval");
        _fr.add(TABLE_REMOVE_INTERVAL,"TableRemoveInterval=supprInterval");
        _fr.add(TABLE_MOVE_COLUMN,"TableMoveColumn=depl");
        _fr.add(TABLE_GET_COLUMN_AT_POINT,"TableGetColumnAtPoint=colPt");
        _fr.add(TABLE_GET_COLUMN_COUNT,"TableGetColumnCount=nbCol");
        _fr.add(TABLE_GET_COLUMN_NAME,"TableGetColumnName=colNom");
        _fr.add(TABLE_GET_ROW_AT_POINT,"TableGetRowAtPoint=lgPt");
        _fr.add(TABLE_GET_ROW_COUNT,"TableGetRowCount=nbRow");
        _fr.add(TABLE_GET_SELECTED_ROW,"TableGetSelectedRow=selectLg");
        _fr.add(TABLE_GET_SELECTED_ROW_COUNT,"TableGetSelectedRowCount=selectLgNb");
        _fr.add(TABLE_GET_SELECTED_ROWS,"TableGetSelectedRows=selectLgs");
        _fr.add(TABLE_GET_VALUE,"TableGetValue=val");
        _fr.add(TABLE_SET_COLUMNS,"TableSetColumns=majCols");
        _fr.add(TABLE_SET_MULTIPLE,"TableSetMultiple=majMultiple");
        _fr.add(TABLE_SET_REORDER,"TableSetReorder=majReord");
        _fr.add(TABLE_SET_ROW_COUNT,"TableSetRowCount=majLgNb");
        _fr.add(TABLE_SET_VALUE,"TableSetValue=maj");
        _fr.add(TABLE_IS_MULTIPLE,"TableIsMultiple=estMultiple");
        _fr.add(TABLE_IS_REORDER,"TableIsReorder=estReord");
        _fr.add(DIMENSION_GET_HEIGHT,"DimensionGetHeight=valHauteur");
        _fr.add(DIMENSION_GET_WIDTH,"DimensionGetWidth=valLargeur");
        _fr.add(FONT_GET_NAME,"FontGetName=valNom");
        _fr.add(FONT_GET_SIZE,"FontGetSize=valTaille");
        _fr.add(FONT_IS_BOLD,"FontIsBold=estGras");
        _fr.add(FONT_IS_ITALIC,"FontIsItalic=estItalique");
        _fr.add(FONT_STRING_WIDTH,"FontStringWidth=largeurChaine");
        _fr.add(BUTTON_GROUP_ADD,"ButtonGroupAdd=ajout");
        _fr.add(RENDER_GET_HEIGHT,"RenderGetHeight=valHauteur");
        _fr.add(RENDER_GET_PAINT,"RenderGetPaint=valPeindre");
        _fr.add(RENDER_GET_WIDTH,"RenderGetWidth=valLargeur");
        _fr.add(RENDER_SET_HEIGHT,"RenderSetHeight=majHauteur");
        _fr.add(RENDER_SET_PAINT,"RenderSetPaint=majPeindre");
        _fr.add(RENDER_SET_WIDTH,"RenderSetWidth=majLargeur");
        _fr.add(COLOR_ALPHA,"ColorAlpha=a");
        _fr.add(COLOR_BLUE,"ColorBlue=b");
        _fr.add(COLOR_RED,"ColorRed=r");
        _fr.add(COLOR_GREEN,"ColorGreen=v");
        _fr.add(COLOR_IS_TRANSPARENT,"ColorIsTransparent=transparent");
        _fr.add(IMAGE_DRAW,"ImageDraw=dessiner");
        _fr.add(IMAGE_DRAW_LINE,"ImageDrawLine=dessinerLigne");
        _fr.add(IMAGE_DRAW_OVAL,"ImageDrawOval=dessinerOval");
        _fr.add(IMAGE_DRAW_POLYGON,"ImageDrawPolygon=dessinerPolygone");
        _fr.add(IMAGE_DRAW_RECT,"ImageDrawRect=dessinerRect");
        _fr.add(IMAGE_FILL_OVAL,"ImageFillOval=remplirOval");
        _fr.add(IMAGE_FILL_POLYGON,"ImageFillPolygon=remplirPolygone");
        _fr.add(IMAGE_FILL_RECT,"ImageFillRect=remplirRect");
        _fr.add(IMAGE_GET,"ImageGet=val");
        _fr.add(IMAGE_SET,"ImageSet=maj");
        _fr.add(IMAGE_EQ,"ImageEq=e");
        _fr.add(IMAGE_GET_COLOR,"ImageGetColor=valCouleur");
        _fr.add(IMAGE_SET_COLOR,"ImageSetColor=majCouleur");
        _fr.add(IMAGE_GET_FONT,"ImageGetFont=valPolice");
        _fr.add(IMAGE_SET_FONT,"ImageSetFont=majPolice");
        _fr.add(IMAGE_GET_HEIGHT,"ImageGetHeight=h");
        _fr.add(IMAGE_GET_WIDTH,"ImageGetWidth=l");
        _fr.add(IMAGE_IS_WITH_ALPHA,"ImageIsWithAlpha=alpha");
        _fr.add(IMAGE_DISPOSE,"ImageDispose=liberer");
        _fr.add(ACTION_PERFORMED,"ActionPerformed=action");
        _fr.add(ACTION_WRAP,"ActionWrap=env");
        _fr.add(ACTION_ENABLED,"ActionEnabled=actif");
        _fr.add(ACTION_ARG,"ActionArg=action");
        _fr.add(STATE_CHANGED,"StateChanged=changeEtat");
        _fr.add(WHEEL_MOVE,"WheelMove=moletteDepl");
        _fr.add(MOUSE_CLICKED,"MouseClicked=clic");
        _fr.add(MOUSE_ENTERED,"MouseEntered=entre");
        _fr.add(MOUSE_EXITED,"MouseExited=sorti");
        _fr.add(MOUSE_PRESSED,"MousePressed=presse");
        _fr.add(MOUSE_DRAGGED,"MouseDragged=appuye");
        _fr.add(MOUSE_MOVED,"MouseMoved=bouge");
        _fr.add(MOUSE_RELEASED,"MouseReleased=relache");
        _fr.add(KEY_TYPED,"KeyTyped=tape");
        _fr.add(KEY_RELEASED,"KeyReleased=touRelache");
        _fr.add(KEY_PRESSED,"KeyPressed=touPresse");
        _fr.add(WINDOW_ACTIVATED,"WindowActivated=active");
        _fr.add(WINDOW_ICONIFIED,"WindowIconified=iconifie");
        _fr.add(WINDOW_DEACTIVATED,"WindowDeactivated=desactive");
        _fr.add(WINDOW_DEICONIFIED,"WindowDeiconified=desiconifie");
        _fr.add(WINDOW_OPENED,"WindowOpened=ouverte");
        _fr.add(WINDOW_CLOSING,"WindowClosing=fermeture");
        _fr.add(WINDOW_CLOSED,"WindowClosed=ferme");
        _fr.add(VALUE_CHANGED,"ValueChanged=changement");
        _fr.add(TREE_LISTENER_VALUE_CHANGED,"TreeListenerValueChanged=valeurChange");
        _fr.add(TREE_NODE_ADD,"TreeNodeAdd=aj");
        _fr.add(TREE_NODE_INSERT,"TreeNodeInsert=inserer");
        _fr.add(TREE_NODE_REMOVE,"TreeNodeRemove=suppr");
        _fr.add(TREE_NODE_REMOVE_FROM_PARENT,"TreeNodeRemoveFromParent=supprDeParent");
        _fr.add(TREE_NODE_REMOVE_ALL_CHILDREN,"TreeNodeRemoveAllChildren=supprTsEnfants");
        _fr.add(TREE_NODE_SET_USER_OBJECT,"TreeNodeSetUserObject=maj");
        _fr.add(TREE_NODE_GET_USER_OBJECT,"TreeNodeGetUserObject=val");
        _fr.add(TREE_NODE_EQ,"TreeNodeEq=eg");
        _fr.add(TREE_NODE_NB,"TreeNodeNb=nb");
        _fr.add(TREE_NODE_GET_FIRST_CHILD,"TreeNodeGetFirstChild=prem");
        _fr.add(TREE_NODE_GET_LAST_CHILD,"TreeNodeGetLastChild=der");
        _fr.add(TREE_NODE_GET_NEXT_SIBLING,"TreeNodeGetNextSibling=suiv");
        _fr.add(TREE_NODE_GET_PREVIOUS_SIBLING,"TreeNodeGetPreviousSibling=prec");
        _fr.add(TREE_NODE_GET_PARENT_NODE,"TreeNodeGetParentNode=parent");
        _fr.add(TREE_NODE_IS_ANCESTOR,"TreeNodeIsAncestor=estAncetre");
        _fr.add(TREE_NODE_IS_DESCENDANT,"TreeNodeIsDescendant=estDescendant");
        _fr.add(TABLE_VALUE_TABLE_CHANGED,"TableValueTableChanged=valeurTableChange");
        _fr.add(PAINT_METHOD,"PaintMethod=peindre");
        _fr.add(PAINT_ADD,"PaintAdd=ajout");
        _fr.add(PAINT_SET,"PaintSet=maj");
        _fr.add(PAINT_REFRESH,"PaintRefresh=rafraichir");
        _fr.add(PAINT_REFRESH_ONE,"PaintRefreshOne=rafraichirUn");
        _fr.add(MENU_BAR_ADD,"MenuBarAdd=ajout");
        _fr.add(MENU_BAR_GET,"MenuBarGet=val");
        _fr.add(MENU_BAR_REMOVE,"MenuBarRemove=suppr");
        _fr.add(MENU_BAR_NB,"MenuBarNb=nb");
        _fr.add(ABS_MENU_GET_PARENT,"AbsMenuGetParent=valParent");
        _fr.add(ABS_MENU_GET_TEXT,"AbsMenuGetText=valText");
        _fr.add(ABS_MENU_IS_ENABLED,"AbsMenuIsEnabled=estActif");
        _fr.add(ABS_MENU_SET_DEEP_ENABLED,"AbsMenuSetDeepEnabled=majActifProfond");
        _fr.add(ABS_MENU_SET_ENABLED,"AbsMenuSetEnabled=majActif");
        _fr.add(ABS_MENU_SET_TEXT,"AbsMenuSetText=majText");
        _fr.add(MENU_ADD,"MenuAdd=ajout");
        _fr.add(MENU_GET,"MenuGet=val");
        _fr.add(MENU_REMOVE,"MenuRemove=suppr");
        _fr.add(MENU_NB,"MenuNb=nb");
        _fr.add(MENU_ADD_SEPARATOR,"MenuAddSeparator=ajSeparateur");
        _fr.add(ABS_MENU_ITEM_ADD_ACTION,"AbsMenuItemAddAction=ajout");
        _fr.add(MENU_ITEM_CHECK_IS_SELECTED,"MenuItemCheckIsSelected=estSelect");
        _fr.add(MENU_ITEM_CHECK_SET_SELECTED,"MenuItemCheckSetSelected=majSelect");
        _fr.add(COMMAND_BINDING,"CommandBinding=lien");
        _fr.add(COMMAND_ACTION,"CommandAction=action");
        _fr.add(CONFIRM_FIELD_OK,"ConfirmFieldOk=OK");
        _fr.add(CONFIRM_FIELD_YES,"ConfirmFieldYes=OUI");
        _fr.add(CONFIRM_FIELD_NO,"ConfirmFieldNo=NON");
        _fr.add(CONFIRM_FIELD_CANCEL,"ConfirmFieldCancel=ANNULER");
        _fr.add(PANEL_BORDER_AFTER_LAST,"PanelBorderAfterLast=APRES_DERNIERE_LIGNE");
        _fr.add(PANEL_BORDER_AFTER_LINE_ENDS,"PanelBorderAfterLineEnds=APRES_FIN_LIGNE");
        _fr.add(PANEL_BORDER_BEFORE_FIRST,"PanelBorderBeforeFirst=AVANT_PREMIERE_LIGNE");
        _fr.add(PANEL_BORDER_BEFORE_LINE_BEGINS,"PanelBorderBeforeLineBegins=AVANT_DEBUT_LIGNE");
        _fr.add(PANEL_BORDER_EAST,"PanelBorderEast=EST");
        _fr.add(PANEL_BORDER_WEST,"PanelBorderWest=OUEST");
        _fr.add(PANEL_BORDER_NORTH,"PanelBorderNorth=NORD");
        _fr.add(PANEL_BORDER_SOUTH,"PanelBorderSouth=SUD");
        _fr.add(PANEL_BORDER_CENTER,"PanelBorderCenter=CENTRE");
        GuiAliasParameters.fr(_fr);
    }

    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping, CustAliases _custAliases, LgNamesContent _content) {
        CustList<KeyValueMemberName> list_ = new CustList<KeyValueMemberName>();
        list_.addAllElts(_custAliases.allMergeTableTypeMethodNames(_mapping,_content));
        list_.addAllElts(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ACTION_PERFORMED),getAliasActionPerformed()),
                new KeyValueMemberName(_mapping.getVal(STATE_CHANGED),getAliasStateChanged()),
                new KeyValueMemberName(_mapping.getVal(KEY_TYPED),getAliasKeyTyped()),
                new KeyValueMemberName(_mapping.getVal(KEY_PRESSED),getAliasKeyPressed()),
                new KeyValueMemberName(_mapping.getVal(KEY_RELEASED),getAliasKeyReleased()),
                new KeyValueMemberName(_mapping.getVal(VALUE_CHANGED),getAliasValueChanged()),
                new KeyValueMemberName(_mapping.getVal(TABLE_VALUE_TABLE_CHANGED),getAliasTableValueTableChanged()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_CLICKED),getAliasMouseClicked()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_ENTERED),getAliasMouseEntered()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EXITED),getAliasMouseExited()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_PRESSED),getAliasMousePressed()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_RELEASED),getAliasMouseReleased()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_DRAGGED),getAliasMouseDragged()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_MOVED),getAliasMouseMoved()),
                new KeyValueMemberName(_mapping.getVal(TREE_LISTENER_VALUE_CHANGED),getAliasTreeListenerValueChanged()),
                new KeyValueMemberName(_mapping.getVal(WHEEL_MOVE),getAliasWheelMove()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_ACTIVATED),getAliasWindowActivated()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_CLOSED),getAliasWindowClosed()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_CLOSING),getAliasWindowClosing()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_DEACTIVATED),getAliasWindowDeactivated()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_DEICONIFIED),getAliasWindowDeiconified()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_ICONIFIED),getAliasWindowIconified()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_OPENED),getAliasWindowOpened())
        ));
        return list_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> f_ = new StringMap<CustList<KeyValueMemberName>>();
        f_.addEntry(getAliasConfirm(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CONFIRM_FIELD_OK),getAliasConfirmFieldOk()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_FIELD_YES),getAliasConfirmFieldYes()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_FIELD_NO),getAliasConfirmFieldNo()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_FIELD_CANCEL),getAliasConfirmFieldCancel())
        ));
        f_.addEntry(getAliasPanelBorder(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_AFTER_LAST),getAliasPanelBorderAfterLast()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_AFTER_LINE_ENDS),getAliasPanelBorderAfterLineEnds()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_BEFORE_FIRST),getAliasPanelBorderBeforeFirst()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_BEFORE_LINE_BEGINS),getAliasPanelBorderBeforeLineBegins()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_EAST),getAliasPanelBorderEast()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_WEST),getAliasPanelBorderWest()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_NORTH),getAliasPanelBorderNorth()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_SOUTH),getAliasPanelBorderSouth()),
                new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_CENTER),getAliasPanelBorderCenter())
        ));
        return f_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
        allWindowMethods(_mapping,m_);
        allEventTypes(_mapping,m_);
        allComponents(_mapping,m_);
        m_.addEntry(getAliasDimension(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(DIMENSION_GET_HEIGHT),getAliasDimensionGetHeight()),
                new KeyValueMemberName(_mapping.getVal(DIMENSION_GET_WIDTH),getAliasDimensionGetWidth())));
        m_.addEntry(getAliasFont(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FONT_GET_NAME),getAliasFontGetName()),
                new KeyValueMemberName(_mapping.getVal(FONT_GET_SIZE),getAliasFontGetSize()),
                new KeyValueMemberName(_mapping.getVal(FONT_IS_BOLD),getAliasFontIsBold()),
                new KeyValueMemberName(_mapping.getVal(FONT_IS_ITALIC),getAliasFontIsItalic()),
                new KeyValueMemberName(_mapping.getVal(FONT_STRING_WIDTH),getAliasFontStringWidth())));
        m_.addEntry(getAliasButtonGroup(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BUTTON_GROUP_ADD),getAliasButtonGroupAdd()))
        );
        m_.addEntry(getAliasRender(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(RENDER_GET_HEIGHT),getAliasRenderGetHeight()),
                new KeyValueMemberName(_mapping.getVal(RENDER_GET_PAINT),getAliasRenderGetPaint()),
                new KeyValueMemberName(_mapping.getVal(RENDER_GET_WIDTH),getAliasRenderGetWidth()),
                new KeyValueMemberName(_mapping.getVal(RENDER_SET_HEIGHT),getAliasRenderSetHeight()),
                new KeyValueMemberName(_mapping.getVal(RENDER_SET_PAINT),getAliasRenderSetPaint()),
                new KeyValueMemberName(_mapping.getVal(RENDER_SET_WIDTH),getAliasRenderSetWidth())
        ));
        m_.addEntry(getAliasColor(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(COLOR_ALPHA),getAliasColorAlpha()),
                new KeyValueMemberName(_mapping.getVal(COLOR_BLUE),getAliasColorBlue()),
                new KeyValueMemberName(_mapping.getVal(COLOR_RED),getAliasColorRed()),
                new KeyValueMemberName(_mapping.getVal(COLOR_GREEN),getAliasColorGreen()),
                new KeyValueMemberName(_mapping.getVal(COLOR_IS_TRANSPARENT),getAliasColorIsTransparent()))
        );
        m_.addEntry(getAliasImage(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(IMAGE_DRAW),getAliasImageDraw()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_DRAW_LINE),getAliasImageDrawLine()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_DRAW_OVAL),getAliasImageDrawOval()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_DRAW_POLYGON),getAliasImageDrawPolygon()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_DRAW_RECT),getAliasImageDrawRect()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_FILL_OVAL),getAliasImageFillOval()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_FILL_POLYGON),getAliasImageFillPolygon()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_FILL_RECT),getAliasImageFillRect()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_GET),getAliasImageGet()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_SET),getAliasImageSet()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_EQ),getAliasImageEq()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_GET_COLOR),getAliasImageGetColor()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_SET_COLOR),getAliasImageSetColor()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_GET_FONT),getAliasImageGetFont()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_SET_FONT),getAliasImageSetFont()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_GET_HEIGHT),getAliasImageGetHeight()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_GET_WIDTH),getAliasImageGetWidth()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_IS_WITH_ALPHA),getAliasImageIsWithAlpha()),
                new KeyValueMemberName(_mapping.getVal(IMAGE_DISPOSE),getAliasImageDispose()))
        );
        m_.addEntry(getAliasActionListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ACTION_PERFORMED),getAliasActionPerformed()))
        );
        m_.addEntry(getAliasAction(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ACTION_WRAP),getAliasActionWrap()),
                new KeyValueMemberName(_mapping.getVal(ACTION_ENABLED),getAliasActionEnabled()),
                new KeyValueMemberName(_mapping.getVal(ACTION_ARG),getAliasActionArg()))
        );
        m_.addEntry(getAliasChangeListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(STATE_CHANGED),getAliasStateChanged()))
        );
        m_.addEntry(getAliasWheelListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(WHEEL_MOVE),getAliasWheelMove()))
        );
        m_.addEntry(getAliasMouseListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(MOUSE_CLICKED),getAliasMouseClicked()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_ENTERED),getAliasMouseEntered()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EXITED),getAliasMouseExited()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_PRESSED),getAliasMousePressed()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_DRAGGED),getAliasMouseDragged()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_MOVED),getAliasMouseMoved()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_RELEASED),getAliasMouseReleased()))
        );
        m_.addEntry(getAliasKeyListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(KEY_TYPED),getAliasKeyTyped()),
                new KeyValueMemberName(_mapping.getVal(KEY_RELEASED),getAliasKeyReleased()),
                new KeyValueMemberName(_mapping.getVal(KEY_PRESSED),getAliasKeyPressed()))
        );
        m_.addEntry(getAliasWindowListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(WINDOW_ACTIVATED),getAliasWindowActivated()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_ICONIFIED),getAliasWindowIconified()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_DEACTIVATED),getAliasWindowDeactivated()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_DEICONIFIED),getAliasWindowDeiconified()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_OPENED),getAliasWindowOpened()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_CLOSING),getAliasWindowClosing()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_CLOSED),getAliasWindowClosed()))
        );
        m_.addEntry(getAliasListSelection(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(VALUE_CHANGED),getAliasValueChanged()))
        );
        m_.addEntry(getAliasTreeListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TREE_LISTENER_VALUE_CHANGED),getAliasTreeListenerValueChanged()))
        );
        m_.addEntry(getAliasTreeNode(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_ADD),getAliasTreeNodeAdd()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_INSERT),getAliasTreeNodeInsert()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_REMOVE),getAliasTreeNodeRemove()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_REMOVE_FROM_PARENT),getAliasTreeNodeRemoveFromParent()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_REMOVE_ALL_CHILDREN),getAliasTreeNodeRemoveAllChildren()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_SET_USER_OBJECT),getAliasTreeNodeSetUserObject()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_GET_USER_OBJECT),getAliasTreeNodeGetUserObject()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_EQ),getAliasTreeNodeEq()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_NB),getAliasTreeNodeNb()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_GET_FIRST_CHILD),getAliasTreeNodeGetFirstChild()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_GET_LAST_CHILD),getAliasTreeNodeGetLastChild()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_GET_NEXT_SIBLING),getAliasTreeNodeGetNextSibling()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_GET_PREVIOUS_SIBLING),getAliasTreeNodeGetPreviousSibling()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_GET_PARENT_NODE),getAliasTreeNodeGetParentNode()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_IS_ANCESTOR),getAliasTreeNodeIsAncestor()),
                new KeyValueMemberName(_mapping.getVal(TREE_NODE_IS_DESCENDANT),getAliasTreeNodeIsDescendant()))
        );
        m_.addEntry(getAliasTableListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TABLE_VALUE_TABLE_CHANGED),getAliasTableValueTableChanged()))
        );
        m_.addEntry(getAliasPaint(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PAINT_METHOD),getAliasPaintMethod()),
                new KeyValueMemberName(_mapping.getVal(PAINT_ADD),getAliasPaintAdd()),
                new KeyValueMemberName(_mapping.getVal(PAINT_SET),getAliasPaintSet()),
                new KeyValueMemberName(_mapping.getVal(PAINT_REFRESH),getAliasPaintRefresh()),
                new KeyValueMemberName(_mapping.getVal(PAINT_REFRESH_ONE),getAliasPaintRefreshOne()))
        );
        m_.addEntry(getAliasMenuBar(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(MENU_BAR_ADD),getAliasMenuBarAdd()),
                new KeyValueMemberName(_mapping.getVal(MENU_BAR_GET),getAliasMenuBarGet()),
                new KeyValueMemberName(_mapping.getVal(MENU_BAR_REMOVE),getAliasMenuBarRemove()),
                new KeyValueMemberName(_mapping.getVal(MENU_BAR_NB),getAliasMenuBarNb())
        ));
        m_.addEntry(getAliasAbsMenu(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_PARENT),getAliasAbsMenuGetParent()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_TEXT),getAliasAbsMenuGetText()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_IS_ENABLED),getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_DEEP_ENABLED),getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_ENABLED),getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_TEXT),getAliasAbsMenuSetText())
        ));
        m_.addEntry(getAliasMenu(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_PARENT),getAliasAbsMenuGetParent()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_TEXT),getAliasAbsMenuGetText()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_IS_ENABLED),getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_DEEP_ENABLED),getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_ENABLED),getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_TEXT),getAliasAbsMenuSetText()),
                new KeyValueMemberName(_mapping.getVal(MENU_ADD),getAliasMenuAdd()),
                new KeyValueMemberName(_mapping.getVal(MENU_GET),getAliasMenuGet()),
                new KeyValueMemberName(_mapping.getVal(MENU_REMOVE),getAliasMenuRemove()),
                new KeyValueMemberName(_mapping.getVal(MENU_NB),getAliasMenuNb()),
                new KeyValueMemberName(_mapping.getVal(MENU_ADD_SEPARATOR),getAliasMenuAddSeparator())
        ));
        m_.addEntry(getAliasAbsMenuItem(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_PARENT),getAliasAbsMenuGetParent()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_TEXT),getAliasAbsMenuGetText()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_IS_ENABLED),getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_DEEP_ENABLED),getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_ENABLED),getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_TEXT),getAliasAbsMenuSetText()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_ITEM_ADD_ACTION),getAliasAbsMenuItemAddAction())
        ));
        m_.addEntry(getAliasMenuItemCheck(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_PARENT),getAliasAbsMenuGetParent()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_GET_TEXT),getAliasAbsMenuGetText()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_IS_ENABLED),getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_DEEP_ENABLED),getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_ENABLED),getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_SET_TEXT),getAliasAbsMenuSetText()),
                new KeyValueMemberName(_mapping.getVal(ABS_MENU_ITEM_ADD_ACTION),getAliasAbsMenuItemAddAction()),
                new KeyValueMemberName(_mapping.getVal(MENU_ITEM_CHECK_IS_SELECTED),getAliasMenuItemCheckIsSelected()),
                new KeyValueMemberName(_mapping.getVal(MENU_ITEM_CHECK_SET_SELECTED),getAliasMenuItemCheckSetSelected())
        ));
        m_.addEntry(getAliasCommand(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(COMMAND_BINDING),getAliasCommandBinding()),
                new KeyValueMemberName(_mapping.getVal(COMMAND_ACTION),getAliasCommandAction())
        ));
        return m_;
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(guiAliasParameters.allTableTypeMethodParamNames(_mapping));
        return m_;
    }
    private void allComponents(StringMap<String> _mapping, StringMap<CustList<KeyValueMemberName>> _m) {
        CustList<KeyValueMemberName> names_ = getCompoMethodNames(_mapping);
        _m.addEntry(getAliasPanel(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(COUNT),getAliasCount()),
                new KeyValueMemberName(_mapping.getVal(GET_INDEX_COMPO),getAliasGetIndexCompo()),
                new KeyValueMemberName(_mapping.getVal(ADD_COMPO),getAliasAddCompo()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_COMPO),getAliasRemoveCompo()),
                new KeyValueMemberName(_mapping.getVal(PANEL_ABSOLUTE),getAliasPanelAbsolute()),
                new KeyValueMemberName(_mapping.getVal(PANEL_FLOW),getAliasPanelFlow()),
                new KeyValueMemberName(_mapping.getVal(PANEL_PAGE_BOX),getAliasPanelPageBox()),
                new KeyValueMemberName(_mapping.getVal(PANEL_GRID),getAliasPanelGrid()),
                new KeyValueMemberName(_mapping.getVal(PANEL_VALIDATE),getAliasPanelValidate()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_ALL),getAliasRemoveAll())),names_));
        _m.addEntry(getAliasTabbedPane(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_NB),getAliasTabbedPaneNb()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_ADD),getAliasTabbedPaneAdd()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_REMOVE),getAliasTabbedPaneRemove()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_SEL_INDEX),getAliasTabbedPaneSelIndex()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_INDEX),getAliasTabbedPaneIndex()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_GET),getAliasTabbedPaneGet()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_GET_TITLE),getAliasTabbedPaneGetTitle()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_SET),getAliasTabbedPaneSet()),
                new KeyValueMemberName(_mapping.getVal(TABBED_PANE_SET_TITLE),getAliasTabbedPaneSetTitle()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_ALL),getAliasRemoveAll())),names_));
        _m.addEntry(getAliasPanelBorder(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(COUNT),getAliasCount()),
                new KeyValueMemberName(_mapping.getVal(GET_INDEX_COMPO),getAliasGetIndexCompo()),
                new KeyValueMemberName(_mapping.getVal(ADD_COMPO),getAliasAddCompo()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_COMPO),getAliasRemoveCompo()),
                new KeyValueMemberName(_mapping.getVal(PANEL_ABSOLUTE),getAliasPanelAbsolute()),
                new KeyValueMemberName(_mapping.getVal(PANEL_FLOW),getAliasPanelFlow()),
                new KeyValueMemberName(_mapping.getVal(PANEL_PAGE_BOX),getAliasPanelPageBox()),
                new KeyValueMemberName(_mapping.getVal(PANEL_GRID),getAliasPanelGrid()),
                new KeyValueMemberName(_mapping.getVal(PANEL_VALIDATE),getAliasPanelValidate()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_ALL),getAliasRemoveAll())),names_));
        _m.addEntry(getAliasComponent(), names_);
        _m.addEntry(getAliasTextLabel(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SET_LABEL_TEXT),getAliasSetLabelText())),names_));
        _m.addEntry(getAliasImageLabel(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SET_LABEL_IMAGE),getAliasSetLabelImage())),names_));
        _m.addEntry(getAliasButton(),merge(new CustList<KeyValueMemberName>(),names_));
        _m.addEntry(getAliasProgBar(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PROG_BAR_OR),getAliasProgBarOr()),
                new KeyValueMemberName(_mapping.getVal(PROG_BAR_VALUE),getAliasProgBarValue()),
                new KeyValueMemberName(_mapping.getVal(PROG_BAR_MIN),getAliasProgBarMin()),
                new KeyValueMemberName(_mapping.getVal(PROG_BAR_MAX),getAliasProgBarMax())),names_));
        _m.addEntry(getAliasScrollPane(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_HORIZONTAL_VALUE),getAliasScrollPaneHorizontalValue()),
                new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_VERTICAL_VALUE),getAliasScrollPaneVerticalValue()),
                new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_GET_VIEW),getAliasScrollPaneGetView()),
                new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_SET_VIEW),getAliasScrollPaneSetView()),
                new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_VALIDATE),getAliasScrollPaneValidate())),names_));
        _m.addEntry(getAliasSplitPane(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_GET_DIVIDER_LOCATION),getAliasSplitPaneGetDividerLocation()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_SET_DIVIDER_LOCATION),getAliasSplitPaneSetDividerLocation()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_GET_DIVIDER_SIZE),getAliasSplitPaneGetDividerSize()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_SET_DIVIDER_SIZE),getAliasSplitPaneSetDividerSize()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_GET_LEFT),getAliasSplitPaneGetLeft()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_SET_LEFT),getAliasSplitPaneSetLeft()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_GET_RIGHT),getAliasSplitPaneGetRight()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_SET_RIGHT),getAliasSplitPaneSetRight()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_IS_CONTINUOUS_LAYOUT),getAliasSplitPaneIsContinuousLayout()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_SET_CONTINUOUS_LAYOUT),getAliasSplitPaneSetContinuousLayout()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE),getAliasSplitPaneIsOneTouchExpandable()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE),getAliasSplitPaneSetOneTouchExpandable()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_VALIDATE),getAliasSplitPaneValidate())),names_));
        _m.addEntry(getAliasInput(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(INPUT_IS_ENABLED),getAliasInputIsEnabled()),
                new KeyValueMemberName(_mapping.getVal(INPUT_SET_ENABLED),getAliasInputSetEnabled())),names_));
        _m.addEntry(getAliasCheckBox(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CHECK_BOX_ADD_ACTION),getAliasCheckBoxAddAction()),
                new KeyValueMemberName(_mapping.getVal(CHECK_BOX_GET_TEXT),getAliasCheckBoxGetText()),
                new KeyValueMemberName(_mapping.getVal(CHECK_BOX_SET_TEXT),getAliasCheckBoxSetText()),
                new KeyValueMemberName(_mapping.getVal(CHECK_BOX_IS_SELECTED),getAliasCheckBoxIsSelected()),
                new KeyValueMemberName(_mapping.getVal(CHECK_BOX_SET_SELECTED),getAliasCheckBoxSetSelected())),names_)
        );
        _m.addEntry(getAliasSpinner(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SPINNER_GET_MAX),getAliasSpinnerGetMax()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_GET_MIN),getAliasSpinnerGetMin()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_GET_STEP),getAliasSpinnerGetStep()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_GET_VALUE),getAliasSpinnerGetValue()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_SET_MAX),getAliasSpinnerSetMax()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_SET_MIN),getAliasSpinnerSetMin()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_SET_STEP),getAliasSpinnerSetStep()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_SET_VALUE),getAliasSpinnerSetValue()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_SET_RANGE),getAliasSpinnerSetRange()),
                new KeyValueMemberName(_mapping.getVal(SPINNER_SET_RANGE_VALUE),getAliasSpinnerSetRangeValue()),
                new KeyValueMemberName(_mapping.getVal(ADD_CHANGE),getAliasAddChange())),names_)
        );
        _m.addEntry(getAliasSlider(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(SLIDER_GET_MAX),getAliasSliderGetMax()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_GET_MIN),getAliasSliderGetMin()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_GET_ORIENTATION),getAliasSliderGetOrientation()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_GET_VALUE),getAliasSliderGetValue()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_SET_MAX),getAliasSliderSetMax()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_SET_MIN),getAliasSliderSetMin()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_SET_ORIENTATION),getAliasSliderSetOrientation()),
                new KeyValueMemberName(_mapping.getVal(SLIDER_SET_VALUE),getAliasSliderSetValue()),
                new KeyValueMemberName(_mapping.getVal(ADD_CHANGE),getAliasAddChange())),names_)
        );
        _m.addEntry(getAliasRadio(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(RADIO_GET_TEXT),getAliasRadioGetText()),
                new KeyValueMemberName(_mapping.getVal(RADIO_SET_TEXT),getAliasRadioSetText()),
                new KeyValueMemberName(_mapping.getVal(RADIO_IS_SELECTED),getAliasRadioIsSelected()),
                new KeyValueMemberName(_mapping.getVal(RADIO_SET_SELECTED),getAliasRadioSetSelected()),
                new KeyValueMemberName(_mapping.getVal(ADD_CHANGE),getAliasAddChange())),names_)
        );
        _m.addEntry(getAliasTextField(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_ADD_ACTION),getAliasTextFieldAddAction()),
                new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_ADD_POPUP),getAliasTextFieldAddPopup()),
                new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_GET_TEXT),getAliasTextFieldGetText()),
                new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_SET_TEXT),getAliasTextFieldSetText())),names_)
        );
        _m.addEntry(getAliasTextArea(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_APPEND),getAliasTextAreaAppend()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_INSERT),getAliasTextAreaInsert()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_REPLACE_RANGE),getAliasTextAreaReplaceRange()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_REPLACE_SELECTION),getAliasTextAreaReplaceSelection()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_GET_SELECTED_TEXT),getAliasTextAreaGetSelectedText()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_SET_SELECTION_START),getAliasTextAreaSetSelectionStart()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_SET_SELECTION_END),getAliasTextAreaSetSelectionEnd()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_GET_TAB_SIZE),getAliasTextAreaGetTabSize()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_SET_TAB_SIZE),getAliasTextAreaSetTabSize()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_GET_TEXT),getAliasTextAreaGetText()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_SET_TEXT),getAliasTextAreaSetText()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_SELECT),getAliasTextAreaSelect()),
                new KeyValueMemberName(_mapping.getVal(TEXT_AREA_SELECT_ALL),getAliasTextAreaSelectAll())),names_)
        );
        _m.addEntry(getAliasCombo(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(COMBO_ADD_ITEM),getAliasComboAddItem()),
                new KeyValueMemberName(_mapping.getVal(COMBO_GET_ITEM_COUNT),getAliasComboGetItemCount()),
                new KeyValueMemberName(_mapping.getVal(COMBO_GET_LISTENERS),getAliasComboGetListeners()),
                new KeyValueMemberName(_mapping.getVal(COMBO_GET_SELECTED_INDEX),getAliasComboGetSelectedIndex()),
                new KeyValueMemberName(_mapping.getVal(COMBO_GET_SELECTED_INDEXES),getAliasComboGetSelectedIndexes()),
                new KeyValueMemberName(_mapping.getVal(COMBO_GET_SELECTED_ITEM),getAliasComboGetSelectedItem()),
                new KeyValueMemberName(_mapping.getVal(COMBO_REMOVE_ALL_ITEMS),getAliasComboRemoveAllItems()),
                new KeyValueMemberName(_mapping.getVal(COMBO_REMOVE_ITEM),getAliasComboRemoveItem()),
                new KeyValueMemberName(_mapping.getVal(COMBO_SELECT_ITEM),getAliasComboSelectItem()),
                new KeyValueMemberName(_mapping.getVal(COMBO_ADD_LISTENER),getAliasComboAddListener()),
                new KeyValueMemberName(_mapping.getVal(COMBO_REMOVE_LISTENER),getAliasComboRemoveListener())),names_)
        );
        _m.addEntry(getAliasGrList(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GR_LIST_ADD),getAliasGrListAdd()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_CLEAR),getAliasGrListClear()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_CLEAR_SELECTION),getAliasGrListClearSelection()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_GET_LIST_VIEW),getAliasGrListGetListView()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_GET_RENDER),getAliasGrListGetRender()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_GET_SELECTED_INDEXES),getAliasGrListGetSelectedIndexes()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_GET_SELECTIONS),getAliasGrListGetSelections()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_GET_VISIBLE_ROW_COUNT),getAliasGrListGetVisibleRowCount()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_REMOVE),getAliasGrListRemove()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_SET),getAliasGrListSet()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_SET_RENDER),getAliasGrListSetRender()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_SET_SELECTED_INDEXES),getAliasGrListSetSelectedIndexes()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_ADD_SELECTION),getAliasGrListAddSelection()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_REMOVE_SELECTION),getAliasGrListRemoveSelection()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_SET_VISIBLE_ROW_COUNT),getAliasGrListSetVisibleRowCount()),
                new KeyValueMemberName(_mapping.getVal(GR_LIST_UPDATE_GRAPHICS),getAliasGrListUpdateGraphics())),names_)
        );
        _m.addEntry(getAliasPopupMenu(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_ADD),getAliasPopupMenuAdd()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_ADD_MENU),getAliasPopupMenuAddMenu()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_GET_COMP),getAliasPopupMenuGetComp()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_REMOVE_COMP),getAliasPopupMenuRemoveComp()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_NB_COMP),getAliasPopupMenuNbComp()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_GET_MENU),getAliasPopupMenuGetMenu()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_REMOVE_MENU),getAliasPopupMenuRemoveMenu()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_NB_MENU),getAliasPopupMenuNbMenu()),
                new KeyValueMemberName(_mapping.getVal(POPUP_MENU_SHOW),getAliasPopupMenuShow())),names_)
        );
        _m.addEntry(getAliasTree(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TREE_ADD_TREE_LISTENER),getAliasTreeAddTreeListener()),
                new KeyValueMemberName(_mapping.getVal(TREE_GET_SELECTED),getAliasTreeGetSelected()),
                new KeyValueMemberName(_mapping.getVal(TREE_IS_ROOT_VISIBLE),getAliasTreeIsRootVisible()),
                new KeyValueMemberName(_mapping.getVal(TREE_SET_ROOT_VISIBLE),getAliasTreeSetRootVisible()),
                new KeyValueMemberName(_mapping.getVal(TREE_RELOAD),getAliasTreeReload())),names_)
        );
        _m.addEntry(getAliasTableGui(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TABLE_ADD_HEADER),getAliasTableAddHeader()),
                new KeyValueMemberName(_mapping.getVal(TABLE_ADD_SELECT),getAliasTableAddSelect()),
                new KeyValueMemberName(_mapping.getVal(TABLE_APPLY_CHANGES),getAliasTableApplyChanges()),
                new KeyValueMemberName(_mapping.getVal(TABLE_ADD_INTERVAL),getAliasTableAddInterval()),
                new KeyValueMemberName(_mapping.getVal(TABLE_REMOVE_INTERVAL),getAliasTableRemoveInterval()),
                new KeyValueMemberName(_mapping.getVal(TABLE_MOVE_COLUMN),getAliasTableMoveColumn()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_COLUMN_AT_POINT),getAliasTableGetColumnAtPoint()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_COLUMN_COUNT),getAliasTableGetColumnCount()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_COLUMN_NAME),getAliasTableGetColumnName()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_ROW_AT_POINT),getAliasTableGetRowAtPoint()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_ROW_COUNT),getAliasTableGetRowCount()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_SELECTED_ROW),getAliasTableGetSelectedRow()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_SELECTED_ROW_COUNT),getAliasTableGetSelectedRowCount()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_SELECTED_ROWS),getAliasTableGetSelectedRows()),
                new KeyValueMemberName(_mapping.getVal(TABLE_GET_VALUE),getAliasTableGetValue()),
                new KeyValueMemberName(_mapping.getVal(TABLE_SET_COLUMNS),getAliasTableSetColumns()),
                new KeyValueMemberName(_mapping.getVal(TABLE_SET_MULTIPLE),getAliasTableSetMultiple()),
                new KeyValueMemberName(_mapping.getVal(TABLE_SET_REORDER),getAliasTableSetReorder()),
                new KeyValueMemberName(_mapping.getVal(TABLE_SET_ROW_COUNT),getAliasTableSetRowCount()),
                new KeyValueMemberName(_mapping.getVal(TABLE_SET_VALUE),getAliasTableSetValue()),
                new KeyValueMemberName(_mapping.getVal(TABLE_IS_MULTIPLE),getAliasTableIsMultiple()),
                new KeyValueMemberName(_mapping.getVal(TABLE_IS_REORDER),getAliasTableIsReorder())),names_)
        );
    }

    private static CustList<KeyValueMemberName> merge(CustList<KeyValueMemberName> _one, CustList<KeyValueMemberName> _two) {
        CustList<KeyValueMemberName> all_ = new CustList<KeyValueMemberName>();
        all_.addAllElts(_one);
        all_.addAllElts(_two);
        return all_;
    }
    private CustList<KeyValueMemberName> getCompoMethodNames(StringMap<String> _mapping) {
        return new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_PARENT_COMPO),getAliasGetParentCompo()),
                new KeyValueMemberName(_mapping.getVal(GET_NEXT_COMPO),getAliasGetNextCompo()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_REPAINT),getAliasComponentRepaint()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_GET_PAINT),getAliasComponentGetPaint()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_SET_PAINT),getAliasComponentSetPaint()),
                new KeyValueMemberName(_mapping.getVal(GET_FONT),getAliasGetFont()),
                new KeyValueMemberName(_mapping.getVal(SET_FONT),getAliasSetFont()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_GET_HEIGHT),getAliasComponentGetHeight()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_GET_WIDTH),getAliasComponentGetWidth()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_IS_AUTOSCROLLS),getAliasComponentIsAutoscrolls()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_SET_AUTOSCROLLS),getAliasComponentSetAutoscrolls()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_GET_PREFERRED_SIZE),getAliasComponentGetPreferredSize()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_SET_PREFERRED_SIZE),getAliasComponentSetPreferredSize()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_SET_SIZE),getAliasComponentSetSize()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_IS_VISIBLE),getAliasComponentIsVisible()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_SET_VISIBLE),getAliasComponentSetVisible()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_INVOKE_LATER),getAliasComponentInvokeLater()),
                new KeyValueMemberName(_mapping.getVal(ADD_KEY_LISTENER),getAliasAddKeyListener()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_KEY_LISTENER),getAliasRemoveKeyListener()),
                new KeyValueMemberName(_mapping.getVal(GET_KEY_LISTENERS),getAliasGetKeyListeners()),
                new KeyValueMemberName(_mapping.getVal(ADD_WHEEL_LISTENER),getAliasAddWheelListener()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_WHEEL_LISTENER),getAliasRemoveWheelListener()),
                new KeyValueMemberName(_mapping.getVal(GET_WHEEL_LISTENERS),getAliasGetWheelListeners()),
                new KeyValueMemberName(_mapping.getVal(ADD_LISTENER),getAliasAddListener()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_LISTENER),getAliasRemoveListener()),
                new KeyValueMemberName(_mapping.getVal(GET_LISTENERS),getAliasGetListeners()),
                new KeyValueMemberName(_mapping.getVal(REQUEST_FOCUS),getAliasRequestFocus()),
                new KeyValueMemberName(_mapping.getVal(COMP_BACK),getAliasCompBack()),
                new KeyValueMemberName(_mapping.getVal(COMPO_REL_LEFT),getAliasCompoRelLeft()),
                new KeyValueMemberName(_mapping.getVal(COMPO_REL_RIGHT),getAliasCompoRelRight()),
                new KeyValueMemberName(_mapping.getVal(COMPO_REL_TOP),getAliasCompoRelTop()),
                new KeyValueMemberName(_mapping.getVal(COMPO_REL_BOTTOM),getAliasCompoRelBottom()),
                new KeyValueMemberName(_mapping.getVal(COMPO_REL_CENT_HORIZ),getAliasCompoRelCentHoriz()),
                new KeyValueMemberName(_mapping.getVal(COMPO_REL_CENT_VERT),getAliasCompoRelCentVert()),
                new KeyValueMemberName(_mapping.getVal(COMP_FOCUSABLE),getAliasCompFocusable()),
                new KeyValueMemberName(_mapping.getVal(COMP_FORE),getAliasCompFore()),
                new KeyValueMemberName(_mapping.getVal(COMP_GET_FIRST_POS),getAliasCompGetFirstPos()),
                new KeyValueMemberName(_mapping.getVal(COMP_GET_SECOND_POS),getAliasCompGetSecondPos()),
                new KeyValueMemberName(_mapping.getVal(COMP_OPAQUE),getAliasCompOpaque()),
                new KeyValueMemberName(_mapping.getVal(COMP_TOOL_TIP),getAliasCompToolTip()),
                new KeyValueMemberName(_mapping.getVal(COMP_LOC),getAliasCompLoc()),
                new KeyValueMemberName(_mapping.getVal(COMP_BOR_LINE),getAliasCompBorLine()),
                new KeyValueMemberName(_mapping.getVal(COMP_BOR_LOWER),getAliasCompBorLower()),
                new KeyValueMemberName(_mapping.getVal(COMP_BOR_RAISE),getAliasCompBorRaise()),
                new KeyValueMemberName(_mapping.getVal(COMP_BOR_TITLE),getAliasCompBorTitle()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_BIND),getAliasComponentBind()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_UNBIND),getAliasComponentUnbind()),
                new KeyValueMemberName(_mapping.getVal(COMPONENT_COMMANDS),getAliasComponentCommands())
        );
    }

    private void allEventTypes(StringMap<String> _mapping, StringMap<CustList<KeyValueMemberName>> _m) {
        _m.addEntry(getAliasActionEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_IS_ALT),getAliasActionEventIsAlt()),
                new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_IS_SHIFT),getAliasActionEventIsShift()),
                new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_IS_CTRL),getAliasActionEventIsCtrl()),
                new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_COMMAND),getAliasActionEventCommand())));
        _m.addEntry(getAliasMouseEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_GET_FIRST),getAliasMouseEventGetFirst()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_GET_SECOND),getAliasMouseEventGetSecond()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_GET_CLICKS),getAliasMouseEventGetClicks()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_ALT),getAliasMouseEventIsAlt()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_SHIFT),getAliasMouseEventIsShift()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_CTRL),getAliasMouseEventIsCtrl()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_LEFT),getAliasMouseEventIsLeft()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_RIGHT),getAliasMouseEventIsRight()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_MIDDLE),getAliasMouseEventIsMiddle())));
        _m.addEntry(getAliasWheelEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_GET_FIRST),getAliasMouseEventGetFirst()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_GET_SECOND),getAliasMouseEventGetSecond()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_GET_CLICKS),getAliasMouseEventGetClicks()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_ALT),getAliasMouseEventIsAlt()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_SHIFT),getAliasMouseEventIsShift()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_CTRL),getAliasMouseEventIsCtrl()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_LEFT),getAliasMouseEventIsLeft()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_RIGHT),getAliasMouseEventIsRight()),
                new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_IS_MIDDLE),getAliasMouseEventIsMiddle()),
                new KeyValueMemberName(_mapping.getVal(WHEEL_ROTATED_CLICKS),getAliasWheelRotatedClicks()))
        );
        _m.addEntry(getAliasKeyEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(KEY_EVENT_CODE),getAliasKeyEventCode()),
                new KeyValueMemberName(_mapping.getVal(KEY_EVENT_CHAR),getAliasKeyEventChar()),
                new KeyValueMemberName(_mapping.getVal(KEY_EVENT_IS_ALT),getAliasKeyEventIsAlt()),
                new KeyValueMemberName(_mapping.getVal(KEY_EVENT_IS_SHIFT),getAliasKeyEventIsShift()),
                new KeyValueMemberName(_mapping.getVal(KEY_EVENT_IS_CTRL),getAliasKeyEventIsCtrl())));
    }

    private void allWindowMethods(StringMap<String> _mapping, StringMap<CustList<KeyValueMemberName>> _m) {
        _m.addEntry(getAliasConfirm(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CONFIRM_FIELD),getAliasConfirmField()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_FULL),getAliasConfirmFull()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_MESSAGE),getAliasConfirmMessage()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_OK),getAliasConfirmOk()),
                new KeyValueMemberName(_mapping.getVal(CONFIRM_YES_NO),getAliasConfirmYesNo())));
        _m.addEntry(getAliasFrame(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PACK),getAliasPack()),
                new KeyValueMemberName(_mapping.getVal(ADD_WINDOW_LISTENER),getAliasAddWindowListener()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_WINDOW_LISTENER),getAliasRemoveWindowListener()),
                new KeyValueMemberName(_mapping.getVal(GET_WINDOW_LISTENERS),getAliasGetWindowListeners()),
                new KeyValueMemberName(_mapping.getVal(DISPOSE),getAliasDispose()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_RELATIVE),getAliasWindowTypeRelative()),
                new KeyValueMemberName(_mapping.getVal(IS_VISIBLE),getAliasIsVisible()),
                new KeyValueMemberName(_mapping.getVal(SET_VISIBLE),getAliasSetVisible()),
                new KeyValueMemberName(_mapping.getVal(SET_CONTENT),getAliasSetContent()),
                new KeyValueMemberName(_mapping.getVal(GET_MENU_BAR),getAliasGetMenuBar()),
                new KeyValueMemberName(_mapping.getVal(SET_MENU_BAR),getAliasSetMenuBar()),
                new KeyValueMemberName(_mapping.getVal(WINDOW),getAliasWindow())));
        _m.addEntry(getAliasWindowSet(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(WINDOW_SET_ADD),getAliasWindowSetAdd()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_SET_ALL),getAliasWindowSetAll()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_SET_CONTAINS),getAliasWindowSetContains()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_SET_REMOVE),getAliasWindowSetRemove()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_SET_SNAPSHOT),getAliasWindowSetSnapshot())));
        _m.addEntry(getAliasDialog(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PACK),getAliasPack()),
                new KeyValueMemberName(_mapping.getVal(ADD_WINDOW_LISTENER),getAliasAddWindowListener()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_WINDOW_LISTENER),getAliasRemoveWindowListener()),
                new KeyValueMemberName(_mapping.getVal(GET_WINDOW_LISTENERS),getAliasGetWindowListeners()),
                new KeyValueMemberName(_mapping.getVal(DISPOSE),getAliasDispose()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_RELATIVE),getAliasWindowTypeRelative()),
                new KeyValueMemberName(_mapping.getVal(IS_VISIBLE),getAliasIsVisible()),
                new KeyValueMemberName(_mapping.getVal(SET_VISIBLE),getAliasSetVisible()),
                new KeyValueMemberName(_mapping.getVal(SET_CONTENT),getAliasSetContent()),
                new KeyValueMemberName(_mapping.getVal(GET_MENU_BAR),getAliasGetMenuBar()),
                new KeyValueMemberName(_mapping.getVal(SET_MENU_BAR),getAliasSetMenuBar()),
                new KeyValueMemberName(_mapping.getVal(DIALOG_IS_MODAL),getAliasDialogIsModal()),
                new KeyValueMemberName(_mapping.getVal(DIALOG_SET_MODAL),getAliasDialogSetModal())));
        _m.addEntry(getAliasWindowType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PACK),getAliasPack()),
                new KeyValueMemberName(_mapping.getVal(ADD_WINDOW_LISTENER),getAliasAddWindowListener()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_WINDOW_LISTENER),getAliasRemoveWindowListener()),
                new KeyValueMemberName(_mapping.getVal(GET_WINDOW_LISTENERS),getAliasGetWindowListeners()),
                new KeyValueMemberName(_mapping.getVal(DISPOSE),getAliasDispose()),
                new KeyValueMemberName(_mapping.getVal(CLOSE_ALL),getAliasCloseAll()),
                new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_RELATIVE),getAliasWindowTypeRelative()),
                new KeyValueMemberName(_mapping.getVal(IS_VISIBLE),getAliasIsVisible()),
                new KeyValueMemberName(_mapping.getVal(SET_VISIBLE),getAliasSetVisible()),
                new KeyValueMemberName(_mapping.getVal(SET_CONTENT),getAliasSetContent()),
                new KeyValueMemberName(_mapping.getVal(GET_MENU_BAR),getAliasGetMenuBar()),
                new KeyValueMemberName(_mapping.getVal(SET_MENU_BAR),getAliasSetMenuBar())));
    }

    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ = new StringMap<String>();
        ref_.addEntry(_mapping.getVal(WINDOW_SET),getAliasWindowSet());
        ref_.addEntry(_mapping.getVal(FRAME),getAliasFrame());
        ref_.addEntry(_mapping.getVal(CONFIRM),getAliasConfirm());
        ref_.addEntry(_mapping.getVal(DIALOG),getAliasDialog());
        ref_.addEntry(_mapping.getVal(WINDOW_TYPE),getAliasWindowType());
        ref_.addEntry(_mapping.getVal(COMPONENT),getAliasComponent());
        ref_.addEntry(_mapping.getVal(ACTION_EVENT),getAliasActionEvent());
        ref_.addEntry(_mapping.getVal(MOUSE_EVENT),getAliasMouseEvent());
        ref_.addEntry(_mapping.getVal(TABLE_LISTENER),getAliasTableListener());
        ref_.addEntry(_mapping.getVal(TABLE_GUI),getAliasTableGui());
        ref_.addEntry(_mapping.getVal(TREE_LISTENER),getAliasTreeListener());
        ref_.addEntry(_mapping.getVal(TREE),getAliasTree());
        ref_.addEntry(_mapping.getVal(TREE_NODE),getAliasTreeNode());
        ref_.addEntry(_mapping.getVal(KEY_EVENT),getAliasKeyEvent());
        ref_.addEntry(_mapping.getVal(WINDOW_EVENT),getAliasWindowEvent());
        ref_.addEntry(_mapping.getVal(PANEL),getAliasPanel());
        ref_.addEntry(_mapping.getVal(TABBED_PANE),getAliasTabbedPane());
        ref_.addEntry(_mapping.getVal(PANEL_BORDER),getAliasPanelBorder());
        ref_.addEntry(_mapping.getVal(BUTTON),getAliasButton());
        ref_.addEntry(_mapping.getVal(PROG_BAR),getAliasProgBar());
        ref_.addEntry(_mapping.getVal(CHECK_BOX),getAliasCheckBox());
        ref_.addEntry(_mapping.getVal(RADIO),getAliasRadio());
        ref_.addEntry(_mapping.getVal(TEXT_LABEL),getAliasTextLabel());
        ref_.addEntry(_mapping.getVal(IMAGE),getAliasImage());
        ref_.addEntry(_mapping.getVal(IMAGE_LABEL),getAliasImageLabel());
        ref_.addEntry(_mapping.getVal(COLOR),getAliasColor());
        ref_.addEntry(_mapping.getVal(INPUT),getAliasInput());
        ref_.addEntry(_mapping.getVal(FONT),getAliasFont());
        ref_.addEntry(_mapping.getVal(TEXT_AREA),getAliasTextArea());
        ref_.addEntry(_mapping.getVal(TEXT_FIELD),getAliasTextField());
        ref_.addEntry(_mapping.getVal(GR_LIST),getAliasGrList());
        ref_.addEntry(_mapping.getVal(COMBO),getAliasCombo());
        ref_.addEntry(_mapping.getVal(BUTTON_GROUP),getAliasButtonGroup());
        ref_.addEntry(_mapping.getVal(RENDER),getAliasRender());
        ref_.addEntry(_mapping.getVal(POPUP_MENU),getAliasPopupMenu());
        ref_.addEntry(_mapping.getVal(DIMENSION),getAliasDimension());
        ref_.addEntry(_mapping.getVal(KEY_LISTENER),getAliasKeyListener());
        ref_.addEntry(_mapping.getVal(MOUSE_LISTENER),getAliasMouseListener());
        ref_.addEntry(_mapping.getVal(WHEEL_LISTENER),getAliasWheelListener());
        ref_.addEntry(_mapping.getVal(WHEEL_EVENT),getAliasWheelEvent());
        ref_.addEntry(_mapping.getVal(ACTION_LISTENER),getAliasActionListener());
        ref_.addEntry(_mapping.getVal(ACTION),getAliasAction());
        ref_.addEntry(_mapping.getVal(CHANGE_LISTENER),getAliasChangeListener());
        ref_.addEntry(_mapping.getVal(WINDOW_LISTENER),getAliasWindowListener());
        ref_.addEntry(_mapping.getVal(SCROLL_PANE),getAliasScrollPane());
        ref_.addEntry(_mapping.getVal(SPLIT_PANE),getAliasSplitPane());
        ref_.addEntry(_mapping.getVal(LIST_SELECTION),getAliasListSelection());
        ref_.addEntry(_mapping.getVal(PAINT),getAliasPaint());
        ref_.addEntry(_mapping.getVal(MENU_BAR),getAliasMenuBar());
        ref_.addEntry(_mapping.getVal(ABS_MENU),getAliasAbsMenu());
        ref_.addEntry(_mapping.getVal(MENU),getAliasMenu());
        ref_.addEntry(_mapping.getVal(ABS_MENU_ITEM),getAliasAbsMenuItem());
        ref_.addEntry(_mapping.getVal(MENU_ITEM),getAliasMenuItem());
        ref_.addEntry(_mapping.getVal(MENU_ITEM_CHECK),getAliasMenuItemCheck());
        ref_.addEntry(_mapping.getVal(SPINNER),getAliasSpinner());
        ref_.addEntry(_mapping.getVal(SLIDER),getAliasSlider());
        ref_.addEntry(_mapping.getVal(COMMAND),getAliasCommand());
        return ref_;
    }
    private static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _args) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWordsValues();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        for (String p:_args) {
            allKeysWords_.add(p);
        }
        return ValidatorStandard.tr(allKeysWords_,_var);
    }
    public String getAliasActionListener() {
        return aliasActionListener;
    }

    public void setAliasActionListener(String _v) {
        this.aliasActionListener = _v;
    }

    public String getAliasAction() {
        return aliasAction;
    }

    public void setAliasAction(String _v) {
        this.aliasAction = _v;
    }

    public String getAliasActionWrap() {
        return aliasActionWrap;
    }

    public void setAliasActionWrap(String _v) {
        this.aliasActionWrap = _v;
    }

    public String getAliasActionEnabled() {
        return aliasActionEnabled;
    }

    public void setAliasActionEnabled(String _v) {
        this.aliasActionEnabled = _v;
    }

    public String getAliasActionArg() {
        return aliasActionArg;
    }

    public void setAliasActionArg(String _v) {
        this.aliasActionArg = _v;
    }

    public String getAliasActionPerformed() {
        return aliasActionPerformed;
    }

    public void setAliasActionPerformed(String _v) {
        this.aliasActionPerformed = _v;
    }

    public String getAliasActionEvent() {
        return aliasActionEvent;
    }

    public void setAliasActionEvent(String _v) {
        this.aliasActionEvent = _v;
    }

    public String getAliasActionEventIsAlt() {
        return aliasActionEventIsAlt;
    }

    public void setAliasActionEventIsAlt(String _v) {
        this.aliasActionEventIsAlt = _v;
    }

    public String getAliasActionEventIsCtrl() {
        return aliasActionEventIsCtrl;
    }

    public void setAliasActionEventIsCtrl(String _v) {
        this.aliasActionEventIsCtrl = _v;
    }

    public String getAliasActionEventIsShift() {
        return aliasActionEventIsShift;
    }

    public void setAliasActionEventIsShift(String _v) {
        this.aliasActionEventIsShift = _v;
    }

    public String getAliasActionEventCommand() {
        return aliasActionEventCommand;
    }

    public void setAliasActionEventCommand(String _v) {
        this.aliasActionEventCommand = _v;
    }

    public String getAliasAddChange() {
        return aliasAddChange;
    }

    public void setAliasAddChange(String _v) {
        this.aliasAddChange = _v;
    }

    public String getAliasChangeListener() {
        return aliasChangeListener;
    }

    public void setAliasChangeListener(String _v) {
        this.aliasChangeListener = _v;
    }

    public String getAliasStateChanged() {
        return aliasStateChanged;
    }

    public void setAliasStateChanged(String _v) {
        this.aliasStateChanged = _v;
    }

    public String getAliasTreeListener() {
        return aliasTreeListener;
    }

    public void setAliasTreeListener(String _v) {
        this.aliasTreeListener = _v;
    }

    public String getAliasTreeListenerValueChanged() {
        return aliasTreeListenerValueChanged;
    }

    public void setAliasTreeListenerValueChanged(String _v) {
        this.aliasTreeListenerValueChanged = _v;
    }

    public String getAliasTreeNode() {
        return aliasTreeNode;
    }

    public void setAliasTreeNode(String _v) {
        this.aliasTreeNode = _v;
    }

    public String getAliasTreeNodeAdd() {
        return aliasTreeNodeAdd;
    }

    public void setAliasTreeNodeAdd(String _v) {
        this.aliasTreeNodeAdd = _v;
    }

    public String getAliasTreeNodeInsert() {
        return aliasTreeNodeInsert;
    }

    public void setAliasTreeNodeInsert(String _v) {
        this.aliasTreeNodeInsert = _v;
    }

    public String getAliasTreeNodeRemove() {
        return aliasTreeNodeRemove;
    }

    public void setAliasTreeNodeRemove(String _v) {
        this.aliasTreeNodeRemove = _v;
    }

    public String getAliasTreeNodeRemoveFromParent() {
        return aliasTreeNodeRemoveFromParent;
    }

    public void setAliasTreeNodeRemoveFromParent(String _v) {
        this.aliasTreeNodeRemoveFromParent = _v;
    }

    public String getAliasTreeNodeRemoveAllChildren() {
        return aliasTreeNodeRemoveAllChildren;
    }

    public void setAliasTreeNodeRemoveAllChildren(String _v) {
        this.aliasTreeNodeRemoveAllChildren = _v;
    }

    public String getAliasTreeNodeSetUserObject() {
        return aliasTreeNodeSetUserObject;
    }

    public void setAliasTreeNodeSetUserObject(String _v) {
        this.aliasTreeNodeSetUserObject = _v;
    }

    public String getAliasTreeNodeNb() {
        return aliasTreeNodeNb;
    }

    public void setAliasTreeNodeNb(String _v) {
        this.aliasTreeNodeNb = _v;
    }

    public String getAliasTreeNodeEq() {
        return aliasTreeNodeEq;
    }

    public void setAliasTreeNodeEq(String _v) {
        this.aliasTreeNodeEq = _v;
    }

    public String getAliasTreeNodeGetFirstChild() {
        return aliasTreeNodeGetFirstChild;
    }

    public void setAliasTreeNodeGetFirstChild(String _v) {
        this.aliasTreeNodeGetFirstChild = _v;
    }

    public String getAliasTreeNodeGetLastChild() {
        return aliasTreeNodeGetLastChild;
    }

    public void setAliasTreeNodeGetLastChild(String _v) {
        this.aliasTreeNodeGetLastChild = _v;
    }

    public String getAliasTreeNodeGetNextSibling() {
        return aliasTreeNodeGetNextSibling;
    }

    public void setAliasTreeNodeGetNextSibling(String _v) {
        this.aliasTreeNodeGetNextSibling = _v;
    }

    public String getAliasTreeNodeGetPreviousSibling() {
        return aliasTreeNodeGetPreviousSibling;
    }

    public void setAliasTreeNodeGetPreviousSibling(String _v) {
        this.aliasTreeNodeGetPreviousSibling = _v;
    }

    public String getAliasTreeNodeGetParentNode() {
        return aliasTreeNodeGetParentNode;
    }

    public void setAliasTreeNodeGetParentNode(String _v) {
        this.aliasTreeNodeGetParentNode = _v;
    }

    public String getAliasTreeNodeGetUserObject() {
        return aliasTreeNodeGetUserObject;
    }

    public void setAliasTreeNodeGetUserObject(String _v) {
        this.aliasTreeNodeGetUserObject = _v;
    }

    public String getAliasTreeNodeIsAncestor() {
        return aliasTreeNodeIsAncestor;
    }

    public void setAliasTreeNodeIsAncestor(String _v) {
        this.aliasTreeNodeIsAncestor = _v;
    }

    public String getAliasTreeNodeIsDescendant() {
        return aliasTreeNodeIsDescendant;
    }

    public void setAliasTreeNodeIsDescendant(String _v) {
        this.aliasTreeNodeIsDescendant = _v;
    }

    public String getAliasTree() {
        return aliasTree;
    }

    public void setAliasTree(String _v) {
        this.aliasTree = _v;
    }

    public String getAliasTreeSetRootVisible() {
        return aliasTreeSetRootVisible;
    }

    public void setAliasTreeSetRootVisible(String _v) {
        this.aliasTreeSetRootVisible = _v;
    }

    public String getAliasTreeIsRootVisible() {
        return aliasTreeIsRootVisible;
    }

    public void setAliasTreeIsRootVisible(String _v) {
        this.aliasTreeIsRootVisible = _v;
    }

    public String getAliasTreeGetSelected() {
        return aliasTreeGetSelected;
    }

    public void setAliasTreeGetSelected(String _v) {
        this.aliasTreeGetSelected = _v;
    }

    public String getAliasTreeAddTreeListener() {
        return aliasTreeAddTreeListener;
    }

    public void setAliasTreeAddTreeListener(String _v) {
        this.aliasTreeAddTreeListener = _v;
    }

    public String getAliasTreeReload() {
        return aliasTreeReload;
    }

    public void setAliasTreeReload(String _v) {
        this.aliasTreeReload = _v;
    }

    public String getAliasTableListener() {
        return aliasTableListener;
    }

    public void setAliasTableListener(String _v) {
        this.aliasTableListener = _v;
    }

    public String getAliasTableValueTableChanged() {
        return aliasTableValueTableChanged;
    }

    public void setAliasTableValueTableChanged(String _v) {
        this.aliasTableValueTableChanged = _v;
    }

    public String getAliasTableGui() {
        return aliasTableGui;
    }

    public void setAliasTableGui(String _v) {
        this.aliasTableGui = _v;
    }

    public String getAliasTableGetSelectedRow() {
        return aliasTableGetSelectedRow;
    }

    public void setAliasTableGetSelectedRow(String _v) {
        this.aliasTableGetSelectedRow = _v;
    }

    public String getAliasTableGetSelectedRows() {
        return aliasTableGetSelectedRows;
    }

    public void setAliasTableGetSelectedRows(String _v) {
        this.aliasTableGetSelectedRows = _v;
    }

    public String getAliasTableGetSelectedRowCount() {
        return aliasTableGetSelectedRowCount;
    }

    public void setAliasTableGetSelectedRowCount(String _v) {
        this.aliasTableGetSelectedRowCount = _v;
    }

    public String getAliasTableGetRowCount() {
        return aliasTableGetRowCount;
    }

    public void setAliasTableGetRowCount(String _v) {
        this.aliasTableGetRowCount = _v;
    }

    public String getAliasTableSetRowCount() {
        return aliasTableSetRowCount;
    }

    public void setAliasTableSetRowCount(String _v) {
        this.aliasTableSetRowCount = _v;
    }

    public String getAliasTableGetColumnCount() {
        return aliasTableGetColumnCount;
    }

    public void setAliasTableGetColumnCount(String _v) {
        this.aliasTableGetColumnCount = _v;
    }

    public String getAliasTableSetColumns() {
        return aliasTableSetColumns;
    }

    public void setAliasTableSetColumns(String _v) {
        this.aliasTableSetColumns = _v;
    }

    public String getAliasTableGetColumnName() {
        return aliasTableGetColumnName;
    }

    public void setAliasTableGetColumnName(String _v) {
        this.aliasTableGetColumnName = _v;
    }

    public String getAliasTableGetValue() {
        return aliasTableGetValue;
    }

    public void setAliasTableGetValue(String _v) {
        this.aliasTableGetValue = _v;
    }

    public String getAliasTableSetValue() {
        return aliasTableSetValue;
    }

    public void setAliasTableSetValue(String _v) {
        this.aliasTableSetValue = _v;
    }

    public String getAliasTableGetRowAtPoint() {
        return aliasTableGetRowAtPoint;
    }

    public void setAliasTableGetRowAtPoint(String _v) {
        this.aliasTableGetRowAtPoint = _v;
    }

    public String getAliasTableGetColumnAtPoint() {
        return aliasTableGetColumnAtPoint;
    }

    public void setAliasTableGetColumnAtPoint(String _v) {
        this.aliasTableGetColumnAtPoint = _v;
    }

    public String getAliasTableIsMultiple() {
        return aliasTableIsMultiple;
    }

    public void setAliasTableIsMultiple(String _v) {
        this.aliasTableIsMultiple = _v;
    }

    public String getAliasTableSetMultiple() {
        return aliasTableSetMultiple;
    }

    public void setAliasTableSetMultiple(String _v) {
        this.aliasTableSetMultiple = _v;
    }

    public String getAliasTableIsReorder() {
        return aliasTableIsReorder;
    }

    public void setAliasTableIsReorder(String _v) {
        this.aliasTableIsReorder = _v;
    }

    public String getAliasTableSetReorder() {
        return aliasTableSetReorder;
    }

    public void setAliasTableSetReorder(String _v) {
        this.aliasTableSetReorder = _v;
    }

    public String getAliasTableMoveColumn() {
        return aliasTableMoveColumn;
    }

    public void setAliasTableMoveColumn(String _v) {
        this.aliasTableMoveColumn = _v;
    }

    public String getAliasTableAddInterval() {
        return aliasTableAddInterval;
    }

    public void setAliasTableAddInterval(String _v) {
        this.aliasTableAddInterval = _v;
    }

    public String getAliasTableRemoveInterval() {
        return aliasTableRemoveInterval;
    }

    public void setAliasTableRemoveInterval(String _v) {
        this.aliasTableRemoveInterval = _v;
    }

    public String getAliasTableApplyChanges() {
        return aliasTableApplyChanges;
    }

    public void setAliasTableApplyChanges(String _v) {
        this.aliasTableApplyChanges = _v;
    }

    public String getAliasTableAddHeader() {
        return aliasTableAddHeader;
    }

    public void setAliasTableAddHeader(String _v) {
        this.aliasTableAddHeader = _v;
    }

    public String getAliasTableAddSelect() {
        return aliasTableAddSelect;
    }

    public void setAliasTableAddSelect(String _v) {
        this.aliasTableAddSelect = _v;
    }

    public String getAliasConfirm() {
        return aliasConfirm;
    }

    public void setAliasConfirm(String _v) {
        this.aliasConfirm = _v;
    }

    public String getAliasConfirmMessage() {
        return aliasConfirmMessage;
    }

    public void setAliasConfirmMessage(String _v) {
        this.aliasConfirmMessage = _v;
    }

    public String getAliasConfirmField() {
        return aliasConfirmField;
    }

    public void setAliasConfirmField(String _v) {
        this.aliasConfirmField = _v;
    }

    public String getAliasConfirmOk() {
        return aliasConfirmOk;
    }

    public void setAliasConfirmOk(String _v) {
        this.aliasConfirmOk = _v;
    }

    public String getAliasConfirmYesNo() {
        return aliasConfirmYesNo;
    }

    public void setAliasConfirmYesNo(String _v) {
        this.aliasConfirmYesNo = _v;
    }

    public String getAliasConfirmFull() {
        return aliasConfirmFull;
    }

    public void setAliasConfirmFull(String _v) {
        this.aliasConfirmFull = _v;
    }

    public String getAliasConfirmFieldOk() {
        return aliasConfirmFieldOk;
    }

    public void setAliasConfirmFieldOk(String _v) {
        this.aliasConfirmFieldOk = _v;
    }

    public String getAliasConfirmFieldCancel() {
        return aliasConfirmFieldCancel;
    }

    public void setAliasConfirmFieldCancel(String _v) {
        this.aliasConfirmFieldCancel = _v;
    }

    public String getAliasConfirmFieldYes() {
        return aliasConfirmFieldYes;
    }

    public void setAliasConfirmFieldYes(String _v) {
        this.aliasConfirmFieldYes = _v;
    }

    public String getAliasConfirmFieldNo() {
        return aliasConfirmFieldNo;
    }

    public void setAliasConfirmFieldNo(String _v) {
        this.aliasConfirmFieldNo = _v;
    }

    public String getAliasFrame() {
        return aliasFrame;
    }

    public void setAliasFrame(String _v) {
        this.aliasFrame = _v;
    }

    public String getAliasDialog() {
        return aliasDialog;
    }

    public void setAliasDialog(String _v) {
        this.aliasDialog = _v;
    }

    public String getAliasDialogIsModal() {
        return aliasDialogIsModal;
    }

    public void setAliasDialogIsModal(String _v) {
        this.aliasDialogIsModal = _v;
    }

    public String getAliasDialogSetModal() {
        return aliasDialogSetModal;
    }

    public void setAliasDialogSetModal(String _v) {
        this.aliasDialogSetModal = _v;
    }

    public String getAliasWindowType() {
        return aliasWindowType;
    }

    public void setAliasWindowType(String _v) {
        this.aliasWindowType = _v;
    }

    public String getAliasWindowTypeRelative() {
        return aliasWindowTypeRelative;
    }

    public void setAliasWindowTypeRelative(String _v) {
        this.aliasWindowTypeRelative = _v;
    }

    public String getAliasRemoveWindowListener() {
        return aliasRemoveWindowListener;
    }

    public void setAliasRemoveWindowListener(String _v) {
        this.aliasRemoveWindowListener = _v;
    }

    public String getAliasGetWindowListeners() {
        return aliasGetWindowListeners;
    }

    public void setAliasGetWindowListeners(String _v) {
        this.aliasGetWindowListeners = _v;
    }

    public String getAliasPanel() {
        return aliasPanel;
    }

    public void setAliasPanel(String _v) {
        this.aliasPanel = _v;
    }

    public String getAliasPanelBorder() {
        return aliasPanelBorder;
    }

    public void setAliasPanelBorder(String _v) {
        this.aliasPanelBorder = _v;
    }

    public String getAliasPanelBorderNorth() {
        return aliasPanelBorderNorth;
    }

    public void setAliasPanelBorderNorth(String _v) {
        this.aliasPanelBorderNorth = _v;
    }

    public String getAliasPanelBorderSouth() {
        return aliasPanelBorderSouth;
    }

    public void setAliasPanelBorderSouth(String _v) {
        this.aliasPanelBorderSouth = _v;
    }

    public String getAliasPanelBorderEast() {
        return aliasPanelBorderEast;
    }

    public void setAliasPanelBorderEast(String _v) {
        this.aliasPanelBorderEast = _v;
    }

    public String getAliasPanelBorderWest() {
        return aliasPanelBorderWest;
    }

    public void setAliasPanelBorderWest(String _v) {
        this.aliasPanelBorderWest = _v;
    }

    public String getAliasPanelBorderCenter() {
        return aliasPanelBorderCenter;
    }

    public void setAliasPanelBorderCenter(String _v) {
        this.aliasPanelBorderCenter = _v;
    }

    public String getAliasPanelBorderBeforeFirst() {
        return aliasPanelBorderBeforeFirst;
    }

    public void setAliasPanelBorderBeforeFirst(String _v) {
        this.aliasPanelBorderBeforeFirst = _v;
    }

    public String getAliasPanelBorderBeforeLineBegins() {
        return aliasPanelBorderBeforeLineBegins;
    }

    public void setAliasPanelBorderBeforeLineBegins(String _v) {
        this.aliasPanelBorderBeforeLineBegins = _v;
    }

    public String getAliasPanelBorderAfterLast() {
        return aliasPanelBorderAfterLast;
    }

    public void setAliasPanelBorderAfterLast(String _v) {
        this.aliasPanelBorderAfterLast = _v;
    }

    public String getAliasPanelBorderAfterLineEnds() {
        return aliasPanelBorderAfterLineEnds;
    }

    public void setAliasPanelBorderAfterLineEnds(String _v) {
        this.aliasPanelBorderAfterLineEnds = _v;
    }

    public String getAliasProgBar() {
        return aliasProgBar;
    }

    public void setAliasProgBar(String _v) {
        this.aliasProgBar = _v;
    }

    public String getAliasProgBarValue() {
        return aliasProgBarValue;
    }

    public void setAliasProgBarValue(String _v) {
        this.aliasProgBarValue = _v;
    }

    public String getAliasProgBarMax() {
        return aliasProgBarMax;
    }

    public void setAliasProgBarMax(String _v) {
        this.aliasProgBarMax = _v;
    }

    public String getAliasProgBarMin() {
        return aliasProgBarMin;
    }

    public void setAliasProgBarMin(String _v) {
        this.aliasProgBarMin = _v;
    }

    public String getAliasProgBarOr() {
        return aliasProgBarOr;
    }

    public void setAliasProgBarOr(String _v) {
        this.aliasProgBarOr = _v;
    }

    public String getAliasButton() {
        return aliasButton;
    }

    public void setAliasButton(String _v) {
        this.aliasButton = _v;
    }

    public String getAliasTextLabel() {
        return aliasTextLabel;
    }

    public void setAliasTextLabel(String _v) {
        this.aliasTextLabel = _v;
    }

    public String getAliasImageLabel() {
        return aliasImageLabel;
    }

    public void setAliasImageLabel(String _v) {
        this.aliasImageLabel = _v;
    }

    public String getAliasGetFont() {
        return aliasGetFont;
    }

    public void setAliasGetFont(String _v) {
        this.aliasGetFont = _v;
    }

    public String getAliasSetFont() {
        return aliasSetFont;
    }

    public void setAliasSetFont(String _v) {
        this.aliasSetFont = _v;
    }

    public String getAliasFont() {
        return aliasFont;
    }

    public void setAliasFont(String _v) {
        this.aliasFont = _v;
    }

    public String getAliasFontGetName() {
        return aliasFontGetName;
    }

    public void setAliasFontGetName(String _v) {
        this.aliasFontGetName = _v;
    }

    public String getAliasFontGetSize() {
        return aliasFontGetSize;
    }

    public void setAliasFontGetSize(String _v) {
        this.aliasFontGetSize = _v;
    }

    public String getAliasFontIsBold() {
        return aliasFontIsBold;
    }

    public void setAliasFontIsBold(String _v) {
        this.aliasFontIsBold = _v;
    }

    public String getAliasFontIsItalic() {
        return aliasFontIsItalic;
    }

    public void setAliasFontIsItalic(String _v) {
        this.aliasFontIsItalic = _v;
    }

    public String getAliasFontStringWidth() {
        return aliasFontStringWidth;
    }

    public void setAliasFontStringWidth(String _v) {
        this.aliasFontStringWidth = _v;
    }

    public String getAliasDimension() {
        return aliasDimension;
    }

    public void setAliasDimension(String _v) {
        this.aliasDimension = _v;
    }

    public String getAliasDimensionGetHeight() {
        return aliasDimensionGetHeight;
    }

    public void setAliasDimensionGetHeight(String _v) {
        this.aliasDimensionGetHeight = _v;
    }

    public String getAliasDimensionGetWidth() {
        return aliasDimensionGetWidth;
    }

    public void setAliasDimensionGetWidth(String _v) {
        this.aliasDimensionGetWidth = _v;
    }

    public String getAliasComponent() {
        return aliasComponent;
    }

    public void setAliasComponent(String _v) {
        this.aliasComponent = _v;
    }

    public String getAliasComponentSetAutoscrolls() {
        return aliasComponentSetAutoscrolls;
    }

    public void setAliasComponentSetAutoscrolls(String _v) {
        this.aliasComponentSetAutoscrolls = _v;
    }

    public String getAliasComponentIsAutoscrolls() {
        return aliasComponentIsAutoscrolls;
    }

    public void setAliasComponentIsAutoscrolls(String _v) {
        this.aliasComponentIsAutoscrolls = _v;
    }

    public String getAliasComponentGetWidth() {
        return aliasComponentGetWidth;
    }

    public void setAliasComponentGetWidth(String _v) {
        this.aliasComponentGetWidth = _v;
    }

    public String getAliasComponentGetHeight() {
        return aliasComponentGetHeight;
    }

    public void setAliasComponentGetHeight(String _v) {
        this.aliasComponentGetHeight = _v;
    }

    public String getAliasComponentGetPreferredSize() {
        return aliasComponentGetPreferredSize;
    }

    public void setAliasComponentGetPreferredSize(String _v) {
        this.aliasComponentGetPreferredSize = _v;
    }

    public String getAliasComponentSetPreferredSize() {
        return aliasComponentSetPreferredSize;
    }

    public void setAliasComponentSetPreferredSize(String _v) {
        this.aliasComponentSetPreferredSize = _v;
    }

    public String getAliasComponentSetSize() {
        return aliasComponentSetSize;
    }

    public void setAliasComponentSetSize(String _v) {
        this.aliasComponentSetSize = _v;
    }

    public String getAliasComponentIsVisible() {
        return aliasComponentIsVisible;
    }

    public void setAliasComponentIsVisible(String _v) {
        this.aliasComponentIsVisible = _v;
    }

    public String getAliasComponentSetVisible() {
        return aliasComponentSetVisible;
    }

    public void setAliasComponentSetVisible(String _v) {
        this.aliasComponentSetVisible = _v;
    }

    public String getAliasComponentInvokeLater() {
        return aliasComponentInvokeLater;
    }

    public void setAliasComponentInvokeLater(String _v) {
        this.aliasComponentInvokeLater = _v;
    }

    public String getAliasImage() {
        return aliasImage;
    }

    public void setAliasImage(String _v) {
        this.aliasImage = _v;
    }

    public String getAliasImageGetWidth() {
        return aliasImageGetWidth;
    }

    public void setAliasImageGetWidth(String _v) {
        this.aliasImageGetWidth = _v;
    }

    public String getAliasImageGetHeight() {
        return aliasImageGetHeight;
    }

    public void setAliasImageGetHeight(String _v) {
        this.aliasImageGetHeight = _v;
    }

    public String getAliasImageGet() {
        return aliasImageGet;
    }

    public void setAliasImageGet(String _v) {
        this.aliasImageGet = _v;
    }

    public String getAliasImageSet() {
        return aliasImageSet;
    }

    public void setAliasImageSet(String _v) {
        this.aliasImageSet = _v;
    }

    public String getAliasImageIsWithAlpha() {
        return aliasImageIsWithAlpha;
    }

    public void setAliasImageIsWithAlpha(String _v) {
        this.aliasImageIsWithAlpha = _v;
    }

    public String getAliasImageEq() {
        return aliasImageEq;
    }

    public void setAliasImageEq(String _v) {
        this.aliasImageEq = _v;
    }

    public String getAliasImageGetColor() {
        return aliasImageGetColor;
    }

    public void setAliasImageGetColor(String _v) {
        this.aliasImageGetColor = _v;
    }

    public String getAliasImageSetColor() {
        return aliasImageSetColor;
    }

    public void setAliasImageSetColor(String _v) {
        this.aliasImageSetColor = _v;
    }

    public String getAliasImageGetFont() {
        return aliasImageGetFont;
    }

    public void setAliasImageGetFont(String _v) {
        this.aliasImageGetFont = _v;
    }

    public String getAliasImageSetFont() {
        return aliasImageSetFont;
    }

    public void setAliasImageSetFont(String _v) {
        this.aliasImageSetFont = _v;
    }

    public String getAliasImageDraw() {
        return aliasImageDraw;
    }

    public void setAliasImageDraw(String _v) {
        this.aliasImageDraw = _v;
    }

    public String getAliasImageDrawLine() {
        return aliasImageDrawLine;
    }

    public void setAliasImageDrawLine(String _v) {
        this.aliasImageDrawLine = _v;
    }

    public String getAliasImageDrawRect() {
        return aliasImageDrawRect;
    }

    public void setAliasImageDrawRect(String _v) {
        this.aliasImageDrawRect = _v;
    }

    public String getAliasImageDrawOval() {
        return aliasImageDrawOval;
    }

    public void setAliasImageDrawOval(String _v) {
        this.aliasImageDrawOval = _v;
    }

    public String getAliasImageFillRect() {
        return aliasImageFillRect;
    }

    public void setAliasImageFillRect(String _v) {
        this.aliasImageFillRect = _v;
    }

    public String getAliasImageFillOval() {
        return aliasImageFillOval;
    }

    public void setAliasImageFillOval(String _v) {
        this.aliasImageFillOval = _v;
    }

    public String getAliasImageDrawPolygon() {
        return aliasImageDrawPolygon;
    }

    public void setAliasImageDrawPolygon(String _v) {
        this.aliasImageDrawPolygon = _v;
    }

    public String getAliasImageFillPolygon() {
        return aliasImageFillPolygon;
    }

    public void setAliasImageFillPolygon(String _v) {
        this.aliasImageFillPolygon = _v;
    }

    public String getAliasImageDispose() {
        return aliasImageDispose;
    }

    public void setAliasImageDispose(String _v) {
        this.aliasImageDispose = _v;
    }

    public String getAliasColor() {
        return aliasColor;
    }

    public void setAliasColor(String _v) {
        this.aliasColor = _v;
    }

    public String getAliasColorRed() {
        return aliasColorRed;
    }

    public void setAliasColorRed(String _v) {
        this.aliasColorRed = _v;
    }

    public String getAliasColorGreen() {
        return aliasColorGreen;
    }

    public void setAliasColorGreen(String _v) {
        this.aliasColorGreen = _v;
    }

    public String getAliasColorBlue() {
        return aliasColorBlue;
    }

    public void setAliasColorBlue(String _v) {
        this.aliasColorBlue = _v;
    }

    public String getAliasColorAlpha() {
        return aliasColorAlpha;
    }

    public void setAliasColorAlpha(String _v) {
        this.aliasColorAlpha = _v;
    }

    public String getAliasColorIsTransparent() {
        return aliasColorIsTransparent;
    }

    public void setAliasColorIsTransparent(String _v) {
        this.aliasColorIsTransparent = _v;
    }

    public String getAliasComponentSetPaint() {
        return aliasComponentSetPaint;
    }

    public void setAliasComponentSetPaint(String _v) {
        this.aliasComponentSetPaint = _v;
    }

    public String getAliasComponentGetPaint() {
        return aliasComponentGetPaint;
    }

    public void setAliasComponentGetPaint(String _v) {
        this.aliasComponentGetPaint = _v;
    }

    public String getAliasComponentRepaint() {
        return aliasComponentRepaint;
    }

    public void setAliasComponentRepaint(String _v) {
        this.aliasComponentRepaint = _v;
    }

    public String getAliasSetContent() {
        return aliasSetContent;
    }

    public void setAliasSetContent(String _v) {
        this.aliasSetContent = _v;
    }

    public String getAliasAddCompo() {
        return aliasAddCompo;
    }

    public void setAliasAddCompo(String _v) {
        this.aliasAddCompo = _v;
    }

    public String getAliasRemoveAll() {
        return aliasRemoveAll;
    }

    public void setAliasRemoveAll(String _v) {
        aliasRemoveAll = _v;
    }

    public String getAliasGetParentCompo() {
        return aliasGetParentCompo;
    }

    public void setAliasGetParentCompo(String _v) {
        this.aliasGetParentCompo = _v;
    }

    public String getAliasGetNextCompo() {
        return aliasGetNextCompo;
    }

    public void setAliasGetNextCompo(String _v) {
        this.aliasGetNextCompo = _v;
    }

    public String getAliasGetIndexCompo() {
        return aliasGetIndexCompo;
    }

    public void setAliasGetIndexCompo(String _v) {
        this.aliasGetIndexCompo = _v;
    }

    public String getAliasPanelFlow() {
        return aliasPanelFlow;
    }

    public void setAliasPanelFlow(String _v) {
        this.aliasPanelFlow = _v;
    }

    public String getAliasPanelAbsolute() {
        return aliasPanelAbsolute;
    }

    public void setAliasPanelAbsolute(String _v) {
        this.aliasPanelAbsolute = _v;
    }

    public String getAliasPanelGrid() {
        return aliasPanelGrid;
    }

    public void setAliasPanelGrid(String _v) {
        this.aliasPanelGrid = _v;
    }

    public String getAliasPanelPageBox() {
        return aliasPanelPageBox;
    }

    public void setAliasPanelPageBox(String _v) {
        this.aliasPanelPageBox = _v;
    }

    public String getAliasPanelValidate() {
        return aliasPanelValidate;
    }

    public void setAliasPanelValidate(String _v) {
        this.aliasPanelValidate = _v;
    }

    public String getAliasTabbedPane() {
        return aliasTabbedPane;
    }

    public void setAliasTabbedPane(String _v) {
        this.aliasTabbedPane = _v;
    }

    public String getAliasTabbedPaneNb() {
        return aliasTabbedPaneNb;
    }

    public void setAliasTabbedPaneNb(String _v) {
        this.aliasTabbedPaneNb = _v;
    }

    public String getAliasTabbedPaneAdd() {
        return aliasTabbedPaneAdd;
    }

    public void setAliasTabbedPaneAdd(String _v) {
        this.aliasTabbedPaneAdd = _v;
    }

    public String getAliasTabbedPaneGet() {
        return aliasTabbedPaneGet;
    }

    public void setAliasTabbedPaneGet(String _v) {
        this.aliasTabbedPaneGet = _v;
    }

    public String getAliasTabbedPaneGetTitle() {
        return aliasTabbedPaneGetTitle;
    }

    public void setAliasTabbedPaneGetTitle(String _v) {
        this.aliasTabbedPaneGetTitle = _v;
    }

    public String getAliasTabbedPaneSet() {
        return aliasTabbedPaneSet;
    }

    public void setAliasTabbedPaneSet(String _v) {
        this.aliasTabbedPaneSet = _v;
    }

    public String getAliasTabbedPaneSetTitle() {
        return aliasTabbedPaneSetTitle;
    }

    public void setAliasTabbedPaneSetTitle(String _v) {
        this.aliasTabbedPaneSetTitle = _v;
    }

    public String getAliasTabbedPaneRemove() {
        return aliasTabbedPaneRemove;
    }

    public void setAliasTabbedPaneRemove(String _v) {
        this.aliasTabbedPaneRemove = _v;
    }

    public String getAliasTabbedPaneIndex() {
        return aliasTabbedPaneIndex;
    }

    public void setAliasTabbedPaneIndex(String _v) {
        this.aliasTabbedPaneIndex = _v;
    }

    public String getAliasTabbedPaneSelIndex() {
        return aliasTabbedPaneSelIndex;
    }

    public void setAliasTabbedPaneSelIndex(String _v) {
        this.aliasTabbedPaneSelIndex = _v;
    }

    public String getAliasAddListener() {
        return aliasAddListener;
    }

    public void setAliasAddListener(String _v) {
        this.aliasAddListener = _v;
    }

    public String getAliasRemoveListener() {
        return aliasRemoveListener;
    }

    public void setAliasRemoveListener(String _v) {
        this.aliasRemoveListener = _v;
    }

    public String getAliasGetListeners() {
        return aliasGetListeners;
    }

    public void setAliasGetListeners(String _v) {
        this.aliasGetListeners = _v;
    }

    public String getAliasAddWindowListener() {
        return aliasAddWindowListener;
    }

    public void setAliasAddWindowListener(String _v) {
        this.aliasAddWindowListener = _v;
    }

    public String getAliasSetLabelText() {
        return aliasSetLabelText;
    }

    public void setAliasSetLabelText(String _v) {
        this.aliasSetLabelText = _v;
    }

    public String getAliasSetLabelImage() {
        return aliasSetLabelImage;
    }

    public void setAliasSetLabelImage(String _v) {
        this.aliasSetLabelImage = _v;
    }

    public String getAliasPaint() {
        return aliasPaint;
    }

    public void setAliasPaint(String _v) {
        this.aliasPaint = _v;
    }

    public String getAliasPaintMethod() {
        return aliasPaintMethod;
    }

    public void setAliasPaintMethod(String _v) {
        this.aliasPaintMethod = _v;
    }

    public String getAliasPaintSet() {
        return aliasPaintSet;
    }

    public void setAliasPaintSet(String _v) {
        this.aliasPaintSet = _v;
    }

    public String getAliasPaintAdd() {
        return aliasPaintAdd;
    }

    public void setAliasPaintAdd(String _v) {
        this.aliasPaintAdd = _v;
    }

    public String getAliasPaintRefresh() {
        return aliasPaintRefresh;
    }

    public void setAliasPaintRefresh(String _v) {
        this.aliasPaintRefresh = _v;
    }

    public String getAliasPaintRefreshOne() {
        return aliasPaintRefreshOne;
    }

    public void setAliasPaintRefreshOne(String _v) {
        this.aliasPaintRefreshOne = _v;
    }

    public String getAliasRemoveCompo() {
        return aliasRemoveCompo;
    }

    public void setAliasRemoveCompo(String _v) {
        this.aliasRemoveCompo = _v;
    }

    public String getAliasCount() {
        return aliasCount;
    }

    public void setAliasCount(String _v) {
        this.aliasCount = _v;
    }

    public String getAliasScrollPane() {
        return aliasScrollPane;
    }

    public void setAliasScrollPane(String _v) {
        this.aliasScrollPane = _v;
    }

    public String getAliasScrollPaneGetView() {
        return aliasScrollPaneGetView;
    }

    public void setAliasScrollPaneGetView(String _v) {
        this.aliasScrollPaneGetView = _v;
    }

    public String getAliasScrollPaneSetView() {
        return aliasScrollPaneSetView;
    }

    public void setAliasScrollPaneSetView(String _v) {
        this.aliasScrollPaneSetView = _v;
    }

    public String getAliasScrollPaneHorizontalValue() {
        return aliasScrollPaneHorizontalValue;
    }

    public void setAliasScrollPaneHorizontalValue(String _v) {
        this.aliasScrollPaneHorizontalValue = _v;
    }

    public String getAliasScrollPaneVerticalValue() {
        return aliasScrollPaneVerticalValue;
    }

    public void setAliasScrollPaneVerticalValue(String _v) {
        this.aliasScrollPaneVerticalValue = _v;
    }

    public String getAliasScrollPaneValidate() {
        return aliasScrollPaneValidate;
    }

    public void setAliasScrollPaneValidate(String _v) {
        this.aliasScrollPaneValidate = _v;
    }

    public String getAliasSplitPane() {
        return aliasSplitPane;
    }

    public void setAliasSplitPane(String _v) {
        this.aliasSplitPane = _v;
    }

    public String getAliasSplitPaneGetDividerLocation() {
        return aliasSplitPaneGetDividerLocation;
    }

    public void setAliasSplitPaneGetDividerLocation(String _v) {
        this.aliasSplitPaneGetDividerLocation = _v;
    }

    public String getAliasSplitPaneSetDividerLocation() {
        return aliasSplitPaneSetDividerLocation;
    }

    public void setAliasSplitPaneSetDividerLocation(String _v) {
        this.aliasSplitPaneSetDividerLocation = _v;
    }

    public String getAliasSplitPaneGetDividerSize() {
        return aliasSplitPaneGetDividerSize;
    }

    public void setAliasSplitPaneGetDividerSize(String _v) {
        this.aliasSplitPaneGetDividerSize = _v;
    }

    public String getAliasSplitPaneSetDividerSize() {
        return aliasSplitPaneSetDividerSize;
    }

    public void setAliasSplitPaneSetDividerSize(String _v) {
        this.aliasSplitPaneSetDividerSize = _v;
    }

    public String getAliasSplitPaneIsContinuousLayout() {
        return aliasSplitPaneIsContinuousLayout;
    }

    public void setAliasSplitPaneIsContinuousLayout(String _v) {
        this.aliasSplitPaneIsContinuousLayout = _v;
    }

    public String getAliasSplitPaneSetContinuousLayout() {
        return aliasSplitPaneSetContinuousLayout;
    }

    public void setAliasSplitPaneSetContinuousLayout(String _v) {
        this.aliasSplitPaneSetContinuousLayout = _v;
    }

    public String getAliasSplitPaneIsOneTouchExpandable() {
        return aliasSplitPaneIsOneTouchExpandable;
    }

    public void setAliasSplitPaneIsOneTouchExpandable(String _v) {
        this.aliasSplitPaneIsOneTouchExpandable = _v;
    }

    public String getAliasSplitPaneSetOneTouchExpandable() {
        return aliasSplitPaneSetOneTouchExpandable;
    }

    public void setAliasSplitPaneSetOneTouchExpandable(String _v) {
        this.aliasSplitPaneSetOneTouchExpandable = _v;
    }

    public String getAliasSplitPaneGetLeft() {
        return aliasSplitPaneGetLeft;
    }

    public void setAliasSplitPaneGetLeft(String _v) {
        this.aliasSplitPaneGetLeft = _v;
    }

    public String getAliasSplitPaneSetLeft() {
        return aliasSplitPaneSetLeft;
    }

    public void setAliasSplitPaneSetLeft(String _v) {
        this.aliasSplitPaneSetLeft = _v;
    }

    public String getAliasSplitPaneGetRight() {
        return aliasSplitPaneGetRight;
    }

    public void setAliasSplitPaneGetRight(String _v) {
        this.aliasSplitPaneGetRight = _v;
    }

    public String getAliasSplitPaneSetRight() {
        return aliasSplitPaneSetRight;
    }

    public void setAliasSplitPaneSetRight(String _v) {
        this.aliasSplitPaneSetRight = _v;
    }

    public String getAliasSplitPaneValidate() {
        return aliasSplitPaneValidate;
    }

    public void setAliasSplitPaneValidate(String _v) {
        this.aliasSplitPaneValidate = _v;
    }

    public String getAliasPack() {
        return aliasPack;
    }

    public void setAliasPack(String _v) {
        this.aliasPack = _v;
    }

    public String getAliasDispose() {
        return aliasDispose;
    }

    public void setAliasDispose(String _v) {
        this.aliasDispose = _v;
    }

    public String getAliasCloseAll() {
        return aliasCloseAll;
    }

    public void setAliasCloseAll(String _v) {
        this.aliasCloseAll = _v;
    }

    public String getAliasIsVisible() {
        return aliasIsVisible;
    }

    public void setAliasIsVisible(String _v) {
        this.aliasIsVisible = _v;
    }

    public String getAliasSetVisible() {
        return aliasSetVisible;
    }

    public void setAliasSetVisible(String _v) {
        this.aliasSetVisible = _v;
    }

    public String getAliasWindow() {
        return aliasWindow;
    }

    public void setAliasWindow(String _v) {
        this.aliasWindow = _v;
    }

    public String getAliasWindowSet() {
        return aliasWindowSet;
    }

    public void setAliasWindowSet(String _v) {
        this.aliasWindowSet = _v;
    }

    public String getAliasWindowSetAll() {
        return aliasWindowSetAll;
    }

    public void setAliasWindowSetAll(String _v) {
        this.aliasWindowSetAll = _v;
    }

    public String getAliasWindowSetAdd() {
        return aliasWindowSetAdd;
    }

    public void setAliasWindowSetAdd(String _v) {
        this.aliasWindowSetAdd = _v;
    }

    public String getAliasWindowSetContains() {
        return aliasWindowSetContains;
    }

    public void setAliasWindowSetContains(String _v) {
        this.aliasWindowSetContains = _v;
    }

    public String getAliasWindowSetRemove() {
        return aliasWindowSetRemove;
    }

    public void setAliasWindowSetRemove(String _v) {
        this.aliasWindowSetRemove = _v;
    }

    public String getAliasWindowSetSnapshot() {
        return aliasWindowSetSnapshot;
    }

    public void setAliasWindowSetSnapshot(String _v) {
        this.aliasWindowSetSnapshot = _v;
    }

    public String getAliasMouseListener() {
        return aliasMouseListener;
    }

    public void setAliasMouseListener(String _v) {
        this.aliasMouseListener = _v;
    }

    public String getAliasMouseClicked() {
        return aliasMouseClicked;
    }

    public void setAliasMouseClicked(String _v) {
        this.aliasMouseClicked = _v;
    }

    public String getAliasMousePressed() {
        return aliasMousePressed;
    }

    public void setAliasMousePressed(String _v) {
        this.aliasMousePressed = _v;
    }

    public String getAliasMouseReleased() {
        return aliasMouseReleased;
    }

    public void setAliasMouseReleased(String _v) {
        this.aliasMouseReleased = _v;
    }

    public String getAliasMouseEntered() {
        return aliasMouseEntered;
    }

    public void setAliasMouseEntered(String _v) {
        this.aliasMouseEntered = _v;
    }

    public String getAliasMouseExited() {
        return aliasMouseExited;
    }

    public void setAliasMouseExited(String _v) {
        this.aliasMouseExited = _v;
    }

    public String getAliasMouseDragged() {
        return aliasMouseDragged;
    }

    public void setAliasMouseDragged(String _v) {
        this.aliasMouseDragged = _v;
    }

    public String getAliasMouseMoved() {
        return aliasMouseMoved;
    }

    public void setAliasMouseMoved(String _v) {
        this.aliasMouseMoved = _v;
    }

    public String getAliasMouseEvent() {
        return aliasMouseEvent;
    }

    public void setAliasMouseEvent(String _v) {
        this.aliasMouseEvent = _v;
    }

    public String getAliasMouseEventGetFirst() {
        return aliasMouseEventGetFirst;
    }

    public void setAliasMouseEventGetFirst(String _v) {
        this.aliasMouseEventGetFirst = _v;
    }

    public String getAliasMouseEventGetSecond() {
        return aliasMouseEventGetSecond;
    }

    public void setAliasMouseEventGetSecond(String _v) {
        this.aliasMouseEventGetSecond = _v;
    }

    public String getAliasMouseEventGetClicks() {
        return aliasMouseEventGetClicks;
    }

    public void setAliasMouseEventGetClicks(String _v) {
        this.aliasMouseEventGetClicks = _v;
    }

    public String getAliasMouseEventIsAlt() {
        return aliasMouseEventIsAlt;
    }

    public void setAliasMouseEventIsAlt(String _v) {
        this.aliasMouseEventIsAlt = _v;
    }

    public String getAliasMouseEventIsCtrl() {
        return aliasMouseEventIsCtrl;
    }

    public void setAliasMouseEventIsCtrl(String _v) {
        this.aliasMouseEventIsCtrl = _v;
    }

    public String getAliasMouseEventIsShift() {
        return aliasMouseEventIsShift;
    }

    public void setAliasMouseEventIsShift(String _v) {
        this.aliasMouseEventIsShift = _v;
    }

    public String getAliasMouseEventIsLeft() {
        return aliasMouseEventIsLeft;
    }

    public void setAliasMouseEventIsLeft(String _v) {
        this.aliasMouseEventIsLeft = _v;
    }

    public String getAliasMouseEventIsMiddle() {
        return aliasMouseEventIsMiddle;
    }

    public void setAliasMouseEventIsMiddle(String _v) {
        this.aliasMouseEventIsMiddle = _v;
    }

    public String getAliasMouseEventIsRight() {
        return aliasMouseEventIsRight;
    }

    public void setAliasMouseEventIsRight(String _v) {
        this.aliasMouseEventIsRight = _v;
    }

    public String getAliasWheelListener() {
        return aliasWheelListener;
    }

    public void setAliasWheelListener(String _v) {
        this.aliasWheelListener = _v;
    }

    public String getAliasWheelMove() {
        return aliasWheelMove;
    }

    public void setAliasWheelMove(String _v) {
        this.aliasWheelMove = _v;
    }

    public String getAliasWheelEvent() {
        return aliasWheelEvent;
    }

    public void setAliasWheelEvent(String _v) {
        this.aliasWheelEvent = _v;
    }

    public String getAliasWheelRotatedClicks() {
        return aliasWheelRotatedClicks;
    }

    public void setAliasWheelRotatedClicks(String _v) {
        this.aliasWheelRotatedClicks = _v;
    }

    public String getAliasRequestFocus() {
        return aliasRequestFocus;
    }

    public void setAliasRequestFocus(String _v) {
        this.aliasRequestFocus = _v;
    }

    public String getAliasCompToolTip() {
        return aliasCompToolTip;
    }

    public void setAliasCompToolTip(String _v) {
        this.aliasCompToolTip = _v;
    }

    public String getAliasCompFocusable() {
        return aliasCompFocusable;
    }

    public void setAliasCompFocusable(String _v) {
        this.aliasCompFocusable = _v;
    }

    public String getAliasCompOpaque() {
        return aliasCompOpaque;
    }

    public void setAliasCompOpaque(String _v) {
        this.aliasCompOpaque = _v;
    }

    public String getAliasCompBack() {
        return aliasCompBack;
    }

    public void setAliasCompBack(String _v) {
        this.aliasCompBack = _v;
    }

    public String getAliasCompoRelLeft() {
        return aliasCompoRelLeft;
    }

    public void setAliasCompoRelLeft(String _v) {
        this.aliasCompoRelLeft = _v;
    }

    public String getAliasCompoRelRight() {
        return aliasCompoRelRight;
    }

    public void setAliasCompoRelRight(String _v) {
        this.aliasCompoRelRight = _v;
    }

    public String getAliasCompoRelTop() {
        return aliasCompoRelTop;
    }

    public void setAliasCompoRelTop(String _v) {
        this.aliasCompoRelTop = _v;
    }

    public String getAliasCompoRelBottom() {
        return aliasCompoRelBottom;
    }

    public void setAliasCompoRelBottom(String _v) {
        this.aliasCompoRelBottom = _v;
    }

    public String getAliasCompoRelCentHoriz() {
        return aliasCompoRelCentHoriz;
    }

    public void setAliasCompoRelCentHoriz(String _v) {
        this.aliasCompoRelCentHoriz = _v;
    }

    public String getAliasCompoRelCentVert() {
        return aliasCompoRelCentVert;
    }

    public void setAliasCompoRelCentVert(String _v) {
        this.aliasCompoRelCentVert = _v;
    }

    public String getAliasCompFore() {
        return aliasCompFore;
    }

    public void setAliasCompFore(String _v) {
        this.aliasCompFore = _v;
    }

    public String getAliasCompGetFirstPos() {
        return aliasCompGetFirstPos;
    }

    public void setAliasCompGetFirstPos(String _v) {
        this.aliasCompGetFirstPos = _v;
    }

    public String getAliasCompGetSecondPos() {
        return aliasCompGetSecondPos;
    }

    public void setAliasCompGetSecondPos(String _v) {
        this.aliasCompGetSecondPos = _v;
    }

    public String getAliasCompLoc() {
        return aliasCompLoc;
    }

    public void setAliasCompLoc(String _v) {
        this.aliasCompLoc = _v;
    }

    public String getAliasCompBorLine() {
        return aliasCompBorLine;
    }

    public void setAliasCompBorLine(String _v) {
        this.aliasCompBorLine = _v;
    }

    public String getAliasCompBorTitle() {
        return aliasCompBorTitle;
    }

    public void setAliasCompBorTitle(String _v) {
        this.aliasCompBorTitle = _v;
    }

    public String getAliasCompBorLower() {
        return aliasCompBorLower;
    }

    public void setAliasCompBorLower(String _v) {
        this.aliasCompBorLower = _v;
    }

    public String getAliasCompBorRaise() {
        return aliasCompBorRaise;
    }

    public void setAliasCompBorRaise(String _v) {
        this.aliasCompBorRaise = _v;
    }

    public String getAliasAddKeyListener() {
        return aliasAddKeyListener;
    }

    public void setAliasAddKeyListener(String _v) {
        this.aliasAddKeyListener = _v;
    }

    public String getAliasRemoveKeyListener() {
        return aliasRemoveKeyListener;
    }

    public void setAliasRemoveKeyListener(String _v) {
        this.aliasRemoveKeyListener = _v;
    }

    public String getAliasGetKeyListeners() {
        return aliasGetKeyListeners;
    }

    public void setAliasGetKeyListeners(String _v) {
        this.aliasGetKeyListeners = _v;
    }

    public String getAliasAddWheelListener() {
        return aliasAddWheelListener;
    }

    public void setAliasAddWheelListener(String _v) {
        this.aliasAddWheelListener = _v;
    }

    public String getAliasRemoveWheelListener() {
        return aliasRemoveWheelListener;
    }

    public void setAliasRemoveWheelListener(String _v) {
        this.aliasRemoveWheelListener = _v;
    }

    public String getAliasGetWheelListeners() {
        return aliasGetWheelListeners;
    }

    public void setAliasGetWheelListeners(String _v) {
        this.aliasGetWheelListeners = _v;
    }

    public String getAliasKeyListener() {
        return aliasKeyListener;
    }

    public void setAliasKeyListener(String _v) {
        this.aliasKeyListener = _v;
    }

    public String getAliasKeyPressed() {
        return aliasKeyPressed;
    }

    public void setAliasKeyPressed(String _v) {
        this.aliasKeyPressed = _v;
    }

    public String getAliasKeyTyped() {
        return aliasKeyTyped;
    }

    public void setAliasKeyTyped(String _v) {
        this.aliasKeyTyped = _v;
    }

    public String getAliasKeyReleased() {
        return aliasKeyReleased;
    }

    public void setAliasKeyReleased(String _v) {
        this.aliasKeyReleased = _v;
    }

    public String getAliasKeyEvent() {
        return aliasKeyEvent;
    }

    public void setAliasKeyEvent(String _v) {
        this.aliasKeyEvent = _v;
    }

    public String getAliasKeyEventChar() {
        return aliasKeyEventChar;
    }

    public void setAliasKeyEventChar(String _v) {
        this.aliasKeyEventChar = _v;
    }

    public String getAliasKeyEventCode() {
        return aliasKeyEventCode;
    }

    public void setAliasKeyEventCode(String _v) {
        this.aliasKeyEventCode = _v;
    }

    public String getAliasKeyEventIsShift() {
        return aliasKeyEventIsShift;
    }

    public void setAliasKeyEventIsShift(String _v) {
        this.aliasKeyEventIsShift = _v;
    }

    public String getAliasKeyEventIsAlt() {
        return aliasKeyEventIsAlt;
    }

    public void setAliasKeyEventIsAlt(String _v) {
        this.aliasKeyEventIsAlt = _v;
    }

    public String getAliasKeyEventIsCtrl() {
        return aliasKeyEventIsCtrl;
    }

    public void setAliasKeyEventIsCtrl(String _v) {
        this.aliasKeyEventIsCtrl = _v;
    }

    public String getAliasWindowListener() {
        return aliasWindowListener;
    }

    public void setAliasWindowListener(String _v) {
        this.aliasWindowListener = _v;
    }

    public String getAliasWindowOpened() {
        return aliasWindowOpened;
    }

    public void setAliasWindowOpened(String _v) {
        this.aliasWindowOpened = _v;
    }

    public String getAliasWindowClosed() {
        return aliasWindowClosed;
    }

    public void setAliasWindowClosed(String _v) {
        this.aliasWindowClosed = _v;
    }

    public String getAliasWindowClosing() {
        return aliasWindowClosing;
    }

    public void setAliasWindowClosing(String _v) {
        this.aliasWindowClosing = _v;
    }

    public String getAliasWindowIconified() {
        return aliasWindowIconified;
    }

    public void setAliasWindowIconified(String _v) {
        this.aliasWindowIconified = _v;
    }

    public String getAliasWindowDeiconified() {
        return aliasWindowDeiconified;
    }

    public void setAliasWindowDeiconified(String _v) {
        this.aliasWindowDeiconified = _v;
    }

    public String getAliasWindowActivated() {
        return aliasWindowActivated;
    }

    public void setAliasWindowActivated(String _v) {
        this.aliasWindowActivated = _v;
    }

    public String getAliasWindowDeactivated() {
        return aliasWindowDeactivated;
    }

    public void setAliasWindowDeactivated(String _v) {
        this.aliasWindowDeactivated = _v;
    }

    public String getAliasWindowEvent() {
        return aliasWindowEvent;
    }

    public void setAliasWindowEvent(String _v) {
        this.aliasWindowEvent = _v;
    }

    public String getAliasListSelection() {
        return aliasListSelection;
    }

    public void setAliasListSelection(String _v) {
        this.aliasListSelection = _v;
    }

    public String getAliasValueChanged() {
        return aliasValueChanged;
    }

    public void setAliasValueChanged(String _v) {
        this.aliasValueChanged = _v;
    }

    public String getAliasInput() {
        return aliasInput;
    }

    public void setAliasInput(String _v) {
        aliasInput = _v;
    }

    public String getAliasInputIsEnabled() {
        return aliasInputIsEnabled;
    }

    public void setAliasInputIsEnabled(String _v) {
        aliasInputIsEnabled = _v;
    }

    public String getAliasInputSetEnabled() {
        return aliasInputSetEnabled;
    }

    public void setAliasInputSetEnabled(String _v) {
        aliasInputSetEnabled = _v;
    }

    public String getAliasRender() {
        return aliasRender;
    }

    public void setAliasRender(String _v) {
        this.aliasRender = _v;
    }

    public String getAliasRenderGetHeight() {
        return aliasRenderGetHeight;
    }

    public void setAliasRenderGetHeight(String _v) {
        this.aliasRenderGetHeight = _v;
    }

    public String getAliasRenderSetHeight() {
        return aliasRenderSetHeight;
    }

    public void setAliasRenderSetHeight(String _v) {
        this.aliasRenderSetHeight = _v;
    }

    public String getAliasRenderGetWidth() {
        return aliasRenderGetWidth;
    }

    public void setAliasRenderGetWidth(String _v) {
        this.aliasRenderGetWidth = _v;
    }

    public String getAliasRenderSetWidth() {
        return aliasRenderSetWidth;
    }

    public void setAliasRenderSetWidth(String _v) {
        this.aliasRenderSetWidth = _v;
    }

    public String getAliasRenderGetPaint() {
        return aliasRenderGetPaint;
    }

    public void setAliasRenderGetPaint(String _v) {
        this.aliasRenderGetPaint = _v;
    }

    public String getAliasRenderSetPaint() {
        return aliasRenderSetPaint;
    }

    public void setAliasRenderSetPaint(String _v) {
        this.aliasRenderSetPaint = _v;
    }

    public String getAliasGrList() {
        return aliasGrList;
    }

    public void setAliasGrList(String _v) {
        this.aliasGrList = _v;
    }

    public String getAliasGrListAdd() {
        return aliasGrListAdd;
    }

    public void setAliasGrListAdd(String _v) {
        this.aliasGrListAdd = _v;
    }

    public String getAliasGrListSet() {
        return aliasGrListSet;
    }

    public void setAliasGrListSet(String _v) {
        this.aliasGrListSet = _v;
    }

    public String getAliasGrListGetListView() {
        return aliasGrListGetListView;
    }

    public void setAliasGrListGetListView(String _v) {
        this.aliasGrListGetListView = _v;
    }

    public String getAliasGrListGetSelectedIndexes() {
        return aliasGrListGetSelectedIndexes;
    }

    public void setAliasGrListGetSelectedIndexes(String _v) {
        this.aliasGrListGetSelectedIndexes = _v;
    }

    public String getAliasGrListSetSelectedIndexes() {
        return aliasGrListSetSelectedIndexes;
    }

    public void setAliasGrListSetSelectedIndexes(String _v) {
        this.aliasGrListSetSelectedIndexes = _v;
    }

    public String getAliasGrListClearSelection() {
        return aliasGrListClearSelection;
    }

    public void setAliasGrListClearSelection(String _v) {
        this.aliasGrListClearSelection = _v;
    }

    public String getAliasGrListUpdateGraphics() {
        return aliasGrListUpdateGraphics;
    }

    public void setAliasGrListUpdateGraphics(String _v) {
        this.aliasGrListUpdateGraphics = _v;
    }

    public String getAliasGrListGetRender() {
        return aliasGrListGetRender;
    }

    public void setAliasGrListGetRender(String _v) {
        this.aliasGrListGetRender = _v;
    }

    public String getAliasGrListSetRender() {
        return aliasGrListSetRender;
    }

    public void setAliasGrListSetRender(String _v) {
        this.aliasGrListSetRender = _v;
    }

    public String getAliasGrListGetSelections() {
        return aliasGrListGetSelections;
    }

    public void setAliasGrListGetSelections(String _v) {
        this.aliasGrListGetSelections = _v;
    }


    public String getAliasGrListAddSelection() {
        return aliasGrListAddSelection;
    }

    public void setAliasGrListAddSelection(String _v) {
        this.aliasGrListAddSelection = _v;
    }

    public String getAliasGrListRemoveSelection() {
        return aliasGrListRemoveSelection;
    }

    public void setAliasGrListRemoveSelection(String _v) {
        this.aliasGrListRemoveSelection = _v;
    }

    public String getAliasGrListGetVisibleRowCount() {
        return aliasGrListGetVisibleRowCount;
    }

    public void setAliasGrListGetVisibleRowCount(String _v) {
        this.aliasGrListGetVisibleRowCount = _v;
    }

    public String getAliasGrListSetVisibleRowCount() {
        return aliasGrListSetVisibleRowCount;
    }

    public void setAliasGrListSetVisibleRowCount(String _v) {
        this.aliasGrListSetVisibleRowCount = _v;
    }

    public String getAliasGrListClear() {
        return aliasGrListClear;
    }

    public void setAliasGrListClear(String _v) {
        this.aliasGrListClear = _v;
    }

    public String getAliasGrListRemove() {
        return aliasGrListRemove;
    }

    public void setAliasGrListRemove(String _v) {
        this.aliasGrListRemove = _v;
    }

    public String getAliasCombo() {
        return aliasCombo;
    }

    public void setAliasCombo(String _v) {
        this.aliasCombo = _v;
    }

    public String getAliasComboGetSelectedItem() {
        return aliasComboGetSelectedItem;
    }

    public void setAliasComboGetSelectedItem(String _v) {
        this.aliasComboGetSelectedItem = _v;
    }

    public String getAliasComboAddItem() {
        return aliasComboAddItem;
    }

    public void setAliasComboAddItem(String _v) {
        this.aliasComboAddItem = _v;
    }

    public String getAliasComboGetItemCount() {
        return aliasComboGetItemCount;
    }

    public void setAliasComboGetItemCount(String _v) {
        this.aliasComboGetItemCount = _v;
    }

    public String getAliasComboSelectItem() {
        return aliasComboSelectItem;
    }

    public void setAliasComboSelectItem(String _v) {
        this.aliasComboSelectItem = _v;
    }

    public String getAliasComboAddListener() {
        return aliasComboAddListener;
    }

    public void setAliasComboAddListener(String _v) {
        this.aliasComboAddListener = _v;
    }

    public String getAliasComboRemoveListener() {
        return aliasComboRemoveListener;
    }

    public void setAliasComboRemoveListener(String _v) {
        this.aliasComboRemoveListener = _v;
    }

    public String getAliasComboGetListeners() {
        return aliasComboGetListeners;
    }

    public void setAliasComboGetListeners(String _v) {
        this.aliasComboGetListeners = _v;
    }

    public String getAliasComboGetSelectedIndexes() {
        return aliasComboGetSelectedIndexes;
    }

    public void setAliasComboGetSelectedIndexes(String _v) {
        this.aliasComboGetSelectedIndexes = _v;
    }

    public String getAliasComboGetSelectedIndex() {
        return aliasComboGetSelectedIndex;
    }

    public void setAliasComboGetSelectedIndex(String _v) {
        this.aliasComboGetSelectedIndex = _v;
    }

    public String getAliasComboRemoveAllItems() {
        return aliasComboRemoveAllItems;
    }

    public void setAliasComboRemoveAllItems(String _v) {
        this.aliasComboRemoveAllItems = _v;
    }

    public String getAliasComboRemoveItem() {
        return aliasComboRemoveItem;
    }

    public void setAliasComboRemoveItem(String _v) {
        this.aliasComboRemoveItem = _v;
    }

    public String getAliasButtonGroup() {
        return aliasButtonGroup;
    }

    public void setAliasButtonGroup(String _v) {
        this.aliasButtonGroup = _v;
    }

    public String getAliasButtonGroupAdd() {
        return aliasButtonGroupAdd;
    }

    public void setAliasButtonGroupAdd(String _v) {
        this.aliasButtonGroupAdd = _v;
    }

    public String getAliasRadio() {
        return aliasRadio;
    }

    public void setAliasRadio(String _v) {
        this.aliasRadio = _v;
    }

    public String getAliasRadioIsSelected() {
        return aliasRadioIsSelected;
    }

    public void setAliasRadioIsSelected(String _v) {
        this.aliasRadioIsSelected = _v;
    }

    public String getAliasRadioSetSelected() {
        return aliasRadioSetSelected;
    }

    public void setAliasRadioSetSelected(String _v) {
        this.aliasRadioSetSelected = _v;
    }

    public String getAliasRadioGetText() {
        return aliasRadioGetText;
    }

    public void setAliasRadioGetText(String _v) {
        this.aliasRadioGetText = _v;
    }

    public String getAliasRadioSetText() {
        return aliasRadioSetText;
    }

    public void setAliasRadioSetText(String _v) {
        this.aliasRadioSetText = _v;
    }

    public String getAliasPopupMenu() {
        return aliasPopupMenu;
    }

    public void setAliasPopupMenu(String _v) {
        this.aliasPopupMenu = _v;
    }

    public String getAliasPopupMenuAdd() {
        return aliasPopupMenuAdd;
    }

    public void setAliasPopupMenuAdd(String _v) {
        this.aliasPopupMenuAdd = _v;
    }

    public String getAliasPopupMenuGetComp() {
        return aliasPopupMenuGetComp;
    }

    public void setAliasPopupMenuGetComp(String _v) {
        this.aliasPopupMenuGetComp = _v;
    }

    public String getAliasPopupMenuRemoveComp() {
        return aliasPopupMenuRemoveComp;
    }

    public void setAliasPopupMenuRemoveComp(String _v) {
        this.aliasPopupMenuRemoveComp = _v;
    }

    public String getAliasPopupMenuNbComp() {
        return aliasPopupMenuNbComp;
    }

    public void setAliasPopupMenuNbComp(String _v) {
        this.aliasPopupMenuNbComp = _v;
    }

    public String getAliasPopupMenuAddMenu() {
        return aliasPopupMenuAddMenu;
    }

    public void setAliasPopupMenuAddMenu(String _v) {
        this.aliasPopupMenuAddMenu = _v;
    }

    public String getAliasPopupMenuGetMenu() {
        return aliasPopupMenuGetMenu;
    }

    public void setAliasPopupMenuGetMenu(String _v) {
        this.aliasPopupMenuGetMenu = _v;
    }

    public String getAliasPopupMenuRemoveMenu() {
        return aliasPopupMenuRemoveMenu;
    }

    public void setAliasPopupMenuRemoveMenu(String _v) {
        this.aliasPopupMenuRemoveMenu = _v;
    }

    public String getAliasPopupMenuNbMenu() {
        return aliasPopupMenuNbMenu;
    }

    public void setAliasPopupMenuNbMenu(String _v) {
        this.aliasPopupMenuNbMenu = _v;
    }

    public String getAliasPopupMenuShow() {
        return aliasPopupMenuShow;
    }

    public void setAliasPopupMenuShow(String _v) {
        this.aliasPopupMenuShow = _v;
    }

    public String getAliasTextField() {
        return aliasTextField;
    }

    public void setAliasTextField(String _v) {
        this.aliasTextField = _v;
    }

    public String getAliasTextFieldGetText() {
        return aliasTextFieldGetText;
    }

    public void setAliasTextFieldGetText(String _v) {
        this.aliasTextFieldGetText = _v;
    }

    public String getAliasTextFieldSetText() {
        return aliasTextFieldSetText;
    }

    public void setAliasTextFieldSetText(String _v) {
        this.aliasTextFieldSetText = _v;
    }

    public String getAliasTextFieldAuto() {
        return aliasTextFieldAuto;
    }

    public void setAliasTextFieldAuto(String _v) {
        this.aliasTextFieldAuto = _v;
    }

    public String getAliasTextFieldAddAction() {
        return aliasTextFieldAddAction;
    }

    public void setAliasTextFieldAddAction(String _v) {
        this.aliasTextFieldAddAction = _v;
    }

    public String getAliasTextFieldAddDocument() {
        return aliasTextFieldAddDocument;
    }

    public void setAliasTextFieldAddDocument(String _v) {
        this.aliasTextFieldAddDocument = _v;
    }

    public String getAliasTextFieldAddPopup() {
        return aliasTextFieldAddPopup;
    }

    public void setAliasTextFieldAddPopup(String _v) {
        this.aliasTextFieldAddPopup = _v;
    }

    public String getAliasTextArea() {
        return aliasTextArea;
    }

    public void setAliasTextArea(String _v) {
        this.aliasTextArea = _v;
    }

    public String getAliasTextAreaGetText() {
        return aliasTextAreaGetText;
    }

    public void setAliasTextAreaGetText(String _v) {
        this.aliasTextAreaGetText = _v;
    }

    public String getAliasTextAreaSetText() {
        return aliasTextAreaSetText;
    }

    public void setAliasTextAreaSetText(String _v) {
        this.aliasTextAreaSetText = _v;
    }

    public String getAliasTextAreaGetTabSize() {
        return aliasTextAreaGetTabSize;
    }

    public void setAliasTextAreaGetTabSize(String _v) {
        this.aliasTextAreaGetTabSize = _v;
    }

    public String getAliasTextAreaSetTabSize() {
        return aliasTextAreaSetTabSize;
    }

    public void setAliasTextAreaSetTabSize(String _v) {
        this.aliasTextAreaSetTabSize = _v;
    }

    public String getAliasTextAreaAppend() {
        return aliasTextAreaAppend;
    }

    public void setAliasTextAreaAppend(String _v) {
        this.aliasTextAreaAppend = _v;
    }

    public String getAliasTextAreaInsert() {
        return aliasTextAreaInsert;
    }

    public void setAliasTextAreaInsert(String _v) {
        this.aliasTextAreaInsert = _v;
    }

    public String getAliasTextAreaReplaceRange() {
        return aliasTextAreaReplaceRange;
    }

    public void setAliasTextAreaReplaceRange(String _v) {
        this.aliasTextAreaReplaceRange = _v;
    }

    public String getAliasTextAreaReplaceSelection() {
        return aliasTextAreaReplaceSelection;
    }

    public void setAliasTextAreaReplaceSelection(String _v) {
        this.aliasTextAreaReplaceSelection = _v;
    }

    public String getAliasTextAreaGetSelectedText() {
        return aliasTextAreaGetSelectedText;
    }

    public void setAliasTextAreaGetSelectedText(String _v) {
        this.aliasTextAreaGetSelectedText = _v;
    }

    public String getAliasTextAreaSetSelectionStart() {
        return aliasTextAreaSetSelectionStart;
    }

    public void setAliasTextAreaSetSelectionStart(String _v) {
        this.aliasTextAreaSetSelectionStart = _v;
    }

    public String getAliasTextAreaSetSelectionEnd() {
        return aliasTextAreaSetSelectionEnd;
    }

    public void setAliasTextAreaSetSelectionEnd(String _v) {
        this.aliasTextAreaSetSelectionEnd = _v;
    }

    public String getAliasTextAreaSelect() {
        return aliasTextAreaSelect;
    }

    public void setAliasTextAreaSelect(String _v) {
        this.aliasTextAreaSelect = _v;
    }

    public String getAliasTextAreaSelectAll() {
        return aliasTextAreaSelectAll;
    }

    public void setAliasTextAreaSelectAll(String _v) {
        this.aliasTextAreaSelectAll = _v;
    }

    public String getAliasCheckBox() {
        return aliasCheckBox;
    }

    public void setAliasCheckBox(String _v) {
        this.aliasCheckBox = _v;
    }

    public String getAliasCheckBoxGetText() {
        return aliasCheckBoxGetText;
    }

    public void setAliasCheckBoxGetText(String _v) {
        this.aliasCheckBoxGetText = _v;
    }

    public String getAliasCheckBoxSetText() {
        return aliasCheckBoxSetText;
    }

    public void setAliasCheckBoxSetText(String _v) {
        this.aliasCheckBoxSetText = _v;
    }

    public String getAliasCheckBoxIsSelected() {
        return aliasCheckBoxIsSelected;
    }

    public void setAliasCheckBoxIsSelected(String _v) {
        this.aliasCheckBoxIsSelected = _v;
    }

    public String getAliasCheckBoxSetSelected() {
        return aliasCheckBoxSetSelected;
    }

    public void setAliasCheckBoxSetSelected(String _v) {
        this.aliasCheckBoxSetSelected = _v;
    }

    public String getAliasCheckBoxAddAction() {
        return aliasCheckBoxAddAction;
    }

    public void setAliasCheckBoxAddAction(String _v) {
        this.aliasCheckBoxAddAction = _v;
    }

    public String getAliasSpinner() {
        return aliasSpinner;
    }

    public void setAliasSpinner(String _v) {
        this.aliasSpinner = _v;
    }

    public String getAliasSpinnerSetRange() {
        return aliasSpinnerSetRange;
    }

    public void setAliasSpinnerSetRange(String _v) {
        this.aliasSpinnerSetRange = _v;
    }

    public String getAliasSpinnerSetRangeValue() {
        return aliasSpinnerSetRangeValue;
    }

    public void setAliasSpinnerSetRangeValue(String _v) {
        this.aliasSpinnerSetRangeValue = _v;
    }

    public String getAliasSpinnerGetValue() {
        return aliasSpinnerGetValue;
    }

    public void setAliasSpinnerGetValue(String _v) {
        this.aliasSpinnerGetValue = _v;
    }

    public String getAliasSpinnerSetValue() {
        return aliasSpinnerSetValue;
    }

    public void setAliasSpinnerSetValue(String _v) {
        this.aliasSpinnerSetValue = _v;
    }

    public String getAliasSpinnerGetMax() {
        return aliasSpinnerGetMax;
    }

    public void setAliasSpinnerGetMax(String _v) {
        this.aliasSpinnerGetMax = _v;
    }

    public String getAliasSpinnerSetMax() {
        return aliasSpinnerSetMax;
    }

    public void setAliasSpinnerSetMax(String _v) {
        this.aliasSpinnerSetMax = _v;
    }

    public String getAliasSpinnerGetMin() {
        return aliasSpinnerGetMin;
    }

    public void setAliasSpinnerGetMin(String _v) {
        this.aliasSpinnerGetMin = _v;
    }

    public String getAliasSpinnerSetMin() {
        return aliasSpinnerSetMin;
    }

    public void setAliasSpinnerSetMin(String _v) {
        this.aliasSpinnerSetMin = _v;
    }

    public String getAliasSpinnerGetStep() {
        return aliasSpinnerGetStep;
    }

    public void setAliasSpinnerGetStep(String _v) {
        this.aliasSpinnerGetStep = _v;
    }

    public String getAliasSpinnerSetStep() {
        return aliasSpinnerSetStep;
    }

    public void setAliasSpinnerSetStep(String _v) {
        this.aliasSpinnerSetStep = _v;
    }

    public String getAliasSlider() {
        return aliasSlider;
    }

    public void setAliasSlider(String _v) {
        this.aliasSlider = _v;
    }

    public String getAliasSliderGetValue() {
        return aliasSliderGetValue;
    }

    public void setAliasSliderGetValue(String _v) {
        this.aliasSliderGetValue = _v;
    }

    public String getAliasSliderSetValue() {
        return aliasSliderSetValue;
    }

    public void setAliasSliderSetValue(String _v) {
        this.aliasSliderSetValue = _v;
    }

    public String getAliasSliderGetMax() {
        return aliasSliderGetMax;
    }

    public void setAliasSliderGetMax(String _v) {
        this.aliasSliderGetMax = _v;
    }

    public String getAliasSliderSetMax() {
        return aliasSliderSetMax;
    }

    public void setAliasSliderSetMax(String _v) {
        this.aliasSliderSetMax = _v;
    }

    public String getAliasSliderGetMin() {
        return aliasSliderGetMin;
    }

    public void setAliasSliderGetMin(String _v) {
        this.aliasSliderGetMin = _v;
    }

    public String getAliasSliderSetMin() {
        return aliasSliderSetMin;
    }

    public void setAliasSliderSetMin(String _v) {
        this.aliasSliderSetMin = _v;
    }

    public String getAliasSliderGetOrientation() {
        return aliasSliderGetOrientation;
    }

    public void setAliasSliderGetOrientation(String _v) {
        this.aliasSliderGetOrientation = _v;
    }

    public String getAliasSliderSetOrientation() {
        return aliasSliderSetOrientation;
    }

    public void setAliasSliderSetOrientation(String _v) {
        this.aliasSliderSetOrientation = _v;
    }

    public String getAliasGetMenuBar() {
        return aliasGetMenuBar;
    }

    public void setAliasGetMenuBar(String _v) {
        this.aliasGetMenuBar = _v;
    }

    public String getAliasSetMenuBar() {
        return aliasSetMenuBar;
    }

    public void setAliasSetMenuBar(String _v) {
        this.aliasSetMenuBar = _v;
    }

    public String getAliasMenuBar() {
        return aliasMenuBar;
    }

    public void setAliasMenuBar(String _v) {
        this.aliasMenuBar = _v;
    }

    public String getAliasMenuBarAdd() {
        return aliasMenuBarAdd;
    }

    public void setAliasMenuBarAdd(String _v) {
        this.aliasMenuBarAdd = _v;
    }

    public String getAliasMenuBarGet() {
        return aliasMenuBarGet;
    }

    public void setAliasMenuBarGet(String _v) {
        this.aliasMenuBarGet = _v;
    }

    public String getAliasMenuBarRemove() {
        return aliasMenuBarRemove;
    }

    public void setAliasMenuBarRemove(String _v) {
        this.aliasMenuBarRemove = _v;
    }

    public String getAliasMenuBarNb() {
        return aliasMenuBarNb;
    }

    public void setAliasMenuBarNb(String _v) {
        this.aliasMenuBarNb = _v;
    }

    public String getAliasAbsMenu() {
        return aliasAbsMenu;
    }

    public void setAliasAbsMenu(String _v) {
        this.aliasAbsMenu = _v;
    }

    public String getAliasAbsMenuGetParent() {
        return aliasAbsMenuGetParent;
    }

    public void setAliasAbsMenuGetParent(String _v) {
        this.aliasAbsMenuGetParent = _v;
    }

    public String getAliasAbsMenuIsEnabled() {
        return aliasAbsMenuIsEnabled;
    }

    public void setAliasAbsMenuIsEnabled(String _v) {
        this.aliasAbsMenuIsEnabled = _v;
    }

    public String getAliasMenu() {
        return aliasMenu;
    }

    public void setAliasMenu(String _v) {
        this.aliasMenu = _v;
    }

    public String getAliasMenuAdd() {
        return aliasMenuAdd;
    }

    public void setAliasMenuAdd(String _v) {
        this.aliasMenuAdd = _v;
    }

    public String getAliasMenuGet() {
        return aliasMenuGet;
    }

    public void setAliasMenuGet(String _v) {
        this.aliasMenuGet = _v;
    }

    public String getAliasMenuRemove() {
        return aliasMenuRemove;
    }

    public void setAliasMenuRemove(String _v) {
        this.aliasMenuRemove = _v;
    }

    public String getAliasMenuNb() {
        return aliasMenuNb;
    }

    public void setAliasMenuNb(String _v) {
        this.aliasMenuNb = _v;
    }

    public String getAliasAbsMenuSetEnabled() {
        return aliasAbsMenuSetEnabled;
    }

    public void setAliasAbsMenuSetEnabled(String _v) {
        this.aliasAbsMenuSetEnabled = _v;
    }

    public String getAliasAbsMenuSetDeepEnabled() {
        return aliasAbsMenuSetDeepEnabled;
    }

    public void setAliasAbsMenuSetDeepEnabled(String _v) {
        this.aliasAbsMenuSetDeepEnabled = _v;
    }

    public String getAliasAbsMenuGetText() {
        return aliasAbsMenuGetText;
    }

    public void setAliasAbsMenuGetText(String _v) {
        this.aliasAbsMenuGetText = _v;
    }

    public String getAliasAbsMenuSetText() {
        return aliasAbsMenuSetText;
    }

    public void setAliasAbsMenuSetText(String _v) {
        this.aliasAbsMenuSetText = _v;
    }

    public String getAliasMenuAddSeparator() {
        return aliasMenuAddSeparator;
    }

    public void setAliasMenuAddSeparator(String _v) {
        this.aliasMenuAddSeparator = _v;
    }

    public String getAliasAbsMenuItem() {
        return aliasAbsMenuItem;
    }

    public void setAliasAbsMenuItem(String _v) {
        this.aliasAbsMenuItem = _v;
    }

    public String getAliasAbsMenuItemAddAction() {
        return aliasAbsMenuItemAddAction;
    }

    public void setAliasAbsMenuItemAddAction(String _v) {
        this.aliasAbsMenuItemAddAction = _v;
    }

    public String getAliasMenuItem() {
        return aliasMenuItem;
    }

    public void setAliasMenuItem(String _v) {
        this.aliasMenuItem = _v;
    }

    public String getAliasMenuItemCheck() {
        return aliasMenuItemCheck;
    }

    public void setAliasMenuItemCheck(String _v) {
        this.aliasMenuItemCheck = _v;
    }

    public String getAliasMenuItemCheckIsSelected() {
        return aliasMenuItemCheckIsSelected;
    }

    public void setAliasMenuItemCheckIsSelected(String _v) {
        this.aliasMenuItemCheckIsSelected = _v;
    }

    public String getAliasMenuItemCheckSetSelected() {
        return aliasMenuItemCheckSetSelected;
    }

    public void setAliasMenuItemCheckSetSelected(String _v) {
        this.aliasMenuItemCheckSetSelected = _v;
    }

    public String getAliasCommand() {
        return aliasCommand;
    }

    public void setAliasCommand(String _v) {
        this.aliasCommand = _v;
    }

    public String getAliasCommandBinding() {
        return aliasCommandBinding;
    }

    public void setAliasCommandBinding(String _v) {
        this.aliasCommandBinding = _v;
    }

    public String getAliasCommandAction() {
        return aliasCommandAction;
    }

    public void setAliasCommandAction(String _v) {
        this.aliasCommandAction = _v;
    }

    public String getAliasComponentBind() {
        return aliasComponentBind;
    }

    public void setAliasComponentBind(String _v) {
        this.aliasComponentBind = _v;
    }

    public String getAliasComponentUnbind() {
        return aliasComponentUnbind;
    }

    public void setAliasComponentUnbind(String _v) {
        this.aliasComponentUnbind = _v;
    }

    public String getAliasComponentCommands() {
        return aliasComponentCommands;
    }

    public void setAliasComponentCommands(String _v) {
        this.aliasComponentCommands = _v;
    }
}
