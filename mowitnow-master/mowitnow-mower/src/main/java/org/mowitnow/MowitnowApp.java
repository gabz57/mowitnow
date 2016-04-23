package org.mowitnow;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.model.Mower;
import org.mowitnow.model.MowitnowAppData;
import org.mowitnow.parser.MowitnowFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the Mowitnow test from Xebia.
 * 
 * @author Arnaud
 *
 */
public class MowitnowApp {

	/**
	 * Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(MowitnowApp.class);

	/**
	 * The application model.
	 */
	private final MowitnowAppData mowerAppData;

	/**
	 * Constructor with the required application model.
	 * 
	 * @param mowerAppData
	 *            the application model
	 */
	public MowitnowApp(MowitnowAppData mowerAppData) {
		if (mowerAppData == null) {
			throw new IllegalArgumentException("An instance of the MowitnowAppData is required");
		}
		this.mowerAppData = mowerAppData;
	}

	/**
	 * Application entry point, a single argument is required, and is simply the
	 * path to a file containing the Mowitnow program to execute.
	 * 
	 * @param args
	 *            the path to a Mowitnow file
	 */
	public static void main(String[] args) {

		if (args == null || args.length != 1 || args[0].length() == 0) {
			log.error("Missing input file path parameter");
			return;
		}

		MowitnowAppData mowerAppData = null;
		try {
			mowerAppData = new MowitnowFileParser().read(new File(args[0]));
		} catch (MowitnowParseException | MowitnowInvalidDataException e) {
			log.error("Failed to read Mowitnow data from input file : " + e.getMessage(), e);
			return;
		}

		log.debug("MowitnowApp start");

		new MowitnowApp(mowerAppData).run();

		log.debug("MowitnowApp end");

	}

	/**
	 * Executes the mowers instructions following a sequential order.
	 */
	public void run() {
		// Initialize the mower(s) on the lawn
		Map<Mower, List<Instruction>> mowersWithInstructions = mowerAppData.getMowerInitialDataList().stream()
				.collect(Collectors.toMap(initData -> new Mower(mowerAppData.getLawn(), initData.getInitialPosition()),
						initData -> initData.getInstructions()));
		// Process them sequentially
		mowersWithInstructions.forEach((mower, instructions) -> {
			log.info("Processing mower, initial position : {}", mower.getPosition());
			mower.processInstructions(instructions);
			log.info("Processed  mower, final position   : {}", mower.getPosition());
		});

	}

}
