package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.MemberPasswordDto;
import com.ssafy.happyhouse.model.dto.MyInterestArea;
import com.ssafy.happyhouse.model.repo.AccountDao;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private SqlSession sqlSession;


	@Override
	public void join(AccountDto dto) {
		sqlSession.insert("accountMapper.join", dto);
	}

	@Override
	public AccountDto login(AccountDto dto) {
		return sqlSession.selectOne("accountMapper.login", dto);
	}

	@Override
	public void updateMember(AccountDto dto) {
		sqlSession.update("accountMapper.updateMember", dto);
	}

	@Override
	public MemberPasswordDto findPassword(AccountDto dto) {
		return sqlSession.selectOne("accountMapper.searchPasswd", dto);
	}

	@Override
	public void delete(AccountDto dto) {
		sqlSession.delete("accountMapper.deleteMember", dto);
		
	}

	@Override
	public void insertInterestArea(MyInterestArea myInterestArea) {
		sqlSession.insert("accountMapper.insertProfile", myInterestArea);
	}

	@Override
	public void deleteInterestArea(String memberid) {
		sqlSession.delete("accountMapper.deleteProfile", memberid);
	}


	@Override
	public void updateInterestArea(MyInterestArea myInterestArea) {
		sqlSession.update("accountMapper.updateProfile", myInterestArea);
	}


	@Override
	public MyInterestArea getInterestArea(String memberid) {
		return sqlSession.selectOne("accountMapper.getProfile", memberid);
	}

	@Override
	public int passwdCheck(MemberPasswordDto memberPasswordDto) {
		return sqlSession.selectOne("accountMapper.passwdCheck", memberPasswordDto);
	}

	@Override
	public void changeStatus(AccountDto dto) {
		sqlSession.update("accountMapper.changeStatus", dto);
	}

	@Override
	public List<String> getUserEmail() {
		return sqlSession.selectList("accountMapper.getUserEmail");
	}
}
