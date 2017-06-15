package lt.ekgame.cmds.arguments.flags;

import java.util.List;

public class ArgFlag {
	
	private List<String> names;
	
	public ArgFlag(List<String> names) {
		this.names = names;
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public String getMainName() {
		return names.get(0);
	}
}
