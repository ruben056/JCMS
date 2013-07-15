package rd.util.widget;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringEscapeUtils;

import rd.util.GeneralUtil;
import rd.util.widget.plugin.Plugin;
import rd.util.widget.plugin.PluginFactory;

public class WidgetFactory {

	// custom widgets created by users, are retrieved from config files
	
	// system/standard widgets go here as static String,
	public static String RD_MENU = "rdMenu";

	/**
	 * 
	 * @param jsonStringWitName: name + json representation of the widget example: rdMenu{'direction':'0', 'opacity':'1'}
	 */
	public static IWidget createWidget(EntityManager eMgr, String jsonStringWithName) throws Exception{
		String name = jsonStringWithName.substring(0, jsonStringWithName.indexOf('{'));
		String atts = jsonStringWithName.substring(jsonStringWithName.indexOf('{'));

		IWidget result = null;
		
		if(WidgetFactory.RD_MENU.equals(name)){
			result = GeneralUtil.getGSON().fromJson(StringEscapeUtils.unescapeHtml4(atts), RdMenu.class);			
		}else{
			System.out.println("Checking plugins for widget with name:" + name);
			Plugin p = PluginFactory.getPluginForWidget(name);
			
			if(p == null){
				throw new ServletException("No widget with the name : " + name +" exsists!!");
			}
			result = (IWidget)GeneralUtil.getGSON().fromJson(StringEscapeUtils.unescapeHtml4(atts), 
					Class.forName(p.getWidgetClass()));
		}
		return result;
	}
}
