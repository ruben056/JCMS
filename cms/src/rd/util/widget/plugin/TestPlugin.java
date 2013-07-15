package rd.util.widget.plugin;

import javax.persistence.EntityManager;

import rd.util.widget.BaseWidget;

public class TestPlugin extends BaseWidget {

	public final static String REPLACE_PATTERN = "\\[\\[jCMS:pageComments(.*?)\\]\\]";
	
	private String title;
	
	@Override
	public String getReplacePattern() {
		return TestPlugin.REPLACE_PATTERN;
	}

	@Override
	public String toHTML(EntityManager eMgr) {
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

}
