package com.slack.autotest.api.service;

public class Services {

	public Services() {

	}

	private ChannelService channelService;

	public ChannelService channelService() {

		if (channelService == null) {
			channelService = new ChannelService();
		}
		return channelService;
	}

}
