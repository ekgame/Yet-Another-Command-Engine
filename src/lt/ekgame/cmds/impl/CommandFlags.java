package lt.ekgame.cmds.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdFlags;
import lt.ekgame.cmds.CmdToken;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;
import lt.ekgame.cmds.arguments.flags.ArgFlag;
import lt.ekgame.cmds.arguments.flags.ArgFlagValue;

public class CommandFlags implements CmdFlags {
	
	private Map<String, ArgFlag> flagArgMap = new HashMap<>();
	private Map<String, Object> flagMap = new HashMap<>();
	
	public CommandFlags(CmdContext context, CmdTokenizer tokenizer, List<ArgFlag> flagArguments) throws ArgumentException {
		flagArguments.forEach(flag -> {
			flag.getNames().forEach(name -> 
				flagArgMap.put(name, flag)
			);
		});
		parseFlags(context, tokenizer);
	}
	
	private void handleFlag(CmdContext context, ArgFlag flag, String argument) throws ArgumentException {
		if (flag instanceof ArgFlagValue) {
			ArgFlagValue flagHandler = (ArgFlagValue) flag;
			Object result = flagHandler.evaluate(context, new CommandTokenizer(argument));
			flagMap.put(flag.getMainName(), result);
		}
		else {
			flagMap.put(flag.getMainName(), true);
		}
	}
	
	private void parseFlags(CmdContext context, CmdTokenizer tokenizer) throws ArgumentException {
		List<FlagDefinition> flagDefinitons = parseFlagDefinitons(tokenizer);
		for (FlagDefinition definition : flagDefinitons) {
			String flag = definition.flagToken.getValue();
			String argument = definition.getRawArguments();
			
			if (flag.startsWith("-") || flag.startsWith("/")) 
				flag = flag.substring(1);
			else continue;
			
			ArgFlag arg = flagArgMap.get(flag);
			if (arg != null) handleFlag(context, arg, argument);
		}
	}
	
	private List<FlagDefinition> parseFlagDefinitons(CmdTokenizer tokenizer) {
		List<FlagDefinition> definitions = new ArrayList<>();
		FlagDefinition definition = null;
		while (true) {
			CmdToken token = tokenizer.nextString();
			if (token.isPresent()) {
				if (token.getValue().startsWith("-") || token.getValue().startsWith("/")) {
					definition = new FlagDefinition(token);
					definitions.add(definition);
				}
				else if (definition != null)
					definition.arguments.add(token);
			}
			else break;
		}
		return definitions;
	}
	
	public boolean has(String longName) {
		return flagMap.containsKey(longName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String longName, T defaultValue) {
		if (flagMap.containsKey(longName))
			return (T) flagMap.get(longName);
		return defaultValue;
	}
	
	class FlagDefinition {
		CmdToken flagToken;
		List<CmdToken> arguments = new ArrayList<CmdToken>();
		
		FlagDefinition(CmdToken flagToken) {
			this.flagToken = flagToken;
		}
		
		String getRawArguments() {
			return arguments.stream()
				.map(CmdToken::getRawValue)
				.collect(Collectors.joining());
		}
	}
}
