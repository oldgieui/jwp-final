package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class SaveController implements Controller {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao qdao = new QuestionDao();
		String writer = request.getParameter("writer").toString();
		String title = request.getParameter("title").toString();
		String contents = request.getParameter("contents").toString();
		Question question = new Question(writer, title, contents); 
		qdao.insert(question);
		return "list.next";
	}

}
