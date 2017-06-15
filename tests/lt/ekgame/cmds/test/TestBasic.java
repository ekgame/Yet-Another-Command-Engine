package lt.ekgame.cmds.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import lt.ekgame.cmds.CmdHandler;
import lt.ekgame.cmds.arguments.ArgumentException;
import lt.ekgame.cmds.impl.CommandTokenizer;

public class TestBasic {
	
	private <T> List<T> list(T... args) {
		return Arrays.asList(args);
	}

	@Test
	public void test() {
		TestEngine engine = new TestEngine();
		
		engine.command(list("ping", "p"), new TestCommandPing());
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("$ ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("q"))
				break;
			
			CommandTokenizer tokenizer = new CommandTokenizer(input);
			TestContext context = new TestContext(tokenizer);
			
			TestResult result = null;
			try {
				CmdHandler<TestContext, TestResult> handler = engine.getHandler(context);
				if (handler != null) {
					result = handler.handle(context);
				}
			} catch (ArgumentException e) {
				System.err.println(e.getMessage());
			} catch (TestResult e) {
				result = e;
			}
			
			if (result != null) {
				System.out.println(result.getMessage());
			}
		}
		
		scanner.close();
	} 
}
