package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.stds.LgNamesContent;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class GuiAliasParameters {
    private static final String ACTION_LISTENER_0_ACTION_PERFORMED_0="___1062";
    private static final String ACTION_0_WRAP_0="___1090";
    private static final String ACTION_0_ENABLED_0="___1091";
    private static final String TREE_LISTENER_0_TREE_LISTENER_VALUE_CHANGED_0="_____1497";
    private static final String TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_0="_____1498";
    private static final String TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_1="_____1499";
    private static final String MOUSE_LISTENER_0_MOUSE_CLICKED_0="___1040";
    private static final String MOUSE_LISTENER_0_MOUSE_PRESSED_0="___1041";
    private static final String MOUSE_LISTENER_0_MOUSE_RELEASED_0="___1042";
    private static final String MOUSE_LISTENER_0_MOUSE_ENTERED_0="___1043";
    private static final String MOUSE_LISTENER_0_MOUSE_EXITED_0="___1044";
    private static final String MOUSE_LISTENER_0_MOUSE_DRAGGED_0="___1045";
    private static final String MOUSE_LISTENER_0_MOUSE_MOVED_0="___1046";
    private static final String WHEEL_LISTENER_0_WHEEL_MOVE_0="___1109";
    private static final String KEY_LISTENER_0_KEY_PRESSED_0="_____1500";
    private static final String KEY_LISTENER_0_KEY_TYPED_0="_____1501";
    private static final String KEY_LISTENER_0_KEY_RELEASED_0="_____1502";
    private static final String WINDOW_LISTENER_0_WINDOW_OPENED_0="_____1503";
    private static final String WINDOW_LISTENER_0_WINDOW_CLOSING_0="_____1504";
    private static final String WINDOW_LISTENER_0_WINDOW_CLOSED_0="_____1505";
    private static final String WINDOW_LISTENER_0_WINDOW_ICONIFIED_0="_____1506";
    private static final String WINDOW_LISTENER_0_WINDOW_DEICONIFIED_0="_____1507";
    private static final String WINDOW_LISTENER_0_WINDOW_ACTIVATED_0="_____1508";
    private static final String WINDOW_LISTENER_0_WINDOW_DEACTIVATED_0="_____1509";
    private static final String LIST_SELECTION_0_VALUE_CHANGED_0="_____1510";
    private static final String LIST_SELECTION_0_VALUE_CHANGED_1="_____1511";
    private static final String LIST_SELECTION_0_VALUE_CHANGED_2="_____1511_";
    private static final String PAINT_0_PAINT_METHOD_0="_____1512";
    private static final String PAINT_0_TABBED_PANE_SET_0="_____1513";
    private static final String PAINT_0_TABBED_PANE_SET_1="_____1514";
    private static final String PAINT_0_TABBED_PANE_SET_2="_____1515";
    private static final String PAINT_0_TABBED_PANE_ADD_0="_____1516";
    private static final String PAINT_0_TABBED_PANE_ADD_1="_____1517";
    private static final String PAINT_0_TABBED_PANE_ADD_2="_____1518";
    private static final String PAINT_1_TABBED_PANE_ADD_0="_____1519";
    private static final String PAINT_1_TABBED_PANE_ADD_1="_____1520";
    private static final String PAINT_0_PAINT_REFRESH_0="_____1521";
    private static final String PAINT_0_PAINT_REFRESH_ONE_0="_____1522";
    private static final String PAINT_0_PAINT_REFRESH_ONE_1="_____1523";
    private static final String PAINT_0_PAINT_REFRESH_ONE_2="_____1524";
    private static final String PAINT_0_PAINT_REFRESH_ONE_3="_____1525";
    private static final String PAINT_0_PAINT_REFRESH_ONE_4="_____1526";
    private static final String PAINT_0_PAINT_REFRESH_ONE_5="_____1527";
    private static final String WINDOW_TYPE_0_SET_CONTENT_0="__925";
    private static final String WINDOW_TYPE_0_WINDOW_TYPE_RELATIVE_0="__926";
    private static final String WINDOW_TYPE_0_COMPONENT_SET_VISIBLE_0="__927";
    private static final String WINDOW_TYPE_0_SET_MENU_BAR_0="__928";
    private static final String WINDOW_TYPE_0_ADD_WINDOW_LISTENER_0="_____1491";
    private static final String WINDOW_TYPE_0_REMOVE_WINDOW_LISTENER_0="_____1492";
    private static final String CONFIRM_0_CONFIRM_FIELD_0="_____1528";
    private static final String CONFIRM_0_CONFIRM_FIELD_1="_____1529";
    private static final String CONFIRM_0_CONFIRM_FIELD_2="_____1530";
    private static final String CONFIRM_0_CONFIRM_FIELD_3="_____1531";
    private static final String CONFIRM_0_CONFIRM_FIELD_4="_____1532";
    private static final String CONFIRM_0_CONFIRM_FIELD_5="_____1533";
    private static final String CONFIRM_0_CONFIRM_FIELD_6="_____1534";
    private static final String CONFIRM_1_CONFIRM_FIELD_0="_____1535";
    private static final String CONFIRM_1_CONFIRM_FIELD_1="_____1536";
    private static final String CONFIRM_1_CONFIRM_FIELD_2="_____1537";
    private static final String CONFIRM_1_CONFIRM_FIELD_3="_____1538";
    private static final String CONFIRM_1_CONFIRM_FIELD_4="_____1539";
    private static final String CONFIRM_1_CONFIRM_FIELD_5="_____1540";
    private static final String CONFIRM_0_CONFIRM_FULL_0="_____1541";
    private static final String CONFIRM_0_CONFIRM_FULL_1="_____1542";
    private static final String CONFIRM_0_CONFIRM_FULL_2="_____1543";
    private static final String CONFIRM_0_CONFIRM_FULL_3="_____1544";
    private static final String CONFIRM_0_CONFIRM_FULL_4="_____1545";
    private static final String CONFIRM_0_CONFIRM_FULL_5="_____1546";
    private static final String CONFIRM_0_CONFIRM_FULL_6="_____1547";
    private static final String CONFIRM_1_CONFIRM_FULL_0="_____1548";
    private static final String CONFIRM_1_CONFIRM_FULL_1="_____1549";
    private static final String CONFIRM_1_CONFIRM_FULL_2="_____1550";
    private static final String CONFIRM_1_CONFIRM_FULL_3="_____1551";
    private static final String CONFIRM_1_CONFIRM_FULL_4="_____1552";
    private static final String CONFIRM_1_CONFIRM_FULL_5="_____1553";
    private static final String CONFIRM_0_CONFIRM_YES_NO_0="_____1554";
    private static final String CONFIRM_0_CONFIRM_YES_NO_1="_____1555";
    private static final String CONFIRM_0_CONFIRM_YES_NO_2="_____1556";
    private static final String CONFIRM_0_CONFIRM_YES_NO_3="_____1557";
    private static final String CONFIRM_0_CONFIRM_YES_NO_4="_____1558";
    private static final String CONFIRM_0_CONFIRM_YES_NO_5="_____1559";
    private static final String CONFIRM_1_CONFIRM_YES_NO_0="_____1560";
    private static final String CONFIRM_1_CONFIRM_YES_NO_1="_____1561";
    private static final String CONFIRM_1_CONFIRM_YES_NO_2="_____1562";
    private static final String CONFIRM_1_CONFIRM_YES_NO_3="_____1563";
    private static final String CONFIRM_1_CONFIRM_YES_NO_4="_____1564";
    private static final String CONFIRM_0_CONFIRM_OK_0="_____1565";
    private static final String CONFIRM_0_CONFIRM_OK_1="_____1566";
    private static final String CONFIRM_0_CONFIRM_OK_2="_____1567";
    private static final String CONFIRM_0_CONFIRM_OK_3="_____1568";
    private static final String CONFIRM_0_CONFIRM_OK_4="_____1569";
    private static final String CONFIRM_1_CONFIRM_OK_0="_____1570";
    private static final String CONFIRM_1_CONFIRM_OK_1="_____1571";
    private static final String CONFIRM_1_CONFIRM_OK_2="_____1572";
    private static final String CONFIRM_1_CONFIRM_OK_3="_____1573";
    private static final String CONFIRM_0_CONFIRM_MESSAGE_0="_____1574";
    private static final String CONFIRM_0_CONFIRM_MESSAGE_1="_____1575";
    private static final String CONFIRM_0_CONFIRM_MESSAGE_2="_____1576";
    private static final String CONFIRM_0_CONFIRM_MESSAGE_3="_____1577";
    private static final String CONFIRM_0_CONFIRM_MESSAGE_4="_____1578";
    private static final String CONFIRM_1_CONFIRM_MESSAGE_0="_____1579";
    private static final String CONFIRM_1_CONFIRM_MESSAGE_1="_____1580";
    private static final String CONFIRM_1_CONFIRM_MESSAGE_2="_____1581";
    private static final String CONFIRM_1_CONFIRM_MESSAGE_3="_____1582";
    private static final String WINDOW_SET_0_TABBED_PANE_ADD_0="_____1583";
    private static final String WINDOW_SET_0_WINDOW_SET_CONTAINS_0="_____1584";
    private static final String WINDOW_SET_0_TREE_NODE_REMOVE_0="_____1585";
    private static final String DIALOG_0_DIALOG_SET_MODAL_0="_____1586";
    private static final String COMPONENT_0_SET_FONT_0="__931";
    private static final String COMPONENT_0_COMP_BACK_0="__932";
    private static final String COMPONENT_0_COMP_FORE_0="__933";
    private static final String COMPONENT_0_COMP_FOCUSABLE_0="__934";
    private static final String COMPONENT_0_COMP_OPAQUE_0="__935";
    private static final String COMPONENT_0_COMP_TOOL_TIP_0="__936";
    private static final String COMPONENT_0_COMP_LOC_0="__937";
    private static final String COMPONENT_0_COMP_LOC_1="__938";
    private static final String COMPONENT_0_COMP_BOR_LINE_0="__939";
    private static final String COMPONENT_1_COMP_BOR_LINE_0="__940";
    private static final String COMPONENT_1_COMP_BOR_LINE_1="__941";
    private static final String COMPONENT_0_COMP_BOR_TITLE_0="__942";
    private static final String COMPONENT_0_COMPONENT_SET_PAINT_0="_____1587";
    private static final String COMPONENT_0_COMPONENT_SET_AUTOSCROLLS_0="__943";
    private static final String COMPONENT_0_COMPONENT_SET_PREFERRED_SIZE_0="__944";
    private static final String COMPONENT_0_COMPONENT_SET_VISIBLE_0="__945";
    private static final String COMPONENT_0_COMPONENT_INVOKE_LATER_0="_____1588";
    private static final String COMPONENT_0_ADD_KEY_LISTENER_0="_____1589";
    private static final String COMPONENT_0_ADD_FOCUS_LISTENER_0="_____1589_";
    private static final String COMPONENT_0_ADD_WHEEL_LISTENER_0="___1104";
    private static final String COMPONENT_0_ADD_LISTENER_0="___1056";
    private static final String COMPONENT_0_REMOVE_KEY_LISTENER_0="_____1590";
    private static final String COMPONENT_0_REMOVE_FOCUS_LISTENER_0="_____1590_";
    private static final String COMPONENT_0_REMOVE_WHEEL_LISTENER_0="___1105";
    private static final String COMPONENT_0_REMOVE_LISTENER_0="___1057";
    private static final String DIMENSION_0_DIMENSION_0="__946";
    private static final String DIMENSION_0_DIMENSION_1="__947";
    private static final String DIMENSION_1_DIMENSION_0="__948";
    private static final String TREE_NODE_0_TREE_NODE_EQ_0="_____1591";
    private static final String TREE_NODE_0_TREE_NODE_EQ_1="_____1592";
    private static final String TREE_NODE_0_TREE_NODE_ADD_0="_____1593";
    private static final String TREE_NODE_0_TREE_NODE_INSERT_0="_____1594";
    private static final String TREE_NODE_0_TREE_NODE_INSERT_1="_____1595";
    private static final String TREE_NODE_0_TREE_NODE_REMOVE_0="_____1596";
    private static final String TREE_NODE_1_TREE_NODE_REMOVE_0="_____1597";
    private static final String TREE_NODE_0_TREE_NODE_SET_USER_OBJECT_0="_____1598";
    private static final String TREE_NODE_0_TREE_NODE_IS_ANCESTOR_0="_____1599";
    private static final String TREE_NODE_0_TREE_NODE_IS_DESCENDANT_0="_____1600";
    private static final String TREE_NODE_0_TREE_NODE_0="_____1601";
    private static final String TREE_0_TREE_ADD_TREE_LISTENER_0="_____1602";
    private static final String TREE_0_TREE_REMOVE_TREE_LISTENER_0="_____1602_";
    private static final String TREE_0_TREE_SET_ROOT_VISIBLE_0="_____1603";
    private static final String TREE_0_TREE_GET_SELECTED_0="_____1604";
    private static final String TREE_0_TREE_0="_____1605";
    private static final String TABLE_GUI_0_TABLE_ADD_HEADER_0="_____1606";
    private static final String TABLE_GUI_0_TABLE_ADD_SELECT_0="_____1607";
    private static final String TABLE_GUI_0_TABLE_SET_MULTIPLE_0="_____1608";
    private static final String TABLE_GUI_0_TABLE_SET_REORDER_0="_____1609";
    private static final String TABLE_GUI_0_TABLE_GET_COLUMN_NAME_0="_____1610";
    private static final String TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_0="_____1611";
    private static final String TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_1="_____1612";
    private static final String TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_0="_____1613";
    private static final String TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_1="_____1614";
    private static final String TABLE_GUI_0_TABLE_SET_ROW_COUNT_0="_____1615";
    private static final String TABLE_GUI_0_TABLE_SET_COLUMNS_0="_____1616";
    private static final String TABLE_GUI_0_TABLE_MOVE_COLUMN_0="_____1617";
    private static final String TABLE_GUI_0_TABLE_MOVE_COLUMN_1="_____1618";
    private static final String TABLE_GUI_0_TABLE_ADD_INTERVAL_0="_____1619";
    private static final String TABLE_GUI_0_TABLE_ADD_INTERVAL_1="_____1620";
    private static final String TABLE_GUI_0_TABLE_REMOVE_INTERVAL_0="_____1621";
    private static final String TABLE_GUI_0_TABLE_REMOVE_INTERVAL_1="_____1622";
    private static final String TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_0="_____1623";
    private static final String TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_1="_____1624";
    private static final String TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_2="_____1625";
    private static final String TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_0="_____1626";
    private static final String TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_1="_____1627";
    private static final String TABLE_GUI_0_TABLE_GUI_0="_____1628";
    private static final String ACTION_EVENT_0_ACTION_EVENT_0="___1067";
    private static final String ACTION_EVENT_0_ACTION_EVENT_1="___1068";
    private static final String ACTION_EVENT_0_ACTION_EVENT_2="___1069";
    private static final String ACTION_EVENT_0_ACTION_EVENT_3="___1070";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_0="___1072";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_1="___1073";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_2="___1074";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_3="___1075";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_4="___1076";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_5="___1077";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_6="___1078";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_7="___1079";
    private static final String MOUSE_EVENT_0_MOUSE_EVENT_8="___1080";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_0="___1111";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_1="___1112";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_2="___1113";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_3="___1114";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_4="___1115";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_5="___1116";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_6="___1117";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_7="___1118";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_8="___1119";
    private static final String WHEEL_EVENT_0_WHEEL_EVENT_9="___1120";
    private static final String KEY_EVENT_0_KEY_EVENT_0="_____1629";
    private static final String KEY_EVENT_0_KEY_EVENT_1="_____1630";
    private static final String KEY_EVENT_0_KEY_EVENT_2="_____1631";
    private static final String KEY_EVENT_0_KEY_EVENT_3="_____1632";
    private static final String KEY_EVENT_0_KEY_EVENT_4="_____1633";
    private static final String PANEL_0_TABBED_PANE_ADD_0="__949";
    private static final String PANEL_1_TABBED_PANE_ADD_0="__950";
    private static final String PANEL_1_TABBED_PANE_ADD_1="__951";
    private static final String PANEL_0_REMOVE_COMPO_0="__952";
    private static final String PANEL_1_REMOVE_COMPO_0="__953";
    private static final String PANEL_0_TREE_NODE_GET_USER_OBJECT_0="__954";
    private static final String PANEL_0_PANEL_GRID_0="__955";
    private static final String PANEL_0_PANEL_GRID_1="__956";
    private static final String PANEL_BORDER_0_TABBED_PANE_ADD_0="__957";
    private static final String PANEL_BORDER_0_TABBED_PANE_ADD_1="__958";
    private static final String TABBED_PANE_0_TABBED_PANE_ADD_0="_____1634";
    private static final String TABBED_PANE_0_TABBED_PANE_ADD_1="_____1635";
    private static final String TABBED_PANE_0_TABBED_PANE_SEL_INDEX_0="_____1636";
    private static final String TABBED_PANE_0_TABBED_PANE_INDEX_0="_____1637";
    private static final String TABBED_PANE_0_TREE_NODE_REMOVE_0="_____1638";
    private static final String TABBED_PANE_1_TREE_NODE_REMOVE_0="_____1639";
    private static final String TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_0="_____1640";
    private static final String TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_1="_____1641";
    private static final String TABBED_PANE_0_TABBED_PANE_SET_TITLE_0="_____1642";
    private static final String TABBED_PANE_0_TABBED_PANE_SET_TITLE_1="_____1643";
    private static final String TABBED_PANE_0_TREE_NODE_GET_USER_OBJECT_0="_____1644";
    private static final String TABBED_PANE_0_TABBED_PANE_GET_TITLE_0="_____1645";
    private static final String SCROLL_PANE_0_SCROLL_PANE_HORIZONTAL_VALUE_0="_____1646";
    private static final String SCROLL_PANE_0_SCROLL_PANE_VERTICAL_VALUE_0="_____1647";
    private static final String SCROLL_PANE_0_SCROLL_PANE_SET_VIEW_0="_____1648";
    private static final String SCROLL_PANE_0_SCROLL_PANE_0="_____1649";
    private static final String SPLIT_PANE_0_SPLIT_PANE_SET_LEFT_0="_____1650";
    private static final String SPLIT_PANE_0_SPLIT_PANE_SET_RIGHT_0="_____1651";
    private static final String SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_LOCATION_0="_____1652";
    private static final String SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_SIZE_0="_____1653";
    private static final String SPLIT_PANE_0_SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE_0="_____1654";
    private static final String SPLIT_PANE_0_SPLIT_PANE_SET_CONTINUOUS_LAYOUT_0="_____1655";
    private static final String SPLIT_PANE_0_SPLIT_PANE_0="_____1656";
    private static final String SPLIT_PANE_0_SPLIT_PANE_1="_____1657";
    private static final String SPLIT_PANE_0_SPLIT_PANE_2="_____1658";
    private static final String INPUT_0_INPUT_SET_ENABLED_0="__1027";
    private static final String SEPARATOR_0_SEPARATOR_SET_ORIENT_0="__1027_";
    private static final String BUTTON_0_ADD_LISTENER_0="___1071";
    private static final String BUTTON_0_REMOVE_LISTENER_0="___1071_";
    private static final String CHECK_BOX_0_ADD_LISTENER_0="___1071__";
    private static final String CHECK_BOX_0_REMOVE_LISTENER_0="___1071___";
    private static final String BUTTON_0_BUTTON_0="__1028";
    private static final String PROG_BAR_0_PROG_BAR_MIN_0="_____1659";
    private static final String PROG_BAR_0_TREE_NODE_GET_USER_OBJECT_0="_____1660";
    private static final String PROG_BAR_0_PROG_BAR_MAX_0="_____1661";
    private static final String PROG_BAR_0_PROG_BAR_OR_0="_____1662";
    private static final String TEXT_LABEL_0_SET_LABEL_TEXT_0="__959";
    private static final String TEXT_LABEL_0_TEXT_LABEL_0="__960";
    private static final String IMAGE_LABEL_0_SET_LABEL_IMAGE_0="__961";
    private static final String IMAGE_LABEL_0_IMAGE_LABEL_0="__962";
    private static final String FONT_0_FONT_STRING_WIDTH_0="__963";
    private static final String FONT_1_FONT_STRING_WIDTH_0="__964";
    private static final String FONT_1_FONT_STRING_WIDTH_1="__965";
    private static final String FONT_0_FONT_STRING_HEIGHT_0="__965_";
    private static final String FONT_0_FONT_0="__966";
    private static final String FONT_1_FONT_0="__967";
    private static final String FONT_1_FONT_1="__968";
    private static final String FONT_1_FONT_2="__969";
    private static final String FONT_1_FONT_3="__970";
    private static final String COLOR_0_COLOR_0="__971";
    private static final String COLOR_1_COLOR_0="__972";
    private static final String COLOR_1_COLOR_1="__973";
    private static final String COLOR_2_COLOR_0="__974";
    private static final String COLOR_2_COLOR_1="__975";
    private static final String COLOR_2_COLOR_2="__976";
    private static final String COLOR_3_COLOR_0="__977";
    private static final String COLOR_3_COLOR_1="__978";
    private static final String COLOR_3_COLOR_2="__979";
    private static final String COLOR_3_COLOR_3="__980";
    private static final String IMAGE_0_TREE_NODE_GET_USER_OBJECT_0="__981";
    private static final String IMAGE_0_TREE_NODE_GET_USER_OBJECT_1="__982";
    private static final String IMAGE_0_TREE_NODE_SET_USER_OBJECT_0="__983";
    private static final String IMAGE_0_TREE_NODE_SET_USER_OBJECT_1="__984";
    private static final String IMAGE_0_TREE_NODE_SET_USER_OBJECT_2="__985";
    private static final String IMAGE_0_IMAGE_SET_COLOR_0="__986";
    private static final String IMAGE_0_SET_FONT_0="__987";
    private static final String IMAGE_0_IMAGE_DRAW_0="__988";
    private static final String IMAGE_0_IMAGE_DRAW_1="__989";
    private static final String IMAGE_0_IMAGE_DRAW_2="__990";
    private static final String IMAGE_1_IMAGE_DRAW_0="__991";
    private static final String IMAGE_1_IMAGE_DRAW_1="__992";
    private static final String IMAGE_1_IMAGE_DRAW_2="__993";
    private static final String IMAGE_0_IMAGE_DRAW_LINE_0="__994";
    private static final String IMAGE_0_IMAGE_DRAW_LINE_1="__995";
    private static final String IMAGE_0_IMAGE_DRAW_LINE_2="__996";
    private static final String IMAGE_0_IMAGE_DRAW_LINE_3="__997";
    private static final String IMAGE_0_IMAGE_DRAW_RECT_0="__998";
    private static final String IMAGE_0_IMAGE_DRAW_RECT_1="__999";
    private static final String IMAGE_0_IMAGE_DRAW_RECT_2="__1000";
    private static final String IMAGE_0_IMAGE_DRAW_RECT_3="__1001";
    private static final String IMAGE_0_IMAGE_DRAW_OVAL_0="__1002";
    private static final String IMAGE_0_IMAGE_DRAW_OVAL_1="__1003";
    private static final String IMAGE_0_IMAGE_DRAW_OVAL_2="__1004";
    private static final String IMAGE_0_IMAGE_DRAW_OVAL_3="__1005";
    private static final String IMAGE_0_IMAGE_DRAW_POLYGON_0="__1006";
    private static final String IMAGE_0_IMAGE_DRAW_POLYGON_1="__1007";
    private static final String IMAGE_0_IMAGE_FILL_RECT_0="__1008";
    private static final String IMAGE_0_IMAGE_FILL_RECT_1="__1009";
    private static final String IMAGE_0_IMAGE_FILL_RECT_2="__1010";
    private static final String IMAGE_0_IMAGE_FILL_RECT_3="__1011";
    private static final String IMAGE_0_IMAGE_FILL_OVAL_0="__1012";
    private static final String IMAGE_0_IMAGE_FILL_OVAL_1="__1013";
    private static final String IMAGE_0_IMAGE_FILL_OVAL_2="__1014";
    private static final String IMAGE_0_IMAGE_FILL_OVAL_3="__1015";
    private static final String IMAGE_0_IMAGE_FILL_POLYGON_0="__1016";
    private static final String IMAGE_0_IMAGE_FILL_POLYGON_1="__1017";
    private static final String IMAGE_0_IMAGE_EQ_0="__1018";
    private static final String IMAGE_0_IMAGE_EQ_1="__1019";
    private static final String IMAGE_0_IMAGE_0="__1020";
    private static final String IMAGE_0_IMAGE_1="__1021";
    private static final String IMAGE_0_IMAGE_2="__1022";
    private static final String RENDER_0_RENDER_SET_HEIGHT_0="_____1663";
    private static final String RENDER_0_RENDER_SET_WIDTH_0="_____1664";
    private static final String RENDER_0_COMPONENT_SET_PAINT_0="_____1665";
    private static final String GR_LIST_0_GR_LIST_SET_RENDER_0="_____1666";
    private static final String GR_LIST_0_GR_LIST_ADD_SELECTION_0="_____1667";
    private static final String GR_LIST_0_GR_LIST_REMOVE_SELECTION_0="_____1668";
    private static final String GR_LIST_0_GR_LIST_SET_VISIBLE_ROW_COUNT_0="_____1669";
    private static final String GR_LIST_0_TABBED_PANE_ADD_0="_____1670";
    private static final String GR_LIST_0_TABBED_PANE_ADD_1="_____1671";
    private static final String GR_LIST_1_TABBED_PANE_ADD_0="_____1672";
    private static final String GR_LIST_1_TABBED_PANE_ADD_1="_____1673";
    private static final String GR_LIST_1_TABBED_PANE_ADD_2="_____1674";
    private static final String GR_LIST_2_TABBED_PANE_ADD_0="_____1675";
    private static final String GR_LIST_0_TREE_NODE_SET_USER_OBJECT_0="_____1676";
    private static final String GR_LIST_0_TREE_NODE_SET_USER_OBJECT_1="_____1677";
    private static final String GR_LIST_0_TREE_NODE_SET_USER_OBJECT_2="_____1678";
    private static final String GR_LIST_1_TREE_NODE_SET_USER_OBJECT_0="_____1679";
    private static final String GR_LIST_1_TREE_NODE_SET_USER_OBJECT_1="_____1680";
    private static final String GR_LIST_0_GR_LIST_SET_SELECTED_INDEXES_0="_____1681";
    private static final String GR_LIST_0_REMOVE_COMPO_0="_____1682";
    private static final String GR_LIST_0_GR_LIST_0="_____1683";
    private static final String COMBO_0_COMBO_ADD_LISTENER_0="_____1684";
    private static final String COMBO_0_COMBO_REMOVE_LISTENER_0="_____1685";
    private static final String COMBO_0_TABBED_PANE_ADD_0="_____1686";
    private static final String COMBO_0_COMBO_SELECT_ITEM_0="_____1687";
    private static final String COMBO_0_COMBO_REMOVE_ITEM_0="_____1688";
    private static final String COMBO_0_COMBO_0="_____1689";
    private static final String COMBO_1_COMBO_0="_____1690";
    private static final String COMBO_1_COMBO_1="_____1691";
    private static final String BUTTON_GROUP_0_TABBED_PANE_ADD_0="_____1692";
    private static final String BUTTON_GROUP_0_TABBED_PANE_REMOVE_0="_____1692_";
    private static final String POPUP_MENU_0_TABBED_PANE_ADD_0="_____1693";
    private static final String POPUP_MENU_0_POPUP_MENU_GET_COMP_0="_____1694";
    private static final String POPUP_MENU_0_POPUP_MENU_REMOVE_COMP_0="_____1695";
    private static final String POPUP_MENU_0_POPUP_MENU_ADD_MENU_0="_____1696";
    private static final String POPUP_MENU_0_POPUP_MENU_GET_MENU_0="_____1697";
    private static final String POPUP_MENU_0_POPUP_MENU_REMOVE_MENU_0="_____1698";
    private static final String POPUP_MENU_0_POPUP_MENU_SHOW_0="_____1699";
    private static final String POPUP_MENU_0_POPUP_MENU_SHOW_1="_____1700";
    private static final String POPUP_MENU_0_POPUP_MENU_SHOW_2="_____1701";
    private static final String RADIO_0_RADIO_SET_SELECTED_0="_____1702";
    private static final String RADIO_0_SET_LABEL_TEXT_0="_____1703";
    private static final String RADIO_0_ADD_CHANGE_0="_____1704";
    private static final String RADIO_0_RADIO_0="_____1705";
    private static final String RADIO_1_RADIO_0="_____1706";
    private static final String RADIO_1_RADIO_1="_____1707";
    private static final String CHECK_BOX_0_RADIO_SET_SELECTED_0="_____1708";
    private static final String CHECK_BOX_0_SET_LABEL_TEXT_0="_____1709";
    private static final String CHECK_BOX_0_CHECK_BOX_0="_____1710";
    private static final String CHECK_BOX_1_CHECK_BOX_0="_____1711";
    private static final String CHECK_BOX_1_CHECK_BOX_1="_____1712";
    private static final String TEXT_FIELD_0_SET_LABEL_TEXT_0="_____1713";
    private static final String TEXT_FIELD_0_TEXT_FIELD_ADD_ACTION_0="_____1714";
    private static final String TEXT_FIELD_0_TEXT_FIELD_REMOVE_ACTION_0="_____1714_";
    private static final String TEXT_FIELD_0_TEXT_FIELD_0="_____1715";
    private static final String TEXT_FIELD_1_TEXT_FIELD_0="_____1716";
    private static final String TEXT_FIELD_2_TEXT_FIELD_0="_____1717";
    private static final String TEXT_FIELD_2_TEXT_FIELD_1="_____1718";
    private static final String TEXT_AREA_0_SET_LABEL_TEXT_0="_____1719";
    private static final String TEXT_AREA_0_TABBED_PANE_ADD_0="_____1720";
    private static final String TEXT_AREA_0_TREE_NODE_INSERT_0="_____1721";
    private static final String TEXT_AREA_0_TREE_NODE_INSERT_1="_____1722";
    private static final String TEXT_AREA_0_TEXT_AREA_SET_SELECTION_START_0="_____1723";
    private static final String TEXT_AREA_0_TEXT_AREA_SET_SELECTION_END_0="_____1724";
    private static final String TEXT_AREA_0_TEXT_AREA_SET_TAB_SIZE_0="_____1725";
    private static final String TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_0="_____1726";
    private static final String TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_1="_____1727";
    private static final String TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_2="_____1728";
    private static final String TEXT_AREA_0_TEXT_AREA_REPLACE_SELECTION_0="_____1729";
    private static final String TEXT_AREA_0_TREE_GET_SELECTED_0="_____1730";
    private static final String TEXT_AREA_0_TREE_GET_SELECTED_1="_____1731";
    private static final String TEXT_AREA_0_TEXT_AREA_0="_____1732";
    private static final String TEXT_AREA_1_TEXT_AREA_0="_____1733";
    private static final String TEXT_AREA_1_TEXT_AREA_1="_____1734";
    private static final String TEXT_AREA_2_TEXT_AREA_0="_____1735";
    private static final String TEXT_AREA_2_TEXT_AREA_1="_____1736";
    private static final String TEXT_AREA_2_TEXT_AREA_2="_____1737";
    private static final String SPINNER_0_SPINNER_SET_MAX_0="_____1738";
    private static final String SPINNER_0_SPINNER_SET_MIN_0="_____1739";
    private static final String SPINNER_0_TREE_NODE_SET_USER_OBJECT_0="_____1740";
    private static final String SPINNER_0_SPINNER_SET_STEP_0="_____1741";
    private static final String SPINNER_0_SPINNER_SET_RANGE_0="_____1742";
    private static final String SPINNER_0_SPINNER_SET_RANGE_1="_____1743";
    private static final String SPINNER_0_SPINNER_SET_RANGE_VALUE_0="_____1744";
    private static final String SPINNER_0_SPINNER_SET_RANGE_VALUE_1="_____1745";
    private static final String SPINNER_0_SPINNER_SET_RANGE_VALUE_2="_____1746";
    private static final String SPINNER_0_ADD_CHANGE_0="_____1747";
    private static final String SPINNER_0_SPINNER_0="_____1748";
    private static final String SPINNER_0_SPINNER_1="_____1749";
    private static final String SPINNER_0_SPINNER_2="_____1750";
    private static final String SPINNER_0_SPINNER_3="_____1751";
    private static final String SLIDER_0_SPINNER_SET_MAX_0="_____1752";
    private static final String SLIDER_0_SPINNER_SET_MIN_0="_____1753";
    private static final String SLIDER_0_TREE_NODE_SET_USER_OBJECT_0="_____1754";
    private static final String SLIDER_0_SLIDER_SET_ORIENTATION_0="_____1755";
    private static final String SLIDER_0_ADD_CHANGE_0="_____1756";
    private static final String SLIDER_0_SLIDER_0="_____1757";
    private static final String SLIDER_1_SLIDER_0="_____1758";
    private static final String SLIDER_1_SLIDER_1="_____1759";
    private static final String SLIDER_2_SLIDER_0="_____1760";
    private static final String SLIDER_2_SLIDER_1="_____1761";
    private static final String SLIDER_2_SLIDER_2="_____1762";
    private static final String SLIDER_3_SLIDER_0="_____1763";
    private static final String SLIDER_3_SLIDER_1="_____1764";
    private static final String SLIDER_3_SLIDER_2="_____1765";
    private static final String SLIDER_3_SLIDER_3="_____1766";
    private static final String MENU_BAR_0_TABBED_PANE_ADD_0="_____1767";
    private static final String MENU_BAR_0_TREE_NODE_REMOVE_0="_____1768";
    private static final String MENU_BAR_0_TREE_NODE_GET_USER_OBJECT_0="_____1769";
    private static final String ABS_MENU_0_ABS_MENU_SET_TEXT_0="_____1770";
    private static final String ABS_MENU_0_INPUT_SET_ENABLED_0="_____1771";
    private static final String ABS_MENU_0_ABS_MENU_SET_DEEP_ENABLED_0="_____1772";
    private static final String MENU_0_TABBED_PANE_ADD_0="_____1773";
    private static final String MENU_0_TREE_NODE_REMOVE_0="_____1774";
    private static final String MENU_0_TREE_NODE_GET_USER_OBJECT_0="_____1775";
    private static final String MENU_0_MENU_0="_____1776";
    private static final String ABS_MENU_ITEM_0_TABBED_PANE_ADD_0="_____1777";
    private static final String MENU_ITEM_0_MENU_ITEM_0="_____1778";
    private static final String MENU_ITEM_CHECK_0_RADIO_SET_SELECTED_0="_____1779";
    private static final String MENU_ITEM_CHECK_0_MENU_ITEM_CHECK_0="_____1780";
    private static final String COMMAND_0_BINDING_0="___1094";
    private static final String COMMAND_0_ACTION_0="___1095";
    private static final String COMPONENT_0_BIND_0="___1096";
    private static final String COMPONENT_0_BIND_1="___1097";
    private static final String COMPONENT_0_BIND_2="___1098";
    private static final String COMPONENT_0_UNBIND_0="___1099";
    private static final String COMPONENT_0_UNBIND_1="___1100";
    private static final String ACTION_LISTENER_IMPLICIT_0_IMPLICIT_0="____1227_";
    private static final String ACTION_LISTENER_IMPLICIT_0_IMPLICIT_1="____1228_";
    private static final String CELL_RENDER_0_GENERATE_0="____1228__";
    private static final String CELL_RENDER_0_GENERATE_1="____1228___";
    private static final String CELL_RENDER_0_GENERATE_2="____1228____";
    private static final String CELL_RENDER_0_GENERATE_3="____1228_____";
    private static final String CELL_RENDER_0_GENERATE_4="____1228______";
    private static final String CELL_RENDER_0_GENERATE_5="____1228_______";
    private static final String CELL_RENDER_0_GENERATE_6="____1228________";
    private static final String CELL_RENDER_IMPLICIT_0_IMPLICIT_0="____1228_________";
    private static final String CELL_RENDER_IMPLICIT_0_IMPLICIT_1="____1228__________";
    private static final String CELL_RENDER_0_GENERATE_0_0="____1228___________";
    private static final String CELL_RENDER_0_GENERATE_0_1="____1228____________";

    private String aliasActionListener0ActionPerformed0;
    private String aliasAction0Wrap0;
    private String aliasAction0Enabled0;
    private String aliasTreeListener0TreeListenerValueChanged0;
    private String aliasTableListener0TableValueTableChanged0;
    private String aliasTableListener0TableValueTableChanged1;
    private String aliasMouseListener0MouseClicked0;
    private String aliasMouseListener0MousePressed0;
    private String aliasMouseListener0MouseReleased0;
    private String aliasMouseListener0MouseEntered0;
    private String aliasMouseListener0MouseExited0;
    private String aliasMouseListener0MouseDragged0;
    private String aliasMouseListener0MouseMoved0;
    private String aliasWheelListener0WheelMove0;
    private String aliasKeyListener0KeyPressed0;
    private String aliasKeyListener0KeyTyped0;
    private String aliasKeyListener0KeyReleased0;
    private String aliasWindowListener0WindowOpened0;
    private String aliasWindowListener0WindowClosing0;
    private String aliasWindowListener0WindowClosed0;
    private String aliasWindowListener0WindowIconified0;
    private String aliasWindowListener0WindowDeiconified0;
    private String aliasWindowListener0WindowActivated0;
    private String aliasWindowListener0WindowDeactivated0;
    private String aliasListSelection0ValueChanged0;
    private String aliasListSelection0ValueChanged1;
    private String aliasListSelection0ValueChanged2;
    private String aliasPaint0PaintMethod0;
    private String aliasPaint0TabbedPaneSet0;
    private String aliasPaint0TabbedPaneSet1;
    private String aliasPaint0TabbedPaneSet2;
    private String aliasPaint0TabbedPaneAdd0;
    private String aliasPaint0TabbedPaneAdd1;
    private String aliasPaint0TabbedPaneAdd2;
    private String aliasPaint1TabbedPaneAdd0;
    private String aliasPaint1TabbedPaneAdd1;
    private String aliasPaint0PaintRefresh0;
    private String aliasPaint0PaintRefreshOne0;
    private String aliasPaint0PaintRefreshOne1;
    private String aliasPaint0PaintRefreshOne2;
    private String aliasPaint0PaintRefreshOne3;
    private String aliasPaint0PaintRefreshOne4;
    private String aliasPaint0PaintRefreshOne5;

    private String aliasWindowType0SetContent0;
    private String aliasWindowType0WindowTypeRelative0;
    private String aliasWindowType0ComponentSetVisible0;
    private String aliasWindowType0SetMenuBar0;
    private String aliasWindowType0AddWindowListener0;
    private String aliasWindowType0RemoveWindowListener0;
    private String aliasConfirm0ConfirmField0;
    private String aliasConfirm0ConfirmField1;
    private String aliasConfirm0ConfirmField2;
    private String aliasConfirm0ConfirmField3;
    private String aliasConfirm0ConfirmField4;
    private String aliasConfirm0ConfirmField5;
    private String aliasConfirm0ConfirmField6;
    private String aliasConfirm1ConfirmField0;
    private String aliasConfirm1ConfirmField1;
    private String aliasConfirm1ConfirmField2;
    private String aliasConfirm1ConfirmField3;
    private String aliasConfirm1ConfirmField4;
    private String aliasConfirm1ConfirmField5;
    private String aliasConfirm0ConfirmFull0;
    private String aliasConfirm0ConfirmFull1;
    private String aliasConfirm0ConfirmFull2;
    private String aliasConfirm0ConfirmFull3;
    private String aliasConfirm0ConfirmFull4;
    private String aliasConfirm0ConfirmFull5;
    private String aliasConfirm0ConfirmFull6;
    private String aliasConfirm1ConfirmFull0;
    private String aliasConfirm1ConfirmFull1;
    private String aliasConfirm1ConfirmFull2;
    private String aliasConfirm1ConfirmFull3;
    private String aliasConfirm1ConfirmFull4;
    private String aliasConfirm1ConfirmFull5;
    private String aliasConfirm0ConfirmYesNo0;
    private String aliasConfirm0ConfirmYesNo1;
    private String aliasConfirm0ConfirmYesNo2;
    private String aliasConfirm0ConfirmYesNo3;
    private String aliasConfirm0ConfirmYesNo4;
    private String aliasConfirm0ConfirmYesNo5;
    private String aliasConfirm1ConfirmYesNo0;
    private String aliasConfirm1ConfirmYesNo1;
    private String aliasConfirm1ConfirmYesNo2;
    private String aliasConfirm1ConfirmYesNo3;
    private String aliasConfirm1ConfirmYesNo4;
    private String aliasConfirm0ConfirmOk0;
    private String aliasConfirm0ConfirmOk1;
    private String aliasConfirm0ConfirmOk2;
    private String aliasConfirm0ConfirmOk3;
    private String aliasConfirm0ConfirmOk4;
    private String aliasConfirm1ConfirmOk0;
    private String aliasConfirm1ConfirmOk1;
    private String aliasConfirm1ConfirmOk2;
    private String aliasConfirm1ConfirmOk3;
    private String aliasConfirm0ConfirmMessage0;
    private String aliasConfirm0ConfirmMessage1;
    private String aliasConfirm0ConfirmMessage2;
    private String aliasConfirm0ConfirmMessage3;
    private String aliasConfirm0ConfirmMessage4;
    private String aliasConfirm1ConfirmMessage0;
    private String aliasConfirm1ConfirmMessage1;
    private String aliasConfirm1ConfirmMessage2;
    private String aliasConfirm1ConfirmMessage3;
    private String aliasWindowSet0TabbedPaneAdd0;
    private String aliasWindowSet0WindowSetContains0;
    private String aliasWindowSet0TreeNodeRemove0;
    private String aliasDialog0DialogSetModal0;
    private String aliasComponent0SetFont0;
    private String aliasComponent0CompBack0;
    private String aliasComponent0CompFore0;
    private String aliasComponent0CompFocusable0;
    private String aliasComponent0CompOpaque0;
    private String aliasComponent0CompToolTip0;
    private String aliasComponent0CompLoc0;
    private String aliasComponent0CompLoc1;
    private String aliasComponent0CompBorLine0;
    private String aliasComponent1CompBorLine0;
    private String aliasComponent1CompBorLine1;
    private String aliasComponent0CompBorTitle0;
    private String aliasComponent0ComponentSetPaint0;
    private String aliasComponent0ComponentSetAutoscrolls0;
    private String aliasComponent0ComponentSetPreferredSize0;
    private String aliasComponent0ComponentSetVisible0;
    private String aliasComponent0ComponentInvokeLater0;
    private String aliasComponent0AddKeyListener0;
    private String aliasComponent0AddFocusListener0;
    private String aliasComponent0AddWheelListener0;
    private String aliasComponent0AddListener0;
    private String aliasComponent0RemoveKeyListener0;
    private String aliasComponent0RemoveFocusListener0;
    private String aliasComponent0RemoveWheelListener0;
    private String aliasComponent0RemoveListener0;
    private String aliasDimension0Dimension0;
    private String aliasDimension0Dimension1;
    private String aliasDimension1Dimension0;
    private String aliasTreeNode0TreeNodeEq0;
    private String aliasTreeNode0TreeNodeEq1;
    private String aliasTreeNode0TreeNodeAdd0;
    private String aliasTreeNode0TreeNodeInsert0;
    private String aliasTreeNode0TreeNodeInsert1;
    private String aliasTreeNode0TreeNodeRemove0;
    private String aliasTreeNode1TreeNodeRemove0;
    private String aliasTreeNode0TreeNodeSetUserObject0;
    private String aliasTreeNode0TreeNodeIsAncestor0;
    private String aliasTreeNode0TreeNodeIsDescendant0;
    private String aliasTreeNode0TreeNode0;
    private String aliasTree0TreeAddTreeListener0;
    private String aliasTree0TreeRemoveTreeListener0;
    private String aliasTree0TreeSetRootVisible0;
    private String aliasTree0TreeGetSelected0;
    private String aliasTree0Tree0;
    private String aliasTableGui0TableAddHeader0;
    private String aliasTableGui0TableAddSelect0;
    private String aliasTableGui0TableSetMultiple0;
    private String aliasTableGui0TableSetReorder0;
    private String aliasTableGui0TableGetColumnName0;
    private String aliasTableGui0TableGetColumnAtPoint0;
    private String aliasTableGui0TableGetColumnAtPoint1;
    private String aliasTableGui0TableGetRowAtPoint0;
    private String aliasTableGui0TableGetRowAtPoint1;
    private String aliasTableGui0TableSetRowCount0;
    private String aliasTableGui0TableSetColumns0;
    private String aliasTableGui0TableMoveColumn0;
    private String aliasTableGui0TableMoveColumn1;
    private String aliasTableGui0TableAddInterval0;
    private String aliasTableGui0TableAddInterval1;
    private String aliasTableGui0TableRemoveInterval0;
    private String aliasTableGui0TableRemoveInterval1;
    private String aliasTableGui0TreeNodeSetUserObject0;
    private String aliasTableGui0TreeNodeSetUserObject1;
    private String aliasTableGui0TreeNodeSetUserObject2;
    private String aliasTableGui0TreeNodeGetUserObject0;
    private String aliasTableGui0TreeNodeGetUserObject1;
    private String aliasTableGui0TableGui0;
    private String aliasActionEvent0ActionEvent0;
    private String aliasActionEvent0ActionEvent1;
    private String aliasActionEvent0ActionEvent2;
    private String aliasActionEvent0ActionEvent3;
    private String aliasMouseEvent0MouseEvent0;
    private String aliasMouseEvent0MouseEvent1;
    private String aliasMouseEvent0MouseEvent2;
    private String aliasMouseEvent0MouseEvent3;
    private String aliasMouseEvent0MouseEvent4;
    private String aliasMouseEvent0MouseEvent5;
    private String aliasMouseEvent0MouseEvent6;
    private String aliasMouseEvent0MouseEvent7;
    private String aliasMouseEvent0MouseEvent8;
    private String aliasWheelEvent0WheelEvent0;
    private String aliasWheelEvent0WheelEvent1;
    private String aliasWheelEvent0WheelEvent2;
    private String aliasWheelEvent0WheelEvent3;
    private String aliasWheelEvent0WheelEvent4;
    private String aliasWheelEvent0WheelEvent5;
    private String aliasWheelEvent0WheelEvent6;
    private String aliasWheelEvent0WheelEvent7;
    private String aliasWheelEvent0WheelEvent8;
    private String aliasWheelEvent0WheelEvent9;
    private String aliasKeyEvent0KeyEvent0;
    private String aliasKeyEvent0KeyEvent1;
    private String aliasKeyEvent0KeyEvent2;
    private String aliasKeyEvent0KeyEvent3;
    private String aliasKeyEvent0KeyEvent4;
    private String aliasPanel0TabbedPaneAdd0;
    private String aliasPanel1TabbedPaneAdd0;
    private String aliasPanel1TabbedPaneAdd1;
    private String aliasPanel0RemoveCompo0;
    private String aliasPanel1RemoveCompo0;
    private String aliasPanel0TreeNodeGetUserObject0;
    private String aliasPanel0PanelGrid0;
    private String aliasPanel0PanelGrid1;
    private String aliasPanelBorder0TabbedPaneAdd0;
    private String aliasPanelBorder0TabbedPaneAdd1;
    private String aliasTabbedPane0TabbedPaneAdd0;
    private String aliasTabbedPane0TabbedPaneAdd1;
    private String aliasTabbedPane0TabbedPaneSelIndex0;
    private String aliasTabbedPane0TabbedPaneIndex0;
    private String aliasTabbedPane0TreeNodeRemove0;
    private String aliasTabbedPane1TreeNodeRemove0;
    private String aliasTabbedPane0TreeNodeSetUserObject0;
    private String aliasTabbedPane0TreeNodeSetUserObject1;
    private String aliasTabbedPane0TabbedPaneSetTitle0;
    private String aliasTabbedPane0TabbedPaneSetTitle1;
    private String aliasTabbedPane0TreeNodeGetUserObject0;
    private String aliasTabbedPane0TabbedPaneGetTitle0;
    private String aliasScrollPane0ScrollPaneHorizontalValue0;
    private String aliasScrollPane0ScrollPaneVerticalValue0;
    private String aliasScrollPane0ScrollPaneSetView0;
    private String aliasScrollPane0ScrollPane0;
    private String aliasSplitPane0SplitPaneSetLeft0;
    private String aliasSplitPane0SplitPaneSetRight0;
    private String aliasSplitPane0SplitPaneSetDividerLocation0;
    private String aliasSplitPane0SplitPaneSetDividerSize0;
    private String aliasSplitPane0SplitPaneSetOneTouchExpandable0;
    private String aliasSplitPane0SplitPaneSetContinuousLayout0;
    private String aliasSplitPane0SplitPane0;
    private String aliasSplitPane0SplitPane1;
    private String aliasSplitPane0SplitPane2;
    private String aliasInput0InputSetEnabled0;
    private String aliasSeparator0SeparatorSetOrient0;
    private String aliasButton0AddListener0;
    private String aliasButton0RemoveListener0;
    private String aliasCheckBox0AddListener0;
    private String aliasCheckBox0RemoveListener0;
    private String aliasButton0Button0;
    private String aliasProgBar0ProgBarMin0;
    private String aliasProgBar0TreeNodeGetUserObject0;
    private String aliasProgBar0ProgBarMax0;
    private String aliasProgBar0ProgBarOr0;
    private String aliasTextLabel0SetLabelText0;
    private String aliasTextLabel0TextLabel0;
    private String aliasImageLabel0SetLabelImage0;
    private String aliasImageLabel0ImageLabel0;
    private String aliasFont0FontStringHeight0;
    private String aliasFont0FontStringWidth0;
    private String aliasFont1FontStringWidth0;
    private String aliasFont1FontStringWidth1;
    private String aliasFont0Font0;
    private String aliasFont1Font0;
    private String aliasFont1Font1;
    private String aliasFont1Font2;
    private String aliasFont1Font3;
    private String aliasColor0Color0;
    private String aliasColor1Color0;
    private String aliasColor1Color1;
    private String aliasColor2Color0;
    private String aliasColor2Color1;
    private String aliasColor2Color2;
    private String aliasColor3Color0;
    private String aliasColor3Color1;
    private String aliasColor3Color2;
    private String aliasColor3Color3;
    private String aliasImage0TreeNodeGetUserObject0;
    private String aliasImage0TreeNodeGetUserObject1;
    private String aliasImage0TreeNodeSetUserObject0;
    private String aliasImage0TreeNodeSetUserObject1;
    private String aliasImage0TreeNodeSetUserObject2;
    private String aliasImage0ImageSetColor0;
    private String aliasImage0SetFont0;
    private String aliasImage0ImageDraw0;
    private String aliasImage0ImageDraw1;
    private String aliasImage0ImageDraw2;
    private String aliasImage1ImageDraw0;
    private String aliasImage1ImageDraw1;
    private String aliasImage1ImageDraw2;
    private String aliasImage0ImageDrawLine0;
    private String aliasImage0ImageDrawLine1;
    private String aliasImage0ImageDrawLine2;
    private String aliasImage0ImageDrawLine3;
    private String aliasImage0ImageDrawRect0;
    private String aliasImage0ImageDrawRect1;
    private String aliasImage0ImageDrawRect2;
    private String aliasImage0ImageDrawRect3;
    private String aliasImage0ImageDrawOval0;
    private String aliasImage0ImageDrawOval1;
    private String aliasImage0ImageDrawOval2;
    private String aliasImage0ImageDrawOval3;
    private String aliasImage0ImageDrawPolygon0;
    private String aliasImage0ImageDrawPolygon1;
    private String aliasImage0ImageFillRect0;
    private String aliasImage0ImageFillRect1;
    private String aliasImage0ImageFillRect2;
    private String aliasImage0ImageFillRect3;
    private String aliasImage0ImageFillOval0;
    private String aliasImage0ImageFillOval1;
    private String aliasImage0ImageFillOval2;
    private String aliasImage0ImageFillOval3;
    private String aliasImage0ImageFillPolygon0;
    private String aliasImage0ImageFillPolygon1;
    private String aliasImage0ImageEq0;
    private String aliasImage0ImageEq1;
    private String aliasImage0Image0;
    private String aliasImage0Image1;
    private String aliasImage0Image2;
    private String aliasRender0RenderSetHeight0;
    private String aliasRender0RenderSetWidth0;
    private String aliasRender0ComponentSetPaint0;
    private String aliasGrList0GrListSetRender0;
    private String aliasGrList0GrListAddSelection0;
    private String aliasGrList0GrListRemoveSelection0;
    private String aliasGrList0GrListSetVisibleRowCount0;
    private String aliasGrList0TabbedPaneAdd0;
    private String aliasGrList0TabbedPaneAdd1;
    private String aliasGrList1TabbedPaneAdd0;
    private String aliasGrList1TabbedPaneAdd1;
    private String aliasGrList1TabbedPaneAdd2;
    private String aliasGrList2TabbedPaneAdd0;
    private String aliasGrList0TreeNodeSetUserObject0;
    private String aliasGrList0TreeNodeSetUserObject1;
    private String aliasGrList0TreeNodeSetUserObject2;
    private String aliasGrList1TreeNodeSetUserObject0;
    private String aliasGrList1TreeNodeSetUserObject1;
    private String aliasGrList0GrListSetSelectedIndexes0;
    private String aliasGrList0RemoveCompo0;
    private String aliasGrList0GrList0;
    private String aliasCombo0ComboAddListener0;
    private String aliasCombo0ComboRemoveListener0;
    private String aliasCombo0TabbedPaneAdd0;
    private String aliasCombo0ComboSelectItem0;
    private String aliasCombo0ComboRemoveItem0;
    private String aliasCombo0Combo0;
    private String aliasCombo1Combo0;
    private String aliasCombo1Combo1;
    private String aliasButtonGroup0TabbedPaneAdd0;
    private String aliasButtonGroup0TabbedPaneRemove0;
    private String aliasPopupMenu0TabbedPaneAdd0;
    private String aliasPopupMenu0PopupMenuGetComp0;
    private String aliasPopupMenu0PopupMenuRemoveComp0;
    private String aliasPopupMenu0PopupMenuAddMenu0;
    private String aliasPopupMenu0PopupMenuGetMenu0;
    private String aliasPopupMenu0PopupMenuRemoveMenu0;
    private String aliasPopupMenu0PopupMenuShow0;
    private String aliasPopupMenu0PopupMenuShow1;
    private String aliasPopupMenu0PopupMenuShow2;
    private String aliasRadio0RadioSetSelected0;
    private String aliasRadio0SetLabelText0;
    private String aliasRadio0Radio0;
    private String aliasRadio1Radio0;
    private String aliasRadio1Radio1;
    private String aliasCheckBox0RadioSetSelected0;
    private String aliasCheckBox0SetLabelText0;
    private String aliasCheckBox0CheckBox0;
    private String aliasCheckBox1CheckBox0;
    private String aliasCheckBox1CheckBox1;
    private String aliasTextField0SetLabelText0;
    private String aliasTextField0TextFieldAddAction0;
    private String aliasTextField0TextFieldRemoveAction0;
    private String aliasTextField0TextField0;
    private String aliasTextField1TextField0;
    private String aliasTextField2TextField0;
    private String aliasTextField2TextField1;
    private String aliasTextArea0SetLabelText0;
    private String aliasTextArea0TabbedPaneAdd0;
    private String aliasTextArea0TreeNodeInsert0;
    private String aliasTextArea0TreeNodeInsert1;
    private String aliasTextArea0TextAreaSetSelectionStart0;
    private String aliasTextArea0TextAreaSetSelectionEnd0;
    private String aliasTextArea0TextAreaSetTabSize0;
    private String aliasTextArea0TextAreaReplaceRange0;
    private String aliasTextArea0TextAreaReplaceRange1;
    private String aliasTextArea0TextAreaReplaceRange2;
    private String aliasTextArea0TextAreaReplaceSelection0;
    private String aliasTextArea0TreeGetSelected0;
    private String aliasTextArea0TreeGetSelected1;
    private String aliasTextArea0TextArea0;
    private String aliasTextArea1TextArea0;
    private String aliasTextArea1TextArea1;
    private String aliasTextArea2TextArea0;
    private String aliasTextArea2TextArea1;
    private String aliasTextArea2TextArea2;
    private String aliasSpinner0SpinnerSetMax0;
    private String aliasSpinner0SpinnerSetMin0;
    private String aliasSpinner0TreeNodeSetUserObject0;
    private String aliasSpinner0SpinnerSetStep0;
    private String aliasSpinner0SpinnerSetRange0;
    private String aliasSpinner0SpinnerSetRange1;
    private String aliasSpinner0SpinnerSetRangeValue0;
    private String aliasSpinner0SpinnerSetRangeValue1;
    private String aliasSpinner0SpinnerSetRangeValue2;
    private String aliasSpinner0AddChange0;
    private String aliasSpinner0Spinner0;
    private String aliasSpinner0Spinner1;
    private String aliasSpinner0Spinner2;
    private String aliasSpinner0Spinner3;
    private String aliasSlider0SpinnerSetMax0;
    private String aliasSlider0SpinnerSetMin0;
    private String aliasSlider0TreeNodeSetUserObject0;
    private String aliasSlider0SliderSetOrientation0;
    private String aliasSlider0AddChange0;
    private String aliasSlider0Slider0;
    private String aliasSlider1Slider0;
    private String aliasSlider1Slider1;
    private String aliasSlider2Slider0;
    private String aliasSlider2Slider1;
    private String aliasSlider2Slider2;
    private String aliasSlider3Slider0;
    private String aliasSlider3Slider1;
    private String aliasSlider3Slider2;
    private String aliasSlider3Slider3;
    private String aliasMenuBar0TabbedPaneAdd0;
    private String aliasMenuBar0TreeNodeRemove0;
    private String aliasMenuBar0TreeNodeGetUserObject0;
    private String aliasAbsMenu0AbsMenuSetText0;
    private String aliasAbsMenu0InputSetEnabled0;
    private String aliasAbsMenu0AbsMenuSetDeepEnabled0;
    private String aliasMenu0TabbedPaneAdd0;
    private String aliasMenu0TreeNodeRemove0;
    private String aliasMenu0TreeNodeGetUserObject0;
    private String aliasMenu0Menu0;
    private String aliasAbsMenuItem0TabbedPaneAdd0;
    private String aliasMenuItem0MenuItem0;
    private String aliasMenuItemCheck0RadioSetSelected0;
    private String aliasMenuItemCheck0MenuItemCheck0;
    private String aliasCommand0Binding0;
    private String aliasCommand0Action0;
    private String aliasComponent0Bind0;
    private String aliasComponent0Bind1;
    private String aliasComponent0Bind2;
    private String aliasComponent0Unbind0;
    private String aliasComponent0Unbind1;
    private String aliasActionListenerImplicit0Implicit0;
    private String aliasActionListenerImplicit0Implicit1;
    private String aliasCellRender0Generate0;
    private String aliasCellRender0Generate1;
    private String aliasCellRender0Generate2;
    private String aliasCellRender0Generate3;
    private String aliasCellRender0Generate4;
    private String aliasCellRender0Generate5;
    private String aliasCellRender0Generate6;
    private String aliasCellRenderImplicit0Implicit0;
    private String aliasCellRenderImplicit0Implicit1;
    private String aliasCellRender0Generate00;
    private String aliasCellRender0Generate01;

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        aliasActionListener0ActionPerformed0=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_LISTENER_0_ACTION_PERFORMED_0));
        aliasAction0Wrap0=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_0_WRAP_0));
        aliasAction0Enabled0=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_0_ENABLED_0));
        aliasTreeListener0TreeListenerValueChanged0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_LISTENER_0_TREE_LISTENER_VALUE_CHANGED_0));
        aliasTableListener0TableValueTableChanged0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_0));
        aliasTableListener0TableValueTableChanged1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_1));
        aliasMouseListener0MouseClicked0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_CLICKED_0));
        aliasMouseListener0MousePressed0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_PRESSED_0));
        aliasMouseListener0MouseReleased0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_RELEASED_0));
        aliasMouseListener0MouseEntered0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_ENTERED_0));
        aliasMouseListener0MouseExited0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_EXITED_0));
        aliasMouseListener0MouseDragged0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_DRAGGED_0));
        aliasMouseListener0MouseMoved0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_LISTENER_0_MOUSE_MOVED_0));
        aliasWheelListener0WheelMove0=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_LISTENER_0_WHEEL_MOVE_0));
        aliasKeyListener0KeyPressed0=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_LISTENER_0_KEY_PRESSED_0));
        aliasKeyListener0KeyTyped0=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_LISTENER_0_KEY_TYPED_0));
        aliasKeyListener0KeyReleased0=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_LISTENER_0_KEY_RELEASED_0));
        aliasWindowListener0WindowOpened0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_OPENED_0));
        aliasWindowListener0WindowClosing0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_CLOSING_0));
        aliasWindowListener0WindowClosed0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_CLOSED_0));
        aliasWindowListener0WindowIconified0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_ICONIFIED_0));
        aliasWindowListener0WindowDeiconified0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_DEICONIFIED_0));
        aliasWindowListener0WindowActivated0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_ACTIVATED_0));
        aliasWindowListener0WindowDeactivated0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_LISTENER_0_WINDOW_DEACTIVATED_0));
        aliasListSelection0ValueChanged0=LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_SELECTION_0_VALUE_CHANGED_0));
        aliasListSelection0ValueChanged1=LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_SELECTION_0_VALUE_CHANGED_1));
        aliasListSelection0ValueChanged2=LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_SELECTION_0_VALUE_CHANGED_2));
        aliasPaint0PaintMethod0=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_METHOD_0));
        aliasPaint0TabbedPaneSet0=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_TABBED_PANE_SET_0));
        aliasPaint0TabbedPaneSet1=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_TABBED_PANE_SET_1));
        aliasPaint0TabbedPaneSet2=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_TABBED_PANE_SET_2));
        aliasPaint0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_TABBED_PANE_ADD_0));
        aliasPaint0TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_TABBED_PANE_ADD_1));
        aliasPaint0TabbedPaneAdd2=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_TABBED_PANE_ADD_2));
        aliasPaint1TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_1_TABBED_PANE_ADD_0));
        aliasPaint1TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_1_TABBED_PANE_ADD_1));
        aliasPaint0PaintRefresh0=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_0));
        aliasPaint0PaintRefreshOne0=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_0));
        aliasPaint0PaintRefreshOne1=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_1));
        aliasPaint0PaintRefreshOne2=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_2));
        aliasPaint0PaintRefreshOne3=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_3));
        aliasPaint0PaintRefreshOne4=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_4));
        aliasPaint0PaintRefreshOne5=LgNamesContent.get(_util,_cust,_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_5));
        aliasWindowType0SetContent0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_0_SET_CONTENT_0));
        aliasWindowType0WindowTypeRelative0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_0_WINDOW_TYPE_RELATIVE_0));
        aliasWindowType0ComponentSetVisible0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_0_COMPONENT_SET_VISIBLE_0));
        aliasWindowType0SetMenuBar0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_0_SET_MENU_BAR_0));
        aliasWindowType0AddWindowListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_0_ADD_WINDOW_LISTENER_0));
        aliasWindowType0RemoveWindowListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_TYPE_0_REMOVE_WINDOW_LISTENER_0));
        aliasConfirm0ConfirmField0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_0));
        aliasConfirm0ConfirmField1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_1));
        aliasConfirm0ConfirmField2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_2));
        aliasConfirm0ConfirmField3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_3));
        aliasConfirm0ConfirmField4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_4));
        aliasConfirm0ConfirmField5=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_5));
        aliasConfirm0ConfirmField6=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_6));
        aliasConfirm1ConfirmField0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_0));
        aliasConfirm1ConfirmField1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_1));
        aliasConfirm1ConfirmField2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_2));
        aliasConfirm1ConfirmField3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_3));
        aliasConfirm1ConfirmField4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_4));
        aliasConfirm1ConfirmField5=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_5));
        aliasConfirm0ConfirmFull0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_0));
        aliasConfirm0ConfirmFull1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_1));
        aliasConfirm0ConfirmFull2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_2));
        aliasConfirm0ConfirmFull3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_3));
        aliasConfirm0ConfirmFull4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_4));
        aliasConfirm0ConfirmFull5=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_5));
        aliasConfirm0ConfirmFull6=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_FULL_6));
        aliasConfirm1ConfirmFull0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FULL_0));
        aliasConfirm1ConfirmFull1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FULL_1));
        aliasConfirm1ConfirmFull2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FULL_2));
        aliasConfirm1ConfirmFull3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FULL_3));
        aliasConfirm1ConfirmFull4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FULL_4));
        aliasConfirm1ConfirmFull5=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_FULL_5));
        aliasConfirm0ConfirmYesNo0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_0));
        aliasConfirm0ConfirmYesNo1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_1));
        aliasConfirm0ConfirmYesNo2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_2));
        aliasConfirm0ConfirmYesNo3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_3));
        aliasConfirm0ConfirmYesNo4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_4));
        aliasConfirm0ConfirmYesNo5=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_5));
        aliasConfirm1ConfirmYesNo0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_0));
        aliasConfirm1ConfirmYesNo1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_1));
        aliasConfirm1ConfirmYesNo2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_2));
        aliasConfirm1ConfirmYesNo3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_3));
        aliasConfirm1ConfirmYesNo4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_4));
        aliasConfirm0ConfirmOk0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_OK_0));
        aliasConfirm0ConfirmOk1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_OK_1));
        aliasConfirm0ConfirmOk2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_OK_2));
        aliasConfirm0ConfirmOk3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_OK_3));
        aliasConfirm0ConfirmOk4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_OK_4));
        aliasConfirm1ConfirmOk0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_OK_0));
        aliasConfirm1ConfirmOk1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_OK_1));
        aliasConfirm1ConfirmOk2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_OK_2));
        aliasConfirm1ConfirmOk3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_OK_3));
        aliasConfirm0ConfirmMessage0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_0));
        aliasConfirm0ConfirmMessage1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_1));
        aliasConfirm0ConfirmMessage2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_2));
        aliasConfirm0ConfirmMessage3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_3));
        aliasConfirm0ConfirmMessage4=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_4));
        aliasConfirm1ConfirmMessage0=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_0));
        aliasConfirm1ConfirmMessage1=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_1));
        aliasConfirm1ConfirmMessage2=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_2));
        aliasConfirm1ConfirmMessage3=LgNamesContent.get(_util,_cust,_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_3));
        aliasWindowSet0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_0_TABBED_PANE_ADD_0));
        aliasWindowSet0WindowSetContains0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_0_WINDOW_SET_CONTAINS_0));
        aliasWindowSet0TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(WINDOW_SET_0_TREE_NODE_REMOVE_0));
        aliasDialog0DialogSetModal0=LgNamesContent.get(_util,_cust,_mapping.getVal(DIALOG_0_DIALOG_SET_MODAL_0));
        aliasComponent0SetFont0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_SET_FONT_0));
        aliasComponent0CompBack0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_BACK_0));
        aliasComponent0CompFore0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_FORE_0));
        aliasComponent0CompFocusable0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_FOCUSABLE_0));
        aliasComponent0CompOpaque0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_OPAQUE_0));
        aliasComponent0CompToolTip0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_TOOL_TIP_0));
        aliasComponent0CompLoc0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_LOC_0));
        aliasComponent0CompLoc1=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_LOC_1));
        aliasComponent0CompBorLine0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_BOR_LINE_0));
        aliasComponent1CompBorLine0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_1_COMP_BOR_LINE_0));
        aliasComponent1CompBorLine1=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_1_COMP_BOR_LINE_1));
        aliasComponent0CompBorTitle0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMP_BOR_TITLE_0));
        aliasComponent0ComponentSetPaint0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMPONENT_SET_PAINT_0));
        aliasComponent0ComponentSetAutoscrolls0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMPONENT_SET_AUTOSCROLLS_0));
        aliasComponent0ComponentSetPreferredSize0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMPONENT_SET_PREFERRED_SIZE_0));
        aliasComponent0ComponentSetVisible0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMPONENT_SET_VISIBLE_0));
        aliasComponent0ComponentInvokeLater0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_COMPONENT_INVOKE_LATER_0));
        aliasComponent0AddKeyListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_ADD_KEY_LISTENER_0));
        aliasComponent0AddFocusListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_ADD_FOCUS_LISTENER_0));
        aliasComponent0AddWheelListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_ADD_WHEEL_LISTENER_0));
        aliasComponent0AddListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_ADD_LISTENER_0));
        aliasComponent0RemoveKeyListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_REMOVE_KEY_LISTENER_0));
        aliasComponent0RemoveFocusListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_REMOVE_FOCUS_LISTENER_0));
        aliasComponent0RemoveWheelListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_REMOVE_WHEEL_LISTENER_0));
        aliasComponent0RemoveListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_REMOVE_LISTENER_0));
        aliasDimension0Dimension0=LgNamesContent.get(_util,_cust,_mapping.getVal(DIMENSION_0_DIMENSION_0));
        aliasDimension0Dimension1=LgNamesContent.get(_util,_cust,_mapping.getVal(DIMENSION_0_DIMENSION_1));
        aliasDimension1Dimension0=LgNamesContent.get(_util,_cust,_mapping.getVal(DIMENSION_1_DIMENSION_0));
        aliasTreeNode0TreeNodeEq0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_EQ_0));
        aliasTreeNode0TreeNodeEq1=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_EQ_1));
        aliasTreeNode0TreeNodeAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_ADD_0));
        aliasTreeNode0TreeNodeInsert0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_INSERT_0));
        aliasTreeNode0TreeNodeInsert1=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_INSERT_1));
        aliasTreeNode0TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_REMOVE_0));
        aliasTreeNode1TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_1_TREE_NODE_REMOVE_0));
        aliasTreeNode0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasTreeNode0TreeNodeIsAncestor0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_IS_ANCESTOR_0));
        aliasTreeNode0TreeNodeIsDescendant0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_IS_DESCENDANT_0));
        aliasTreeNode0TreeNode0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_NODE_0_TREE_NODE_0));
        aliasTree0TreeAddTreeListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_0_TREE_ADD_TREE_LISTENER_0));
        aliasTree0TreeRemoveTreeListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_0_TREE_REMOVE_TREE_LISTENER_0));
        aliasTree0TreeSetRootVisible0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_0_TREE_SET_ROOT_VISIBLE_0));
        aliasTree0TreeGetSelected0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_0_TREE_GET_SELECTED_0));
        aliasTree0Tree0=LgNamesContent.get(_util,_cust,_mapping.getVal(TREE_0_TREE_0));
        aliasTableGui0TableAddHeader0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_ADD_HEADER_0));
        aliasTableGui0TableAddSelect0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_ADD_SELECT_0));
        aliasTableGui0TableSetMultiple0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_SET_MULTIPLE_0));
        aliasTableGui0TableSetReorder0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_SET_REORDER_0));
        aliasTableGui0TableGetColumnName0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_GET_COLUMN_NAME_0));
        aliasTableGui0TableGetColumnAtPoint0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_0));
        aliasTableGui0TableGetColumnAtPoint1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_1));
        aliasTableGui0TableGetRowAtPoint0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_0));
        aliasTableGui0TableGetRowAtPoint1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_1));
        aliasTableGui0TableSetRowCount0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_SET_ROW_COUNT_0));
        aliasTableGui0TableSetColumns0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_SET_COLUMNS_0));
        aliasTableGui0TableMoveColumn0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_MOVE_COLUMN_0));
        aliasTableGui0TableMoveColumn1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_MOVE_COLUMN_1));
        aliasTableGui0TableAddInterval0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_ADD_INTERVAL_0));
        aliasTableGui0TableAddInterval1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_ADD_INTERVAL_1));
        aliasTableGui0TableRemoveInterval0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_0));
        aliasTableGui0TableRemoveInterval1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_1));
        aliasTableGui0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasTableGui0TreeNodeSetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_1));
        aliasTableGui0TreeNodeSetUserObject2=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_2));
        aliasTableGui0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasTableGui0TreeNodeGetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_1));
        aliasTableGui0TableGui0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_GUI_0_TABLE_GUI_0));
        aliasActionEvent0ActionEvent0=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_0));
        aliasActionEvent0ActionEvent1=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_1));
        aliasActionEvent0ActionEvent2=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_2));
        aliasActionEvent0ActionEvent3=LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_3));
        aliasMouseEvent0MouseEvent0=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_0));
        aliasMouseEvent0MouseEvent1=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_1));
        aliasMouseEvent0MouseEvent2=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_2));
        aliasMouseEvent0MouseEvent3=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_3));
        aliasMouseEvent0MouseEvent4=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_4));
        aliasMouseEvent0MouseEvent5=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_5));
        aliasMouseEvent0MouseEvent6=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_6));
        aliasMouseEvent0MouseEvent7=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_7));
        aliasMouseEvent0MouseEvent8=LgNamesContent.get(_util,_cust,_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_8));
        aliasWheelEvent0WheelEvent0=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_0));
        aliasWheelEvent0WheelEvent1=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_1));
        aliasWheelEvent0WheelEvent2=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_2));
        aliasWheelEvent0WheelEvent3=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_3));
        aliasWheelEvent0WheelEvent4=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_4));
        aliasWheelEvent0WheelEvent5=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_5));
        aliasWheelEvent0WheelEvent6=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_6));
        aliasWheelEvent0WheelEvent7=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_7));
        aliasWheelEvent0WheelEvent8=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_8));
        aliasWheelEvent0WheelEvent9=LgNamesContent.get(_util,_cust,_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_9));
        aliasKeyEvent0KeyEvent0=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_0_KEY_EVENT_0));
        aliasKeyEvent0KeyEvent1=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_0_KEY_EVENT_1));
        aliasKeyEvent0KeyEvent2=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_0_KEY_EVENT_2));
        aliasKeyEvent0KeyEvent3=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_0_KEY_EVENT_3));
        aliasKeyEvent0KeyEvent4=LgNamesContent.get(_util,_cust,_mapping.getVal(KEY_EVENT_0_KEY_EVENT_4));
        aliasPanel0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_0_TABBED_PANE_ADD_0));
        aliasPanel1TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_1_TABBED_PANE_ADD_0));
        aliasPanel1TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_1_TABBED_PANE_ADD_1));
        aliasPanel0RemoveCompo0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_0_REMOVE_COMPO_0));
        aliasPanel1RemoveCompo0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_1_REMOVE_COMPO_0));
        aliasPanel0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasPanel0PanelGrid0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_0_PANEL_GRID_0));
        aliasPanel0PanelGrid1=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_0_PANEL_GRID_1));
        aliasPanelBorder0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_0_TABBED_PANE_ADD_0));
        aliasPanelBorder0TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(PANEL_BORDER_0_TABBED_PANE_ADD_1));
        aliasTabbedPane0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_ADD_0));
        aliasTabbedPane0TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_ADD_1));
        aliasTabbedPane0TabbedPaneSelIndex0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_SEL_INDEX_0));
        aliasTabbedPane0TabbedPaneIndex0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_INDEX_0));
        aliasTabbedPane0TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TREE_NODE_REMOVE_0));
        aliasTabbedPane1TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_1_TREE_NODE_REMOVE_0));
        aliasTabbedPane0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasTabbedPane0TreeNodeSetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_1));
        aliasTabbedPane0TabbedPaneSetTitle0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_SET_TITLE_0));
        aliasTabbedPane0TabbedPaneSetTitle1=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_SET_TITLE_1));
        aliasTabbedPane0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasTabbedPane0TabbedPaneGetTitle0=LgNamesContent.get(_util,_cust,_mapping.getVal(TABBED_PANE_0_TABBED_PANE_GET_TITLE_0));
        aliasScrollPane0ScrollPaneHorizontalValue0=LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_HORIZONTAL_VALUE_0));
        aliasScrollPane0ScrollPaneVerticalValue0=LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_VERTICAL_VALUE_0));
        aliasScrollPane0ScrollPaneSetView0=LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_SET_VIEW_0));
        aliasScrollPane0ScrollPane0=LgNamesContent.get(_util,_cust,_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_0));
        aliasSplitPane0SplitPaneSetLeft0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_LEFT_0));
        aliasSplitPane0SplitPaneSetRight0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_RIGHT_0));
        aliasSplitPane0SplitPaneSetDividerLocation0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_LOCATION_0));
        aliasSplitPane0SplitPaneSetDividerSize0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_SIZE_0));
        aliasSplitPane0SplitPaneSetOneTouchExpandable0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE_0));
        aliasSplitPane0SplitPaneSetContinuousLayout0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_CONTINUOUS_LAYOUT_0));
        aliasSplitPane0SplitPane0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_0));
        aliasSplitPane0SplitPane1=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_1));
        aliasSplitPane0SplitPane2=LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_2));
        aliasInput0InputSetEnabled0=LgNamesContent.get(_util,_cust,_mapping.getVal(INPUT_0_INPUT_SET_ENABLED_0));
        aliasSeparator0SeparatorSetOrient0=LgNamesContent.get(_util,_cust,_mapping.getVal(SEPARATOR_0_SEPARATOR_SET_ORIENT_0));
        aliasButton0AddListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_0_ADD_LISTENER_0));
        aliasButton0RemoveListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_0_REMOVE_LISTENER_0));
        aliasCheckBox0AddListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_0_ADD_LISTENER_0));
        aliasCheckBox0RemoveListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_0_REMOVE_LISTENER_0));
        aliasButton0Button0=LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_0_BUTTON_0));
        aliasProgBar0ProgBarMin0=LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_0_PROG_BAR_MIN_0));
        aliasProgBar0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasProgBar0ProgBarMax0=LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_0_PROG_BAR_MAX_0));
        aliasProgBar0ProgBarOr0=LgNamesContent.get(_util,_cust,_mapping.getVal(PROG_BAR_0_PROG_BAR_OR_0));
        aliasTextLabel0SetLabelText0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_LABEL_0_SET_LABEL_TEXT_0));
        aliasTextLabel0TextLabel0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_LABEL_0_TEXT_LABEL_0));
        aliasImageLabel0SetLabelImage0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_LABEL_0_SET_LABEL_IMAGE_0));
        aliasImageLabel0ImageLabel0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_LABEL_0_IMAGE_LABEL_0));
        aliasFont0FontStringWidth0=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_0_FONT_STRING_WIDTH_0));
        aliasFont1FontStringWidth0=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_1_FONT_STRING_WIDTH_0));
        aliasFont1FontStringWidth1=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_1_FONT_STRING_WIDTH_1));
        aliasFont0FontStringHeight0=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_0_FONT_STRING_HEIGHT_0));
        aliasFont0Font0=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_0_FONT_0));
        aliasFont1Font0=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_1_FONT_0));
        aliasFont1Font1=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_1_FONT_1));
        aliasFont1Font2=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_1_FONT_2));
        aliasFont1Font3=LgNamesContent.get(_util,_cust,_mapping.getVal(FONT_1_FONT_3));
        aliasColor0Color0=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_0_COLOR_0));
        aliasColor1Color0=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_1_COLOR_0));
        aliasColor1Color1=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_1_COLOR_1));
        aliasColor2Color0=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_2_COLOR_0));
        aliasColor2Color1=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_2_COLOR_1));
        aliasColor2Color2=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_2_COLOR_2));
        aliasColor3Color0=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_3_COLOR_0));
        aliasColor3Color1=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_3_COLOR_1));
        aliasColor3Color2=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_3_COLOR_2));
        aliasColor3Color3=LgNamesContent.get(_util,_cust,_mapping.getVal(COLOR_3_COLOR_3));
        aliasImage0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasImage0TreeNodeGetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_TREE_NODE_GET_USER_OBJECT_1));
        aliasImage0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasImage0TreeNodeSetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_TREE_NODE_SET_USER_OBJECT_1));
        aliasImage0TreeNodeSetUserObject2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_TREE_NODE_SET_USER_OBJECT_2));
        aliasImage0ImageSetColor0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_SET_COLOR_0));
        aliasImage0SetFont0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_SET_FONT_0));
        aliasImage0ImageDraw0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_0));
        aliasImage0ImageDraw1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_1));
        aliasImage0ImageDraw2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_2));
        aliasImage1ImageDraw0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_1_IMAGE_DRAW_0));
        aliasImage1ImageDraw1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_1_IMAGE_DRAW_1));
        aliasImage1ImageDraw2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_1_IMAGE_DRAW_2));
        aliasImage0ImageDrawLine0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_0));
        aliasImage0ImageDrawLine1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_1));
        aliasImage0ImageDrawLine2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_2));
        aliasImage0ImageDrawLine3=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_3));
        aliasImage0ImageDrawRect0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_0));
        aliasImage0ImageDrawRect1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_1));
        aliasImage0ImageDrawRect2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_2));
        aliasImage0ImageDrawRect3=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_3));
        aliasImage0ImageDrawOval0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_0));
        aliasImage0ImageDrawOval1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_1));
        aliasImage0ImageDrawOval2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_2));
        aliasImage0ImageDrawOval3=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_3));
        aliasImage0ImageDrawPolygon0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_POLYGON_0));
        aliasImage0ImageDrawPolygon1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_DRAW_POLYGON_1));
        aliasImage0ImageFillRect0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_0));
        aliasImage0ImageFillRect1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_1));
        aliasImage0ImageFillRect2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_2));
        aliasImage0ImageFillRect3=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_3));
        aliasImage0ImageFillOval0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_0));
        aliasImage0ImageFillOval1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_1));
        aliasImage0ImageFillOval2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_2));
        aliasImage0ImageFillOval3=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_3));
        aliasImage0ImageFillPolygon0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_POLYGON_0));
        aliasImage0ImageFillPolygon1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_FILL_POLYGON_1));
        aliasImage0ImageEq0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_EQ_0));
        aliasImage0ImageEq1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_EQ_1));
        aliasImage0Image0=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_0));
        aliasImage0Image1=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_1));
        aliasImage0Image2=LgNamesContent.get(_util,_cust,_mapping.getVal(IMAGE_0_IMAGE_2));
        aliasRender0RenderSetHeight0=LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_0_RENDER_SET_HEIGHT_0));
        aliasRender0RenderSetWidth0=LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_0_RENDER_SET_WIDTH_0));
        aliasRender0ComponentSetPaint0=LgNamesContent.get(_util,_cust,_mapping.getVal(RENDER_0_COMPONENT_SET_PAINT_0));
        aliasGrList0GrListSetRender0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_GR_LIST_SET_RENDER_0));
        aliasGrList0GrListAddSelection0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_GR_LIST_ADD_SELECTION_0));
        aliasGrList0GrListRemoveSelection0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_GR_LIST_REMOVE_SELECTION_0));
        aliasGrList0GrListSetVisibleRowCount0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_GR_LIST_SET_VISIBLE_ROW_COUNT_0));
        aliasGrList0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_TABBED_PANE_ADD_0));
        aliasGrList0TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_TABBED_PANE_ADD_1));
        aliasGrList1TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_1_TABBED_PANE_ADD_0));
        aliasGrList1TabbedPaneAdd1=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_1_TABBED_PANE_ADD_1));
        aliasGrList1TabbedPaneAdd2=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_1_TABBED_PANE_ADD_2));
        aliasGrList2TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_2_TABBED_PANE_ADD_0));
        aliasGrList0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasGrList0TreeNodeSetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_1));
        aliasGrList0TreeNodeSetUserObject2=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_2));
        aliasGrList1TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_0));
        aliasGrList1TreeNodeSetUserObject1=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_1));
        aliasGrList0GrListSetSelectedIndexes0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_GR_LIST_SET_SELECTED_INDEXES_0));
        aliasGrList0RemoveCompo0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_REMOVE_COMPO_0));
        aliasGrList0GrList0=LgNamesContent.get(_util,_cust,_mapping.getVal(GR_LIST_0_GR_LIST_0));
        aliasCombo0ComboAddListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_0_COMBO_ADD_LISTENER_0));
        aliasCombo0ComboRemoveListener0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_0_COMBO_REMOVE_LISTENER_0));
        aliasCombo0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_0_TABBED_PANE_ADD_0));
        aliasCombo0ComboSelectItem0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_0_COMBO_SELECT_ITEM_0));
        aliasCombo0ComboRemoveItem0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_0_COMBO_REMOVE_ITEM_0));
        aliasCombo0Combo0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_0_COMBO_0));
        aliasCombo1Combo0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_1_COMBO_0));
        aliasCombo1Combo1=LgNamesContent.get(_util,_cust,_mapping.getVal(COMBO_1_COMBO_1));
        aliasButtonGroup0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_GROUP_0_TABBED_PANE_ADD_0));
        aliasButtonGroup0TabbedPaneRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(BUTTON_GROUP_0_TABBED_PANE_REMOVE_0));
        aliasPopupMenu0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_TABBED_PANE_ADD_0));
        aliasPopupMenu0PopupMenuGetComp0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_GET_COMP_0));
        aliasPopupMenu0PopupMenuRemoveComp0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_REMOVE_COMP_0));
        aliasPopupMenu0PopupMenuAddMenu0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_ADD_MENU_0));
        aliasPopupMenu0PopupMenuGetMenu0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_GET_MENU_0));
        aliasPopupMenu0PopupMenuRemoveMenu0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_REMOVE_MENU_0));
        aliasPopupMenu0PopupMenuShow0=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_SHOW_0));
        aliasPopupMenu0PopupMenuShow1=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_SHOW_1));
        aliasPopupMenu0PopupMenuShow2=LgNamesContent.get(_util,_cust,_mapping.getVal(POPUP_MENU_0_POPUP_MENU_SHOW_2));
        aliasRadio0RadioSetSelected0=LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_0_RADIO_SET_SELECTED_0));
        aliasRadio0SetLabelText0=LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_0_SET_LABEL_TEXT_0));
        aliasRadio0Radio0=LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_0_RADIO_0));
        aliasRadio1Radio0=LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_1_RADIO_0));
        aliasRadio1Radio1=LgNamesContent.get(_util,_cust,_mapping.getVal(RADIO_1_RADIO_1));
        aliasCheckBox0RadioSetSelected0=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_0_RADIO_SET_SELECTED_0));
        aliasCheckBox0SetLabelText0=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_0_SET_LABEL_TEXT_0));
        aliasCheckBox0CheckBox0=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_0_CHECK_BOX_0));
        aliasCheckBox1CheckBox0=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_1_CHECK_BOX_0));
        aliasCheckBox1CheckBox1=LgNamesContent.get(_util,_cust,_mapping.getVal(CHECK_BOX_1_CHECK_BOX_1));
        aliasTextField0SetLabelText0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_0_SET_LABEL_TEXT_0));
        aliasTextField0TextFieldAddAction0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_0_TEXT_FIELD_ADD_ACTION_0));
        aliasTextField0TextFieldRemoveAction0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_0_TEXT_FIELD_REMOVE_ACTION_0));
        aliasTextField0TextField0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_0_TEXT_FIELD_0));
        aliasTextField1TextField0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_1_TEXT_FIELD_0));
        aliasTextField2TextField0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_2_TEXT_FIELD_0));
        aliasTextField2TextField1=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_FIELD_2_TEXT_FIELD_1));
        aliasTextArea0SetLabelText0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_SET_LABEL_TEXT_0));
        aliasTextArea0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TABBED_PANE_ADD_0));
        aliasTextArea0TreeNodeInsert0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TREE_NODE_INSERT_0));
        aliasTextArea0TreeNodeInsert1=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TREE_NODE_INSERT_1));
        aliasTextArea0TextAreaSetSelectionStart0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_START_0));
        aliasTextArea0TextAreaSetSelectionEnd0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_END_0));
        aliasTextArea0TextAreaSetTabSize0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_SET_TAB_SIZE_0));
        aliasTextArea0TextAreaReplaceRange0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_0));
        aliasTextArea0TextAreaReplaceRange1=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_1));
        aliasTextArea0TextAreaReplaceRange2=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_2));
        aliasTextArea0TextAreaReplaceSelection0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_SELECTION_0));
        aliasTextArea0TreeGetSelected0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TREE_GET_SELECTED_0));
        aliasTextArea0TreeGetSelected1=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TREE_GET_SELECTED_1));
        aliasTextArea0TextArea0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_0_TEXT_AREA_0));
        aliasTextArea1TextArea0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_1_TEXT_AREA_0));
        aliasTextArea1TextArea1=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_1_TEXT_AREA_1));
        aliasTextArea2TextArea0=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_2_TEXT_AREA_0));
        aliasTextArea2TextArea1=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_2_TEXT_AREA_1));
        aliasTextArea2TextArea2=LgNamesContent.get(_util,_cust,_mapping.getVal(TEXT_AREA_2_TEXT_AREA_2));
        aliasSpinner0SpinnerSetMax0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_MAX_0));
        aliasSpinner0SpinnerSetMin0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_MIN_0));
        aliasSpinner0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasSpinner0SpinnerSetStep0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_STEP_0));
        aliasSpinner0SpinnerSetRange0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_0));
        aliasSpinner0SpinnerSetRange1=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_1));
        aliasSpinner0SpinnerSetRangeValue0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_VALUE_0));
        aliasSpinner0SpinnerSetRangeValue1=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_VALUE_1));
        aliasSpinner0SpinnerSetRangeValue2=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_VALUE_2));
        aliasSpinner0AddChange0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_ADD_CHANGE_0));
        aliasSpinner0Spinner0=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_0));
        aliasSpinner0Spinner1=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_1));
        aliasSpinner0Spinner2=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_2));
        aliasSpinner0Spinner3=LgNamesContent.get(_util,_cust,_mapping.getVal(SPINNER_0_SPINNER_3));
        aliasSlider0SpinnerSetMax0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_0_SPINNER_SET_MAX_0));
        aliasSlider0SpinnerSetMin0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_0_SPINNER_SET_MIN_0));
        aliasSlider0TreeNodeSetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_0_TREE_NODE_SET_USER_OBJECT_0));
        aliasSlider0SliderSetOrientation0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_0_SLIDER_SET_ORIENTATION_0));
        aliasSlider0AddChange0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_0_ADD_CHANGE_0));
        aliasSlider0Slider0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_0_SLIDER_0));
        aliasSlider1Slider0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_1_SLIDER_0));
        aliasSlider1Slider1=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_1_SLIDER_1));
        aliasSlider2Slider0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_2_SLIDER_0));
        aliasSlider2Slider1=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_2_SLIDER_1));
        aliasSlider2Slider2=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_2_SLIDER_2));
        aliasSlider3Slider0=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_3_SLIDER_0));
        aliasSlider3Slider1=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_3_SLIDER_1));
        aliasSlider3Slider2=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_3_SLIDER_2));
        aliasSlider3Slider3=LgNamesContent.get(_util,_cust,_mapping.getVal(SLIDER_3_SLIDER_3));
        aliasMenuBar0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_0_TABBED_PANE_ADD_0));
        aliasMenuBar0TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_0_TREE_NODE_REMOVE_0));
        aliasMenuBar0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_BAR_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasAbsMenu0AbsMenuSetText0=LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_0_ABS_MENU_SET_TEXT_0));
        aliasAbsMenu0InputSetEnabled0=LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_0_INPUT_SET_ENABLED_0));
        aliasAbsMenu0AbsMenuSetDeepEnabled0=LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_0_ABS_MENU_SET_DEEP_ENABLED_0));
        aliasMenu0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_0_TABBED_PANE_ADD_0));
        aliasMenu0TreeNodeRemove0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_0_TREE_NODE_REMOVE_0));
        aliasMenu0TreeNodeGetUserObject0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_0_TREE_NODE_GET_USER_OBJECT_0));
        aliasMenu0Menu0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_0_MENU_0));
        aliasAbsMenuItem0TabbedPaneAdd0=LgNamesContent.get(_util,_cust,_mapping.getVal(ABS_MENU_ITEM_0_TABBED_PANE_ADD_0));
        aliasMenuItem0MenuItem0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM_0_MENU_ITEM_0));
        aliasMenuItemCheck0RadioSetSelected0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM_CHECK_0_RADIO_SET_SELECTED_0));
        aliasMenuItemCheck0MenuItemCheck0=LgNamesContent.get(_util,_cust,_mapping.getVal(MENU_ITEM_CHECK_0_MENU_ITEM_CHECK_0));
        aliasCommand0Binding0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMMAND_0_BINDING_0));
        aliasCommand0Action0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMMAND_0_ACTION_0));
        aliasComponent0Bind0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_BIND_0));
        aliasComponent0Bind1=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_BIND_1));
        aliasComponent0Bind2=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_BIND_2));
        aliasComponent0Unbind0=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_UNBIND_0));
        aliasComponent0Unbind1=LgNamesContent.get(_util,_cust,_mapping.getVal(COMPONENT_0_UNBIND_1));
        aliasActionListenerImplicit0Implicit0= LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_0));
        aliasActionListenerImplicit0Implicit1= LgNamesContent.get(_util,_cust,_mapping.getVal(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_1));
        aliasCellRender0Generate0 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_0));
        aliasCellRender0Generate1 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_1));
        aliasCellRender0Generate2 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_2));
        aliasCellRender0Generate3 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_3));
        aliasCellRender0Generate4 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_4));
        aliasCellRender0Generate5 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_5));
        aliasCellRender0Generate6 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_6));
        aliasCellRenderImplicit0Implicit0 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_IMPLICIT_0_IMPLICIT_0));
        aliasCellRenderImplicit0Implicit1 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_IMPLICIT_0_IMPLICIT_1));
        aliasCellRender0Generate00 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_0_0));
        aliasCellRender0Generate01 = LgNamesContent.get(_util,_cust,_mapping.getVal(CELL_RENDER_0_GENERATE_0_1));
    }
    public static void en(TranslationsFile _en){
        _en.add(ACTION_LISTENER_0_ACTION_PERFORMED_0,"ActionListener0ActionPerformed0=a");
        _en.add(ACTION_0_WRAP_0,"Action0Wrap0=a");
        _en.add(ACTION_0_ENABLED_0,"Action0Enabled0=a");
        _en.add(TREE_LISTENER_0_TREE_LISTENER_VALUE_CHANGED_0,"TreeListener0TreeListenerValueChanged0=a");
        _en.add(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_0,"TableListener0TableValueTableChanged0=a");
        _en.add(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_1,"TableListener0TableValueTableChanged1=b");
        _en.add(MOUSE_LISTENER_0_MOUSE_CLICKED_0,"MouseListener0MouseClicked0=a");
        _en.add(MOUSE_LISTENER_0_MOUSE_PRESSED_0,"MouseListener0MousePressed0=a");
        _en.add(MOUSE_LISTENER_0_MOUSE_RELEASED_0,"MouseListener0MouseReleased0=a");
        _en.add(MOUSE_LISTENER_0_MOUSE_ENTERED_0,"MouseListener0MouseEntered0=a");
        _en.add(MOUSE_LISTENER_0_MOUSE_EXITED_0,"MouseListener0MouseExited0=a");
        _en.add(MOUSE_LISTENER_0_MOUSE_DRAGGED_0,"MouseListener0MouseDragged0=a");
        _en.add(MOUSE_LISTENER_0_MOUSE_MOVED_0,"MouseListener0MouseMoved0=a");
        _en.add(WHEEL_LISTENER_0_WHEEL_MOVE_0,"WheelListener0WheelMove0=a");
        _en.add(KEY_LISTENER_0_KEY_PRESSED_0,"KeyListener0KeyPressed0=a");
        _en.add(KEY_LISTENER_0_KEY_TYPED_0,"KeyListener0KeyTyped0=a");
        _en.add(KEY_LISTENER_0_KEY_RELEASED_0,"KeyListener0KeyReleased0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_OPENED_0,"WindowListener0WindowOpened0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_CLOSING_0,"WindowListener0WindowClosing0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_CLOSED_0,"WindowListener0WindowClosed0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_ICONIFIED_0,"WindowListener0WindowIconified0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_DEICONIFIED_0,"WindowListener0WindowDeiconified0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_ACTIVATED_0,"WindowListener0WindowActivated0=a");
        _en.add(WINDOW_LISTENER_0_WINDOW_DEACTIVATED_0,"WindowListener0WindowDeactivated0=a");
        _en.add(LIST_SELECTION_0_VALUE_CHANGED_0,"ListSelection0ValueChanged0=a");
        _en.add(LIST_SELECTION_0_VALUE_CHANGED_1,"ListSelection0ValueChanged1=b");
        _en.add(LIST_SELECTION_0_VALUE_CHANGED_2,"ListSelection0ValueChanged2=c");
        _en.add(PAINT_0_PAINT_METHOD_0,"Paint0PaintMethod0=a");
        _en.add(PAINT_0_TABBED_PANE_SET_0,"Paint0TabbedPaneSet0=a");
        _en.add(PAINT_0_TABBED_PANE_SET_1,"Paint0TabbedPaneSet1=b");
        _en.add(PAINT_0_TABBED_PANE_SET_2,"Paint0TabbedPaneSet2=c");
        _en.add(PAINT_0_TABBED_PANE_ADD_0,"Paint0TabbedPaneAdd0=a");
        _en.add(PAINT_0_TABBED_PANE_ADD_1,"Paint0TabbedPaneAdd1=b");
        _en.add(PAINT_0_TABBED_PANE_ADD_2,"Paint0TabbedPaneAdd2=c");
        _en.add(PAINT_1_TABBED_PANE_ADD_0,"Paint1TabbedPaneAdd0=a");
        _en.add(PAINT_1_TABBED_PANE_ADD_1,"Paint1TabbedPaneAdd1=b");
        _en.add(PAINT_0_PAINT_REFRESH_0,"Paint0PaintRefresh0=a");
        _en.add(PAINT_0_PAINT_REFRESH_ONE_0,"Paint0PaintRefreshOne0=a");
        _en.add(PAINT_0_PAINT_REFRESH_ONE_1,"Paint0PaintRefreshOne1=b");
        _en.add(PAINT_0_PAINT_REFRESH_ONE_2,"Paint0PaintRefreshOne2=c");
        _en.add(PAINT_0_PAINT_REFRESH_ONE_3,"Paint0PaintRefreshOne3=d");
        _en.add(PAINT_0_PAINT_REFRESH_ONE_4,"Paint0PaintRefreshOne4=e");
        _en.add(PAINT_0_PAINT_REFRESH_ONE_5,"Paint0PaintRefreshOne5=f");
        _en.add(WINDOW_TYPE_0_SET_CONTENT_0,"WindowType0SetContent0=a");
        _en.add(WINDOW_TYPE_0_WINDOW_TYPE_RELATIVE_0,"WindowType0WindowTypeRelative0=a");
        _en.add(WINDOW_TYPE_0_COMPONENT_SET_VISIBLE_0,"WindowType0ComponentSetVisible0=a");
        _en.add(WINDOW_TYPE_0_SET_MENU_BAR_0,"WindowType0SetMenuBar0=a");
        _en.add(WINDOW_TYPE_0_ADD_WINDOW_LISTENER_0,"WindowType0AddWindowListener0=a");
        _en.add(WINDOW_TYPE_0_REMOVE_WINDOW_LISTENER_0,"WindowType0RemoveWindowListener0=a");
        _en.add(CONFIRM_0_CONFIRM_FIELD_0,"Confirm0ConfirmField0=a");
        _en.add(CONFIRM_0_CONFIRM_FIELD_1,"Confirm0ConfirmField1=b");
        _en.add(CONFIRM_0_CONFIRM_FIELD_2,"Confirm0ConfirmField2=c");
        _en.add(CONFIRM_0_CONFIRM_FIELD_3,"Confirm0ConfirmField3=d");
        _en.add(CONFIRM_0_CONFIRM_FIELD_4,"Confirm0ConfirmField4=e");
        _en.add(CONFIRM_0_CONFIRM_FIELD_5,"Confirm0ConfirmField5=f");
        _en.add(CONFIRM_0_CONFIRM_FIELD_6,"Confirm0ConfirmField6=g");
        _en.add(CONFIRM_1_CONFIRM_FIELD_0,"Confirm1ConfirmField0=a");
        _en.add(CONFIRM_1_CONFIRM_FIELD_1,"Confirm1ConfirmField1=b");
        _en.add(CONFIRM_1_CONFIRM_FIELD_2,"Confirm1ConfirmField2=c");
        _en.add(CONFIRM_1_CONFIRM_FIELD_3,"Confirm1ConfirmField3=d");
        _en.add(CONFIRM_1_CONFIRM_FIELD_4,"Confirm1ConfirmField4=e");
        _en.add(CONFIRM_1_CONFIRM_FIELD_5,"Confirm1ConfirmField5=f");
        _en.add(CONFIRM_0_CONFIRM_FULL_0,"Confirm0ConfirmFull0=a");
        _en.add(CONFIRM_0_CONFIRM_FULL_1,"Confirm0ConfirmFull1=b");
        _en.add(CONFIRM_0_CONFIRM_FULL_2,"Confirm0ConfirmFull2=c");
        _en.add(CONFIRM_0_CONFIRM_FULL_3,"Confirm0ConfirmFull3=d");
        _en.add(CONFIRM_0_CONFIRM_FULL_4,"Confirm0ConfirmFull4=e");
        _en.add(CONFIRM_0_CONFIRM_FULL_5,"Confirm0ConfirmFull5=f");
        _en.add(CONFIRM_0_CONFIRM_FULL_6,"Confirm0ConfirmFull6=g");
        _en.add(CONFIRM_1_CONFIRM_FULL_0,"Confirm1ConfirmFull0=a");
        _en.add(CONFIRM_1_CONFIRM_FULL_1,"Confirm1ConfirmFull1=b");
        _en.add(CONFIRM_1_CONFIRM_FULL_2,"Confirm1ConfirmFull2=c");
        _en.add(CONFIRM_1_CONFIRM_FULL_3,"Confirm1ConfirmFull3=d");
        _en.add(CONFIRM_1_CONFIRM_FULL_4,"Confirm1ConfirmFull4=e");
        _en.add(CONFIRM_1_CONFIRM_FULL_5,"Confirm1ConfirmFull5=f");
        _en.add(CONFIRM_0_CONFIRM_YES_NO_0,"Confirm0ConfirmYesNo0=a");
        _en.add(CONFIRM_0_CONFIRM_YES_NO_1,"Confirm0ConfirmYesNo1=b");
        _en.add(CONFIRM_0_CONFIRM_YES_NO_2,"Confirm0ConfirmYesNo2=c");
        _en.add(CONFIRM_0_CONFIRM_YES_NO_3,"Confirm0ConfirmYesNo3=d");
        _en.add(CONFIRM_0_CONFIRM_YES_NO_4,"Confirm0ConfirmYesNo4=e");
        _en.add(CONFIRM_0_CONFIRM_YES_NO_5,"Confirm0ConfirmYesNo5=f");
        _en.add(CONFIRM_1_CONFIRM_YES_NO_0,"Confirm1ConfirmYesNo0=a");
        _en.add(CONFIRM_1_CONFIRM_YES_NO_1,"Confirm1ConfirmYesNo1=b");
        _en.add(CONFIRM_1_CONFIRM_YES_NO_2,"Confirm1ConfirmYesNo2=c");
        _en.add(CONFIRM_1_CONFIRM_YES_NO_3,"Confirm1ConfirmYesNo3=d");
        _en.add(CONFIRM_1_CONFIRM_YES_NO_4,"Confirm1ConfirmYesNo4=e");
        _en.add(CONFIRM_0_CONFIRM_OK_0,"Confirm0ConfirmOk0=a");
        _en.add(CONFIRM_0_CONFIRM_OK_1,"Confirm0ConfirmOk1=b");
        _en.add(CONFIRM_0_CONFIRM_OK_2,"Confirm0ConfirmOk2=c");
        _en.add(CONFIRM_0_CONFIRM_OK_3,"Confirm0ConfirmOk3=d");
        _en.add(CONFIRM_0_CONFIRM_OK_4,"Confirm0ConfirmOk4=e");
        _en.add(CONFIRM_1_CONFIRM_OK_0,"Confirm1ConfirmOk0=a");
        _en.add(CONFIRM_1_CONFIRM_OK_1,"Confirm1ConfirmOk1=b");
        _en.add(CONFIRM_1_CONFIRM_OK_2,"Confirm1ConfirmOk2=c");
        _en.add(CONFIRM_1_CONFIRM_OK_3,"Confirm1ConfirmOk3=d");
        _en.add(CONFIRM_0_CONFIRM_MESSAGE_0,"Confirm0ConfirmMessage0=a");
        _en.add(CONFIRM_0_CONFIRM_MESSAGE_1,"Confirm0ConfirmMessage1=b");
        _en.add(CONFIRM_0_CONFIRM_MESSAGE_2,"Confirm0ConfirmMessage2=c");
        _en.add(CONFIRM_0_CONFIRM_MESSAGE_3,"Confirm0ConfirmMessage3=d");
        _en.add(CONFIRM_0_CONFIRM_MESSAGE_4,"Confirm0ConfirmMessage4=e");
        _en.add(CONFIRM_1_CONFIRM_MESSAGE_0,"Confirm1ConfirmMessage0=a");
        _en.add(CONFIRM_1_CONFIRM_MESSAGE_1,"Confirm1ConfirmMessage1=b");
        _en.add(CONFIRM_1_CONFIRM_MESSAGE_2,"Confirm1ConfirmMessage2=c");
        _en.add(CONFIRM_1_CONFIRM_MESSAGE_3,"Confirm1ConfirmMessage3=d");
        _en.add(WINDOW_SET_0_TABBED_PANE_ADD_0,"WindowSet0TabbedPaneAdd0=a");
        _en.add(WINDOW_SET_0_WINDOW_SET_CONTAINS_0,"WindowSet0WindowSetContains0=a");
        _en.add(WINDOW_SET_0_TREE_NODE_REMOVE_0,"WindowSet0TreeNodeRemove0=a");
        _en.add(DIALOG_0_DIALOG_SET_MODAL_0,"Dialog0DialogSetModal0=a");
        _en.add(COMPONENT_0_SET_FONT_0,"Component0SetFont0=a");
        _en.add(COMPONENT_0_COMP_BACK_0,"Component0CompBack0=a");
        _en.add(COMPONENT_0_COMP_FORE_0,"Component0CompFore0=a");
        _en.add(COMPONENT_0_COMP_FOCUSABLE_0,"Component0CompFocusable0=a");
        _en.add(COMPONENT_0_COMP_OPAQUE_0,"Component0CompOpaque0=a");
        _en.add(COMPONENT_0_COMP_TOOL_TIP_0,"Component0CompToolTip0=a");
        _en.add(COMPONENT_0_COMP_LOC_0,"Component0CompLoc0=a");
        _en.add(COMPONENT_0_COMP_LOC_1,"Component0CompLoc1=b");
        _en.add(COMPONENT_0_COMP_BOR_LINE_0,"Component0CompBorLine0=a");
        _en.add(COMPONENT_1_COMP_BOR_LINE_0,"Component1CompBorLine0=a");
        _en.add(COMPONENT_1_COMP_BOR_LINE_1,"Component1CompBorLine1=b");
        _en.add(COMPONENT_0_COMP_BOR_TITLE_0,"Component0CompBorTitle0=a");
        _en.add(COMPONENT_0_COMPONENT_SET_PAINT_0,"Component0ComponentSetPaint0=a");
        _en.add(COMPONENT_0_COMPONENT_SET_AUTOSCROLLS_0,"Component0ComponentSetAutoscrolls0=a");
        _en.add(COMPONENT_0_COMPONENT_SET_PREFERRED_SIZE_0,"Component0ComponentSetPreferredSize0=a");
        _en.add(COMPONENT_0_COMPONENT_SET_VISIBLE_0,"Component0ComponentSetVisible0=a");
        _en.add(COMPONENT_0_COMPONENT_INVOKE_LATER_0,"Component0ComponentInvokeLater0=a");
        _en.add(COMPONENT_0_ADD_KEY_LISTENER_0,"Component0AddKeyListener0=a");
        _en.add(COMPONENT_0_ADD_FOCUS_LISTENER_0,"Component0AddFocusListener0=a");
        _en.add(COMPONENT_0_ADD_WHEEL_LISTENER_0,"Component0AddWheelListener0=a");
        _en.add(COMPONENT_0_ADD_LISTENER_0,"Component0AddListener0=a");
        _en.add(COMPONENT_0_REMOVE_KEY_LISTENER_0,"Component0RemoveKeyListener0=a");
        _en.add(COMPONENT_0_REMOVE_FOCUS_LISTENER_0,"Component0RemoveFocusListener0=a");
        _en.add(COMPONENT_0_REMOVE_WHEEL_LISTENER_0,"Component0RemoveWheelListener0=a");
        _en.add(COMPONENT_0_REMOVE_LISTENER_0,"Component0RemoveListener0=a");
        _en.add(DIMENSION_0_DIMENSION_0,"Dimension0Dimension0=a");
        _en.add(DIMENSION_0_DIMENSION_1,"Dimension0Dimension1=b");
        _en.add(DIMENSION_1_DIMENSION_0,"Dimension1Dimension0=a");
        _en.add(TREE_NODE_0_TREE_NODE_EQ_0,"TreeNode0TreeNodeEq0=a");
        _en.add(TREE_NODE_0_TREE_NODE_EQ_1,"TreeNode0TreeNodeEq1=b");
        _en.add(TREE_NODE_0_TREE_NODE_ADD_0,"TreeNode0TreeNodeAdd0=a");
        _en.add(TREE_NODE_0_TREE_NODE_INSERT_0,"TreeNode0TreeNodeInsert0=a");
        _en.add(TREE_NODE_0_TREE_NODE_INSERT_1,"TreeNode0TreeNodeInsert1=b");
        _en.add(TREE_NODE_0_TREE_NODE_REMOVE_0,"TreeNode0TreeNodeRemove0=a");
        _en.add(TREE_NODE_1_TREE_NODE_REMOVE_0,"TreeNode1TreeNodeRemove0=a");
        _en.add(TREE_NODE_0_TREE_NODE_SET_USER_OBJECT_0,"TreeNode0TreeNodeSetUserObject0=a");
        _en.add(TREE_NODE_0_TREE_NODE_IS_ANCESTOR_0,"TreeNode0TreeNodeIsAncestor0=a");
        _en.add(TREE_NODE_0_TREE_NODE_IS_DESCENDANT_0,"TreeNode0TreeNodeIsDescendant0=a");
        _en.add(TREE_NODE_0_TREE_NODE_0,"TreeNode0TreeNode0=a");
        _en.add(TREE_0_TREE_ADD_TREE_LISTENER_0,"Tree0TreeAddTreeListener0=a");
        _en.add(TREE_0_TREE_REMOVE_TREE_LISTENER_0,"Tree0TreeRemoveTreeListener0=a");
        _en.add(TREE_0_TREE_SET_ROOT_VISIBLE_0,"Tree0TreeSetRootVisible0=a");
        _en.add(TREE_0_TREE_GET_SELECTED_0,"Tree0TreeGetSelected0=a");
        _en.add(TREE_0_TREE_0,"Tree0Tree0=a");
        _en.add(TABLE_GUI_0_TABLE_ADD_HEADER_0,"TableGui0TableAddHeader0=a");
        _en.add(TABLE_GUI_0_TABLE_ADD_SELECT_0,"TableGui0TableAddSelect0=a");
        _en.add(TABLE_GUI_0_TABLE_SET_MULTIPLE_0,"TableGui0TableSetMultiple0=a");
        _en.add(TABLE_GUI_0_TABLE_SET_REORDER_0,"TableGui0TableSetReorder0=a");
        _en.add(TABLE_GUI_0_TABLE_GET_COLUMN_NAME_0,"TableGui0TableGetColumnName0=a");
        _en.add(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_0,"TableGui0TableGetColumnAtPoint0=a");
        _en.add(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_1,"TableGui0TableGetColumnAtPoint1=b");
        _en.add(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_0,"TableGui0TableGetRowAtPoint0=a");
        _en.add(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_1,"TableGui0TableGetRowAtPoint1=b");
        _en.add(TABLE_GUI_0_TABLE_SET_ROW_COUNT_0,"TableGui0TableSetRowCount0=a");
        _en.add(TABLE_GUI_0_TABLE_SET_COLUMNS_0,"TableGui0TableSetColumns0=a");
        _en.add(TABLE_GUI_0_TABLE_MOVE_COLUMN_0,"TableGui0TableMoveColumn0=a");
        _en.add(TABLE_GUI_0_TABLE_MOVE_COLUMN_1,"TableGui0TableMoveColumn1=b");
        _en.add(TABLE_GUI_0_TABLE_ADD_INTERVAL_0,"TableGui0TableAddInterval0=a");
        _en.add(TABLE_GUI_0_TABLE_ADD_INTERVAL_1,"TableGui0TableAddInterval1=b");
        _en.add(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_0,"TableGui0TableRemoveInterval0=a");
        _en.add(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_1,"TableGui0TableRemoveInterval1=b");
        _en.add(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_0,"TableGui0TreeNodeSetUserObject0=a");
        _en.add(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_1,"TableGui0TreeNodeSetUserObject1=b");
        _en.add(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_2,"TableGui0TreeNodeSetUserObject2=c");
        _en.add(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_0,"TableGui0TreeNodeGetUserObject0=a");
        _en.add(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_1,"TableGui0TreeNodeGetUserObject1=b");
        _en.add(TABLE_GUI_0_TABLE_GUI_0,"TableGui0TableGui0=a");
        _en.add(ACTION_EVENT_0_ACTION_EVENT_0,"ActionEvent0ActionEvent0=a");
        _en.add(ACTION_EVENT_0_ACTION_EVENT_1,"ActionEvent0ActionEvent1=b");
        _en.add(ACTION_EVENT_0_ACTION_EVENT_2,"ActionEvent0ActionEvent2=c");
        _en.add(ACTION_EVENT_0_ACTION_EVENT_3,"ActionEvent0ActionEvent3=d");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_0,"MouseEvent0MouseEvent0=a");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_1,"MouseEvent0MouseEvent1=b");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_2,"MouseEvent0MouseEvent2=c");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_3,"MouseEvent0MouseEvent3=d");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_4,"MouseEvent0MouseEvent4=e");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_5,"MouseEvent0MouseEvent5=f");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_6,"MouseEvent0MouseEvent6=g");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_7,"MouseEvent0MouseEvent7=h");
        _en.add(MOUSE_EVENT_0_MOUSE_EVENT_8,"MouseEvent0MouseEvent8=i");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_0,"WheelEvent0WheelEvent0=a");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_1,"WheelEvent0WheelEvent1=b");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_2,"WheelEvent0WheelEvent2=c");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_3,"WheelEvent0WheelEvent3=d");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_4,"WheelEvent0WheelEvent4=e");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_5,"WheelEvent0WheelEvent5=f");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_6,"WheelEvent0WheelEvent6=g");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_7,"WheelEvent0WheelEvent7=h");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_8,"WheelEvent0WheelEvent8=i");
        _en.add(WHEEL_EVENT_0_WHEEL_EVENT_9,"WheelEvent0WheelEvent9=j");
        _en.add(KEY_EVENT_0_KEY_EVENT_0,"KeyEvent0KeyEvent0=a");
        _en.add(KEY_EVENT_0_KEY_EVENT_1,"KeyEvent0KeyEvent1=b");
        _en.add(KEY_EVENT_0_KEY_EVENT_2,"KeyEvent0KeyEvent2=c");
        _en.add(KEY_EVENT_0_KEY_EVENT_3,"KeyEvent0KeyEvent3=d");
        _en.add(KEY_EVENT_0_KEY_EVENT_4,"KeyEvent0KeyEvent4=e");
        _en.add(PANEL_0_TABBED_PANE_ADD_0,"Panel0TabbedPaneAdd0=a");
        _en.add(PANEL_1_TABBED_PANE_ADD_0,"Panel1TabbedPaneAdd0=a");
        _en.add(PANEL_1_TABBED_PANE_ADD_1,"Panel1TabbedPaneAdd1=b");
        _en.add(PANEL_0_REMOVE_COMPO_0,"Panel0RemoveCompo0=a");
        _en.add(PANEL_1_REMOVE_COMPO_0,"Panel1RemoveCompo0=a");
        _en.add(PANEL_0_TREE_NODE_GET_USER_OBJECT_0,"Panel0TreeNodeGetUserObject0=a");
        _en.add(PANEL_0_PANEL_GRID_0,"Panel0PanelGrid0=a");
        _en.add(PANEL_0_PANEL_GRID_1,"Panel0PanelGrid1=b");
        _en.add(PANEL_BORDER_0_TABBED_PANE_ADD_0,"PanelBorder0TabbedPaneAdd0=a");
        _en.add(PANEL_BORDER_0_TABBED_PANE_ADD_1,"PanelBorder0TabbedPaneAdd1=b");
        _en.add(TABBED_PANE_0_TABBED_PANE_ADD_0,"TabbedPane0TabbedPaneAdd0=a");
        _en.add(TABBED_PANE_0_TABBED_PANE_ADD_1,"TabbedPane0TabbedPaneAdd1=b");
        _en.add(TABBED_PANE_0_TABBED_PANE_SEL_INDEX_0,"TabbedPane0TabbedPaneSelIndex0=a");
        _en.add(TABBED_PANE_0_TABBED_PANE_INDEX_0,"TabbedPane0TabbedPaneIndex0=a");
        _en.add(TABBED_PANE_0_TREE_NODE_REMOVE_0,"TabbedPane0TreeNodeRemove0=a");
        _en.add(TABBED_PANE_1_TREE_NODE_REMOVE_0,"TabbedPane1TreeNodeRemove0=a");
        _en.add(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_0,"TabbedPane0TreeNodeSetUserObject0=a");
        _en.add(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_1,"TabbedPane0TreeNodeSetUserObject1=b");
        _en.add(TABBED_PANE_0_TABBED_PANE_SET_TITLE_0,"TabbedPane0TabbedPaneSetTitle0=a");
        _en.add(TABBED_PANE_0_TABBED_PANE_SET_TITLE_1,"TabbedPane0TabbedPaneSetTitle1=b");
        _en.add(TABBED_PANE_0_TREE_NODE_GET_USER_OBJECT_0,"TabbedPane0TreeNodeGetUserObject0=a");
        _en.add(TABBED_PANE_0_TABBED_PANE_GET_TITLE_0,"TabbedPane0TabbedPaneGetTitle0=a");
        _en.add(SCROLL_PANE_0_SCROLL_PANE_HORIZONTAL_VALUE_0,"ScrollPane0ScrollPaneHorizontalValue0=a");
        _en.add(SCROLL_PANE_0_SCROLL_PANE_VERTICAL_VALUE_0,"ScrollPane0ScrollPaneVerticalValue0=a");
        _en.add(SCROLL_PANE_0_SCROLL_PANE_SET_VIEW_0,"ScrollPane0ScrollPaneSetView0=a");
        _en.add(SCROLL_PANE_0_SCROLL_PANE_0,"ScrollPane0ScrollPane0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_SET_LEFT_0,"SplitPane0SplitPaneSetLeft0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_SET_RIGHT_0,"SplitPane0SplitPaneSetRight0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_LOCATION_0,"SplitPane0SplitPaneSetDividerLocation0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_SIZE_0,"SplitPane0SplitPaneSetDividerSize0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE_0,"SplitPane0SplitPaneSetOneTouchExpandable0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_SET_CONTINUOUS_LAYOUT_0,"SplitPane0SplitPaneSetContinuousLayout0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_0,"SplitPane0SplitPane0=a");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_1,"SplitPane0SplitPane1=b");
        _en.add(SPLIT_PANE_0_SPLIT_PANE_2,"SplitPane0SplitPane2=c");
        _en.add(INPUT_0_INPUT_SET_ENABLED_0,"Input0InputSetEnabled0=a");
        _en.add(SEPARATOR_0_SEPARATOR_SET_ORIENT_0,"Separator0SeparatorSetOrient0=a");
        _en.add(BUTTON_0_ADD_LISTENER_0,"Button0AddListener0=a");
        _en.add(BUTTON_0_REMOVE_LISTENER_0,"Button0RemoveListener0=a");
        _en.add(CHECK_BOX_0_ADD_LISTENER_0,"CheckBox0AddListener0=a");
        _en.add(CHECK_BOX_0_REMOVE_LISTENER_0,"CheckBox0RemoveListener0=a");
        _en.add(BUTTON_0_BUTTON_0,"Button0Button0=a");
        _en.add(PROG_BAR_0_PROG_BAR_MIN_0,"ProgBar0ProgBarMin0=a");
        _en.add(PROG_BAR_0_TREE_NODE_GET_USER_OBJECT_0,"ProgBar0TreeNodeGetUserObject0=a");
        _en.add(PROG_BAR_0_PROG_BAR_MAX_0,"ProgBar0ProgBarMax0=a");
        _en.add(PROG_BAR_0_PROG_BAR_OR_0,"ProgBar0ProgBarOr0=a");
        _en.add(TEXT_LABEL_0_SET_LABEL_TEXT_0,"TextLabel0SetLabelText0=a");
        _en.add(TEXT_LABEL_0_TEXT_LABEL_0,"TextLabel0TextLabel0=a");
        _en.add(IMAGE_LABEL_0_SET_LABEL_IMAGE_0,"ImageLabel0SetLabelImage0=a");
        _en.add(IMAGE_LABEL_0_IMAGE_LABEL_0,"ImageLabel0ImageLabel0=a");
        _en.add(FONT_0_FONT_STRING_WIDTH_0,"Font0FontStringWidth0=a");
        _en.add(FONT_1_FONT_STRING_WIDTH_0,"Font1FontStringWidth0=a");
        _en.add(FONT_1_FONT_STRING_WIDTH_1,"Font1FontStringWidth1=b");
        _en.add(FONT_0_FONT_STRING_HEIGHT_0,"Font0FontStringHeight0=a");
        _en.add(FONT_0_FONT_0,"Font0Font0=a");
        _en.add(FONT_1_FONT_0,"Font1Font0=a");
        _en.add(FONT_1_FONT_1,"Font1Font1=b");
        _en.add(FONT_1_FONT_2,"Font1Font2=c");
        _en.add(FONT_1_FONT_3,"Font1Font3=d");
        _en.add(COLOR_0_COLOR_0,"Color0Color0=a");
        _en.add(COLOR_1_COLOR_0,"Color1Color0=a");
        _en.add(COLOR_1_COLOR_1,"Color1Color1=b");
        _en.add(COLOR_2_COLOR_0,"Color2Color0=a");
        _en.add(COLOR_2_COLOR_1,"Color2Color1=b");
        _en.add(COLOR_2_COLOR_2,"Color2Color2=c");
        _en.add(COLOR_3_COLOR_0,"Color3Color0=a");
        _en.add(COLOR_3_COLOR_1,"Color3Color1=b");
        _en.add(COLOR_3_COLOR_2,"Color3Color2=c");
        _en.add(COLOR_3_COLOR_3,"Color3Color3=d");
        _en.add(IMAGE_0_TREE_NODE_GET_USER_OBJECT_0,"Image0TreeNodeGetUserObject0=a");
        _en.add(IMAGE_0_TREE_NODE_GET_USER_OBJECT_1,"Image0TreeNodeGetUserObject1=b");
        _en.add(IMAGE_0_TREE_NODE_SET_USER_OBJECT_0,"Image0TreeNodeSetUserObject0=a");
        _en.add(IMAGE_0_TREE_NODE_SET_USER_OBJECT_1,"Image0TreeNodeSetUserObject1=b");
        _en.add(IMAGE_0_TREE_NODE_SET_USER_OBJECT_2,"Image0TreeNodeSetUserObject2=c");
        _en.add(IMAGE_0_IMAGE_SET_COLOR_0,"Image0ImageSetColor0=a");
        _en.add(IMAGE_0_SET_FONT_0,"Image0SetFont0=a");
        _en.add(IMAGE_0_IMAGE_DRAW_0,"Image0ImageDraw0=a");
        _en.add(IMAGE_0_IMAGE_DRAW_1,"Image0ImageDraw1=b");
        _en.add(IMAGE_0_IMAGE_DRAW_2,"Image0ImageDraw2=c");
        _en.add(IMAGE_1_IMAGE_DRAW_0,"Image1ImageDraw0=a");
        _en.add(IMAGE_1_IMAGE_DRAW_1,"Image1ImageDraw1=b");
        _en.add(IMAGE_1_IMAGE_DRAW_2,"Image1ImageDraw2=c");
        _en.add(IMAGE_0_IMAGE_DRAW_LINE_0,"Image0ImageDrawLine0=a");
        _en.add(IMAGE_0_IMAGE_DRAW_LINE_1,"Image0ImageDrawLine1=b");
        _en.add(IMAGE_0_IMAGE_DRAW_LINE_2,"Image0ImageDrawLine2=c");
        _en.add(IMAGE_0_IMAGE_DRAW_LINE_3,"Image0ImageDrawLine3=d");
        _en.add(IMAGE_0_IMAGE_DRAW_RECT_0,"Image0ImageDrawRect0=a");
        _en.add(IMAGE_0_IMAGE_DRAW_RECT_1,"Image0ImageDrawRect1=b");
        _en.add(IMAGE_0_IMAGE_DRAW_RECT_2,"Image0ImageDrawRect2=c");
        _en.add(IMAGE_0_IMAGE_DRAW_RECT_3,"Image0ImageDrawRect3=d");
        _en.add(IMAGE_0_IMAGE_DRAW_OVAL_0,"Image0ImageDrawOval0=a");
        _en.add(IMAGE_0_IMAGE_DRAW_OVAL_1,"Image0ImageDrawOval1=b");
        _en.add(IMAGE_0_IMAGE_DRAW_OVAL_2,"Image0ImageDrawOval2=c");
        _en.add(IMAGE_0_IMAGE_DRAW_OVAL_3,"Image0ImageDrawOval3=d");
        _en.add(IMAGE_0_IMAGE_DRAW_POLYGON_0,"Image0ImageDrawPolygon0=a");
        _en.add(IMAGE_0_IMAGE_DRAW_POLYGON_1,"Image0ImageDrawPolygon1=b");
        _en.add(IMAGE_0_IMAGE_FILL_RECT_0,"Image0ImageFillRect0=a");
        _en.add(IMAGE_0_IMAGE_FILL_RECT_1,"Image0ImageFillRect1=b");
        _en.add(IMAGE_0_IMAGE_FILL_RECT_2,"Image0ImageFillRect2=c");
        _en.add(IMAGE_0_IMAGE_FILL_RECT_3,"Image0ImageFillRect3=d");
        _en.add(IMAGE_0_IMAGE_FILL_OVAL_0,"Image0ImageFillOval0=a");
        _en.add(IMAGE_0_IMAGE_FILL_OVAL_1,"Image0ImageFillOval1=b");
        _en.add(IMAGE_0_IMAGE_FILL_OVAL_2,"Image0ImageFillOval2=c");
        _en.add(IMAGE_0_IMAGE_FILL_OVAL_3,"Image0ImageFillOval3=d");
        _en.add(IMAGE_0_IMAGE_FILL_POLYGON_0,"Image0ImageFillPolygon0=a");
        _en.add(IMAGE_0_IMAGE_FILL_POLYGON_1,"Image0ImageFillPolygon1=b");
        _en.add(IMAGE_0_IMAGE_EQ_0,"Image0ImageEq0=a");
        _en.add(IMAGE_0_IMAGE_EQ_1,"Image0ImageEq1=b");
        _en.add(IMAGE_0_IMAGE_0,"Image0Image0=a");
        _en.add(IMAGE_0_IMAGE_1,"Image0Image1=b");
        _en.add(IMAGE_0_IMAGE_2,"Image0Image2=c");
        _en.add(RENDER_0_RENDER_SET_HEIGHT_0,"Render0RenderSetHeight0=a");
        _en.add(RENDER_0_RENDER_SET_WIDTH_0,"Render0RenderSetWidth0=a");
        _en.add(RENDER_0_COMPONENT_SET_PAINT_0,"Render0ComponentSetPaint0=a");
        _en.add(GR_LIST_0_GR_LIST_SET_RENDER_0,"GrList0GrListSetRender0=a");
        _en.add(GR_LIST_0_GR_LIST_ADD_SELECTION_0,"GrList0GrListAddSelection0=a");
        _en.add(GR_LIST_0_GR_LIST_REMOVE_SELECTION_0,"GrList0GrListRemoveSelection0=a");
        _en.add(GR_LIST_0_GR_LIST_SET_VISIBLE_ROW_COUNT_0,"GrList0GrListSetVisibleRowCount0=a");
        _en.add(GR_LIST_0_TABBED_PANE_ADD_0,"GrList0TabbedPaneAdd0=a");
        _en.add(GR_LIST_0_TABBED_PANE_ADD_1,"GrList0TabbedPaneAdd1=b");
        _en.add(GR_LIST_1_TABBED_PANE_ADD_0,"GrList1TabbedPaneAdd0=a");
        _en.add(GR_LIST_1_TABBED_PANE_ADD_1,"GrList1TabbedPaneAdd1=b");
        _en.add(GR_LIST_1_TABBED_PANE_ADD_2,"GrList1TabbedPaneAdd2=c");
        _en.add(GR_LIST_2_TABBED_PANE_ADD_0,"GrList2TabbedPaneAdd0=a");
        _en.add(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_0,"GrList0TreeNodeSetUserObject0=a");
        _en.add(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_1,"GrList0TreeNodeSetUserObject1=b");
        _en.add(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_2,"GrList0TreeNodeSetUserObject2=c");
        _en.add(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_0,"GrList1TreeNodeSetUserObject0=a");
        _en.add(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_1,"GrList1TreeNodeSetUserObject1=b");
        _en.add(GR_LIST_0_GR_LIST_SET_SELECTED_INDEXES_0,"GrList0GrListSetSelectedIndexes0=a");
        _en.add(GR_LIST_0_REMOVE_COMPO_0,"GrList0RemoveCompo0=a");
        _en.add(GR_LIST_0_GR_LIST_0,"GrList0GrList0=a");
        _en.add(COMBO_0_COMBO_ADD_LISTENER_0,"Combo0ComboAddListener0=a");
        _en.add(COMBO_0_COMBO_REMOVE_LISTENER_0,"Combo0ComboRemoveListener0=a");
        _en.add(COMBO_0_TABBED_PANE_ADD_0,"Combo0TabbedPaneAdd0=a");
        _en.add(COMBO_0_COMBO_SELECT_ITEM_0,"Combo0ComboSelectItem0=a");
        _en.add(COMBO_0_COMBO_REMOVE_ITEM_0,"Combo0ComboRemoveItem0=a");
        _en.add(COMBO_0_COMBO_0,"Combo0Combo0=a");
        _en.add(COMBO_1_COMBO_0,"Combo1Combo0=a");
        _en.add(COMBO_1_COMBO_1,"Combo1Combo1=b");
        _en.add(BUTTON_GROUP_0_TABBED_PANE_ADD_0,"ButtonGroup0TabbedPaneAdd0=a");
        _en.add(BUTTON_GROUP_0_TABBED_PANE_REMOVE_0,"ButtonGroup0TabbedPaneRemove0=a");
        _en.add(POPUP_MENU_0_TABBED_PANE_ADD_0,"PopupMenu0TabbedPaneAdd0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_GET_COMP_0,"PopupMenu0PopupMenuGetComp0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_REMOVE_COMP_0,"PopupMenu0PopupMenuRemoveComp0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_ADD_MENU_0,"PopupMenu0PopupMenuAddMenu0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_GET_MENU_0,"PopupMenu0PopupMenuGetMenu0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_REMOVE_MENU_0,"PopupMenu0PopupMenuRemoveMenu0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_SHOW_0,"PopupMenu0PopupMenuShow0=a");
        _en.add(POPUP_MENU_0_POPUP_MENU_SHOW_1,"PopupMenu0PopupMenuShow1=b");
        _en.add(POPUP_MENU_0_POPUP_MENU_SHOW_2,"PopupMenu0PopupMenuShow2=c");
        _en.add(RADIO_0_RADIO_SET_SELECTED_0,"Radio0RadioSetSelected0=a");
        _en.add(RADIO_0_SET_LABEL_TEXT_0,"Radio0SetLabelText0=a");
        _en.add(RADIO_0_ADD_CHANGE_0,"Radio0AddChange0=a");
        _en.add(RADIO_0_RADIO_0,"Radio0Radio0=a");
        _en.add(RADIO_1_RADIO_0,"Radio1Radio0=a");
        _en.add(RADIO_1_RADIO_1,"Radio1Radio1=b");
        _en.add(CHECK_BOX_0_RADIO_SET_SELECTED_0,"CheckBox0RadioSetSelected0=a");
        _en.add(CHECK_BOX_0_SET_LABEL_TEXT_0,"CheckBox0SetLabelText0=a");
        _en.add(CHECK_BOX_0_CHECK_BOX_0,"CheckBox0CheckBox0=a");
        _en.add(CHECK_BOX_1_CHECK_BOX_0,"CheckBox1CheckBox0=a");
        _en.add(CHECK_BOX_1_CHECK_BOX_1,"CheckBox1CheckBox1=b");
        _en.add(TEXT_FIELD_0_SET_LABEL_TEXT_0,"TextField0SetLabelText0=a");
        _en.add(TEXT_FIELD_0_TEXT_FIELD_ADD_ACTION_0,"TextField0TextFieldAddAction0=a");
        _en.add(TEXT_FIELD_0_TEXT_FIELD_REMOVE_ACTION_0,"TextField0TextFieldRemoveAction0=a");
        _en.add(TEXT_FIELD_0_TEXT_FIELD_0,"TextField0TextField0=a");
        _en.add(TEXT_FIELD_1_TEXT_FIELD_0,"TextField1TextField0=a");
        _en.add(TEXT_FIELD_2_TEXT_FIELD_0,"TextField2TextField0=a");
        _en.add(TEXT_FIELD_2_TEXT_FIELD_1,"TextField2TextField1=b");
        _en.add(TEXT_AREA_0_SET_LABEL_TEXT_0,"TextArea0SetLabelText0=a");
        _en.add(TEXT_AREA_0_TABBED_PANE_ADD_0,"TextArea0TabbedPaneAdd0=a");
        _en.add(TEXT_AREA_0_TREE_NODE_INSERT_0,"TextArea0TreeNodeInsert0=a");
        _en.add(TEXT_AREA_0_TREE_NODE_INSERT_1,"TextArea0TreeNodeInsert1=b");
        _en.add(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_START_0,"TextArea0TextAreaSetSelectionStart0=a");
        _en.add(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_END_0,"TextArea0TextAreaSetSelectionEnd0=a");
        _en.add(TEXT_AREA_0_TEXT_AREA_SET_TAB_SIZE_0,"TextArea0TextAreaSetTabSize0=a");
        _en.add(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_0,"TextArea0TextAreaReplaceRange0=a");
        _en.add(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_1,"TextArea0TextAreaReplaceRange1=b");
        _en.add(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_2,"TextArea0TextAreaReplaceRange2=c");
        _en.add(TEXT_AREA_0_TEXT_AREA_REPLACE_SELECTION_0,"TextArea0TextAreaReplaceSelection0=a");
        _en.add(TEXT_AREA_0_TREE_GET_SELECTED_0,"TextArea0TreeGetSelected0=a");
        _en.add(TEXT_AREA_0_TREE_GET_SELECTED_1,"TextArea0TreeGetSelected1=b");
        _en.add(TEXT_AREA_0_TEXT_AREA_0,"TextArea0TextArea0=a");
        _en.add(TEXT_AREA_1_TEXT_AREA_0,"TextArea1TextArea0=a");
        _en.add(TEXT_AREA_1_TEXT_AREA_1,"TextArea1TextArea1=b");
        _en.add(TEXT_AREA_2_TEXT_AREA_0,"TextArea2TextArea0=a");
        _en.add(TEXT_AREA_2_TEXT_AREA_1,"TextArea2TextArea1=b");
        _en.add(TEXT_AREA_2_TEXT_AREA_2,"TextArea2TextArea2=c");
        _en.add(SPINNER_0_SPINNER_SET_MAX_0,"Spinner0SpinnerSetMax0=a");
        _en.add(SPINNER_0_SPINNER_SET_MIN_0,"Spinner0SpinnerSetMin0=a");
        _en.add(SPINNER_0_TREE_NODE_SET_USER_OBJECT_0,"Spinner0TreeNodeSetUserObject0=a");
        _en.add(SPINNER_0_SPINNER_SET_STEP_0,"Spinner0SpinnerSetStep0=a");
        _en.add(SPINNER_0_SPINNER_SET_RANGE_0,"Spinner0SpinnerSetRange0=a");
        _en.add(SPINNER_0_SPINNER_SET_RANGE_1,"Spinner0SpinnerSetRange1=b");
        _en.add(SPINNER_0_SPINNER_SET_RANGE_VALUE_0,"Spinner0SpinnerSetRangeValue0=a");
        _en.add(SPINNER_0_SPINNER_SET_RANGE_VALUE_1,"Spinner0SpinnerSetRangeValue1=b");
        _en.add(SPINNER_0_SPINNER_SET_RANGE_VALUE_2,"Spinner0SpinnerSetRangeValue2=c");
        _en.add(SPINNER_0_ADD_CHANGE_0,"Spinner0AddChange0=a");
        _en.add(SPINNER_0_SPINNER_0,"Spinner0Spinner0=a");
        _en.add(SPINNER_0_SPINNER_1,"Spinner0Spinner1=b");
        _en.add(SPINNER_0_SPINNER_2,"Spinner0Spinner2=c");
        _en.add(SPINNER_0_SPINNER_3,"Spinner0Spinner3=d");
        _en.add(SLIDER_0_SPINNER_SET_MAX_0,"Slider0SpinnerSetMax0=a");
        _en.add(SLIDER_0_SPINNER_SET_MIN_0,"Slider0SpinnerSetMin0=a");
        _en.add(SLIDER_0_TREE_NODE_SET_USER_OBJECT_0,"Slider0TreeNodeSetUserObject0=a");
        _en.add(SLIDER_0_SLIDER_SET_ORIENTATION_0,"Slider0SliderSetOrientation0=a");
        _en.add(SLIDER_0_ADD_CHANGE_0,"Slider0AddChange0=a");
        _en.add(SLIDER_0_SLIDER_0,"Slider0Slider0=a");
        _en.add(SLIDER_1_SLIDER_0,"Slider1Slider0=a");
        _en.add(SLIDER_1_SLIDER_1,"Slider1Slider1=b");
        _en.add(SLIDER_2_SLIDER_0,"Slider2Slider0=a");
        _en.add(SLIDER_2_SLIDER_1,"Slider2Slider1=b");
        _en.add(SLIDER_2_SLIDER_2,"Slider2Slider2=c");
        _en.add(SLIDER_3_SLIDER_0,"Slider3Slider0=a");
        _en.add(SLIDER_3_SLIDER_1,"Slider3Slider1=b");
        _en.add(SLIDER_3_SLIDER_2,"Slider3Slider2=c");
        _en.add(SLIDER_3_SLIDER_3,"Slider3Slider3=d");
        _en.add(MENU_BAR_0_TABBED_PANE_ADD_0,"MenuBar0TabbedPaneAdd0=a");
        _en.add(MENU_BAR_0_TREE_NODE_REMOVE_0,"MenuBar0TreeNodeRemove0=a");
        _en.add(MENU_BAR_0_TREE_NODE_GET_USER_OBJECT_0,"MenuBar0TreeNodeGetUserObject0=a");
        _en.add(ABS_MENU_0_ABS_MENU_SET_TEXT_0,"AbsMenu0AbsMenuSetText0=a");
        _en.add(ABS_MENU_0_INPUT_SET_ENABLED_0,"AbsMenu0InputSetEnabled0=a");
        _en.add(ABS_MENU_0_ABS_MENU_SET_DEEP_ENABLED_0,"AbsMenu0AbsMenuSetDeepEnabled0=a");
        _en.add(MENU_0_TABBED_PANE_ADD_0,"Menu0TabbedPaneAdd0=a");
        _en.add(MENU_0_TREE_NODE_REMOVE_0,"Menu0TreeNodeRemove0=a");
        _en.add(MENU_0_TREE_NODE_GET_USER_OBJECT_0,"Menu0TreeNodeGetUserObject0=a");
        _en.add(MENU_0_MENU_0,"Menu0Menu0=a");
        _en.add(ABS_MENU_ITEM_0_TABBED_PANE_ADD_0,"AbsMenuItem0TabbedPaneAdd0=a");
        _en.add(MENU_ITEM_0_MENU_ITEM_0,"MenuItem0MenuItem0=a");
        _en.add(MENU_ITEM_CHECK_0_RADIO_SET_SELECTED_0,"MenuItemCheck0RadioSetSelected0=a");
        _en.add(MENU_ITEM_CHECK_0_MENU_ITEM_CHECK_0,"MenuItemCheck0MenuItemCheck0=a");
        _en.add(COMMAND_0_BINDING_0,"Command0Binding0=a");
        _en.add(COMMAND_0_ACTION_0,"Command0Action0=a");
        _en.add(COMPONENT_0_BIND_0,"Component0Bind0=a");
        _en.add(COMPONENT_0_BIND_1,"Component0Bind1=b");
        _en.add(COMPONENT_0_BIND_2,"Component0Bind2=c");
        _en.add(COMPONENT_0_UNBIND_0,"Component0Unbind0=a");
        _en.add(COMPONENT_0_UNBIND_1,"Component0Unbind1=b");
        _en.add(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_0,"ActionListenerImplicit0Implicit0=a");
        _en.add(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_1,"ActionListenerImplicit0Implicit1=a");
        _en.add(CELL_RENDER_IMPLICIT_0_IMPLICIT_0,"CellRenderImplicit0Implicit0=a");
        _en.add(CELL_RENDER_IMPLICIT_0_IMPLICIT_1,"CellRenderImplicit0Implicit1=a");
        _en.add(CELL_RENDER_0_GENERATE_0,"CellRender0Generate0=a");
        _en.add(CELL_RENDER_0_GENERATE_1,"CellRender0Generate1=b");
        _en.add(CELL_RENDER_0_GENERATE_2,"CellRender0Generate2=c");
        _en.add(CELL_RENDER_0_GENERATE_3,"CellRender0Generate3=d");
        _en.add(CELL_RENDER_0_GENERATE_4,"CellRender0Generate4=e");
        _en.add(CELL_RENDER_0_GENERATE_5,"CellRender0Generate5=f");
        _en.add(CELL_RENDER_0_GENERATE_6,"CellRender0Generate6=g");
        _en.add(CELL_RENDER_0_GENERATE_0_0,"CellRender0Generate00=a");
        _en.add(CELL_RENDER_0_GENERATE_0_1,"CellRender0Generate01=b");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(ACTION_LISTENER_0_ACTION_PERFORMED_0,"ActionListener0ActionPerformed0=a");
        _fr.add(ACTION_0_WRAP_0,"Action0Wrap0=a");
        _fr.add(ACTION_0_ENABLED_0,"Action0Enabled0=a");
        _fr.add(TREE_LISTENER_0_TREE_LISTENER_VALUE_CHANGED_0,"TreeListener0TreeListenerValueChanged0=a");
        _fr.add(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_0,"TableListener0TableValueTableChanged0=a");
        _fr.add(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_1,"TableListener0TableValueTableChanged1=b");
        _fr.add(MOUSE_LISTENER_0_MOUSE_CLICKED_0,"MouseListener0MouseClicked0=a");
        _fr.add(MOUSE_LISTENER_0_MOUSE_PRESSED_0,"MouseListener0MousePressed0=a");
        _fr.add(MOUSE_LISTENER_0_MOUSE_RELEASED_0,"MouseListener0MouseReleased0=a");
        _fr.add(MOUSE_LISTENER_0_MOUSE_ENTERED_0,"MouseListener0MouseEntered0=a");
        _fr.add(MOUSE_LISTENER_0_MOUSE_EXITED_0,"MouseListener0MouseExited0=a");
        _fr.add(MOUSE_LISTENER_0_MOUSE_DRAGGED_0,"MouseListener0MouseDragged0=a");
        _fr.add(MOUSE_LISTENER_0_MOUSE_MOVED_0,"MouseListener0MouseMoved0=a");
        _fr.add(WHEEL_LISTENER_0_WHEEL_MOVE_0,"WheelListener0WheelMove0=a");
        _fr.add(KEY_LISTENER_0_KEY_PRESSED_0,"KeyListener0KeyPressed0=a");
        _fr.add(KEY_LISTENER_0_KEY_TYPED_0,"KeyListener0KeyTyped0=a");
        _fr.add(KEY_LISTENER_0_KEY_RELEASED_0,"KeyListener0KeyReleased0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_OPENED_0,"WindowListener0WindowOpened0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_CLOSING_0,"WindowListener0WindowClosing0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_CLOSED_0,"WindowListener0WindowClosed0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_ICONIFIED_0,"WindowListener0WindowIconified0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_DEICONIFIED_0,"WindowListener0WindowDeiconified0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_ACTIVATED_0,"WindowListener0WindowActivated0=a");
        _fr.add(WINDOW_LISTENER_0_WINDOW_DEACTIVATED_0,"WindowListener0WindowDeactivated0=a");
        _fr.add(LIST_SELECTION_0_VALUE_CHANGED_0,"ListSelection0ValueChanged0=a");
        _fr.add(LIST_SELECTION_0_VALUE_CHANGED_1,"ListSelection0ValueChanged1=b");
        _fr.add(LIST_SELECTION_0_VALUE_CHANGED_2,"ListSelection0ValueChanged2=c");
        _fr.add(PAINT_0_PAINT_METHOD_0,"Paint0PaintMethod0=a");
        _fr.add(PAINT_0_TABBED_PANE_SET_0,"Paint0TabbedPaneSet0=a");
        _fr.add(PAINT_0_TABBED_PANE_SET_1,"Paint0TabbedPaneSet1=b");
        _fr.add(PAINT_0_TABBED_PANE_SET_2,"Paint0TabbedPaneSet2=c");
        _fr.add(PAINT_0_TABBED_PANE_ADD_0,"Paint0TabbedPaneAdd0=a");
        _fr.add(PAINT_0_TABBED_PANE_ADD_1,"Paint0TabbedPaneAdd1=b");
        _fr.add(PAINT_0_TABBED_PANE_ADD_2,"Paint0TabbedPaneAdd2=c");
        _fr.add(PAINT_1_TABBED_PANE_ADD_0,"Paint1TabbedPaneAdd0=a");
        _fr.add(PAINT_1_TABBED_PANE_ADD_1,"Paint1TabbedPaneAdd1=b");
        _fr.add(PAINT_0_PAINT_REFRESH_0,"Paint0PaintRefresh0=a");
        _fr.add(PAINT_0_PAINT_REFRESH_ONE_0,"Paint0PaintRefreshOne0=a");
        _fr.add(PAINT_0_PAINT_REFRESH_ONE_1,"Paint0PaintRefreshOne1=b");
        _fr.add(PAINT_0_PAINT_REFRESH_ONE_2,"Paint0PaintRefreshOne2=c");
        _fr.add(PAINT_0_PAINT_REFRESH_ONE_3,"Paint0PaintRefreshOne3=d");
        _fr.add(PAINT_0_PAINT_REFRESH_ONE_4,"Paint0PaintRefreshOne4=e");
        _fr.add(PAINT_0_PAINT_REFRESH_ONE_5,"Paint0PaintRefreshOne5=f");
        _fr.add(WINDOW_TYPE_0_SET_CONTENT_0,"WindowType0SetContent0=a");
        _fr.add(WINDOW_TYPE_0_WINDOW_TYPE_RELATIVE_0,"WindowType0WindowTypeRelative0=a");
        _fr.add(WINDOW_TYPE_0_COMPONENT_SET_VISIBLE_0,"WindowType0ComponentSetVisible0=a");
        _fr.add(WINDOW_TYPE_0_SET_MENU_BAR_0,"WindowType0SetMenuBar0=a");
        _fr.add(WINDOW_TYPE_0_ADD_WINDOW_LISTENER_0,"WindowType0AddWindowListener0=a");
        _fr.add(WINDOW_TYPE_0_REMOVE_WINDOW_LISTENER_0,"WindowType0RemoveWindowListener0=a");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_0,"Confirm0ConfirmField0=a");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_1,"Confirm0ConfirmField1=b");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_2,"Confirm0ConfirmField2=c");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_3,"Confirm0ConfirmField3=d");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_4,"Confirm0ConfirmField4=e");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_5,"Confirm0ConfirmField5=f");
        _fr.add(CONFIRM_0_CONFIRM_FIELD_6,"Confirm0ConfirmField6=g");
        _fr.add(CONFIRM_1_CONFIRM_FIELD_0,"Confirm1ConfirmField0=a");
        _fr.add(CONFIRM_1_CONFIRM_FIELD_1,"Confirm1ConfirmField1=b");
        _fr.add(CONFIRM_1_CONFIRM_FIELD_2,"Confirm1ConfirmField2=c");
        _fr.add(CONFIRM_1_CONFIRM_FIELD_3,"Confirm1ConfirmField3=d");
        _fr.add(CONFIRM_1_CONFIRM_FIELD_4,"Confirm1ConfirmField4=e");
        _fr.add(CONFIRM_1_CONFIRM_FIELD_5,"Confirm1ConfirmField5=f");
        _fr.add(CONFIRM_0_CONFIRM_FULL_0,"Confirm0ConfirmFull0=a");
        _fr.add(CONFIRM_0_CONFIRM_FULL_1,"Confirm0ConfirmFull1=b");
        _fr.add(CONFIRM_0_CONFIRM_FULL_2,"Confirm0ConfirmFull2=c");
        _fr.add(CONFIRM_0_CONFIRM_FULL_3,"Confirm0ConfirmFull3=d");
        _fr.add(CONFIRM_0_CONFIRM_FULL_4,"Confirm0ConfirmFull4=e");
        _fr.add(CONFIRM_0_CONFIRM_FULL_5,"Confirm0ConfirmFull5=f");
        _fr.add(CONFIRM_0_CONFIRM_FULL_6,"Confirm0ConfirmFull6=g");
        _fr.add(CONFIRM_1_CONFIRM_FULL_0,"Confirm1ConfirmFull0=a");
        _fr.add(CONFIRM_1_CONFIRM_FULL_1,"Confirm1ConfirmFull1=b");
        _fr.add(CONFIRM_1_CONFIRM_FULL_2,"Confirm1ConfirmFull2=c");
        _fr.add(CONFIRM_1_CONFIRM_FULL_3,"Confirm1ConfirmFull3=d");
        _fr.add(CONFIRM_1_CONFIRM_FULL_4,"Confirm1ConfirmFull4=e");
        _fr.add(CONFIRM_1_CONFIRM_FULL_5,"Confirm1ConfirmFull5=f");
        _fr.add(CONFIRM_0_CONFIRM_YES_NO_0,"Confirm0ConfirmYesNo0=a");
        _fr.add(CONFIRM_0_CONFIRM_YES_NO_1,"Confirm0ConfirmYesNo1=b");
        _fr.add(CONFIRM_0_CONFIRM_YES_NO_2,"Confirm0ConfirmYesNo2=c");
        _fr.add(CONFIRM_0_CONFIRM_YES_NO_3,"Confirm0ConfirmYesNo3=d");
        _fr.add(CONFIRM_0_CONFIRM_YES_NO_4,"Confirm0ConfirmYesNo4=e");
        _fr.add(CONFIRM_0_CONFIRM_YES_NO_5,"Confirm0ConfirmYesNo5=f");
        _fr.add(CONFIRM_1_CONFIRM_YES_NO_0,"Confirm1ConfirmYesNo0=a");
        _fr.add(CONFIRM_1_CONFIRM_YES_NO_1,"Confirm1ConfirmYesNo1=b");
        _fr.add(CONFIRM_1_CONFIRM_YES_NO_2,"Confirm1ConfirmYesNo2=c");
        _fr.add(CONFIRM_1_CONFIRM_YES_NO_3,"Confirm1ConfirmYesNo3=d");
        _fr.add(CONFIRM_1_CONFIRM_YES_NO_4,"Confirm1ConfirmYesNo4=e");
        _fr.add(CONFIRM_0_CONFIRM_OK_0,"Confirm0ConfirmOk0=a");
        _fr.add(CONFIRM_0_CONFIRM_OK_1,"Confirm0ConfirmOk1=b");
        _fr.add(CONFIRM_0_CONFIRM_OK_2,"Confirm0ConfirmOk2=c");
        _fr.add(CONFIRM_0_CONFIRM_OK_3,"Confirm0ConfirmOk3=d");
        _fr.add(CONFIRM_0_CONFIRM_OK_4,"Confirm0ConfirmOk4=e");
        _fr.add(CONFIRM_1_CONFIRM_OK_0,"Confirm1ConfirmOk0=a");
        _fr.add(CONFIRM_1_CONFIRM_OK_1,"Confirm1ConfirmOk1=b");
        _fr.add(CONFIRM_1_CONFIRM_OK_2,"Confirm1ConfirmOk2=c");
        _fr.add(CONFIRM_1_CONFIRM_OK_3,"Confirm1ConfirmOk3=d");
        _fr.add(CONFIRM_0_CONFIRM_MESSAGE_0,"Confirm0ConfirmMessage0=a");
        _fr.add(CONFIRM_0_CONFIRM_MESSAGE_1,"Confirm0ConfirmMessage1=b");
        _fr.add(CONFIRM_0_CONFIRM_MESSAGE_2,"Confirm0ConfirmMessage2=c");
        _fr.add(CONFIRM_0_CONFIRM_MESSAGE_3,"Confirm0ConfirmMessage3=d");
        _fr.add(CONFIRM_0_CONFIRM_MESSAGE_4,"Confirm0ConfirmMessage4=e");
        _fr.add(CONFIRM_1_CONFIRM_MESSAGE_0,"Confirm1ConfirmMessage0=a");
        _fr.add(CONFIRM_1_CONFIRM_MESSAGE_1,"Confirm1ConfirmMessage1=b");
        _fr.add(CONFIRM_1_CONFIRM_MESSAGE_2,"Confirm1ConfirmMessage2=c");
        _fr.add(CONFIRM_1_CONFIRM_MESSAGE_3,"Confirm1ConfirmMessage3=d");
        _fr.add(WINDOW_SET_0_TABBED_PANE_ADD_0,"WindowSet0TabbedPaneAdd0=a");
        _fr.add(WINDOW_SET_0_WINDOW_SET_CONTAINS_0,"WindowSet0WindowSetContains0=a");
        _fr.add(WINDOW_SET_0_TREE_NODE_REMOVE_0,"WindowSet0TreeNodeRemove0=a");
        _fr.add(DIALOG_0_DIALOG_SET_MODAL_0,"Dialog0DialogSetModal0=a");
        _fr.add(COMPONENT_0_SET_FONT_0,"Component0SetFont0=a");
        _fr.add(COMPONENT_0_COMP_BACK_0,"Component0CompBack0=a");
        _fr.add(COMPONENT_0_COMP_FORE_0,"Component0CompFore0=a");
        _fr.add(COMPONENT_0_COMP_FOCUSABLE_0,"Component0CompFocusable0=a");
        _fr.add(COMPONENT_0_COMP_OPAQUE_0,"Component0CompOpaque0=a");
        _fr.add(COMPONENT_0_COMP_TOOL_TIP_0,"Component0CompToolTip0=a");
        _fr.add(COMPONENT_0_COMP_LOC_0,"Component0CompLoc0=a");
        _fr.add(COMPONENT_0_COMP_LOC_1,"Component0CompLoc1=b");
        _fr.add(COMPONENT_0_COMP_BOR_LINE_0,"Component0CompBorLine0=a");
        _fr.add(COMPONENT_1_COMP_BOR_LINE_0,"Component1CompBorLine0=a");
        _fr.add(COMPONENT_1_COMP_BOR_LINE_1,"Component1CompBorLine1=b");
        _fr.add(COMPONENT_0_COMP_BOR_TITLE_0,"Component0CompBorTitle0=a");
        _fr.add(COMPONENT_0_COMPONENT_SET_PAINT_0,"Component0ComponentSetPaint0=a");
        _fr.add(COMPONENT_0_COMPONENT_SET_AUTOSCROLLS_0,"Component0ComponentSetAutoscrolls0=a");
        _fr.add(COMPONENT_0_COMPONENT_SET_PREFERRED_SIZE_0,"Component0ComponentSetPreferredSize0=a");
        _fr.add(COMPONENT_0_COMPONENT_SET_VISIBLE_0,"Component0ComponentSetVisible0=a");
        _fr.add(COMPONENT_0_COMPONENT_INVOKE_LATER_0,"Component0ComponentInvokeLater0=a");
        _fr.add(COMPONENT_0_ADD_KEY_LISTENER_0,"Component0AddKeyListener0=a");
        _fr.add(COMPONENT_0_ADD_FOCUS_LISTENER_0,"Component0AddFocusListener0=a");
        _fr.add(COMPONENT_0_ADD_WHEEL_LISTENER_0,"Component0AddWheelListener0=a");
        _fr.add(COMPONENT_0_ADD_LISTENER_0,"Component0AddListener0=a");
        _fr.add(COMPONENT_0_REMOVE_KEY_LISTENER_0,"Component0RemoveKeyListener0=a");
        _fr.add(COMPONENT_0_REMOVE_FOCUS_LISTENER_0,"Component0RemoveFocusListener0=a");
        _fr.add(COMPONENT_0_REMOVE_WHEEL_LISTENER_0,"Component0RemoveWheelListener0=a");
        _fr.add(COMPONENT_0_REMOVE_LISTENER_0,"Component0RemoveListener0=a");
        _fr.add(DIMENSION_0_DIMENSION_0,"Dimension0Dimension0=a");
        _fr.add(DIMENSION_0_DIMENSION_1,"Dimension0Dimension1=b");
        _fr.add(DIMENSION_1_DIMENSION_0,"Dimension1Dimension0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_EQ_0,"TreeNode0TreeNodeEq0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_EQ_1,"TreeNode0TreeNodeEq1=b");
        _fr.add(TREE_NODE_0_TREE_NODE_ADD_0,"TreeNode0TreeNodeAdd0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_INSERT_0,"TreeNode0TreeNodeInsert0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_INSERT_1,"TreeNode0TreeNodeInsert1=b");
        _fr.add(TREE_NODE_0_TREE_NODE_REMOVE_0,"TreeNode0TreeNodeRemove0=a");
        _fr.add(TREE_NODE_1_TREE_NODE_REMOVE_0,"TreeNode1TreeNodeRemove0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_SET_USER_OBJECT_0,"TreeNode0TreeNodeSetUserObject0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_IS_ANCESTOR_0,"TreeNode0TreeNodeIsAncestor0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_IS_DESCENDANT_0,"TreeNode0TreeNodeIsDescendant0=a");
        _fr.add(TREE_NODE_0_TREE_NODE_0,"TreeNode0TreeNode0=a");
        _fr.add(TREE_0_TREE_ADD_TREE_LISTENER_0,"Tree0TreeAddTreeListener0=a");
        _fr.add(TREE_0_TREE_REMOVE_TREE_LISTENER_0,"Tree0TreeRemoveTreeListener0=a");
        _fr.add(TREE_0_TREE_SET_ROOT_VISIBLE_0,"Tree0TreeSetRootVisible0=a");
        _fr.add(TREE_0_TREE_GET_SELECTED_0,"Tree0TreeGetSelected0=a");
        _fr.add(TREE_0_TREE_0,"Tree0Tree0=a");
        _fr.add(TABLE_GUI_0_TABLE_ADD_HEADER_0,"TableGui0TableAddHeader0=a");
        _fr.add(TABLE_GUI_0_TABLE_ADD_SELECT_0,"TableGui0TableAddSelect0=a");
        _fr.add(TABLE_GUI_0_TABLE_SET_MULTIPLE_0,"TableGui0TableSetMultiple0=a");
        _fr.add(TABLE_GUI_0_TABLE_SET_REORDER_0,"TableGui0TableSetReorder0=a");
        _fr.add(TABLE_GUI_0_TABLE_GET_COLUMN_NAME_0,"TableGui0TableGetColumnName0=a");
        _fr.add(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_0,"TableGui0TableGetColumnAtPoint0=a");
        _fr.add(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_1,"TableGui0TableGetColumnAtPoint1=b");
        _fr.add(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_0,"TableGui0TableGetRowAtPoint0=a");
        _fr.add(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_1,"TableGui0TableGetRowAtPoint1=b");
        _fr.add(TABLE_GUI_0_TABLE_SET_ROW_COUNT_0,"TableGui0TableSetRowCount0=a");
        _fr.add(TABLE_GUI_0_TABLE_SET_COLUMNS_0,"TableGui0TableSetColumns0=a");
        _fr.add(TABLE_GUI_0_TABLE_MOVE_COLUMN_0,"TableGui0TableMoveColumn0=a");
        _fr.add(TABLE_GUI_0_TABLE_MOVE_COLUMN_1,"TableGui0TableMoveColumn1=b");
        _fr.add(TABLE_GUI_0_TABLE_ADD_INTERVAL_0,"TableGui0TableAddInterval0=a");
        _fr.add(TABLE_GUI_0_TABLE_ADD_INTERVAL_1,"TableGui0TableAddInterval1=b");
        _fr.add(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_0,"TableGui0TableRemoveInterval0=a");
        _fr.add(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_1,"TableGui0TableRemoveInterval1=b");
        _fr.add(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_0,"TableGui0TreeNodeSetUserObject0=a");
        _fr.add(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_1,"TableGui0TreeNodeSetUserObject1=b");
        _fr.add(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_2,"TableGui0TreeNodeSetUserObject2=c");
        _fr.add(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_0,"TableGui0TreeNodeGetUserObject0=a");
        _fr.add(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_1,"TableGui0TreeNodeGetUserObject1=b");
        _fr.add(TABLE_GUI_0_TABLE_GUI_0,"TableGui0TableGui0=a");
        _fr.add(ACTION_EVENT_0_ACTION_EVENT_0,"ActionEvent0ActionEvent0=a");
        _fr.add(ACTION_EVENT_0_ACTION_EVENT_1,"ActionEvent0ActionEvent1=b");
        _fr.add(ACTION_EVENT_0_ACTION_EVENT_2,"ActionEvent0ActionEvent2=c");
        _fr.add(ACTION_EVENT_0_ACTION_EVENT_3,"ActionEvent0ActionEvent3=d");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_0,"MouseEvent0MouseEvent0=a");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_1,"MouseEvent0MouseEvent1=b");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_2,"MouseEvent0MouseEvent2=c");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_3,"MouseEvent0MouseEvent3=d");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_4,"MouseEvent0MouseEvent4=e");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_5,"MouseEvent0MouseEvent5=f");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_6,"MouseEvent0MouseEvent6=g");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_7,"MouseEvent0MouseEvent7=h");
        _fr.add(MOUSE_EVENT_0_MOUSE_EVENT_8,"MouseEvent0MouseEvent8=i");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_0,"WheelEvent0WheelEvent0=a");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_1,"WheelEvent0WheelEvent1=b");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_2,"WheelEvent0WheelEvent2=c");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_3,"WheelEvent0WheelEvent3=d");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_4,"WheelEvent0WheelEvent4=e");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_5,"WheelEvent0WheelEvent5=f");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_6,"WheelEvent0WheelEvent6=g");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_7,"WheelEvent0WheelEvent7=h");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_8,"WheelEvent0WheelEvent8=i");
        _fr.add(WHEEL_EVENT_0_WHEEL_EVENT_9,"WheelEvent0WheelEvent9=j");
        _fr.add(KEY_EVENT_0_KEY_EVENT_0,"KeyEvent0KeyEvent0=a");
        _fr.add(KEY_EVENT_0_KEY_EVENT_1,"KeyEvent0KeyEvent1=b");
        _fr.add(KEY_EVENT_0_KEY_EVENT_2,"KeyEvent0KeyEvent2=c");
        _fr.add(KEY_EVENT_0_KEY_EVENT_3,"KeyEvent0KeyEvent3=d");
        _fr.add(KEY_EVENT_0_KEY_EVENT_4,"KeyEvent0KeyEvent4=e");
        _fr.add(PANEL_0_TABBED_PANE_ADD_0,"Panel0TabbedPaneAdd0=a");
        _fr.add(PANEL_1_TABBED_PANE_ADD_0,"Panel1TabbedPaneAdd0=a");
        _fr.add(PANEL_1_TABBED_PANE_ADD_1,"Panel1TabbedPaneAdd1=b");
        _fr.add(PANEL_0_REMOVE_COMPO_0,"Panel0RemoveCompo0=a");
        _fr.add(PANEL_1_REMOVE_COMPO_0,"Panel1RemoveCompo0=a");
        _fr.add(PANEL_0_TREE_NODE_GET_USER_OBJECT_0,"Panel0TreeNodeGetUserObject0=a");
        _fr.add(PANEL_0_PANEL_GRID_0,"Panel0PanelGrid0=a");
        _fr.add(PANEL_0_PANEL_GRID_1,"Panel0PanelGrid1=b");
        _fr.add(PANEL_BORDER_0_TABBED_PANE_ADD_0,"PanelBorder0TabbedPaneAdd0=a");
        _fr.add(PANEL_BORDER_0_TABBED_PANE_ADD_1,"PanelBorder0TabbedPaneAdd1=b");
        _fr.add(TABBED_PANE_0_TABBED_PANE_ADD_0,"TabbedPane0TabbedPaneAdd0=a");
        _fr.add(TABBED_PANE_0_TABBED_PANE_ADD_1,"TabbedPane0TabbedPaneAdd1=b");
        _fr.add(TABBED_PANE_0_TABBED_PANE_SEL_INDEX_0,"TabbedPane0TabbedPaneSelIndex0=a");
        _fr.add(TABBED_PANE_0_TABBED_PANE_INDEX_0,"TabbedPane0TabbedPaneIndex0=a");
        _fr.add(TABBED_PANE_0_TREE_NODE_REMOVE_0,"TabbedPane0TreeNodeRemove0=a");
        _fr.add(TABBED_PANE_1_TREE_NODE_REMOVE_0,"TabbedPane1TreeNodeRemove0=a");
        _fr.add(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_0,"TabbedPane0TreeNodeSetUserObject0=a");
        _fr.add(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_1,"TabbedPane0TreeNodeSetUserObject1=b");
        _fr.add(TABBED_PANE_0_TABBED_PANE_SET_TITLE_0,"TabbedPane0TabbedPaneSetTitle0=a");
        _fr.add(TABBED_PANE_0_TABBED_PANE_SET_TITLE_1,"TabbedPane0TabbedPaneSetTitle1=b");
        _fr.add(TABBED_PANE_0_TREE_NODE_GET_USER_OBJECT_0,"TabbedPane0TreeNodeGetUserObject0=a");
        _fr.add(TABBED_PANE_0_TABBED_PANE_GET_TITLE_0,"TabbedPane0TabbedPaneGetTitle0=a");
        _fr.add(SCROLL_PANE_0_SCROLL_PANE_HORIZONTAL_VALUE_0,"ScrollPane0ScrollPaneHorizontalValue0=a");
        _fr.add(SCROLL_PANE_0_SCROLL_PANE_VERTICAL_VALUE_0,"ScrollPane0ScrollPaneVerticalValue0=a");
        _fr.add(SCROLL_PANE_0_SCROLL_PANE_SET_VIEW_0,"ScrollPane0ScrollPaneSetView0=a");
        _fr.add(SCROLL_PANE_0_SCROLL_PANE_0,"ScrollPane0ScrollPane0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_SET_LEFT_0,"SplitPane0SplitPaneSetLeft0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_SET_RIGHT_0,"SplitPane0SplitPaneSetRight0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_LOCATION_0,"SplitPane0SplitPaneSetDividerLocation0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_SIZE_0,"SplitPane0SplitPaneSetDividerSize0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE_0,"SplitPane0SplitPaneSetOneTouchExpandable0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_SET_CONTINUOUS_LAYOUT_0,"SplitPane0SplitPaneSetContinuousLayout0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_0,"SplitPane0SplitPane0=a");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_1,"SplitPane0SplitPane1=b");
        _fr.add(SPLIT_PANE_0_SPLIT_PANE_2,"SplitPane0SplitPane2=c");
        _fr.add(INPUT_0_INPUT_SET_ENABLED_0,"Input0InputSetEnabled0=a");
        _fr.add(SEPARATOR_0_SEPARATOR_SET_ORIENT_0,"Separator0SeparatorSetOrient0=a");
        _fr.add(BUTTON_0_ADD_LISTENER_0,"Button0AddListener0=a");
        _fr.add(BUTTON_0_REMOVE_LISTENER_0,"Button0RemoveListener0=a");
        _fr.add(CHECK_BOX_0_ADD_LISTENER_0,"CheckBox0AddListener0=a");
        _fr.add(CHECK_BOX_0_REMOVE_LISTENER_0,"CheckBox0RemoveListener0=a");
        _fr.add(BUTTON_0_BUTTON_0,"Button0Button0=a");
        _fr.add(PROG_BAR_0_PROG_BAR_MIN_0,"ProgBar0ProgBarMin0=a");
        _fr.add(PROG_BAR_0_TREE_NODE_GET_USER_OBJECT_0,"ProgBar0TreeNodeGetUserObject0=a");
        _fr.add(PROG_BAR_0_PROG_BAR_MAX_0,"ProgBar0ProgBarMax0=a");
        _fr.add(PROG_BAR_0_PROG_BAR_OR_0,"ProgBar0ProgBarOr0=a");
        _fr.add(TEXT_LABEL_0_SET_LABEL_TEXT_0,"TextLabel0SetLabelText0=a");
        _fr.add(TEXT_LABEL_0_TEXT_LABEL_0,"TextLabel0TextLabel0=a");
        _fr.add(IMAGE_LABEL_0_SET_LABEL_IMAGE_0,"ImageLabel0SetLabelImage0=a");
        _fr.add(IMAGE_LABEL_0_IMAGE_LABEL_0,"ImageLabel0ImageLabel0=a");
        _fr.add(FONT_0_FONT_STRING_WIDTH_0,"Font0FontStringWidth0=a");
        _fr.add(FONT_1_FONT_STRING_WIDTH_0,"Font1FontStringWidth0=a");
        _fr.add(FONT_1_FONT_STRING_WIDTH_1,"Font1FontStringWidth1=b");
        _fr.add(FONT_0_FONT_STRING_HEIGHT_0,"Font0FontStringHeight0=a");
        _fr.add(FONT_0_FONT_0,"Font0Font0=a");
        _fr.add(FONT_1_FONT_0,"Font1Font0=a");
        _fr.add(FONT_1_FONT_1,"Font1Font1=b");
        _fr.add(FONT_1_FONT_2,"Font1Font2=c");
        _fr.add(FONT_1_FONT_3,"Font1Font3=d");
        _fr.add(COLOR_0_COLOR_0,"Color0Color0=a");
        _fr.add(COLOR_1_COLOR_0,"Color1Color0=a");
        _fr.add(COLOR_1_COLOR_1,"Color1Color1=b");
        _fr.add(COLOR_2_COLOR_0,"Color2Color0=a");
        _fr.add(COLOR_2_COLOR_1,"Color2Color1=b");
        _fr.add(COLOR_2_COLOR_2,"Color2Color2=c");
        _fr.add(COLOR_3_COLOR_0,"Color3Color0=a");
        _fr.add(COLOR_3_COLOR_1,"Color3Color1=b");
        _fr.add(COLOR_3_COLOR_2,"Color3Color2=c");
        _fr.add(COLOR_3_COLOR_3,"Color3Color3=d");
        _fr.add(IMAGE_0_TREE_NODE_GET_USER_OBJECT_0,"Image0TreeNodeGetUserObject0=a");
        _fr.add(IMAGE_0_TREE_NODE_GET_USER_OBJECT_1,"Image0TreeNodeGetUserObject1=b");
        _fr.add(IMAGE_0_TREE_NODE_SET_USER_OBJECT_0,"Image0TreeNodeSetUserObject0=a");
        _fr.add(IMAGE_0_TREE_NODE_SET_USER_OBJECT_1,"Image0TreeNodeSetUserObject1=b");
        _fr.add(IMAGE_0_TREE_NODE_SET_USER_OBJECT_2,"Image0TreeNodeSetUserObject2=c");
        _fr.add(IMAGE_0_IMAGE_SET_COLOR_0,"Image0ImageSetColor0=a");
        _fr.add(IMAGE_0_SET_FONT_0,"Image0SetFont0=a");
        _fr.add(IMAGE_0_IMAGE_DRAW_0,"Image0ImageDraw0=a");
        _fr.add(IMAGE_0_IMAGE_DRAW_1,"Image0ImageDraw1=b");
        _fr.add(IMAGE_0_IMAGE_DRAW_2,"Image0ImageDraw2=c");
        _fr.add(IMAGE_1_IMAGE_DRAW_0,"Image1ImageDraw0=a");
        _fr.add(IMAGE_1_IMAGE_DRAW_1,"Image1ImageDraw1=b");
        _fr.add(IMAGE_1_IMAGE_DRAW_2,"Image1ImageDraw2=c");
        _fr.add(IMAGE_0_IMAGE_DRAW_LINE_0,"Image0ImageDrawLine0=a");
        _fr.add(IMAGE_0_IMAGE_DRAW_LINE_1,"Image0ImageDrawLine1=b");
        _fr.add(IMAGE_0_IMAGE_DRAW_LINE_2,"Image0ImageDrawLine2=c");
        _fr.add(IMAGE_0_IMAGE_DRAW_LINE_3,"Image0ImageDrawLine3=d");
        _fr.add(IMAGE_0_IMAGE_DRAW_RECT_0,"Image0ImageDrawRect0=a");
        _fr.add(IMAGE_0_IMAGE_DRAW_RECT_1,"Image0ImageDrawRect1=b");
        _fr.add(IMAGE_0_IMAGE_DRAW_RECT_2,"Image0ImageDrawRect2=c");
        _fr.add(IMAGE_0_IMAGE_DRAW_RECT_3,"Image0ImageDrawRect3=d");
        _fr.add(IMAGE_0_IMAGE_DRAW_OVAL_0,"Image0ImageDrawOval0=a");
        _fr.add(IMAGE_0_IMAGE_DRAW_OVAL_1,"Image0ImageDrawOval1=b");
        _fr.add(IMAGE_0_IMAGE_DRAW_OVAL_2,"Image0ImageDrawOval2=c");
        _fr.add(IMAGE_0_IMAGE_DRAW_OVAL_3,"Image0ImageDrawOval3=d");
        _fr.add(IMAGE_0_IMAGE_DRAW_POLYGON_0,"Image0ImageDrawPolygon0=a");
        _fr.add(IMAGE_0_IMAGE_DRAW_POLYGON_1,"Image0ImageDrawPolygon1=b");
        _fr.add(IMAGE_0_IMAGE_FILL_RECT_0,"Image0ImageFillRect0=a");
        _fr.add(IMAGE_0_IMAGE_FILL_RECT_1,"Image0ImageFillRect1=b");
        _fr.add(IMAGE_0_IMAGE_FILL_RECT_2,"Image0ImageFillRect2=c");
        _fr.add(IMAGE_0_IMAGE_FILL_RECT_3,"Image0ImageFillRect3=d");
        _fr.add(IMAGE_0_IMAGE_FILL_OVAL_0,"Image0ImageFillOval0=a");
        _fr.add(IMAGE_0_IMAGE_FILL_OVAL_1,"Image0ImageFillOval1=b");
        _fr.add(IMAGE_0_IMAGE_FILL_OVAL_2,"Image0ImageFillOval2=c");
        _fr.add(IMAGE_0_IMAGE_FILL_OVAL_3,"Image0ImageFillOval3=d");
        _fr.add(IMAGE_0_IMAGE_FILL_POLYGON_0,"Image0ImageFillPolygon0=a");
        _fr.add(IMAGE_0_IMAGE_FILL_POLYGON_1,"Image0ImageFillPolygon1=b");
        _fr.add(IMAGE_0_IMAGE_EQ_0,"Image0ImageEq0=a");
        _fr.add(IMAGE_0_IMAGE_EQ_1,"Image0ImageEq1=b");
        _fr.add(IMAGE_0_IMAGE_0,"Image0Image0=a");
        _fr.add(IMAGE_0_IMAGE_1,"Image0Image1=b");
        _fr.add(IMAGE_0_IMAGE_2,"Image0Image2=c");
        _fr.add(RENDER_0_RENDER_SET_HEIGHT_0,"Render0RenderSetHeight0=a");
        _fr.add(RENDER_0_RENDER_SET_WIDTH_0,"Render0RenderSetWidth0=a");
        _fr.add(RENDER_0_COMPONENT_SET_PAINT_0,"Render0ComponentSetPaint0=a");
        _fr.add(GR_LIST_0_GR_LIST_SET_RENDER_0,"GrList0GrListSetRender0=a");
        _fr.add(GR_LIST_0_GR_LIST_ADD_SELECTION_0,"GrList0GrListAddSelection0=a");
        _fr.add(GR_LIST_0_GR_LIST_REMOVE_SELECTION_0,"GrList0GrListRemoveSelection0=a");
        _fr.add(GR_LIST_0_GR_LIST_SET_VISIBLE_ROW_COUNT_0,"GrList0GrListSetVisibleRowCount0=a");
        _fr.add(GR_LIST_0_TABBED_PANE_ADD_0,"GrList0TabbedPaneAdd0=a");
        _fr.add(GR_LIST_0_TABBED_PANE_ADD_1,"GrList0TabbedPaneAdd1=b");
        _fr.add(GR_LIST_1_TABBED_PANE_ADD_0,"GrList1TabbedPaneAdd0=a");
        _fr.add(GR_LIST_1_TABBED_PANE_ADD_1,"GrList1TabbedPaneAdd1=b");
        _fr.add(GR_LIST_1_TABBED_PANE_ADD_2,"GrList1TabbedPaneAdd2=c");
        _fr.add(GR_LIST_2_TABBED_PANE_ADD_0,"GrList2TabbedPaneAdd0=a");
        _fr.add(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_0,"GrList0TreeNodeSetUserObject0=a");
        _fr.add(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_1,"GrList0TreeNodeSetUserObject1=b");
        _fr.add(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_2,"GrList0TreeNodeSetUserObject2=c");
        _fr.add(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_0,"GrList1TreeNodeSetUserObject0=a");
        _fr.add(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_1,"GrList1TreeNodeSetUserObject1=b");
        _fr.add(GR_LIST_0_GR_LIST_SET_SELECTED_INDEXES_0,"GrList0GrListSetSelectedIndexes0=a");
        _fr.add(GR_LIST_0_REMOVE_COMPO_0,"GrList0RemoveCompo0=a");
        _fr.add(GR_LIST_0_GR_LIST_0,"GrList0GrList0=a");
        _fr.add(COMBO_0_COMBO_ADD_LISTENER_0,"Combo0ComboAddListener0=a");
        _fr.add(COMBO_0_COMBO_REMOVE_LISTENER_0,"Combo0ComboRemoveListener0=a");
        _fr.add(COMBO_0_TABBED_PANE_ADD_0,"Combo0TabbedPaneAdd0=a");
        _fr.add(COMBO_0_COMBO_SELECT_ITEM_0,"Combo0ComboSelectItem0=a");
        _fr.add(COMBO_0_COMBO_REMOVE_ITEM_0,"Combo0ComboRemoveItem0=a");
        _fr.add(COMBO_0_COMBO_0,"Combo0Combo0=a");
        _fr.add(COMBO_1_COMBO_0,"Combo1Combo0=a");
        _fr.add(COMBO_1_COMBO_1,"Combo1Combo1=b");
        _fr.add(BUTTON_GROUP_0_TABBED_PANE_ADD_0,"ButtonGroup0TabbedPaneAdd0=a");
        _fr.add(BUTTON_GROUP_0_TABBED_PANE_REMOVE_0,"ButtonGroup0TabbedPaneRemove0=a");
        _fr.add(POPUP_MENU_0_TABBED_PANE_ADD_0,"PopupMenu0TabbedPaneAdd0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_GET_COMP_0,"PopupMenu0PopupMenuGetComp0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_REMOVE_COMP_0,"PopupMenu0PopupMenuRemoveComp0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_ADD_MENU_0,"PopupMenu0PopupMenuAddMenu0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_GET_MENU_0,"PopupMenu0PopupMenuGetMenu0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_REMOVE_MENU_0,"PopupMenu0PopupMenuRemoveMenu0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_SHOW_0,"PopupMenu0PopupMenuShow0=a");
        _fr.add(POPUP_MENU_0_POPUP_MENU_SHOW_1,"PopupMenu0PopupMenuShow1=b");
        _fr.add(POPUP_MENU_0_POPUP_MENU_SHOW_2,"PopupMenu0PopupMenuShow2=c");
        _fr.add(RADIO_0_RADIO_SET_SELECTED_0,"Radio0RadioSetSelected0=a");
        _fr.add(RADIO_0_SET_LABEL_TEXT_0,"Radio0SetLabelText0=a");
        _fr.add(RADIO_0_ADD_CHANGE_0,"Radio0AddChange0=a");
        _fr.add(RADIO_0_RADIO_0,"Radio0Radio0=a");
        _fr.add(RADIO_1_RADIO_0,"Radio1Radio0=a");
        _fr.add(RADIO_1_RADIO_1,"Radio1Radio1=b");
        _fr.add(CHECK_BOX_0_RADIO_SET_SELECTED_0,"CheckBox0RadioSetSelected0=a");
        _fr.add(CHECK_BOX_0_SET_LABEL_TEXT_0,"CheckBox0SetLabelText0=a");
        _fr.add(CHECK_BOX_0_CHECK_BOX_0,"CheckBox0CheckBox0=a");
        _fr.add(CHECK_BOX_1_CHECK_BOX_0,"CheckBox1CheckBox0=a");
        _fr.add(CHECK_BOX_1_CHECK_BOX_1,"CheckBox1CheckBox1=b");
        _fr.add(TEXT_FIELD_0_SET_LABEL_TEXT_0,"TextField0SetLabelText0=a");
        _fr.add(TEXT_FIELD_0_TEXT_FIELD_ADD_ACTION_0,"TextField0TextFieldAddAction0=a");
        _fr.add(TEXT_FIELD_0_TEXT_FIELD_REMOVE_ACTION_0,"TextField0TextFieldRemoveAction0=a");
        _fr.add(TEXT_FIELD_0_TEXT_FIELD_0,"TextField0TextField0=a");
        _fr.add(TEXT_FIELD_1_TEXT_FIELD_0,"TextField1TextField0=a");
        _fr.add(TEXT_FIELD_2_TEXT_FIELD_0,"TextField2TextField0=a");
        _fr.add(TEXT_FIELD_2_TEXT_FIELD_1,"TextField2TextField1=b");
        _fr.add(TEXT_AREA_0_SET_LABEL_TEXT_0,"TextArea0SetLabelText0=a");
        _fr.add(TEXT_AREA_0_TABBED_PANE_ADD_0,"TextArea0TabbedPaneAdd0=a");
        _fr.add(TEXT_AREA_0_TREE_NODE_INSERT_0,"TextArea0TreeNodeInsert0=a");
        _fr.add(TEXT_AREA_0_TREE_NODE_INSERT_1,"TextArea0TreeNodeInsert1=b");
        _fr.add(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_START_0,"TextArea0TextAreaSetSelectionStart0=a");
        _fr.add(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_END_0,"TextArea0TextAreaSetSelectionEnd0=a");
        _fr.add(TEXT_AREA_0_TEXT_AREA_SET_TAB_SIZE_0,"TextArea0TextAreaSetTabSize0=a");
        _fr.add(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_0,"TextArea0TextAreaReplaceRange0=a");
        _fr.add(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_1,"TextArea0TextAreaReplaceRange1=b");
        _fr.add(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_2,"TextArea0TextAreaReplaceRange2=c");
        _fr.add(TEXT_AREA_0_TEXT_AREA_REPLACE_SELECTION_0,"TextArea0TextAreaReplaceSelection0=a");
        _fr.add(TEXT_AREA_0_TREE_GET_SELECTED_0,"TextArea0TreeGetSelected0=a");
        _fr.add(TEXT_AREA_0_TREE_GET_SELECTED_1,"TextArea0TreeGetSelected1=b");
        _fr.add(TEXT_AREA_0_TEXT_AREA_0,"TextArea0TextArea0=a");
        _fr.add(TEXT_AREA_1_TEXT_AREA_0,"TextArea1TextArea0=a");
        _fr.add(TEXT_AREA_1_TEXT_AREA_1,"TextArea1TextArea1=b");
        _fr.add(TEXT_AREA_2_TEXT_AREA_0,"TextArea2TextArea0=a");
        _fr.add(TEXT_AREA_2_TEXT_AREA_1,"TextArea2TextArea1=b");
        _fr.add(TEXT_AREA_2_TEXT_AREA_2,"TextArea2TextArea2=c");
        _fr.add(SPINNER_0_SPINNER_SET_MAX_0,"Spinner0SpinnerSetMax0=a");
        _fr.add(SPINNER_0_SPINNER_SET_MIN_0,"Spinner0SpinnerSetMin0=a");
        _fr.add(SPINNER_0_TREE_NODE_SET_USER_OBJECT_0,"Spinner0TreeNodeSetUserObject0=a");
        _fr.add(SPINNER_0_SPINNER_SET_STEP_0,"Spinner0SpinnerSetStep0=a");
        _fr.add(SPINNER_0_SPINNER_SET_RANGE_0,"Spinner0SpinnerSetRange0=a");
        _fr.add(SPINNER_0_SPINNER_SET_RANGE_1,"Spinner0SpinnerSetRange1=b");
        _fr.add(SPINNER_0_SPINNER_SET_RANGE_VALUE_0,"Spinner0SpinnerSetRangeValue0=a");
        _fr.add(SPINNER_0_SPINNER_SET_RANGE_VALUE_1,"Spinner0SpinnerSetRangeValue1=b");
        _fr.add(SPINNER_0_SPINNER_SET_RANGE_VALUE_2,"Spinner0SpinnerSetRangeValue2=c");
        _fr.add(SPINNER_0_ADD_CHANGE_0,"Spinner0AddChange0=a");
        _fr.add(SPINNER_0_SPINNER_0,"Spinner0Spinner0=a");
        _fr.add(SPINNER_0_SPINNER_1,"Spinner0Spinner1=b");
        _fr.add(SPINNER_0_SPINNER_2,"Spinner0Spinner2=c");
        _fr.add(SPINNER_0_SPINNER_3,"Spinner0Spinner3=d");
        _fr.add(SLIDER_0_SPINNER_SET_MAX_0,"Slider0SpinnerSetMax0=a");
        _fr.add(SLIDER_0_SPINNER_SET_MIN_0,"Slider0SpinnerSetMin0=a");
        _fr.add(SLIDER_0_TREE_NODE_SET_USER_OBJECT_0,"Slider0TreeNodeSetUserObject0=a");
        _fr.add(SLIDER_0_SLIDER_SET_ORIENTATION_0,"Slider0SliderSetOrientation0=a");
        _fr.add(SLIDER_0_ADD_CHANGE_0,"Slider0AddChange0=a");
        _fr.add(SLIDER_0_SLIDER_0,"Slider0Slider0=a");
        _fr.add(SLIDER_1_SLIDER_0,"Slider1Slider0=a");
        _fr.add(SLIDER_1_SLIDER_1,"Slider1Slider1=b");
        _fr.add(SLIDER_2_SLIDER_0,"Slider2Slider0=a");
        _fr.add(SLIDER_2_SLIDER_1,"Slider2Slider1=b");
        _fr.add(SLIDER_2_SLIDER_2,"Slider2Slider2=c");
        _fr.add(SLIDER_3_SLIDER_0,"Slider3Slider0=a");
        _fr.add(SLIDER_3_SLIDER_1,"Slider3Slider1=b");
        _fr.add(SLIDER_3_SLIDER_2,"Slider3Slider2=c");
        _fr.add(SLIDER_3_SLIDER_3,"Slider3Slider3=d");
        _fr.add(MENU_BAR_0_TABBED_PANE_ADD_0,"MenuBar0TabbedPaneAdd0=a");
        _fr.add(MENU_BAR_0_TREE_NODE_REMOVE_0,"MenuBar0TreeNodeRemove0=a");
        _fr.add(MENU_BAR_0_TREE_NODE_GET_USER_OBJECT_0,"MenuBar0TreeNodeGetUserObject0=a");
        _fr.add(ABS_MENU_0_ABS_MENU_SET_TEXT_0,"AbsMenu0AbsMenuSetText0=a");
        _fr.add(ABS_MENU_0_INPUT_SET_ENABLED_0,"AbsMenu0InputSetEnabled0=a");
        _fr.add(ABS_MENU_0_ABS_MENU_SET_DEEP_ENABLED_0,"AbsMenu0AbsMenuSetDeepEnabled0=a");
        _fr.add(MENU_0_TABBED_PANE_ADD_0,"Menu0TabbedPaneAdd0=a");
        _fr.add(MENU_0_TREE_NODE_REMOVE_0,"Menu0TreeNodeRemove0=a");
        _fr.add(MENU_0_TREE_NODE_GET_USER_OBJECT_0,"Menu0TreeNodeGetUserObject0=a");
        _fr.add(MENU_0_MENU_0,"Menu0Menu0=a");
        _fr.add(ABS_MENU_ITEM_0_TABBED_PANE_ADD_0,"AbsMenuItem0TabbedPaneAdd0=a");
        _fr.add(MENU_ITEM_0_MENU_ITEM_0,"MenuItem0MenuItem0=a");
        _fr.add(MENU_ITEM_CHECK_0_RADIO_SET_SELECTED_0,"MenuItemCheck0RadioSetSelected0=a");
        _fr.add(MENU_ITEM_CHECK_0_MENU_ITEM_CHECK_0,"MenuItemCheck0MenuItemCheck0=a");
        _fr.add(COMMAND_0_BINDING_0,"Command0Binding0=a");
        _fr.add(COMMAND_0_ACTION_0,"Command0Action0=a");
        _fr.add(COMPONENT_0_BIND_0,"Component0Bind0=a");
        _fr.add(COMPONENT_0_BIND_1,"Component0Bind1=b");
        _fr.add(COMPONENT_0_BIND_2,"Component0Bind2=c");
        _fr.add(COMPONENT_0_UNBIND_0,"Component0Unbind0=a");
        _fr.add(COMPONENT_0_UNBIND_1,"Component0Unbind1=b");
        _fr.add(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_0,"ActionListenerImplicit0Implicit0=a");
        _fr.add(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_1,"ActionListenerImplicit0Implicit1=a");
        _fr.add(CELL_RENDER_IMPLICIT_0_IMPLICIT_0,"CellRenderImplicit0Implicit0=a");
        _fr.add(CELL_RENDER_IMPLICIT_0_IMPLICIT_1,"CellRenderImplicit0Implicit1=a");
        _fr.add(CELL_RENDER_0_GENERATE_0,"CellRender0Generate0=a");
        _fr.add(CELL_RENDER_0_GENERATE_1,"CellRender0Generate1=b");
        _fr.add(CELL_RENDER_0_GENERATE_2,"CellRender0Generate2=c");
        _fr.add(CELL_RENDER_0_GENERATE_3,"CellRender0Generate3=d");
        _fr.add(CELL_RENDER_0_GENERATE_4,"CellRender0Generate4=e");
        _fr.add(CELL_RENDER_0_GENERATE_5,"CellRender0Generate5=f");
        _fr.add(CELL_RENDER_0_GENERATE_6,"CellRender0Generate6=g");
        _fr.add(CELL_RENDER_0_GENERATE_0_0,"CellRender0Generate00=a");
        _fr.add(CELL_RENDER_0_GENERATE_0_1,"CellRender0Generate01=b");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ACTION_LISTENER_0_ACTION_PERFORMED_0),aliasActionListener0ActionPerformed0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ACTION_0_WRAP_0),aliasAction0Wrap0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ACTION_0_ENABLED_0),aliasAction0Enabled0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_LISTENER_0_TREE_LISTENER_VALUE_CHANGED_0),aliasTreeListener0TreeListenerValueChanged0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_0),aliasTableListener0TableValueTableChanged0),new KeyValueMemberName(_mapping.getVal(TABLE_LISTENER_0_TABLE_VALUE_TABLE_CHANGED_1),aliasTableListener0TableValueTableChanged1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_CLICKED_0),aliasMouseListener0MouseClicked0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_PRESSED_0),aliasMouseListener0MousePressed0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_RELEASED_0),aliasMouseListener0MouseReleased0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_ENTERED_0),aliasMouseListener0MouseEntered0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_EXITED_0),aliasMouseListener0MouseExited0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_DRAGGED_0),aliasMouseListener0MouseDragged0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_LISTENER_0_MOUSE_MOVED_0),aliasMouseListener0MouseMoved0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WHEEL_LISTENER_0_WHEEL_MOVE_0),aliasWheelListener0WheelMove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(KEY_LISTENER_0_KEY_PRESSED_0),aliasKeyListener0KeyPressed0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(KEY_LISTENER_0_KEY_TYPED_0),aliasKeyListener0KeyTyped0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(KEY_LISTENER_0_KEY_RELEASED_0),aliasKeyListener0KeyReleased0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_OPENED_0),aliasWindowListener0WindowOpened0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_CLOSING_0),aliasWindowListener0WindowClosing0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_CLOSED_0),aliasWindowListener0WindowClosed0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_ICONIFIED_0),aliasWindowListener0WindowIconified0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_DEICONIFIED_0),aliasWindowListener0WindowDeiconified0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_ACTIVATED_0),aliasWindowListener0WindowActivated0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_LISTENER_0_WINDOW_DEACTIVATED_0),aliasWindowListener0WindowDeactivated0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LIST_SELECTION_0_VALUE_CHANGED_0),aliasListSelection0ValueChanged0),new KeyValueMemberName(_mapping.getVal(LIST_SELECTION_0_VALUE_CHANGED_1),aliasListSelection0ValueChanged1),new KeyValueMemberName(_mapping.getVal(LIST_SELECTION_0_VALUE_CHANGED_2),aliasListSelection0ValueChanged2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_METHOD_0),aliasPaint0PaintMethod0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PAINT_0_TABBED_PANE_SET_0),aliasPaint0TabbedPaneSet0),new KeyValueMemberName(_mapping.getVal(PAINT_0_TABBED_PANE_SET_1),aliasPaint0TabbedPaneSet1),new KeyValueMemberName(_mapping.getVal(PAINT_0_TABBED_PANE_SET_2),aliasPaint0TabbedPaneSet2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PAINT_0_TABBED_PANE_ADD_0),aliasPaint0TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(PAINT_0_TABBED_PANE_ADD_1),aliasPaint0TabbedPaneAdd1),new KeyValueMemberName(_mapping.getVal(PAINT_0_TABBED_PANE_ADD_2),aliasPaint0TabbedPaneAdd2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PAINT_1_TABBED_PANE_ADD_0),aliasPaint1TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(PAINT_1_TABBED_PANE_ADD_1),aliasPaint1TabbedPaneAdd1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_0),aliasPaint0PaintRefresh0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_0),aliasPaint0PaintRefreshOne0),new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_1),aliasPaint0PaintRefreshOne1),new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_2),aliasPaint0PaintRefreshOne2),new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_3),aliasPaint0PaintRefreshOne3),new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_4),aliasPaint0PaintRefreshOne4),new KeyValueMemberName(_mapping.getVal(PAINT_0_PAINT_REFRESH_ONE_5),aliasPaint0PaintRefreshOne5)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_0_SET_CONTENT_0),aliasWindowType0SetContent0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_0_WINDOW_TYPE_RELATIVE_0),aliasWindowType0WindowTypeRelative0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_0_COMPONENT_SET_VISIBLE_0),aliasWindowType0ComponentSetVisible0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_0_SET_MENU_BAR_0),aliasWindowType0SetMenuBar0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_0_ADD_WINDOW_LISTENER_0),aliasWindowType0AddWindowListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_TYPE_0_REMOVE_WINDOW_LISTENER_0),aliasWindowType0RemoveWindowListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_0),aliasConfirm0ConfirmField0),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_1),aliasConfirm0ConfirmField1),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_2),aliasConfirm0ConfirmField2),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_3),aliasConfirm0ConfirmField3),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_4),aliasConfirm0ConfirmField4),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_5),aliasConfirm0ConfirmField5),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FIELD_6),aliasConfirm0ConfirmField6)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_0),aliasConfirm1ConfirmField0),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_1),aliasConfirm1ConfirmField1),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_2),aliasConfirm1ConfirmField2),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_3),aliasConfirm1ConfirmField3),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_4),aliasConfirm1ConfirmField4),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FIELD_5),aliasConfirm1ConfirmField5)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_0),aliasConfirm0ConfirmFull0),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_1),aliasConfirm0ConfirmFull1),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_2),aliasConfirm0ConfirmFull2),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_3),aliasConfirm0ConfirmFull3),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_4),aliasConfirm0ConfirmFull4),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_5),aliasConfirm0ConfirmFull5),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_FULL_6),aliasConfirm0ConfirmFull6)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FULL_0),aliasConfirm1ConfirmFull0),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FULL_1),aliasConfirm1ConfirmFull1),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FULL_2),aliasConfirm1ConfirmFull2),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FULL_3),aliasConfirm1ConfirmFull3),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FULL_4),aliasConfirm1ConfirmFull4),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_FULL_5),aliasConfirm1ConfirmFull5)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_0),aliasConfirm0ConfirmYesNo0),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_1),aliasConfirm0ConfirmYesNo1),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_2),aliasConfirm0ConfirmYesNo2),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_3),aliasConfirm0ConfirmYesNo3),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_4),aliasConfirm0ConfirmYesNo4),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_YES_NO_5),aliasConfirm0ConfirmYesNo5)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_0),aliasConfirm1ConfirmYesNo0),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_1),aliasConfirm1ConfirmYesNo1),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_2),aliasConfirm1ConfirmYesNo2),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_3),aliasConfirm1ConfirmYesNo3),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_YES_NO_4),aliasConfirm1ConfirmYesNo4)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_OK_0),aliasConfirm0ConfirmOk0),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_OK_1),aliasConfirm0ConfirmOk1),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_OK_2),aliasConfirm0ConfirmOk2),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_OK_3),aliasConfirm0ConfirmOk3),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_OK_4),aliasConfirm0ConfirmOk4)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_OK_0),aliasConfirm1ConfirmOk0),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_OK_1),aliasConfirm1ConfirmOk1),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_OK_2),aliasConfirm1ConfirmOk2),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_OK_3),aliasConfirm1ConfirmOk3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_0),aliasConfirm0ConfirmMessage0),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_1),aliasConfirm0ConfirmMessage1),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_2),aliasConfirm0ConfirmMessage2),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_3),aliasConfirm0ConfirmMessage3),new KeyValueMemberName(_mapping.getVal(CONFIRM_0_CONFIRM_MESSAGE_4),aliasConfirm0ConfirmMessage4)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_0),aliasConfirm1ConfirmMessage0),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_1),aliasConfirm1ConfirmMessage1),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_2),aliasConfirm1ConfirmMessage2),new KeyValueMemberName(_mapping.getVal(CONFIRM_1_CONFIRM_MESSAGE_3),aliasConfirm1ConfirmMessage3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_SET_0_TABBED_PANE_ADD_0),aliasWindowSet0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_SET_0_WINDOW_SET_CONTAINS_0),aliasWindowSet0WindowSetContains0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WINDOW_SET_0_TREE_NODE_REMOVE_0),aliasWindowSet0TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DIALOG_0_DIALOG_SET_MODAL_0),aliasDialog0DialogSetModal0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_SET_FONT_0),aliasComponent0SetFont0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_BACK_0),aliasComponent0CompBack0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_FORE_0),aliasComponent0CompFore0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_FOCUSABLE_0),aliasComponent0CompFocusable0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_OPAQUE_0),aliasComponent0CompOpaque0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_TOOL_TIP_0),aliasComponent0CompToolTip0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_LOC_0),aliasComponent0CompLoc0),new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_LOC_1),aliasComponent0CompLoc1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_BOR_LINE_0),aliasComponent0CompBorLine0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_1_COMP_BOR_LINE_0),aliasComponent1CompBorLine0),new KeyValueMemberName(_mapping.getVal(COMPONENT_1_COMP_BOR_LINE_1),aliasComponent1CompBorLine1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMP_BOR_TITLE_0),aliasComponent0CompBorTitle0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMPONENT_SET_PAINT_0),aliasComponent0ComponentSetPaint0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMPONENT_SET_AUTOSCROLLS_0),aliasComponent0ComponentSetAutoscrolls0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMPONENT_SET_PREFERRED_SIZE_0),aliasComponent0ComponentSetPreferredSize0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMPONENT_SET_VISIBLE_0),aliasComponent0ComponentSetVisible0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_COMPONENT_INVOKE_LATER_0),aliasComponent0ComponentInvokeLater0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_ADD_KEY_LISTENER_0),aliasComponent0AddKeyListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_ADD_FOCUS_LISTENER_0),aliasComponent0AddFocusListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_ADD_WHEEL_LISTENER_0),aliasComponent0AddWheelListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_ADD_LISTENER_0),aliasComponent0AddListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_REMOVE_KEY_LISTENER_0),aliasComponent0RemoveKeyListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_REMOVE_FOCUS_LISTENER_0),aliasComponent0RemoveFocusListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_REMOVE_WHEEL_LISTENER_0),aliasComponent0RemoveWheelListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_REMOVE_LISTENER_0),aliasComponent0RemoveListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DIMENSION_0_DIMENSION_0),aliasDimension0Dimension0),new KeyValueMemberName(_mapping.getVal(DIMENSION_0_DIMENSION_1),aliasDimension0Dimension1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DIMENSION_1_DIMENSION_0),aliasDimension1Dimension0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_EQ_0),aliasTreeNode0TreeNodeEq0),new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_EQ_1),aliasTreeNode0TreeNodeEq1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_ADD_0),aliasTreeNode0TreeNodeAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_INSERT_0),aliasTreeNode0TreeNodeInsert0),new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_INSERT_1),aliasTreeNode0TreeNodeInsert1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_REMOVE_0),aliasTreeNode0TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_1_TREE_NODE_REMOVE_0),aliasTreeNode1TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_SET_USER_OBJECT_0),aliasTreeNode0TreeNodeSetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_IS_ANCESTOR_0),aliasTreeNode0TreeNodeIsAncestor0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_IS_DESCENDANT_0),aliasTreeNode0TreeNodeIsDescendant0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_NODE_0_TREE_NODE_0),aliasTreeNode0TreeNode0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_0_TREE_ADD_TREE_LISTENER_0),aliasTree0TreeAddTreeListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_0_TREE_REMOVE_TREE_LISTENER_0),aliasTree0TreeRemoveTreeListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_0_TREE_SET_ROOT_VISIBLE_0),aliasTree0TreeSetRootVisible0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_0_TREE_GET_SELECTED_0),aliasTree0TreeGetSelected0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TREE_0_TREE_0),aliasTree0Tree0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_ADD_HEADER_0),aliasTableGui0TableAddHeader0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_ADD_SELECT_0),aliasTableGui0TableAddSelect0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_SET_MULTIPLE_0),aliasTableGui0TableSetMultiple0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_SET_REORDER_0),aliasTableGui0TableSetReorder0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_GET_COLUMN_NAME_0),aliasTableGui0TableGetColumnName0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_0),aliasTableGui0TableGetColumnAtPoint0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_GET_COLUMN_AT_POINT_1),aliasTableGui0TableGetColumnAtPoint1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_0),aliasTableGui0TableGetRowAtPoint0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_GET_ROW_AT_POINT_1),aliasTableGui0TableGetRowAtPoint1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_SET_ROW_COUNT_0),aliasTableGui0TableSetRowCount0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_SET_COLUMNS_0),aliasTableGui0TableSetColumns0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_MOVE_COLUMN_0),aliasTableGui0TableMoveColumn0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_MOVE_COLUMN_1),aliasTableGui0TableMoveColumn1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_ADD_INTERVAL_0),aliasTableGui0TableAddInterval0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_ADD_INTERVAL_1),aliasTableGui0TableAddInterval1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_0),aliasTableGui0TableRemoveInterval0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_REMOVE_INTERVAL_1),aliasTableGui0TableRemoveInterval1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_0),aliasTableGui0TreeNodeSetUserObject0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_1),aliasTableGui0TreeNodeSetUserObject1),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TREE_NODE_SET_USER_OBJECT_2),aliasTableGui0TreeNodeSetUserObject2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_0),aliasTableGui0TreeNodeGetUserObject0),new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TREE_NODE_GET_USER_OBJECT_1),aliasTableGui0TreeNodeGetUserObject1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABLE_GUI_0_TABLE_GUI_0),aliasTableGui0TableGui0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_0),aliasActionEvent0ActionEvent0),new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_1),aliasActionEvent0ActionEvent1),new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_2),aliasActionEvent0ActionEvent2),new KeyValueMemberName(_mapping.getVal(ACTION_EVENT_0_ACTION_EVENT_3),aliasActionEvent0ActionEvent3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_0),aliasMouseEvent0MouseEvent0),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_1),aliasMouseEvent0MouseEvent1),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_2),aliasMouseEvent0MouseEvent2),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_3),aliasMouseEvent0MouseEvent3),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_4),aliasMouseEvent0MouseEvent4),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_5),aliasMouseEvent0MouseEvent5),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_6),aliasMouseEvent0MouseEvent6),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_7),aliasMouseEvent0MouseEvent7),new KeyValueMemberName(_mapping.getVal(MOUSE_EVENT_0_MOUSE_EVENT_8),aliasMouseEvent0MouseEvent8)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_0),aliasWheelEvent0WheelEvent0),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_1),aliasWheelEvent0WheelEvent1),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_2),aliasWheelEvent0WheelEvent2),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_3),aliasWheelEvent0WheelEvent3),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_4),aliasWheelEvent0WheelEvent4),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_5),aliasWheelEvent0WheelEvent5),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_6),aliasWheelEvent0WheelEvent6),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_7),aliasWheelEvent0WheelEvent7),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_8),aliasWheelEvent0WheelEvent8),new KeyValueMemberName(_mapping.getVal(WHEEL_EVENT_0_WHEEL_EVENT_9),aliasWheelEvent0WheelEvent9)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(KEY_EVENT_0_KEY_EVENT_0),aliasKeyEvent0KeyEvent0),new KeyValueMemberName(_mapping.getVal(KEY_EVENT_0_KEY_EVENT_1),aliasKeyEvent0KeyEvent1),new KeyValueMemberName(_mapping.getVal(KEY_EVENT_0_KEY_EVENT_2),aliasKeyEvent0KeyEvent2),new KeyValueMemberName(_mapping.getVal(KEY_EVENT_0_KEY_EVENT_3),aliasKeyEvent0KeyEvent3),new KeyValueMemberName(_mapping.getVal(KEY_EVENT_0_KEY_EVENT_4),aliasKeyEvent0KeyEvent4)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_0_TABBED_PANE_ADD_0),aliasPanel0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_1_TABBED_PANE_ADD_0),aliasPanel1TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(PANEL_1_TABBED_PANE_ADD_1),aliasPanel1TabbedPaneAdd1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_0_REMOVE_COMPO_0),aliasPanel0RemoveCompo0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_1_REMOVE_COMPO_0),aliasPanel1RemoveCompo0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_0_TREE_NODE_GET_USER_OBJECT_0),aliasPanel0TreeNodeGetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_0_PANEL_GRID_0),aliasPanel0PanelGrid0),new KeyValueMemberName(_mapping.getVal(PANEL_0_PANEL_GRID_1),aliasPanel0PanelGrid1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_0_TABBED_PANE_ADD_0),aliasPanelBorder0TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(PANEL_BORDER_0_TABBED_PANE_ADD_1),aliasPanelBorder0TabbedPaneAdd1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_ADD_0),aliasTabbedPane0TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_ADD_1),aliasTabbedPane0TabbedPaneAdd1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_SEL_INDEX_0),aliasTabbedPane0TabbedPaneSelIndex0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_INDEX_0),aliasTabbedPane0TabbedPaneIndex0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TREE_NODE_REMOVE_0),aliasTabbedPane0TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_1_TREE_NODE_REMOVE_0),aliasTabbedPane1TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_0),aliasTabbedPane0TreeNodeSetUserObject0),new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TREE_NODE_SET_USER_OBJECT_1),aliasTabbedPane0TreeNodeSetUserObject1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_SET_TITLE_0),aliasTabbedPane0TabbedPaneSetTitle0),new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_SET_TITLE_1),aliasTabbedPane0TabbedPaneSetTitle1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TREE_NODE_GET_USER_OBJECT_0),aliasTabbedPane0TreeNodeGetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TABBED_PANE_0_TABBED_PANE_GET_TITLE_0),aliasTabbedPane0TabbedPaneGetTitle0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_HORIZONTAL_VALUE_0),aliasScrollPane0ScrollPaneHorizontalValue0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_VERTICAL_VALUE_0),aliasScrollPane0ScrollPaneVerticalValue0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_SET_VIEW_0),aliasScrollPane0ScrollPaneSetView0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SCROLL_PANE_0_SCROLL_PANE_0),aliasScrollPane0ScrollPane0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_LEFT_0),aliasSplitPane0SplitPaneSetLeft0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_RIGHT_0),aliasSplitPane0SplitPaneSetRight0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_LOCATION_0),aliasSplitPane0SplitPaneSetDividerLocation0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_DIVIDER_SIZE_0),aliasSplitPane0SplitPaneSetDividerSize0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE_0),aliasSplitPane0SplitPaneSetOneTouchExpandable0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_SET_CONTINUOUS_LAYOUT_0),aliasSplitPane0SplitPaneSetContinuousLayout0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_0),aliasSplitPane0SplitPane0),new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_1),aliasSplitPane0SplitPane1),new KeyValueMemberName(_mapping.getVal(SPLIT_PANE_0_SPLIT_PANE_2),aliasSplitPane0SplitPane2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INPUT_0_INPUT_SET_ENABLED_0),aliasInput0InputSetEnabled0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SEPARATOR_0_SEPARATOR_SET_ORIENT_0),aliasSeparator0SeparatorSetOrient0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BUTTON_0_ADD_LISTENER_0),aliasButton0AddListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BUTTON_0_REMOVE_LISTENER_0),aliasButton0RemoveListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHECK_BOX_0_ADD_LISTENER_0),aliasCheckBox0AddListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHECK_BOX_0_REMOVE_LISTENER_0),aliasCheckBox0RemoveListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BUTTON_0_BUTTON_0),aliasButton0Button0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PROG_BAR_0_PROG_BAR_MIN_0),aliasProgBar0ProgBarMin0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PROG_BAR_0_TREE_NODE_GET_USER_OBJECT_0),aliasProgBar0TreeNodeGetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PROG_BAR_0_PROG_BAR_MAX_0),aliasProgBar0ProgBarMax0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(PROG_BAR_0_PROG_BAR_OR_0),aliasProgBar0ProgBarOr0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_LABEL_0_SET_LABEL_TEXT_0),aliasTextLabel0SetLabelText0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_LABEL_0_TEXT_LABEL_0),aliasTextLabel0TextLabel0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_LABEL_0_SET_LABEL_IMAGE_0),aliasImageLabel0SetLabelImage0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_LABEL_0_IMAGE_LABEL_0),aliasImageLabel0ImageLabel0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FONT_0_FONT_STRING_WIDTH_0),aliasFont0FontStringWidth0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FONT_1_FONT_STRING_WIDTH_0),aliasFont1FontStringWidth0),new KeyValueMemberName(_mapping.getVal(FONT_1_FONT_STRING_WIDTH_1),aliasFont1FontStringWidth1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FONT_0_FONT_STRING_HEIGHT_0),aliasFont0FontStringHeight0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FONT_0_FONT_0),aliasFont0Font0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FONT_1_FONT_0),aliasFont1Font0),new KeyValueMemberName(_mapping.getVal(FONT_1_FONT_1),aliasFont1Font1),new KeyValueMemberName(_mapping.getVal(FONT_1_FONT_2),aliasFont1Font2),new KeyValueMemberName(_mapping.getVal(FONT_1_FONT_3),aliasFont1Font3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COLOR_0_COLOR_0),aliasColor0Color0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COLOR_1_COLOR_0),aliasColor1Color0),new KeyValueMemberName(_mapping.getVal(COLOR_1_COLOR_1),aliasColor1Color1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COLOR_2_COLOR_0),aliasColor2Color0),new KeyValueMemberName(_mapping.getVal(COLOR_2_COLOR_1),aliasColor2Color1),new KeyValueMemberName(_mapping.getVal(COLOR_2_COLOR_2),aliasColor2Color2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COLOR_3_COLOR_0),aliasColor3Color0),new KeyValueMemberName(_mapping.getVal(COLOR_3_COLOR_1),aliasColor3Color1),new KeyValueMemberName(_mapping.getVal(COLOR_3_COLOR_2),aliasColor3Color2),new KeyValueMemberName(_mapping.getVal(COLOR_3_COLOR_3),aliasColor3Color3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_TREE_NODE_GET_USER_OBJECT_0),aliasImage0TreeNodeGetUserObject0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_TREE_NODE_GET_USER_OBJECT_1),aliasImage0TreeNodeGetUserObject1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_TREE_NODE_SET_USER_OBJECT_0),aliasImage0TreeNodeSetUserObject0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_TREE_NODE_SET_USER_OBJECT_1),aliasImage0TreeNodeSetUserObject1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_TREE_NODE_SET_USER_OBJECT_2),aliasImage0TreeNodeSetUserObject2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_SET_COLOR_0),aliasImage0ImageSetColor0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_SET_FONT_0),aliasImage0SetFont0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_0),aliasImage0ImageDraw0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_1),aliasImage0ImageDraw1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_2),aliasImage0ImageDraw2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_1_IMAGE_DRAW_0),aliasImage1ImageDraw0),new KeyValueMemberName(_mapping.getVal(IMAGE_1_IMAGE_DRAW_1),aliasImage1ImageDraw1),new KeyValueMemberName(_mapping.getVal(IMAGE_1_IMAGE_DRAW_2),aliasImage1ImageDraw2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_0),aliasImage0ImageDrawLine0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_1),aliasImage0ImageDrawLine1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_2),aliasImage0ImageDrawLine2),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_LINE_3),aliasImage0ImageDrawLine3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_0),aliasImage0ImageDrawRect0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_1),aliasImage0ImageDrawRect1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_2),aliasImage0ImageDrawRect2),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_RECT_3),aliasImage0ImageDrawRect3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_0),aliasImage0ImageDrawOval0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_1),aliasImage0ImageDrawOval1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_2),aliasImage0ImageDrawOval2),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_OVAL_3),aliasImage0ImageDrawOval3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_POLYGON_0),aliasImage0ImageDrawPolygon0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_DRAW_POLYGON_1),aliasImage0ImageDrawPolygon1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_0),aliasImage0ImageFillRect0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_1),aliasImage0ImageFillRect1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_2),aliasImage0ImageFillRect2),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_RECT_3),aliasImage0ImageFillRect3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_0),aliasImage0ImageFillOval0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_1),aliasImage0ImageFillOval1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_2),aliasImage0ImageFillOval2),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_OVAL_3),aliasImage0ImageFillOval3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_POLYGON_0),aliasImage0ImageFillPolygon0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_FILL_POLYGON_1),aliasImage0ImageFillPolygon1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_EQ_0),aliasImage0ImageEq0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_EQ_1),aliasImage0ImageEq1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_0),aliasImage0Image0),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_1),aliasImage0Image1),new KeyValueMemberName(_mapping.getVal(IMAGE_0_IMAGE_2),aliasImage0Image2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RENDER_0_RENDER_SET_HEIGHT_0),aliasRender0RenderSetHeight0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RENDER_0_RENDER_SET_WIDTH_0),aliasRender0RenderSetWidth0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RENDER_0_COMPONENT_SET_PAINT_0),aliasRender0ComponentSetPaint0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_GR_LIST_SET_RENDER_0),aliasGrList0GrListSetRender0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_GR_LIST_ADD_SELECTION_0),aliasGrList0GrListAddSelection0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_GR_LIST_REMOVE_SELECTION_0),aliasGrList0GrListRemoveSelection0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_GR_LIST_SET_VISIBLE_ROW_COUNT_0),aliasGrList0GrListSetVisibleRowCount0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_TABBED_PANE_ADD_0),aliasGrList0TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(GR_LIST_0_TABBED_PANE_ADD_1),aliasGrList0TabbedPaneAdd1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_1_TABBED_PANE_ADD_0),aliasGrList1TabbedPaneAdd0),new KeyValueMemberName(_mapping.getVal(GR_LIST_1_TABBED_PANE_ADD_1),aliasGrList1TabbedPaneAdd1),new KeyValueMemberName(_mapping.getVal(GR_LIST_1_TABBED_PANE_ADD_2),aliasGrList1TabbedPaneAdd2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_2_TABBED_PANE_ADD_0),aliasGrList2TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_0),aliasGrList0TreeNodeSetUserObject0),new KeyValueMemberName(_mapping.getVal(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_1),aliasGrList0TreeNodeSetUserObject1),new KeyValueMemberName(_mapping.getVal(GR_LIST_0_TREE_NODE_SET_USER_OBJECT_2),aliasGrList0TreeNodeSetUserObject2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_0),aliasGrList1TreeNodeSetUserObject0),new KeyValueMemberName(_mapping.getVal(GR_LIST_1_TREE_NODE_SET_USER_OBJECT_1),aliasGrList1TreeNodeSetUserObject1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_GR_LIST_SET_SELECTED_INDEXES_0),aliasGrList0GrListSetSelectedIndexes0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_REMOVE_COMPO_0),aliasGrList0RemoveCompo0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(GR_LIST_0_GR_LIST_0),aliasGrList0GrList0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_0_COMBO_ADD_LISTENER_0),aliasCombo0ComboAddListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_0_COMBO_REMOVE_LISTENER_0),aliasCombo0ComboRemoveListener0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_0_TABBED_PANE_ADD_0),aliasCombo0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_0_COMBO_SELECT_ITEM_0),aliasCombo0ComboSelectItem0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_0_COMBO_REMOVE_ITEM_0),aliasCombo0ComboRemoveItem0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_0_COMBO_0),aliasCombo0Combo0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMBO_1_COMBO_0),aliasCombo1Combo0),new KeyValueMemberName(_mapping.getVal(COMBO_1_COMBO_1),aliasCombo1Combo1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BUTTON_GROUP_0_TABBED_PANE_ADD_0),aliasButtonGroup0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BUTTON_GROUP_0_TABBED_PANE_REMOVE_0),aliasButtonGroup0TabbedPaneRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_TABBED_PANE_ADD_0),aliasPopupMenu0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_GET_COMP_0),aliasPopupMenu0PopupMenuGetComp0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_REMOVE_COMP_0),aliasPopupMenu0PopupMenuRemoveComp0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_ADD_MENU_0),aliasPopupMenu0PopupMenuAddMenu0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_GET_MENU_0),aliasPopupMenu0PopupMenuGetMenu0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_REMOVE_MENU_0),aliasPopupMenu0PopupMenuRemoveMenu0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_SHOW_0),aliasPopupMenu0PopupMenuShow0),new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_SHOW_1),aliasPopupMenu0PopupMenuShow1),new KeyValueMemberName(_mapping.getVal(POPUP_MENU_0_POPUP_MENU_SHOW_2),aliasPopupMenu0PopupMenuShow2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RADIO_0_RADIO_SET_SELECTED_0),aliasRadio0RadioSetSelected0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RADIO_0_SET_LABEL_TEXT_0),aliasRadio0SetLabelText0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RADIO_0_RADIO_0),aliasRadio0Radio0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(RADIO_1_RADIO_0),aliasRadio1Radio0),new KeyValueMemberName(_mapping.getVal(RADIO_1_RADIO_1),aliasRadio1Radio1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHECK_BOX_0_RADIO_SET_SELECTED_0),aliasCheckBox0RadioSetSelected0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHECK_BOX_0_SET_LABEL_TEXT_0),aliasCheckBox0SetLabelText0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHECK_BOX_0_CHECK_BOX_0),aliasCheckBox0CheckBox0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHECK_BOX_1_CHECK_BOX_0),aliasCheckBox1CheckBox0),new KeyValueMemberName(_mapping.getVal(CHECK_BOX_1_CHECK_BOX_1),aliasCheckBox1CheckBox1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_0_SET_LABEL_TEXT_0),aliasTextField0SetLabelText0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_0_TEXT_FIELD_ADD_ACTION_0),aliasTextField0TextFieldAddAction0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_0_TEXT_FIELD_REMOVE_ACTION_0),aliasTextField0TextFieldRemoveAction0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_0_TEXT_FIELD_0),aliasTextField0TextField0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_1_TEXT_FIELD_0),aliasTextField1TextField0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_2_TEXT_FIELD_0),aliasTextField2TextField0),new KeyValueMemberName(_mapping.getVal(TEXT_FIELD_2_TEXT_FIELD_1),aliasTextField2TextField1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_SET_LABEL_TEXT_0),aliasTextArea0SetLabelText0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TABBED_PANE_ADD_0),aliasTextArea0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TREE_NODE_INSERT_0),aliasTextArea0TreeNodeInsert0),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TREE_NODE_INSERT_1),aliasTextArea0TreeNodeInsert1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_START_0),aliasTextArea0TextAreaSetSelectionStart0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_SET_SELECTION_END_0),aliasTextArea0TextAreaSetSelectionEnd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_SET_TAB_SIZE_0),aliasTextArea0TextAreaSetTabSize0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_0),aliasTextArea0TextAreaReplaceRange0),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_1),aliasTextArea0TextAreaReplaceRange1),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_RANGE_2),aliasTextArea0TextAreaReplaceRange2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_REPLACE_SELECTION_0),aliasTextArea0TextAreaReplaceSelection0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TREE_GET_SELECTED_0),aliasTextArea0TreeGetSelected0),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TREE_GET_SELECTED_1),aliasTextArea0TreeGetSelected1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_0_TEXT_AREA_0),aliasTextArea0TextArea0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_1_TEXT_AREA_0),aliasTextArea1TextArea0),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_1_TEXT_AREA_1),aliasTextArea1TextArea1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(TEXT_AREA_2_TEXT_AREA_0),aliasTextArea2TextArea0),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_2_TEXT_AREA_1),aliasTextArea2TextArea1),new KeyValueMemberName(_mapping.getVal(TEXT_AREA_2_TEXT_AREA_2),aliasTextArea2TextArea2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_MAX_0),aliasSpinner0SpinnerSetMax0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_MIN_0),aliasSpinner0SpinnerSetMin0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_TREE_NODE_SET_USER_OBJECT_0),aliasSpinner0TreeNodeSetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_STEP_0),aliasSpinner0SpinnerSetStep0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_0),aliasSpinner0SpinnerSetRange0),new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_1),aliasSpinner0SpinnerSetRange1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_VALUE_0),aliasSpinner0SpinnerSetRangeValue0),new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_VALUE_1),aliasSpinner0SpinnerSetRangeValue1),new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_SET_RANGE_VALUE_2),aliasSpinner0SpinnerSetRangeValue2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_ADD_CHANGE_0),aliasSpinner0AddChange0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_0),aliasSpinner0Spinner0),new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_1),aliasSpinner0Spinner1),new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_2),aliasSpinner0Spinner2),new KeyValueMemberName(_mapping.getVal(SPINNER_0_SPINNER_3),aliasSpinner0Spinner3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_0_SPINNER_SET_MAX_0),aliasSlider0SpinnerSetMax0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_0_SPINNER_SET_MIN_0),aliasSlider0SpinnerSetMin0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_0_TREE_NODE_SET_USER_OBJECT_0),aliasSlider0TreeNodeSetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_0_SLIDER_SET_ORIENTATION_0),aliasSlider0SliderSetOrientation0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_0_ADD_CHANGE_0),aliasSlider0AddChange0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_0_SLIDER_0),aliasSlider0Slider0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_1_SLIDER_0),aliasSlider1Slider0),new KeyValueMemberName(_mapping.getVal(SLIDER_1_SLIDER_1),aliasSlider1Slider1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_2_SLIDER_0),aliasSlider2Slider0),new KeyValueMemberName(_mapping.getVal(SLIDER_2_SLIDER_1),aliasSlider2Slider1),new KeyValueMemberName(_mapping.getVal(SLIDER_2_SLIDER_2),aliasSlider2Slider2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SLIDER_3_SLIDER_0),aliasSlider3Slider0),new KeyValueMemberName(_mapping.getVal(SLIDER_3_SLIDER_1),aliasSlider3Slider1),new KeyValueMemberName(_mapping.getVal(SLIDER_3_SLIDER_2),aliasSlider3Slider2),new KeyValueMemberName(_mapping.getVal(SLIDER_3_SLIDER_3),aliasSlider3Slider3)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_BAR_0_TABBED_PANE_ADD_0),aliasMenuBar0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_BAR_0_TREE_NODE_REMOVE_0),aliasMenuBar0TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_BAR_0_TREE_NODE_GET_USER_OBJECT_0),aliasMenuBar0TreeNodeGetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ABS_MENU_0_ABS_MENU_SET_TEXT_0),aliasAbsMenu0AbsMenuSetText0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ABS_MENU_0_INPUT_SET_ENABLED_0),aliasAbsMenu0InputSetEnabled0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ABS_MENU_0_ABS_MENU_SET_DEEP_ENABLED_0),aliasAbsMenu0AbsMenuSetDeepEnabled0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_0_TABBED_PANE_ADD_0),aliasMenu0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_0_TREE_NODE_REMOVE_0),aliasMenu0TreeNodeRemove0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_0_TREE_NODE_GET_USER_OBJECT_0),aliasMenu0TreeNodeGetUserObject0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_0_MENU_0),aliasMenu0Menu0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ABS_MENU_ITEM_0_TABBED_PANE_ADD_0),aliasAbsMenuItem0TabbedPaneAdd0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_ITEM_0_MENU_ITEM_0),aliasMenuItem0MenuItem0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_ITEM_CHECK_0_RADIO_SET_SELECTED_0),aliasMenuItemCheck0RadioSetSelected0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(MENU_ITEM_CHECK_0_MENU_ITEM_CHECK_0),aliasMenuItemCheck0MenuItemCheck0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMMAND_0_BINDING_0),aliasCommand0Binding0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMMAND_0_ACTION_0),aliasCommand0Action0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_BIND_0),aliasComponent0Bind0),new KeyValueMemberName(_mapping.getVal(COMPONENT_0_BIND_1),aliasComponent0Bind1),new KeyValueMemberName(_mapping.getVal(COMPONENT_0_BIND_2),aliasComponent0Bind2)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(COMPONENT_0_UNBIND_0),aliasComponent0Unbind0),new KeyValueMemberName(_mapping.getVal(COMPONENT_0_UNBIND_1),aliasComponent0Unbind1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_0),aliasActionListenerImplicit0Implicit0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ACTION_LISTENER_IMPLICIT_0_IMPLICIT_1),aliasActionListenerImplicit0Implicit1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CELL_RENDER_IMPLICIT_0_IMPLICIT_0),aliasCellRenderImplicit0Implicit0)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CELL_RENDER_IMPLICIT_0_IMPLICIT_1),aliasCellRenderImplicit0Implicit1)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_0),aliasCellRender0Generate0),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_1),aliasCellRender0Generate1),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_2),aliasCellRender0Generate2),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_3),aliasCellRender0Generate3),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_4),aliasCellRender0Generate4),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_5),aliasCellRender0Generate5),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_6),aliasCellRender0Generate6)));
        m_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_0_0),aliasCellRender0Generate00),new KeyValueMemberName(_mapping.getVal(CELL_RENDER_0_GENERATE_0_1),aliasCellRender0Generate01)));
        return m_;
    }

    public String getAliasActionListener0ActionPerformed0() {
        return aliasActionListener0ActionPerformed0;
    }

    public String getAliasAction0Wrap0() {
        return aliasAction0Wrap0;
    }

    public String getAliasAction0Enabled0() {
        return aliasAction0Enabled0;
    }

    public String getAliasTreeListener0TreeListenerValueChanged0() {
        return aliasTreeListener0TreeListenerValueChanged0;
    }

    public String getAliasTableListener0TableValueTableChanged0() {
        return aliasTableListener0TableValueTableChanged0;
    }

    public String getAliasTableListener0TableValueTableChanged1() {
        return aliasTableListener0TableValueTableChanged1;
    }

    public String getAliasMouseListener0MouseClicked0() {
        return aliasMouseListener0MouseClicked0;
    }

    public String getAliasMouseListener0MousePressed0() {
        return aliasMouseListener0MousePressed0;
    }

    public String getAliasMouseListener0MouseReleased0() {
        return aliasMouseListener0MouseReleased0;
    }

    public String getAliasMouseListener0MouseEntered0() {
        return aliasMouseListener0MouseEntered0;
    }

    public String getAliasMouseListener0MouseExited0() {
        return aliasMouseListener0MouseExited0;
    }

    public String getAliasMouseListener0MouseDragged0() {
        return aliasMouseListener0MouseDragged0;
    }

    public String getAliasMouseListener0MouseMoved0() {
        return aliasMouseListener0MouseMoved0;
    }

    public String getAliasWheelListener0WheelMove0() {
        return aliasWheelListener0WheelMove0;
    }

    public String getAliasKeyListener0KeyPressed0() {
        return aliasKeyListener0KeyPressed0;
    }

    public String getAliasKeyListener0KeyTyped0() {
        return aliasKeyListener0KeyTyped0;
    }

    public String getAliasKeyListener0KeyReleased0() {
        return aliasKeyListener0KeyReleased0;
    }

    public String getAliasWindowListener0WindowOpened0() {
        return aliasWindowListener0WindowOpened0;
    }

    public String getAliasWindowListener0WindowClosing0() {
        return aliasWindowListener0WindowClosing0;
    }

    public String getAliasWindowListener0WindowClosed0() {
        return aliasWindowListener0WindowClosed0;
    }

    public String getAliasWindowListener0WindowIconified0() {
        return aliasWindowListener0WindowIconified0;
    }

    public String getAliasWindowListener0WindowDeiconified0() {
        return aliasWindowListener0WindowDeiconified0;
    }

    public String getAliasWindowListener0WindowActivated0() {
        return aliasWindowListener0WindowActivated0;
    }

    public String getAliasWindowListener0WindowDeactivated0() {
        return aliasWindowListener0WindowDeactivated0;
    }

    public String getAliasListSelection0ValueChanged0() {
        return aliasListSelection0ValueChanged0;
    }

    public String getAliasListSelection0ValueChanged1() {
        return aliasListSelection0ValueChanged1;
    }

    public String getAliasListSelection0ValueChanged2() {
        return aliasListSelection0ValueChanged2;
    }

    public String getAliasPaint0PaintMethod0() {
        return aliasPaint0PaintMethod0;
    }

    public String getAliasWindowType0SetContent0() {
        return aliasWindowType0SetContent0;
    }

    public String getAliasWindowType0WindowTypeRelative0() {
        return aliasWindowType0WindowTypeRelative0;
    }

    public String getAliasWindowType0ComponentSetVisible0() {
        return aliasWindowType0ComponentSetVisible0;
    }

    public String getAliasWindowType0SetMenuBar0() {
        return aliasWindowType0SetMenuBar0;
    }

    public String getAliasWindowType0AddWindowListener0() {
        return aliasWindowType0AddWindowListener0;
    }

    public String getAliasWindowType0RemoveWindowListener0() {
        return aliasWindowType0RemoveWindowListener0;
    }

    public String getAliasConfirm0ConfirmField0() {
        return aliasConfirm0ConfirmField0;
    }

    public String getAliasConfirm0ConfirmField1() {
        return aliasConfirm0ConfirmField1;
    }

    public String getAliasConfirm0ConfirmField2() {
        return aliasConfirm0ConfirmField2;
    }

    public String getAliasConfirm0ConfirmField3() {
        return aliasConfirm0ConfirmField3;
    }

    public String getAliasConfirm0ConfirmField4() {
        return aliasConfirm0ConfirmField4;
    }

    public String getAliasConfirm0ConfirmField5() {
        return aliasConfirm0ConfirmField5;
    }

    public String getAliasConfirm0ConfirmField6() {
        return aliasConfirm0ConfirmField6;
    }

    public String getAliasConfirm1ConfirmField0() {
        return aliasConfirm1ConfirmField0;
    }

    public String getAliasConfirm1ConfirmField1() {
        return aliasConfirm1ConfirmField1;
    }

    public String getAliasConfirm1ConfirmField2() {
        return aliasConfirm1ConfirmField2;
    }

    public String getAliasConfirm1ConfirmField3() {
        return aliasConfirm1ConfirmField3;
    }

    public String getAliasConfirm1ConfirmField4() {
        return aliasConfirm1ConfirmField4;
    }

    public String getAliasConfirm1ConfirmField5() {
        return aliasConfirm1ConfirmField5;
    }

    public String getAliasConfirm0ConfirmFull0() {
        return aliasConfirm0ConfirmFull0;
    }

    public String getAliasConfirm0ConfirmFull1() {
        return aliasConfirm0ConfirmFull1;
    }

    public String getAliasConfirm0ConfirmFull2() {
        return aliasConfirm0ConfirmFull2;
    }

    public String getAliasConfirm0ConfirmFull3() {
        return aliasConfirm0ConfirmFull3;
    }

    public String getAliasConfirm0ConfirmFull4() {
        return aliasConfirm0ConfirmFull4;
    }

    public String getAliasConfirm0ConfirmFull5() {
        return aliasConfirm0ConfirmFull5;
    }

    public String getAliasConfirm0ConfirmFull6() {
        return aliasConfirm0ConfirmFull6;
    }

    public String getAliasConfirm1ConfirmFull0() {
        return aliasConfirm1ConfirmFull0;
    }

    public String getAliasConfirm1ConfirmFull1() {
        return aliasConfirm1ConfirmFull1;
    }

    public String getAliasConfirm1ConfirmFull2() {
        return aliasConfirm1ConfirmFull2;
    }

    public String getAliasConfirm1ConfirmFull3() {
        return aliasConfirm1ConfirmFull3;
    }

    public String getAliasConfirm1ConfirmFull4() {
        return aliasConfirm1ConfirmFull4;
    }

    public String getAliasConfirm1ConfirmFull5() {
        return aliasConfirm1ConfirmFull5;
    }

    public String getAliasConfirm0ConfirmYesNo0() {
        return aliasConfirm0ConfirmYesNo0;
    }

    public String getAliasConfirm0ConfirmYesNo1() {
        return aliasConfirm0ConfirmYesNo1;
    }

    public String getAliasConfirm0ConfirmYesNo2() {
        return aliasConfirm0ConfirmYesNo2;
    }

    public String getAliasConfirm0ConfirmYesNo3() {
        return aliasConfirm0ConfirmYesNo3;
    }

    public String getAliasConfirm0ConfirmYesNo4() {
        return aliasConfirm0ConfirmYesNo4;
    }

    public String getAliasConfirm0ConfirmYesNo5() {
        return aliasConfirm0ConfirmYesNo5;
    }

    public String getAliasConfirm1ConfirmYesNo0() {
        return aliasConfirm1ConfirmYesNo0;
    }

    public String getAliasConfirm1ConfirmYesNo1() {
        return aliasConfirm1ConfirmYesNo1;
    }

    public String getAliasConfirm1ConfirmYesNo2() {
        return aliasConfirm1ConfirmYesNo2;
    }

    public String getAliasConfirm1ConfirmYesNo3() {
        return aliasConfirm1ConfirmYesNo3;
    }

    public String getAliasConfirm1ConfirmYesNo4() {
        return aliasConfirm1ConfirmYesNo4;
    }

    public String getAliasConfirm0ConfirmOk0() {
        return aliasConfirm0ConfirmOk0;
    }

    public String getAliasConfirm0ConfirmOk1() {
        return aliasConfirm0ConfirmOk1;
    }

    public String getAliasConfirm0ConfirmOk2() {
        return aliasConfirm0ConfirmOk2;
    }

    public String getAliasConfirm0ConfirmOk3() {
        return aliasConfirm0ConfirmOk3;
    }

    public String getAliasConfirm0ConfirmOk4() {
        return aliasConfirm0ConfirmOk4;
    }

    public String getAliasConfirm1ConfirmOk0() {
        return aliasConfirm1ConfirmOk0;
    }

    public String getAliasConfirm1ConfirmOk1() {
        return aliasConfirm1ConfirmOk1;
    }

    public String getAliasConfirm1ConfirmOk2() {
        return aliasConfirm1ConfirmOk2;
    }

    public String getAliasConfirm1ConfirmOk3() {
        return aliasConfirm1ConfirmOk3;
    }

    public String getAliasConfirm0ConfirmMessage0() {
        return aliasConfirm0ConfirmMessage0;
    }

    public String getAliasConfirm0ConfirmMessage1() {
        return aliasConfirm0ConfirmMessage1;
    }

    public String getAliasConfirm0ConfirmMessage2() {
        return aliasConfirm0ConfirmMessage2;
    }

    public String getAliasConfirm0ConfirmMessage3() {
        return aliasConfirm0ConfirmMessage3;
    }

    public String getAliasConfirm0ConfirmMessage4() {
        return aliasConfirm0ConfirmMessage4;
    }

    public String getAliasConfirm1ConfirmMessage0() {
        return aliasConfirm1ConfirmMessage0;
    }

    public String getAliasConfirm1ConfirmMessage1() {
        return aliasConfirm1ConfirmMessage1;
    }

    public String getAliasConfirm1ConfirmMessage2() {
        return aliasConfirm1ConfirmMessage2;
    }

    public String getAliasConfirm1ConfirmMessage3() {
        return aliasConfirm1ConfirmMessage3;
    }

    public String getAliasWindowSet0TabbedPaneAdd0() {
        return aliasWindowSet0TabbedPaneAdd0;
    }

    public String getAliasWindowSet0WindowSetContains0() {
        return aliasWindowSet0WindowSetContains0;
    }

    public String getAliasWindowSet0TreeNodeRemove0() {
        return aliasWindowSet0TreeNodeRemove0;
    }

    public String getAliasDialog0DialogSetModal0() {
        return aliasDialog0DialogSetModal0;
    }

    public String getAliasComponent0SetFont0() {
        return aliasComponent0SetFont0;
    }

    public String getAliasComponent0CompBack0() {
        return aliasComponent0CompBack0;
    }

    public String getAliasComponent0CompFore0() {
        return aliasComponent0CompFore0;
    }

    public String getAliasComponent0CompFocusable0() {
        return aliasComponent0CompFocusable0;
    }

    public String getAliasComponent0CompOpaque0() {
        return aliasComponent0CompOpaque0;
    }

    public String getAliasComponent0CompToolTip0() {
        return aliasComponent0CompToolTip0;
    }

    public String getAliasComponent0CompLoc0() {
        return aliasComponent0CompLoc0;
    }

    public String getAliasComponent0CompLoc1() {
        return aliasComponent0CompLoc1;
    }

    public String getAliasComponent0CompBorLine0() {
        return aliasComponent0CompBorLine0;
    }

    public String getAliasComponent1CompBorLine0() {
        return aliasComponent1CompBorLine0;
    }

    public String getAliasComponent1CompBorLine1() {
        return aliasComponent1CompBorLine1;
    }

    public String getAliasComponent0CompBorTitle0() {
        return aliasComponent0CompBorTitle0;
    }

    public String getAliasComponent0ComponentSetPaint0() {
        return aliasComponent0ComponentSetPaint0;
    }

    public String getAliasComponent0ComponentSetAutoscrolls0() {
        return aliasComponent0ComponentSetAutoscrolls0;
    }

    public String getAliasComponent0ComponentSetPreferredSize0() {
        return aliasComponent0ComponentSetPreferredSize0;
    }

    public String getAliasComponent0ComponentSetVisible0() {
        return aliasComponent0ComponentSetVisible0;
    }

    public String getAliasComponent0ComponentInvokeLater0() {
        return aliasComponent0ComponentInvokeLater0;
    }

    public String getAliasComponent0AddKeyListener0() {
        return aliasComponent0AddKeyListener0;
    }

    public String getAliasComponent0AddFocusListener0() {
        return aliasComponent0AddFocusListener0;
    }

    public String getAliasComponent0AddWheelListener0() {
        return aliasComponent0AddWheelListener0;
    }

    public String getAliasComponent0AddListener0() {
        return aliasComponent0AddListener0;
    }

    public String getAliasComponent0RemoveKeyListener0() {
        return aliasComponent0RemoveKeyListener0;
    }

    public String getAliasComponent0RemoveFocusListener0() {
        return aliasComponent0RemoveFocusListener0;
    }

    public String getAliasComponent0RemoveWheelListener0() {
        return aliasComponent0RemoveWheelListener0;
    }

    public String getAliasComponent0RemoveListener0() {
        return aliasComponent0RemoveListener0;
    }

    public String getAliasDimension0Dimension0() {
        return aliasDimension0Dimension0;
    }

    public String getAliasDimension0Dimension1() {
        return aliasDimension0Dimension1;
    }

    public String getAliasDimension1Dimension0() {
        return aliasDimension1Dimension0;
    }

    public String getAliasTreeNode0TreeNodeEq0() {
        return aliasTreeNode0TreeNodeEq0;
    }

    public String getAliasTreeNode0TreeNodeEq1() {
        return aliasTreeNode0TreeNodeEq1;
    }

    public String getAliasTreeNode0TreeNodeAdd0() {
        return aliasTreeNode0TreeNodeAdd0;
    }

    public String getAliasTreeNode0TreeNodeInsert0() {
        return aliasTreeNode0TreeNodeInsert0;
    }

    public String getAliasTreeNode0TreeNodeInsert1() {
        return aliasTreeNode0TreeNodeInsert1;
    }

    public String getAliasTreeNode0TreeNodeRemove0() {
        return aliasTreeNode0TreeNodeRemove0;
    }

    public String getAliasTreeNode1TreeNodeRemove0() {
        return aliasTreeNode1TreeNodeRemove0;
    }

    public String getAliasTreeNode0TreeNodeSetUserObject0() {
        return aliasTreeNode0TreeNodeSetUserObject0;
    }

    public String getAliasTreeNode0TreeNodeIsAncestor0() {
        return aliasTreeNode0TreeNodeIsAncestor0;
    }

    public String getAliasTreeNode0TreeNodeIsDescendant0() {
        return aliasTreeNode0TreeNodeIsDescendant0;
    }

    public String getAliasTreeNode0TreeNode0() {
        return aliasTreeNode0TreeNode0;
    }

    public String getAliasTree0TreeAddTreeListener0() {
        return aliasTree0TreeAddTreeListener0;
    }

    public String getAliasTree0TreeRemoveTreeListener0() {
        return aliasTree0TreeRemoveTreeListener0;
    }

    public String getAliasTree0TreeSetRootVisible0() {
        return aliasTree0TreeSetRootVisible0;
    }

    public String getAliasTree0TreeGetSelected0() {
        return aliasTree0TreeGetSelected0;
    }

    public String getAliasTree0Tree0() {
        return aliasTree0Tree0;
    }

    public String getAliasTableGui0TableAddHeader0() {
        return aliasTableGui0TableAddHeader0;
    }

    public String getAliasTableGui0TableAddSelect0() {
        return aliasTableGui0TableAddSelect0;
    }

    public String getAliasTableGui0TableSetMultiple0() {
        return aliasTableGui0TableSetMultiple0;
    }

    public String getAliasTableGui0TableSetReorder0() {
        return aliasTableGui0TableSetReorder0;
    }

    public String getAliasTableGui0TableGetColumnName0() {
        return aliasTableGui0TableGetColumnName0;
    }

    public String getAliasTableGui0TableGetColumnAtPoint0() {
        return aliasTableGui0TableGetColumnAtPoint0;
    }

    public String getAliasTableGui0TableGetColumnAtPoint1() {
        return aliasTableGui0TableGetColumnAtPoint1;
    }

    public String getAliasTableGui0TableGetRowAtPoint0() {
        return aliasTableGui0TableGetRowAtPoint0;
    }

    public String getAliasTableGui0TableGetRowAtPoint1() {
        return aliasTableGui0TableGetRowAtPoint1;
    }

    public String getAliasTableGui0TableSetRowCount0() {
        return aliasTableGui0TableSetRowCount0;
    }

    public String getAliasTableGui0TableSetColumns0() {
        return aliasTableGui0TableSetColumns0;
    }

    public String getAliasTableGui0TableMoveColumn0() {
        return aliasTableGui0TableMoveColumn0;
    }

    public String getAliasTableGui0TableMoveColumn1() {
        return aliasTableGui0TableMoveColumn1;
    }

    public String getAliasTableGui0TableAddInterval0() {
        return aliasTableGui0TableAddInterval0;
    }

    public String getAliasTableGui0TableAddInterval1() {
        return aliasTableGui0TableAddInterval1;
    }

    public String getAliasTableGui0TableRemoveInterval0() {
        return aliasTableGui0TableRemoveInterval0;
    }

    public String getAliasTableGui0TableRemoveInterval1() {
        return aliasTableGui0TableRemoveInterval1;
    }

    public String getAliasTableGui0TreeNodeSetUserObject0() {
        return aliasTableGui0TreeNodeSetUserObject0;
    }

    public String getAliasTableGui0TreeNodeSetUserObject1() {
        return aliasTableGui0TreeNodeSetUserObject1;
    }

    public String getAliasTableGui0TreeNodeSetUserObject2() {
        return aliasTableGui0TreeNodeSetUserObject2;
    }

    public String getAliasTableGui0TreeNodeGetUserObject0() {
        return aliasTableGui0TreeNodeGetUserObject0;
    }

    public String getAliasTableGui0TreeNodeGetUserObject1() {
        return aliasTableGui0TreeNodeGetUserObject1;
    }

    public String getAliasTableGui0TableGui0() {
        return aliasTableGui0TableGui0;
    }

    public String getAliasActionEvent0ActionEvent0() {
        return aliasActionEvent0ActionEvent0;
    }

    public String getAliasActionEvent0ActionEvent1() {
        return aliasActionEvent0ActionEvent1;
    }

    public String getAliasActionEvent0ActionEvent2() {
        return aliasActionEvent0ActionEvent2;
    }

    public String getAliasActionEvent0ActionEvent3() {
        return aliasActionEvent0ActionEvent3;
    }

    public String getAliasMouseEvent0MouseEvent0() {
        return aliasMouseEvent0MouseEvent0;
    }

    public String getAliasMouseEvent0MouseEvent1() {
        return aliasMouseEvent0MouseEvent1;
    }

    public String getAliasMouseEvent0MouseEvent2() {
        return aliasMouseEvent0MouseEvent2;
    }

    public String getAliasMouseEvent0MouseEvent3() {
        return aliasMouseEvent0MouseEvent3;
    }

    public String getAliasMouseEvent0MouseEvent4() {
        return aliasMouseEvent0MouseEvent4;
    }

    public String getAliasMouseEvent0MouseEvent5() {
        return aliasMouseEvent0MouseEvent5;
    }

    public String getAliasMouseEvent0MouseEvent6() {
        return aliasMouseEvent0MouseEvent6;
    }

    public String getAliasMouseEvent0MouseEvent7() {
        return aliasMouseEvent0MouseEvent7;
    }

    public String getAliasMouseEvent0MouseEvent8() {
        return aliasMouseEvent0MouseEvent8;
    }

    public String getAliasWheelEvent0WheelEvent0() {
        return aliasWheelEvent0WheelEvent0;
    }

    public String getAliasWheelEvent0WheelEvent1() {
        return aliasWheelEvent0WheelEvent1;
    }

    public String getAliasWheelEvent0WheelEvent2() {
        return aliasWheelEvent0WheelEvent2;
    }

    public String getAliasWheelEvent0WheelEvent3() {
        return aliasWheelEvent0WheelEvent3;
    }

    public String getAliasWheelEvent0WheelEvent4() {
        return aliasWheelEvent0WheelEvent4;
    }

    public String getAliasWheelEvent0WheelEvent5() {
        return aliasWheelEvent0WheelEvent5;
    }

    public String getAliasWheelEvent0WheelEvent6() {
        return aliasWheelEvent0WheelEvent6;
    }

    public String getAliasWheelEvent0WheelEvent7() {
        return aliasWheelEvent0WheelEvent7;
    }

    public String getAliasWheelEvent0WheelEvent8() {
        return aliasWheelEvent0WheelEvent8;
    }

    public String getAliasWheelEvent0WheelEvent9() {
        return aliasWheelEvent0WheelEvent9;
    }

    public String getAliasKeyEvent0KeyEvent0() {
        return aliasKeyEvent0KeyEvent0;
    }

    public String getAliasKeyEvent0KeyEvent1() {
        return aliasKeyEvent0KeyEvent1;
    }

    public String getAliasKeyEvent0KeyEvent2() {
        return aliasKeyEvent0KeyEvent2;
    }

    public String getAliasKeyEvent0KeyEvent3() {
        return aliasKeyEvent0KeyEvent3;
    }

    public String getAliasKeyEvent0KeyEvent4() {
        return aliasKeyEvent0KeyEvent4;
    }

    public String getAliasPanel0TabbedPaneAdd0() {
        return aliasPanel0TabbedPaneAdd0;
    }

    public String getAliasPanel1TabbedPaneAdd0() {
        return aliasPanel1TabbedPaneAdd0;
    }

    public String getAliasPanel1TabbedPaneAdd1() {
        return aliasPanel1TabbedPaneAdd1;
    }

    public String getAliasPanel0RemoveCompo0() {
        return aliasPanel0RemoveCompo0;
    }

    public String getAliasPanel1RemoveCompo0() {
        return aliasPanel1RemoveCompo0;
    }

    public String getAliasPanel0TreeNodeGetUserObject0() {
        return aliasPanel0TreeNodeGetUserObject0;
    }

    public String getAliasPanel0PanelGrid0() {
        return aliasPanel0PanelGrid0;
    }

    public String getAliasPanel0PanelGrid1() {
        return aliasPanel0PanelGrid1;
    }

    public String getAliasPanelBorder0TabbedPaneAdd0() {
        return aliasPanelBorder0TabbedPaneAdd0;
    }

    public String getAliasPanelBorder0TabbedPaneAdd1() {
        return aliasPanelBorder0TabbedPaneAdd1;
    }

    public String getAliasTabbedPane0TabbedPaneAdd0() {
        return aliasTabbedPane0TabbedPaneAdd0;
    }

    public String getAliasTabbedPane0TabbedPaneAdd1() {
        return aliasTabbedPane0TabbedPaneAdd1;
    }

    public String getAliasTabbedPane0TabbedPaneSelIndex0() {
        return aliasTabbedPane0TabbedPaneSelIndex0;
    }

    public String getAliasTabbedPane0TabbedPaneIndex0() {
        return aliasTabbedPane0TabbedPaneIndex0;
    }

    public String getAliasTabbedPane0TreeNodeRemove0() {
        return aliasTabbedPane0TreeNodeRemove0;
    }

    public String getAliasTabbedPane1TreeNodeRemove0() {
        return aliasTabbedPane1TreeNodeRemove0;
    }

    public String getAliasTabbedPane0TreeNodeSetUserObject0() {
        return aliasTabbedPane0TreeNodeSetUserObject0;
    }

    public String getAliasTabbedPane0TreeNodeSetUserObject1() {
        return aliasTabbedPane0TreeNodeSetUserObject1;
    }

    public String getAliasTabbedPane0TabbedPaneSetTitle0() {
        return aliasTabbedPane0TabbedPaneSetTitle0;
    }

    public String getAliasTabbedPane0TabbedPaneSetTitle1() {
        return aliasTabbedPane0TabbedPaneSetTitle1;
    }

    public String getAliasTabbedPane0TreeNodeGetUserObject0() {
        return aliasTabbedPane0TreeNodeGetUserObject0;
    }

    public String getAliasTabbedPane0TabbedPaneGetTitle0() {
        return aliasTabbedPane0TabbedPaneGetTitle0;
    }

    public String getAliasScrollPane0ScrollPaneHorizontalValue0() {
        return aliasScrollPane0ScrollPaneHorizontalValue0;
    }

    public String getAliasScrollPane0ScrollPaneVerticalValue0() {
        return aliasScrollPane0ScrollPaneVerticalValue0;
    }

    public String getAliasScrollPane0ScrollPaneSetView0() {
        return aliasScrollPane0ScrollPaneSetView0;
    }

    public String getAliasScrollPane0ScrollPane0() {
        return aliasScrollPane0ScrollPane0;
    }

    public String getAliasSplitPane0SplitPaneSetLeft0() {
        return aliasSplitPane0SplitPaneSetLeft0;
    }

    public String getAliasSplitPane0SplitPaneSetRight0() {
        return aliasSplitPane0SplitPaneSetRight0;
    }

    public String getAliasSplitPane0SplitPaneSetDividerLocation0() {
        return aliasSplitPane0SplitPaneSetDividerLocation0;
    }

    public String getAliasSplitPane0SplitPaneSetDividerSize0() {
        return aliasSplitPane0SplitPaneSetDividerSize0;
    }

    public String getAliasSplitPane0SplitPaneSetOneTouchExpandable0() {
        return aliasSplitPane0SplitPaneSetOneTouchExpandable0;
    }

    public String getAliasSplitPane0SplitPaneSetContinuousLayout0() {
        return aliasSplitPane0SplitPaneSetContinuousLayout0;
    }

    public String getAliasSplitPane0SplitPane0() {
        return aliasSplitPane0SplitPane0;
    }

    public String getAliasSplitPane0SplitPane1() {
        return aliasSplitPane0SplitPane1;
    }

    public String getAliasSplitPane0SplitPane2() {
        return aliasSplitPane0SplitPane2;
    }

    public String getAliasInput0InputSetEnabled0() {
        return aliasInput0InputSetEnabled0;
    }

    public String getAliasSeparator0SeparatorSetOrient0() {
        return aliasSeparator0SeparatorSetOrient0;
    }

    public String getAliasButton0AddListener0() {
        return aliasButton0AddListener0;
    }

    public String getAliasButton0RemoveListener0() {
        return aliasButton0RemoveListener0;
    }

    public String getAliasCheckBox0AddListener0() {
        return aliasCheckBox0AddListener0;
    }

    public String getAliasCheckBox0RemoveListener0() {
        return aliasCheckBox0RemoveListener0;
    }

    public String getAliasButton0Button0() {
        return aliasButton0Button0;
    }

    public String getAliasProgBar0ProgBarMin0() {
        return aliasProgBar0ProgBarMin0;
    }

    public String getAliasProgBar0TreeNodeGetUserObject0() {
        return aliasProgBar0TreeNodeGetUserObject0;
    }

    public String getAliasProgBar0ProgBarMax0() {
        return aliasProgBar0ProgBarMax0;
    }

    public String getAliasProgBar0ProgBarOr0() {
        return aliasProgBar0ProgBarOr0;
    }

    public String getAliasTextLabel0SetLabelText0() {
        return aliasTextLabel0SetLabelText0;
    }

    public String getAliasTextLabel0TextLabel0() {
        return aliasTextLabel0TextLabel0;
    }

    public String getAliasImageLabel0SetLabelImage0() {
        return aliasImageLabel0SetLabelImage0;
    }

    public String getAliasImageLabel0ImageLabel0() {
        return aliasImageLabel0ImageLabel0;
    }

    public String getAliasFont0FontStringWidth0() {
        return aliasFont0FontStringWidth0;
    }

    public String getAliasFont1FontStringWidth0() {
        return aliasFont1FontStringWidth0;
    }

    public String getAliasFont1FontStringWidth1() {
        return aliasFont1FontStringWidth1;
    }

    public String getAliasFont0FontStringHeight0() {
        return aliasFont0FontStringHeight0;
    }

    public String getAliasFont0Font0() {
        return aliasFont0Font0;
    }

    public String getAliasFont1Font0() {
        return aliasFont1Font0;
    }

    public String getAliasFont1Font1() {
        return aliasFont1Font1;
    }

    public String getAliasFont1Font2() {
        return aliasFont1Font2;
    }

    public String getAliasFont1Font3() {
        return aliasFont1Font3;
    }

    public String getAliasColor0Color0() {
        return aliasColor0Color0;
    }

    public String getAliasColor1Color0() {
        return aliasColor1Color0;
    }

    public String getAliasColor1Color1() {
        return aliasColor1Color1;
    }

    public String getAliasColor2Color0() {
        return aliasColor2Color0;
    }

    public String getAliasColor2Color1() {
        return aliasColor2Color1;
    }

    public String getAliasColor2Color2() {
        return aliasColor2Color2;
    }

    public String getAliasColor3Color0() {
        return aliasColor3Color0;
    }

    public String getAliasColor3Color1() {
        return aliasColor3Color1;
    }

    public String getAliasColor3Color2() {
        return aliasColor3Color2;
    }

    public String getAliasColor3Color3() {
        return aliasColor3Color3;
    }

    public String getAliasImage0TreeNodeGetUserObject0() {
        return aliasImage0TreeNodeGetUserObject0;
    }

    public String getAliasImage0TreeNodeGetUserObject1() {
        return aliasImage0TreeNodeGetUserObject1;
    }

    public String getAliasImage0TreeNodeSetUserObject0() {
        return aliasImage0TreeNodeSetUserObject0;
    }

    public String getAliasImage0TreeNodeSetUserObject1() {
        return aliasImage0TreeNodeSetUserObject1;
    }

    public String getAliasImage0TreeNodeSetUserObject2() {
        return aliasImage0TreeNodeSetUserObject2;
    }

    public String getAliasImage0ImageSetColor0() {
        return aliasImage0ImageSetColor0;
    }

    public String getAliasImage0SetFont0() {
        return aliasImage0SetFont0;
    }

    public String getAliasImage0ImageDraw0() {
        return aliasImage0ImageDraw0;
    }

    public String getAliasImage0ImageDraw1() {
        return aliasImage0ImageDraw1;
    }

    public String getAliasImage0ImageDraw2() {
        return aliasImage0ImageDraw2;
    }

    public String getAliasImage1ImageDraw0() {
        return aliasImage1ImageDraw0;
    }

    public String getAliasImage1ImageDraw1() {
        return aliasImage1ImageDraw1;
    }

    public String getAliasImage1ImageDraw2() {
        return aliasImage1ImageDraw2;
    }

    public String getAliasImage0ImageDrawLine0() {
        return aliasImage0ImageDrawLine0;
    }

    public String getAliasImage0ImageDrawLine1() {
        return aliasImage0ImageDrawLine1;
    }

    public String getAliasImage0ImageDrawLine2() {
        return aliasImage0ImageDrawLine2;
    }

    public String getAliasImage0ImageDrawLine3() {
        return aliasImage0ImageDrawLine3;
    }

    public String getAliasImage0ImageDrawRect0() {
        return aliasImage0ImageDrawRect0;
    }

    public String getAliasImage0ImageDrawRect1() {
        return aliasImage0ImageDrawRect1;
    }

    public String getAliasImage0ImageDrawRect2() {
        return aliasImage0ImageDrawRect2;
    }

    public String getAliasImage0ImageDrawRect3() {
        return aliasImage0ImageDrawRect3;
    }

    public String getAliasImage0ImageDrawOval0() {
        return aliasImage0ImageDrawOval0;
    }

    public String getAliasImage0ImageDrawOval1() {
        return aliasImage0ImageDrawOval1;
    }

    public String getAliasImage0ImageDrawOval2() {
        return aliasImage0ImageDrawOval2;
    }

    public String getAliasImage0ImageDrawOval3() {
        return aliasImage0ImageDrawOval3;
    }

    public String getAliasImage0ImageDrawPolygon0() {
        return aliasImage0ImageDrawPolygon0;
    }

    public String getAliasImage0ImageDrawPolygon1() {
        return aliasImage0ImageDrawPolygon1;
    }

    public String getAliasImage0ImageFillRect0() {
        return aliasImage0ImageFillRect0;
    }

    public String getAliasImage0ImageFillRect1() {
        return aliasImage0ImageFillRect1;
    }

    public String getAliasImage0ImageFillRect2() {
        return aliasImage0ImageFillRect2;
    }

    public String getAliasImage0ImageFillRect3() {
        return aliasImage0ImageFillRect3;
    }

    public String getAliasImage0ImageFillOval0() {
        return aliasImage0ImageFillOval0;
    }

    public String getAliasImage0ImageFillOval1() {
        return aliasImage0ImageFillOval1;
    }

    public String getAliasImage0ImageFillOval2() {
        return aliasImage0ImageFillOval2;
    }

    public String getAliasImage0ImageFillOval3() {
        return aliasImage0ImageFillOval3;
    }

    public String getAliasImage0ImageFillPolygon0() {
        return aliasImage0ImageFillPolygon0;
    }

    public String getAliasImage0ImageFillPolygon1() {
        return aliasImage0ImageFillPolygon1;
    }

    public String getAliasImage0ImageEq0() {
        return aliasImage0ImageEq0;
    }

    public String getAliasImage0ImageEq1() {
        return aliasImage0ImageEq1;
    }

    public String getAliasImage0Image0() {
        return aliasImage0Image0;
    }

    public String getAliasImage0Image1() {
        return aliasImage0Image1;
    }

    public String getAliasImage0Image2() {
        return aliasImage0Image2;
    }

    public String getAliasRender0RenderSetHeight0() {
        return aliasRender0RenderSetHeight0;
    }

    public String getAliasRender0RenderSetWidth0() {
        return aliasRender0RenderSetWidth0;
    }

    public String getAliasRender0ComponentSetPaint0() {
        return aliasRender0ComponentSetPaint0;
    }

    public String getAliasGrList0GrListSetRender0() {
        return aliasGrList0GrListSetRender0;
    }

    public String getAliasGrList0GrListAddSelection0() {
        return aliasGrList0GrListAddSelection0;
    }

    public String getAliasGrList0GrListRemoveSelection0() {
        return aliasGrList0GrListRemoveSelection0;
    }

    public String getAliasGrList0GrListSetVisibleRowCount0() {
        return aliasGrList0GrListSetVisibleRowCount0;
    }

    public String getAliasGrList0TabbedPaneAdd0() {
        return aliasGrList0TabbedPaneAdd0;
    }

    public String getAliasGrList0TabbedPaneAdd1() {
        return aliasGrList0TabbedPaneAdd1;
    }

    public String getAliasGrList1TabbedPaneAdd0() {
        return aliasGrList1TabbedPaneAdd0;
    }

    public String getAliasGrList1TabbedPaneAdd1() {
        return aliasGrList1TabbedPaneAdd1;
    }

    public String getAliasGrList1TabbedPaneAdd2() {
        return aliasGrList1TabbedPaneAdd2;
    }

    public String getAliasGrList2TabbedPaneAdd0() {
        return aliasGrList2TabbedPaneAdd0;
    }

    public String getAliasGrList0TreeNodeSetUserObject0() {
        return aliasGrList0TreeNodeSetUserObject0;
    }

    public String getAliasGrList0TreeNodeSetUserObject1() {
        return aliasGrList0TreeNodeSetUserObject1;
    }

    public String getAliasGrList0TreeNodeSetUserObject2() {
        return aliasGrList0TreeNodeSetUserObject2;
    }

    public String getAliasGrList1TreeNodeSetUserObject0() {
        return aliasGrList1TreeNodeSetUserObject0;
    }

    public String getAliasGrList1TreeNodeSetUserObject1() {
        return aliasGrList1TreeNodeSetUserObject1;
    }

    public String getAliasGrList0GrListSetSelectedIndexes0() {
        return aliasGrList0GrListSetSelectedIndexes0;
    }

    public String getAliasGrList0RemoveCompo0() {
        return aliasGrList0RemoveCompo0;
    }

    public String getAliasGrList0GrList0() {
        return aliasGrList0GrList0;
    }

    public String getAliasCombo0ComboAddListener0() {
        return aliasCombo0ComboAddListener0;
    }

    public String getAliasCombo0ComboRemoveListener0() {
        return aliasCombo0ComboRemoveListener0;
    }

    public String getAliasCombo0TabbedPaneAdd0() {
        return aliasCombo0TabbedPaneAdd0;
    }

    public String getAliasCombo0ComboSelectItem0() {
        return aliasCombo0ComboSelectItem0;
    }

    public String getAliasCombo0ComboRemoveItem0() {
        return aliasCombo0ComboRemoveItem0;
    }

    public String getAliasCombo0Combo0() {
        return aliasCombo0Combo0;
    }

    public String getAliasCombo1Combo0() {
        return aliasCombo1Combo0;
    }

    public String getAliasCombo1Combo1() {
        return aliasCombo1Combo1;
    }

    public String getAliasButtonGroup0TabbedPaneAdd0() {
        return aliasButtonGroup0TabbedPaneAdd0;
    }

    public String getAliasButtonGroup0TabbedPaneRemove0() {
        return aliasButtonGroup0TabbedPaneRemove0;
    }

    public String getAliasPopupMenu0TabbedPaneAdd0() {
        return aliasPopupMenu0TabbedPaneAdd0;
    }

    public String getAliasPopupMenu0PopupMenuGetComp0() {
        return aliasPopupMenu0PopupMenuGetComp0;
    }

    public String getAliasPopupMenu0PopupMenuRemoveComp0() {
        return aliasPopupMenu0PopupMenuRemoveComp0;
    }

    public String getAliasPopupMenu0PopupMenuShow0() {
        return aliasPopupMenu0PopupMenuShow0;
    }

    public String getAliasPopupMenu0PopupMenuShow1() {
        return aliasPopupMenu0PopupMenuShow1;
    }

    public String getAliasPopupMenu0PopupMenuShow2() {
        return aliasPopupMenu0PopupMenuShow2;
    }

    public String getAliasRadio0RadioSetSelected0() {
        return aliasRadio0RadioSetSelected0;
    }

    public String getAliasRadio0SetLabelText0() {
        return aliasRadio0SetLabelText0;
    }

    public String getAliasRadio0Radio0() {
        return aliasRadio0Radio0;
    }

    public String getAliasRadio1Radio0() {
        return aliasRadio1Radio0;
    }

    public String getAliasRadio1Radio1() {
        return aliasRadio1Radio1;
    }

    public String getAliasCheckBox0RadioSetSelected0() {
        return aliasCheckBox0RadioSetSelected0;
    }

    public String getAliasCheckBox0SetLabelText0() {
        return aliasCheckBox0SetLabelText0;
    }

    public String getAliasCheckBox0CheckBox0() {
        return aliasCheckBox0CheckBox0;
    }

    public String getAliasCheckBox1CheckBox0() {
        return aliasCheckBox1CheckBox0;
    }

    public String getAliasCheckBox1CheckBox1() {
        return aliasCheckBox1CheckBox1;
    }

    public String getAliasTextField0SetLabelText0() {
        return aliasTextField0SetLabelText0;
    }

    public String getAliasTextField0TextFieldAddAction0() {
        return aliasTextField0TextFieldAddAction0;
    }

    public String getAliasTextField0TextFieldRemoveAction0() {
        return aliasTextField0TextFieldRemoveAction0;
    }

    public String getAliasTextField0TextField0() {
        return aliasTextField0TextField0;
    }

    public String getAliasTextField1TextField0() {
        return aliasTextField1TextField0;
    }

    public String getAliasTextField2TextField0() {
        return aliasTextField2TextField0;
    }

    public String getAliasTextField2TextField1() {
        return aliasTextField2TextField1;
    }

    public String getAliasTextArea0SetLabelText0() {
        return aliasTextArea0SetLabelText0;
    }

    public String getAliasTextArea0TabbedPaneAdd0() {
        return aliasTextArea0TabbedPaneAdd0;
    }

    public String getAliasTextArea0TreeNodeInsert0() {
        return aliasTextArea0TreeNodeInsert0;
    }

    public String getAliasTextArea0TreeNodeInsert1() {
        return aliasTextArea0TreeNodeInsert1;
    }

    public String getAliasTextArea0TextAreaSetSelectionStart0() {
        return aliasTextArea0TextAreaSetSelectionStart0;
    }

    public String getAliasTextArea0TextAreaSetSelectionEnd0() {
        return aliasTextArea0TextAreaSetSelectionEnd0;
    }

    public String getAliasTextArea0TextAreaSetTabSize0() {
        return aliasTextArea0TextAreaSetTabSize0;
    }

    public String getAliasTextArea0TextAreaReplaceRange0() {
        return aliasTextArea0TextAreaReplaceRange0;
    }

    public String getAliasTextArea0TextAreaReplaceRange1() {
        return aliasTextArea0TextAreaReplaceRange1;
    }

    public String getAliasTextArea0TextAreaReplaceRange2() {
        return aliasTextArea0TextAreaReplaceRange2;
    }

    public String getAliasTextArea0TextAreaReplaceSelection0() {
        return aliasTextArea0TextAreaReplaceSelection0;
    }

    public String getAliasTextArea0TreeGetSelected0() {
        return aliasTextArea0TreeGetSelected0;
    }

    public String getAliasTextArea0TreeGetSelected1() {
        return aliasTextArea0TreeGetSelected1;
    }

    public String getAliasTextArea0TextArea0() {
        return aliasTextArea0TextArea0;
    }

    public String getAliasTextArea1TextArea0() {
        return aliasTextArea1TextArea0;
    }

    public String getAliasTextArea1TextArea1() {
        return aliasTextArea1TextArea1;
    }

    public String getAliasTextArea2TextArea0() {
        return aliasTextArea2TextArea0;
    }

    public String getAliasTextArea2TextArea1() {
        return aliasTextArea2TextArea1;
    }

    public String getAliasTextArea2TextArea2() {
        return aliasTextArea2TextArea2;
    }

    public String getAliasSpinner0SpinnerSetMax0() {
        return aliasSpinner0SpinnerSetMax0;
    }

    public String getAliasSpinner0SpinnerSetMin0() {
        return aliasSpinner0SpinnerSetMin0;
    }

    public String getAliasSpinner0TreeNodeSetUserObject0() {
        return aliasSpinner0TreeNodeSetUserObject0;
    }

    public String getAliasSpinner0SpinnerSetStep0() {
        return aliasSpinner0SpinnerSetStep0;
    }

    public String getAliasSpinner0SpinnerSetRange0() {
        return aliasSpinner0SpinnerSetRange0;
    }

    public String getAliasSpinner0SpinnerSetRange1() {
        return aliasSpinner0SpinnerSetRange1;
    }

    public String getAliasSpinner0SpinnerSetRangeValue0() {
        return aliasSpinner0SpinnerSetRangeValue0;
    }

    public String getAliasSpinner0SpinnerSetRangeValue1() {
        return aliasSpinner0SpinnerSetRangeValue1;
    }

    public String getAliasSpinner0SpinnerSetRangeValue2() {
        return aliasSpinner0SpinnerSetRangeValue2;
    }

    public String getAliasSpinner0AddChange0() {
        return aliasSpinner0AddChange0;
    }

    public String getAliasSpinner0Spinner0() {
        return aliasSpinner0Spinner0;
    }

    public String getAliasSpinner0Spinner1() {
        return aliasSpinner0Spinner1;
    }

    public String getAliasSpinner0Spinner2() {
        return aliasSpinner0Spinner2;
    }

    public String getAliasSpinner0Spinner3() {
        return aliasSpinner0Spinner3;
    }

    public String getAliasSlider0SpinnerSetMax0() {
        return aliasSlider0SpinnerSetMax0;
    }

    public String getAliasSlider0SpinnerSetMin0() {
        return aliasSlider0SpinnerSetMin0;
    }

    public String getAliasSlider0TreeNodeSetUserObject0() {
        return aliasSlider0TreeNodeSetUserObject0;
    }

    public String getAliasSlider0SliderSetOrientation0() {
        return aliasSlider0SliderSetOrientation0;
    }

    public String getAliasSlider0AddChange0() {
        return aliasSlider0AddChange0;
    }

    public String getAliasSlider0Slider0() {
        return aliasSlider0Slider0;
    }

    public String getAliasSlider1Slider0() {
        return aliasSlider1Slider0;
    }

    public String getAliasSlider1Slider1() {
        return aliasSlider1Slider1;
    }

    public String getAliasSlider2Slider0() {
        return aliasSlider2Slider0;
    }

    public String getAliasSlider2Slider1() {
        return aliasSlider2Slider1;
    }

    public String getAliasSlider2Slider2() {
        return aliasSlider2Slider2;
    }

    public String getAliasSlider3Slider0() {
        return aliasSlider3Slider0;
    }

    public String getAliasSlider3Slider1() {
        return aliasSlider3Slider1;
    }

    public String getAliasSlider3Slider2() {
        return aliasSlider3Slider2;
    }

    public String getAliasSlider3Slider3() {
        return aliasSlider3Slider3;
    }

    public String getAliasMenuBar0TabbedPaneAdd0() {
        return aliasMenuBar0TabbedPaneAdd0;
    }

    public String getAliasMenuBar0TreeNodeRemove0() {
        return aliasMenuBar0TreeNodeRemove0;
    }

    public String getAliasMenuBar0TreeNodeGetUserObject0() {
        return aliasMenuBar0TreeNodeGetUserObject0;
    }

    public String getAliasAbsMenu0AbsMenuSetText0() {
        return aliasAbsMenu0AbsMenuSetText0;
    }

    public String getAliasAbsMenu0AbsMenuSetDeepEnabled0() {
        return aliasAbsMenu0AbsMenuSetDeepEnabled0;
    }

    public String getAliasMenu0TabbedPaneAdd0() {
        return aliasMenu0TabbedPaneAdd0;
    }

    public String getAliasMenu0TreeNodeRemove0() {
        return aliasMenu0TreeNodeRemove0;
    }

    public String getAliasMenu0TreeNodeGetUserObject0() {
        return aliasMenu0TreeNodeGetUserObject0;
    }

    public String getAliasMenu0Menu0() {
        return aliasMenu0Menu0;
    }

    public String getAliasMenuItem0MenuItem0() {
        return aliasMenuItem0MenuItem0;
    }

    public String getAliasMenuItemCheck0RadioSetSelected0() {
        return aliasMenuItemCheck0RadioSetSelected0;
    }

    public String getAliasMenuItemCheck0MenuItemCheck0() {
        return aliasMenuItemCheck0MenuItemCheck0;
    }

    public String getAliasCommand0Binding0() {
        return aliasCommand0Binding0;
    }

    public String getAliasCommand0Action0() {
        return aliasCommand0Action0;
    }

    public String getAliasComponent0Bind0() {
        return aliasComponent0Bind0;
    }

    public String getAliasComponent0Bind1() {
        return aliasComponent0Bind1;
    }

    public String getAliasComponent0Bind2() {
        return aliasComponent0Bind2;
    }

    public String getAliasComponent0Unbind0() {
        return aliasComponent0Unbind0;
    }

    public String getAliasComponent0Unbind1() {
        return aliasComponent0Unbind1;
    }

    public String getAliasActionListenerImplicit0Implicit0() {
        return aliasActionListenerImplicit0Implicit0;
    }

    public String getAliasActionListenerImplicit0Implicit1() {
        return aliasActionListenerImplicit0Implicit1;
    }

    public String getAliasCellRender0Generate0() {
        return aliasCellRender0Generate0;
    }

    public String getAliasCellRender0Generate1() {
        return aliasCellRender0Generate1;
    }

    public String getAliasCellRender0Generate2() {
        return aliasCellRender0Generate2;
    }

    public String getAliasCellRender0Generate3() {
        return aliasCellRender0Generate3;
    }

    public String getAliasCellRender0Generate4() {
        return aliasCellRender0Generate4;
    }

    public String getAliasCellRender0Generate5() {
        return aliasCellRender0Generate5;
    }

    public String getAliasCellRender0Generate6() {
        return aliasCellRender0Generate6;
    }

    public String getAliasCellRenderImplicit0Implicit0() {
        return aliasCellRenderImplicit0Implicit0;
    }

    public String getAliasCellRenderImplicit0Implicit1() {
        return aliasCellRenderImplicit0Implicit1;
    }

    public String getAliasCellRender0Generate00() {
        return aliasCellRender0Generate00;
    }

    public String getAliasCellRender0Generate01() {
        return aliasCellRender0Generate01;
    }
}
