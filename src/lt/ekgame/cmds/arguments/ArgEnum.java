package lt.ekgame.cmds.arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;

public class ArgEnum<T> implements CmdArgument {
	
	private Map<String, T> enumMap = new HashMap<>();

	@Override
	public Object parse(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		CmdToken token = tokenizer.nextToken();
		if (token.isPresent()) {
			String input = token.getValue().toLowerCase();
			if (enumMap.containsKey(input))
				return enumMap.get(input);
			
			throw new ArgumentException("Invalid option \"" + input + "\".");
		}
		return null;
	}
	
	public ArgEnum<T> entry(String name, T value) {
		enumMap.put(name.toLowerCase(), value);
		return this;
	}

	@Override
	public String getUsage() {
		return enumMap.keySet().stream().collect(Collectors.joining("|", "(", ")"));
	}
}
