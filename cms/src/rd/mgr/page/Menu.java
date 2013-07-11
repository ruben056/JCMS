package rd.mgr.page;

import javax.persistence.EntityManager;

import rd.util.ComponentFactory;
import rd.util.ENUM_UTIL;

public class Menu {
	
	private static int menuIDs = 0;
	
	private int direction = ENUM_UTIL.DIRECTION_HORIZONTAL; 
	private long parent = 0; // top level
	private String backGround = "";
	private int columns = 1; // dropdown submenus
	private int opacity = 1; // opacity of the submenus
	
	public Menu(){
		
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public long getParent() {
		return this.parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public String getBackGround() {
		return backGround;
	}

	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	
	
	private String getDirectionString(){
		if(direction == ENUM_UTIL.DIRECTION_HORIZONTAL){
			return "horizontal";
		}else{
			return "vertical";
		}
	}
	public String toHTML(EntityManager eMgr){
		
//		int menuID = Menu.menuIDs++;
//		StringBuffer s = new StringBuffer("<div class='menu-fg menu-fg-").append(getDirectionString());
//		s.append("' id='menu-fg-").append(menuID).append("'>");
		StringBuffer s = new StringBuffer();
//		s.append("<div id='").append("container").append("'>");
		s.append("<ul id='nav'>");
		s.append(buildList(eMgr, getParent(), 0));
		s.append("</ul>");
//		s.append("</div>");
		
		//After that, (in your own javascript file) create a new class instance.
		s.append("<script src='/cms/js/MenuMatic_0.68.3-source.js'></script>");
		s.append("<script src='/cms/js/widgets/menu.js'></script>");
		
		return s.toString();
	}
	
	
	// TODO add order by here : book page 129
	// retrieve all Pages for a certain parentPage:
	private String buildList(EntityManager eMgr, long parentID, int depth){
		StringBuffer sb = new StringBuffer();
	
		Page[] childPages = ComponentFactory.getPageMgr().getPagesForParent(eMgr, parentID);
		if(childPages.length < 1){
			return "";
		}
		
		if(depth != 0){
			sb.append("<ul");
//			StringBuffer style = new StringBuffer(); 
//			if(!StringUtil.isNull(getBackGround())){
//				style.append("background: ").append(getBackGround()).append(";");
//			}
//			if(getOpacity() != 1){
//				style.append("opacity: ").append(getOpacity()).append(";");
//			}
//			if(style.length() > 0){
//				sb.append(" style=").append(style.toString());
//			}
			sb.append(">");
		}
		
		for (int i = 0; i < childPages.length; i++) {
			Page child = childPages[i];
			sb.append("<li><a href='" +  child.getRelativeURL(eMgr)).append("'>").append(child.getName()).append("</a>");
			sb.append(buildList(eMgr, child.getId(), depth+1));
			sb.append("</li>");
		}
		if(depth != 0)sb.append("</ul>");
		
		return sb.toString();
	}
}