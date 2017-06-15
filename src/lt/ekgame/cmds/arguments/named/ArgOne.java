package lt.ekgame.cmds.arguments.named;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;

public class ArgOne extends NamedArgument {

	private CmdArgument argument;
	
	public ArgOne(String name, CmdArgument argument) {
		super(name);
		this.argument = argument;
	}

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		Object result = argument.parse(context, tokenizer);
		if (result == null) {
			throw new ArgumentException("Required argument " + getUsage() + " not found.");
		}
		return result;
	}

	@Override
	public String getUsage() {
		return argument.getUsage();
	}
}
