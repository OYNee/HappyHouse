package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.MemberPasswordDto;
import com.ssafy.happyhouse.model.dto.MyInterestArea;

public interface AccountService {

    public void join(AccountDto dto);
    public AccountDto login(AccountDto dto);
    public void updateMember(AccountDto dto);
    public MemberPasswordDto findPswd(AccountDto dto);
    public void delete(AccountDto dto);
    
    public MyInterestArea getInterestArea(String memberid);
    
    public int passwdCheck(MemberPasswordDto memberPasswordDto);
    public void changeStatus(AccountDto dto);
}
