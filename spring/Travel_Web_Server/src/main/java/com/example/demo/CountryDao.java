package com.example.demo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CountryDao {
	private JdbcTemplate jdbcTemplate;
	
	public CountryDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//id에 따른 country 정보 리턴
	public List<Country> selectCountryById(String id) {
		System.out.println("select * from country where id=\"" + id + "\"");
		List<Country> results = jdbcTemplate.query("select * from country where id=\"" + id + "\"",
				new RowMapper<Country>() {
					@Override
					public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
						Country country = new Country(rs.getString("id"), rs.getString("c_name"), rs.getString("date"), rs.getString("budget"), rs.getString("food"), rs.getString("shopping"), rs.getString("tour"), rs.getString("traffic"), rs.getString("stay"), rs.getString("etc"));
						return country;
					}
				});
		return results;
	}
	
	//country 추가
	public int insertToCountry(String id, String c_name,String date,String budget,String food, String shopping, String tour, String traffic, String stay, String etc) {
		int result=0;
		
		try {
			String query = "insert into country values(\"" + id + "\", \"" + c_name + "\", \"" + date + "\", \"" + Integer.parseInt(budget) + "\", \"" + Integer.parseInt(food) + "\", \"" + Integer.parseInt(shopping) + "\", \"" +Integer.parseInt(tour) + "\", \"" + Integer.parseInt(traffic) +"\", \"" + Integer.parseInt(stay) + "\", \"" + Integer.parseInt(etc) +"\")";
			System.out.println(query);
			result = jdbcTemplate.update(query);
		}catch (Exception e) {
			System.out.println("insertCountry 오류");
		}
		return result;
	}
	
}
