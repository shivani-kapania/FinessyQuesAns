package com.finessy.web.ques_ans;

public interface QuesAnsSQL {
	
	String FIND_ANSWER_ID = "select answer_id from answers where question_id=?;";
	
	String FIND_ANSWER_DETAILS = "select * from answer_details where answer_id=?;";
	
	String FIND_RELATED_QUESTIONIDs = "select question_id,description from questions_details where tags in (select tags from questions_details where question_id=?);";
	
	String FIND_RELATED_QUESTION_DETAILS = "select description from questions_details where question_id=?;";
}
