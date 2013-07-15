package rd.util.widget.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import rd.util.widget.IWidget;
import rd.util.widget.WidgetFactory;

public class ContentParser {

	private final static String SEARCH_PATTERN = "\\[\\[jCMS:(.*?)\\]\\]";

	/**
	 * It parses the string for the first widget pattern it comes across
	 * When it is found it is replaced by its widgets html.
	 * With this new string this logic is repeated untill all widgets are replaced in the content
	 * 
	 * @param eMgr
	 * @param content
	 * @return
	 */
	public static String parse(EntityManager eMgr, String content) {

		String result = content;
		Pattern p = Pattern.compile(ContentParser.SEARCH_PATTERN);
		Matcher m = p.matcher(content);
		if(m.find()){
			IWidget w = WidgetFactory.createWidget(eMgr, m.group(1));
			String tmp = content.replaceFirst(w.getReplacePattern(), w.toHTML(eMgr));
			result = ContentParser.parse(eMgr, tmp);
		}
		return result; 
	}
}
