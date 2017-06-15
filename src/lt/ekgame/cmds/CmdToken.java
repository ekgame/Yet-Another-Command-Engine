package lt.ekgame.cmds;

public interface CmdToken {
	
	public boolean isPresent();
	
	public String getRawValue();
	
	public String getValue();
}
