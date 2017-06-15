package lt.ekgame.cmds.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdFlags;
import lt.ekgame.cmds.CmdTokenizer;
import lt.ekgame.cmds.arguments.ArgumentException;
import lt.ekgame.cmds.arguments.flags.ArgFlag;

public class CommandContext implements CmdContext {
	
	private CmdTokenizer tokenizer;
	
	private List<String> nodeStack = new LinkedList<>();
	private Map<String, Object> objects = new HashMap<>();
	private CmdFlags flags;
	
	public CommandContext(CmdTokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	@Override
	public CmdTokenizer getTokenizer() {
		return tokenizer;
	}
	
	public void set(String name, Object value) {
		objects.put(name, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String argument) {
		return (T) objects.get(argument);
	}

	@Override
	public List<String> getNodeStack() {
		return Collections.unmodifiableList(nodeStack);
	}

	@Override
	public void pushNode(String name) {
		nodeStack.add(name);
	}

	@Override
	public void handleFlags(List<ArgFlag> flagArguments) throws ArgumentException {
		flags = new CommandFlags(this, tokenizer, flagArguments);
	}

	@Override
	public CmdFlags getFlags() {
		return flags;
	}
}
