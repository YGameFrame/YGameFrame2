package fyy.ygame_frame.base;

import fyy.ygame_frame.extra.YITask;
import fyy.ygame_frame.extra.YRequest;

/**
*<b>��ͨ��Ϸ�߼�</b>
*
*<p>
*<b>����</b>��
*�̳���<b>��Ϸ�߼�</b>{@link YGameView}����һ����Ϸ�г����߼���
*
*<p>
*<b>ע</b>��
*Ӧ��������������������������Ϊ��Ӧ<b>��Ϸ��ͼ</b>�ı�ǩ��
*
*@author FeiYiyun
*
*/
public final class YNormalGameLogic extends YAGameLogic
{

	@Override
	protected void onReceiveRequest(YRequest rqstUIorGame)
	{
		if (null != rqstUIorGame.domainLogicReceivers)
			// Ŀ�Ķ���ǿգ������߼���������
			for (YABaseDomainLogic<?> dmn_lgc : rqstUIorGame.domainLogicReceivers)
				dmn_lgc.onDealRequest(rqstUIorGame);
	}

	@Override
	protected YITask[] onSubmitCurrentTasks(YABaseDomainLogic<?> dmn_lgcKey)
	{
		return dmn_lgcKey.onSubmitCurrentTasks();
	}

	@Override
	protected void onAddDomainLogic(YABaseDomainLogic<?> dmn_lgcKey)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRemoveDomainLogic(YABaseDomainLogic<?> dmn_lgcKey)
	{
		// TODO Auto-generated method stub

	}
}
