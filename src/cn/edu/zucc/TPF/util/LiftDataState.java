package cn.edu.zucc.TPF.util;

import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LiftDataBean;

public class LiftDataState implements DataState{

	public LiftDataState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int JudgeState(LiftBean lift, LiftDataBean liftData) {
		// TODO Auto-generated method stub
		int level = 0;
		if(Math.abs(liftData.getAccx()) > lift.getAccxLimit()) level++;
		if(Math.abs(liftData.getAccy()) > lift.getAccyLimit()) level++;
		if(Math.abs(liftData.getAccz()) > lift.getAcczLimit()) level++;
		return level;
	}

}
