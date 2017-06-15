package lt.ekgame.cmds.arguments.flags;

import java.util.List;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;

public class ArgFlagValue extends ArgFlag {

	private CmdArgument argument;
	
	public ArgFlagValue(List<String> names, CmdArgument argument) {
		super(names);
		this.argument = argument;
	}
	
	public Object evaluate(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		return argument.parse(context, tokenizer);
	}
}
