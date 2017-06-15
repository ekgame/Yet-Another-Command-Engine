package lt.ekgame.cmds.arguments;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;

public class ArgRemainingString implements CmdArgument {
	
	private String usage; 
	
	public ArgRemainingString(String usage) {
		this.usage = usage;
	}

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) {
		CmdToken token = tokenizer.remaning();
		if (token.isPresent()) {
			String value = token.getValue().trim();
			if (!value.isEmpty()) 
				return value;
		}
		return null;
	}

	@Override
	public String getUsage() {
		return String.format("<%s>", usage);
	}
}