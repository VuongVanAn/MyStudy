package com.vietis.task.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vietis.task.dto.ExerciseQuestDTO;
import com.vietis.task.dto.ResultDTO;
import com.vietis.task.service.ExerciseQuestService;
import com.vietis.task.service.ResultService;

@Controller
public class ExerciseQuestController {
	
	@Autowired
	private ExerciseQuestService questService;
	
	@Autowired
	private ResultService resultService;
	
	@GetMapping("/exercise/{id}/{userId}")
	public String findOne(@PathVariable Integer id, @PathVariable Integer userId,
			              Model model, HttpServletRequest request) {
		List<ExerciseQuestDTO> questList = questService.findOne(id);
		ExerciseQuestDTO dto = questList.get(0);
		if (dto.getUserId() != userId) return "authorize";
		model.addAttribute("exerciseId", id);
		model.addAttribute("questList", questList);
		return "client/exercise";
	}
	
	@PostMapping("/exercise/result/{id}")
	public String resultExam(@PathVariable Integer id, @RequestParam Map<String, String> requestMapper, Model model) {
		List<ExerciseQuestDTO> listQuest = questService.findOne(id);
		for (ExerciseQuestDTO item : listQuest) {
			if (requestMapper.get("answerUser["+ item.getId() +"]") != null) {
				item.setAnswerUser(requestMapper.get("answerUser["+ item.getId() +"]"));
			}
		}
		ResultDTO dto = resultService.saveResult(listQuest, id);
		model.addAttribute("result", "Your mark is: " + dto.getScore());
		model.addAttribute("questList", listQuest);
		return "client/result";
	}
	
	@GetMapping("/exercise/history")
	public String historyPage(Model model, HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("MY_LOGIN");
		if (id == null) return "authorize";
		List<ResultDTO> listResult = resultService.findAll(id);
		model.addAttribute("listResult", listResult);
		return "client/history";
	}
}
