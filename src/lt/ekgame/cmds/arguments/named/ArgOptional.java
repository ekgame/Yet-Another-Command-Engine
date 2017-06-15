package lt.ekgame.cmds.arguments.named;

import java.util.Optional;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;

public class ArgOptional extends NamedArgument {
	
	private CmdArgument argument;
	
	public ArgOptional(String name, CmdArgument argument) {
		super(name);
		this.argument = argument;
	}

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		Object result = argument.parse(context, tokenizer);
		return result == null ? Optional.empty() : Optional.of(result);
	}

	@Override
	public String getUsage() {
		return "[" + argument.getUsage() + "]";
	}
}
