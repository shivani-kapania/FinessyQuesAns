package com.finessy.web.ques_ans;

public class QuesAnsDTO {
	private String desc;
	private Integer postBy;
	private Integer answerID;
	private String postOn;
	public QuesAnsDTO(Integer answerID, String desc, Integer postBy, String postOn) {
		super();
		this.answerID = answerID;
		this.desc = desc;
		this.postBy = postBy;
		this.postOn = postOn;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getPostBy() {
		return postBy;
	}
	public void setPostBy(Integer postBy) {
		this.postBy = postBy;
	}
	public Integer getAnswerID() {
		return answerID;
	}
	public void setAnswerID(Integer answerID) {
		this.answerID = answerID;
	}
	public String getPostOn() {
		return postOn;
	}
	public void setPostOn(String postOn) {
		this.postOn = postOn;
	}
	@Override
	public String toString() {
		return "QuesAnsDTO [answerID=" + answerID + ", desc=" + desc + ", postBy=" + postBy + ", postOn=" + postOn
				+ "]";
	}
	
	
}
