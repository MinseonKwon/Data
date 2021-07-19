package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Controller
public class HelloController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CountryDao countryDao;
	
	private String server_folder = "C:\\spring\\";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	// 넘겨받은 id, pw따라 로그인 체크 후 맞으면 ok, 틀리면 no 전송
	@SuppressWarnings({ "unchecked", "null" }) //경고
	@ResponseBody // 리턴되는 값이 View(jsp파일 등)를 통해 출력이 아닌 http body에 직접 쓰여지게 됨
	@RequestMapping(value = "/loginCheck", produces = "application/json; charset=utf-8", method = {RequestMethod.GET, RequestMethod.POST}) //logincheck라는 객체로
	public JSONObject loginCheck(Model model, @RequestParam(value = "id", required=false) String id,
			@RequestParam(value = "password", required=false) String pw) {

		System.out.println("loginCheck 실행");

		// id따른 Member 정보 받아옴
		Member result = memberDao.selectMemberById(id);
		
		// 결과값 JSON으로 보내기
		JSONObject jsonMain = new JSONObject(); // json 객체 [{변수명:값, 변수명:값}
		JSONArray jArray = new JSONArray(); // json 배열

		JSONObject row = new JSONObject(); //json 객체

		if (pw.equals(result.getPassword())) {
			System.out.println("로그인 잘됨");
			row.put("result", "ok");
		} else {
			System.out.println("로그인 잘못됨");
			row.put("result", "no");
		}
		jArray.add(0, row); //  0  {"result","ok"}  ....
		jsonMain.put("login_result", jArray); //-->jArray가 포함된 키가 "login_result"인 jsonObject
		return jsonMain; //를 반환
	}

	// 넘겨받은 id, pw, name,age에 따라 insert 후 맞으면 ok, 틀리면 no 전송
	@SuppressWarnings({ "unchecked", "null" })
	@ResponseBody
	@RequestMapping(value = "/insertToMembers", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public JSONObject insertToMembers(Model model, @RequestParam(value = "id") String id,
			@RequestParam(value = "password") String pw, @RequestParam(value = "name") String name, @RequestParam(value = "age") String age ) {

		System.out.println("insertToMembers 실행");

		// member 추가하기
		int result = memberDao.insertToMember(id, pw, name,age);

		// 결과값 JSON으로 보내기
		JSONObject jsonMain = new JSONObject(); // json 객체 [{변수명:값, 변수명:값}
		JSONArray jArray = new JSONArray(); // json 배열

		JSONObject row = new JSONObject();

		if (result > 0) {
			System.out.println("member 추가함");
			row.put("result", "ok");
		} else {
			System.out.println("member 추가못함");
			row.put("result", "no");
		}

		jArray.add(0, row);
		jsonMain.put("register_result", jArray);
		return jsonMain;
	}
	
	//넘겨 받은 id와 c_name에 따라 insert 후 맞으면 ok 아니면 no 전송
	@SuppressWarnings({ "unchecked", "null" })
	@ResponseBody
	@RequestMapping(value = "/insertTolist", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public JSONObject insertToGlist(Model model, @RequestParam(value = "id") String id, @RequestParam(value = "c_name") String c_name, @RequestParam(value="date") String date,
			@RequestParam(value="budget") String budget, @RequestParam(value="food") String food, @RequestParam(value="shopping") String shopping, @RequestParam(value="tour") String tour,
			@RequestParam(value="traffic") String traffic, @RequestParam(value="stay") String stay, @RequestParam(value="etc") String etc) {

		System.out.println("insertTolist 실행");
		System.out.println(id+", "+c_name);
		
		// 나라 목록 추가하기
		int result = countryDao.insertToCountry(id, c_name,date,budget,food,shopping,tour,traffic,stay,etc);
		
		System.out.println(result);
		
		// 결과값 JSON으로 보내기
		JSONObject jsonMain = new JSONObject(); // json 객체 [{변수명:값, 변수명:값}
		JSONArray jArray = new JSONArray(); // json 배열

		JSONObject row = new JSONObject();

		if (result > 0) {
			System.out.println("re_list에 추가함");
			row.put("result", "ok");
		} else {
			System.out.println("re_list에 추가못함");
			row.put("result", "no");
		}

		jArray.add(0, row);
		jsonMain.put("list_result", jArray);
		return jsonMain;
	}
	
	//목록을 띄우기위해 db에서 id에따른 나라의 개수와 나라이름, 날짜, 예산을 보냄
	@SuppressWarnings({ "unchecked", "null" }) //경고
	@ResponseBody // 리턴되는 값이 View(jsp파일 등)를 통해 출력이 아닌 http body에 직접 쓰여지게 됨
	@RequestMapping(value = "/re_list", produces = "application/json; charset=utf-8", method = {RequestMethod.GET, RequestMethod.POST}) //logincheck라는 객체로
	public JSONObject loginCheck(Model model, @RequestParam(value = "id", required=false) String id) {
		
		System.out.println("re_list 실행");
		
		//id에 따른 나라정보를 받아옴
		List<Country> result = countryDao.selectCountryById(id);
		System.out.println("id에 따른 나라 개수 : "+result.size());
		
		int count = result.size();
		
		//결과 값 JSON으로 보내기
		JSONObject jsonMain = new JSONObject();
		JSONArray jArray = new JSONArray();
		
	
		if(result.size()!=0) {
			for(int i=0;i<count;i++) {
				JSONObject row = new JSONObject();
				Country rs = result.get(i);
				System.out.println("나라 목록 불러오기");
				
				row.put("result", result.size());
				row.put("c_name", rs.getc_Name());
				row.put("date", rs.getDate());
				row.put("budget", rs.getBudget());
				
				jArray.add(i,row);
			}
		}
		else {
			JSONObject row = new JSONObject();
			row.put("result", count);
			jArray.add(0,row);
		}
		jsonMain.put("re_list", jArray);
		System.out.println(jsonMain);
		return jsonMain;
		
	}
}
