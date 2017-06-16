package lt.ekgame.cmds;

public interface CmdEngine<Context extends CmdContext, Result extends Throwable> extends CmdBranch<Context, Result> {

}
