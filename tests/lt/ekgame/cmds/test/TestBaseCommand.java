package lt.ekgame.cmds.test;

import java.util.List;

import lt.ekgame.cmds.CmdMiddleware;
import lt.ekgame.cmds.ResultStatus;
import lt.ekgame.cmds.arguments.named.NamedArgument;
import lt.ekgame.cmds.impl.CommandHandler;

public abstract class TestBaseCommand extends CommandHandler<TestContext, TestResult>  {

	@Override
	protected abstract List<CmdMiddleware<TestContext, TestResult>> getMiddleware();

	@Override
	protected abstract List<NamedArgument> getArguments();

	@Override
	protected abstract TestResult handleCommand(TestContext context);
	
	protected TestResult resultError(String message) {
		return new TestResult(this, ResultStatus.REJECTED, message);
	}

	protected TestResult resultOk(String message) {
		return new TestResult(this, ResultStatus.ACCEPTED, message);
	}
}
