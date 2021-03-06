package lt.ekgame.cmds.arguments;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;

public class ArgString implements CmdArgument {
	
	private String usage; 
	
	public ArgString(String usage) {
		this.usage = usage;
	}

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) {
		CmdToken token = tokenizer.nextString();
		return token.isPresent() ? token.getValue() : null;
	}

	@Override
	public String getUsage() {
		return String.format("<%s>", usage);
	}
}
