package lt.ekgame.cmds;

import lt.ekgame.cmds.arguments.ArgumentException;

public interface CmdArgument {
	
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException;
	
	public String getUsage();

}
