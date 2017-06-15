package lt.ekgame.cmds;

public interface CmdEngine<Context extends CmdContext, Result extends Exception> extends CmdBranch<Context, Result> {

}
