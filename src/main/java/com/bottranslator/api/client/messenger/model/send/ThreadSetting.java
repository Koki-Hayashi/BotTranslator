package com.bottranslator.api.client.messenger.model.send;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadSetting
{
	@JsonProperty("setting_type")
	private String settingType;

	@JsonProperty("thread_state")
	private String threadState;

	@JsonProperty("greeting")
	private Greeting greeting;

	@JsonProperty("call_to_actions")
	private CallToAction[] callToActions;

	public String getSettingType()
	{
		return settingType;
	}

	public void setSettingType(String settingType)
	{
		this.settingType = settingType;
	}

	public String getThreadState()
	{
		return threadState;
	}

	public void setThreadState(String threadState)
	{
		this.threadState = threadState;
	}

	public Greeting getGreeting()
	{
		return greeting;
	}

	public void setGreeting(Greeting greeting)
	{
		this.greeting = greeting;
	}

	public CallToAction[] getCallToActions()
	{
		return callToActions;
	}

	public void setCallToActions(CallToAction[] callToActions)
	{
		this.callToActions = callToActions;
	}

	@Override
	public String toString()
	{
		return "ThreadSetting{" +
			"settingType='" + settingType + '\'' +
			", threadState='" + threadState + '\'' +
			", greeting=" + greeting +
			", callToActions=" + Arrays.toString(callToActions) +
			'}';
	}
}
