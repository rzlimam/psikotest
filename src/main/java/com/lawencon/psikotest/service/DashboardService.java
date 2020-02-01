package com.lawencon.psikotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.DashboardDao;
import com.lawencon.psikotest.entity.POJODashboard;

@Service
public class DashboardService {
	
	@Autowired
	private DashboardDao dashboard;
	
	public POJODashboard getDashboard() {
		POJODashboard dash = new POJODashboard();
		dash.setTotalPass(dashboard.countPass().intValue());
		dash.setTotalFail(dashboard.countFail().intValue());
		dash.setTotalQuestion(dashboard.countQuestion().intValue());
		dash.setTotalPackage(dashboard.countPackage().intValue());
		return dash;
	}

}
