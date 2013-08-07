package rd.util.widget.plugin;

import java.util.Vector;

import rd.util.widget.IWidget;

public class TestPlugin implements IWidget {

	public final static String REPLACE_PATTERN = "\\[\\[jCMS:testPlugin(.*?)\\]\\]";
	
	private String title;
	
	@Override
	public String getReplacePattern() {
		return TestPlugin.REPLACE_PATTERN;
	}

	@Override
	public String toHTML() {
		StringBuilder sb = new StringBuilder("<div>");
		// TODO Auto-generated method stub
		sb.append("title: " + getTitle());
		sb.append("<p>qs fdmqskfdj qmsldf  comments stuff comment stuff</p>");
		sb.append("</div>");
		return sb.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean isOnePerPage() {
		return false;
	}

	@Override
	public Vector<String> addStylesAndScripts(Vector<String> stylesAndScripts) {
		return stylesAndScripts;
	}

}
