package lt.ekgame.cmds;

import java.util.List;

import lt.ekgame.cmds.arguments.ArgumentException;
import lt.ekgame.cmds.arguments.flags.ArgFlag;

public interface CmdContext {
	
	CmdTokenizer getTokenizer();
	
	List<String> getNodeStack();
	
	void pushNode(String name);
	
	void set(String name, Object value);
	
	void handleFlags(List<ArgFlag> flags) throws ArgumentException;
	
	CmdFlags getFlags();
	
	public <T> T get(String name);

}
