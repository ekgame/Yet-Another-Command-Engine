package lt.ekgame.cmds.test;

import lt.ekgame.cmds.ResultStatus;
import lt.ekgame.cmds.impl.CommandEngine;

public class TestEngine extends CommandEngine<TestContext, TestResult> {

	public TestEngine() {
		super(context -> {
			return new TestResult(null, ResultStatus.UNKNOWN_COMMAND, "Unknown command.");
		});
	}
}
