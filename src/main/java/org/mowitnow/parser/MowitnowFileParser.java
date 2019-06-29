package org.mowitnow.parser;

import lombok.extern.slf4j.Slf4j;
import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.model.Lawn;
import org.mowitnow.model.MowerInitializationData;
import org.mowitnow.model.MowitnowAppData;
import org.mowitnow.model.Position;
import org.mowitnow.parser.expression.MowitnowExpressionParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to create a {@link MowitnowAppData} from the content of a file.
 *
 * @author Arnaud
 */
@Slf4j
public class MowitnowFileParser {

    /**
     * Create a {@link MowitnowAppData} from the content of the given file. The
     * content is a simple text following a basic format (see
     * {@link MowitnowExpressionParser}).
     *
     * @param file the file to read
     * @return an instance of the {@link MowitnowAppData}
     * @throws MowitnowParseException when the file cannot be read or parsed
     */
    public MowitnowAppData read(File file) throws MowitnowParseException {
        if (file == null) {
            throw new MowitnowParseException("Cannot read null file");
        }

        log.debug("Reading file {}", file.getAbsolutePath());
        Path path = readFilePath(file);
        List<String> lines = readLines(path);

        // now we are sure there is at least a line for the lawn, and only
        // couple of lines for each Mower

        return buildMowerAppData(lines);
    }

    /**
     * Get the path of the given file, ensures the File is readable and isn't a
     * directory.
     *
     * @param file the file to read
     * @return the given file as a path
     * @throws MowitnowParseException when the file isn't readable or is a directory
     */
    private Path readFilePath(File file) throws MowitnowParseException {
        Path path;
        try {
            path = file.toPath();
        } catch (InvalidPathException ipe) {
            throw new MowitnowParseException("Failed to convert the input File to a path", ipe);
        }
        if (!Files.exists(path)) {
            throw new MowitnowParseException(
                "Input file path doesn't exist '" + path.toAbsolutePath().toString() + "'");
        }
        if (Files.isDirectory(path)) {
            throw new MowitnowParseException("Input file is a directory '" + path.toAbsolutePath().toString() + "'");
        }
        return path;
    }

    /**
     * Extracts the line of the given file {@link Path}, ensures there are
     * enough line to read, otherwise raises an exception.
     *
     * @param path the file as a path
     * @return the list of lines from the given file
     * @throws MowitnowParseException when the lines of the files couldn't be read or there isn't
     *                                enough lines to read.
     */
    private List<String> readLines(Path path) throws MowitnowParseException {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new MowitnowParseException("Failed to read the input file", ioe);
        }

        if (lines.isEmpty()) {
            throw new MowitnowParseException("No lines to read");
        }

        int nbLinesInFile = lines.size();
        if (nbLinesInFile < 3) {
            throw new MowitnowParseException("Not enough data to read for parsing Mowitnow data");
        }
        if (nbLinesInFile % 2 != 1) {
            throw new MowitnowParseException("Invalid number of lines for parsing Mowitnow data");
        }
        return lines;
    }

    /**
     * Delegates the parsing and construction of the {@link MowitnowAppData} to a
     * specialized class which handle the parsing part.
     *
     * @param lines the content of the file
     * @return a complete instance of the {@link MowitnowAppData}
     * @throws MowitnowParseException when the parsing of the lines fails
     */
    private MowitnowAppData buildMowerAppData(List<String> lines) throws MowitnowParseException {
        try {
            return new MowitnowAppDataBuilder(lines.get(0), lines.subList(1, lines.size())).build();
        } catch (MowitnowInvalidDataException mide) {
            throw new MowitnowParseException(mide.getMessage(), mide);
        }
    }

    /**
     * Handle the parsing and the construction of the {@link MowitnowAppData}.
     *
     * @author Arnaud
     */
    static class MowitnowAppDataBuilder {

        private final String lawnLine;
        private final List<String> mowerAndInstructionsLines;

        public MowitnowAppDataBuilder(String lawnLine, List<String> mowerAndInstructionsLines) {
            super();
            this.lawnLine = lawnLine;
            this.mowerAndInstructionsLines = mowerAndInstructionsLines;
        }

        /**
         * Produces a {@link MowitnowAppData} with the given lines.
         *
         * @return a {@link MowitnowAppData} instance
         * @throws MowitnowParseException when the parsing of the lines fails
         */
        public MowitnowAppData build() throws MowitnowParseException {
            MowitnowParser<String> mowitnowParseer = new MowitnowExpressionParser();
            Lawn lawn = readLawn(mowitnowParseer, lawnLine);
            List<MowerInitializationData> mowerInitialDataList = readMowerInitialisationData(
                mowitnowParseer,
                mowerAndInstructionsLines
            );
            return new MowitnowAppData(lawn, mowerInitialDataList);
        }

        /**
         * Read the lawn line to produce a {@link Lawn} instance.
         *
         * @param mowitnowParser a String parser for reading Mowitnow content
         * @param lawnLine       the line to read
         * @return a Lawn instance
         * @throws MowitnowParseException when the parsing of the lines fails
         */
        private Lawn readLawn(MowitnowParser<String> mowitnowParser, final String lawnLine)
            throws MowitnowParseException {
            return mowitnowParser.parseLawn(lawnLine);
        }

        /**
         * Read the mower initialization data lines, a mower needs two lines to
         * be completely defined.
         *
         * @param mowitnowParser                    a String parser for reading Mowitnow content
         * @param mowerPositionAndInstructionsLines
         * @return a list of data for processing a mower
         * @throws MowitnowParseException when the parsing of the lines fails
         */
        private List<MowerInitializationData> readMowerInitialisationData(
            MowitnowParser<String> mowitnowParser,
            List<String> mowerPositionAndInstructionsLines
        ) throws MowitnowParseException {
            int nbMowers = mowerPositionAndInstructionsLines.size() / 2;
            List<MowerInitializationData> mowerInitialDataList = new ArrayList<>(nbMowers);
            for (int mowerIndex = 0; mowerIndex < nbMowers; mowerIndex++) {
                final int offset = mowerIndex * 2;
                Position mowerInitialPosition = mowitnowParser
                    .parsePosition(mowerPositionAndInstructionsLines.get(offset));
                List<Instruction> mowerInstructions = mowitnowParser
                    .parseInstructions(mowerPositionAndInstructionsLines.get(offset + 1));
                mowerInitialDataList.add(new MowerInitializationData(mowerInitialPosition, mowerInstructions));
            }

            return mowerInitialDataList;
        }
    }
}
