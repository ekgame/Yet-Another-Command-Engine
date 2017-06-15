package lt.ekgame.cmds.arguments;

import java.util.Arrays;
import java.util.List;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;

public class ArgBoolean implements CmdArgument {
	
	private static final List<String> trueValues = Arrays.asList("true", "tru", "yes", "yep", "1", "on");
	private static final List<String> falseValues = Arrays.asList("false", "no", "nope", "0", "off");

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		CmdToken token = tokenizer.nextToken();
		if (!token.isPresent())
			return null;
		
		String input = token.getValue();
		
		if (matchIgnoreCase(input, trueValues))
			return true;
		
		if (matchIgnoreCase(input, falseValues))
			return false;
		
		throw new ArgumentException(String.format("\"%s\" is not a valid boolean value.", input));
	}
	
	private boolean matchIgnoreCase(String input, List<String> values) {
		for (String value : values)
			if (value.equalsIgnoreCase(input))
				return true;
		return false;
	}

	@Override
	public String getUsage() {
		return "<true|false>";
	}
}