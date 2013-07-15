package rd.util.widget;

import javax.persistence.EntityManager;

public class RDFooter extends BaseWidget{

	public final static String REPLACE_PATTERN = "\\[\\[jCMS:rdFooter(.*?)\\]\\]";
	
	@Override
	public String getReplacePattern() {
		return RDFooter.REPLACE_PATTERN;
	}

	@Override
	public String toHTML(EntityManager eMgr) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("<div id='rdFooter'>");
		
		
		sb.append("</div>");
		return sb.toString();
	}

	@Override
	public boolean isOnePerPage() {
		return true;
	}

}
