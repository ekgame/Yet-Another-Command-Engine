package lt.ekgame.cmds.impl;

import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdHandler;
import lt.ekgame.cmds.CmdNode;

public class CommandNode<C extends CmdContext, R extends Exception> extends CommandBranch<C, R> implements CmdNode<C, R> {
	
	private CommandBranch<C, R> parent;
	
	public CommandNode(String name, CommandBranch<C, R> parent, CmdHandler<C, R> defaultHandler) {
		super(name, defaultHandler);
		this.parent = parent;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public CommandBranch<C, R> getParent() {
		return parent;
	}
}
