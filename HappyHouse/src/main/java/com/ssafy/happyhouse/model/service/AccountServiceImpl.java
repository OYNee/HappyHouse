package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.MemberPasswordDto;
import com.ssafy.happyhouse.model.dto.MyInterestArea;
import com.ssafy.happyhouse.model.repo.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountDao accountDao;


    @Override
    public void join(AccountDto dto) {
        accountDao.join(dto);
        accountDao.insertInterestArea(dto.getMyInterestArea());
    }

    @Override
    public AccountDto login(AccountDto dto) {
        if (dto.getUserid() == null || dto.getPasswd() == null) {
            throw new IllegalArgumentException();
        }
        AccountDto accountDto = accountDao.login(dto);
        accountDto.setMyInterestArea(accountDao.getInterestArea(accountDto.getUserid()));
        System.out.println(accountDto.getMyInterestArea().toString());
        return accountDto;
    }

    @Override
    public void updateMember(AccountDto dto) {
        accountDao.updateMember(dto);
        accountDao.updateInterestArea(dto.getMyInterestArea());
    }

    @Override
    public MemberPasswordDto findPswd(AccountDto dto) {
        return accountDao.findPassword(dto);
    }

    @Override
    public void delete(AccountDto dto) {
    	accountDao.deleteInterestArea(dto.getUserid());
        accountDao.delete(dto);
    }

    @Override
	public MyInterestArea getInterestArea(String memberid) {
		return accountDao.getInterestArea(memberid);
	}

	@Override
	public int passwdCheck(MemberPasswordDto memberPasswordDto) {
		return accountDao.passwdCheck(memberPasswordDto);
	}

	@Override
	public void changeStatus(AccountDto dto) {
		accountDao.changeStatus(dto);
	}

}
