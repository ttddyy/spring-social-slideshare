package org.springframework.social.slideshare.api.impl.xml;

/**
 * @author Tadaya Tsuyukubo
 */
public class SlideShareServiceError {
	//<SlideShareServiceError>
//	<Message ID="0">No API Key Provided</Message>
//	</SlideShareServiceError>

	public static class Message {
		private Integer id;
		private String value;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public SlideShareServiceErrorCode getErrorCode() {
		if (this.message != null) {
			return SlideShareServiceErrorCode.valueOf(this.message.getId());
		}
		return null;
	}

	private Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
