package fyy.ygame_frame.base.broadcast;

/**
*<b>�㲥���սӿ�</b>
*
*<p>
*<b>����</b>��
*����<b>�㲥�ӿ�</b>{@link YIBroadcast}��������Ϣ��
*
*<p>
*<b>ע</b>��
*����������Ϣ�����ڴ���ͬһ��<b>�㲥�ӿ�</b>{@link YIBroadcast}������<b>�㲥���սӿ�</b>��
*������ӦΪ����<b>���սӿ�</b>��״̬�仯��
*
*@author FeiYiyun
*
*/
public interface YIReceiver
{
	/**�����յ�<b>�㲥�ӿ�</b>{@link YIBroadcast}��������Ϣʱ���ú������ص���
	*@param iMsgKey ��Ϣ��ֵ
	*@param objectDetailMsg ��Ϣ��ϸ����
	*/
	void onReceiveBroadcastMsg(int iMsgKey, Object objectDetailMsg);

	/**����������<b>�㲥�ӿ�</b>{@link YIBroadcast}
	*@param broadcast ������<b>�㲥�ӿ�</b>
	*/
	void setBroadcast(YIBroadcast broadcast);
}
