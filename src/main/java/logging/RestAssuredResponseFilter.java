package logging;

import com.epam.reportportal.listeners.LogLevel;
import com.utils.ReportPortalLogging;

import context.ScenarioContext;

import enums.GlobalVariables;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RestAssuredResponseFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAssuredResponseFilter.class);
    protected ScenarioContext scenarioContext;

    public RestAssuredResponseFilter() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        try {
            Response response = ctx.next(requestSpec, responseSpec);
            // NOSONAR
            String responseLog =
                    ResponsePrinter.print(
                            response,
                            response,
                            generateStream(),
                            LogDetail.ALL,
                            true,
                            new HashSet<>());
            String logMessage = String.format("RESPONSE DATA:%n%s", responseLog);
            //            LOGGER.info(logMessage);
            scenarioContext.setContext(GlobalVariables.DATA_RESPONSE, logMessage);
            //            ReportPortalLogging.dispatchLog(logMessage, LogLevel.INFO.toString());
            ReportPortalLogging.dispatchLaunchLog(logMessage, LogLevel.INFO.toString());
            return response;
        } catch (Exception e) {
            List<String> errorStackTrace =
                    Arrays.stream(e.getStackTrace())
                            .map(StackTraceElement::toString)
                            .collect(Collectors.toList());
            String errorStackTraceMsg = StringUtils.join(errorStackTrace, "\n");
            //            ReportPortalLogging.dispatchLog(
            //                    String.format("%s%n%s", e.getMessage(), errorStackTraceMsg),
            //                    LogLevel.ERROR.toString());
            ReportPortalLogging.dispatchLaunchLog(
                    String.format("%s%n%s", e.getMessage(), errorStackTraceMsg),
                    LogLevel.ERROR.toString());
            throw e;
        }
    }

    private PrintStream generateStream() {
        return new PrintStream(System.out) {
            @Override
            public void print(final String string) {
                //                System.out.println(string);
                LOGGER.info(string);
            }
        };
    }
}
