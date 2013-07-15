package rd.util.widget;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringEscapeUtils;

import rd.util.GeneralUtil;

public class WidgetFactory {

	// system/standard widgets go here as static String,
	// custom widgets created by users, are retrieved from db
	public static String RD_MENU = "rdMenu";
	/**
	 * 
	 * @param jsonStringWitName: name + json representation of the widget example: rdMenu{'direction':'0', 'opacity':'1'}
	 */
	public static IWidget createWidget(EntityManager eMgr, String jsonStringWithName){
		String name = jsonStringWithName.substring(0, jsonStringWithName.indexOf('{'));
		String atts = jsonStringWithName.substring(jsonStringWithName.indexOf('{'));

		IWidget result = null;
		
		if(WidgetFactory.RD_MENU.equals(name)){
			result = GeneralUtil.getGSON().fromJson(StringEscapeUtils.unescapeHtml4(atts), RdMenu.class);			
		}else{
			System.out.println("no widget found with name " + name);
		}
		
		return result;
	}
}
