package com.twistlet.falcon.model.service;

import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Service
public class SmsServiceImpl implements SmsService {

	private final String smsGatewayLocation;
	private final DatabaseLoggingService databaseLoggingService;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public SmsServiceImpl(
			@Value("${sms.location}") final String smsGatewayLocation,
			final DatabaseLoggingService databaseLoggingService) {
		this.smsGatewayLocation = smsGatewayLocation;
		this.databaseLoggingService = databaseLoggingService;
	}

	@Override
	public void send(final String sendTo, final String message) {
		final WebClient webClient = new WebClient();
		String errorMessage = "";
		try {
			final String urlEncodedMessage = URLEncoder
					.encode(message, "UTF-8");
			final String location = MessageFormat.format(smsGatewayLocation,
					sendTo, urlEncodedMessage);
			logger.info("Invoking: {}", location);
			final HtmlPage htmlPage = webClient.getPage(location);
			final String content = htmlPage.asText();
			if (!StringUtils.trimToEmpty(content).startsWith("1701:")) {
				errorMessage = content;
			}
		} catch (final Exception e) {
			final String delimiter = (errorMessage.equals("") ? "" : ", ");
			errorMessage = errorMessage + delimiter + e.toString();
			e.printStackTrace();
		} finally {
			databaseLoggingService.logSmsSent(sendTo, message, errorMessage);
			webClient.closeAllWindows();
		}
	}
}