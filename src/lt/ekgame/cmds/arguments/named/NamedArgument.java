package lt.ekgame.cmds.arguments.named;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;

public abstract class NamedArgument implements CmdArgument {
	
	private String name;
	
	public NamedArgument(String name) {
		this.name = name;
	}

	@Override
	public abstract Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException;

	@Override
	public abstract String getUsage();
	
	public String getName() {
		return name;
	}

}
