package next.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;
import core.mvc.JsonCreator;

public class MobileListController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		List<Question> questions;
		questions = questionDao.findAll();
		JsonCreator jc = JsonCreator.getInstance();
		ArrayList<String> jsons = new ArrayList<String>();
		for (Question question : questions) {
			String json = jc.createArray(String.valueOf(question
					.getQuestionId()), question.getTitle(), question
					.getWriter(), question.getCreatedDate().toString());
			jsons.add(json);
		}
		request.setAttribute("jsonDatas", jsons);
		return "../mobileList.jsp";
	}

}
