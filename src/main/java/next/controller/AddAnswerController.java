package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.Controller;

public class AddAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnswerDao adao = new AnswerDao();
		Long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		System.out.println(questionId);
		System.out.println(writer);
		System.out.println(contents);
		Answer answer = new Answer(writer, contents, questionId);

		adao.insert(answer);

		QuestionDao qdao = new QuestionDao();
		qdao.updateCountOfComment(questionId);

		return "/";
	}
}
