package lt.ekgame.cmds;

public interface CmdFlags {
	
	public boolean has(String longName);

	public <T> T get(String longName, T defaultValue);
	
}
