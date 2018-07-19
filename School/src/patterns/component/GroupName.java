package patterns.component;

import java.util.ArrayList;

public class GroupName implements SongComponent{
	private String groupName;
	private String groupLeader;
	private ArrayList<SongComponent> components;
	public GroupName(String groupName, String groupLeader) {
		this.groupName = groupName;
		this.groupLeader = groupLeader;
		components = new ArrayList<SongComponent>(10);
	}
	@Override
	public void add(SongComponent component) {
		components.add(component);
	}

	@Override
	public void remove(SongComponent component) {
		components.remove(component);
	}
	
	@Override
	public void displayInfo() {
		System.out.println("The group name is " + groupName + " and the group leader is " + groupLeader + ".\n");
		for(SongComponent component: components) {
			component.displayInfo();
		}
	}
	public String getGroupName() {
		return groupName;
	}
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

}
