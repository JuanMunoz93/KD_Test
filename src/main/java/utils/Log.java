package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Log {
    private Log() {
    }

    private static final Level defaultLevel = Level.INFO;

    private static final String CONSOLE_LOGGER_NAME = "Console";
    private static final String CONSOLE_APPENDER_NAME = "CONSOLE_APPENDER";


    private static final String TEST_LOGFILE_NAME = "TestLog.log";
    private static final String TEST_LOGGER_NAME = "Test";
    private static final String TESTS_APPENDER_NAME = "TEST_APPENDER";


    private static final LoggerContext context = (LoggerContext) LogManager.getContext(false);
    private static final Configuration logConfiguration = context.getConfiguration();
    private static ConsoleAppender consoleAppender = null;
    private static final AppenderRef refConsole = AppenderRef.createAppenderRef(CONSOLE_APPENDER_NAME, defaultLevel, null);


    public static final Logger LOGGER = LogManager.getLogger(TEST_LOGGER_NAME);

    public static void initLogs(String evidenceDirectory, String projectFolder) {
        Path evidencePath= Paths.get(evidenceDirectory);
        PatternLayout layout = createPattern();
        configureBasicLoggers(evidencePath, projectFolder, layout);
        context.updateLoggers();
    }

    private static void configureBasicLoggers(Path evidenceDirectory, String projectFolder, PatternLayout layout) {
        consoleAppender = createConsoleAppender(layout);
        LoggerConfig consoleConfig = createConsoleLogger(consoleAppender);
        logConfiguration.addLogger(CONSOLE_LOGGER_NAME, consoleConfig);
        logConfiguration.addAppender(consoleAppender);

        FileAppender testAppender = createTestAppender(layout, evidenceDirectory, projectFolder);
        LoggerConfig testConfig = createTestLogger(testAppender);
        logConfiguration.addAppender(testAppender);
        logConfiguration.addLogger(TEST_LOGGER_NAME, testConfig);
    }


    public static PatternLayout createPattern() {
        String logPattern = "%d{yyyy-MMM-dd HH:mm:ss} [%logger{36}] %-5level : %msg%n%throwable";
        return PatternLayout.newBuilder()
                .withPattern(logPattern)
                .withConfiguration(logConfiguration)
                .withCharset(StandardCharsets.UTF_8)
                .withAlwaysWriteExceptions(false)
                .build();
    }

    public static ConsoleAppender createConsoleAppender(PatternLayout layout) {
        ConsoleAppender console = ConsoleAppender.newBuilder()
                .setName(CONSOLE_APPENDER_NAME)
                .withImmediateFlush(true)
                .setIgnoreExceptions(false)
                .withBufferedIo(true)
                .withBufferSize(4000)
                .setLayout(layout)
                .setConfiguration(logConfiguration)
                .build();
        console.start();
        return console;
    }

    public static LoggerConfig createConsoleLogger(ConsoleAppender console) {
        AppenderRef[] refsConsole = new AppenderRef[]{refConsole};
        LoggerConfig consoleConfig = LoggerConfig.createLogger(false, Level.INFO, CONSOLE_LOGGER_NAME, null, refsConsole, null, logConfiguration, null);
        consoleConfig.addAppender(console, defaultLevel, null);
        return consoleConfig;
    }

    public static FileAppender createTestAppender(PatternLayout layout, Path evidenceDirectory, String projectFolder) {
        FileAppender test = FileAppender.newBuilder()
                .withFileName(evidenceDirectory.resolve(projectFolder).resolve(TEST_LOGFILE_NAME).toString())
                .withAdvertise(true)
                .withLocking(false)
                .setName(TESTS_APPENDER_NAME)
                .withImmediateFlush(true)
                .setIgnoreExceptions(false)
                .withBufferedIo(true)
                .withBufferSize(4000)
                .withAdvertise(false)
                .withLayout(layout)
                .setConfiguration(logConfiguration)
                .build();
        test.start();
        return test;
    }

    public static LoggerConfig createTestLogger(FileAppender testAppender) {
        AppenderRef refTest = AppenderRef.createAppenderRef(TESTS_APPENDER_NAME, defaultLevel, null);
        AppenderRef[] refsTest = new AppenderRef[]{refTest, refConsole};
        LoggerConfig testsConfig = LoggerConfig.createLogger(false, Level.DEBUG, TEST_LOGGER_NAME, "true", refsTest, null, logConfiguration, null);
        testsConfig.addAppender(testAppender, defaultLevel, null);
        testsConfig.addAppender(consoleAppender, defaultLevel, null);
        return testsConfig;
    }
}
