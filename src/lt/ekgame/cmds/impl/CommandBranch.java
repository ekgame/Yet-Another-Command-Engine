package lt.ekgame.cmds.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import lt.ekgame.cmds.CmdBranch;
import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdHandler;
import lt.ekgame.cmds.CmdNode;
import lt.ekgame.cmds.CmdToken;

public class CommandBranch<C extends CmdContext, R extends Exception> implements CmdBranch<C, R> {
	
	protected String name;
	private CmdHandler<C, R> defaultHandler = null;
	protected Map<String, CmdNode<C, R>> nodeMap = new HashMap<>();
	protected Map<String, CmdHandler<C, R>> handlerMap = new HashMap<>();
	
	public CommandBranch(String name, CmdHandler<C, R> handler) {
		this.name = name;
		this.defaultHandler = handler;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public CmdHandler<C, R> getHandler(C context) {
		CmdToken token = context.getTokenizer().nextToken();
		if (token.isPresent()) {
			String command = token.getValue().toLowerCase();
			if (nodeMap.containsKey(command))
				return nodeMap.get(command).getHandler(context);
			
			if (handlerMap.containsKey(command))
				return handlerMap.get(command);
			
			context.getTokenizer().pushBack(token);
		}
		return defaultHandler;
	}
	
	@Override
	public void command(String label, CmdHandler<C, R> defaultHandler, Consumer<CmdNode<C, R>> consumer) {
		CommandNode<C, R> node = new CommandNode<>(label, this, defaultHandler);
		consumer.accept(node);
		nodeMap.put(label.toLowerCase(), node);
	}
	
	@Override
	public void command(List<String> labels, CmdHandler<C, R> defaultHandler, Consumer<CmdNode<C, R>> consumer) {
		String name = labels.get(0);
		CommandNode<C, R> node = new CommandNode<>(name, this, defaultHandler);
		consumer.accept(node);
		labels.forEach(label -> nodeMap.put(label.toLowerCase(), node));
	}
	

	@Override
	public void command(String label, CmdHandler<C, R> handler) {
		handlerMap.put(label.toLowerCase(), handler);
	}

	@Override
	public void command(List<String> labels, CmdHandler<C, R> handler) {
		labels.forEach(label -> command(label, handler));
	}

	@Override
	public CmdHandler<C, R> getCommand(String label) {
		return handlerMap.get(label.toLowerCase());
	}
}
