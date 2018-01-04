package com.finessy.web.ques_ans;

import java.sql.SQLException;
import java.util.ArrayList;

import com.finessy.web.commonDAO.CommonDAO;
import com.finessy.web.notifications.NotificationSQL;
import com.sun.crypto.provider.RSACipher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuesAnsDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public ArrayList<Integer> findAnswerIDs(int questionId) throws ClassNotFoundException, SQLException {
		
		ArrayList<Integer> answerIDArrayList = new ArrayList<Integer>();
		try {
			
			connection = CommonDAO.getConnection();
			preparedStatement = connection.prepareStatement(QuesAnsSQL.FIND_ANSWER_ID);
			preparedStatement.setInt(2, questionId);
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.isBeforeFirst() ) { 
				answerIDArrayList.add(0);
				return answerIDArrayList;
			} 
			
			while(resultSet.next()) {
				answerIDArrayList.add(resultSet.getInt(1));
			}
			return answerIDArrayList;
			
		}finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
	}
	public ArrayList<Integer> findRelatedQuestionIDs(int questionId) throws ClassNotFoundException, SQLException {
		
		ArrayList<Integer> relatedQuestionIDs = new ArrayList<Integer>();
		try {
			
			connection = CommonDAO.getConnection();
			preparedStatement = connection.prepareStatement(QuesAnsSQL.FIND_RELATED_QUESTIONIDs);
			preparedStatement.setInt(1, questionId);
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.isBeforeFirst() ) { 
				relatedQuestionIDs.add(0);
				return relatedQuestionIDs;
			} 
			
			while(resultSet.next()) {
				relatedQuestionIDs.add(resultSet.getInt(1));
			}
			return relatedQuestionIDs;
			
		}finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
	}
	
	public ArrayList<QuesAnsDTO> findAnswerDetails(int questionID) throws ClassNotFoundException, SQLException {
		
		ArrayList<Integer> answerIDArrayList = new ArrayList<Integer>();
		answerIDArrayList = findAnswerIDs(questionID);
		ArrayList<QuesAnsDTO> ansDTOs = new ArrayList<QuesAnsDTO>();
		
		try { 
			connection = CommonDAO.getConnection();
			preparedStatement = connection.prepareStatement(QuesAnsSQL.FIND_ANSWER_DETAILS);
			
			for(Integer i: answerIDArrayList) {
				preparedStatement.setInt(1, i);
				resultSet = preparedStatement.executeQuery();
			
				while(resultSet.next()) {
				QuesAnsDTO quesAnsDTO = new QuesAnsDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
				ansDTOs.add(quesAnsDTO);
				}	
			}
		}finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return ansDTOs;
	}
	
	
	public ArrayList<String> findRelatedQuestions(int questionID) throws ClassNotFoundException, SQLException{
		
		ArrayList<Integer> relatedQIDArrayList = new ArrayList<Integer>();
		relatedQIDArrayList = findRelatedQuestionIDs(questionID);
		ArrayList<String> relatedQDetails = new ArrayList<String>();
		
		connection = CommonDAO.getConnection();
		preparedStatement = connection.prepareStatement(QuesAnsSQL.FIND_RELATED_QUESTION_DETAILS);
		
		try {
			for(Integer i: relatedQIDArrayList) {
				
				preparedStatement.setInt(1, i);
				resultSet = preparedStatement.executeQuery();
				
				if(!resultSet.isBeforeFirst()) {
					relatedQDetails.add(null);
					return relatedQDetails;
				}
				while(resultSet.next()) {
					relatedQDetails.add(resultSet.getString(1));
				}
				//return relatedQDetails;
			}
		}
		finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}
		return relatedQDetails;
	}
}
		