package com.utils;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.google.api.client.util.ArrayMap;

import io.cucumber.core.gherkin.DataTableArgument;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Step;
import io.cucumber.plugin.event.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.util.*;

import javax.annotation.Nonnull;

public class ReportPortalLogging extends ScenarioReporter {

    private static final String LAUNCH_PROPERTY = "SG_TEST_AUTO";
    private static final List<Map<String, String>> callableSendLogWithExecutorService =
            new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportPortalLogging.class);

    public static void dispatchLog(String logMessage, String logLevel) {
        LOGGER.info(logMessage);
        ReportPortal.emitLog(logMessage, logLevel, generateTimeStamp());
    }

    public static void appendExecutorServiceLog(String logMessage, String logLevel) {
        Map<String, String> executorServiceCallBackLog = new ArrayMap<>();
        executorServiceCallBackLog.put(logLevel, logMessage);
        callableSendLogWithExecutorService.add(executorServiceCallBackLog);
    }

    public static void sendExecutorServiceLog() {
        callableSendLogWithExecutorService.forEach(
                log ->
                        log.forEach(
                                (key, value) ->
                                        ReportPortal.emitLog(value, key, generateTimeStamp())));
        callableSendLogWithExecutorService.clear();
    }

    public static void dispatchLogWithAttachment(String logMessage, String logLevel, File file) {
        LOGGER.info(logMessage);
        ReportPortal.emitLog(logMessage, logLevel, generateTimeStamp(), file);
    }

    public static void dispatchLaunchLog(String logMessage, String logLevel) {
        ReportPortal.emitLaunchLog(logMessage, logLevel, generateTimeStamp());
    }

    private static Date generateTimeStamp() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Extension point to customize ReportPortal instance
     *
     * @return ReportPortal
     */
    @Override
    protected ReportPortal buildReportPortal() {
        String launchName = System.getenv(LAUNCH_PROPERTY);
        boolean emptyReportLaunchTimeInEnv = launchName == null || launchName.length() == 0;
        if (emptyReportLaunchTimeInEnv) {
            return ReportPortal.builder().build();
        }
        ReportPortal rp = ReportPortal.builder().build();
        rp.getParameters().setLaunchName(launchName);
        return rp;
    }

    @Override
    @Nonnull
    protected String getDescription(@Nonnull TestCase testCase, @Nonnull URI uri) {
        String markDownTemplate = "# File Location:\n%s\n# Scenario:\n%s";
        return String.format(markDownTemplate, uri, buildScenarioDescription(testCase));
    }

    @Override
    protected StartTestItemRQ buildStartScenarioRequest(
            TestCase testCase, String name, URI uri, int line) {
        return super.buildStartScenarioRequest(testCase, testCase.getName(), uri, line);
    }

    private String buildScenarioDescription(TestCase testCase) {
        StringBuilder scenario = new StringBuilder();
        testCase.getTestSteps()
                .forEach(
                        testStep -> {
                            String testStepsType = testStep.getClass().getName();
                            boolean isPickleStepTestStep =
                                    !testStepsType.equalsIgnoreCase(
                                            "io.cucumber.core.runner.HookTestStep");
                            if (isPickleStepTestStep) {
                                PickleStepTestStep step = (PickleStepTestStep) testStep;
                                Step featureStep = step.getStep();
                                DataTableArgument stepArgument =
                                        (DataTableArgument) featureStep.getArgument();
                                scenario.append(featureStep.getText()).append("\n");
                                if (stepArgument != null)
                                    scenario.append(buildTableDescription(stepArgument.cells()))
                                            .append("\n");
                            }
                        });
        return scenario.toString();
    }

    private String buildTableDescription(List<List<String>> cells) {
        StringBuilder dataTableArgument = new StringBuilder();
        for (List<String> columns : cells) {
            dataTableArgument.append("|");
            for (String cell : columns) {
                dataTableArgument.append(cell);
                dataTableArgument.append("|");
            }
            dataTableArgument.append("\n");
        }
        return dataTableArgument.toString();
    }
}
