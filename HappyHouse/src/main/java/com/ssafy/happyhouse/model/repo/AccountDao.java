package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.MemberPasswordDto;
import com.ssafy.happyhouse.model.dto.MyInterestArea;

public interface AccountDao {

    public void join(AccountDto dto);
    public AccountDto login(AccountDto dto);
    public void updateMember(AccountDto dto);
    public MemberPasswordDto findPassword(AccountDto dto);
    public void delete(AccountDto dto);
    
    public void insertInterestArea(MyInterestArea myInterestArea);
    public void deleteInterestArea(String memberid);
    public MyInterestArea getInterestArea(String memberid);
    public void updateInterestArea(MyInterestArea myInterestArea);
    
    public int passwdCheck(MemberPasswordDto memberPasswordDto);
    public void changeStatus(AccountDto dto);
    
    public List<String> getUserEmail();
}
