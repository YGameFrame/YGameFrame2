package fyy.ygame_frame.base;

import android.util.Log;
import fyy.ygame_frame.base.broadcast.YIBroadcast;
import fyy.ygame_frame.base.broadcast.YIReceiver;

/**
*<b>����㲥</b>
*
*<p>
*<b>����</b>��
*ʵ����<b>�㲥�ӿ�</b>{@link YIBroadcast}������<b>ͬһ��</b><b><i>��Ϸʵ��</i></b>����������
*����<b>����</b>{@link YABaseDomainData}��<b>�߼�</b>{@link YABaseDomainLogic}��<b>��ͼ</b>
*{@link YABaseDomainView}֮���ͨ�š�
*
*<p>
*<b>ע</b>��
*����ù㲥�ı�����<b>ͬһ��</b>ʵ�������������
*
*@author FeiYiyun
*
*/
public class YDomainBroadcast implements YIBroadcast
{
	private static final String strTag = "YDomainBroadcast";
	private final YIReceiver[] receivers;

	/**��������Ϊͬһ��<b><i>��Ϸʵ��</i></b>���������������ɶ�������ڡ�
	*@param receivers ͬһ<b><i>��Ϸʵ��</i></b>����������
	*/
	public YDomainBroadcast(YIReceiver... receivers)
	{
		if (3 != receivers.length)
		{
			Log.e(strTag, "����㲥��Ӧ��ֻ��Ӷ�Ӧ��ͬһ����Ϸʵ���������������ǰ�����" + receivers.length
					+ "������������" + "�Ƿ����ӻ�������˲�����");
			throw new IllegalArgumentException("����㲥���ʵ�岿���쳣");
		}
		this.receivers = receivers;
		for (YIReceiver receiver : receivers)
		{
			receiver.setBroadcast(this);
			receiver.onReceiveBroadcastMsg(
					YGameEnvironment.BroadcastMsgKey.MSG_DOMAIN_FINISHED, null);
		}
	}

	public void send(int iMsgKey, Object objectDetailMsg, YIReceiver rcvrSend)
	{
		for (YIReceiver receiver : receivers)
			if (rcvrSend != receiver)
				receiver.onReceiveBroadcastMsg(iMsgKey, objectDetailMsg);
	}

}
