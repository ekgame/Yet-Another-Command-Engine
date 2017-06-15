package lt.ekgame.cmds.arguments.named;

import java.util.ArrayList;
import java.util.List;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;

public class ArgMultiple extends NamedArgument {
	
	private CmdArgument argument;
	private boolean requireOne;
	
	public ArgMultiple(String name, boolean requireOne, CmdArgument argument) {
		super(name);
		this.argument = argument;
		this.requireOne = requireOne;
	}

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		List<Object> result = new ArrayList<>();
		while (true) {
			Object last = argument.parse(context, tokenizer);
			if (last != null)
				result.add(last);
			else
				break;
		}
		
		if (requireOne && result.size() < 1) {
			throw new ArgumentException("At least one item is required.");
		}
		
		return result;
	}

	@Override
	public String getUsage() {
		return argument.getUsage() + " ...";
	}
}