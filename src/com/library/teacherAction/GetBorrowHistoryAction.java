package com.library.teacherAction;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Borrow;
import com.opensymphony.xwork2.Action;

public class GetBorrowHistoryAction implements Action {
	List<Borrow> BorrowHistory = new ArrayList<Borrow>();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Actions actions = new Actions();
		BorrowHistory= actions.getBorrowHistory();
		
		ServletActionContext.getResponse().getWriter().print("getSuccess");
		return null;
	}

	public List<Borrow> getBorrowHistory() {
		return BorrowHistory;
	}

	public void setBorrowHistory(List<Borrow> borrowHistory) {
		BorrowHistory = borrowHistory;
	}

}
