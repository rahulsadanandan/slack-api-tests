package com.slack.autotest.api.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.time.Instant;
import java.util.List;

import com.slack.autotest.api.service.Services;

public class ChannelStepDefinitions {

	String createdChannelID;
	String createdChannelName;
	Services services = new Services();

	// this is to confirm that even after failed tests, the created channels are
	// archived
	void archiveAllMatchingChannels(String channelName) {
		Response listChannelResponse = services.channelService().listAllChannels();
		System.out.println(listChannelResponse.asString());
		List<String> allChannelNames = listChannelResponse.jsonPath().getList("channels.name");
		List<String> allChannelIDs = listChannelResponse.jsonPath().getList("channels.id");
		for (int i = 0; i < allChannelNames.size(); i++) {
			if (allChannelNames.get(i).contains(channelName)) {
				Response response = services.channelService().archiveChannel(allChannelIDs.get(i));
				if ((Boolean) response.path("ok")) {
					System.out.println("Matching channel deleted");
				}
			}
		}
	}

	@Given("I create and join a new channel {string}")
	public void create_channel(String channelName) {
		archiveAllMatchingChannels(channelName);

		long now = Instant.now().toEpochMilli();
		createdChannelName = channelName + now;
		Response response = services.channelService().createChannel(createdChannelName);
		createdChannelID = response.path("channel.id");
		Assert.assertTrue("Channel not created",
				(Boolean) response.path("ok") && (Boolean) response.path("channel.is_member") && response.getStatusCode() == 200);
	}

	@And("I rename the created channel")
	public void rename_channel() {
		Response response = services.channelService().renameChannel(createdChannelID, createdChannelName + "renamed");
		Assert.assertTrue("Channel not renamed", (Boolean) response.path("ok") && response.getStatusCode() == 200);
	}

	@Then("I should list all Channels and Validate if the Channel name has changed successfully")
	public void verify_channel_renamed() {
		Response listChannelResponse = services.channelService().listAllChannels();
		List<String> allChannelNames = listChannelResponse.jsonPath().getList("channels.name");
		Boolean found = false;
		for (String ele : allChannelNames) {
			if (ele.contains(createdChannelName + "renamed")) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("Cannot verify renamed channel in list", found);
	}

	@And("I archive the created channel")
	public void archive_channel() {
		Response response = services.channelService().archiveChannel(createdChannelID);
		Assert.assertTrue("Channel not renamed", (Boolean) response.path("ok") && response.getStatusCode() == 200);
	}

	@Then("I should Validate if the Channel is archived successfully")
	public void verify_channel_archived() {

		Response listChannelResponse = services.channelService().listAllChannels();
		List<String> allChannelNames = listChannelResponse.jsonPath().getList("channels.name");
		Boolean found = false;
		for (String ele : allChannelNames) {
			if (ele.contains(createdChannelName + "renamed")) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("Cannot verify the channel is archived", !found);
	}

}
