package com.slack.autotest.api.service;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import com.slack.autotest.api.config.ConfigReader;

public class ChannelService {

	public Response createChannel(String channelName) {

		Response response = given().log().all().formParam("token", ConfigReader.getProperty("token"))
				.formParam("name", channelName)

				.when().post(ConfigReader.getProperty("baseUrl") + "/conversations.create").then().extract().response();

		return response;

	}

	public Response listAllChannels() {

		Response response = given().log().all().param("token", ConfigReader.getProperty("token"))
				.param("exclude_archived", true)

				.when().get(ConfigReader.getProperty("baseUrl") + "/conversations.list").then().extract().response();

		return response;

	}

	public Response archiveChannel(String channelID) {

		Response response = given().log().all().formParam("token", ConfigReader.getProperty("token"))
				.formParam("channel", channelID)

				.when().post(ConfigReader.getProperty("baseUrl") + "/conversations.archive").then().extract()
				.response();

		return response;

	}

	public Response renameChannel(String channelID, String newName) {

		Response response = given().log().all().formParam("token", ConfigReader.getProperty("token"))
				.formParam("channel", channelID).formParam("name", newName)

				.when().post(ConfigReader.getProperty("baseUrl") + "/conversations.rename").then().extract().response();

		return response;

	}

}
