package lt.ekgame.cmds;

public interface CmdNode<C extends CmdContext, R extends Throwable> extends CmdBranch<C, R> {
	
	CmdBranch<C, R> getParent();
	
	String getName();
}
