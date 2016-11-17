package status;

/**
 * Created by koki on 11/15/16.
 */
public enum ResponseStatus
{
	OK(200),
	INTERNAL_SERVER_ERROR(500);

	int statusCode;

	ResponseStatus(int statusCode)
	{
		this.statusCode = statusCode;
	}

	public int getStatusCode()
	{
		return statusCode;
	}
}
