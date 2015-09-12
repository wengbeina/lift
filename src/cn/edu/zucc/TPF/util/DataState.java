package cn.edu.zucc.TPF.util;

import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LiftDataBean;

public interface DataState {
   /**判断数据是否异常，并且返回异常等级*/
   public int JudgeState(LiftBean lift, LiftDataBean bean);
}
