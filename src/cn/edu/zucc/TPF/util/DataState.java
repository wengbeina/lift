package cn.edu.zucc.TPF.util;

import cn.edu.zucc.TPF.Bean.LiftBean;
import cn.edu.zucc.TPF.Bean.LiftDataBean;

public interface DataState {
   /**�ж������Ƿ��쳣�����ҷ����쳣�ȼ�*/
   public int JudgeState(LiftBean lift, LiftDataBean bean);
}
