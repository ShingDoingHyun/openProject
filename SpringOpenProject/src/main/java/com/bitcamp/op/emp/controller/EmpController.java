package com.bitcamp.op.emp.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.op.emp.service.EmpListService;

@Controller
public class EmpController {

	@Autowired
	EmpListService empListService;
	
	@RequestMapping("/emp/empList")
	public String empList(Model model, @RequestParam(value="page", defaultValue="1") int pageNum) throws SQLException {

		model.addAttribute("empDetailDTOs", empListService.empList());
		return "emp/emp_list";
	}
}
