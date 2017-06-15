package lt.ekgame.cmds.arguments;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;

public class ArgDouble implements CmdArgument {
	
	private String usage;
	
	public ArgDouble(String usage) {
		this.usage = usage;
	}

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		CmdToken token = tokenizer.nextToken();
		if (!token.isPresent())
			return null;
		
		String rawToken = token.getValue();
		
		try {
			return Double.parseDouble(rawToken);
		}
		catch (NumberFormatException e) {
			throw new ArgumentException(String.format("\"%s\" is not a valid number.", rawToken));
		}
	}

	@Override
	public String getUsage() {
		return String.format("<%s>", usage);
	}
}
