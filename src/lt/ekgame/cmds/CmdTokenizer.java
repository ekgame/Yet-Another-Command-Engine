package lt.ekgame.cmds;

public interface CmdTokenizer {
	
	CmdToken nextToken();
	
	CmdToken nextString();
	
	CmdToken nextLine();
	
	CmdToken remaning();
	
	void pushBack(CmdToken token);
	
	String getOriginalInput();
}
