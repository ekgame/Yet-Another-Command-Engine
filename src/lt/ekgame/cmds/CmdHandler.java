package lt.ekgame.cmds;

import lt.ekgame.cmds.arguments.ArgumentException;

public interface CmdHandler<C extends CmdContext, R extends Exception> {
	
	public R handle(C context) throws ArgumentException, R;
	
}
